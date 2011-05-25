package uoc.edu.tds.pec4.pantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import uoc.edu.tds.pec4.beans.Evento;
import uoc.edu.tds.pec4.dtos.DTOCentroDocente;
import uoc.edu.tds.pec4.dtos.DTOEvento;
import uoc.edu.tds.pec4.dtos.DTOEventoRequisitos;
import uoc.edu.tds.pec4.dtos.DTOEventoRolPlazas;
import uoc.edu.tds.pec4.dtos.DTOTipoEvento;
import uoc.edu.tds.pec4.dtos.DTOUsuario;
import uoc.edu.tds.pec4.excepciones.OperationErrorBD;
import uoc.edu.tds.pec4.excepciones.OperationErrorDatosFormulario;
import uoc.edu.tds.pec4.excepciones.OperationErrorRMI;
import uoc.edu.tds.pec4.iface.RemoteInterface;
import uoc.edu.tds.pec4.utils.ClearForm;
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
public class PantallaEvento extends javax.swing.JPanel implements Pantallas {
	private static final long serialVersionUID = 1L;

	// Controles del apartado de filtros
	private JPanel jPanelCentro;
	private JLabel jLabelNombreCentroText;
	private JLabel jLabelNombreCentro;
	private JLabel jLabelCodigoText;
	private JLabel jLabelCodigo;

	// Controles del apartado Datos
	private JPanel jPanelDatos;
	private JLabel jLabelEventoText;
	private JLabel jLabelNombreText;
	private JTextField jTextFieldNombre;
	private JLabel jLabelTipoEvento;
	private JComboBox jComboBoxTipoEvento;
	private JLabel jLabelFechaInicioCelebracion;
	private JTextField jTextFieldFechaInicioCelebracion;
	private JLabel jLabelFechaFinCelebracion;
	private JTextField jTextFieldFechaFinCelebracion;
	private JLabel jLabelFechaInicioInscripcion;
	private JTextField jTextFieldFechaInicioInscripcion;
	private JLabel jLabelFechaFinInscripcion;
	private JTextField jTextFieldFechaFinInscripcion;
	private JLabel jLabelUmbral;
	private JTextField jTextFieldUmbral;
	private JLabel jLabelPrecio;
	private JTextField jTextFieldPrecio;
	private JLabel jLabelPlazas;
	private JTextField jTextFieldPlazas;
	private JLabel jLabelDuracion;
	private JTextField jTextFieldDuracion;
	private JLabel jLabelDescripcion;
	private JTextArea jTextAreaDescripcion;	
	private JLabel jLabelRequisitos;
	private JScrollPane jScrollPaneRequisitos;
	private JTable jTableRequisitos;
	private DefaultTableModel dtmRequisitos;
	private String[] columnNamesRequisitos = {"idEvento", "Evento"};
	private JButton jButtonRequisitos;
	private JLabel jLabelRolPlazas;
	private JScrollPane jScrollPaneRolPlazas;
	private JTable jTableRolPlazas;
	private DefaultTableModel dtmRolPlazas;
	private String[] columnNamesRolPlazas = {"idRol", "Rol", "Plazas"};
	private JButton jButtonRolPlazas;
	private JButton jButtonAlta;
	private JButton jButtonCancelar;
	private JButton JButtonClear;

	private RemoteInterface remote;
	private DTOUsuario dtoUsuario;
	private DTOEvento dtoEventoAModficar;
	private DTOCentroDocente dtoCentroDocente;
	private Boolean bEventoModificacion = false;

