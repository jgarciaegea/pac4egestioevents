package uoc.edu.tds.pec4.pantallas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import uoc.edu.tds.pec4.beans.Evento;
import uoc.edu.tds.pec4.beans.EventoCalendario;
import uoc.edu.tds.pec4.beans.TipoEventoRol;
import uoc.edu.tds.pec4.dtos.DTOAsistente;
import uoc.edu.tds.pec4.dtos.DTOCentroDocente;
import uoc.edu.tds.pec4.dtos.DTOEvento;
import uoc.edu.tds.pec4.dtos.DTOEventoCalendario;
import uoc.edu.tds.pec4.dtos.DTOPersonalSecretaria;
import uoc.edu.tds.pec4.dtos.DTOTipoEvento;
import uoc.edu.tds.pec4.dtos.DTOTipoEventoRol;
import uoc.edu.tds.pec4.dtos.DTOUniversidad;
import uoc.edu.tds.pec4.dtos.DTOUsuario;
import uoc.edu.tds.pec4.excepciones.OperationErrorBD;
import uoc.edu.tds.pec4.excepciones.OperationErrorDatosFormulario;
import uoc.edu.tds.pec4.excepciones.OperationErrorLogin;
import uoc.edu.tds.pec4.iface.RemoteInterface;
import uoc.edu.tds.pec4.utils.ClearForm;
import uoc.edu.tds.pec4.utils.Constantes;
import uoc.edu.tds.pec4.utils.MostrarCombo;
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
public class PantallaCalendarioEventos extends javax.swing.JPanel implements Pantallas {
	private static final long serialVersionUID = 1L;
	private static final Integer COL_CANCELADO = 7;
	private static final Integer COL_CELEBRADO = 8;
	private static final Integer COL_CENTRO = 9;

	// Controles del apartado de filtros
	private JPanel jPanelFiltro;
	private JLabel jLabelFiltro;
	private JLabel jLabelUniversidad;
	private JComboBox jComboBoxUniveridad;
	private JLabel jLabelCentroDocente;
	private JComboBox jComboBoxCentroDocente;
	private JLabel jLabelFechaIni;
	private JTextField jTextFieldFechaFin;
	private JTextField jTextFieldFechaIni;
	private JCheckBox jCheckBoxShowEventoFinalizado;
	private JCheckBox jCheckBoxShowAll;
	private JComboBox jComboBoxTipoEvento;
	private JLabel jLabelTipoEvento;
	private JLabel jLabelFechaFinCelebracion;
	private JButton jButtonCancelar;
	private JButton jButtonSearch;
	private JButton jButtonClear;
	
	// Controles del apartado Datos
	private JPanel jPanelDatos;
	private JScrollPane jScrollPane1;
	private DefaultTableModel dtm;
	private String[] columnNames = {"idEvento", "Fecha", "Evento", "Universidad", "Centro docente", "Tipo Evento", "Duracion", "Cancelado", "Celebrado", "idCentro"};
	private JTable jTableDatos;
	private JButton jButtonNew;
	private JButton jButtonUpdate;
	private JButton jButtonDelete;
	private JButton jButtonInscripcion;
	private JButton jButtonViewInscripciones;
	private JButton jButtonViewAsistenciaAusencia;

	private RemoteInterface remote;
	private DTOUsuario dtoUsuario;
	private List<DTOTipoEventoRol> lstDTOTipoEventoRol;

	public PantallaCalendarioEventos(RemoteInterface remote1, DTOUsuario dtoUsuario1) {
		super();
		this.remote = remote1;
		this.dtoUsuario = dtoUsuario1;
		try {
			remote.testConexion();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		System.out.print("Para quitar el warning que sale si no se utiliza es provisional " + remote.toString());
		initGUI();
	}
	
	private void initGUI() {
		try {
			this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(null, "Calendario de Eventos", 0, 0, new Font("Dialog", 1, 12), new Color(51, 51, 51)), null), null));
			this.setPreferredSize(new java.awt.Dimension(823, 501));
			
			this.add(getJPanelFiltro());
			this.add(getJPanelDatos());
			jButtonClearActionPerformed();
			//Rellenamos inforamci�n de los filtros
			if (dtoUsuario instanceof DTOAsistente) recuperarPermisosTipRol();
			cargaCombos();

