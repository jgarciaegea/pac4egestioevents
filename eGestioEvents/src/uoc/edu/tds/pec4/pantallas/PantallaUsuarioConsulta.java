package uoc.edu.tds.pec4.pantallas;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import uoc.edu.tds.pec4.beans.Usuario;
import uoc.edu.tds.pec4.beans.UsuarioViewConsulta;
import uoc.edu.tds.pec4.dtos.DTOCentroDocente;
import uoc.edu.tds.pec4.dtos.DTOTipoDocumento;
import uoc.edu.tds.pec4.dtos.DTOTipoRol;
import uoc.edu.tds.pec4.dtos.DTOUniversidad;
import uoc.edu.tds.pec4.dtos.DTOUsuario;
import uoc.edu.tds.pec4.dtos.DTOUsuarioConsulta;
import uoc.edu.tds.pec4.excepciones.OperationErrorBD;
import uoc.edu.tds.pec4.excepciones.OperationErrorDatosFormulario;
import uoc.edu.tds.pec4.gestores.FactoriaUsuario;
import uoc.edu.tds.pec4.gestores.GestorRMI;
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
public class PantallaUsuarioConsulta extends javax.swing.JPanel implements Pantallas {
	private String[] columnNames = {"Codigo","idTipoUsuario","Nombre", "Apellidos", "Fecha de alta", "Perfil", "Universidad"};
	final static int interval = 1000;
	private DefaultTableModel dtm;
	private static final long serialVersionUID = 1L;
	private JComboBox jComboBoxCentroDocente;
	private JComboBox jComboBoxUniver;
	private JComboBox jComboBoxTipoDoc;
	private JLabel jLabelUniversidad;
	private JLabel jLabelTipo;
	private JButton jButtonAcceptar;
	private JButton jButtonCancelar;
	private JPanel jPanelButtom;
	private JLabel jLabelTelf;
	private JTextField jTextFieldLocalidad;
	private JTextField jTextFieldProvincia;
	private JLabel jLabelLocalidad;
	private JLabel jLabelDocIden;
	private JLabel jLabelModifica;
	private JLabel jLabelElimina;
	private JTable jTableRes;
	private JComboBox jComboBoxPerfil;
	private JComboBox jComboBoxTipoRol;
	private JLabel jLabelTipoPerfil;
	private JTextField jTextFieldDocuIden;
	private JRadioButton jRadioButtonDesaNo;
	private JRadioButton jRadioButtonDesSi;
	private JLabel jLabelActivos;
	private JScrollPane scrollPane;
	private JLabel jLabelTipoDoc;
	private JLabel jLabelApe;
	private JTextField jTextFieldApe;
	private JLabel jLabelNombre;
	private JTextField jTextFieldNombre;
	private JPanel jPanel2;
	private RemoteInterface remote;
	private ButtonGroup grupoBu;
	private JTextField jTextFieldHasta;
	private JLabel jLabelHasta;
	private JLabel jLabelDesde;
	private JTextField jTextFieldFechaIni;
	private JLabel jLabelFechaAlta;
	private GestorRMI gestorRMI;
	