	public PantallaEvento(RemoteInterface remote1, DTOUsuario dtoUsuarioLogin, DTOEvento eventoAModificar){
		super();
		this.remote = remote1;
		this.dtoUsuario = dtoUsuarioLogin;
		initGUI();
		if (eventoAModificar != null){ 
			this.dtoEventoAModficar = eventoAModificar;
			bEventoModificacion = true;
		}
		else {
			this.dtoEventoAModficar = new DTOEvento();
			Evento evento = new Evento();
			evento.setIdTipoEvento(Integer.parseInt(((MostrarCombo) jComboBoxTipoEvento.getSelectedItem()).getID().toString()));
			evento.setIdCentro(dtoUsuario.getDtoCentroDocente().getCentroDocente().getIdCentro());
			dtoEventoAModficar.setEvento(evento);			
			dtoEventoAModficar.setDtoCentroDocente(dtoUsuario.getDtoCentroDocente());
		}
		System.out.print("Para quitar el warning que sale si no se utiliza es provisional " + remote.toString());	
		initGUI();
		if (bEventoModificacion){ 
			cargaEvento();
			//Deshabilitamos que se pueda cambiar el centro docente
			jComboBoxTipoEvento.setEnabled(false);
			JButtonClear.setVisible(false);
			//jButtonRequisitos.setEnabled(false);
			//jButtonRolPlazas.setEnabled(false);
		}
		else {
			cargaCentroUsuario();
		}
		
		try {
			remote.testConexion();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public PantallaEvento(RemoteInterface remote1, DTOUsuario dtoUsuarioLogin){
		this(remote1, dtoUsuarioLogin, null);
	}

	private void initGUI() {
		try {
			this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(null, bEventoModificacion.booleanValue()?"Modificaci—n de evento":"Alta de evento", 0, 0, new Font("Dialog", 1, 12), new Color(51, 51, 51)), null), null));
			this.setPreferredSize(new java.awt.Dimension(775, 491));
			this.add(getJPanelCentro());
			this.add(getJPanelDatos());

			//Rellenamos inforamci—n de los filtros
			cargaCombos();
		} catch (Exception e) {
			try{
				throw new OperationErrorDatosFormulario(e.getMessage());
			}catch (OperationErrorDatosFormulario ex){
				ex.showDialogError(jPanelDatos);
			}
		}
	}
	
	private void cargaDatosCentroDocenteEnPantalla(DTOCentroDocente dto){
		if (dto != null){
			jLabelNombreCentro.setText(dto.getCentroDocente().getNombre());
			jLabelCodigo.setText(dto.getCentroDocente().getIdCentro().toString());
		}
	}
	/**
	 * Carga los valores del Centro docente del usuario
	 */
	private void cargaCentroUsuario(){
		dtoCentroDocente = new DTOCentroDocente();
		dtoCentroDocente = dtoUsuario.getDtoCentroDocente();
		cargaDatosCentroDocenteEnPantalla(dtoCentroDocente);
	}
		
	/**
	 * Carga los valores del evento a modificar
	 */
	private void cargaEvento(){
		try {
			dtoEventoAModficar = remote.getEvento(dtoEventoAModficar);
			
			if(dtoEventoAModficar != null){	
				// Cargamos el centro docente del evento en pantalla
				cargaDatosCentroDocenteEnPantalla(dtoEventoAModficar.getDtoCentroDocente());
				
				/*****************************************
				 * CARGAMOS LOS VALORES DEL EVENTO
				 *****************************************/
				jTextFieldNombre.setText(dtoEventoAModficar.getEvento().getNombre());
				if(dtoEventoAModficar.getEvento().getFechaInicioCelebracion() !=  null){
					try {
						jTextFieldFechaInicioCelebracion.setText(Utils.convertFecha(dtoEventoAModficar.getEvento().getFechaInicioCelebracion().toString()));
					} catch (OperationErrorDatosFormulario e) {
						e.printStackTrace();
					}
				}
				if(dtoEventoAModficar.getEvento().getFechaFinCelebracion() !=  null){
					try {
						jTextFieldFechaFinCelebracion.setText(Utils.convertFecha(dtoEventoAModficar.getEvento().getFechaFinCelebracion().toString()));
					} catch (OperationErrorDatosFormulario e) {
						e.printStackTrace();
					}
				}
				if(dtoEventoAModficar.getEvento().getFechaInicioInscripcion() !=  null){
					try {
						jTextFieldFechaInicioInscripcion.setText(Utils.convertFecha(dtoEventoAModficar.getEvento().getFechaInicioInscripcion().toString()));
					} catch (OperationErrorDatosFormulario e) {
						e.printStackTrace();
					}
				}
				if(dtoEventoAModficar.getEvento().getFechaFinInscripcion() !=  null){
					try {
						jTextFieldFechaFinInscripcion.setText(Utils.convertFecha(dtoEventoAModficar.getEvento().getFechaFinInscripcion().toString()));
					} catch (OperationErrorDatosFormulario e) {
						e.printStackTrace();
					}
				}
				
				if (dtoEventoAModficar.getEvento().getUmbral() != null) jTextFieldUmbral.setText(dtoEventoAModficar.getEvento().getUmbral().toString());
				if (dtoEventoAModficar.getEvento().getPrecio() != null) jTextFieldPrecio.setText(dtoEventoAModficar.getEvento().getPrecio().toString());
				if (dtoEventoAModficar.getEvento().getPlazas() != null) jTextFieldPlazas.setText(dtoEventoAModficar.getEvento().getPlazas().toString());
				if (dtoEventoAModficar.getEvento().getDuracion() != null) jTextFieldDuracion.setText(dtoEventoAModficar.getEvento().getDuracion().toString());
				
				if (dtoEventoAModficar.getEvento().getDescripcion() != null) jTextAreaDescripcion.setText(dtoEventoAModficar.getEvento().getDescripcion());

				
				if(dtoEventoAModficar.getDtoTipoEvento() != null){
					jComboBoxTipoEvento.setSelectedItem(new MostrarCombo(dtoEventoAModficar.getDtoTipoEvento().getTipoEvento().getIdTipoEvento()
							,dtoEventoAModficar.getDtoTipoEvento().getTipoEvento().getDescripcion()));
				}
				
				/*****************************************
				 * CARGAMOS LOS REQUISITOS
				 *****************************************/
				muestraRequisitos(dtoEventoAModficar.getDtoEventoRequisitos());
				/*****************************************
				 * CARGAMOS LOS ROL  /PLAZAS
				 ****************************************/
				muestraRolPlazas(dtoEventoAModficar.getDtoEventoRolPlazas());
			}
		} catch (RemoteException e) {
			try {
				throw new OperationErrorRMI(e.getMessage());
			} catch (OperationErrorRMI e1) {
				e1.showDialogError();
			}
		} catch (OperationErrorBD e) {
			try {
				throw new OperationErrorBD(e.getMessage());
			} catch (OperationErrorBD e1) {
				e1.showDialogError();
			}
		} catch (OperationErrorDatosFormulario e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void muestraRequisitos(List<DTOEventoRequisitos> lstDtoEventoRequisitos) throws OperationErrorDatosFormulario{
		try{
			dtmRequisitos.getDataVector().removeAllElements();
			if(lstDtoEventoRequisitos != null){
				Object[][] aobj = new Object[lstDtoEventoRequisitos.size()][columnNamesRequisitos.length];
				int k = 0;
			
				for(DTOEventoRequisitos dtoEventoRequisitos : lstDtoEventoRequisitos){
					 aobj[k][0] = new String(dtoEventoRequisitos.getDtoEventoReq().getEvento().getIdEvento().toString());
					 aobj[k][1] = new String(dtoEventoRequisitos.getDtoEventoReq().getEvento().getNombre());
					 k++;
	       	 	}
				
				if (aobj != null && aobj.length > 0){
	       	 		for(int row = 0; row < aobj.length; row++){
	       	 			dtmRequisitos.addRow(aobj[row]);
	       	 		}
	       	 	}
			}
			actualizaTablaRequisitos();
		}catch(Exception e){
			throw new OperationErrorDatosFormulario("Error en la carga de los eventos requisitos en la pantalla");
		}
	}
	
	private void muestraRolPlazas(List<DTOEventoRolPlazas> lstDtoEventoRolPlazas) throws OperationErrorDatosFormulario{
		try{
			dtmRolPlazas.getDataVector().removeAllElements();
			if(lstDtoEventoRolPlazas != null){
				Object[][] aobj = new Object[lstDtoEventoRolPlazas.size()][columnNamesRolPlazas.length];
				int k = 0;
			
				for(DTOEventoRolPlazas dtEventoRolPlazas : lstDtoEventoRolPlazas){
					 aobj[k][0] = new String(dtEventoRolPlazas.getDtoTipoRol().getTipoRol().getIdRol().toString());
					 aobj[k][1] = new String(dtEventoRolPlazas.getDtoTipoRol().getTipoRol().getDescripcion());
					 aobj[k][2] = new String(dtEventoRolPlazas.getEventoRolPlazas().getPlazas().toString());
					 k++;
	       	 	}
				
				if (aobj != null && aobj.length > 0){
	       	 		for(int row = 0; row < aobj.length; row++){
	       	 		dtmRolPlazas.addRow(aobj[row]);
	       	 		}
	       	 	}
			}
			actualizaTablaRolPlazas();
		}catch(Exception e){
			throw new OperationErrorDatosFormulario("Error en la carga los evento Rol/Plazas en la pantalla");
		}
	}
	
	/**
	 * MŽtodo que valida los datos introducidos en el formulario
	 * @throws OperationErrorDatosFormulario
	 */
	private void validaFormulario(boolean modificacion) throws OperationErrorDatosFormulario{
		try{
			if(Utils.valorisNull(jTextFieldNombre.getText())) throw new Exception(Utils.MESSAGE_ERROR + " nombre" );
			//Desde la pantalla de modificaci—n no se puede cambiar el tipo evento
			if(!modificacion){
				if(Utils.valorisNull(jComboBoxTipoEvento.getSelectedItem())) throw new Exception(Utils.MESSAGE_ERROR + " tipo evento" );
			}
			if(Utils.valorisNull(jTextFieldFechaInicioCelebracion.getText())) throw new Exception(Utils.MESSAGE_ERROR + " fecha inicio celebraci—n" );
			if(!Utils.parseaFecha(jTextFieldFechaInicioCelebracion.getText())) throw new Exception(Utils.MESSAGE_ERROR + " fecha inicio celebraci—n " + Utils.MESSAGE_FECHA );
			if(Utils.valorisNull(jTextFieldFechaFinCelebracion.getText())) throw new Exception(Utils.MESSAGE_ERROR + " fecha fin celebraci—n" );
			if(!Utils.parseaFecha(jTextFieldFechaFinCelebracion.getText())) throw new Exception(Utils.MESSAGE_ERROR + " fecha fin celebraci—n " + Utils.MESSAGE_FECHA );
			if(!"".equalsIgnoreCase(jTextFieldFechaFinCelebracion.getText()) && "".equalsIgnoreCase(jTextFieldFechaInicioCelebracion.getText())){
				 throw new Exception("No puede introducir la fecha final sin previamente informar la fecha de inicio celebracion");
			}
			if (Utils.transformFecha(jTextFieldFechaFinCelebracion.getText()).before(Utils.transformFecha(jTextFieldFechaInicioCelebracion.getText()))){
				throw new Exception( "La fecha inicio es mayor que la fecha final de celebraci—n");
			}
			if(Utils.valorisNull(jTextFieldFechaInicioInscripcion.getText())) throw new Exception(Utils.MESSAGE_ERROR + " fecha inicio inscripci—n" );
			if(!Utils.parseaFecha(jTextFieldFechaInicioInscripcion.getText())) throw new Exception(Utils.MESSAGE_ERROR + " fecha inicio inscripci—n " + Utils.MESSAGE_FECHA );
			if(Utils.valorisNull(jTextFieldFechaFinInscripcion.getText())) throw new Exception(Utils.MESSAGE_ERROR + " fecha fin inscripci—n" );
			if(!Utils.parseaFecha(jTextFieldFechaFinInscripcion.getText())) throw new Exception(Utils.MESSAGE_ERROR + " fecha fin inscripci—n " + Utils.MESSAGE_FECHA );
			if(!"".equalsIgnoreCase(jTextFieldFechaFinInscripcion.getText()) && "".equalsIgnoreCase(jTextFieldFechaInicioInscripcion.getText())){
				 throw new Exception("No puede introducir la fecha final sin previamente informar la fecha de inicio celebracion");
			}
			if (Utils.transformFecha(jTextFieldFechaFinInscripcion.getText()).before(Utils.transformFecha(jTextFieldFechaInicioInscripcion.getText()))){
				throw new Exception( "La fecha inicio es mayor que la fecha final de inscripci—n");
			}
			if (Utils.transformFecha(jTextFieldFechaInicioCelebracion.getText()).before(Utils.transformFecha(jTextFieldFechaInicioInscripcion.getText()))){
				throw new Exception( "La fecha inicio de inscipcion es mayor que la fecha inicial de celebraci—n");
			}
			if (Utils.transformFecha(jTextFieldFechaFinInscripcion.getText()).before(Utils.transformFecha(jTextFieldFechaInicioInscripcion.getText()))){
				throw new Exception( "La fecha fin de inscipcion es mayor que la fecha final de celebraci—n");
			}
			if(Utils.valorisNull(jTextFieldUmbral.getText())) throw new Exception(Utils.MESSAGE_ERROR + " umbral" );
			if(!Utils.validaNumerico(jTextFieldUmbral.getText())) throw new Exception(Utils.MESSAGE_ERROR + " umbral " + Utils.MESSAGE_NUMERIC );
			if(Integer.parseInt((jTextFieldUmbral.getText())) <= 0) throw new Exception(Utils.MESSAGE_ERROR + " umbral " + Utils.MESSAGE_NUMERIC_MAS0 );
			if(Utils.valorisNull(jTextFieldPrecio.getText())) throw new Exception(Utils.MESSAGE_ERROR + " precio" );
			if(!Utils.validaNumerico(jTextFieldPrecio.getText())) throw new Exception(Utils.MESSAGE_ERROR + " precio " + Utils.MESSAGE_NUMERIC );
			if(Utils.valorisNull(jTextFieldPlazas.getText())) throw new Exception(Utils.MESSAGE_ERROR + " plazas" );
			if(!Utils.validaNumerico(jTextFieldPlazas.getText())) throw new Exception(Utils.MESSAGE_ERROR + " plazas " + Utils.MESSAGE_NUMERIC );
			if(Integer.parseInt((jTextFieldPlazas.getText())) <= 0) throw new Exception(Utils.MESSAGE_ERROR + " plazas " + Utils.MESSAGE_NUMERIC_MAS0 );
			if(Utils.valorisNull(jTextFieldDuracion.getText())) throw new Exception(Utils.MESSAGE_ERROR + " duraci—n" );
			if(!Utils.validaNumerico(jTextFieldDuracion.getText())) throw new Exception(Utils.MESSAGE_ERROR + " duraci—n " + Utils.MESSAGE_NUMERIC );
			if(Integer.parseInt((jTextFieldDuracion.getText())) <= 0) throw new Exception(Utils.MESSAGE_ERROR + " duraci—n " + Utils.MESSAGE_NUMERIC_MAS0 );
		}catch(Exception e){
			throw new OperationErrorDatosFormulario(e.getMessage());
		}
	}
	
	/**
	 * MŽtodo para cargar los combos de la pantalla Evento
	 * @throws OperationErrorDatosFormulario
	 */
	private void cargaCombos() throws OperationErrorDatosFormulario{
		try{
			//Cargamos los Tipos de eventos
			List<DTOTipoEvento> lstDtoTipoEvento = remote.getTiposEventos();
			List<MostrarCombo> lstComboTipoEvento = new ArrayList<MostrarCombo>();
			if(lstDtoTipoEvento != null){
				for(DTOTipoEvento dtoTipoEvento : lstDtoTipoEvento){
					lstComboTipoEvento.add(new MostrarCombo(dtoTipoEvento.getTipoEvento().getIdTipoEvento(),
							dtoTipoEvento.getTipoEvento().getDescripcion()));
				}
			}
			ComboBoxModel jComboBoxTipoEventoEventoModel = new DefaultComboBoxModel(lstComboTipoEvento.toArray());
			jComboBoxTipoEvento.setModel(jComboBoxTipoEventoEventoModel);

		}catch(Exception e){
			throw new OperationErrorDatosFormulario("Error al cargar las listas seleccionables");
		}
		
	}
	
	/**
	 * MŽtodo donde seteamos el evento que se va a insertar o eliminar
	 * @param modificacion
	 * @return
	 * @throws OperationErrorDatosFormulario
	 */
	private DTOEvento altaModificaEvento(boolean modificacion) throws OperationErrorDatosFormulario{

		/*****************************************
		 * DATOS ESPECêFICOS DEL EVENTO
		 *****************************************/
		Evento evento = dtoEventoAModficar.getEvento();
		evento.setNombre(jTextFieldNombre.getText());
		evento.setIdTipoEvento(Integer.parseInt(((MostrarCombo) jComboBoxTipoEvento.getSelectedItem()).getID().toString()));
		try {
			evento.setFechaInicioCelebracion(Utils.transformFecha(jTextFieldFechaInicioCelebracion.getText()));
		} catch (OperationErrorDatosFormulario e) {
			throw e;
		}
		try {
			evento.setFechaFinCelebracion(Utils.transformFecha(jTextFieldFechaFinCelebracion.getText()));
		} catch (OperationErrorDatosFormulario e) {
			throw e;
		}
		try {
			evento.setFechaInicioInscripcion(Utils.transformFecha(jTextFieldFechaInicioInscripcion.getText()));
		} catch (OperationErrorDatosFormulario e) {
			throw e;
		}		
		try {
			evento.setFechaFinInscripcion(Utils.transformFecha(jTextFieldFechaFinInscripcion.getText()));
		} catch (OperationErrorDatosFormulario e) {
			throw e;
		}			
		evento.setUmbral(Integer.parseInt(jTextFieldUmbral.getText()));
		evento.setPrecio(Integer.parseInt(jTextFieldPrecio.getText()));
		evento.setPlazas(Integer.parseInt(jTextFieldPlazas.getText()));
		evento.setDuracion(Integer.parseInt(jTextFieldDuracion.getText()));
		evento.setDescripcion(jTextAreaDescripcion.getText());
		
		if(modificacion){
			// Si es modificaci—n estos ID se han de conservar del dtoEvento pasado a la pantalla
			evento.setIdEvento(dtoEventoAModficar.getEvento().getIdEvento());
			evento.setIdCentro(dtoEventoAModficar.getEvento().getIdCentro());
		}
		else {
			// El Centro docente si es alta ha de ser el del usuario
			evento.setIdCentro(dtoUsuario.getUsuario().getIdCentro());
		}
		
		/***********************************************
		 * RELLENAMOS EL DTOEvento CON TODOS LOS VALORES
		 ***********************************************/
		DTOEvento dtoEvento = new DTOEvento();
		dtoEvento.setEvento(evento);
		if (jTableRequisitos.getRowCount() > 0) dtoEvento.setDtoEventoRequisitos(dtoEventoAModficar.getDtoEventoRequisitos());
		if (jTableRolPlazas.getRowCount() > 0) dtoEvento.setDtoEventoRolPlazas(dtoEventoAModficar.getDtoEventoRolPlazas());
		return dtoEvento;
	}
	
	/**
	 * Limpia el formulario
	 */
	private void limpiaFormulario(){
		ClearForm.clearForm(jPanelDatos);
		dtmRequisitos.getDataVector().removeAllElements();
		actualizaTablaRequisitos();
		dtmRolPlazas.getDataVector().removeAllElements();
		actualizaTablaRolPlazas();
	}
	
	private void actualizaTablaRequisitos(){
		jTableRequisitos.repaint();
		jTableRequisitos.revalidate();
		jTableRequisitos.updateUI();
	}
	
	private void actualizaTablaRolPlazas(){
		jTableRolPlazas.repaint();
		jTableRolPlazas.revalidate();
		jTableRolPlazas.updateUI();
	}
	/*
	 ************************* PANEL FILTROS ****************************** 
	 */
	private JPanel getJPanelCentro() {
		if(jPanelCentro == null) {
			jPanelCentro = new JPanel();
			jPanelCentro.setLayout(null);
			jPanelCentro.setBounds(17, 32, 744, 58);
			jPanelCentro.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
			jPanelCentro.setFont(new java.awt.Font("Arial",0,10));
			jPanelCentro.setPreferredSize(new java.awt.Dimension(733, 56));
			jPanelCentro.add(getJLabelNombreCentroText());
			jPanelCentro.add(getJLabelNombreCentro());
			jPanelCentro.add(getJLabelCodigoText());
			jPanelCentro.add(getJLabelCodigo());
		}
		return jPanelCentro;
	}
	
	private JLabel getJLabelNombreCentroText() {
		if(jLabelNombreCentroText == null) {
			jLabelNombreCentroText = new JLabel();
			jLabelNombreCentroText.setText("Nombre del Centro");
			jLabelNombreCentroText.setLayout(null);
			jLabelNombreCentroText.setBounds(14, 9, 235, 15);
			jLabelNombreCentroText.setFont(new java.awt.Font("Arial",1,12));
		}
		return jLabelNombreCentroText;
	}

	private JLabel getJLabelNombreCentro() {
		if(jLabelNombreCentro == null) {
			jLabelNombreCentro = new JLabel();
			jLabelNombreCentro.setText("xxxxxx");
			jLabelNombreCentro.setBounds(14, 31, 409, 15);
			jLabelNombreCentro.setFont(new java.awt.Font("Arial",0,10));
		}
		return jLabelNombreCentro;
	}
	
	private JLabel getJLabelCodigoText() {
		if(jLabelCodigoText == null) {
			jLabelCodigoText = new JLabel();
			jLabelCodigoText.setText("C—digo");
			jLabelCodigoText.setBounds(453, 9, 75, 15);
			jLabelCodigoText.setFont(new java.awt.Font("Arial",1,12));
		}
		return jLabelCodigoText;
	}
	
	private JLabel getJLabelCodigo() {
		if(jLabelCodigo == null) {
			jLabelCodigo = new JLabel();
			jLabelCodigo.setText("xxxxxx");
			jLabelCodigo.setBounds(453, 31, 85, 15);
			jLabelCodigo.setFont(new java.awt.Font("Arial",0,10));
		}
		return jLabelCodigo;
	}
	
	/*
	 ************************* PANEL DATOS ****************************** 
	 */
	
	private JPanel getJPanelDatos() {
		if(jPanelDatos == null) {
			jPanelDatos = new JPanel();
			jPanelDatos.setLayout(null);
			jPanelDatos.setBounds(17, 96, 744, 429);
			jPanelDatos.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
			jPanelDatos.setFont(new java.awt.Font("Arial",0,10));
			jPanelDatos.setPreferredSize(new java.awt.Dimension(733, 394));
			jPanelDatos.add(getJLabelEventoText());
			jPanelDatos.add(getJLabelNombreText());
			jPanelDatos.add(getJTextFieldNombre());
			jPanelDatos.add(getJLabelTipoEvento());
			jPanelDatos.add(getJComboBoxTipoEvento());
			jPanelDatos.add(getJLabelFechaInicioCelebracion());
			jPanelDatos.add(getJTextFieldFechaInicioCelebracion());
			jPanelDatos.add(getJLabelFechaFinCelebracion());
			jPanelDatos.add(getJTextFieldFechaFinCelebracion());
			jPanelDatos.add(getJLabelFechaInicioInscripcion());
			jPanelDatos.add(getJTextFieldFechaInicioInscripcion());
			jPanelDatos.add(getJLabelFechaFinInscripcion());
			jPanelDatos.add(getJTextFieldFechaFinInscripcion());
			jPanelDatos.add(getJLabelUmbral());
			jPanelDatos.add(getJTextFieldUmbral());
			jPanelDatos.add(getJLabelPrecio());
			jPanelDatos.add(getJTextFieldPrecio());
			jPanelDatos.add(getJLabelPlazas());
			jPanelDatos.add(getJTextFieldPlazas());
			jPanelDatos.add(getJLabelDuracion());
			jPanelDatos.add(getJTextFieldDuracion());
			jPanelDatos.add(getJLabelDescripcion());
			jPanelDatos.add(getJTextAreaDescripcion());
			jPanelDatos.add(getJLabelRequisitos());
			jPanelDatos.add(getJScrollPaneRequisitos());
			jPanelDatos.add(getJButtonRequisitos());
			jPanelDatos.add(getJLabelRolPlazas());
			jPanelDatos.add(getJScrollPaneRolPlazas());
			jPanelDatos.add(getJButtonRolPlazas());
			jPanelDatos.add(getJButtonAlta());
			jPanelDatos.add(getJButtonCancelar());
			jPanelDatos.add(getJButtonClear());
		}
		return jPanelDatos;
	}
	
	private JLabel getJLabelTipoEvento() {
		if(jLabelTipoEvento == null) {
			jLabelTipoEvento = new JLabel();
			jLabelTipoEvento.setText("Tipo");
			jLabelTipoEvento.setLayout(null);
			jLabelTipoEvento.setBounds(14, 71, 61, 15);
			jLabelTipoEvento.setFont(new java.awt.Font("Arial",0,10));
		}
		return jLabelTipoEvento;
	}
	
	private JComboBox getJComboBoxTipoEvento() {
		if(jComboBoxTipoEvento == null) {
			jComboBoxTipoEvento = new JComboBox();
			jComboBoxTipoEvento.setOpaque(false);
			jComboBoxTipoEvento.setBounds(75, 67, 328, 22);
			jComboBoxTipoEvento.setFont(new java.awt.Font("Arial",0,10));
			jComboBoxTipoEvento.addItemListener(new ItemListener(){
				public void itemStateChanged(ItemEvent e) {
					dtmRequisitos.getDataVector().removeAllElements();
					actualizaTablaRequisitos();
					dtmRolPlazas.getDataVector().removeAllElements();
					actualizaTablaRolPlazas();
				}
			});
		}
		return jComboBoxTipoEvento;
	}

	private JLabel getJLabelFechaFinInscripcion() {
		if(jLabelFechaFinInscripcion == null) {
			jLabelFechaFinInscripcion = new JLabel();
			jLabelFechaFinInscripcion.setText("hasta");
			jLabelFechaFinInscripcion.setLayout(null);
			jLabelFechaFinInscripcion.setBounds(198, 134, 66, 15);
			jLabelFechaFinInscripcion.setFont(new java.awt.Font("Arial",0,10));
		}
		return jLabelFechaFinInscripcion;
	}
	
	private JTextField getJTextFieldFechaInicioCelebracion() {
		if(jTextFieldFechaInicioCelebracion == null) {
			jTextFieldFechaInicioCelebracion = new JTextField();
			jTextFieldFechaInicioCelebracion.setBounds(75, 101, 113, 19);
			jTextFieldFechaInicioCelebracion.setFont(new java.awt.Font("Arial",0,10));
		}
		return jTextFieldFechaInicioCelebracion;
	}
	
	private JLabel getJLabelFechaFinCelebracion() {
		if(jLabelFechaFinCelebracion == null) {
			jLabelFechaFinCelebracion = new JLabel();
			jLabelFechaFinCelebracion.setText("Fecha Final");
			jLabelFechaFinCelebracion.setLayout(null);
			jLabelFechaFinCelebracion.setBounds(198, 105, 72, 15);
			jLabelFechaFinCelebracion.setFont(new java.awt.Font("Arial",0,10));
		}
		return jLabelFechaFinCelebracion;
	}
	
	private JTextField getJTextFieldFechaFinCelebracion() {
		if(jTextFieldFechaFinCelebracion == null) {
			jTextFieldFechaFinCelebracion = new JTextField();
			jTextFieldFechaFinCelebracion.setBounds(270, 103, 113, 19);
			jTextFieldFechaFinCelebracion.setFont(new java.awt.Font("Arial",0,10));
		}
		return jTextFieldFechaFinCelebracion;
	}
	
	private JScrollPane getJScrollPaneRequisitos() {
		if(jScrollPaneRequisitos == null) {
			jScrollPaneRequisitos = new JScrollPane();
			jScrollPaneRequisitos.setBounds(443, 62, 120, 128);
			jScrollPaneRequisitos.setFont(new java.awt.Font("Arial",0,10));
			jScrollPaneRequisitos.setViewportView(getJTableRequisitos());
		}
		return jScrollPaneRequisitos;
	}

	private JButton getJButtonRequisitos() {
		if(jButtonRequisitos == null) {
			ImageIcon icon = new ImageIcon("imagen/dcib023t.gif");
			jButtonRequisitos = new JButton();
			jButtonRequisitos.setIcon(icon);
			jButtonRequisitos.setLayout(null);
			jButtonRequisitos.setText("Requisitos");
			jButtonRequisitos.setBounds(443, 199, 120, 25);
			jButtonRequisitos.setFont(new java.awt.Font("Arial",0,10));
			jButtonRequisitos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(Utils.valorisNull(jComboBoxTipoEvento.getSelectedItem())) {
						Utils.mostraMensajeInformacion(jPanelDatos, Utils.MESSAGE_ERROR + " tipo evento", "Evento");
					}
					else {
						try {
							if (!bEventoModificacion){
								dtoEventoAModficar.getEvento().setIdTipoEvento(Integer.parseInt(((MostrarCombo) jComboBoxTipoEvento.getSelectedItem()).getID().toString()));
							}
							PantallaEventoRequisitos v1 = new PantallaEventoRequisitos(null, remote, dtoEventoAModficar);
							v1.setModal(true);
							v1.setVisible(true);
				             if (v1.getAceptar()) {
				            	 dtoEventoAModficar.setDtoEventoRequisitos(v1.getDTOEventoRequisitos());
				            	 try {
									muestraRequisitos(dtoEventoAModficar.getDtoEventoRequisitos());
								} catch (OperationErrorDatosFormulario e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
				             }
						}finally{
						//jButtonClearActionPerformed();
						}
					}
				}
			});
		}
		return jButtonRequisitos;
	}
	
	private JButton getJButtonRolPlazas() {
		if(jButtonRolPlazas == null) {
			jButtonRolPlazas = new JButton();
			jButtonRolPlazas.setLayout(null);
			jButtonRolPlazas.setText("Rol/Plazas");
			jButtonRolPlazas.setBounds(584, 199, 120, 25);
			jButtonRolPlazas.setFont(new java.awt.Font("Arial",0,10));
			jButtonRolPlazas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(Utils.valorisNull(jComboBoxTipoEvento.getSelectedItem())) {
						Utils.mostraMensajeInformacion(jPanelDatos, Utils.MESSAGE_ERROR + " tipo evento", "Evento");
					}
					else {
						try {
							if (!bEventoModificacion){
								dtoEventoAModficar.getEvento().setIdTipoEvento(Integer.parseInt(((MostrarCombo) jComboBoxTipoEvento.getSelectedItem()).getID().toString()));
							}
							dtoEventoAModficar.getEvento().setPlazas(Integer.parseInt(jTextFieldPlazas.getText()));
							PantallaEventoRolPlazas v2 = new PantallaEventoRolPlazas(null, remote, dtoEventoAModficar);
							v2.setModal(true);
							v2.setVisible(true);
				             if (v2.getAceptar()) {
				            	 dtoEventoAModficar.setDtoEventoRolPlazas(v2.getDTOEventoRolPlazas());
				            	 try {
									muestraRolPlazas(dtoEventoAModficar.getDtoEventoRolPlazas());
								} catch (OperationErrorDatosFormulario e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
				             }
						}finally{
						//jButtonClearActionPerformed();
						}
					}
				}
			});
		}
		return jButtonRolPlazas;
	}
	
	private JLabel getJLabelEventoText() {
		if(jLabelEventoText == null) {
			jLabelEventoText = new JLabel();
			jLabelEventoText.setText("Datos del evento");
			jLabelEventoText.setBounds(14, 12, 143, 15);
			jLabelEventoText.setFont(new java.awt.Font("Arial",1,12));
		}
		return jLabelEventoText;
	}
	
	private JLabel getJLabelNombreText() {
		if(jLabelNombreText == null) {
			jLabelNombreText = new JLabel();
			jLabelNombreText.setText("Nombre");
			jLabelNombreText.setBounds(14, 39, 57, 15);
			jLabelNombreText.setFont(new java.awt.Font("Arial",0,10));
		}
		return jLabelNombreText;
	}
	
	private JLabel getJLabelFechaInicioCelebracion() {
		if(jLabelFechaInicioCelebracion == null) {
			jLabelFechaInicioCelebracion = new JLabel();
			jLabelFechaInicioCelebracion.setText("Comienzo");
			jLabelFechaInicioCelebracion.setBounds(14, 103, 57, 15);
			jLabelFechaInicioCelebracion.setFont(new java.awt.Font("Arial",0,10));
		}
		return jLabelFechaInicioCelebracion;
	}
	
	private JLabel getJLabelFechaInicioInscripcion() {
		if(jLabelFechaInicioInscripcion == null) {
			jLabelFechaInicioInscripcion = new JLabel();
			jLabelFechaInicioInscripcion.setText("Inscripcion");
			jLabelFechaInicioInscripcion.setBounds(14, 134, 61, 15);
			jLabelFechaInicioInscripcion.setFont(new java.awt.Font("Arial",0,10));
		}
		return jLabelFechaInicioInscripcion;
	}
	
	private JTextField getJTextFieldFechaInicioInscripcion() {
		if(jTextFieldFechaInicioInscripcion == null) {
			jTextFieldFechaInicioInscripcion = new JTextField();
			jTextFieldFechaInicioInscripcion.setBounds(77, 132, 109, 19);
			jTextFieldFechaInicioInscripcion.setFont(new java.awt.Font("Arial",0,10));
		}
		return jTextFieldFechaInicioInscripcion;
	}
	
	private JTextField getJTextFieldFechaFinInscripcion() {
		if(jTextFieldFechaFinInscripcion == null) {
			jTextFieldFechaFinInscripcion = new JTextField();
			jTextFieldFechaFinInscripcion.setBounds(270, 132, 113, 19);
			jTextFieldFechaFinInscripcion.setFont(new java.awt.Font("Arial",0,10));
		}
		return jTextFieldFechaFinInscripcion;
	}
	
	private JLabel getJLabelUmbral() {
		if(jLabelUmbral == null) {
			jLabelUmbral = new JLabel();
			jLabelUmbral.setText("Umbral %");
			jLabelUmbral.setBounds(14, 165, 53, 15);
			jLabelUmbral.setFont(new java.awt.Font("Arial",0,10));
		}
		return jLabelUmbral;
	}
	
	private JTextField getJTextFieldUmbral() {
		if(jTextFieldUmbral == null) {
			jTextFieldUmbral = new JTextField();
			jTextFieldUmbral.setBounds(77, 161, 49, 19);
			jTextFieldUmbral.setFont(new java.awt.Font("Arial",0,10));
		}
		return jTextFieldUmbral;
	}
	
	private JLabel getJLabelPrecio() {
		if(jLabelPrecio == null) {
			jLabelPrecio = new JLabel();
			jLabelPrecio.setBounds(198, 165, 65, 15);
			jLabelPrecio.setText("Precio");
			jLabelPrecio.setFont(new java.awt.Font("Arial",0,10));
		}
		return jLabelPrecio;
	}
	
	private JTextField getJTextFieldPrecio() {
		if(jTextFieldPrecio == null) {
			jTextFieldPrecio = new JTextField();
			jTextFieldPrecio.setBounds(270, 163, 78, 19);
			jTextFieldPrecio.setFont(new java.awt.Font("Arial",0,10));
		}
		return jTextFieldPrecio;
	}
	
	private JTextField getJTextFieldNombre() {
		if(jTextFieldNombre == null) {
			jTextFieldNombre = new JTextField();
			jTextFieldNombre.setBounds(75, 37, 331, 19);
			jTextFieldNombre.setFont(new java.awt.Font("Arial",0,10));
		}
		return jTextFieldNombre;
	}
	
	private JScrollPane getJScrollPaneRolPlazas() {
		if(jScrollPaneRolPlazas == null) {
			jScrollPaneRolPlazas = new JScrollPane();
			jScrollPaneRolPlazas.setBounds(584, 60, 120, 128);
			jScrollPaneRolPlazas.setFont(new java.awt.Font("Arial",0,10));
			jScrollPaneRolPlazas.setViewportView(getJTableRolPlazas());
		}
		return jScrollPaneRolPlazas;
	}
	
	private JTable getJTableRolPlazas() {
		if(jTableRolPlazas == null) {
			dtmRolPlazas = new DefaultTableModel();
			for(int i=0;i<columnNamesRolPlazas.length;i++){dtmRolPlazas.addColumn(columnNamesRolPlazas[i]);}
			jTableRolPlazas = new JTable(dtmRolPlazas);
			Utils.ocultaColumna(jTableRolPlazas, 0);
			jTableRolPlazas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			jTableRolPlazas.setPreferredSize(new java.awt.Dimension(95, 97));
			jTableRolPlazas.setVerifyInputWhenFocusTarget(false);
			jTableRolPlazas.setBounds(23, 197, 117, 124);
		}
		return jTableRolPlazas;
	}
	
	private JLabel getJLabelRequisitos() {
		if(jLabelRequisitos == null) {
			jLabelRequisitos = new JLabel();
			jLabelRequisitos.setText("Requisitos");
			jLabelRequisitos.setBounds(447, 39, 119, 15);
			jLabelRequisitos.setFont(new java.awt.Font("Arial",0,10));
		}
		return jLabelRequisitos;
	}
	
	private JLabel getJLabelRolPlazas() {
		if(jLabelRolPlazas == null) {
			jLabelRolPlazas = new JLabel();
			jLabelRolPlazas.setText("Rol / Plazas");
			jLabelRolPlazas.setBounds(587, 39, 120, 15);
			jLabelRolPlazas.setFont(new java.awt.Font("Arial",0,10));
		}
		return jLabelRolPlazas;
	}
	
	private JLabel getJLabelDescripcion() {
		if(jLabelDescripcion == null) {
			jLabelDescripcion = new JLabel();
			jLabelDescripcion.setText("Descripci—n");
			jLabelDescripcion.setBounds(14, 225, 90, 15);
			jLabelDescripcion.setFont(new java.awt.Font("Arial",0,10));
		}
		return jLabelDescripcion;
	}
	
	private JTextArea getJTextAreaDescripcion() {
		if(jTextAreaDescripcion == null) {
			jTextAreaDescripcion = new JTextArea();
			jTextAreaDescripcion.setBounds(14, 246, 679, 101);
			jTextAreaDescripcion.setFont(new java.awt.Font("Arial",0,10));
		}
		return jTextAreaDescripcion;
	}
	private JTable getJTableRequisitos() {
		if(jTableRequisitos == null) {
			dtmRequisitos = new DefaultTableModel();
			for(int i=0;i<columnNamesRequisitos.length;i++){dtmRequisitos.addColumn(columnNamesRequisitos[i]);}
			jTableRequisitos = new JTable(dtmRequisitos);
			Utils.ocultaColumna(jTableRequisitos, 0);
			jTableRequisitos.setBounds(519, 60, 117, 126);
			jTableRequisitos.setVerifyInputWhenFocusTarget(false);
			jTableRequisitos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			jTableRequisitos.setPreferredSize(new java.awt.Dimension(102, 101));
		}
		return jTableRequisitos;
	}
	
	private JLabel getJLabelPlazas() {
		if(jLabelPlazas == null) {
			jLabelPlazas = new JLabel();
			jLabelPlazas.setText("Plazas");
			jLabelPlazas.setBounds(14, 196, 47, 15);
			jLabelPlazas.setFont(new java.awt.Font("Arial",0,10));
		}
		return jLabelPlazas;
	}
	
	private JTextField getJTextFieldPlazas() {
		if(jTextFieldPlazas == null) {
			jTextFieldPlazas = new JTextField();
			jTextFieldPlazas.setBounds(79, 194, 42, 19);
			jTextFieldPlazas.setFont(new java.awt.Font("Arial",0,10));
		}
		return jTextFieldPlazas;
	}
	
	private JButton getJButtonAlta() {
		if(jButtonAlta == null) {
			jButtonAlta = new JButton();
			ImageIcon icon = new ImageIcon("imagen/dcib023t.gif");
			jButtonAlta.setIcon(icon);
			jButtonAlta.setLayout(null);
			jButtonAlta.setText("Grabar");
			jButtonAlta.setBounds(275, 359, 90, 25);
			jButtonAlta.setFont(new java.awt.Font("Arial",0,10));
			jButtonAlta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						validaFormulario(bEventoModificacion.booleanValue());
						if(bEventoModificacion.booleanValue()){
							remote.modificaEvento(altaModificaEvento(bEventoModificacion.booleanValue()));
							Utils.mostraMensajeInformacion(jPanelDatos, "Registro modificado correctamente", "Modificaci—n evento");
						}else{
							remote.insertaEvento(altaModificaEvento(bEventoModificacion.booleanValue()));
							Utils.mostraMensajeInformacion(jPanelDatos, "Registro insertado correctamente.", "Alta evento");
							limpiaFormulario();
						}
					} catch (RemoteException e1) {
						try {
							throw new OperationErrorRMI(e1.getMessage());
						} catch (OperationErrorRMI e2) {
							e2.showDialogError(jPanelDatos);
						}
					} catch (OperationErrorBD e2) {
						e2.showDialogError(jPanelDatos);
					} catch (OperationErrorDatosFormulario e3) {
						e3.showDialogError(jPanelDatos);
					}
				}
	    	});
		}
		return jButtonAlta;
	}
	
	private JButton getJButtonCancelar() {
		if(jButtonCancelar == null) {
			jButtonCancelar = new JButton();
			ImageIcon icon = new ImageIcon("imagen/dcib022t.gif");
			jButtonCancelar.setIcon(icon);
			jButtonCancelar.setLayout(null);
			jButtonCancelar.setText("Cancelar");
			jButtonCancelar.setBounds(369, 359, 90, 25);
			jButtonCancelar.setFont(new java.awt.Font("Arial",0,10));
			jButtonCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					removeAll();
				}
	    	});
		}
		return jButtonCancelar;
	}
	
	private JButton getJButtonClear() {
		if (JButtonClear == null) {
			JButtonClear = new JButton();
			JButtonClear.setLayout(null);
			JButtonClear.setText("Limpiar");
			JButtonClear.setBounds(464, 359, 90, 25);
			JButtonClear.setFont(new java.awt.Font("Arial",0,10));
			JButtonClear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					limpiaFormulario();
				}
	    	});
		}
		return JButtonClear;
	}
	
	private JLabel getJLabelDuracion() {
		if(jLabelDuracion == null) {
			jLabelDuracion = new JLabel();
			jLabelDuracion.setText("Duraci—n");
			jLabelDuracion.setBounds(198, 197, 60, 15);
			jLabelDuracion.setFont(new java.awt.Font("Arial",0,10));
		}
		return jLabelDuracion;
	}
	
	private JTextField getJTextFieldDuracion() {
		if(jTextFieldDuracion == null) {
			jTextFieldDuracion = new JTextField();
			jTextFieldDuracion.setBounds(270, 195, 66, 19);
			jTextFieldDuracion.setFont(new java.awt.Font("Arial",0,10));
		}
		return jTextFieldDuracion;
	}
}
