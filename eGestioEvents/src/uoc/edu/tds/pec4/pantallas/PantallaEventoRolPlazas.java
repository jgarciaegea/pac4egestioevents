package uoc.edu.tds.pec4.pantallas;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import uoc.edu.tds.pec4.beans.Inscripcion;
import uoc.edu.tds.pec4.dtos.DTOEvento;
import uoc.edu.tds.pec4.dtos.DTOEventoRequisitos;
import uoc.edu.tds.pec4.dtos.DTOEventoRolPlazas;
import uoc.edu.tds.pec4.dtos.DTOInscripcion;
import uoc.edu.tds.pec4.dtos.DTOTipoEvento;
import uoc.edu.tds.pec4.dtos.DTOTipoEventoRol;
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
public class PantallaEventoRolPlazas extends javax.swing.JDialog {
	private JPanel jPanelBase;
	private JScrollPane jScrollPane1;
	private JButton jButtonLimpiar;
	private JButton jButtonCancelar;
	private JButton jButtonAceptar;
	private JTable jTableDatos;
	private String[] columnNames = {"idRol", "Rol", "Plazas"};
	private DefaultTableModel dtm;
	
	private RemoteInterface remote;
	private DTOTipoEvento dtoTipoEvento;
	private List<DTOEventoRolPlazas> lstDtoEventoRolPlazas = new ArrayList<DTOEventoRolPlazas>();
	private Integer plazas;

	public PantallaEventoRolPlazas(JFrame frame, RemoteInterface remote1, DTOTipoEvento dtoTipoEvento1, List<DTOEventoRolPlazas> lstDtoEventoRolPlazas1, Integer plazas1) {
		super(frame);
		initGUI(frame);
		this.remote = remote1;
		this.dtoTipoEvento = dtoTipoEvento1;
		this.lstDtoEventoRolPlazas = lstDtoEventoRolPlazas1;
		this.plazas = plazas1;
		
		if (dtoTipoEvento != null && dtoTipoEvento.getTipoEvento() != null){
			try
			{
				// TODO 1: si el tipo de evento no tiene tripoeventorol pues no hacemos nada y salimos
				cargaDatosRoles();
				//cargaRolPlazasActual();
			} catch (OperationErrorDatosFormulario e3) {
				e3.showDialogError(jPanelBase);
			}
		}
	}

	private void cargaDatosRoles() throws OperationErrorDatosFormulario{
		/*	try{
			
			dtoTipoEvento = remote.getTiposEvento(dtoTipoEvento);
			if(dtoTipoEvento == null){
				Utils.mostraMensajeInformacion(jPanelBase, "El Tipo de evento no se puede consultar", "Evento Rol/Plazas");
				return;
			}
			
			for(DTOTipoEventoRol dtoTipoEventoRol : dtoTipoEvento.getDtoTipoEventoRol()){
				dtoTipoEventoRol.ge
			}
			// rellenar la tabla con los tipos evento y ponerle los plazas de la lista y checkeado loas q esten
			
			for(DTOEventoRolPlazas dtoEventoRolPlazas : lstDtoEventoRolPlazas){
				dtoEventoRolPlazas.getEventoRolPlazas().getIdRol()
			}
			
			jLabelEvento.setText(dtoEvento.getEvento().getNombre());
			jLabelCodigo.setText(dtoEvento.getEvento().getIdEvento().toString());
		}catch(Exception e){
			throw new OperationErrorDatosFormulario("Error en la carga de los datos del evento en asistencia/ausencia");
		}*/		
	}

	/*
	 * Parametrizamos el DTOInscripcion a consultar
	 * @return
	 * @throws OperationErrorDatosFormulario
	 */
		/*
	private DTOInscripcion consultaInscripcion() throws OperationErrorDatosFormulario{
		//Rellenamos la inscripcion
		DTOInscripcion dtoInscripcion = new DTOInscripcion();
		Inscripcion inscripcion = new Inscripcion();
		inscripcion.setIdEvento(dtoEvento.getEvento().getIdEvento());
		dtoInscripcion.setInscripcion(inscripcion);	

		return dtoInscripcion;
	}
	
	private void cargaInscripcionesByEvento() throws OperationErrorDatosFormulario{
		try{
			dtm.getDataVector().removeAllElements();
			List<DTOInscripcion> lstDtoInscripcion = remote.getInscripcionesByEventoFinalizado(consultaInscripcion());
			if(lstDtoInscripcion == null || lstDtoInscripcion.isEmpty()){
				Utils.mostraMensajeInformacion(jPanelBase, "Evento no finalizado", "Asistencia/Ausencia del evento");
				return;
			}
			muestraResultado(lstDtoInscripcion);
			actualizaTabla();
		}catch(Exception e){
			throw new OperationErrorDatosFormulario("Error en la carga de la asistencia/ausencia");
		}		
	}
	*/
	private void actualizaTabla(){
		jTableDatos.repaint();
		jTableDatos.revalidate();
		jTableDatos.updateUI();
	}
	
