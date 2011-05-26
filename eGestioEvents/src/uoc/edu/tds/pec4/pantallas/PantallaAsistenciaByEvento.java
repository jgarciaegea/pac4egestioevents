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
public class PantallaAsistenciaByEvento extends javax.swing.JDialog {
	private static final long serialVersionUID = 2300404848702162002L;
	private static final Integer COL_CODIGO = 4;
	private static final Integer COL_CHECKIN = 5;
	
	private JPanel jPanelBase;
	private JPanel jPanelCentro;
	private JLabel jLabelEventoText;
	private JScrollPane jScrollPane1;
	private JPanel jPanel1;
	private JButton jButtonCheckIN;
	private JButton jButtonCerrar;
	private JTable jTableDatos;
	private JLabel jLabelCodigo;
	private JLabel jLabelCodigoText;
	private JLabel jLabelEvento;
	private String[] columnNames = {TDSLanguageUtils.getMessage("clientePEC4.asistenciabyevento.colum1"), TDSLanguageUtils.getMessage("clientePEC4.asistenciabyevento.colum2"), TDSLanguageUtils.getMessage("clientePEC4.asistenciabyevento.colum3"), TDSLanguageUtils.getMessage("clientePEC4.asistenciabyevento.colum4"), TDSLanguageUtils.getMessage("clientePEC4.asistenciabyevento.colum5"), TDSLanguageUtils.getMessage("clientePEC4.asistenciabyevento.colum6")};
	private DefaultTableModel dtm;
	
	private RemoteInterface remote;
	private DTOEvento dtoEvento;
	private Boolean bIsEmpty = false;

	/**
	* Auto-generated main method to display this JDialog
	*/
		
	public PantallaAsistenciaByEvento(JFrame frame, RemoteInterface remote1, DTOEvento dtoEvento1, Boolean bUpdate) {
		super(frame);
		initGUI(frame);
		this.remote = remote1;
		this.dtoEvento = dtoEvento1;
		
		if (dtoEvento != null && dtoEvento.getEvento() != null){
			try
			{
				jButtonCheckIN.setVisible(bUpdate);
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
	
	private void cargaDatosEvento() throws OperationErrorDatosFormulario{
		try{
			dtoEvento = remote.getEvento(dtoEvento);
			if(dtoEvento == null){
				Utils.mostraMensajeInformacion(jPanelBase, TDSLanguageUtils.getMessage("clientePEC4.asistenciabyevento.MSG1"), TDSLanguageUtils.getMessage("clientePEC4.asistenciabyevento.MSG1.title"));
				return;
			}
			jLabelEvento.setText(dtoEvento.getEvento().getNombre());
			jLabelCodigo.setText(dtoEvento.getEvento().getIdEvento().toString());
		}catch(Exception e){
			throw new OperationErrorDatosFormulario(TDSLanguageUtils.getMessage("clientePEC4.asistenciabyevento.ERROR1"));
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
			List<DTOInscripcion> lstDtoInscripcion = remote.getInscripcionesByEventoFinalizado(consultaInscripcion());
			if(lstDtoInscripcion == null || lstDtoInscripcion.isEmpty()){
				Utils.mostraMensajeInformacion(jPanelBase, TDSLanguageUtils.getMessage("clientePEC4.asistenciabyevento.MSG2"), TDSLanguageUtils.getMessage("clientePEC4.asistenciabyevento.MSG2.title"));
				return;
			}
			muestraResultado(lstDtoInscripcion);
			actualizaTabla();
		}catch(Exception e){
			throw new OperationErrorDatosFormulario(TDSLanguageUtils.getMessage("clientePEC4.asistenciabyevento.ERROR2"));
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
					 aobj[k][2] = new String(dtoInscripcion.getInscripcion().getFechaInscripcion().toString());
					 aobj[k][3] = new String(dtoInscripcion.getInscripcion().getAsistencia());
					 aobj[k][4] = new String(dtoInscripcion.getInscripcion().getCodigo());
					 aobj[k][5] = new Boolean(dtoInscripcion.getInscripcion().getCheckIn());
					 k++;
	       	 	}
				
				if (aobj != null && aobj.length > 0){
	       	 		for(int row = 0; row < aobj.length; row++){
	       	 			dtm.addRow(aobj[row]);
	       	 		}
	       	 	}
			}
		}catch(Exception e){
			throw new OperationErrorDatosFormulario(TDSLanguageUtils.getMessage("clientePEC4.asistenciabyevento.ERROR3"));
		}
	}
	private void initGUI(JFrame frame) {
		this.setTitle(TDSLanguageUtils.getMessage("clientePEC4.asistenciabyevento.title"));
		this.setModal(true);
		this.setLocationRelativeTo(frame);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		{
			jPanelBase = new JPanel();
			getContentPane().add(jPanelBase, BorderLayout.CENTER);
			jPanelBase.setPreferredSize(new java.awt.Dimension(618, 418));
			{
				jPanelCentro = new JPanel();
				jPanelBase.add(jPanelCentro);
				jPanelBase.add(getJPanel1());
				jPanelCentro.setFont(new java.awt.Font("Arial",0,10));
				jPanelCentro.setPreferredSize(new java.awt.Dimension(559, 62));
				jPanelCentro.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
				jPanelCentro.setBounds(17, 32, 744, 58);
				jPanelCentro.setLayout(null);
				{
					jLabelEventoText = new JLabel();
					jPanelCentro.add(jLabelEventoText);
					jLabelEventoText.setText(TDSLanguageUtils.getMessage("clientePEC4.calendarioeventos.column2"));
					jLabelEventoText.setFont(new java.awt.Font("Arial",1,12));
					jLabelEventoText.setLayout(null);
					jLabelEventoText.setBounds(30, 14, 40, 14);
				}
				{
					jLabelEvento = new JLabel();
					jPanelCentro.add(jLabelEvento);
					jLabelEvento.setText(TDSLanguageUtils.getMessage("clientePEC4.asistenciabyevento.label1"));
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
					jLabelCodigo.setText(TDSLanguageUtils.getMessage("clientePEC4.asistenciabyevento.label1"));
					jLabelCodigo.setFont(new java.awt.Font("Arial",0,10));
					jLabelCodigo.setBounds(429, 34, 30, 13);
				}
			}
		}
		try {
			this.setSize(618, 468);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private JButton getJButtonCheckIN() {
		if(jButtonCheckIN == null) {
			jButtonCheckIN = new JButton();
			jButtonCheckIN.setLayout(null);
			jButtonCheckIN.setText("Check-IN");
			jButtonCheckIN.setFont(new java.awt.Font("Arial",0,10));
			jButtonCheckIN.setPreferredSize(new java.awt.Dimension(110, 29));
			jButtonCheckIN.setBounds(275, 359, 90, 25);
			jButtonCheckIN.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					jButtonCheckINActionPerformed(evt);
				}
			});
		}
		return jButtonCheckIN;
	}
	
