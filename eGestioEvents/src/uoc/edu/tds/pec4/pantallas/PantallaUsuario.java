package uoc.edu.tds.pec4.pantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import uoc.edu.tds.pec4.beans.Contacto;
import uoc.edu.tds.pec4.beans.DatosBancarios;
import uoc.edu.tds.pec4.beans.DocumentoIdentificacion;
import uoc.edu.tds.pec4.beans.Telefono;
import uoc.edu.tds.pec4.beans.Usuario;
import uoc.edu.tds.pec4.dtos.DTOAdministrador;
import uoc.edu.tds.pec4.dtos.DTOAsistente;
import uoc.edu.tds.pec4.dtos.DTOCentroDocente;
import uoc.edu.tds.pec4.dtos.DTOContacto;
import uoc.edu.tds.pec4.dtos.DTODatosBancarios;
import uoc.edu.tds.pec4.dtos.DTODocumentoIdentificacion;
import uoc.edu.tds.pec4.dtos.DTOPais;
import uoc.edu.tds.pec4.dtos.DTOPersonalSecretaria;
import uoc.edu.tds.pec4.dtos.DTOTelefono;
import uoc.edu.tds.pec4.dtos.DTOTipoDocumento;
import uoc.edu.tds.pec4.dtos.DTOTipoRol;
import uoc.edu.tds.pec4.dtos.DTOTipoTelefono;
import uoc.edu.tds.pec4.dtos.DTOUniversidad;
import uoc.edu.tds.pec4.dtos.DTOUsuario;
import uoc.edu.tds.pec4.excepciones.OperationErrorBD;
import uoc.edu.tds.pec4.excepciones.OperationErrorDatosFormulario;
import uoc.edu.tds.pec4.excepciones.OperationErrorRMI;
import uoc.edu.tds.pec4.gestores.FactoriaUsuario;
import uoc.edu.tds.pec4.gestores.GestorRMI;
import uoc.edu.tds.pec4.iface.RemoteInterface;
import uoc.edu.tds.pec4.utils.ClearForm;
import uoc.edu.tds.pec4.utils.Constantes;
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
public class PantallaUsuario extends javax.swing.JPanel implements Pantallas {
	
