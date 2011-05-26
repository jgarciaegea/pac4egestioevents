package uoc.edu.tds.pec4.pantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import uoc.edu.tds.pec4.beans.CentroDocente;
import uoc.edu.tds.pec4.beans.Contacto;
import uoc.edu.tds.pec4.beans.Telefono;
import uoc.edu.tds.pec4.dtos.DTOCentroDocente;
import uoc.edu.tds.pec4.dtos.DTOContacto;
import uoc.edu.tds.pec4.dtos.DTOPais;
import uoc.edu.tds.pec4.dtos.DTOTelefono;
import uoc.edu.tds.pec4.dtos.DTOTipoTelefono;
import uoc.edu.tds.pec4.dtos.DTOUniversidad;
import uoc.edu.tds.pec4.excepciones.OperationErrorBD;
import uoc.edu.tds.pec4.excepciones.OperationErrorDatosFormulario;
import uoc.edu.tds.pec4.excepciones.OperationErrorRMI;
import uoc.edu.tds.pec4.iface.RemoteInterface;
import uoc.edu.tds.pec4.resources.TDSLanguageUtils;
import uoc.edu.tds.pec4.utils.ClearForm;
import uoc.edu.tds.pec4.utils.EmailValidator;
import uoc.edu.tds.pec4.utils.JTextFieldLimit;
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
public class PantallaCentroDocente extends javax.swing.JPanel implements Pantallas {
	