	private DTOInscripcion getSelectedInscripcion() throws OperationErrorDatosFormulario{
		try {
			@SuppressWarnings("unchecked")
			List<Object> lstRes = (Vector<Object>) dtm.getDataVector().get(jTableDatos.getSelectedRow());
			Inscripcion inscripcion = new Inscripcion();
			DTOInscripcion dtoInscripcion = new DTOInscripcion();
			inscripcion.setCodigo(lstRes.get(COL_CODIGO).toString());
			inscripcion.setIdEvento(dtoEvento.getEvento().getIdEvento());
			dtoInscripcion.setInscripcion(inscripcion);
			return dtoInscripcion;
		} catch (NumberFormatException e1) {
			throw new OperationErrorDatosFormulario(e1.getMessage());
		} catch (Exception e1) {
			throw new OperationErrorDatosFormulario(e1.getMessage());
		}
	}
	
	private Boolean isCheckIN() {
		@SuppressWarnings("unchecked")
		List<Object> lstRes = (Vector<Object>) dtm.getDataVector().get(jTableDatos.getSelectedRow());
		
		return (Boolean.parseBoolean(lstRes.get(COL_CHECKIN).toString()));
	}
	
	private void jButtonCheckINActionPerformed(ActionEvent evt) {
		System.out.println("jButtonCheckIN.actionPerformed, event="+evt);
		if (jTableDatos.getSelectedRow() == -1) {
			Utils.mostraMensajeInformacion(jPanelCentro, TDSLanguageUtils.getMessage("clientePEC4.consultausuario.INFO.MSG1"), TDSLanguageUtils.getMessage("clientePEC4.asistenciabyevento.colum4"));
		}else{
			if (!isCheckIN()){
				try {
					DTOInscripcion dtoInscripcion = getSelectedInscripcion();
					remote.checkIN(dtoInscripcion);
					Utils.mostraMensajeInformacion(jPanelCentro, TDSLanguageUtils.getMessage("clientePEC4.asistenciabyevento.MSG3"), TDSLanguageUtils.getMessage("clientePEC4.asistenciabyevento.colum4"));
					cargaInscripcionesByEvento();
				} catch (Exception e1) {
					try {
						throw new OperationErrorDatosFormulario(e1.getMessage());
					} catch (OperationErrorDatosFormulario e2) {
						e2.showDialogError();
					}
				}
			}
			else{
				Utils.mostraMensajeInformacion(jPanelCentro, TDSLanguageUtils.getMessage("clientePEC4.asistenciabyevento.MSG4"), TDSLanguageUtils.getMessage("clientePEC4.asistenciabyevento.colum4"));
			}
		}
	}
	
	private JPanel getJPanel1() {
		if(jPanel1 == null) {
			jPanel1 = new JPanel();
			{
				jScrollPane1 = new JScrollPane();
				jPanel1.add(jScrollPane1);
				jPanel1.add(getJButtonCheckIN());
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
					Utils.ocultaColumna(jTableDatos, COL_CODIGO);
					Utils.ocultaColumna(jTableDatos, COL_CHECKIN);
					jTableDatos.setPreferredSize(new java.awt.Dimension(538, 278));
				}
			}
			{
				jButtonCerrar = new JButton();
				jPanel1.add(jButtonCerrar);
				jPanel1.setPreferredSize(new java.awt.Dimension(567, 346));
				jButtonCerrar.setLayout(null);
				jButtonCerrar.setText("Cerrar");
				jButtonCerrar.setFont(new java.awt.Font("Arial",0,10));
				jButtonCerrar.setBounds(275, 359, 90, 25);
				jButtonCerrar.setPreferredSize(new java.awt.Dimension(110, 29));
				jButtonCerrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose(); 
					}
				});
			}
		}
		return jPanel1;
	}

}
