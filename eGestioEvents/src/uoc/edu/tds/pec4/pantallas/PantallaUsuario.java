package uoc.edu.tds.pec4.pantallas;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

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
import uoc.edu.tds.pec4.beans.Usuario;
import uoc.edu.tds.pec4.dtos.DTOAdministrador;
import uoc.edu.tds.pec4.dtos.DTOAsistente;
import uoc.edu.tds.pec4.dtos.DTOCentroDocente;
import uoc.edu.tds.pec4.dtos.DTOContacto;
import uoc.edu.tds.pec4.dtos.DTODatosBancarios;
import uoc.edu.tds.pec4.dtos.DTODocumentoIdentificacion;
import uoc.edu.tds.pec4.dtos.DTOPais;
import uoc.edu.tds.pec4.dtos.DTOPersonalSecretaria;
import uoc.edu.tds.pec4.dtos.DTOTipoDocumento;
import uoc.edu.tds.pec4.dtos.DTOTipoRol;
import uoc.edu.tds.pec4.dtos.DTOTipoTelefono;
import uoc.edu.tds.pec4.dtos.DTOUniversidad;
import uoc.edu.tds.pec4.dtos.DTOUsuario;
import uoc.edu.tds.pec4.excepciones.OperationErrorBD;
import uoc.edu.tds.pec4.excepciones.OperationErrorDatosFormulario;
import uoc.edu.tds.pec4.gestores.GestorRMI;
import uoc.edu.tds.pec4.iface.RemoteInterface;
import uoc.edu.tds.pec4.utils.ClearForm;
import uoc.edu.tds.pec4.utils.Constantes;
import uoc.edu.tds.pec4.utils.EmailValidator;
import uoc.edu.tds.pec4.utils.JTextFieldLimit;
import uoc.edu.tds.pec4.utils.MostrarCombo;
import uoc.edu.tds.pec4.utils.Utils;

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
	private JTextField jTextField2;
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

	public PantallaUsuario(GestorRMI gestorRMI,RemoteInterface remote1) {
		super();
		remote = remote1;
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
			this.setPreferredSize(new java.awt.Dimension(784, 565));
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
				jPanel2Layout.rowHeights = new int[] {20, 25, 24, 17, 23, 23, 22, 23, 32, 22, 20, 28, 34, 14, 28, 34, 15, 19};
				jPanel2Layout.columnWeights = new double[] {0.0, 0.0, 0.0, 0.1};
				jPanel2Layout.rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
				jPanel2.setPreferredSize(new java.awt.Dimension(724, 461));
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
					jTextField2 = new JTextField();
					jPanel2.add(jTextField2, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					jTextField2.setPreferredSize(new java.awt.Dimension(200, 21));
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
					jLabelCont.setText("Contraseña");
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
					jComboBoxTipoDoc.setPreferredSize(new java.awt.Dimension(200, 21));
					
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
					jLabelDocIden.setText("Documento identificación");
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
					jComboBoxpais.setPreferredSize(new java.awt.Dimension(200, 21));
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
					
					jComboBoxSexo.setPreferredSize(new java.awt.Dimension(200, 21));
				}
				{
					jLabelBlog = new JLabel();
					jPanel2.add(jLabelBlog, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabelBlog.setText("Página Web o Blog");
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
					jLabelTelf.setText("Teléfono");
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
					
					jComboBoxTipo.setPreferredSize(new java.awt.Dimension(200, 21));
				}
				{
					jLabelUniversidad = new JLabel();
					jPanel2.add(jLabelUniversidad, new GridBagConstraints(0, 13, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabelUniversidad.setText("Universidad");
				}
				{
					
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
					jComboBoxUniver = new JComboBox();
					jPanel2.add(jComboBoxUniver, new GridBagConstraints(1, 13, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					jComboBoxUniver.setModel(jComboBoxUniverModel);
					jComboBoxUniver.setPreferredSize(new java.awt.Dimension(200, 21));
					
				}
				{
					
					jComboBoxCentroDocente = new JComboBox();
					jPanel2.add(jComboBoxCentroDocente, new GridBagConstraints(3, 13, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					jComboBoxCentroDocente.setPreferredSize(new java.awt.Dimension(200, 21));
					
					//Empezamos seleccionando el primer objeto cargado
					if(jComboBoxUniver.getItemCount() > 0) rellenaCentrosDocentes(((MostrarCombo)jComboBoxUniver.getSelectedItem()).getID());
					
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
					jComboBoxTipoRol.setPreferredSize(new java.awt.Dimension(200, 21));
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
									validaFormulario();
									String codigo = remote.insertaUsuario(altaUsuario());
									limpiaFormulario();
									Utils.mostraMensajeInformacion(jPanel2, "Registro insertado correctamente.\nSu identificador de usuario es " + codigo, "Alta usuario");
								} catch (RemoteException e1) {
									e1.printStackTrace();
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
					jButtonCancelar.setText("Cancelar");
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
			
			jTextField2.setEnabled(false);
			
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
	 * Rellena los centros docentes dependiendo del país seleccionado
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
	 * Método que valida los datos introducidos en el formulario
	 * @throws OperationErrorDatosFormulario
	 */
	private void validaFormulario() throws OperationErrorDatosFormulario{
		try{
			
			//Campos a validar genéricamente
			if(!jRadioButtonAdmin.isSelected() && !jRadioButtonSecr.isSelected() && !jRadioButtonAsis.isSelected()) throw new Exception("Ha de seleccionar un tipo de perfil");
			
			if(Utils.valorisNull(jTextFieldNombre.getText())) throw new Exception(Utils.MESSAGE_ERROR + " nombre" );
			if(Utils.valorisNull(jTextFieldApe.getText())) throw new Exception(Utils.MESSAGE_ERROR + " apellidos" );
			if(Utils.valorisNull(jComboBoxTipoDoc.getSelectedItem())) throw new Exception(Utils.MESSAGE_ERROR + " tipo de documento" );
			if(Utils.valorisNull(jTextFieldDirec.getText())) throw new Exception(Utils.MESSAGE_ERROR + " direccion" );
			
			if(Utils.valorisNull(jComboBoxUniver.getSelectedItem())) throw new Exception(Utils.MESSAGE_ERROR + " universidad" );
			if(Utils.valorisNull(jComboBoxTipo.getSelectedItem())) throw new Exception(Utils.MESSAGE_ERROR + " tipo teléfono" );
			if(Utils.valorisNull(jTextFieldExtension.getText())) throw new Exception(Utils.MESSAGE_ERROR + " extensión teléfono" );
			if(Utils.valorisNull(jTextFieldTelefono.getText())) throw new Exception(Utils.MESSAGE_ERROR + " teléfono" );
			if(Utils.valorisNull(jTextFieldFechaNac.getText())) throw new Exception(Utils.MESSAGE_ERROR + " fecha nacimiento" );
			if(Utils.valorisNull(jTextFieldWebBlog.getText())) throw new Exception(Utils.MESSAGE_ERROR + " página web o blog" );
			if(Utils.valorisNull(jComboBoxSexo.getSelectedItem())) throw new Exception(Utils.MESSAGE_ERROR + " sexo" );
			if(Utils.valorisNull(jTextFieldEmail.getText())) throw new Exception(Utils.MESSAGE_ERROR + " email" );
			if(Utils.valorisNull(jTextFieldCP.getText())) throw new Exception(Utils.MESSAGE_ERROR + " código postal" );
			if(Utils.valorisNull(jTextFieldLocalidad.getText())) throw new Exception(Utils.MESSAGE_ERROR + " localidad" );
			if(Utils.valorisNull(jTextFieldDocIden.getText())) throw new Exception(Utils.MESSAGE_ERROR + " documento identificación" );
			if(Utils.valorisNull(jTextFieldDirec.getText())) throw new Exception(Utils.MESSAGE_ERROR + " dirección" );
			if(Utils.valorisNull(jTextFieldCon.getText())) throw new Exception(Utils.MESSAGE_ERROR + " contraseña" );
			if(!Utils.parseaFecha(jTextFieldFechaNac.getText())) throw new Exception(Utils.MESSAGE_ERROR + " fecha nacimiento" + Utils.MESSAGE_FECHA );
			
			//Campos a validar exclusivamente para un ASISTENTE o SECRETARIA
			if(jRadioButtonSecr.isSelected() || jRadioButtonAsis.isSelected()){
				if(Utils.valorisNull(jComboBoxCentroDocente.getSelectedItem())) throw new Exception(Utils.MESSAGE_ERROR + " centro docente" );
				if(Utils.valorisNull(jComboBoxpais.getSelectedItem())) throw new Exception(Utils.MESSAGE_ERROR + " país" );
			}
			
			//Campos a validar exclusivamente para un ASISTENTE
			if(jRadioButtonAsis.isSelected()){
				//Campos a validar si es un DTOAsistente
				if(Utils.valorisNull(jTextFieldBanco.getText())) throw new Exception(Utils.MESSAGE_ERROR + " banco" );
				if(Utils.valorisNull(jComboBoxTipoRol.getSelectedItem())) throw new Exception(Utils.MESSAGE_ERROR + " tipo de rol" );
				if(Utils.valorisNull(jTextFieldSucursal.getText())) throw new Exception(Utils.MESSAGE_ERROR + " sucursal" );
				if(Utils.valorisNull(jTextFieldDC.getText())) throw new Exception(Utils.MESSAGE_ERROR + " DC" );
				if(Utils.valorisNull(jTextFieldCuenta.getText())) throw new Exception(Utils.MESSAGE_ERROR + " cuenta" );
				
				//Aquí ya no vendrá ningún campo null así que realizamos más modificaciones
				if(!Utils.validaNumerico(jTextFieldCon.getText())) 	throw new Exception(Utils.MESSAGE_ERROR + " password " + Utils.MESSAGE_NUMERIC );
				if(!Utils.validaNumerico(jTextFieldCP.getText())) 	throw new Exception(Utils.MESSAGE_ERROR + " código postal " + Utils.MESSAGE_NUMERIC );
				if(!Utils.validaNumerico(jTextFieldTelefono.getText())) throw new Exception(Utils.MESSAGE_ERROR + " teléfono" + Utils.MESSAGE_NUMERIC );
				if(!Utils.validaNumerico(jTextFieldExtension.getText())) throw new Exception(Utils.MESSAGE_ERROR + " extensión teléfono" + Utils.MESSAGE_NUMERIC );
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
	 * Método para cargar los combos de la pantalla Usuario
	 * @throws OperationErrorDatosFormulario
	 */
	private void cargaCombos() throws OperationErrorDatosFormulario{
		try{
			
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
			
			List<MostrarCombo> lstComboBox = new ArrayList<MostrarCombo>();
			lstComboBox.add(new MostrarCombo("M","Masculino"));
			lstComboBox.add(new MostrarCombo("F","Femenino"));
			ComboBoxModel jComboBoxSexoModel =  new DefaultComboBoxModel(lstComboBox.toArray());
			jComboBoxSexo.setModel(jComboBoxSexoModel);
			
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
			throw new OperationErrorDatosFormulario("Error al cargar las listas seleccionables");
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private <B extends DTOUsuario> B altaUsuario() throws OperationErrorDatosFormulario{
		
		//Datos específicos del contacto
		DTOContacto dtoContacto = new DTOContacto();
		Contacto contacto = new Contacto();
		contacto.setDomicilio(jTextFieldDirec.getText());
		contacto.setCp(Integer.parseInt(jTextFieldCP.getText()));
		contacto.setLocalidad(jTextFieldLocalidad.getText());
		contacto.setProvincia(null);
		contacto.setIdPais(Integer.parseInt(((MostrarCombo) jComboBoxpais.getSelectedItem()).getID().toString()));
		contacto.setEmail(jTextFieldEmail.getText());
		contacto.setWeb(jTextFieldWebBlog.getText());
		contacto.setEstado(1);
		contacto.setFechaEstado(new java.sql.Date(System.currentTimeMillis()));
		contacto.setMotivoEstado("Alta de usuario");
		dtoContacto.setContacto(contacto);
		
		//Datos específicos del usuario
		Usuario usuario = new Usuario();
		usuario.setNombre(jTextFieldNombre.getText());
		usuario.setApellidos(jTextFieldApe.getText());
		usuario.setSexo(((MostrarCombo) jComboBoxSexo.getSelectedItem()).getID().toString());
		
		try {
			usuario.setFechaNacimiento(Utils.transformFecha(jTextFieldFechaNac.getText()));
		} catch (OperationErrorDatosFormulario e) {
			throw e;
		}
		
		usuario.setFechaContrasena(new java.sql.Date(System.currentTimeMillis()));
		usuario.setContrasena(jTextFieldCon.getText());
		usuario.setCambiarContrasena(false);
		usuario.setEstado(1);
		usuario.setFechaEstado(new java.sql.Date(System.currentTimeMillis()));
		usuario.setMotivoEstado("Alta de usuario");
		usuario.setIdDocumentoIdentificacion(Integer.parseInt(((MostrarCombo) jComboBoxTipoDoc.getSelectedItem()).getID().toString()));
		usuario.setCodigo(generaCodigo(usuario));
		
		//Rellenamos el d documento de identidad
		DTODocumentoIdentificacion dtoDocumentoIden = new DTODocumentoIdentificacion();
		DocumentoIdentificacion docIden = new DocumentoIdentificacion();
		docIden.setIdDocumentoIdentificacion(usuario.getIdDocumentoIdentificacion());
		docIden.setIdTipoDocumento(Integer.parseInt(((MostrarCombo) jComboBoxTipoDoc.getSelectedItem()).getID().toString()));
		docIden.setIdPais(Integer.parseInt(((MostrarCombo) jComboBoxpais.getSelectedItem()).getID().toString()));
		docIden.setNumeroDocumento(jTextFieldDocIden.getText());
		dtoDocumentoIden.setDocumentoIdentificacion(docIden);
		
		
		//Tipo de usuario
		if(jRadioButtonAdmin.isSelected()) usuario.setTipoUsuario(Constantes.ADMINISTRADOR);
		if(jRadioButtonSecr.isSelected()) usuario.setTipoUsuario(Constantes.SECRETARIA);
		if(jRadioButtonAsis.isSelected()) usuario.setTipoUsuario(Constantes.ASISTENTE);
		
		
		if(Constantes.ASISTENTE == usuario.getTipoUsuario() || usuario.getTipoUsuario()==Constantes.SECRETARIA){
			usuario.setIdCentro(Integer.parseInt(((MostrarCombo) jComboBoxCentroDocente.getSelectedItem()).getID().toString()));
		}
		
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
			datosBancarios.setEstado(1);
			datosBancarios.setFechaEstado(new java.sql.Date(System.currentTimeMillis()));
			datosBancarios.setMotivoEstado("Alta de usuario");
			dtoDatosBancarios.setDatosBancarios(datosBancarios);
		}
		
		switch(usuario.getTipoUsuario()){
			case Constantes.ADMINISTRADOR:
					DTOAdministrador dtoAdministrador = new DTOAdministrador();
					dtoAdministrador.setUsuario(usuario);
					dtoAdministrador.setDtoContacto(dtoContacto);
					dtoAdministrador.setDtoDocumentoIden(dtoDocumentoIden);
					dtoAdministrador.getUsuario().setIdRol(-1); //No tienen roles
					dtoAdministrador.getUsuario().setIdCentro(-1); //No tiene centro que asignar
					dtoAdministrador.getUsuario().setIdDatosBancarios(-1); //No tiene datos bancarios
					return (B) dtoAdministrador;
			case Constantes.SECRETARIA:
					DTOPersonalSecretaria dtoPersonal = new DTOPersonalSecretaria();
					dtoPersonal.setUsuario(usuario);
					dtoPersonal.setDtoContacto(dtoContacto);
					dtoPersonal.setDtoDocumentoIden(dtoDocumentoIden);
					dtoPersonal.getUsuario().setIdRol(-1); //No tienen roles
					dtoPersonal.getUsuario().setIdDatosBancarios(-1); //No tiene datos bancarios
					return (B) dtoPersonal;
			case Constantes.ASISTENTE:
					DTOAsistente dtoAsistente = new DTOAsistente();
					dtoAsistente.setUsuario(usuario);
					dtoAsistente.setDtoContacto(dtoContacto);
					dtoAsistente.setDtoDocumentoIden(dtoDocumentoIden);
					dtoAsistente.setDtoDatosBancarios(dtoDatosBancarios);
					return (B) dtoAsistente;
			default:
				throw new OperationErrorDatosFormulario("El tipo de usuario " + usuario.getTipoUsuario() + " no está contemplado");
		}
		
	}
	
	/*
	 * Limpia el formulario
	 */
	private void limpiaFormulario(){
		ClearForm.clearForm(jPanel1);
		ClearForm.clearForm(jPanel2);
		jRadioButtonAdmin.setSelected(true);
	}
	
	/*
	 * Método que genera el código del usuario
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
			//Falta método para comprobar codigo
			codigoAcep = true;
		}
		return codigoUsuario.toString();
		
	}

}