	private static final long serialVersionUID = 1L;
	private JComboBox jComboBoxUniver;
	private JComboBox jComboBoxTipo;
	private JLabel jLabelTipo;
	private JTextField jTextFieldExtension;
	private JButton jButtonAcceptar;
	private JTextField jTextFieldPrefijo;
	private JLabel jLabelPrefijo;
	private JButton jButtonCancelar;
	private JPanel jPanelButtom;
	private JLabel jLabelExtension;
	private JTextField jTextFieldTelefono;
	private JLabel jLabelTelf;
	private JTextField jTextFieldWebBlog;
	private JLabel jLabelBlog;
	private JTextField jTextFieldEmail;
	private JLabel jLabelEmail;
	private JComboBox jComboBoxpais;
	private JLabel jLabelPais;
	private JTextField jTextFieldCP;
	private JLabel jLabelCP;
	private JTextField jTextFieldLocalidad;
	private JLabel jLabelLocalidad;
	private JTextField jTextFieldDirec;
	private JLabel jLabelDirec;
	private JLabel jLabelApe;
	private JTextField jTextFieldNombre;
	private JLabel jLabelNombre;
	private JPanel jPanel2;
	private RemoteInterface remote;
	private Boolean bCentroDocenteModif = false;
	private DTOCentroDocente dtoCentroDocenteModificar;
	/*
	 * Constructor que recibe un idUsuario y lo calculamos
	 */
	public PantallaCentroDocente(RemoteInterface remote1, DTOCentroDocente dtoCentroDocente) {
		super();
		remote = remote1;
		try {
			remote.testConexion();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		System.out.print("Para quitar el warning que sale si no se utiliza es provisional " + remote.toString());
		initGUI();
		this.dtoCentroDocenteModificar = dtoCentroDocente;
		if(dtoCentroDocenteModificar != null){
			bCentroDocenteModif = true;//Significa que vamos a realizar la modificación
			cargaCentroDocente();
			
		}
		
	}
	
	public PantallaCentroDocente(RemoteInterface remote1) {
		this(remote1,null);
	}
	
	private void initGUI() {
		try {
			this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(null, bCentroDocenteModif.booleanValue()?"Modificación de centro docente":"Alta de centro docente", 0, 0, new Font("Dialog", 1, 12), new Color(51, 51, 51)), null), null));
			this.setPreferredSize(new java.awt.Dimension(784, 634));
			{
				jPanel2 = new JPanel();
				this.add(jPanel2);
				GridBagLayout jPanel2Layout = new GridBagLayout();
				jPanel2Layout.columnWidths = new int[] {142, 222, 108};
				jPanel2Layout.rowHeights = new int[] {28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 20};
				jPanel2Layout.columnWeights = new double[] {0.0, 0.0, 0.0};
				jPanel2Layout.rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.1};
				jPanel2.setPreferredSize(new java.awt.Dimension(728, 444));
				jPanel2.setLayout(jPanel2Layout);
				{
					jLabelNombre = new JLabel();
					jPanel2.add(jLabelNombre, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(4, 0, 0, 0), 0, 0));
					jLabelNombre.setText(TDSLanguageUtils.getMessage("clientePEC4.altausuario.label23"));
					jLabelNombre.setLayout(null);
				}
				{
					jTextFieldNombre = new JTextField();
					jPanel2.add(jTextFieldNombre, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 8, 0, 0), 0, 0));
					jTextFieldNombre.setPreferredSize(new java.awt.Dimension(200, 21));
				}
				{
					jLabelApe = new JLabel();
					jPanel2.add(jLabelApe, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(6, 0, 0, 0), 0, 0));
					jLabelApe.setText(TDSLanguageUtils.getMessage("clientePEC4.altausuario.label5"));
					jLabelApe.setLayout(null);
				}
				{
					jLabelDirec = new JLabel();
					jPanel2.add(jLabelDirec, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabelDirec.setText(TDSLanguageUtils.getMessage("clientePEC4.altausuario.label8"));
					jLabelDirec.setLayout(null);
				}
				{
					jTextFieldDirec = new JTextField();
					jPanel2.add(jTextFieldDirec, new GridBagConstraints(1, 2, 2, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					jTextFieldDirec.setPreferredSize(new java.awt.Dimension(200, 21));
				}
				{
					jLabelLocalidad = new JLabel();
					jPanel2.add(jLabelLocalidad, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabelLocalidad.setText(TDSLanguageUtils.getMessage("clientePEC4.altausuario.label9"));
					jLabelLocalidad.setLayout(null);
				}
				{
					jTextFieldLocalidad = new JTextField();
					jPanel2.add(jTextFieldLocalidad, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					jTextFieldLocalidad.setPreferredSize(new java.awt.Dimension(200, 21));
				}
				{
					jLabelCP = new JLabel();
					jPanel2.add(jLabelCP, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabelCP.setText(TDSLanguageUtils.getMessage("clientePEC4.altausuario.label18"));
					jLabelCP.setLayout(null);
					
				}
				{
					jTextFieldCP = new JTextField();
					jPanel2.add(jTextFieldCP, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					jTextFieldCP.setPreferredSize(new java.awt.Dimension(200, 21));
					jTextFieldCP.setDocument(new JTextFieldLimit(5));
				}
				{
					jLabelPais = new JLabel();
					jPanel2.add(jLabelPais, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabelPais.setText(TDSLanguageUtils.getMessage("clientePEC4.altausuario.label19"));
					jLabelPais.setLayout(null);
				}
				{
					
					jComboBoxpais = new JComboBox();
					jPanel2.add(jComboBoxpais, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					jComboBoxpais.setPreferredSize(new java.awt.Dimension(200, 26));
				}
				{
					jLabelEmail = new JLabel();
					jPanel2.add(jLabelEmail, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabelEmail.setText(TDSLanguageUtils.getMessage("clientePEC4.altausuario.label10"));
					jLabelEmail.setLayout(null);
				}
				{
					jTextFieldEmail = new JTextField();
					jPanel2.add(jTextFieldEmail, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					jTextFieldEmail.setPreferredSize(new java.awt.Dimension(200, 21));
				}
				{
					jLabelBlog = new JLabel();
					jPanel2.add(jLabelBlog, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabelBlog.setText(TDSLanguageUtils.getMessage("clientePEC4.altausuario.label11"));
					jLabelBlog.setLayout(null);
				}
				{
					jTextFieldWebBlog = new JTextField();
					jPanel2.add(jTextFieldWebBlog, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					jTextFieldWebBlog.setPreferredSize(new java.awt.Dimension(200, 21));
				}
				{
					jLabelTelf = new JLabel();
					jPanel2.add(jLabelTelf, new GridBagConstraints(0, 9, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabelTelf.setText(TDSLanguageUtils.getMessage("clientePEC4.altausuario.label12"));
					jLabelTelf.setLayout(null);
				}
				{
					jTextFieldTelefono = new JTextField();
					jPanel2.add(jTextFieldTelefono, new GridBagConstraints(1, 9, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					jTextFieldTelefono.setPreferredSize(new java.awt.Dimension(200, 21));
					jTextFieldTelefono.setDocument(new JTextFieldLimit(9));
				}
				{
					jLabelExtension = new JLabel();
					jPanel2.add(jLabelExtension, new GridBagConstraints(0, 10, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabelExtension.setText(TDSLanguageUtils.getMessage("clientePEC4.altausuario.label13"));
				}
				{
					jTextFieldExtension = new JTextField();
					jPanel2.add(jTextFieldExtension, new GridBagConstraints(1, 10, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					jTextFieldExtension.setPreferredSize(new java.awt.Dimension(200, 21));
				}
				{
					jLabelTipo = new JLabel();
					jPanel2.add(jLabelTipo, new GridBagConstraints(0, 12, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabelTipo.setText(TDSLanguageUtils.getMessage("clientePEC4.altausuario.label14"));
				}
				{
					
					jComboBoxTipo = new JComboBox();
					jPanel2.add(jComboBoxTipo, new GridBagConstraints(1, 12, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					
					jComboBoxTipo.setPreferredSize(new java.awt.Dimension(200, 26));
				}
				{
					jComboBoxUniver = new JComboBox();
					jPanel2.add(jComboBoxUniver, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					jComboBoxUniver.setPreferredSize(new java.awt.Dimension(200, 25));
				}
				{
					jLabelPrefijo = new JLabel();
					jPanel2.add(jLabelPrefijo, new GridBagConstraints(0, 11, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabelPrefijo.setText(TDSLanguageUtils.getMessage("clientePEC4.altausuario.label22"));
				}
				{
					jTextFieldPrefijo = new JTextField();
					jPanel2.add(jTextFieldPrefijo, new GridBagConstraints(1, 11, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					jTextFieldPrefijo.setPreferredSize(new java.awt.Dimension(200, 21));
				}
			}
			{
				jPanelButtom = new JPanel();
				this.add(jPanelButtom);
				jPanelButtom.setPreferredSize(new java.awt.Dimension(758, 56));
				jPanelButtom.setBounds(238, 280, 10, 10);
				{
					jButtonAcceptar = new JButton();
					jPanelButtom.add(jButtonAcceptar);
					jButtonAcceptar.setText(TDSLanguageUtils.getMessage("clientePEC4.altausuario.boton1"));
					jButtonAcceptar.setLayout(null);
					jButtonAcceptar.setBounds(277, -16, 64, 21);
					
					jButtonAcceptar.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
									try {
										validaFormulario(bCentroDocenteModif.booleanValue());
										if(bCentroDocenteModif.booleanValue()){
											remote.modificaCentroDocente(altaModificaCentroDocente(bCentroDocenteModif.booleanValue()));
											Utils.mostraMensajeInformacion(jPanel2, TDSLanguageUtils.getMessage("clientePEC4.altausuario.INFO.MSG1"), TDSLanguageUtils.getMessage("clientePEC4.altacentro.title2"));
										}else{
											remote.insertaCentroDocente(altaModificaCentroDocente(bCentroDocenteModif.booleanValue()));
											Utils.mostraMensajeInformacion(jPanel2, TDSLanguageUtils.getMessage("clientePEC4.altacentro.MSG1"), TDSLanguageUtils.getMessage("clientePEC4.altacentro.title"));
											limpiaFormulario();
										}
									} catch (RemoteException e1) {
										try {
											throw new OperationErrorRMI(e1.getMessage());
										} catch (OperationErrorRMI e2) {
											e2.showDialogError(jPanel2);
										}
									} catch (OperationErrorBD e2) {
										e2.showDialogError(jPanel2);
									} catch (OperationErrorDatosFormulario e3) {
										e3.showDialogError(jPanel2);
									}
							}
				    	});
					 
				}
				{
					jButtonCancelar = new JButton();
					jPanelButtom.add(jButtonCancelar);
					jButtonCancelar.setText(TDSLanguageUtils.getMessage("clientePEC4.calendarioeventos.label10"));
					jButtonCancelar.setLayout(null);
					
					jButtonCancelar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							limpiaFormulario();
						}
			    	});
				}
			}

			//Cargamos combos
			cargaCombos();
			
		} catch (Exception e) {
			try{
				throw new OperationErrorDatosFormulario(e.getMessage());
			}catch(OperationErrorDatosFormulario ex){
				ex.showDialogError(jPanel2);
			}
		}
	}
	
	/**
	 * Método que valida los datos introducidos en el formulario
	 * @throws OperationErrorDatosFormulario
	 */
	private void validaFormulario(boolean modificacion) throws OperationErrorDatosFormulario{
		try{
			
			//Contacto y centro
			if(Utils.valorisNull(jComboBoxUniver.getSelectedItem()))  throw new Exception(Utils.MESSAGE_ERROR + " " + TDSLanguageUtils.getMessage("clientePEC4.altausuario.label23"));
			if(Utils.valorisNull(jTextFieldNombre.getText())) throw new Exception(Utils.MESSAGE_ERROR + " " + TDSLanguageUtils.getMessage("clientePEC4.altacentro.label1"));
			if(Utils.valorisNull(jTextFieldDirec.getText())) throw new Exception(Utils.MESSAGE_ERROR + " " + TDSLanguageUtils.getMessage("clientePEC4.altausuario.label8"));
			if(Utils.valorisNull(jTextFieldLocalidad.getText())) throw new Exception(Utils.MESSAGE_ERROR + " " + TDSLanguageUtils.getMessage("clientePEC4.altausuario.label9"));
			
			EmailValidator emailValidator = new EmailValidator();
			if(!emailValidator.validate(jTextFieldEmail.getText())) throw new Exception (Utils.MESSAGE_EMAIL); 
			if(Utils.valorisNull(jTextFieldCP.getText())) throw new Exception(Utils.MESSAGE_ERROR + " " + TDSLanguageUtils.getMessage("clientePEC4.altausuario.label18"));
			
			//Pais
			if(Utils.valorisNull(jComboBoxpais.getSelectedItem())) throw new Exception(Utils.MESSAGE_ERROR + " " + TDSLanguageUtils.getMessage("clientePEC4.altausuario.label19"));
			
			//Teléfono
			if(Utils.valorisNull(jTextFieldTelefono.getText())) throw new Exception(Utils.MESSAGE_ERROR + " " + TDSLanguageUtils.getMessage("clientePEC4.altausuario.label12"));
			if(Utils.valorisNull(jComboBoxTipo.getSelectedItem())) throw new Exception(Utils.MESSAGE_ERROR + " "+ TDSLanguageUtils.getMessage("clientePEC4.altacentro.label2"));
			
		}catch(Exception e){
			throw new OperationErrorDatosFormulario(TDSLanguageUtils.getMessage("clientePEC4.error14"));
		}
			
	}
	
	/**
	 * Método para cargar los combos de la pantalla Usuario
	 * @throws OperationErrorDatosFormulario
	 */
	private void cargaCombos() throws OperationErrorDatosFormulario{
		try{
			
			
			//Cargamos las universidades
			List<DTOUniversidad> lstDtoUniversidad = remote.getUniversidades();
			List<MostrarCombo> lstComboUniv = new ArrayList<MostrarCombo>();
			if(lstDtoUniversidad != null){
				for(DTOUniversidad dtoUniverRec : lstDtoUniversidad){
					lstComboUniv.add(new MostrarCombo(dtoUniverRec.getUniversidad().getIdUniversidad(),
							dtoUniverRec.getUniversidad().getNombre()));
				}
			}
			ComboBoxModel jComboBoxUniverModel = new DefaultComboBoxModel(lstComboUniv.toArray());
			jComboBoxUniver.setModel(jComboBoxUniverModel);
			
			//Cargamos la lista de países
			List<DTOPais> lstDtoPaises = remote.getPaises();
			List<MostrarCombo> lstComboPais = new ArrayList<MostrarCombo>();
			if(lstDtoPaises!=null){
				for(DTOPais dtoPaisRec : lstDtoPaises){
					lstComboPais.add(new MostrarCombo(dtoPaisRec.getPais().getIdPais(),dtoPaisRec.getPais().getNombrePais()));
				}
			}
			ComboBoxModel jComboBoxpaisModel =  new DefaultComboBoxModel(lstComboPais.toArray());
			jComboBoxpais.setModel(jComboBoxpaisModel);
			
			//Cargamos los diferentes tipos de teléfono
			List<DTOTipoTelefono> lstDtoTiposTelf = remote.getTiposTelefono();
			List<MostrarCombo> lstComboTipoTelf = new ArrayList<MostrarCombo>();
			if(lstDtoTiposTelf!=null){
				for(DTOTipoTelefono dtoTipoTelfRec : lstDtoTiposTelf){
					lstComboTipoTelf.add(new MostrarCombo(dtoTipoTelfRec.getTipoTelefono().getIdTipoTelefono(),
							dtoTipoTelfRec.getTipoTelefono().getDescripcion()));
				}
			}
			
			ComboBoxModel jComboBoxTipoModel =  new DefaultComboBoxModel(lstComboTipoTelf.toArray());
			jComboBoxTipo.setModel(jComboBoxTipoModel);
			
		}catch(Exception e){
			throw new OperationErrorDatosFormulario(TDSLanguageUtils.getMessage("clientePEC4.altacentro.MSG2"));
		}
		
	}
	
	private DTOCentroDocente altaModificaCentroDocente(boolean modificacion) throws OperationErrorDatosFormulario{
		
		/*****************************************
		 * DATOS ESPECÍFICOS DEL CONRATO
		 *****************************************/
		DTOContacto dtoContacto = new DTOContacto();
		Contacto contacto = new Contacto();
		contacto.setDomicilio(jTextFieldDirec.getText());
		contacto.setCp(Integer.parseInt(jTextFieldCP.getText()));
		contacto.setLocalidad(jTextFieldLocalidad.getText());
		contacto.setIdPais(Integer.parseInt(((MostrarCombo) jComboBoxpais.getSelectedItem()).getID().toString()));
		contacto.setEmail(jTextFieldEmail.getText());
		contacto.setWeb(jTextFieldWebBlog.getText());
		
		if(!modificacion){
			contacto.setMotivoEstado("alta de usuario");
		}else{
			contacto.setMotivoEstado("Modificación de usuario");
			//MUY IMPORTANTE EL ID DEL CONTACTO SE HA DE DE RELLENAR DEL QUE PASAMOS PARA CONSULTAR. Digamos que es como si fuera un campo hidden
			contacto.setIdContacto(dtoCentroDocenteModificar.getCentroDocente().getIdContacto());
		}
		dtoContacto.setContacto(contacto);
		
		/*****************************************
		 * DATOS ESPECÍFICOS DEL TELEFONO
		 *****************************************/
		DTOTelefono dtoTelefono = new DTOTelefono();
		Telefono telefono = new Telefono();
		if(!Utils.valorisNull(jTextFieldExtension.getText())) telefono.setExtension(Integer.parseInt(jTextFieldExtension.getText()));
		if(!Utils.valorisNull(jTextFieldPrefijo.getText())) telefono.setPrefijoPais(jTextFieldPrefijo.getText());
		
		telefono.setTelefono(jTextFieldTelefono.getText());
		telefono.setIdTipoTelefono(Integer.parseInt(((MostrarCombo) jComboBoxTipo.getSelectedItem()).getID().toString()));
		if(modificacion){
			//MUY IMPORTANTE EL ID DEL TELEFONO Y DEL CONTACTO SE HA DE DE RELLENAR DEL QUE PASAMOS PARA CONSULTAR. Digamos que es como si fuera un campo hidden
			telefono.setIdContacto(dtoCentroDocenteModificar.getCentroDocente().getIdContacto());
			if(dtoCentroDocenteModificar.getDtoContacto().getDtoTelefono() != null) telefono.setIdTelefono(dtoCentroDocenteModificar.getDtoContacto().getDtoTelefono().getTelefono().getIdTelefono());
		}
		dtoTelefono.setTelefono(telefono);
		dtoContacto.setDtoTelefono(dtoTelefono);
		
		/*****************************************
		 * DATOS DEL CENTRO DOCENTE
		 *****************************************/
		DTOCentroDocente dtoCentroDocente = new DTOCentroDocente();
		CentroDocente centroDocente = new CentroDocente();
		centroDocente.setNombre(jTextFieldNombre.getText());
		centroDocente.setIdUniversidad(Integer.parseInt(((MostrarCombo) jComboBoxUniver.getSelectedItem()).getID().toString()));
		if(modificacion){
			//MUY IMPORTANTE EL ID DEL CONTACTO SE HA DE DE RELLENAR DEL QUE PASAMOS PARA CONSULTAR. Digamos que es como si fuera un campo hidden
			contacto.setIdContacto(dtoCentroDocenteModificar.getCentroDocente().getIdContacto());
			centroDocente.setIdCentro(dtoCentroDocenteModificar.getCentroDocente().getIdCentro());
			centroDocente.setMotivoEstado("Modificación de centro docente");
		}else{
			centroDocente.setMotivoEstado("Alta de centro docente");
		}
		dtoCentroDocente.setCentroDocente(centroDocente);
		dtoCentroDocente.setDtoContacto(dtoContacto);
		
		
		return dtoCentroDocente;
	}
	
	/**
	 * Limpia el formulario
	 */
	private void limpiaFormulario(){
		ClearForm.clearForm(jPanel2);
	}
	
	
	/**
	 * Carga los valores del Usuario a consultar
	 */
	private void cargaCentroDocente(){
		try {
			
			dtoCentroDocenteModificar = remote.getCentroDocente(dtoCentroDocenteModificar);
			
			if(dtoCentroDocenteModificar != null){
				
				
				/*****************************************
				 * CARGAMOS LA UNIVERSIDAD
				 *****************************************/
				if(dtoCentroDocenteModificar.getDtoUniversidad() != null){
					jComboBoxpais.setSelectedItem(new MostrarCombo(dtoCentroDocenteModificar.getDtoUniversidad().getUniversidad().getIdUniversidad()
							,dtoCentroDocenteModificar.getDtoUniversidad().getUniversidad().getNombre()));
				}
				
				/*****************************************
				 * CARGAMOS LOS VALORES DEL CENTRO DOCENTE
				 *****************************************/
				if(dtoCentroDocenteModificar.getCentroDocente() != null){
					jTextFieldNombre.setText(dtoCentroDocenteModificar.getCentroDocente().getNombre());
				}
				
				/*****************************************
				 * CARGAMOS LOS VALORES DEL CONTACTO
				 *****************************************/
				if(dtoCentroDocenteModificar.getDtoContacto() != null){
					
					if(dtoCentroDocenteModificar.getDtoContacto().getContacto().getDomicilio() != null){
						jTextFieldDirec.setText(dtoCentroDocenteModificar.getDtoContacto().getContacto().getDomicilio());
					}
					
					if(dtoCentroDocenteModificar.getDtoContacto().getContacto().getWeb() != null){
						jTextFieldWebBlog.setText(dtoCentroDocenteModificar.getDtoContacto().getContacto().getWeb());
					}
					
					if(dtoCentroDocenteModificar.getDtoContacto().getContacto().getEmail() != null){
						jTextFieldEmail.setText(dtoCentroDocenteModificar.getDtoContacto().getContacto().getEmail());
					}
					
					if(dtoCentroDocenteModificar.getDtoContacto().getContacto().getCp() != null){
						jTextFieldCP.setText(dtoCentroDocenteModificar.getDtoContacto().getContacto().getCp().toString());
					}
					
					if(dtoCentroDocenteModificar.getDtoContacto().getContacto().getLocalidad() != null){
						jTextFieldLocalidad.setText(dtoCentroDocenteModificar.getDtoContacto().getContacto().getLocalidad());
					}
					
					if(dtoCentroDocenteModificar.getDtoContacto().getDtoPais().getPais().getIdPais()!= null){
						jComboBoxpais.setSelectedItem(new MostrarCombo(dtoCentroDocenteModificar.getDtoContacto().getDtoPais().getPais().getIdPais(),
								dtoCentroDocenteModificar.getDtoContacto().getDtoPais().getPais().getNombrePais()));
					}
				}
				
				/*****************************************
				 * CARGAMOS LOS VALORES DEL TELEFONO
				 *****************************************/
				if(dtoCentroDocenteModificar.getDtoContacto().getDtoTelefono() != null){
					
					jComboBoxTipo.setSelectedItem(new MostrarCombo(dtoCentroDocenteModificar.getDtoContacto().getDtoTelefono().getDtoTipoTelefono().getTipoTelefono().getIdTipoTelefono(),
							dtoCentroDocenteModificar.getDtoContacto().getDtoTelefono().getDtoTipoTelefono().getTipoTelefono().getDescripcion()));
					
					if(dtoCentroDocenteModificar.getDtoContacto().getDtoTelefono().getTelefono().getExtension()!= null && dtoCentroDocenteModificar.getDtoContacto().getDtoTelefono().getTelefono().getExtension()!=-1){
						jTextFieldExtension.setText(dtoCentroDocenteModificar.getDtoContacto().getDtoTelefono().getTelefono().getExtension().toString());
					}
					
					if(dtoCentroDocenteModificar.getDtoContacto().getDtoTelefono().getTelefono().getTelefono()!= null){
						jTextFieldTelefono.setText(dtoCentroDocenteModificar.getDtoContacto().getDtoTelefono().getTelefono().getTelefono().toString());
					}
					
					if(dtoCentroDocenteModificar.getDtoContacto().getDtoTelefono().getTelefono().getPrefijoPais()!= null){
						jTextFieldPrefijo.setText(dtoCentroDocenteModificar.getDtoContacto().getDtoTelefono().getTelefono().getPrefijoPais());
					}
				}
				
			}
			
			
		} catch (RemoteException e) {
			try {
				throw new OperationErrorRMI(TDSLanguageUtils.getMessage("clientePEC4.altacentro.MSG3"));
			} catch (OperationErrorRMI e1) {
				e1.showDialogError();
			}
		} catch (OperationErrorBD e) {
			try {
				throw new OperationErrorBD(TDSLanguageUtils.getMessage("clientePEC4.altacentro.MSG4"));
			} catch (OperationErrorBD e1) {
				e1.showDialogError();
			}
		}
		
	}
	
}
