package uoc.edu.tds.pec4.pantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import uoc.edu.tds.pec4.beans.Usuario;
import uoc.edu.tds.pec4.dtos.DTOCentroDocente;
import uoc.edu.tds.pec4.dtos.DTOEvento;
import uoc.edu.tds.pec4.dtos.DTOTipoEvento;
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
	private Usuario usuario;
	private DTOEvento dtoEventoAModficar;
	private DTOCentroDocente dtoCentroDocente;
	private Boolean bEventoModificacion = false;

	// TODO 1: Revisar	

	public PantallaEvento(RemoteInterface remote1, Usuario usuarioLogin, DTOEvento eventoAModificar){
		super();
		this.remote = remote1;
		this.usuario = usuarioLogin;

		System.out.print("Para quitar el warning que sale si no se utiliza es provisional " + remote.toString());	
		initGUI();
		if (eventoAModificar != null){ 
			this.dtoEventoAModficar = eventoAModificar;
			bEventoModificacion = true;
			cargaEvento();
			//Deshabilitamos que se pueda cambiar el centro docente
			jComboBoxTipoEvento.setEditable(false);
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
	
	public PantallaEvento(RemoteInterface remote1, Usuario usuarioLogin){
		this(remote1, usuarioLogin, null);
	}

	private void initGUI() {
		try {
			this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(null, bEventoModificacion.booleanValue()?"Modificación de evento":"Alta de evento", 0, 0, new Font("Dialog", 1, 12), new Color(51, 51, 51)), null), null));
			this.setPreferredSize(new java.awt.Dimension(784, 538));
			this.add(getJPanelDatos());
			this.add(getJPanelCentro());

			//Rellenamos inforamción de los filtros
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
		/*
		try {
			dtoCentroDocente = remote.getCentoDocentebyId(usuario.getIdCentro());
			
			cargaDatosCentroDocenteEnPantalla(dtoCentroDocente);
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
		}*/
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
				// TODO 1: Realmente todos son obligatorios por lo que no hay que mirar si son null.
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
				
				
				
				/*****************************************
				 * CARGAMOS LOS ROL  /PLAZAS
				 ****************************************/
				

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
		}
	}
		
	
	/**
	 * Método que valida los datos introducidos en el formulario
	 * @throws OperationErrorDatosFormulario
	 */
	private void validaFormulario(boolean modificacion) throws OperationErrorDatosFormulario{
		try{
			if(Utils.valorisNull(jTextFieldNombre.getText())) throw new Exception(Utils.MESSAGE_ERROR + " nombre" );
			if(Utils.valorisNull(jTextFieldFechaInicioCelebracion.getText())) throw new Exception(Utils.MESSAGE_ERROR + " fecha inicio celebración" );
			if(Utils.valorisNull(jTextFieldFechaFinCelebracion.getText())) throw new Exception(Utils.MESSAGE_ERROR + " fecha fin celebración" );
			if(Utils.valorisNull(jTextFieldFechaInicioInscripcion.getText())) throw new Exception(Utils.MESSAGE_ERROR + " fecha inicio inscripción" );
			if(Utils.valorisNull(jTextFieldFechaFinInscripcion.getText())) throw new Exception(Utils.MESSAGE_ERROR + " fecha fin inscripción" );
			if(Utils.valorisNull(jTextFieldUmbral.getText())) throw new Exception(Utils.MESSAGE_ERROR + " umbral" );
			if(Utils.valorisNull(jTextFieldPrecio.getText())) throw new Exception(Utils.MESSAGE_ERROR + " precio" );
			if(Utils.valorisNull(jTextFieldPlazas.getText())) throw new Exception(Utils.MESSAGE_ERROR + " plazas" );
			if(Utils.valorisNull(jTextFieldDuracion.getText())) throw new Exception(Utils.MESSAGE_ERROR + " duración" );
			
			//Desde la pantalla de modificación no se puede cambiar el tipo evento
			if(!modificacion){
				if(Utils.valorisNull(jComboBoxTipoEvento.getSelectedItem())) throw new Exception(Utils.MESSAGE_ERROR + " tipo evento" );
			}
			
			if(!Utils.parseaFecha(jTextFieldFechaInicioCelebracion.getText())) throw new Exception(Utils.MESSAGE_ERROR + " fecha inicio celebración " + Utils.MESSAGE_FECHA );
			if(!Utils.parseaFecha(jTextFieldFechaFinCelebracion.getText())) throw new Exception(Utils.MESSAGE_ERROR + " fecha fin celebración " + Utils.MESSAGE_FECHA );
			if(!Utils.parseaFecha(jTextFieldFechaInicioInscripcion.getText())) throw new Exception(Utils.MESSAGE_ERROR + " fecha inicio inscripción " + Utils.MESSAGE_FECHA );
			if(!Utils.parseaFecha(jTextFieldFechaFinInscripcion.getText())) throw new Exception(Utils.MESSAGE_ERROR + " fecha fin inscripción " + Utils.MESSAGE_FECHA );
			
			if(!Utils.validaNumerico(jTextFieldUmbral.getText())) throw new Exception(Utils.MESSAGE_ERROR + " umbral " + Utils.MESSAGE_NUMERIC );
			if(!Utils.validaNumerico(jTextFieldPrecio.getText())) throw new Exception(Utils.MESSAGE_ERROR + " precio " + Utils.MESSAGE_NUMERIC );
			if(!Utils.validaNumerico(jTextFieldPlazas.getText())) throw new Exception(Utils.MESSAGE_ERROR + " plazas " + Utils.MESSAGE_NUMERIC );
			if(!Utils.validaNumerico(jTextFieldDuracion.getText())) throw new Exception(Utils.MESSAGE_ERROR + " duración " + Utils.MESSAGE_NUMERIC );
		}catch(Exception e){
			throw new OperationErrorDatosFormulario(e.getMessage());
		}
	}
	
	/**
	 * Método para cargar los combos de la pantalla Evento
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
	 * Método donde seteamos el evento que se va a insertar o eliminar
	 * @param modificacion
	 * @return
	 * @throws OperationErrorDatosFormulario
	 */
	private DTOEvento altaModificaEvento(boolean modificacion) throws OperationErrorDatosFormulario{

		/*****************************************
		 * DATOS ESPECÍFICOS DEL EVENTO
		 *****************************************/
		Evento evento = new Evento();
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
			// Si es modificación estos ID se han de conservar del dtoEvento pasado a la pantalla
			evento.setIdEvento(dtoEventoAModficar.getEvento().getIdEvento());
			evento.setIdCentro(dtoEventoAModficar.getEvento().getIdCentro());
		}
		else {
			// El Centro docente si es alta ha de ser el del usuario
			evento.setIdCentro(usuario.getIdCentro());
		}
		
		// TODO 1: Requisitos
		// TODO 1: Rol / Plazas
		
		/***********************************************
		 * RELLENAMOS EL DTOEvento CON TODOS LOS VALORES
		 ***********************************************/
		DTOEvento dtoEvento = new DTOEvento();
		dtoEvento.setEvento(evento);
		return dtoEvento;
	}
	
	/**
	 * Limpia el formulario
	 */
	private void limpiaFormulario(){
		ClearForm.clearForm(jPanelDatos);
		//ClearForm.clearForm(jPanelCentro);
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
			jPanelCentro.setPreferredSize(new java.awt.Dimension(702, 71));
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
			jLabelNombreCentroText.setFont(new java.awt.Font("Dialog",1,12));
		}
		return jLabelNombreCentroText;
	}

	private JLabel getJLabelNombreCentro() {
		if(jLabelNombreCentro == null) {
			jLabelNombreCentro = new JLabel();
			jLabelNombreCentro.setText("xxxxxx");
			jLabelNombreCentro.setBounds(14, 31, 361, 15);
		}
		return jLabelNombreCentro;
	}
	
	private JLabel getJLabelCodigoText() {
		if(jLabelCodigoText == null) {
			jLabelCodigoText = new JLabel();
			jLabelCodigoText.setText("Código");
			jLabelCodigoText.setBounds(453, 9, 75, 15);
			jLabelCodigoText.setFont(new java.awt.Font("Dialog",1,12));
		}
		return jLabelCodigoText;
	}
	
	private JLabel getJLabelCodigo() {
		if(jLabelCodigo == null) {
			jLabelCodigo = new JLabel();
			jLabelCodigo.setText("xxxxxx");
			jLabelCodigo.setBounds(453, 31, 85, 15);
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
			jPanelDatos.setPreferredSize(new java.awt.Dimension(712, 422));
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
			jLabelTipoEvento.setBounds(14, 83, 68, 15);
		}
		return jLabelTipoEvento;
	}
	
	private JComboBox getJComboBoxTipoEvento() {
		if(jComboBoxTipoEvento == null) {
			jComboBoxTipoEvento = new JComboBox();
			jComboBoxTipoEvento.setOpaque(false);
			jComboBoxTipoEvento.setBounds(82, 79, 328, 22);
		}
		return jComboBoxTipoEvento;
	}

	private JLabel getJLabelFechaFinInscripcion() {
		if(jLabelFechaFinInscripcion == null) {
			jLabelFechaFinInscripcion = new JLabel();
			jLabelFechaFinInscripcion.setText("hasta");
			jLabelFechaFinInscripcion.setLayout(null);
			jLabelFechaFinInscripcion.setBounds(198, 145, 66, 15);
		}
		return jLabelFechaFinInscripcion;
	}
	
	private JTextField getJTextFieldFechaInicioCelebracion() {
		if(jTextFieldFechaInicioCelebracion == null) {
			jTextFieldFechaInicioCelebracion = new JTextField();
			jTextFieldFechaInicioCelebracion.setText("01/01/2010");
			jTextFieldFechaInicioCelebracion.setBounds(78, 111, 113, 19);
		}
		return jTextFieldFechaInicioCelebracion;
	}
	
	private JLabel getJLabelFechaFinCelebracion() {
		if(jLabelFechaFinCelebracion == null) {
			jLabelFechaFinCelebracion = new JLabel();
			jLabelFechaFinCelebracion.setText("Fecha Final");
			jLabelFechaFinCelebracion.setLayout(null);
			jLabelFechaFinCelebracion.setBounds(198, 113, 72, 15);
		}
		return jLabelFechaFinCelebracion;
	}
	
	private JTextField getJTextFieldFechaFinCelebracion() {
		if(jTextFieldFechaFinCelebracion == null) {
			jTextFieldFechaFinCelebracion = new JTextField();
			jTextFieldFechaFinCelebracion.setText("01/01/2012");
			jTextFieldFechaFinCelebracion.setBounds(270, 111, 113, 19);
		}
		return jTextFieldFechaFinCelebracion;
	}
	
	private JScrollPane getJScrollPaneRequisitos() {
		if(jScrollPaneRequisitos == null) {
			jScrollPaneRequisitos = new JScrollPane();
			jScrollPaneRequisitos.setBounds(443, 62, 120, 128);
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
			jButtonRequisitos.setBounds(443, 199, 130, 25);
			// TODO 1: Gestion de los requisitos.
		}
		return jButtonRequisitos;
	}
	
	private JButton getJButtonRolPlazas() {
		if(jButtonRolPlazas == null) {
			jButtonRolPlazas = new JButton();
			jButtonRolPlazas.setLayout(null);
			jButtonRolPlazas.setText("Rol/Plazas");
			jButtonRolPlazas.setBounds(584, 199, 130, 25);
		}
		return jButtonRolPlazas;
	}
	
	private JLabel getJLabelEventoText() {
		if(jLabelEventoText == null) {
			jLabelEventoText = new JLabel();
			jLabelEventoText.setText("Datos del evento");
			jLabelEventoText.setBounds(14, 12, 143, 15);
			jLabelEventoText.setFont(new java.awt.Font("Dialog",1,12));
		}
		return jLabelEventoText;
	}
	
	private JLabel getJLabelNombreText() {
		if(jLabelNombreText == null) {
			jLabelNombreText = new JLabel();
			jLabelNombreText.setText("Nombre");
			jLabelNombreText.setBounds(14, 51, 47, 15);
		}
		return jLabelNombreText;
	}
	
	private JLabel getJLabelFechaInicioCelebracion() {
		if(jLabelFechaInicioCelebracion == null) {
			jLabelFechaInicioCelebracion = new JLabel();
			jLabelFechaInicioCelebracion.setText("Comienzo");
			jLabelFechaInicioCelebracion.setBounds(14, 113, 57, 15);
		}
		return jLabelFechaInicioCelebracion;
	}
	
	private JLabel getJLabelFechaInicioInscripcion() {
		if(jLabelFechaInicioInscripcion == null) {
			jLabelFechaInicioInscripcion = new JLabel();
			jLabelFechaInicioInscripcion.setText("Inscripcion");
			jLabelFechaInicioInscripcion.setBounds(14, 145, 71, 15);
		}
		return jLabelFechaInicioInscripcion;
	}
	
	private JTextField getJTextFieldFechaInicioInscripcion() {
		if(jTextFieldFechaInicioInscripcion == null) {
			jTextFieldFechaInicioInscripcion = new JTextField();
			jTextFieldFechaInicioInscripcion.setText("01/01/2010");
			jTextFieldFechaInicioInscripcion.setBounds(79, 143, 109, 19);
		}
		return jTextFieldFechaInicioInscripcion;
	}
	
	private JTextField getJTextFieldFechaFinInscripcion() {
		if(jTextFieldFechaFinInscripcion == null) {
			jTextFieldFechaFinInscripcion = new JTextField();
			jTextFieldFechaFinInscripcion.setText("01/01/2012");
			jTextFieldFechaFinInscripcion.setBounds(270, 143, 113, 19);
		}
		return jTextFieldFechaFinInscripcion;
	}
	
	private JLabel getJLabelUmbral() {
		if(jLabelUmbral == null) {
			jLabelUmbral = new JLabel();
			jLabelUmbral.setText("Umbral %");
			jLabelUmbral.setBounds(14, 178, 65, 15);
		}
		return jLabelUmbral;
	}
	
	private JTextField getJTextFieldUmbral() {
		if(jTextFieldUmbral == null) {
			jTextFieldUmbral = new JTextField();
			jTextFieldUmbral.setBounds(79, 176, 49, 19);
		}
		return jTextFieldUmbral;
	}
	
	private JLabel getJLabelPrecio() {
		if(jLabelPrecio == null) {
			jLabelPrecio = new JLabel();
			jLabelPrecio.setBounds(198, 178, 65, 15);
			jLabelPrecio.setText("Precio");
		}
		return jLabelPrecio;
	}
	
	private JTextField getJTextFieldPrecio() {
		if(jTextFieldPrecio == null) {
			jTextFieldPrecio = new JTextField();
			jTextFieldPrecio.setText("0");
			jTextFieldPrecio.setBounds(275, 175, 78, 19);
		}
		return jTextFieldPrecio;
	}
	
	private JTextField getJTextFieldNombre() {
		if(jTextFieldNombre == null) {
			jTextFieldNombre = new JTextField();
			jTextFieldNombre.setBounds(79, 46, 331, 19);
		}
		return jTextFieldNombre;
	}
	
	private JScrollPane getJScrollPaneRolPlazas() {
		if(jScrollPaneRolPlazas == null) {
			jScrollPaneRolPlazas = new JScrollPane();
			jScrollPaneRolPlazas.setBounds(584, 60, 120, 128);
			jScrollPaneRolPlazas.setViewportView(getJTableRolPlazas());
		}
		return jScrollPaneRolPlazas;
	}
	
	private JTable getJTableRolPlazas() {
		if(jTableRolPlazas == null) {
			dtmRolPlazas = new DefaultTableModel();
			for(int i=0;i<columnNamesRolPlazas.length;i++){dtmRolPlazas.addColumn(columnNamesRolPlazas[i]);}
			jTableRolPlazas = new JTable(dtmRolPlazas);
			Utils.ocultaColumna(jTableRequisitos, 0);
			jTableRolPlazas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			jTableRolPlazas.setPreferredSize(new java.awt.Dimension(117, 108));
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
		}
		return jLabelRequisitos;
	}
	
	private JLabel getJLabelRolPlazas() {
		if(jLabelRolPlazas == null) {
			jLabelRolPlazas = new JLabel();
			jLabelRolPlazas.setText("Rol / Plazas");
			jLabelRolPlazas.setBounds(587, 39, 120, 15);
		}
		return jLabelRolPlazas;
	}
	
	private JLabel getJLabelDescripcion() {
		if(jLabelDescripcion == null) {
			jLabelDescripcion = new JLabel();
			jLabelDescripcion.setText("Descripción");
			jLabelDescripcion.setBounds(14, 248, 67, 15);
		}
		return jLabelDescripcion;
	}
	
	private JTextArea getJTextAreaDescripcion() {
		if(jTextAreaDescripcion == null) {
			jTextAreaDescripcion = new JTextArea();
			jTextAreaDescripcion.setBounds(18, 275, 679, 90);
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
			jTableRequisitos.setPreferredSize(new java.awt.Dimension(115, 110));
		}
		return jTableRequisitos;
	}
	
	private JLabel getJLabelPlazas() {
		if(jLabelPlazas == null) {
			jLabelPlazas = new JLabel();
			jLabelPlazas.setText("Plazas");
			jLabelPlazas.setBounds(14, 217, 37, 15);
		}
		return jLabelPlazas;
	}
	
	private JTextField getJTextFieldPlazas() {
		if(jTextFieldPlazas == null) {
			jTextFieldPlazas = new JTextField();
			jTextFieldPlazas.setText("0");
			jTextFieldPlazas.setBounds(81, 215, 42, 19);
		}
		return jTextFieldPlazas;
	}
	
	private JButton getJButtonAlta() {
		if(jButtonAlta == null) {
			jButtonAlta = new JButton();
			jButtonAlta.setLayout(null);
			jButtonAlta.setText("Alta");
			jButtonAlta.setBounds(275, 387, 83, 25);
			jButtonAlta.setSize(90, 25);
			jButtonAlta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						validaFormulario(bEventoModificacion.booleanValue());
						if(bEventoModificacion.booleanValue()){
							remote.modificaEvento(altaModificaEvento(bEventoModificacion.booleanValue()));
							Utils.mostraMensajeInformacion(jPanelDatos, "Registro modificado correctamente", "Modificación evento");
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
			jButtonCancelar.setLayout(null);
			jButtonCancelar.setText("Cancelar");
			jButtonCancelar.setBounds(369, 387, 77, 25);
			jButtonCancelar.setSize(90, 25);
			//TODO 1: Comprobar que nos podamos inscribir y llamar a la pantalla de isncripción (alta)
		}
		return jButtonCancelar;
	}
	
	private JButton getJButtonClear() {
		if (JButtonClear == null) {
			JButtonClear = new JButton();
			JButtonClear.setLayout(null);
			JButtonClear.setText("Limpiar");
			JButtonClear.setBounds(464, 387, 90, 25);
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
			jLabelDuracion.setText("Duración");
			jLabelDuracion.setBounds(198, 218, 51, 15);
		}
		return jLabelDuracion;
	}
	
	private JTextField getJTextFieldDuracion() {
		if(jTextFieldDuracion == null) {
			jTextFieldDuracion = new JTextField();
			jTextFieldDuracion.setBounds(275, 216, 66, 19);
		}
		return jTextFieldDuracion;
	}
}