	private static final long serialVersionUID = 1L;
	private JPanel jPanel1;
	private JTextField jTextFieldCuenta;
	private JLabel jLabelCuenta;
	private JTextField jTextFieldDC;
	private JLabel jLabelDC;
	private JTextField jTextFieldSucursal;
	private JLabel jLabelSucursal;
	private JTextField jTextFieldBanco;
	private JLabel jLabelBanco;
	private JLabel jLabelDatosBanc;
	private JComboBox jComboBoxTipoRol;
	private JLabel jLabelTipoRol;
	private JLabel jLabelCenDocente;
	private JComboBox jComboBoxCentroDocente;
	private JComboBox jComboBoxUniver;
	private JLabel jLabelUniversidad;
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
	private JTextField jTextFieldFechaNac;
	private JLabel jLabelFechaNac;
	private JTextField jTextFieldWebBlog;
	private JLabel jLabelBlog;
	private JComboBox jComboBoxSexo;
	private JLabel jLabelSexo;
	private JTextField jTextFieldEmail;
	private JLabel jLabelEmail;
	private JComboBox jComboBoxpais;
	private JLabel jLabelPais;
	private JTextField jTextFieldCP;
	private JLabel jLabelCP;
	private JTextField jTextFieldLocalidad;
	private JLabel jLabelLocalidad;
	private JTextField jTextFieldDocIden;
	private JLabel jLabelDocIden;
	private JTextField jTextFieldDirec;
	private JLabel jLabelDirec;
	private JComboBox jComboBoxTipoDoc;
	private JLabel jLabelTipoDoc;
	private JTextField jTextFieldCon;
	private JLabel jLabelCont;
	private JLabel jLabelCodigo;
	private JTextField jTextFieldCodigo;
	private JLabel jLabelApe;
	private JTextField jTextFieldApe;
	private JLabel jLabelNombre;
	private JTextField jTextFieldNombre;
	private JPanel jPanel2;
	private JRadioButton jRadioButtonAsis;
	private JRadioButton jRadioButtonSecr;
	private JRadioButton jRadioButtonAdmin;
	private JLabel tipoperfil;
	private RemoteInterface remote;
	private ButtonGroup grupoBu;
	private Boolean bUserModificacion = false;
	private DTOUsuario dtoUsuarioaModificar;
	/*
	 * Constructor que recibe un idUsuario y lo calculamos
	 */
	public PantallaUsuario(GestorRMI gestorRMI,RemoteInterface remote1, DTOUsuario dtoUsuarioaModificar) {
		super();
		remote = remote1;
		try {
			remote.testConexion();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		System.out.print("Para quitar el warning que sale si no se utiliza es provisional " + remote.toString());
		initGUI();
		this.dtoUsuarioaModificar = dtoUsuarioaModificar;
		if(dtoUsuarioaModificar != null){
			bUserModificacion = true;//Significa que vamos a realizar la modificaci�n
			cargaUsuario();
			if(Constantes.ADMINISTRADOR == dtoUsuarioaModificar.getUsuario().getTipoUsuario()) jRadioButtonAdmin.isSelected();
			if(Constantes.SECRETARIA == dtoUsuarioaModificar.getUsuario().getTipoUsuario()) jRadioButtonSecr.isSelected();
			if(Constantes.ASISTENTE == dtoUsuarioaModificar.getUsuario().getTipoUsuario()) jRadioButtonAsis.isSelected();
			
			//Desabilitamos que se pueda cambiar de tipo de Usuario
			Enumeration<AbstractButton> ite = grupoBu.getElements();
			while(ite.hasMoreElements()){
				JRadioButton jButton = (JRadioButton) ite.nextElement();
				jButton.setEnabled(false);
			}
			jTextFieldCon.setEnabled(false);
			jButtonCancelar.setVisible(false);
			
		}
		
	}
	
	public PantallaUsuario(GestorRMI gestorRMI,RemoteInterface remote1) {
		this(gestorRMI,remote1,null);
	}
	
	private void initGUI() {
		try {
			this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(null, bUserModificacion.booleanValue()?"Modificaci�n de usuarios":"Alta de usuarios", 0, 0, new Font("Dialog", 1, 12), new Color(51, 51, 51)), null), null));
			this.setPreferredSize(new java.awt.Dimension(784, 634));
			{
				jPanel1 = new JPanel();
				this.add(jPanel1);
				{
					tipoperfil = new JLabel();
					jPanel1.add(tipoperfil);
					tipoperfil.setText("Tipo Perfil");
				}
				{
					jRadioButtonAdmin = new JRadioButton();
					jPanel1.add(jRadioButtonAdmin);
					jRadioButtonAdmin.setText("Administrador");
					jRadioButtonAdmin.setBounds(103, 7, 86, 18);
				}
				{
					jRadioButtonSecr = new JRadioButton();
					jPanel1.add(jRadioButtonSecr);
					jRadioButtonSecr.setText("Personal Secretaria");
					jRadioButtonSecr.setBounds(61, 5, 86, 18);
				}
				{
					jRadioButtonAsis = new JRadioButton();
					jPanel1.add(jRadioButtonAsis);
					jRadioButtonAsis.setText("Asistente");
				}
			}
			{
				jPanel2 = new JPanel();
				this.add(jPanel2);
				GridBagLayout jPanel2Layout = new GridBagLayout();
				jPanel2Layout.columnWidths = new int[] {142, 222, 108, 7};
				jPanel2Layout.rowHeights = new int[] {28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 16, 28, 28, 18, 19};
				jPanel2Layout.columnWeights = new double[] {0.0, 0.0, 0.0, 0.1};
				jPanel2Layout.rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
				jPanel2.setPreferredSize(new java.awt.Dimension(724, 500));
				jPanel2.setLayout(jPanel2Layout);
				{
					jTextFieldNombre = new JTextField();
					jPanel2.add(jTextFieldNombre, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.SOUTHWEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 15), 0, 0));
					jTextFieldNombre.setPreferredSize(new java.awt.Dimension(200, 21));
				}
				{
					jLabelNombre = new JLabel();
					jPanel2.add(jLabelNombre, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(4, 0, 0, 0), 0, 0));
					jLabelNombre.setText("Nombre");
					jLabelNombre.setLayout(null);
				}
				{
					jTextFieldApe = new JTextField();
					jPanel2.add(jTextFieldApe, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(2, 8, 0, 0), 0, 0));
					jTextFieldApe.setPreferredSize(new java.awt.Dimension(200, 21));
				}
				{
					jLabelApe = new JLabel();
					jPanel2.add(jLabelApe, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(6, 0, 0, 0), 0, 0));
					jLabelApe.setText("Apellidos");
					jLabelApe.setLayout(null);
				}
				{
					jTextFieldCodigo = new JTextField();
					jPanel2.add(jTextFieldCodigo, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					jTextFieldCodigo.setPreferredSize(new java.awt.Dimension(200, 21));
				}
				{
					jLabelCodigo = new JLabel();
					jPanel2.add(jLabelCodigo, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
					jLabelCodigo.setText("Codigo Usuario");
					jLabelCodigo.setLayout(null);
				}
				{
					jLabelCont = new JLabel();
					jPanel2.add(jLabelCont, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(3, 0, 0, 0), 0, 0));
					jLabelCont.setText("Contrase�a");
					jLabelCont.setLayout(null);
				}
				{
					jTextFieldCon = new JPasswordField();
					jPanel2.add(jTextFieldCon, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					jTextFieldCon.setPreferredSize(new java.awt.Dimension(200, 21));
					jTextFieldCon.setDocument(new JTextFieldLimit(8));
				}
				{
					jLabelTipoDoc = new JLabel();
					jPanel2.add(jLabelTipoDoc, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabelTipoDoc.setText("Tipo de documento");
					jLabelTipoDoc.setLayout(null);
				}
				{
					
					jComboBoxTipoDoc = new JComboBox();
					jPanel2.add(jComboBoxTipoDoc, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					jComboBoxTipoDoc.setPreferredSize(new java.awt.Dimension(200, 26));
					
				}
				{
					jLabelDirec = new JLabel();
					jPanel2.add(jLabelDirec, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabelDirec.setText("Direccion");
					jLabelDirec.setLayout(null);
				}
				{
					jTextFieldDirec = new JTextField();
					jPanel2.add(jTextFieldDirec, new GridBagConstraints(1, 3, 2, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					jTextFieldDirec.setPreferredSize(new java.awt.Dimension(200, 21));
				}
				{
					jLabelDocIden = new JLabel();
					jPanel2.add(jLabelDocIden, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabelDocIden.setText("Documento identificaci�n");
					jLabelDocIden.setLayout(null);
				}
				{
					jTextFieldDocIden = new JTextField();
					jPanel2.add(jTextFieldDocIden, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					jTextFieldDocIden.setPreferredSize(new java.awt.Dimension(200, 21));
				}
				{
					jLabelLocalidad = new JLabel();
					jPanel2.add(jLabelLocalidad, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabelLocalidad.setText("Localidad");
					jLabelLocalidad.setLayout(null);
				}
				{
					jTextFieldLocalidad = new JTextField();
					jPanel2.add(jTextFieldLocalidad, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					jTextFieldLocalidad.setPreferredSize(new java.awt.Dimension(200, 21));
				}
				{
					jLabelCP = new JLabel();
					jPanel2.add(jLabelCP, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabelCP.setText("CP");
					jLabelCP.setLayout(null);
					
				}
				{
					jTextFieldCP = new JTextField();
					jPanel2.add(jTextFieldCP, new GridBagConstraints(3, 4, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					jTextFieldCP.setPreferredSize(new java.awt.Dimension(200, 21));
					jTextFieldCP.setDocument(new JTextFieldLimit(5));
				}
				{
					jLabelPais = new JLabel();
					jPanel2.add(jLabelPais, new GridBagConstraints(2, 5, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabelPais.setText("Pais");
					jLabelPais.setLayout(null);
				}
				{
					
					jComboBoxpais = new JComboBox();
					jPanel2.add(jComboBoxpais, new GridBagConstraints(3, 5, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					jComboBoxpais.setPreferredSize(new java.awt.Dimension(200, 26));
				}
				{
					jLabelEmail = new JLabel();
					jPanel2.add(jLabelEmail, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabelEmail.setText("Email");
					jLabelEmail.setLayout(null);
				}
				{
					jTextFieldEmail = new JTextField();
					jPanel2.add(jTextFieldEmail, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					jTextFieldEmail.setPreferredSize(new java.awt.Dimension(200, 21));
				}
				{
					jLabelSexo = new JLabel();
					jPanel2.add(jLabelSexo, new GridBagConstraints(2, 6, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabelSexo.setText("Sexo");
					jLabelSexo.setLayout(null);
				}
				{
					jComboBoxSexo = new JComboBox();
					jPanel2.add(jComboBoxSexo, new GridBagConstraints(3, 6, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					
					jComboBoxSexo.setPreferredSize(new java.awt.Dimension(200, 26));
				}
				{
					jLabelBlog = new JLabel();
					jPanel2.add(jLabelBlog, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabelBlog.setText("P�gina Web o Blog");
					jLabelBlog.setLayout(null);
				}
				{
					jTextFieldWebBlog = new JTextField();
					jPanel2.add(jTextFieldWebBlog, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					jTextFieldWebBlog.setPreferredSize(new java.awt.Dimension(200, 21));
				}
				{
					jLabelFechaNac = new JLabel();
					jPanel2.add(jLabelFechaNac, new GridBagConstraints(2, 7, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabelFechaNac.setText("Fecha Nacimiento");
					jLabelFechaNac.setLayout(null);
				}
				{
					jTextFieldFechaNac = new JTextField();
					jPanel2.add(jTextFieldFechaNac, new GridBagConstraints(3, 7, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					jTextFieldFechaNac.setPreferredSize(new java.awt.Dimension(200, 21));
					jTextFieldFechaNac.setDocument(new JTextFieldLimit(10));
				}
				{
					jLabelTelf = new JLabel();
					jPanel2.add(jLabelTelf, new GridBagConstraints(0, 9, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabelTelf.setText("Tel�fono");
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
					jLabelExtension.setText("Extension");
				}
				{
					jTextFieldExtension = new JTextField();
					jPanel2.add(jTextFieldExtension, new GridBagConstraints(1, 10, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					jTextFieldExtension.setPreferredSize(new java.awt.Dimension(200, 21));
				}
				{
					jLabelTipo = new JLabel();
					jPanel2.add(jLabelTipo, new GridBagConstraints(0, 11, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabelTipo.setText("Tipo");
				}
				{
					
					jComboBoxTipo = new JComboBox();
					jPanel2.add(jComboBoxTipo, new GridBagConstraints(1, 11, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					
					jComboBoxTipo.setPreferredSize(new java.awt.Dimension(200, 26));
				}
				{
					jLabelUniversidad = new JLabel();
					jPanel2.add(jLabelUniversidad, new GridBagConstraints(0, 13, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabelUniversidad.setText("Universidad");
				}
				{
					jComboBoxUniver = new JComboBox();
					jPanel2.add(jComboBoxUniver, new GridBagConstraints(1, 13, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					jComboBoxUniver.setPreferredSize(new java.awt.Dimension(200, 25));
				}
				{
					
					jComboBoxCentroDocente = new JComboBox();
					jPanel2.add(jComboBoxCentroDocente, new GridBagConstraints(3, 13, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					jComboBoxCentroDocente.setPreferredSize(new java.awt.Dimension(200, 26));
					
				}
				{
					jLabelCenDocente = new JLabel();
					jPanel2.add(jLabelCenDocente, new GridBagConstraints(2, 13, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabelCenDocente.setText("Centro Docente");
				}
				{
					jLabelTipoRol = new JLabel();
					jPanel2.add(jLabelTipoRol, new GridBagConstraints(0, 14, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabelTipoRol.setText("Tipo Rol");
				}
				{
					
					
					jComboBoxTipoRol = new JComboBox();
					jPanel2.add(jComboBoxTipoRol, new GridBagConstraints(1, 14, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					jComboBoxTipoRol.setPreferredSize(new java.awt.Dimension(200, 26));
				}
				{
					jLabelDatosBanc = new JLabel();
					jPanel2.add(jLabelDatosBanc, new GridBagConstraints(0, 16, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabelDatosBanc.setText("Datos Bancarios");
				}
				{
					jLabelBanco = new JLabel();
					jPanel2.add(jLabelBanco, new GridBagConstraints(1, 17, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabelBanco.setText("Banco");
				}
				{
					jTextFieldBanco = new JTextField();
					jPanel2.add(jTextFieldBanco, new GridBagConstraints(1, 17, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 36, 0, 0), 0, 0));
					jTextFieldBanco.setPreferredSize(new java.awt.Dimension(60, 21));
					jTextFieldBanco.setDocument(new JTextFieldLimit(4));
				}
				{
					jLabelSucursal = new JLabel();
					jPanel2.add(jLabelSucursal, new GridBagConstraints(1, 17, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 62), 0, 0));
					jLabelSucursal.setText("Sucursal");
				}
				{
					jTextFieldSucursal = new JTextField();
					jPanel2.add(jTextFieldSucursal, new GridBagConstraints(1, 17, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 10), 0, 0));
					jTextFieldSucursal.setPreferredSize(new java.awt.Dimension(50, 21));
					jTextFieldSucursal.setDocument(new JTextFieldLimit(4));
				}
				{
					jLabelDC = new JLabel();
					jPanel2.add(jLabelDC, new GridBagConstraints(2, 17, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 9, 0, 0), 0, 0));
					jLabelDC.setText("DC");
				}
				{
					jTextFieldDC = new JTextField();
					jPanel2.add(jTextFieldDC, new GridBagConstraints(2, 17, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 29, 0, 0), 0, 0));
					jTextFieldDC.setPreferredSize(new java.awt.Dimension(26, 21));
					jTextFieldDC.setDocument(new JTextFieldLimit(2));
				}
				{
					jLabelCuenta = new JLabel();
					jPanel2.add(jLabelCuenta, new GridBagConstraints(2, 17, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 9), 0, 0));
					jLabelCuenta.setText("Cuenta");
				}
				{
					jTextFieldCuenta = new JTextField();
					jPanel2.add(jTextFieldCuenta, new GridBagConstraints(3, 17, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jTextFieldCuenta.setPreferredSize(new java.awt.Dimension(120, 21));
					jTextFieldCuenta.setDocument(new JTextFieldLimit(12));
				}
				{
					jLabelPrefijo = new JLabel();
					jPanel2.add(jLabelPrefijo, new GridBagConstraints(2, 9, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabelPrefijo.setText("Prefijo");
				}
				{
					jTextFieldPrefijo = new JTextField();
					jPanel2.add(jTextFieldPrefijo, new GridBagConstraints(3, 9, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
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
					jButtonAcceptar.setText("Acceptar");
					jButtonAcceptar.setLayout(null);
					jButtonAcceptar.setBounds(277, -16, 64, 21);
					
					jButtonAcceptar.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								try {
									String codigo = "";
									validaFormulario(bUserModificacion.booleanValue());
									if(bUserModificacion.booleanValue()){
										remote.modificaUsuario(altaModificaUsuario(bUserModificacion.booleanValue()));
										Utils.mostraMensajeInformacion(jPanel2, "Registro modificado correctamente", "Modificaci�n usuario");
									}else{
										codigo = remote.insertaUsuario(altaModificaUsuario(bUserModificacion.booleanValue()));
										Utils.mostraMensajeInformacion(jPanel2, "Registro insertado correctamente.\nSu identificador de usuario es " + codigo, "Alta usuario");
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
					jButtonCancelar.setText("Limpiar");
					jButtonCancelar.setLayout(null);
					
					jButtonCancelar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							limpiaFormulario();
						}
			    	});
				}
			}

			//Group the radio buttons.
			grupoBu = new ButtonGroup();
			grupoBu.add(jRadioButtonAdmin);
			grupoBu.add(jRadioButtonSecr);
			grupoBu.add(jRadioButtonAsis);
			
			jRadioButtonAdmin.addItemListener(new ItemListener(){
				public void itemStateChanged(ItemEvent e) {
					try {
						if(e.getStateChange() == ItemEvent.SELECTED) {
							showHideDatosUsuario(false);
							showHideDatosUni(false);
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
			
			jRadioButtonSecr.addItemListener(new ItemListener(){
				public void itemStateChanged(ItemEvent e) {
					try {
						if(e.getStateChange() == ItemEvent.SELECTED) {
							showHideDatosUsuario(false);
							showHideDatosUni(true);
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
			
			jRadioButtonAsis.addItemListener(new ItemListener(){
				public void itemStateChanged(ItemEvent e) {
					try {
						if(e.getStateChange() == ItemEvent.SELECTED) {
							showHideDatosUsuario(true);
							showHideDatosUni(true);
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
			
			//Cargamos combos
			cargaCombos();
			
			jRadioButtonAdmin.setSelected(true);
			
			//Ocultamos la informacion
			showHideDatosUsuario(false);
			showHideDatosUni(false);
			
			jTextFieldCodigo.setEnabled(false);
			
		} catch (Exception e) {
			try{
				throw new OperationErrorDatosFormulario(e.getMessage());
			}catch(OperationErrorDatosFormulario ex){
				ex.showDialogError(jPanel2);
			}
		}
	}
	
	/*
	 * Oculta o pon visible dependiendo del tipo de Usuario seleccionado
	 */
	private void showHideDatosUsuario(Boolean bValor){
		jComboBoxTipoRol.setVisible(bValor);
		jTextFieldSucursal.setVisible(bValor);
		jTextFieldDC.setVisible(bValor);
		jTextFieldBanco.setVisible(bValor);
		jTextFieldCuenta.setVisible(bValor);
		jLabelCuenta.setVisible(bValor);
		jLabelDC.setVisible(bValor);
		jLabelSucursal.setVisible(bValor);
		jLabelBanco.setVisible(bValor);
		jLabelTipoRol.setVisible(bValor);
		jComboBoxTipoRol.setVisible(bValor);
		jLabelDatosBanc.setVisible(bValor);
	}
	
	/*
	 * Oculta o pon visible dependiendo del tipo de Usuario seleccionado
	 */
	private void showHideDatosUni(Boolean bValor){
		jLabelCenDocente.setVisible(bValor);
		jComboBoxCentroDocente.setVisible(bValor);
		jComboBoxUniver.setVisible(bValor);
		jLabelUniversidad.setVisible(bValor);
	}
	
	
	/**
	 * Rellena los centros docentes dependiendo del pa�s seleccionado
	 * @param obj
	 */
	private void rellenaCentrosDocentes(Object obj){
		try {
			List<DTOCentroDocente> lstDtoCentroDoc = remote.rellenaCentrosByIdUniversidad((Integer)obj);
			List<MostrarCombo> lstComboCentroDoc = new ArrayList<MostrarCombo>();
			if(lstDtoCentroDoc != null){
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
	private void validaFormulario(boolean modificacion) throws OperationErrorDatosFormulario{
		try{
			
			//Campos a validar gen�ricamente
			if(!jRadioButtonAdmin.isSelected() && !jRadioButtonSecr.isSelected() && !jRadioButtonAsis.isSelected()) throw new Exception("Ha de seleccionar un tipo de perfil");
			
			if(Utils.valorisNull(jTextFieldNombre.getText())) throw new Exception(Utils.MESSAGE_ERROR + " nombre" );
			if(Utils.valorisNull(jTextFieldApe.getText())) throw new Exception(Utils.MESSAGE_ERROR + " apellidos" );
			if(Utils.valorisNull(jComboBoxTipoDoc.getSelectedItem())) throw new Exception(Utils.MESSAGE_ERROR + " tipo de documento" );
			if(Utils.valorisNull(jTextFieldDirec.getText())) throw new Exception(Utils.MESSAGE_ERROR + " direccion" );
			if(Utils.valorisNull(jComboBoxTipo.getSelectedItem())) throw new Exception(Utils.MESSAGE_ERROR + " tipo tel�fono" );
			if(Utils.valorisNull(jComboBoxpais.getSelectedItem())) throw new Exception(Utils.MESSAGE_ERROR + " pa�s" );
			if(Utils.valorisNull(jTextFieldTelefono.getText())) throw new Exception(Utils.MESSAGE_ERROR + " tel�fono" );
			if(Utils.valorisNull(jTextFieldFechaNac.getText())) throw new Exception(Utils.MESSAGE_ERROR + " fecha nacimiento" );
			if(Utils.valorisNull(jComboBoxSexo.getSelectedItem())) throw new Exception(Utils.MESSAGE_ERROR + " sexo" );
			if(Utils.valorisNull(jTextFieldCP.getText())) throw new Exception(Utils.MESSAGE_ERROR + " c�digo postal" );
			if(Utils.valorisNull(jTextFieldLocalidad.getText())) throw new Exception(Utils.MESSAGE_ERROR + " localidad" );
			if(Utils.valorisNull(jTextFieldDocIden.getText())) throw new Exception(Utils.MESSAGE_ERROR + " documento identificaci�n" );
			if(Utils.valorisNull(jTextFieldDirec.getText())) throw new Exception(Utils.MESSAGE_ERROR + " direcci�n" );
			
			//Desde la pantalla de modificaci�n no se puede cambiar la contrase�a
			if(!modificacion){
				if(Utils.valorisNull(jTextFieldCon.getText())) throw new Exception(Utils.MESSAGE_ERROR + " contrase�a" );
			}
			
			if(!Utils.parseaFecha(jTextFieldFechaNac.getText())) throw new Exception(Utils.MESSAGE_ERROR + " fecha nacimiento" + Utils.MESSAGE_FECHA );
			//if(Utils.valorisNull(jTextFieldExtension.getText())) throw new Exception(Utils.MESSAGE_ERROR + " extensi�n tel�fono" );
			//if(Utils.valorisNull(jTextFieldWebBlog.getText())) throw new Exception(Utils.MESSAGE_ERROR + " p�gina web o blog" );
			//if(Utils.valorisNull(jTextFieldEmail.getText())) throw new Exception(Utils.MESSAGE_ERROR + " email" );
			
			//Campos a validar exclusivamente para un ASISTENTE o SECRETARIA
			if(jRadioButtonSecr.isSelected() || jRadioButtonAsis.isSelected()){
				if(Utils.valorisNull(jComboBoxUniver.getSelectedItem())) throw new Exception(Utils.MESSAGE_ERROR + " universidad" );
				if(Utils.valorisNull(jComboBoxCentroDocente.getSelectedItem())) throw new Exception(Utils.MESSAGE_ERROR + " centro docente" );
			}
			
			//Campos a validar exclusivamente para un ASISTENTE
			if(jRadioButtonAsis.isSelected()){
				//Campos a validar si es un DTOAsistente
				if(Utils.valorisNull(jTextFieldBanco.getText())) throw new Exception(Utils.MESSAGE_ERROR + " banco" );
				if(Utils.valorisNull(jComboBoxTipoRol.getSelectedItem())) throw new Exception(Utils.MESSAGE_ERROR + " tipo de rol" );
				if(Utils.valorisNull(jTextFieldSucursal.getText())) throw new Exception(Utils.MESSAGE_ERROR + " sucursal" );
				if(Utils.valorisNull(jTextFieldDC.getText())) throw new Exception(Utils.MESSAGE_ERROR + " DC" );
				if(Utils.valorisNull(jTextFieldCuenta.getText())) throw new Exception(Utils.MESSAGE_ERROR + " cuenta" );
				
				//Aqu� ya no vendr� ning�n campo null as� que realizamos m�s modificaciones
				if(!Utils.validaNumerico(jTextFieldCon.getText())) 	throw new Exception(Utils.MESSAGE_ERROR + " password " + Utils.MESSAGE_NUMERIC );
				if(!Utils.validaNumerico(jTextFieldCP.getText())) 	throw new Exception(Utils.MESSAGE_ERROR + " c�digo postal " + Utils.MESSAGE_NUMERIC );
				if(!Utils.validaNumerico(jTextFieldTelefono.getText())) throw new Exception(Utils.MESSAGE_ERROR + " tel�fono" + Utils.MESSAGE_NUMERIC );
				if(!Utils.valorisNull(jTextFieldEmail.getText()) && !Utils.validaNumerico(jTextFieldExtension.getText())) throw new Exception(Utils.MESSAGE_ERROR + " extensi�n tel�fono" + Utils.MESSAGE_NUMERIC );
				if(!Utils.validaNumerico(jTextFieldSucursal.getText())) throw new Exception(Utils.MESSAGE_ERROR + " sucursal" + Utils.MESSAGE_NUMERIC );
				if(!Utils.validaNumerico(jTextFieldDC.getText())) throw new Exception(Utils.MESSAGE_ERROR + " DC" + Utils.MESSAGE_NUMERIC );
				if(!Utils.validaNumerico(jTextFieldBanco.getText())) throw new Exception(Utils.MESSAGE_ERROR + " banco" + Utils.MESSAGE_NUMERIC );
				if(!Utils.validaNumerico(jTextFieldCuenta.getText())) throw new Exception(Utils.MESSAGE_ERROR + " cuenta" + Utils.MESSAGE_NUMERIC );
			}
			
			EmailValidator emailValidator = new EmailValidator();
			if(!emailValidator.validate(jTextFieldEmail.getText())) throw new Exception (Utils.MESSAGE_EMAIL); 
			
		}catch(Exception e){
			throw new OperationErrorDatosFormulario(e.getMessage());
		}
			
	}
	
	/**
	 * M�todo para cargar los combos de la pantalla Usuario
	 * @throws OperationErrorDatosFormulario
	 */
	private void cargaCombos() throws OperationErrorDatosFormulario{
		try{
			
			
			if(!jRadioButtonAdmin.isSelected()){
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
				
				
				//Empezamos seleccionando el primer objeto cargado
				if(jComboBoxUniver.getItemCount() > 0) rellenaCentrosDocentes(((MostrarCombo)jComboBoxUniver.getSelectedItem()).getID());
			}
			
			jComboBoxUniver.addItemListener(new ItemListener(){
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
			
			
			//Cargamos tipo de rol
			List<DTOTipoRol> lstDtotipoRol = remote.getTiposRol();
			List<MostrarCombo> lstComboTipoRol = new ArrayList<MostrarCombo>();
			if(lstDtotipoRol != null){
				for(DTOTipoRol dtoTipoRolRec : lstDtotipoRol){
					lstComboTipoRol.add(new MostrarCombo(dtoTipoRolRec.getTipoRol().getIdRol(),
							dtoTipoRolRec.getTipoRol().getDescripcion()));
				}
			}
			
			ComboBoxModel jComboBoxTipoRolModel = new DefaultComboBoxModel(lstComboTipoRol.toArray());
			jComboBoxTipoRol.setModel(jComboBoxTipoRolModel);
			
			//Recuperamos los diferentes tipos de documentos
			List<DTOTipoDocumento> lstdtoTipoDoc = remote.getTiposDocumento();
			List<MostrarCombo> lstComoTipoDoc = new ArrayList<MostrarCombo>();
			if(lstdtoTipoDoc!=null){
				for(DTOTipoDocumento dtoTipoDocRes : lstdtoTipoDoc){
					lstComoTipoDoc.add(new MostrarCombo(dtoTipoDocRes.getTipoDocumento().getIdTipoDocumento(),
							dtoTipoDocRes.getTipoDocumento().getDescripcionDocumento()));
				}
			}
			
			jComboBoxTipoDoc.setModel(new DefaultComboBoxModel(lstComoTipoDoc.toArray()));
			
			//Cargamos la lista de pa�ses
			List<DTOPais> lstDtoPaises = remote.getPaises();
			List<MostrarCombo> lstComboPais = new ArrayList<MostrarCombo>();
			if(lstDtoPaises!=null){
				for(DTOPais dtoPaisRec : lstDtoPaises){
					lstComboPais.add(new MostrarCombo(dtoPaisRec.getPais().getIdPais(),dtoPaisRec.getPais().getNombrePais()));
				}
			}
			ComboBoxModel jComboBoxpaisModel =  new DefaultComboBoxModel(lstComboPais.toArray());
			jComboBoxpais.setModel(jComboBoxpaisModel);
			
			List<MostrarCombo> lstComboBox = new ArrayList<MostrarCombo>();
			lstComboBox.add(new MostrarCombo("M","Masculino"));
			lstComboBox.add(new MostrarCombo("F","Femenino"));
			ComboBoxModel jComboBoxSexoModel =  new DefaultComboBoxModel(lstComboBox.toArray());
			jComboBoxSexo.setModel(jComboBoxSexoModel);
			
			//Cargamos los diferentes tipos de tel�fono
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
			throw new OperationErrorDatosFormulario("Error al cargar las listas seleccionables");
		}
		
	}
	
	/**
	 * M�todo donde seteamos el usuario que se va a insertar o eliminar
	 * @param modificacion
	 * @return
	 * @throws OperationErrorDatosFormulario
	 */
	private DTOUsuario altaModificaUsuario(boolean modificacion) throws OperationErrorDatosFormulario{
		
		/*****************************************
		 * DATOS ESPEC�FICOS DEL CONRATO
		 *****************************************/
		DTOContacto dtoContacto = new DTOContacto();
		Contacto contacto = new Contacto();
		contacto.setDomicilio(jTextFieldDirec.getText());
		contacto.setCp(Integer.parseInt(jTextFieldCP.getText()));
		contacto.setLocalidad(jTextFieldLocalidad.getText());
		contacto.setProvincia(null);
		contacto.setIdPais(Integer.parseInt(((MostrarCombo) jComboBoxpais.getSelectedItem()).getID().toString()));
		contacto.setEmail(jTextFieldEmail.getText());
		contacto.setWeb(jTextFieldWebBlog.getText());
		if(!modificacion){
			contacto.setEstado(1);
			contacto.setFechaEstado(new java.sql.Date(System.currentTimeMillis()));
			contacto.setMotivoEstado("alta de usuario");
		}else{
			//MUY IMPORTANTE EL ID DEL CONTACTO SE HA DE DE RELLENAR DEL QUE PASAMOS PARA CONSULTAR. Digamos que es como si fuera un campo hidden
			contacto.setIdContacto(dtoUsuarioaModificar.getUsuario().getIdContacto());
		}
		dtoContacto.setContacto(contacto);
		
		
		/*****************************************
		 * DATOS ESPEC�FICOS DEL USUARIO
		 *****************************************/
		//Datos espec�ficos del usuario
		Usuario usuario = new Usuario();
		usuario.setNombre(jTextFieldNombre.getText());
		usuario.setApellidos(jTextFieldApe.getText());
		usuario.setSexo(((MostrarCombo) jComboBoxSexo.getSelectedItem()).getID().toString());
		
		try {
			usuario.setFechaNacimiento(Utils.transformFecha(jTextFieldFechaNac.getText()));
		} catch (OperationErrorDatosFormulario e) {
			throw e;
		}
		
		if(!modificacion){
			usuario.setFechaContrasena(new java.sql.Date(System.currentTimeMillis()));
			usuario.setContrasena(jTextFieldCon.getText());
			usuario.setCambiarContrasena(false);
			usuario.setFechaEstado(new java.sql.Date(System.currentTimeMillis()));
			usuario.setMotivoEstado("Alta de usuario");
			usuario.setCodigo(generaCodigo(usuario));
		}else{
			//MUY IMPORTANTE EL ID DEL USUARIO SE HA DE DE RELLENAR DEL QUE PASAMOS PARA CONSULTAR. Digamos que es como si fuera un campo hidden
			usuario.setIdContacto(dtoUsuarioaModificar.getUsuario().getIdContacto());
			usuario.setCodigo(dtoUsuarioaModificar.getUsuario().getCodigo());
		}
		
		usuario.setIdDocumentoIdentificacion(Integer.parseInt(((MostrarCombo) jComboBoxTipoDoc.getSelectedItem()).getID().toString()));
		
		//Tipo de usuario
		if(jRadioButtonAdmin.isSelected()) usuario.setTipoUsuario(Constantes.ADMINISTRADOR);
		if(jRadioButtonSecr.isSelected()) usuario.setTipoUsuario(Constantes.SECRETARIA);
		if(jRadioButtonAsis.isSelected()) usuario.setTipoUsuario(Constantes.ASISTENTE);
		
		if(Constantes.ASISTENTE == usuario.getTipoUsuario() || usuario.getTipoUsuario()==Constantes.SECRETARIA){
			usuario.setIdCentro(Integer.parseInt(((MostrarCombo) jComboBoxCentroDocente.getSelectedItem()).getID().toString()));
		}
		
		/*****************************************
		 * DATOS ESPEC�FICOS DEL DOCUMENTO IDENTIDAD
		 *****************************************/
		//Rellenamos el d documento de identidad
		DTODocumentoIdentificacion dtoDocumentoIden = new DTODocumentoIdentificacion();
		DocumentoIdentificacion docIden = new DocumentoIdentificacion();
		docIden.setIdDocumentoIdentificacion(usuario.getIdDocumentoIdentificacion());
		docIden.setIdTipoDocumento(Integer.parseInt(((MostrarCombo) jComboBoxTipoDoc.getSelectedItem()).getID().toString()));
		docIden.setIdPais(Integer.parseInt(((MostrarCombo) jComboBoxpais.getSelectedItem()).getID().toString()));
		docIden.setNumeroDocumento(jTextFieldDocIden.getText());
		if(modificacion){
			//MUY IMPORTANTE EL ID DEL CONTACTO SE HA DE DE RELLENAR DEL QUE PASAMOS PARA CONSULTAR. Digamos que es como si fuera un campo hidden
			docIden.setIdDocumentoIdentificacion(dtoUsuarioaModificar.getUsuario().getIdDocumentoIdentificacion());
		}
		dtoDocumentoIden.setDocumentoIdentificacion(docIden);
		
		
		/*****************************************
		 * DATOS ESPEC�FICOS DE DATOS BANCARIOS
		 *****************************************/
		DTODatosBancarios dtoDatosBancarios = null;
		if(Constantes.ASISTENTE == usuario.getTipoUsuario()){
			usuario.setIdRol(Integer.parseInt(((MostrarCombo) jComboBoxTipoRol.getSelectedItem()).getID().toString()));
			
			//Rellenamos datos bancarios
			dtoDatosBancarios = new DTODatosBancarios();
			DatosBancarios datosBancarios = new DatosBancarios();
			try {
				datosBancarios.setBanco(Integer.parseInt(jTextFieldCuenta.getText()));
				datosBancarios.setSucursal(Integer.parseInt(jTextFieldSucursal.getText()));
				datosBancarios.setDc(Integer.parseInt(jTextFieldDC.getText()));
				datosBancarios.setCc(Integer.parseInt(jTextFieldCuenta.getText()));
			} catch (NumberFormatException e) {
				throw e;
			}
			
			if(!modificacion){
				datosBancarios.setFechaEstado(new java.sql.Date(System.currentTimeMillis()));
				datosBancarios.setMotivoEstado("alta de usuario");
			}else{
				if(modificacion){
					//MUY IMPORTANTE EL ID DEL DATOS BANCARIOS SE HA DE DE RELLENAR DEL QUE PASAMOS PARA CONSULTAR. Digamos que es como si fuera un campo hidden
					datosBancarios.setIdDatosBancarios(dtoUsuarioaModificar.getUsuario().getIdDatosBancarios());
				}
			}
			dtoDatosBancarios.setDatosBancarios(datosBancarios);
		}
		
		/*****************************************
		 * DATOS ESPEC�FICOS DEL TELEFONO
		 *****************************************/
		DTOTelefono dtoTelefono = new DTOTelefono();
		Telefono telefono = new Telefono();
		if(!Utils.valorisNull(jTextFieldExtension.getText())) telefono.setExtension(Integer.parseInt(jTextFieldExtension.getText()));
		if(!Utils.valorisNull(jTextFieldPrefijo.getText())) telefono.setPrefijoPais(jTextFieldPrefijo.getText());
		
		telefono.setTelefono(jTextFieldTelefono.getText());
		telefono.setIdTipoTelefono(Integer.parseInt(((MostrarCombo) jComboBoxTipo.getSelectedItem()).getID().toString()));
		if(modificacion){
			//MUY IMPORTANTE EL ID DEL TELEFONO Y DEL CONTACTO SE HA DE DE RELLENAR DEL QUE PASAMOS PARA CONSULTAR. Digamos que es como si fuera un campo hidden
			telefono.setIdContacto(dtoUsuarioaModificar.getUsuario().getIdContacto());
			telefono.setIdTelefono(dtoUsuarioaModificar.getDtoContacto().getDtoTelefono().getTelefono().getIdTelefono());
		}
		dtoTelefono.setTelefono(telefono);
		dtoContacto.setDtoTelefono(dtoTelefono);
		
		
		/***********************************************
		 * RELLENAMOS EL DTOUSUARIO CON TODOS LOS VALORES
		 ***********************************************/
		DTOUsuario dtoUsuario = null;
		try {
			dtoUsuario = FactoriaUsuario.getUsuario(usuario.getTipoUsuario());
			if(dtoUsuario instanceof DTOAdministrador){
				dtoUsuario = new DTOAdministrador();
				rellenaInfoComun(dtoUsuario,usuario,dtoContacto,dtoDocumentoIden,dtoTelefono);
				dtoUsuario.getUsuario().setIdRol(-1); //No tienen roles
				dtoUsuario.getUsuario().setIdCentro(-1); //No tiene centro que asignar
				dtoUsuario.getUsuario().setIdDatosBancarios(-1); //No tiene datos bancarios
				return dtoUsuario;
			}else if(dtoUsuario instanceof DTOPersonalSecretaria){
				dtoUsuario = new DTOPersonalSecretaria();
				rellenaInfoComun(dtoUsuario,usuario,dtoContacto,dtoDocumentoIden,dtoTelefono);
				dtoUsuario.getUsuario().setIdRol(-1); //No tienen roles
				dtoUsuario.getUsuario().setIdDatosBancarios(-1); //No tiene datos bancarios
				return dtoUsuario;
			}else if(dtoUsuario instanceof DTOAsistente){
				dtoUsuario = new DTOAsistente();
				rellenaInfoComun(dtoUsuario,usuario,dtoContacto,dtoDocumentoIden,dtoTelefono);
				dtoUsuario.setDtoDatosBancarios(dtoDatosBancarios);
				return dtoUsuario;
			}	
			
		} catch (Exception e) {
			throw new OperationErrorDatosFormulario(e.getMessage());
		}
		throw new OperationErrorDatosFormulario("El tipo de usuario " + usuario.getTipoUsuario() + " no est� contemplado");
	}
	
	/**
	 * Rellena la informaci�n com�n de un usuario
	 * @param dtoUsuario
	 * @param usuario
	 * @param dtoContacto
	 * @param dtoDocumentoIden
	 * @param dtoTelefono
	 * @throws Exception
	 */
	private void rellenaInfoComun(DTOUsuario dtoUsuario,Usuario usuario, DTOContacto dtoContacto, 
			DTODocumentoIdentificacion dtoDocumentoIden,DTOTelefono dtoTelefono) throws Exception{
		try{
			dtoUsuario.setUsuario(usuario);
			dtoUsuario.setDtoContacto(dtoContacto);
			dtoContacto.setDtoTelefono(dtoTelefono);
			dtoUsuario.setDtoDocumentoIden(dtoDocumentoIden);
		}catch(Exception e){
			throw e;
		}
	}
	
	/**
	 * Limpia el formulario
	 */
	private void limpiaFormulario(){
		ClearForm.clearForm(jPanel1);
		ClearForm.clearForm(jPanel2);
		jRadioButtonAdmin.setSelected(true);
	}
	
	/**
	 * M�todo que genera el c�digo del usuario
	 * @param usuario
	 * @return
	 */
	private static String generaCodigo(Usuario usuario){
		StringBuffer codigoUsuario = new StringBuffer();
		Boolean codigoAcep = new Boolean(false);
		while(!codigoAcep){
			if(usuario.getNombre().length() > 2) codigoUsuario.append(usuario.getNombre().toUpperCase().trim().substring(0, 1));
			if(usuario.getApellidos().length() > 2) codigoUsuario.append(usuario.getApellidos().toUpperCase().trim().substring(0, 1));	
			
			for(int i=0;i<5;i++){
				codigoUsuario.append(((int) Math.floor(Math.random()*10)+1));
			}
			//Falta m�todo para comprobar codigo
			codigoAcep = true;
		}
		return codigoUsuario.toString();
	}
	
	/**
	 * Carga los valores del Usuario a consultar
	 */
	private void cargaUsuario(){
		try {
			
			dtoUsuarioaModificar = remote.getUsuario(dtoUsuarioaModificar);
			
			if(dtoUsuarioaModificar != null){
				
				/*****************************************
				 * CARGAMOS LOS VALORES DEL USUARIO
				 *****************************************/
				Usuario usuario = dtoUsuarioaModificar.getUsuario();
				
				//Valores del usuario
				if(usuario.getNombre()!= null) jTextFieldNombre.setText(usuario.getNombre());
				if(usuario.getApellidos()!= null) jTextFieldApe.setText(usuario.getApellidos());
				if(usuario.getCodigo()!= null) jTextFieldCodigo.setText(usuario.getCodigo());
				
				if(dtoUsuarioaModificar.getUsuario().getFechaNacimiento() !=  null){
					try {
						jTextFieldFechaNac.setText(Utils.convertFecha(dtoUsuarioaModificar.getUsuario().getFechaNacimiento().toString()));
					} catch (OperationErrorDatosFormulario e) {
						e.printStackTrace();
					}
				}
				
				if(dtoUsuarioaModificar.getDtoDocumentoIden() != null){
					jTextFieldDocIden.setText(dtoUsuarioaModificar.getDtoDocumentoIden().getDocumentoIdentificacion().getNumeroDocumento());
					jComboBoxTipoDoc.setSelectedItem(new MostrarCombo(dtoUsuarioaModificar.getDtoDocumentoIden().getDtoTipoDocumento().getTipoDocumento().getIdTipoDocumento()
							,dtoUsuarioaModificar.getDtoDocumentoIden().getDtoTipoDocumento().getTipoDocumento().getDescripcionDocumento()));
				}
				
				/*****************************************
				 * CARGAMOS LOS VALORES DEL CONTACTO
				 *****************************************/
				if(dtoUsuarioaModificar.getDtoContacto() != null){
					if(dtoUsuarioaModificar.getDtoContacto().getContacto().getDomicilio() != null){
						jTextFieldDirec.setText(dtoUsuarioaModificar.getDtoContacto().getContacto().getDomicilio());
					}
					
					if(dtoUsuarioaModificar.getDtoContacto().getContacto().getWeb() != null){
						jTextFieldWebBlog.setText(dtoUsuarioaModificar.getDtoContacto().getContacto().getWeb());
					}
					
					if(dtoUsuarioaModificar.getDtoContacto().getContacto().getEmail() != null){
						jTextFieldEmail.setText(dtoUsuarioaModificar.getDtoContacto().getContacto().getEmail());
					}
					
					if(dtoUsuarioaModificar.getDtoContacto().getContacto().getCp() != null){
						jTextFieldCP.setText(dtoUsuarioaModificar.getDtoContacto().getContacto().getCp().toString());
					}
					
					if(dtoUsuarioaModificar.getDtoContacto().getContacto().getLocalidad() != null){
						jTextFieldLocalidad.setText(dtoUsuarioaModificar.getDtoContacto().getContacto().getLocalidad());
					}
					
					if(dtoUsuarioaModificar.getDtoContacto().getDtoPais().getPais().getIdPais()!= null){
						jComboBoxpais.setSelectedItem(new MostrarCombo(dtoUsuarioaModificar.getDtoContacto().getDtoPais().getPais().getIdPais(),
								dtoUsuarioaModificar.getDtoContacto().getDtoPais().getPais().getNombrePais()));
					}
					jComboBoxSexo.setSelectedItem(new MostrarCombo(usuario.getSexo(),"F".equalsIgnoreCase(usuario.getSexo())?"Femenino":"Masculino"));
				}
				
				
				/*****************************************
				 * CARGAMOS LOS VALORES DEL TELEFONO
				 *****************************************/
				if(dtoUsuarioaModificar.getDtoContacto().getDtoTelefono() != null){
					
					jComboBoxTipo.setSelectedItem(new MostrarCombo(dtoUsuarioaModificar.getDtoContacto().getDtoTelefono().getDtoTelefono().getDtoTipoTelefono().getTipoTelefono().getIdTipoTelefono(),
							dtoUsuarioaModificar.getDtoContacto().getDtoTelefono().getDtoTipoTelefono().getTipoTelefono().getDescripcion()));
					
					if(dtoUsuarioaModificar.getDtoContacto().getDtoTelefono().getTelefono().getExtension()!= null && dtoUsuarioaModificar.getDtoContacto().getDtoTelefono().getTelefono().getExtension()!=-1){
						jTextFieldExtension.setText(dtoUsuarioaModificar.getDtoContacto().getDtoTelefono().getTelefono().getExtension().toString());
					}
					
					if(dtoUsuarioaModificar.getDtoContacto().getDtoTelefono().getTelefono().getTelefono()!= null){
						jTextFieldTelefono.setText(dtoUsuarioaModificar.getDtoContacto().getDtoTelefono().getTelefono().getTelefono().toString());
					}
					
					if(dtoUsuarioaModificar.getDtoContacto().getDtoTelefono().getTelefono().getPrefijoPais()!= null){
						jTextFieldPrefijo.setText(dtoUsuarioaModificar.getDtoContacto().getDtoTelefono().getTelefono().getPrefijoPais());
					}
				}
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
	
}
