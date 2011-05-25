package uoc.edu.tds.pec4.pantallas;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import uoc.edu.tds.pec4.beans.Evento;
import uoc.edu.tds.pec4.beans.Inscripcion;
import uoc.edu.tds.pec4.beans.Usuario;
import uoc.edu.tds.pec4.dtos.DTOEvento;
import uoc.edu.tds.pec4.dtos.DTOInscripcion;
import uoc.edu.tds.pec4.excepciones.OperationErrorBD;
import uoc.edu.tds.pec4.excepciones.OperationErrorDatosFormulario;
import uoc.edu.tds.pec4.iface.RemoteInterface;
import uoc.edu.tds.pec4.utils.Utils;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class PantallaEventoRequisitos extends javax.swing.JDialog {
	private JPanel jPanelBase;
	private JScrollPane jScrollPane1;
	private JButton jButtonAceptar;
	private JButton jButtonCancelar;
	private JButton jButtonLimpiar;
	private JTable jTableDatos;
	private String[] columnNames = {"idEvento", "Evento", "Requisito"};
	private DefaultTableModel dtm;
	
	private RemoteInterface remote;
	private DTOEvento dtoEvento;

	/**
	* Auto-generated main method to display this JDialog
	*/
		
	public PantallaEventoRequisitos(JFrame frame, RemoteInterface remote1, DTOEvento dtoEvento1) {
		super(frame);
		initGUI(frame);
		this.remote = remote1;
		this.dtoEvento = dtoEvento1;
		
		if (dtoEvento != null && dtoEvento.getEvento() != null){
			// TODO 1: cargar todos los eventos ya celebrados de ese centro
			try {
				cargaEventosByEventoCentro();
			} catch (OperationErrorDatosFormulario e) {
				e.showDialogError(jPanelBase);
			}
			// TODO 2: a partir del dtoevento (lista dtoeventoreq) checkear los q esten dentro.
		}
	}

	private DTOEvento consultaEvento() {
		DTOEvento dtoEventroCentro = new DTOEvento();
		Evento eventoCentro = new Evento();
		eventoCentro.setIdCentro(dtoEvento.getEvento().getIdCentro());
		dtoEventroCentro.setEvento(eventoCentro);

		return dtoEventroCentro;
	}
	
	private void cargaEventosByEventoCentro() throws OperationErrorDatosFormulario{
		try{
			dtm.getDataVector().removeAllElements();
			List<DTOEvento> lstDtoEvento = remote.getEventosFinalizados(consultaEvento());
			if(lstDtoEvento == null || lstDtoEvento.isEmpty()){
				Utils.mostraMensajeInformacion(jPanelBase, "No hay eventos", "Asistencia/Ausencia del evento");
				return;
			}
			muestraResultado(lstDtoEvento);
			actualizaTabla();
		}catch(Exception e){
			throw new OperationErrorDatosFormulario("Error en la carga de la asistencia/ausencia");
		}		
	}
	
	private void actualizaTabla(){
		jTableDatos.repaint();
		jTableDatos.revalidate();
		jTableDatos.updateUI();
	}
	
	/*
	 * Mostramos el resultado obtenido
	 */
	private void muestraResultado(List<DTOEvento> lstDtoEvento) throws OperationErrorDatosFormulario{
		try{
			dtm.getDataVector().removeAllElements();
		     
			Object[][] aobj = new Object[lstDtoEvento.size()][columnNames.length];
			int k = 0;
			if(lstDtoEvento != null){
				for(DTOEvento dtoEvento : lstDtoEvento){
					 aobj[k][0] = new String(dtoEvento.getEvento().getIdEvento().toString());
					 aobj[k][1] = new String(dtoEvento.getEvento().getDescripcion());
					 aobj[k][2] = new Boolean(false);
					 k++;
	       	 	}
				
				if (aobj != null && aobj.length > 0){
	       	 		for(int row = 0; row < aobj.length; row++){
	       	 			dtm.addRow(aobj[row]);
	       	 		}
	       	 	}
			}
		}catch(Exception e){
			throw new OperationErrorDatosFormulario("Error en la carga de la asistencia/ausencia en la pantalla");
		}
	}
	private void initGUI(JFrame frame) {
		this.setTitle("Eventos requisitos");
		this.setModal(true);
		this.setLocationRelativeTo(frame);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		try {
			{
				jPanelBase = new JPanel();
				getContentPane().add(jPanelBase, BorderLayout.CENTER);
				jPanelBase.setPreferredSize(new java.awt.Dimension(618, 418));
				{
					jScrollPane1 = new JScrollPane();
					jPanelBase.add(jScrollPane1);
					jPanelBase.add(getJButtonAceptar());
					jPanelBase.add(getJButtonCancelar());
					jPanelBase.add(getJButtonLimpiar());
					jScrollPane1.setPreferredSize(new java.awt.Dimension(559, 295));
					{
						dtm = new DefaultTableModel();
						{
							for(int i=0;i<columnNames.length;i++){
					        	dtm.addColumn(columnNames[i]);
					        }
							
						}
						jTableDatos = new JTable(dtm);
						jScrollPane1.setViewportView(jTableDatos);
						jTableDatos.setPreferredSize(new java.awt.Dimension(538, 278));
					}
				}
			}
			this.setSize(618, 378);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private JButton getJButtonLimpiar() {
		if(jButtonLimpiar == null) {
			jButtonLimpiar = new JButton();
			jButtonLimpiar.setLayout(null);
			jButtonLimpiar.setText("Limpiar");
			jButtonLimpiar.setFont(new java.awt.Font("Arial",0,10));
			jButtonLimpiar.setPreferredSize(new java.awt.Dimension(92, 40));
			jButtonLimpiar.setBounds(275, 359, 90, 25);
			jButtonLimpiar.setSize(90, 40);
			jButtonLimpiar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					// TODO 1: poner todos a checked false
				}
			});
		}
		return jButtonLimpiar;
	}
	
	private JButton getJButtonCancelar() {
		if(jButtonCancelar == null) {
			jButtonCancelar = new JButton();
			jButtonCancelar.setLayout(null);
			jButtonCancelar.setText("Cancelar");
			jButtonCancelar.setFont(new java.awt.Font("Arial",0,10));
			jButtonCancelar.setPreferredSize(new java.awt.Dimension(92, 40));
			jButtonCancelar.setBounds(275, 359, 90, 25);
			jButtonCancelar.setSize(90, 40);
			jButtonCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					dispose();
				}
			});
		}
		return jButtonCancelar;
	}
	
	private JButton getJButtonAceptar() {
		if(jButtonAceptar == null) {
			jButtonAceptar = new JButton();
			jButtonAceptar.setLayout(null);
			jButtonAceptar.setText("Aceptar");
			jButtonAceptar.setFont(new java.awt.Font("Arial",0,10));
			jButtonAceptar.setPreferredSize(new java.awt.Dimension(96, 40));
			jButtonAceptar.setBounds(275, 359, 90, 25);
			jButtonAceptar.setSize(90, 40);
			jButtonAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// TODO 1: recorrer la tabla y pasar un dtoeventorequisitos
				}
			});
		}
		return jButtonAceptar;
	}

}