			gestionarPermisos();
		} catch (Exception e) {
			try{
				throw new OperationErrorDatosFormulario(e.getMessage());
			}catch (OperationErrorDatosFormulario ex){
				ex.showDialogError(jPanelDatos);
			}
		}
	}
	
	
	/**
	 * Recupera el evento seleccionado de la tabla
	 */
	private DTOEvento getSelectedEvento() throws OperationErrorDatosFormulario{
		try {
			@SuppressWarnings("unchecked")
			List<Object> lstRes = (Vector<Object>) dtm.getDataVector().get(jTableDatos.getSelectedRow());
			Evento evento = new Evento();
			DTOEvento dtoEvento = new DTOEvento();
			evento.setIdEvento(Integer.parseInt(lstRes.get(0).toString()));
			dtoEvento.setEvento(evento);
			return dtoEvento;
		} catch (NumberFormatException e1) {
			throw new OperationErrorDatosFormulario(e1.getMessage());
		} catch (Exception e1) {
			throw new OperationErrorDatosFormulario(e1.getMessage());
		}
	}
	
	private Boolean canUpdateEvento() {
		return (!isCancelledEvento() && !isCelebradoEvento() && isCentroUsuario());
	}
	
	private Boolean isCancelledEvento() {
		@SuppressWarnings("unchecked")
		List<Object> lstRes = (Vector<Object>) dtm.getDataVector().get(jTableDatos.getSelectedRow());
		
		return (Boolean.parseBoolean(lstRes.get(COL_CANCELADO).toString()));
	}

	private Boolean isCelebradoEvento() {
		@SuppressWarnings("unchecked")
		List<Object> lstRes = (Vector<Object>) dtm.getDataVector().get(jTableDatos.getSelectedRow());
		
		return (Boolean.parseBoolean(lstRes.get(COL_CELEBRADO).toString()));
	}
	
	private Boolean isCentroUsuario() {
		@SuppressWarnings("unchecked")
		List<Object> lstRes = (Vector<Object>) dtm.getDataVector().get(jTableDatos.getSelectedRow());
		Integer iCentro = Integer.parseInt(lstRes.get(COL_CENTRO).toString());
		return (iCentro.equals(dtoUsuario.getDtoCentroDocente().getCentroDocente().getIdCentro()));
	}

	/**
	 * Vamos a la pantalla de gesti�n del evento
	*/
	private void goPantallaEvento(DTOEvento dtoEvento){
		this.setBorder(null);
		this.removeAll();
		this.setAlignmentX(LEFT_ALIGNMENT);
		this.setAlignmentY(TOP_ALIGNMENT);
		this.add((Component)new PantallaEvento(remote, dtoUsuario, dtoEvento));
		this.repaint();
		this.revalidate();
		this.updateUI();
	}
	 
	
	/**
	 * Parametrizamos el eventoCalendario que vamos a consultar
	 * @return
	 * @throws OperationErrorDatosFormulario
	 */
	private DTOEventoCalendario getDTOFiltro() throws OperationErrorDatosFormulario{
		
		//Rellenamos el Evento
		DTOEventoCalendario dtoEventoCalendario = new DTOEventoCalendario();
		EventoCalendario eventoCalendario = new EventoCalendario();
		
		Integer idUniversidad = Integer.parseInt(((MostrarCombo) jComboBoxUniveridad.getSelectedItem()).getID().toString());
		eventoCalendario.setIdUniversidad(idUniversidad==0?null:idUniversidad);
		
		if (jComboBoxCentroDocente.getSelectedIndex() > -1){
			Integer idCentroDocente = Integer.parseInt(((MostrarCombo) jComboBoxCentroDocente.getSelectedItem()).getID().toString());
			eventoCalendario.setIdCentro(idCentroDocente==0?null:idCentroDocente);
		}
		Integer idTipoEvento = Integer.parseInt(((MostrarCombo) jComboBoxTipoEvento.getSelectedItem()).getID().toString());
		eventoCalendario.setIdTipoEvento(idTipoEvento==0?null:idTipoEvento);

		eventoCalendario.setFechaInicioCelebracion(Utils.transformFecha(jTextFieldFechaIni.getText()));
		eventoCalendario.setFechaFinCelebracion(Utils.transformFecha(jTextFieldFechaFin.getText()));
		
		if(jCheckBoxShowAll.isSelected()){
			eventoCalendario.setEstado(null);
		}else {
			eventoCalendario.setEstado(Constantes.REGISTRO_ACTIVO);
		}
		
		if(jCheckBoxShowEventoFinalizado.isSelected()){
			eventoCalendario.setEventoFinalizado(null);
		}
		else {
			eventoCalendario.setEventoFinalizado(false);
		}
		dtoEventoCalendario.setEventoCalendario(eventoCalendario);
		return dtoEventoCalendario;
	}
	
	/**
	 * Carga el listado de eventos
	 */
	private void getEventosCalendario() throws OperationErrorDatosFormulario{
		try{
			dtm.getDataVector().removeAllElements();
			List<DTOEventoCalendario> lstDtoEventoCalendario = remote.getEventosCalendario(getDTOFiltro());
			if(lstDtoEventoCalendario == null || lstDtoEventoCalendario.isEmpty()){
				Utils.mostraMensajeInformacion(jPanelDatos, "No hay resultados","B�squeda eventos");
				return;
			}
			muestraResultado(lstDtoEventoCalendario);
			actualizaTabla();
		}catch(Exception e){
			throw new OperationErrorDatosFormulario(e.getMessage());
		}
		
	}
	
	/**
	 * Rellena los centros docentes dependiendo de la universidad seleccionada
	 * @param obj
	 */
	private void rellenaCentrosDocentes(Object obj){
		try {
			if((Integer)obj == 0) obj = null;//Las encontrar� todas
			List<DTOCentroDocente> lstDtoCentroDoc = remote.rellenaCentrosByIdUniversidad((Integer)obj);
			List<MostrarCombo> lstComboCentroDoc = new ArrayList<MostrarCombo>();
			if(lstDtoCentroDoc != null){
				lstComboCentroDoc.add(new MostrarCombo(0, Constantes.NOMBRE_TODOS));
				for(DTOCentroDocente dtoCentroDocRec : lstDtoCentroDoc){
					lstComboCentroDoc.add(new MostrarCombo(dtoCentroDocRec.getCentroDocente().getIdCentro(),
							dtoCentroDocRec.getCentroDocente().getNombre()));
				}
			}
			ComboBoxModel jComboBoxCentroDocenteModel = new DefaultComboBoxModel(lstComboCentroDoc.toArray());
			jComboBoxCentroDocente.setModel(jComboBoxCentroDocenteModel);
			
		} catch (OperationErrorBD e1) {
			e1.showDialogError();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * M�todo que valida los datos introducidos en el formulario
	 * @throws OperationErrorDatosFormulario
	 */
	private void validaFormulario() throws OperationErrorDatosFormulario{
		try{
			if(Utils.valorisNull(jTextFieldFechaIni.getText())) throw new Exception(Utils.MESSAGE_ERROR + " fecha inicio celebraci�n" );
			if(!Utils.parseaFecha(jTextFieldFechaIni.getText())) throw new Exception(Utils.MESSAGE_ERROR + " fecha inicio celebraci�n " + Utils.MESSAGE_FECHA );
			if(Utils.valorisNull(jTextFieldFechaFin.getText())) throw new Exception(Utils.MESSAGE_ERROR + " fecha fin celebraci�n" );
			if(!Utils.parseaFecha(jTextFieldFechaFin.getText())) throw new Exception(Utils.MESSAGE_ERROR + " fecha fin celebraci�n " + Utils.MESSAGE_FECHA );
			if(!"".equalsIgnoreCase(jTextFieldFechaFin.getText()) && "".equalsIgnoreCase(jTextFieldFechaIni.getText())){
				throw new Exception("No puede introducir la fecha final sin previamente informar la fecha de inicio celebracion");
			}
			if (Utils.transformFecha(jTextFieldFechaFin.getText()).before(Utils.transformFecha(jTextFieldFechaIni.getText()))){
				throw new Exception( "La fecha inicio es mayor que la fecha final");
			}
		}catch(Exception e){
			throw new OperationErrorDatosFormulario(e.getMessage());
		}
			
	}
	
	/**
	 * M�todo para cargar los combos
	 * @throws OperationErrorDatosFormulario
	 */
	private void cargaCombos() throws OperationErrorDatosFormulario{
		try{
			//Cargamos las universidades
			List<DTOUniversidad> lstDtoUniversidad = remote.getUniversidades();
			List<MostrarCombo> lstComboUniv = new ArrayList<MostrarCombo>();
			if(lstDtoUniversidad != null){
				lstComboUniv.add(new MostrarCombo(0, Constantes.NOMBRE_TODOS));
				for(DTOUniversidad dtoUniverRec : lstDtoUniversidad){
					lstComboUniv.add(new MostrarCombo(dtoUniverRec.getUniversidad().getIdUniversidad(),
							dtoUniverRec.getUniversidad().getNombre()));
				}
			}
			ComboBoxModel jComboBoxUniverModel = new DefaultComboBoxModel(lstComboUniv.toArray());
			jComboBoxUniveridad.setModel(jComboBoxUniverModel);
						
			//Empezamos seleccionando el primer objeto cargado
			/*
			if (jComboBoxUniveridad.getItemCount() > 0) {
				jComboBoxUniveridad.setSelectedIndex(0);
				rellenaCentrosDocentes(((MostrarCombo)jComboBoxUniveridad.getSelectedItem()).getID());
			}*/
			//Cargamos los Tipos de eventos
			List<DTOTipoEvento> lstDtoTipoEvento = remote.getTiposEventos();
			List<MostrarCombo> lstComboTipoEvento = new ArrayList<MostrarCombo>();
			
			// para los asistentes segun sus permisos...
			if (dtoUsuario instanceof DTOAsistente) {
				if(lstDtoTipoEvento != null){
					
					for(DTOTipoEvento dtoTipoEvento : lstDtoTipoEvento){	
						for (DTOTipoEventoRol dtoTipoEventoRol:lstDTOTipoEventoRol){
							if (dtoTipoEventoRol.getTipoEventoRol().getIdTipoEvento().equals(dtoTipoEvento.getTipoEvento().getIdTipoEvento())){
								lstComboTipoEvento.add(new MostrarCombo(dtoTipoEvento.getTipoEvento().getIdTipoEvento(),
										dtoTipoEvento.getTipoEvento().getDescripcion()));
							}
						}
					}
				}
				
			}else{// para el resto de usuarios...
				if(lstDtoTipoEvento != null){
					lstComboTipoEvento.add(new MostrarCombo(0, Constantes.NOMBRE_TODOS));
					for(DTOTipoEvento dtoTipoEvento : lstDtoTipoEvento){
						lstComboTipoEvento.add(new MostrarCombo(dtoTipoEvento.getTipoEvento().getIdTipoEvento(),
								dtoTipoEvento.getTipoEvento().getDescripcion()));
					}
				}
			}
			



			ComboBoxModel jComboBoxTipoEventoEventoModel = new DefaultComboBoxModel(lstComboTipoEvento.toArray());
			jComboBoxTipoEvento.setModel(jComboBoxTipoEventoEventoModel);
		}catch(Exception e){
			throw new OperationErrorDatosFormulario("Error al cargar las listas seleccionables");
		}
		
	}
	
	/*
	 * Limpia el formulario
	 */
	private void jButtonClearActionPerformed(){
		ClearForm.clearForm(jPanelFiltro);
		ClearForm.clearForm(jPanelDatos);
		
		java.util.Calendar fecha = java.util.Calendar.getInstance();
		jTextFieldFechaIni.setText("01/01/" + fecha.get(java.util.Calendar.YEAR));
		jTextFieldFechaFin.setText("31/12/" + fecha.get(java.util.Calendar.YEAR));
		
		dtm.getDataVector().removeAllElements();
		actualizaTabla();
	}
	
	private void actualizaTabla(){
		jTableDatos.repaint();
		jTableDatos.revalidate();
		jTableDatos.updateUI();
	}
	
	/*
	 * Mostramos el resultado obtenido
	 */
	private void muestraResultado(List<DTOEventoCalendario> lstDtoEventoCalendario) throws OperationErrorDatosFormulario{
		try{			
			dtm.getDataVector().removeAllElements();
		  
			Object[][] aobj = new Object[lstDtoEventoCalendario.size()][columnNames.length];
			int k = 0;

			if(lstDtoEventoCalendario != null){ 	
				for(DTOEventoCalendario dtoEventoCalendario : lstDtoEventoCalendario){
					aobj[k][0] = new String(dtoEventoCalendario.getEvento().getIdEvento().toString());
					aobj[k][1] = new String(Utils.convertFecha(dtoEventoCalendario.getEvento().getFechaInicioCelebracion().toString()));
					aobj[k][2] = new String(dtoEventoCalendario.getEvento().getNombre());
	                aobj[k][3] = new String(dtoEventoCalendario.getDtoCentroDocente().getDtoUniversidad().getUniversidad().getNombre());
	                aobj[k][4] = new String(dtoEventoCalendario.getDtoCentroDocente().getCentroDocente().getNombre());
	                aobj[k][5] = new String(dtoEventoCalendario.getDtoTipoEvento().getTipoEvento().getDescripcion());
	                aobj[k][6] = new String(dtoEventoCalendario.getEvento().getUmbral().toString());
	                aobj[k][7] = new String(dtoEventoCalendario.getEventoCalendario().getEventoCancelado().toString());
	                aobj[k][8] = new String(dtoEventoCalendario.getEventoCalendario().getEventoFinalizado().toString());
	                aobj[k][9] = new Integer(dtoEventoCalendario.getDtoCentroDocente().getCentroDocente().getIdCentro());
	                k++;
	       	 	}
	       	 
	       	 	if(aobj != null && aobj.length > 0){
	       	 		for(int row = 0; row < aobj.length; row++){
	       	 			dtm.addRow(aobj[row]);
	       	 		}
	       	 	}
			}
			actualizaTabla();
		}catch(Exception e){
			throw new OperationErrorDatosFormulario(e.getMessage());
		}
	}
	
	/*
	 ************************* PANEL FILTROS ****************************** 
	 */
	private JPanel getJPanelFiltro() {
		if(jPanelFiltro == null) {
			jPanelFiltro = new JPanel();
			jPanelFiltro.setLayout(null);
			jPanelFiltro.setBounds(24, 32, 733, 155);
			jPanelFiltro.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
			jPanelFiltro.setPreferredSize(new java.awt.Dimension(788, 148));
			jPanelFiltro.setFont(new java.awt.Font("Arial",0,10));
			jPanelFiltro.add(getJLabelFiltro());
			jPanelFiltro.add(getJLabelUniversidad());
			jPanelFiltro.add(getJComboBoxUniveridad());
			jPanelFiltro.add(getJLabelCentroDocente());
			jPanelFiltro.add(getJComboBoxCentroDocente());
			jPanelFiltro.add(getJLabelFechaIni());
			jPanelFiltro.add(getJTextFieldFechaIni());
			jPanelFiltro.add(getJTextFieldFechaFin());
			jPanelFiltro.add(getJButtonSearch());
			jPanelFiltro.add(getJButtonClear());
			jPanelFiltro.add(getJCheckBoxShowAll());
			jPanelFiltro.add(getJCheckBoxShowEventoFinalizado());
			jPanelFiltro.add(getJButtonCancelar());
			jPanelFiltro.add(getJLabelFechaFinCelebracion());
			jPanelFiltro.add(getJLabelTipoEvento());
			jPanelFiltro.add(getJComboBoxTipoEvento());
		}
		return jPanelFiltro;
	}
	
	private JLabel getJLabelFiltro() {
		if(jLabelFiltro == null) {
			jLabelFiltro = new JLabel();
			jLabelFiltro.setText("Filtro");
			jLabelFiltro.setLayout(null);
			jLabelFiltro.setBounds(14, 9, 49, 15);
			jLabelFiltro.setFont(new java.awt.Font("Arial",1,12));
		}
		return jLabelFiltro;
	}

	private JLabel getJLabelUniversidad() {
		if(jLabelUniversidad == null) {
			jLabelUniversidad = new JLabel();
			jLabelUniversidad.setText("Universidad");
			jLabelUniversidad.setLayout(null);
			jLabelUniversidad.setBounds(76, 17, 68, 15);
			jLabelUniversidad.setFont(new java.awt.Font("Arial",0,10));
		}
		return jLabelUniversidad;
	}
	
	private JComboBox getJComboBoxUniveridad() {
		if(jComboBoxUniveridad == null) {
			jComboBoxUniveridad = new JComboBox();
			jComboBoxUniveridad.setOpaque(false);
			jComboBoxUniveridad.setBounds(193, 12, 358, 26);
			jComboBoxUniveridad.setFont(new java.awt.Font("Arial",0,10));
			jComboBoxUniveridad.addItemListener(new ItemListener(){
				public void itemStateChanged(ItemEvent e) {
					try {
						if(e.getStateChange() == ItemEvent.SELECTED) {
							rellenaCentrosDocentes(((MostrarCombo)e.getItem()).getID());
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return jComboBoxUniveridad;
	}
	
	private JLabel getJLabelCentroDocente() {
		if(jLabelCentroDocente == null) {
			jLabelCentroDocente = new JLabel();
			jLabelCentroDocente.setText("Centro Docente");
			jLabelCentroDocente.setLayout(null);
			jLabelCentroDocente.setBounds(76, 43, 89, 15);
			jLabelCentroDocente.setFont(new java.awt.Font("Arial",0,10));
		}
		return jLabelCentroDocente;
	}

	private JComboBox getJComboBoxCentroDocente() {
		if(jComboBoxCentroDocente == null) {
			jComboBoxCentroDocente = new JComboBox();
			jComboBoxCentroDocente.setOpaque(false);
			jComboBoxCentroDocente.setBounds(193, 38, 358, 26);
			jComboBoxCentroDocente.setFont(new java.awt.Font("Arial",0,10));
		}
		return jComboBoxCentroDocente;
	}
	
	private JLabel getJLabelFechaIni() {
		if(jLabelFechaIni == null) {
			jLabelFechaIni = new JLabel();
			jLabelFechaIni.setText("Fecha Celebraci�n desde");
			jLabelFechaIni.setLayout(null);
			jLabelFechaIni.setBounds(76, 71, 119, 15);
			jLabelFechaIni.setFont(new java.awt.Font("Arial",0,10));
		}
		return jLabelFechaIni;
	}
	
	private JTextField getJTextFieldFechaIni() {
		if(jTextFieldFechaIni == null) {
			jTextFieldFechaIni = new JTextField();
			jTextFieldFechaIni.setBounds(195, 70, 113, 19);
			jTextFieldFechaIni.setFont(new java.awt.Font("Arial",0,10));
		}
		return jTextFieldFechaIni;
	}

	private JTextField getJTextFieldFechaFin() {
		if(jTextFieldFechaFin == null) {
			jTextFieldFechaFin = new JTextField();
			jTextFieldFechaFin.setBounds(368, 69, 113, 19);
			jTextFieldFechaFin.setFont(new java.awt.Font("Arial",0,10));
		}
		return jTextFieldFechaFin;
	}
	
	private JCheckBox getJCheckBoxShowAll() {
		if(jCheckBoxShowAll == null) {
			jCheckBoxShowAll = new JCheckBox();
			jCheckBoxShowAll.setText("Mostrar eventos cancelados");
			jCheckBoxShowAll.setBounds(183, 115, 185, 23);
			jCheckBoxShowAll.setFont(new java.awt.Font("Arial",0,10));
		}
		return jCheckBoxShowAll;
	}
	
	private JCheckBox getJCheckBoxShowEventoFinalizado() {
		if(jCheckBoxShowEventoFinalizado == null) {
			jCheckBoxShowEventoFinalizado = new JCheckBox();
			jCheckBoxShowEventoFinalizado.setText("Mostrar eventos finalizados");
			jCheckBoxShowEventoFinalizado.setBounds(397, 115, 183, 23);
			jCheckBoxShowEventoFinalizado.setFont(new java.awt.Font("Arial",0,10));
		}
		return jCheckBoxShowEventoFinalizado;
	}
	
	private JButton getJButtonSearch() {
		if(jButtonSearch == null) {
			jButtonSearch = new JButton();
			jButtonSearch.setLayout(null);
			jButtonSearch.setText("Buscar");
			jButtonSearch.setBounds(635, 26, 85, 25);
			jButtonSearch.setFont(new java.awt.Font("Arial",0,10));
			jButtonSearch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						validaFormulario();
						getEventosCalendario();
					} catch (OperationErrorDatosFormulario e3) {
						e3.showDialogError(jPanelDatos);
					}
				}
			});
		}
		return jButtonSearch;
	}
	
	private JButton getJButtonClear() {
		if (jButtonClear == null) {
			jButtonClear = new JButton();
			jButtonClear.setLayout(null);
			jButtonClear.setText("Limpiar");
			jButtonClear.setBounds(635, 51, 85, 25);
			jButtonClear.setFont(new java.awt.Font("Arial",0,10));
			jButtonClear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					jButtonClearActionPerformed();
				}
			});
		}
		return jButtonClear;
	}

	/*
	 ************************* PANEL DATOS ****************************** 
	 */
	private JPanel getJPanelDatos() {
		if(jPanelDatos == null) {
			jPanelDatos = new JPanel();
			jPanelDatos.setLayout(null);
			jPanelDatos.setBounds(24, 199, 733, 277);
			jPanelDatos.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
			jPanelDatos.setPreferredSize(new java.awt.Dimension(789, 294));
			jPanelDatos.setFont(new java.awt.Font("Arial",0,10));
			jPanelDatos.add(getJButtonNew());
			jPanelDatos.add(getJButtonUpdate());
			jPanelDatos.add(getJButtonDelete());
			jPanelDatos.add(getJButtonViewInscripciones());
			jPanelDatos.add(getJButtonViewAsistenciaAusencia());
			jPanelDatos.add(getJScrollPane1());
			jPanelDatos.add(getJButtonInscripcion());
		}
		return jPanelDatos;
	}
	
	private JScrollPane getJScrollPane1() {
		if(jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setBounds(14, 14, 761, 230);
			jScrollPane1.setFont(new java.awt.Font("Arial",0,10));
			jScrollPane1.setViewportView(getJTableDatos());
		}
		return jScrollPane1;
	}

	private JTable getJTableDatos() {
		if(jTableDatos == null) {
			dtm = new DefaultTableModel();
			for(int i=0;i<columnNames.length;i++){dtm.addColumn(columnNames[i]);}
			jTableDatos = new JTable(dtm);
			Utils.ocultaColumna(jTableDatos, 0);
			Utils.ocultaColumna(jTableDatos, 9);
			jTableDatos.setLayout(null);
			jTableDatos.setBounds(30, 33, 675, 149);
			jTableDatos.setVerifyInputWhenFocusTarget(false);
			jTableDatos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			jTableDatos.setPreferredSize(new java.awt.Dimension(753, 225));
		}
		return jTableDatos;
	}
	
	private JButton getJButtonNew() {
		if(jButtonNew == null) {
			ImageIcon icon = new ImageIcon("imagen/dcib023t.gif");
			jButtonNew = new JButton();
			jButtonNew.setIcon(icon);
			jButtonNew.setLayout(null);
			jButtonNew.setText("Nuevo Evento");
			jButtonNew.setBounds(13, 256, 138, 25);
			jButtonNew.setFont(new java.awt.Font("Arial",0,10));
			jButtonNew.setSize(138, 25);
			jButtonNew.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					goPantallaEvento(null);
					//jButtonClearActionPerformed();
				}
			});
		}
		return jButtonNew;
	}
	
	private JButton getJButtonUpdate() {
		if(jButtonUpdate == null) {
			jButtonUpdate = new JButton();
			jButtonUpdate.setLayout(null);
			jButtonUpdate.setText("Modificar Evento");
			jButtonUpdate.setBounds(158, 256, 138, 25);
			jButtonUpdate.setFont(new java.awt.Font("Arial",0,10));
			jButtonUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(jTableDatos.getSelectedRow() == -1){
						Utils.mostraMensajeInformacion(jPanelDatos, "No ha seleccionado ning�n registro de la tabla", "Calendario Eventos");
					}else{
						if (canUpdateEvento()){
							try {
								DTOEvento dtoEvento = getSelectedEvento();
								// TODO 1: Modificar el evento siempre que podamos, llamamos a la pantalla de eventos.
								goPantallaEvento(dtoEvento);
							} catch (OperationErrorDatosFormulario e1) {
								e1.showDialogError();
							}
						}else{
							Utils.mostraMensajeInformacion(jPanelDatos, "Evento cancelado/finalizado o no tienes permisos en ese centro", "Calendario Eventos");
						}
					}
				}
			});
		}
		return jButtonUpdate;
	}
	
	private JButton getJButtonDelete() {
		if(jButtonDelete == null) {
			jButtonDelete = new JButton();
			ImageIcon icon = new ImageIcon("imagen/dcib022t.gif");
			jButtonDelete.setIcon(icon);
			jButtonDelete.setLayout(null);
			jButtonDelete.setText("Cancelar Evento");
			jButtonDelete.setBounds(304, 256, 138, 25);
			jButtonDelete.setFont(new java.awt.Font("Arial",0,10));
			jButtonDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(jTableDatos.getSelectedRow() == -1){
						Utils.mostraMensajeInformacion(jPanelDatos, "No ha seleccionado ning�n registro de la tabla", "Calendario Eventos");
					}else{
						if (isCancelledEvento()){
							try {
								DTOEvento dtoEvento = getSelectedEvento();
								remote.bajaEvento(dtoEvento);
								getEventosCalendario();
								Utils.mostraMensajeInformacion(jPanelDatos, "Evento dado de baja correctamente", "Calendario Eventos");
							} catch (Exception e1) {
								try {
									throw new OperationErrorDatosFormulario(e1.getMessage());
								} catch (OperationErrorDatosFormulario e2) {
									e2.showDialogError();
								}
							}finally{
								jButtonClearActionPerformed();
							}
						}else{
							Utils.mostraMensajeInformacion(jPanelDatos, "El evento ya est� cancelado", "Calendario Eventos");
						}
					}
				}
			});
		}
		return jButtonDelete;
	}
	
	private JButton getJButtonViewInscripciones() {
		if(jButtonViewInscripciones == null) {
			jButtonViewInscripciones = new JButton();
			jButtonViewInscripciones.setLayout(null);
			jButtonViewInscripciones.setText("Ver Inscripciones");
			jButtonViewInscripciones.setBounds(504, 256, 118, 25);
			jButtonViewInscripciones.setFont(new java.awt.Font("Arial",0,10));
			jButtonViewInscripciones.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (jTableDatos.getSelectedRow() == -1) {
						Utils.mostraMensajeInformacion(jPanelDatos, "No ha seleccionado ning�n registro de la tabla", "Consulta Eventos");
					}else{
						try {
							DTOEvento dtoEvento = getSelectedEvento();
					        //this.setVisible(false);
					        PantallaInscripcionesByEvento v3 = new PantallaInscripcionesByEvento(null, remote, dtoEvento, canUpdateEvento());
					        if (!v3.isEmpty()) v3.setVisible(true);
							//goPantallaUsuario(dtoUsuario);
						} catch (OperationErrorDatosFormulario e1) {
							e1.showDialogError();
						}finally{
							//jButtonClearActionPerformed();
						}
					}
				}
			});
		}
		return jButtonViewInscripciones;
	}
	
	private JButton getJButtonViewAsistenciaAusencia() {
		if(jButtonViewAsistenciaAusencia == null) {
			jButtonViewAsistenciaAusencia = new JButton();
			jButtonViewAsistenciaAusencia.setLayout(null);
			jButtonViewAsistenciaAusencia.setText("Ver Asistencia/Ausencia");
			jButtonViewAsistenciaAusencia.setBounds(622, 256, 149, 25);
			jButtonViewAsistenciaAusencia.setFont(new java.awt.Font("Arial",0,10));
			jButtonViewAsistenciaAusencia.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (jTableDatos.getSelectedRow() == -1) {
						Utils.mostraMensajeInformacion(jPanelDatos, "No ha seleccionado ning�n registro de la tabla", "Consulta Eventos");
					}else{
						if (isCelebradoEvento()){
							try {
								DTOEvento dtoEvento = getSelectedEvento();
								PantallaAsistenciaByEvento v4 = new PantallaAsistenciaByEvento(null, remote, dtoEvento, (!isCancelledEvento() && isCelebradoEvento() && isCentroUsuario()));
								if (!v4.isEmpty()) v4.setVisible(true);
							} catch (OperationErrorDatosFormulario e1) {
								e1.showDialogError();
							}finally{
								//jButtonClearActionPerformed();
							}
						}else{
							Utils.mostraMensajeInformacion(jPanelDatos, "El evento a�n no ha finalizado", "Calendario Eventos");
						}
					}
				}
			});
		}
		return jButtonViewAsistenciaAusencia;
	}
	
	private JButton getJButtonInscripcion() {
		if(jButtonInscripcion == null) {
			jButtonInscripcion = new JButton();
			jButtonInscripcion.setLayout(null);
			jButtonInscripcion.setText("Inscripci�n");
			jButtonInscripcion.setFont(new java.awt.Font("Arial",0,10));
			jButtonInscripcion.setBounds(659, 253, 112, 25);
			jButtonInscripcion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(jTableDatos.getSelectedRow() == -1){
						Utils.mostraMensajeInformacion(jPanelDatos, "No ha seleccionado ning�n registro de la tabla", "Calendario Eventos");
					}else{
						try {
							DTOEvento dtoEvento = getSelectedEvento();
							goPantallaInscripcion(dtoEvento);
						} catch (Exception e1) {
							try {
								throw new OperationErrorDatosFormulario(e1.getMessage());
							} catch (OperationErrorDatosFormulario e2) {
								e2.showDialogError();
							}
						}finally{
							jButtonClearActionPerformed();
						}
						
					}
				}
			});
		}
		return jButtonInscripcion;
	}
	
	public void recuperarPermisosTipRol(){
		DTOTipoEventoRol dtoTipoEventoRol = new DTOTipoEventoRol();
		TipoEventoRol tipoEventoRol = new TipoEventoRol();
		tipoEventoRol.setIdRol(dtoUsuario.getUsuario().getIdRol());
		dtoTipoEventoRol.setTipoEventoRol(tipoEventoRol);
		lstDTOTipoEventoRol = new ArrayList<DTOTipoEventoRol>();
		try {
			lstDTOTipoEventoRol=remote.consultaTipoEventoRol(dtoTipoEventoRol);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (OperationErrorBD e) {
			e.printStackTrace();
			e.showDialogError();
		}
		if (lstDTOTipoEventoRol!=null)
			System.out.println("hemos encontrado datos de permisos de TipoRol");
		else
			System.out.println("No hemos encontrado datos de permisos de TipoRol");
	}
	
	
	public void gestionarPermisos(){
		jButtonNew.setVisible((dtoUsuario instanceof DTOPersonalSecretaria));
		jButtonUpdate.setVisible((dtoUsuario instanceof DTOPersonalSecretaria));
		jButtonDelete.setVisible((dtoUsuario instanceof DTOPersonalSecretaria));
		jButtonInscripcion.setVisible((dtoUsuario instanceof DTOAsistente));
		jButtonViewInscripciones.setVisible((dtoUsuario instanceof DTOPersonalSecretaria));
		jButtonViewAsistenciaAusencia.setVisible((dtoUsuario instanceof DTOPersonalSecretaria));
		jCheckBoxShowAll.setVisible((dtoUsuario instanceof DTOPersonalSecretaria));
		jCheckBoxShowEventoFinalizado.setVisible((dtoUsuario instanceof DTOPersonalSecretaria));
	}
	
	public void goPantallaInscripcion( DTOEvento dtoEvento) throws OperationErrorLogin, RemoteException, OperationErrorBD,OperationErrorDatosFormulario{
		System.out.println("Repintando Pantalla inscripcion");
		this.setBorder(null);
		this.removeAll();
		this.setAlignmentX(LEFT_ALIGNMENT);
		this.setAlignmentY(TOP_ALIGNMENT);
		this.add((Component)new PantallaInscripcion(remote,dtoUsuario,dtoEvento));
		this.repaint();
		this.revalidate();
		this.updateUI();
	}
	
	private JButton getJButtonCancelar() {
		if(jButtonCancelar == null) {
			jButtonCancelar = new JButton();
			jButtonCancelar.setLayout(null);
			jButtonCancelar.setText("Cancelar");
			jButtonCancelar.setFont(new java.awt.Font("Arial",0,10));
			jButtonCancelar.setBounds(635, 105, 60, 22);
			jButtonCancelar.setSize(85, 25);
			jButtonCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					setVisible(false);
					removeAll();
				}
			});
		}
		return jButtonCancelar;
	}
	
	private JLabel getJLabelFechaFinCelebracion() {
		if(jLabelFechaFinCelebracion == null) {
			jLabelFechaFinCelebracion = new JLabel();
			jLabelFechaFinCelebracion.setText("hasta");
			jLabelFechaFinCelebracion.setFont(new java.awt.Font("Arial",0,10));
			jLabelFechaFinCelebracion.setLayout(null);
			jLabelFechaFinCelebracion.setBounds(326, 71, 42, 15);
		}
		return jLabelFechaFinCelebracion;
	}
	
	private JLabel getJLabelTipoEvento() {
		if(jLabelTipoEvento == null) {
			jLabelTipoEvento = new JLabel();
			jLabelTipoEvento.setText("Tipo Evento");
			jLabelTipoEvento.setFont(new java.awt.Font("Arial",0,10));
			jLabelTipoEvento.setLayout(null);
			jLabelTipoEvento.setBounds(76, 95, 89, 15);
		}
		return jLabelTipoEvento;
	}
	
	private JComboBox getJComboBoxTipoEvento() {
		if(jComboBoxTipoEvento == null) {
			jComboBoxTipoEvento = new JComboBox();
			jComboBoxTipoEvento.setOpaque(false);
			jComboBoxTipoEvento.setFont(new java.awt.Font("Arial",0,10));
			jComboBoxTipoEvento.setBounds(193, 94, 358, 22);
		}
		return jComboBoxTipoEvento;
	}

}
