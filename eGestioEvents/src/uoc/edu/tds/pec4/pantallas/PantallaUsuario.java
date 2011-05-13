package uoc.edu.tds.pec4.pantallas;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import uoc.edu.tds.pec4.dtos.DTOCentroDocente;
import uoc.edu.tds.pec4.dtos.DTOPais;
import uoc.edu.tds.pec4.dtos.DTOTipoDocumento;
import uoc.edu.tds.pec4.dtos.DTOTipoRol;
import uoc.edu.tds.pec4.dtos.DTOTipoTelefono;
import uoc.edu.tds.pec4.dtos.DTOUniversidad;
import uoc.edu.tds.pec4.excepciones.OperationErrorBD;
import uoc.edu.tds.pec4.excepciones.OperationErrorRMI;
import uoc.edu.tds.pec4.gestores.GestorRMI;
import uoc.edu.tds.pec4.iface.RemoteInterface;
import uoc.edu.tds.pec4.utils.MostrarCombo;

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
	/**
	 * 
	 */
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
	private JTextField jTextField1;
	private JPanel jPanel2;
	private JRadioButton jRadioButtonAsis;
	private JRadioButton jRadioButtonSecr;
	private JRadioButton jRadioButtonAdmin;
	private JLabel tipoperfil;
	private RemoteInterface remote;

	public PantallaUsuario(GestorRMI gestorRMI) {
		super();
		try {
			remote = gestorRMI.lookup();
			System.out.print("Para quitar el warning que sale si no se utiliza es provisional " + remote.toString());
		} catch (OperationErrorRMI e) {
			e.showDialogError();
		}
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
					//Recuperamos los diferentes tipos de documentos
					List<DTOTipoDocumento> lstdtoTipoDoc = remote.getTiposDocumento();
					List<MostrarCombo> lstComoTipoDoc = new ArrayList<MostrarCombo>();
					for(DTOTipoDocumento dtoTipoDocRes : lstdtoTipoDoc){
						lstComoTipoDoc.add(new MostrarCombo(dtoTipoDocRes.getTipoDocumento().getIdTipoDocumento(),
								dtoTipoDocRes.getTipoDocumento().getDescripcionDocumento()));
					}
					
					jComboBoxTipoDoc = new JComboBox();
					jPanel2.add(jComboBoxTipoDoc, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					jComboBoxTipoDoc.setModel(new DefaultComboBoxModel(lstComoTipoDoc.toArray()));
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
				}
				{
					jLabelPais = new JLabel();
					jPanel2.add(jLabelPais, new GridBagConstraints(2, 5, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabelPais.setText("Pais");
					jLabelPais.setLayout(null);
				}
				{
					
					//Cargamos la lista de países
					List<DTOPais> lstDtoPaises = remote.getPaises();
					List<MostrarCombo> lstComboPais = new ArrayList<MostrarCombo>();
					for(DTOPais dtoPaisRec : lstDtoPaises){
						lstComboPais.add(new MostrarCombo(dtoPaisRec.getPais().getIdPais(),dtoPaisRec.getPais().getNombrePais()));
					}
					
					ComboBoxModel jComboBoxpaisModel =  new DefaultComboBoxModel(lstComboPais.toArray());
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
				}
				{
					jLabelSexo = new JLabel();
					jPanel2.add(jLabelSexo, new GridBagConstraints(2, 6, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabelSexo.setText("Sexo");
					jLabelSexo.setLayout(null);
				}
				{
					List<MostrarCombo> lstComboBox = new ArrayList<MostrarCombo>();
					lstComboBox.add(new MostrarCombo("M","Masculino"));
					lstComboBox.add(new MostrarCombo("F","Femenino"));
					ComboBoxModel jComboBoxSexoModel =  new DefaultComboBoxModel(lstComboBox.toArray());
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
					
					//Cargamos los diferentes tipos de teléfono
					List<DTOTipoTelefono> lstDtoTiposTelf = remote.getTiposTelefono();
					List<MostrarCombo> lstComboTipoTelf = new ArrayList<MostrarCombo>();
					for(DTOTipoTelefono dtoTipoTelfRec : lstDtoTiposTelf){
						lstComboTipoTelf.add(new MostrarCombo(dtoTipoTelfRec.getTipoTelefono().getIdTipoTelefono(),
								dtoTipoTelfRec.getTipoTelefono().getDescripcion()));
					}
					
					ComboBoxModel jComboBoxTipoModel =  new DefaultComboBoxModel(lstComboTipoTelf.toArray());
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
					jTextFieldBanco.setPreferredSize(new java.awt.Dimension(60, 21));
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
				}
				{
					jButtonCancelar = new JButton();
					jPanelButtom.add(jButtonCancelar);
					jButtonCancelar.setText("Cancelar");
					jButtonCancelar.setLayout(null);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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

}