	public PantallaUsuarioConsulta(GestorRMI gestorRMI,RemoteInterface remote1) {
		super();
		this.remote = remote1;
		this.gestorRMI = gestorRMI;
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
			this.setPreferredSize(new java.awt.Dimension(784, 601));
			{
				jPanel2 = new JPanel();
				this.add(jPanel2);
				GridBagLayout jPanel2Layout = new GridBagLayout();
				jPanel2Layout.columnWidths = new int[] {142, 222, 7};
				jPanel2Layout.rowHeights = new int[] {28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 25, 128};
				jPanel2Layout.columnWeights = new double[] {0.0, 0.0, 0.1};
				jPanel2Layout.rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
				jPanel2.setPreferredSize(new java.awt.Dimension(649, 502));
				jPanel2.setLayout(jPanel2Layout);
				{
					jTextFieldNombre = new JTextField();
					jPanel2.add(jTextFieldNombre, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 15), 0, 0));
					jTextFieldNombre.setPreferredSize(new java.awt.Dimension(200, 21));
				}
				{
					jLabelNombre = new JLabel();
					jPanel2.add(jLabelNombre, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(4, 0, 0, 0), 0, 0));
					jLabelNombre.setText("Nombre");
					jLabelNombre.setLayout(null);
				}
				{
					jTextFieldApe = new JTextField();
					jPanel2.add(jTextFieldApe, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(2, 8, 0, 0), 0, 0));
					jTextFieldApe.setPreferredSize(new java.awt.Dimension(200, 21));
				}
				{
					jLabelApe = new JLabel();
					jPanel2.add(jLabelApe, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(6, 0, 0, 0), 0, 0));
					jLabelApe.setText("Apellidos");
					jLabelApe.setLayout(null);
				}
				{
					jLabelTipoDoc = new JLabel();
					jPanel2.add(jLabelTipoDoc, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabelTipoDoc.setText("Tipo de documento");
					jLabelTipoDoc.setLayout(null);
				}
				{
					jComboBoxTipoDoc = new JComboBox();
					jPanel2.add(jComboBoxTipoDoc, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					jComboBoxTipoDoc.setPreferredSize(new java.awt.Dimension(200, 26));
				}
				{
					jLabelDocIden = new JLabel();
					jPanel2.add(jLabelDocIden, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabelDocIden.setText("Documento identificación");
					jLabelDocIden.setLayout(null);
				}
				{
					jLabelLocalidad = new JLabel();
					jPanel2.add(jLabelLocalidad, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabelLocalidad.setText("Localidad");
					jLabelLocalidad.setLayout(null);
				}
				{
					jTextFieldLocalidad = new JTextField();
					jPanel2.add(jTextFieldLocalidad, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					jTextFieldLocalidad.setPreferredSize(new java.awt.Dimension(200, 21));
				}
				{
					
					jTextFieldProvincia = new JTextField();
					jPanel2.add(jTextFieldProvincia, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					jTextFieldProvincia.setPreferredSize(new java.awt.Dimension(200, 21));
				}
				{
					jLabelTelf = new JLabel();
					jPanel2.add(jLabelTelf, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabelTelf.setText("Centro Docente");
					jLabelTelf.setLayout(null);
				}
				{
					jLabelTipo = new JLabel();
					jPanel2.add(jLabelTipo, new GridBagConstraints(0, 8, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabelTipo.setText("Tipo Rol");
				}
				{
					jLabelUniversidad = new JLabel();
					jPanel2.add(jLabelUniversidad, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabelUniversidad.setText("Universidad");
				}
				{
					
					jComboBoxUniver = new JComboBox();
					jPanel2.add(jComboBoxUniver, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					jComboBoxUniver.setPreferredSize(new java.awt.Dimension(200, 26));
					jComboBoxUniver.setOpaque(false);

				}
				{
					
					jComboBoxCentroDocente = new JComboBox();
					jPanel2.add(jComboBoxCentroDocente, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					jComboBoxCentroDocente.setPreferredSize(new java.awt.Dimension(200, 26));
					
				}
				{
					jLabelActivos = new JLabel();
					jPanel2.add(jLabelActivos, new GridBagConstraints(0, 10, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabelActivos.setText("Incluir desactivados");
				}
				{
					jRadioButtonDesSi = new JRadioButton();
					jPanel2.add(jRadioButtonDesSi, new GridBagConstraints(1, 10, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					jRadioButtonDesSi.setText("Si");
				}
				{
					jRadioButtonDesaNo = new JRadioButton();
					jPanel2.add(jRadioButtonDesaNo, new GridBagConstraints(1, 10, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, new Insets(0, 0, 0, 80), 0, 0));
					jRadioButtonDesaNo.setText("No");
				}
				{
					jTextFieldDocuIden = new JTextField();
					jPanel2.add(jTextFieldDocuIden, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					jTextFieldDocuIden.setPreferredSize(new java.awt.Dimension(200, 21));
				}
				{
					jLabelTipoPerfil = new JLabel();
					jLabelTipoPerfil.setText("Tipo Perfil");
					jPanel2.add(jLabelTipoPerfil, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					
					jComboBoxTipoRol = new JComboBox();
					jComboBoxTipoRol.setPreferredSize(new java.awt.Dimension(200, 26));
					jPanel2.add(jComboBoxTipoRol, new GridBagConstraints(1, 8, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					

				}
				{
					jComboBoxPerfil = new JComboBox();
					jComboBoxPerfil.setPreferredSize(new java.awt.Dimension(200, 26));
					jPanel2.add(jComboBoxPerfil, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					
				}
				{
					dtm=new DefaultTableModel();
					{
						for(int i=0;i<columnNames.length;i++){
				        	dtm.addColumn(columnNames[i]);
				        }
						
					}
					
					jTableRes = new JTable(dtm);
					
					//Ocultamos la columna del id
					Utils.ocultaColumna(jTableRes,0);
					Utils.ocultaColumna(jTableRes,1);
					
					jTableRes.setSize(297, 130);
					jTableRes.setPreferredSize(new java.awt.Dimension(597, 148));
					scrollPane=new JScrollPane(jTableRes);
					scrollPane.setVisible(true);
					jPanel2.add(scrollPane, new GridBagConstraints(0, 12, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					
					jLabelFechaAlta = new JLabel();
					jLabelFechaAlta.setText("Fecha Alta");
					jPanel2.add(jLabelFechaAlta, new GridBagConstraints(0, 9, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					
					jTextFieldFechaIni = new JTextField();
					jTextFieldFechaIni.setPreferredSize(new java.awt.Dimension(100, 21));
					jPanel2.add(jTextFieldFechaIni, new GridBagConstraints(1, 9, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 20), 0, 0));
					
					jLabelDesde = new JLabel();
					jLabelDesde.setText("Desde");
					jPanel2.add(jLabelDesde, new GridBagConstraints(1, 9, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 10, 0, 0), 0, 0));
					
					jLabelHasta = new JLabel();
					jLabelHasta.setText("Hasta");
					jPanel2.add(jLabelHasta, new GridBagConstraints(1, 9, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jTextFieldHasta = new JTextField();
					jTextFieldHasta.setPreferredSize(new java.awt.Dimension(100, 21));
					jPanel2.add(jTextFieldHasta, new GridBagConstraints(2, 9, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 7, 0, 0), 0, 0));
					jPanel2.add(getJLabelElimina(), new GridBagConstraints(2, 11, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 45), 0, 0));
					jPanel2.add(getJLabelModifica(), new GridBagConstraints(2, 11, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					scrollPane.setPreferredSize(new java.awt.Dimension(600, 150));
				}
			}
			{
				jPanelButtom = new JPanel();
				this.add(jPanelButtom);
				jPanelButtom.setPreferredSize(new java.awt.Dimension(758, 565));
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
									cargaListadoUsuarios();
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
			grupoBu.add(jRadioButtonDesaNo);
			grupoBu.add(jRadioButtonDesSi);
			
			//Cargamos combos
			cargaCombos();
			
			jRadioButtonDesSi.setSelected(true);
			
			/*
			 * Cuando clique sobre el botón modificar recogeremos el código y pondremos el usuario como inactivo
			 */
			jLabelElimina.addMouseListener(new MouseListener(){
				public void mouseClicked(MouseEvent e) {
					if(jTableRes.getSelectedRow() == -1){
						Utils.mostraMensajeInformacion(jPanel2, "No ha seleccionado ningún registro de la tabla", "Consulta Usuarios");
					}else{
						try {
							DTOUsuario dtoUsuario = getRecuperaUsuarioSeleccionado();
							remote.bajaUsuario(dtoUsuario);
							cargaListadoUsuarios();
							Utils.mostraMensajeInformacion(jPanel2,"Usuario dado de baja correctamente", "Consulta usuario");
						} catch (Exception e1) {
							try {
								throw new OperationErrorDatosFormulario(e1.getMessage());
							} catch (OperationErrorDatosFormulario e2) {
								e2.showDialogError();
							}
						}finally{
							limpiaFormulario();
						}
						
					}
				}
				public void mousePressed(MouseEvent e) {}
				public void mouseReleased(MouseEvent e) {}
				public void mouseEntered(MouseEvent e) {}
				public void mouseExited(MouseEvent e) {}
			});
			
			
			/*
			 * Modificamos el usuario
			 */
			jLabelModifica.addMouseListener(new MouseListener(){
				public void mouseClicked(MouseEvent e) {
					if(jTableRes.getSelectedRow() == -1){
						Utils.mostraMensajeInformacion(jPanel2, "No ha seleccionado ningún registro de la tabla", "Consulta Usuarios");
					}else{
						try {
							DTOUsuario dtoUsuario = getRecuperaUsuarioSeleccionado();
							goPantallaUsuario(dtoUsuario);
						} catch (OperationErrorDatosFormulario e1) {
							e1.showDialogError();
						}finally{
							limpiaFormulario();
						}
					}
				}
				public void mousePressed(MouseEvent e) {}
				public void mouseReleased(MouseEvent e) {}
				public void mouseEntered(MouseEvent e) {}
				public void mouseExited(MouseEvent e) {}
			});
			
			
		} catch (Exception e) {
			try{
				throw new OperationErrorDatosFormulario(e.getMessage());
			}catch(OperationErrorDatosFormulario ex){
				ex.showDialogError(jPanel2);
			}
		}
	}
	
	/*
	 * Recupera el usuario seleccionado de la tabla
	 */
	private DTOUsuario getRecuperaUsuarioSeleccionado() throws OperationErrorDatosFormulario{
		try {
			@SuppressWarnings("unchecked")
			List<Object> lstRes = (Vector<Object>) dtm.getDataVector().get(jTableRes.getSelectedRow());
			DTOUsuario dtoUsuario = obtenUsuario(lstRes.get(0).toString(),Integer.parseInt(lstRes.get(1).toString()));
			return dtoUsuario;
		} catch (NumberFormatException e1) {
			throw new OperationErrorDatosFormulario(e1.getMessage());
		} catch (Exception e1) {
			throw new OperationErrorDatosFormulario(e1.getMessage());
		}
	}
	
	/*
	 * Vamos a la pantalla de usuario
	 */
	private void goPantallaUsuario(DTOUsuario dtoUsuario){
		this.removeAll();
		this.setAlignmentX(LEFT_ALIGNMENT);
		this.setAlignmentY(TOP_ALIGNMENT);
		this.add((Component)new PantallaUsuario(gestorRMI, remote, dtoUsuario));
		this.revalidate();
	}
	
	/*
	 * Obtenemos el usuario mediante los campos recuperados de la tabla
	 */
	private DTOUsuario obtenUsuario(String codigoUsuario,Integer tipoUsuario) throws OperationErrorDatosFormulario{
		try{
			Usuario usuario = new Usuario();
			DTOUsuario dtoUsuario = FactoriaUsuario.getUsuario(tipoUsuario);
			usuario.setCodigo(codigoUsuario);
			usuario.setTipoUsuario(tipoUsuario);
			dtoUsuario.setUsuario(usuario);
			return dtoUsuario;
		}catch(Exception e){
			throw new OperationErrorDatosFormulario(e.getMessage());
		}
	}
	
	/*
	 * Carga el listado de usuarios
	 */
	private void cargaListadoUsuarios() throws OperationErrorDatosFormulario{
		try{
			List<DTOUsuario> lstDtoUsuario = remote.getUsuarios(consultaUsuarios());
			if(lstDtoUsuario == null || lstDtoUsuario.isEmpty()){
				dtm.getDataVector().removeAllElements();
				Utils.mostraMensajeInformacion(jPanel2,"No hay resultados","Búsqueda usuarios");
				jTableRes.repaint();
				return;
			}
			muestraResultado(lstDtoUsuario);
		}catch(Exception e){
			throw new OperationErrorDatosFormulario(e.getMessage());
		}
		
	}
	
	
	/**
	 * Rellena los centros docentes dependiendo del país seleccionado
	 * @param obj
	 */
	private void rellenaCentrosDocentes(Object obj){
		try {
			if((Integer)obj == 0) obj = null;//Las encontrará todas
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
	 * Método que valida los datos introducidos en el formulario
	 * @throws OperationErrorDatosFormulario
	 */
	private void validaFormulario() throws OperationErrorDatosFormulario{
		try{
			
			if(!"".equalsIgnoreCase(jTextFieldHasta.getText()) && "".equalsIgnoreCase(jTextFieldFechaIni.getText())){
				 throw new Exception("No puede introducir la fecha final sin previamente informar la fecha de inicio");
			}
			
			if(!"".equalsIgnoreCase(jTextFieldFechaIni.getText())){
				if(!Utils.parseaFecha(jTextFieldFechaIni.getText())) throw new Exception(Utils.MESSAGE_ERROR + " fecha inicio" + Utils.MESSAGE_FECHA );
			}
			
			if(!"".equalsIgnoreCase(jTextFieldHasta.getText())){
				if(!Utils.parseaFecha(jTextFieldHasta.getText())) throw new Exception(Utils.MESSAGE_ERROR + " fecha fin" + Utils.MESSAGE_FECHA );
			}
			
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
			jComboBoxUniver.setModel(jComboBoxUniverModel);
			
			//Cargamos tipo de rol
			List<DTOTipoRol> lstDtotipoRol = remote.getTiposRol();
			List<MostrarCombo> lstComboTipoRol = new ArrayList<MostrarCombo>();
			if(lstDtotipoRol != null){
				lstComboTipoRol.add(new MostrarCombo(0, Constantes.NOMBRE_TODOS));
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
				lstComoTipoDoc.add(new MostrarCombo(0, Constantes.NOMBRE_TODOS));
				for(DTOTipoDocumento dtoTipoDocRes : lstdtoTipoDoc){
					lstComoTipoDoc.add(new MostrarCombo(dtoTipoDocRes.getTipoDocumento().getIdTipoDocumento(),
							dtoTipoDocRes.getTipoDocumento().getDescripcionDocumento()));
				}
			}
			jComboBoxTipoDoc.setModel(new DefaultComboBoxModel(lstComoTipoDoc.toArray()));
			
			
			//Cargamos los diferentes tipos de teléfono
			List<MostrarCombo> lstComboTipos = new ArrayList<MostrarCombo>();
			lstComboTipos.add(new MostrarCombo(0, Constantes.NOMBRE_TODOS));
			lstComboTipos.add(new MostrarCombo(1, String.valueOf(Constantes.NOMBRE_ADMINISTRADOR)));
			lstComboTipos.add(new MostrarCombo(2, String.valueOf(Constantes.NOMBRE_SECRETARIA)));
			lstComboTipos.add(new MostrarCombo(3, String.valueOf(Constantes.NOMBRE_ASISTENTE)));
			jComboBoxPerfil.setModel(new DefaultComboBoxModel(lstComboTipos.toArray()));
			
			
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
			
		}catch(Exception e){
			throw new OperationErrorDatosFormulario("Error al cargar las listas seleccionables");
		}
		
	}
	
	/*
	 * Parametrizamos el usuario que vamos consultar
	 * @return
	 * @throws OperationErrorDatosFormulario
	 */
	private DTOUsuarioConsulta consultaUsuarios() throws OperationErrorDatosFormulario{
		//Rellenamos el usuario
		DTOUsuarioConsulta dtoUsuario = new DTOUsuarioConsulta();
		UsuarioViewConsulta usuario = new UsuarioViewConsulta();
		if(!"".equalsIgnoreCase(jTextFieldNombre.getText())) usuario.setNombre(jTextFieldNombre.getText());
		if(!"".equalsIgnoreCase(jTextFieldApe.getText())) usuario.setNombre(jTextFieldApe.getText());
		Integer idRol = Integer.parseInt(((MostrarCombo) jComboBoxTipoRol.getSelectedItem()).getID().toString());
		usuario.setIdRol(idRol==0?null:idRol);
		Integer idCentro = Integer.parseInt(((MostrarCombo) jComboBoxCentroDocente.getSelectedItem()).getID().toString());
		usuario.setIdCentro(idCentro==0?null:idCentro);
		if(!"".equalsIgnoreCase(jTextFieldDocuIden.getText())) usuario.setNumeroDocumento(jTextFieldDocuIden.getText());
		Integer idtipoDocumento = Integer.parseInt(((MostrarCombo) jComboBoxTipoRol.getSelectedItem()).getID().toString());
		usuario.setIdDocumentoIdentificacion(idtipoDocumento==0?null:idtipoDocumento);
		if(!"".equalsIgnoreCase(jTextFieldLocalidad.getText())) usuario.setLocalidad(jTextFieldLocalidad.getText());
		if(jRadioButtonDesaNo.isSelected()) usuario.setEstado(Constantes.REGISTRO_ACTIVO);
		if(jRadioButtonDesSi.isSelected()) usuario.setEstado(null);	//De esta manera visualizaremos todos los usuarios activos o inactivos
		if(!"".equalsIgnoreCase(jTextFieldFechaIni.getText())) usuario.setFechaInicio(Utils.transformFecha(jTextFieldFechaIni.getText()));
		if(!"".equalsIgnoreCase(jTextFieldHasta.getText())) usuario.setFechaFin(Utils.transformFecha(jTextFieldHasta.getText()));
		dtoUsuario.setUsuarioViewConsulta(usuario);
		return dtoUsuario;
	}
	
	/*
	 * Limpia el formulario
	 */
	private void limpiaFormulario(){
		ClearForm.clearForm(jPanel2);
	}
	
	
	/*
	 * Mostramos el resultado obtenido
	 */
	private void muestraResultado(List<DTOUsuario> lstDtoUsuario) throws OperationErrorDatosFormulario{
		
		try{
			dtm.getDataVector().removeAllElements();
		     
			Object[][] aobj = new Object[lstDtoUsuario.size()][7];
			int k = 0;
			
			if(lstDtoUsuario != null){
	       	 	
				for(DTOUsuario dtoUsuario : lstDtoUsuario){
					 aobj[k][0] = new String(dtoUsuario.getUsuario().getCodigo());
					 aobj[k][1] = new String(dtoUsuario.getUsuario().getTipoUsuario().toString());
					 aobj[k][2] = new String(dtoUsuario.getUsuario().getNombre());
	                 aobj[k][3] = new String(dtoUsuario.getUsuario().getApellidos());
	                 aobj[k][4] = new String(dtoUsuario.getUsuario().getFechaAlta().toString());
	                 aobj[k][5] = new String(FactoriaUsuario.getDescripcion(dtoUsuario.getUsuario().getTipoUsuario()));
	                 if(dtoUsuario.getDtoCentroDocente() != null){
	                	 aobj[k][6] = new String(dtoUsuario.getDtoCentroDocente().getDtoContacto().getDtoPais().getPais().getNombrePais());
	                 }
	                 k++;
	       	 	}
	       	 
	       	 	if(aobj != null && aobj.length > 0){
	       	 		for(int row = 0; row < aobj.length; row++){
	       	 			dtm.addRow(aobj[row]);
	       	 		}
	       	 	}
			}
		}catch(Exception e){
			throw new OperationErrorDatosFormulario(e.getMessage());
		}
	}
	
	private JLabel getJLabelElimina() {
		if(jLabelElimina == null) {
			jLabelElimina = new JLabel();
			jLabelElimina.setText("Elimina");
			ImageIcon icon = new ImageIcon("imagen/dcib022t.gif");
			jLabelElimina.setIcon(icon);
			jLabelElimina.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		}
		return jLabelElimina;
	}
	
	private JLabel getJLabelModifica() {
		if(jLabelModifica == null) {
			jLabelModifica = new JLabel();
			jLabelModifica.setText("Modifica");
			ImageIcon icon = new ImageIcon("imagen/dcib023t.gif");
			jLabelModifica.setIcon(icon);
			jLabelModifica.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
		return jLabelModifica;
	}

}
