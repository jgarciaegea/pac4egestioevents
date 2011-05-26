package uoc.edu.tds.pec4.pantallas;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

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
import uoc.edu.tds.pec4.resources.TDSLanguageUtils;
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
	private JButton jButtonBaja;
	private JButton jButtonCodigoAssistencia;
	private JButton jButtonCerrar;
	private JTable jTableDatos;
	private JLabel jLabelCodigo;
	private JLabel jLabelCodigoText;
	private JLabel jLabelEvento;
	private String[] columnNames = {"cAsistente",TDSLanguageUtils.getMessage("clientePEC4.asistenciabyevento.colum1"),TDSLanguageUtils.getMessage("clientePEC4.asistenciabyevento.colum2")};
	private DefaultTableModel dtm;
	
	private RemoteInterface remote;
	private DTOEvento dtoEvento;
	private Boolean bIsEmpty = false;

	/**
	* Auto-generated main method to display this JDialog
	*/
		
	public PantallaInscripcionesByEvento(JFrame frame, RemoteInterface remote1, DTOEvento dtoEvento1, Boolean bUpdate) {
		super(frame);
		initGUI();
		this.remote = remote1;
		this.dtoEvento = dtoEvento1;
		
		if (dtoEvento != null && dtoEvento.getEvento() != null){
			try
			{
				jButtonBaja.setVisible(bUpdate);
				jButtonCodigoAssistencia.setVisible(bUpdate);
				cargaDatosEvento();
				cargaInscripcionesByEvento();
				bIsEmpty = (jTableDatos.getRowCount() == 0);
			} catch (OperationErrorDatosFormulario e3) {
				bIsEmpty = true;
				e3.showDialogError(jPanelBase);
			}
		}
	}
	
	public Boolean isEmpty(){
		return bIsEmpty;
	}

	private DTOInscripcion getSelectedInscripcion() throws OperationErrorDatosFormulario{
		try {
			@SuppressWarnings("unchecked")
			List<Object> lstRes = (Vector<Object>) dtm.getDataVector().get(jTableDatos.getSelectedRow());
			Inscripcion inscripcion = new Inscripcion();
			DTOInscripcion dtoInscripcion = new DTOInscripcion();
			inscripcion.setCodigo(lstRes.get(0).toString());
			inscripcion.setIdEvento(dtoEvento.getEvento().getIdEvento());
			dtoInscripcion.setInscripcion(inscripcion);
			return dtoInscripcion;
		} catch (NumberFormatException e1) {
			throw new OperationErrorDatosFormulario(e1.getMessage());
		} catch (Exception e1) {
			throw new OperationErrorDatosFormulario(e1.getMessage());
		}
	}
	
	/*
	private void setSelectedInscripcionCodigoAsistencia(String codigoAsistencia){
		jTableDatos.setValueAt(new String(codigoAsistencia), jTableDatos.getSelectedRow(), 1);
	}*/
	
	private void cargaDatosEvento() throws OperationErrorDatosFormulario{
		try{
			dtoEvento = remote.getEvento(dtoEvento);
			if(dtoEvento == null){
				Utils.mostraMensajeInformacion(jPanelBase, TDSLanguageUtils.getMessage("clientePEC4.panelInscripcion.LABEL7"), TDSLanguageUtils.getMessage("clientePEC4.panelInscripcion.LABEL8"));
				return;
			}
			jLabelEvento.setText(dtoEvento.getEvento().getNombre());
			jLabelCodigo.setText(dtoEvento.getEvento().getIdEvento().toString());
		}catch(Exception e){
			throw new OperationErrorDatosFormulario(TDSLanguageUtils.getMessage("clientePEC4.panelInscripcion.LABEL9"));
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
				Utils.mostraMensajeInformacion(jPanelBase, TDSLanguageUtils.getMessage("clientePEC4.panelInscripcion.LABEL10"), TDSLanguageUtils.getMessage("clientePEC4.panelInscripcion.LABEL8"));
				return;
			}
			muestraResultado(lstDtoInscripcion);
			actualizaTabla();
		}catch(Exception e){
			throw new OperationErrorDatosFormulario(TDSLanguageUtils.getMessage("clientePEC4.panelInscripcion.ERROR1"));
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
					 aobj[k][0] = new String(dtoInscripcion.getDtoAsistente().getUsuario().getCodigo());
					 aobj[k][1] = new String(dtoInscripcion.getInscripcion().getCodigoAsistencia());
					 aobj[k][2] = new String(dtoInscripcion.getDtoAsistente().getUsuario().getNombreCompleto());
					 k++;
	       	 	}
				
				if (aobj != null && aobj.length > 0){
	       	 		for(int row = 0; row < aobj.length; row++){
	       	 			dtm.addRow(aobj[row]);
	       	 		}
	       	 	}
			}
		}catch(Exception e){
			throw new OperationErrorDatosFormulario(TDSLanguageUtils.getMessage("clientePEC4.panelInscripcion.ERROR2"));
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
						jLabelCodigoText.setText(TDSLanguageUtils.getMessage("clientePEC4.asistenciabyevento.colum1"));
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
					jPanelBase.add(getJButtonBaja());
					jPanelBase.add(getJButtonCodigoAssistencia());
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
						Utils.ocultaColumna(jTableDatos, 0);
						jTableDatos.setPreferredSize(new java.awt.Dimension(538, 278));
					}
				}
				{
					jButtonCerrar = new JButton();
					jPanelBase.add(jButtonCerrar);
					jButtonCerrar.setLayout(null);
					jButtonCerrar.setText(TDSLanguageUtils.getMessage("clientePEC4.panelInscripcion.boton1.botonCerrar"));
					jButtonCerrar.setFont(new java.awt.Font("Arial",0,10));
					jButtonCerrar.setBounds(275, 359, 90, 25);
					jButtonCerrar.setPreferredSize(new java.awt.Dimension(146, 32));
					jButtonCerrar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
						    dispose(); 
						}
					});
				}
			}
			this.setSize(618, 440);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private JButton getJButtonCodigoAssistencia() {
		if(jButtonCodigoAssistencia == null) {
			jButtonCodigoAssistencia = new JButton();
			jButtonCodigoAssistencia.setLayout(null);
			jButtonCodigoAssistencia.setText(TDSLanguageUtils.getMessage("clientePEC4.panelInscripcion.boton2"));
			jButtonCodigoAssistencia.setFont(new java.awt.Font("Arial",0,10));
			jButtonCodigoAssistencia.setPreferredSize(new java.awt.Dimension(164, 32));
			jButtonCodigoAssistencia.setBounds(275, 359, 90, 25);
			jButtonCodigoAssistencia.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					jButtonCodigoAssistenciaActionPerformed(evt);
				}
			});
		}
		return jButtonCodigoAssistencia;
	}
	
	private void jButtonCodigoAssistenciaActionPerformed(ActionEvent evt) {
		System.out.println("jButtonCodigoAssistencia.actionPerformed, event="+evt);
		if (jTableDatos.getSelectedRow() == -1) {
			Utils.mostraMensajeInformacion(jPanelCentro, TDSLanguageUtils.getMessage("clientePEC4.consultausuario.INFO.MSG1"), TDSLanguageUtils.getMessage("clientePEC4.panelInscripcion.LABEL11"));
		}else{
			try {
				DTOInscripcion dtoInscripcion = getSelectedInscripcion();
				dtoInscripcion = remote.getInscripcion(dtoInscripcion);
				dtoInscripcion = remote.getCodigoAsistentica(dtoInscripcion);
				Utils.mostraMensajeInformacion(jPanelCentro, TDSLanguageUtils.getMessage("clientePEC4.panelInscripcion.LABEL12") + ": " + dtoInscripcion.getInscripcion().getCodigoAsistencia(), TDSLanguageUtils.getMessage("clientePEC4.panelInscripcion.LABEL11"));
				cargaInscripcionesByEvento(); //setSelectedInscripcionCodigoAsistencia(dtoInscripcion.getInscripcion().getCodigoAsistencia());
			} catch (Exception e1) {
				try {
					throw new OperationErrorDatosFormulario(e1.getMessage());
				} catch (OperationErrorDatosFormulario e2) {
					e2.showDialogError();
				}
			}
		}
	}
	
	private JButton getJButtonBaja() {
		if(jButtonBaja == null) {
			jButtonBaja = new JButton();
			jButtonBaja.setLayout(null);
			jButtonBaja.setText(TDSLanguageUtils.getMessage("clientePEC4.panelInscripcion.LABEL13"));
			jButtonBaja.setFont(new java.awt.Font("Arial",0,10));
			jButtonBaja.setPreferredSize(new java.awt.Dimension(164, 32));
			jButtonBaja.setBounds(275, 359, 90, 25);
			jButtonBaja.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					jButtonBajaActionPerformed(evt);
				}
			});
		}
		return jButtonBaja;
	}
	
	private void jButtonBajaActionPerformed(ActionEvent evt) {
		System.out.println("jButtonBaja.actionPerformed, event="+evt);
		if (jTableDatos.getSelectedRow() == -1) {
			Utils.mostraMensajeInformacion(jPanelCentro, TDSLanguageUtils.getMessage("clientePEC4.consultausuario.INFO.MSG1"), TDSLanguageUtils.getMessage("clientePEC4.panelInscripcion.LABEL11"));
		}else{
			try {
				DTOInscripcion dtoInscripcion = getSelectedInscripcion();
				remote.bajaInscripcion(dtoInscripcion);
				Utils.mostraMensajeInformacion(jPanelCentro, TDSLanguageUtils.getMessage("clientePEC4.panelInscripcion.LABEL14"), TDSLanguageUtils.getMessage("clientePEC4.panelInscripcion.LABEL11"));
				cargaInscripcionesByEvento();
			} catch (Exception e1) {
				try {
					throw new OperationErrorDatosFormulario(e1.getMessage());
				} catch (OperationErrorDatosFormulario e2) {
					e2.showDialogError();
				}
			}
		}
	}

}