	/*
	 * Mostramos el resultado obtenido
	 */
	private void muestraResultado(List<DTOInscripcion> lstDtoInscripcion) throws OperationErrorDatosFormulario{
		try{
			dtm.getDataVector().removeAllElements();
		     
			Object[][] aobj = new Object[lstDtoInscripcion.size()][columnNames.length];
			int k = 0;
			if(lstDtoInscripcion != null){
				for(DTOInscripcion dtoInscripcion : lstDtoInscripcion){
					 aobj[k][0] = new String(dtoInscripcion.getInscripcion().getCodigoAsistencia());
					 aobj[k][1] = new String(dtoInscripcion.getDtoAsistente().getUsuario().getNombreCompleto());
					 aobj[k][2] = new String(dtoInscripcion.getInscripcion().getFechaInscripcion().toString());
					 aobj[k][2] = new String(dtoInscripcion.getInscripcion().getAsistencia());
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
		this.setTitle("Evento Rol/Plazas");
		this.setModal(true);
		this.setLocationRelativeTo(frame);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		try {
			{
				jPanelBase = new JPanel();
				getContentPane().add(jPanelBase, BorderLayout.CENTER);
				jPanelBase.setPreferredSize(new java.awt.Dimension(618, 446));
				{
					jScrollPane1 = new JScrollPane();
					jPanelBase.add(jScrollPane1);
					jScrollPane1.setPreferredSize(new java.awt.Dimension(443, 295));
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
				{
					jButtonAceptar = new JButton();
					jPanelBase.add(jButtonAceptar);
					jButtonAceptar.setLayout(null);
					jButtonAceptar.setText("Aceptar");
					jButtonAceptar.setFont(new java.awt.Font("Arial",0,10));
					jButtonAceptar.setBounds(275, 359, 90, 25);
					jButtonAceptar.setPreferredSize(new java.awt.Dimension(94, 32));
					jButtonAceptar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							// TODO 1: recorrer la tabla y pasar un dtoeventorolplaza
						}
					});
				}
				{
					jButtonCancelar = new JButton();
					jPanelBase.add(jButtonCancelar);
					jButtonCancelar.setLayout(null);
					jButtonCancelar.setText("Cancelar");
					jButtonCancelar.setFont(new java.awt.Font("Arial",0,10));
					jButtonCancelar.setPreferredSize(new java.awt.Dimension(94,32));
					jButtonCancelar.setBounds(275, 359, 90, 25);
					jButtonCancelar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							dispose();
						}
					});
				}
				{
					jButtonLimpiar = new JButton();
					jPanelBase.add(jButtonLimpiar);
					jButtonLimpiar.setLayout(null);
					jButtonLimpiar.setText("Limpiar");
					jButtonLimpiar.setFont(new java.awt.Font("Arial",0,10));
					jButtonLimpiar.setPreferredSize(new java.awt.Dimension(94,32));
					jButtonLimpiar.setBounds(275, 359, 90, 25);
					jButtonLimpiar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							// TODO 1: poner todos las plazas a 0
						}
					});
				}
			}
			this.setSize(482, 371);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void jButtonCancelarActionPerformed(ActionEvent evt) {
		System.out.println("jButtonCancelar.actionPerformed, event="+evt);
		//TODO add your code for jButtonCancelar.actionPerformed
	}
	
	private void jButtonLimpiarActionPerformed(ActionEvent evt) {
		System.out.println("jButtonLimpiar.actionPerformed, event="+evt);
		//TODO add your code for jButtonLimpiar.actionPerformed
	}

}
