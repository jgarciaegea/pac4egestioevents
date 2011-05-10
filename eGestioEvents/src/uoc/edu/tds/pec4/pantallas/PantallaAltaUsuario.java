package uoc.edu.tds.pec4.pantallas;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;


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
public class PantallaAltaUsuario extends javax.swing.JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel jPanel1;
	private JLabel jLabelApe;
	private JTextField jTextFieldApe;
	private JLabel jLabelNombre;
	private JTextField jTextField1;
	private JPanel jPanel2;
	private JButton jButtonCancelar;
	private JButton jButtonAcceptar;
	private JTextField jTextFieldCuenta;
	private JLabel jLabelCuenta;
	private JTextField jTextFieldDC;
	private JLabel jLabelDC;
	private JTextField jTextFieldSucursal;
	private JLabel jLabelSucursal;
	private JTextField jTextFieldBanco;
	private JLabel jLabelBanco;
	private JLabel jLabelDatosBanc;
	private JLabel jLabelDocIden;
	private JComboBox jComboBoxTipoRol;
	private JLabel jLabelTipoRol;
	private JLabel jLabelCenDocente;
	private JComboBox jComboBoxCentroDocente;
	private JComboBox jComboBoxUniver;
	private JLabel jLabelUniversidad;
	private JComboBox jComboBoxTipo;
	private JLabel jLabelTipo;
	private JTextField jTextFieldExtension;
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
	private JTextField jTextFieldDirec;
	private JLabel jLabelDirec;
	private JComboBox jComboBoxTipoDoc;
	private JLabel jLabelTipoDoc;
	private JLabel jLabelCont;
	private JTextField jTextFieldCon;
	private JLabel jLabelCodigo;
	private JTextField jTextField2;
	private JPanel jPanelButtom;
	private JLabel tipoperfil;
	private JRadioButton jRadioButtonAsis;
	private JRadioButton jRadioButtonSecr;
	private JRadioButton jRadioButtonAdmin;

	/**
	* Auto-generated main method to display this JFrame
	 * @throws Exception 
	*/
		
	public PantallaAltaUsuario() throws Exception {
		super();
		initGUI();
		visualizaDatosBancarios(true);
		visualizaDatosTipoRol(true);
	}
	
	private void initGUI() throws Exception {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setSize(774, 564);
			{
				jPanelButtom = new JPanel();
				getContentPane().add(jPanelButtom, BorderLayout.SOUTH);
				jPanelButtom.setBounds(238, 280, 10, 10);
				jPanelButtom.setPreferredSize(new java.awt.Dimension(766, 40));
				{
					jButtonAcceptar = new JButton();
					jPanelButtom.add(jButtonAcceptar);
					jButtonAcceptar.setText("Acceptar");
					jButtonAcceptar.setLayout(null);
					jButtonAcceptar.setBounds(277, -16, 64, 21);
				}
				{
					jButtonCancelar = new JButton();
					jPanelButtom.add(jButtonCancelar);
					jButtonCancelar.setText("Cancelar");
					jButtonCancelar.setLayout(null);
				}
			}
			{
				jPanel1 = new JPanel();
				getContentPane().add(jPanel1, BorderLayout.NORTH);
				{
					tipoperfil = new JLabel();
					jPanel1.add(tipoperfil);
					tipoperfil.setPreferredSize(new java.awt.Dimension(63, 14));
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
				getContentPane().add(jPanel2, BorderLayout.CENTER);
				GridBagLayout jPanel2Layout = new GridBagLayout();
				jPanel2.setPreferredSize(new java.awt.Dimension(675, 321));
				jPanel2Layout.rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
				jPanel2Layout.rowHeights = new int[] {20, 25, 24, 17, 23, 23, 22, 23, 32, 22, 20, 28, 34, 14, 28, 34, 15, 19};
				jPanel2Layout.columnWeights = new double[] {0.0, 0.0, 0.0, 0.1};
				jPanel2Layout.columnWidths = new int[] {142, 222, 108, 7};
				jPanel2.setLayout(jPanel2Layout);
				{
					jTextField1 = new JTextField();
					jPanel2.add(jTextField1, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.SOUTHWEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 15), 0, 0));
					jTextField1.setPreferredSize(new java.awt.Dimension(200, 21));
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
					jTextFieldCon = new JTextField();
					jPanel2.add(jTextFieldCon, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					jTextFieldCon.setPreferredSize(new java.awt.Dimension(200, 21));
				}
				{
					jLabelTipoDoc = new JLabel();
					jPanel2.add(jLabelTipoDoc, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabelTipoDoc.setText("Tipo de documento");
					jLabelTipoDoc.setLayout(null);
				}
				{
					ComboBoxModel jComboBoxTipoDocModel = 
						new DefaultComboBoxModel(
								new String[] { "Item One", "Item Two" });
					jComboBoxTipoDoc = new JComboBox();
					jPanel2.add(jComboBoxTipoDoc, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					jComboBoxTipoDoc.setModel(jComboBoxTipoDocModel);
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
					jTextFieldDirec.setPreferredSize(new java.awt.Dimension(360, 21));
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
				}
				{
					jLabelPais = new JLabel();
					jPanel2.add(jLabelPais, new GridBagConstraints(2, 5, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabelPais.setText("Pais");
					jLabelPais.setLayout(null);
				}
				{
					ComboBoxModel jComboBoxpaisModel = 
						new DefaultComboBoxModel(
								new String[] { "Item One", "Item Two" });
					jComboBoxpais = new JComboBox();
					jPanel2.add(jComboBoxpais, new GridBagConstraints(3, 5, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					jComboBoxpais.setModel(jComboBoxpaisModel);
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
					jTextFieldEmail.setSize(200, 21);
				}
				{
					jLabelSexo = new JLabel();
					jPanel2.add(jLabelSexo, new GridBagConstraints(2, 6, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabelSexo.setText("Sexo");
					jLabelSexo.setLayout(null);
				}
				{
					ComboBoxModel jComboBoxSexoModel = 
						new DefaultComboBoxModel(
								new String[] { "Item One", "Item Two" });
					jComboBoxSexo = new JComboBox();
					jPanel2.add(jComboBoxSexo, new GridBagConstraints(3, 6, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					jComboBoxSexo.setModel(jComboBoxSexoModel);
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
					ComboBoxModel jComboBoxTipoModel = 
						new DefaultComboBoxModel(
								new String[] { "Item One", "Item Two" });
					jComboBoxTipo = new JComboBox();
					jPanel2.add(jComboBoxTipo, new GridBagConstraints(1, 11, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					jComboBoxTipo.setModel(jComboBoxTipoModel);
					jComboBoxTipo.setPreferredSize(new java.awt.Dimension(200, 21));
				}
				{
					jLabelUniversidad = new JLabel();
					jPanel2.add(jLabelUniversidad, new GridBagConstraints(0, 13, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabelUniversidad.setText("Universidad");
				}
				{
					ComboBoxModel jComboBoxUniverModel = 
						new DefaultComboBoxModel(
								new String[] { "Item One", "Item Two" });
					jComboBoxUniver = new JComboBox();
					jPanel2.add(jComboBoxUniver, new GridBagConstraints(1, 13, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					jComboBoxUniver.setModel(jComboBoxUniverModel);
					jComboBoxUniver.setPreferredSize(new java.awt.Dimension(200, 21));
				}
				{
					ComboBoxModel jComboBoxCentroDocenteModel = 
						new DefaultComboBoxModel(
								new String[] { "Item One", "Item Two" });
					jComboBoxCentroDocente = new JComboBox();
					jPanel2.add(jComboBoxCentroDocente, new GridBagConstraints(3, 13, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					jComboBoxCentroDocente.setModel(jComboBoxCentroDocenteModel);
					jComboBoxCentroDocente.setPreferredSize(new java.awt.Dimension(200, 21));
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
					ComboBoxModel jComboBoxTipoRolModel = 
						new DefaultComboBoxModel(
								new String[] { "Item One", "Item Two" });
					jComboBoxTipoRol = new JComboBox();
					jPanel2.add(jComboBoxTipoRol, new GridBagConstraints(1, 14, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					jComboBoxTipoRol.setModel(jComboBoxTipoRolModel);
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
					jTextFieldBanco.setPreferredSize(new java.awt.Dimension(50, 21));
				}
				{
					jLabelSucursal = new JLabel();
					jPanel2.add(jLabelSucursal, new GridBagConstraints(1, 17, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 75), 0, 0));
					jLabelSucursal.setText("Sucursal");
				}
				{
					jTextFieldSucursal = new JTextField();
					jPanel2.add(jTextFieldSucursal, new GridBagConstraints(1, 17, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 23), 0, 0));
					jTextFieldSucursal.setPreferredSize(new java.awt.Dimension(50, 21));
				}
				{
					jLabelDC = new JLabel();
					jPanel2.add(jLabelDC, new GridBagConstraints(2, 17, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 9, 0, 0), 0, 0));
					jLabelDC.setText("DC");
				}
				{
					jTextFieldDC = new JTextField();
					jPanel2.add(jTextFieldDC, new GridBagConstraints(2, 17, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 29, 0, 0), 0, 0));
					jTextFieldDC.setPreferredSize(new java.awt.Dimension(30, 21));
				}
				{
					jLabelCuenta = new JLabel();
					jPanel2.add(jLabelCuenta, new GridBagConstraints(2, 17, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 9), 0, 0));
					jLabelCuenta.setText("Cuenta");
				}
				{
					jTextFieldCuenta = new JTextField();
					jPanel2.add(jTextFieldCuenta, new GridBagConstraints(3, 17, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jTextFieldCuenta.setPreferredSize(new java.awt.Dimension(200, 21));
				}
			}
		} catch (Exception e) {
		    throw new Exception();
		}
	}
	
	/**
	 * Método encargado de ocultar/visualizar los datos bancarios
	 * @param bValor
	 */
	private void visualizaDatosBancarios(Boolean bValor){
		jTextFieldCuenta.setVisible(bValor);
		jLabelCuenta.setVisible(bValor);
		jTextFieldDC.setVisible(bValor);
		jLabelDC.setVisible(bValor);
		jTextFieldSucursal.setVisible(bValor);
		jLabelSucursal.setVisible(bValor);
		jTextFieldBanco.setVisible(bValor);
		jLabelBanco.setVisible(bValor);
		jLabelDatosBanc.setVisible(bValor);
	}
	
	/**
	 * Método para ocultar el tipo de Rol
	 * @param bValor
	 */
	private void visualizaDatosTipoRol(Boolean bValor){
		jComboBoxTipoRol.setVisible(bValor);
		jLabelTipoRol.setVisible(bValor);
	}
	
	public static void main (String args[]){
		SwingUtilities.invokeLater(new Runnable() {
            public void run(){
				try {
					PantallaAltaUsuario pantallaAltausuario = new PantallaAltaUsuario();
					pantallaAltausuario.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
        });
		
	}

}
