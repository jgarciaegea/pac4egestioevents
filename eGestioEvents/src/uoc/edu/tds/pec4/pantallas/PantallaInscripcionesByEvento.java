package uoc.edu.tds.pec4.pantallas;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import uoc.edu.tds.pec4.dtos.DTOInscripcion;
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
public class PantallaInscripcionesByEvento extends javax.swing.JDialog {
	private static final long serialVersionUID = -1494522497192077912L;
	private JPanel jPanelBase;
	private JPanel jPanelCentro;
	private JLabel jLabelEventoText;
	private JScrollPane jScrollPane1;
	private JButton jButtonCerrar;
	private JTable jTableDatos;
	private JLabel jLabelCodigo;
	private JLabel jLabelCodigoText;
	private JLabel jLabelEvento;
	private String[] columnNames = {"C—digo","Asistente"};
	private DefaultTableModel dtm;
	
	private RemoteInterface remote;
	private DTOEvento dtoEvento;

	/**
	* Auto-generated main method to display this JDialog
	*/
		
	public PantallaInscripcionesByEvento(JFrame frame, RemoteInterface remote1, DTOEvento dtoEvento1) {
		super(frame);
		initGUI();
		this.remote = remote1;
		this.dtoEvento = dtoEvento1;
		
		if (dtoEvento != null && dtoEvento.getEvento() != null){
			try
			{
				cargaInscripcionesByEvento();
			} catch (OperationErrorDatosFormulario e3) {
				e3.showDialogError(jPanelBase);
			}
		}
	}

	/*
	 * Parametrizamos el DTOInscripcion a consultar
	 * @return
	 * @throws OperationErrorDatosFormulario
	 */
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
			List<DTOInscripcion> lstDtoInscripcion = remote.getInscripciones(consultaInscripcion());
			if(lstDtoInscripcion == null || lstDtoInscripcion.isEmpty()){
				Utils.mostraMensajeInformacion(jPanelBase, "No existen inscripciones", "Inscripciones del evento");
				return;
			}
			muestraResultado(lstDtoInscripcion);
			actualizaTabla();
		}catch(Exception e){
			throw new OperationErrorDatosFormulario("Error en la carga de las inscripciones");
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
	private void muestraResultado(List<DTOInscripcion> lstDtoInscripcion) throws OperationErrorDatosFormulario{
		try{
			dtm.getDataVector().removeAllElements();
		     
			Object[][] aobj = new Object[lstDtoInscripcion.size()][columnNames.length];
			int k = 0;
			if(lstDtoInscripcion != null){
				for(DTOInscripcion dtoInscripcion : lstDtoInscripcion){
					 aobj[k][0] = new String(dtoInscripcion.getInscripcion().getCodigoAsistencia());
					 aobj[k][1] = new String(dtoInscripcion.getDtoAsistente().getUsuario().getNombreCompleto());
					 k++;
	       	 	}
				
				if (aobj != null && aobj.length > 0){
	       	 		for(int row = 0; row < aobj.length; row++){
	       	 			dtm.addRow(aobj[row]);
	       	 		}
	       	 	}
			}
		}catch(Exception e){
			throw new OperationErrorDatosFormulario("Error en la carga de las inscripciones en la pantalla");
		}
	}
	private void initGUI() {
		try {
			{
				jPanelBase = new JPanel();
				getContentPane().add(jPanelBase, BorderLayout.CENTER);
				jPanelBase.setPreferredSize(new java.awt.Dimension(618, 418));
				{
					jPanelCentro = new JPanel();
					jPanelBase.add(jPanelCentro);
					jPanelCentro.setFont(new java.awt.Font("Arial",0,10));
					jPanelCentro.setPreferredSize(new java.awt.Dimension(559, 62));
					jPanelCentro.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
					jPanelCentro.setBounds(17, 32, 744, 58);
					jPanelCentro.setLayout(null);
					{
						jLabelEventoText = new JLabel();
						jPanelCentro.add(jLabelEventoText);
						jLabelEventoText.setText("Evento");
						jLabelEventoText.setFont(new java.awt.Font("Arial",1,12));
						jLabelEventoText.setLayout(null);
						jLabelEventoText.setBounds(30, 14, 40, 14);
					}
					{
						jLabelEvento = new JLabel();
						jPanelCentro.add(jLabelEvento);
						jLabelEvento.setText("xxxxxx");
						jLabelEvento.setFont(new java.awt.Font("Arial",0,10));
						jLabelEvento.setBounds(30, 34, 334, 13);
					}
					{
						jLabelCodigoText = new JLabel();
						jPanelCentro.add(jLabelCodigoText);
						jLabelCodigoText.setText("C—digo");
						jLabelCodigoText.setFont(new java.awt.Font("Arial",1,12));
						jLabelCodigoText.setBounds(429, 14, 40, 14);
					}
					{
						jLabelCodigo = new JLabel();
						jPanelCentro.add(jLabelCodigo);
						jLabelCodigo.setText("xxxxxx");
						jLabelCodigo.setFont(new java.awt.Font("Arial",0,10));
						jLabelCodigo.setBounds(429, 34, 30, 13);
					}
				}
				{
					jScrollPane1 = new JScrollPane();
					jPanelBase.add(jScrollPane1);
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
				{
					jButtonCerrar = new JButton();
					jPanelBase.add(jButtonCerrar);
					jButtonCerrar.setLayout(null);
					jButtonCerrar.setText("Cerrar");
					jButtonCerrar.setFont(new java.awt.Font("Arial",0,10));
					jButtonCerrar.setBounds(275, 359, 90, 25);
					jButtonCerrar.setPreferredSize(new java.awt.Dimension(94, 32));
					jButtonCerrar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
						    dispose(); 
						}
					});
				}
			}
			this.setSize(618, 468);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
