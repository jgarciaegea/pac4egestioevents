package uoc.edu.tds.pec4.pantallas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
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

import uoc.edu.tds.pec4.beans.CentroDocente;
import uoc.edu.tds.pec4.beans.CentroDocenteViewConsulta;
import uoc.edu.tds.pec4.beans.Usuario;
import uoc.edu.tds.pec4.dtos.DTOCentroDocente;
import uoc.edu.tds.pec4.dtos.DTOCentroDocenteConsulta;
import uoc.edu.tds.pec4.dtos.DTOUniversidad;
import uoc.edu.tds.pec4.excepciones.OperationErrorDatosFormulario;
import uoc.edu.tds.pec4.gestores.GestorRMI;
import uoc.edu.tds.pec4.iface.RemoteInterface;
import uoc.edu.tds.pec4.resources.TDSLanguageUtils;
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
public class PantallaCentroDocenteConsulta extends javax.swing.JPanel implements Pantallas {
	private String[] columnNames = {TDSLanguageUtils.getMessage("clientePEC4.asistenciabyevento.colum1"),TDSLanguageUtils.getMessage("clientePEC4.altausuario.label24"),TDSLanguageUtils.getMessage("clientePEC4.altausuario.label23"), TDSLanguageUtils.getMessage("clientePEC4.consultausuario.label1"), TDSLanguageUtils.getMessage("clientePEC4.altausuario.label9")};
	private DefaultTableModel dtm;
	private static final long serialVersionUID = 1L;
	private JComboBox jComboBoxUniver;
	private JLabel jLabelUniversidad;
	private JButton jButtonAcceptar;
	private JButton jButtonCancelar;
	private JPanel jPanelButtom;
	private JLabel jLabelLocalidad;
	private JLabel jLabelDocIden;
	private JRadioButton jRadioButtonDesaNo;
	private JRadioButton jRadioButtonDesSi;
	private JLabel jLabelActivos;
	private JLabel jLabelModifica;
	private JLabel jLabelElimina;
	private JTable jTableRes;
	private JTextField jTextFieldCP;
	private JScrollPane scrollPane;
	private JLabel jLabelNombre;
	private JTextField jTextFieldNombre;
	private JTextField jTextFieldLocalidad;
	private JPanel jPanel2;
	private RemoteInterface remote;
	private JTextField jTextFieldHasta;
	private JLabel jLabelHasta;
	private JLabel jLabelDesde;
	private JTextField jTextFieldFechaIni;
	private JLabel jLabelFechaAlta;
	private ButtonGroup grupoBu;
	
	public PantallaCentroDocenteConsulta(GestorRMI gestorRMI,RemoteInterface remote1) {
		super();
		this.remote = remote1;
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
			this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(null, TDSLanguageUtils.getMessage("clientePEC4.consultacentro.title"), 0, 0, new Font("Dialog", 1, 12), new Color(51, 51, 51)), null), null));
			this.setPreferredSize(new java.awt.Dimension(784, 624));
			{
				jPanel2 = new JPanel();
				this.add(jPanel2);
				GridBagLayout jPanel2Layout = new GridBagLayout();
				jPanel2Layout.columnWidths = new int[] {142, 222, 7};
				jPanel2Layout.rowHeights = new int[] {28, 28, 28, 28, 28, 28, 28, 28, 0, 0, 107, 0, 0};
				jPanel2Layout.columnWeights = new double[] {0.0, 0.0, 0.1};
				jPanel2Layout.rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
				jPanel2.setPreferredSize(new java.awt.Dimension(649, 350));
				jPanel2.setLayout(jPanel2Layout);
				{
					jTextFieldNombre = new JTextField();
					jPanel2.add(jTextFieldNombre, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 15), 0, 0));
					jTextFieldNombre.setPreferredSize(new java.awt.Dimension(200, 21));
				}
				{
					jLabelNombre = new JLabel();
					jPanel2.add(jLabelNombre, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(4, 0, 0, 0), 0, 0));
					jLabelNombre.setText(TDSLanguageUtils.getMessage("clientePEC4.altausuario.label5"));
					jLabelNombre.setLayout(null);
				}
				{
					jLabelDocIden = new JLabel();
					jPanel2.add(jLabelDocIden, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabelDocIden.setText(TDSLanguageUtils.getMessage("clientePEC4.altausuario.label18"));
					jLabelDocIden.setLayout(null);
				}
				{
					jLabelLocalidad = new JLabel();
					jPanel2.add(jLabelLocalidad, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabelLocalidad.setText(TDSLanguageUtils.getMessage("clientePEC4.altausuario.label9"));
					jLabelLocalidad.setLayout(null);
				}
				{
					
					jTextFieldLocalidad = new JTextField();
					jPanel2.add(jTextFieldLocalidad, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					jTextFieldLocalidad.setPreferredSize(new java.awt.Dimension(200, 21));
				}
				{
					jLabelUniversidad = new JLabel();
					jPanel2.add(jLabelUniversidad, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabelUniversidad.setText(TDSLanguageUtils.getMessage("clientePEC4.altausuario.label23"));
				}
				{
					
					jComboBoxUniver = new JComboBox();
					jPanel2.add(jComboBoxUniver, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					jComboBoxUniver.setPreferredSize(new java.awt.Dimension(200, 26));
					jComboBoxUniver.setOpaque(false);

				}
				{
					jTextFieldCP = new JTextField();
					jPanel2.add(jTextFieldCP, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					jTextFieldCP.setPreferredSize(new java.awt.Dimension(200, 21));
				}
			}
			{
				jPanelButtom = new JPanel();
				this.add(jPanelButtom);
				jPanelButtom.setPreferredSize(new java.awt.Dimension(625, 188));
				jPanelButtom.setBounds(238, 280, 10, 10);
				{
					dtm=new DefaultTableModel();
					{
						for(int i=0;i<columnNames.length;i++){
							dtm.addColumn(columnNames[i]);
						}
						
					}
					
					jTableRes = new JTable(dtm);
					jPanelButtom.add(jTableRes);

					//Ocultamos la columna del id
					Utils.ocultaColumna(jTableRes,0);
					
					jTableRes.setSize(297, 130);
					jTableRes.setPreferredSize(new java.awt.Dimension(582, 193));
					scrollPane=new JScrollPane(jTableRes);
					jPanelButtom.add(scrollPane);
					scrollPane.setVisible(true);

					jLabelFechaAlta = new JLabel();
					jLabelFechaAlta.setText(TDSLanguageUtils.getMessage("clientePEC4.consultausuario.label1"));
					jPanel2.add(jLabelFechaAlta, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					
					jTextFieldFechaIni = new JTextField();
					jTextFieldFechaIni.setPreferredSize(new java.awt.Dimension(100, 21));
					jPanel2.add(jTextFieldFechaIni, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 20), 0, 0));
					
					jLabelDesde = new JLabel();
					jLabelDesde.setText(TDSLanguageUtils.getMessage("clientePEC4.consultausuario.label2"));
					jPanel2.add(jLabelDesde, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 10, 0, 0), 0, 0));
					
					jLabelHasta = new JLabel();
					jLabelHasta.setText(TDSLanguageUtils.getMessage("clientePEC4.consultausuario.label3"));
					jPanel2.add(jLabelHasta, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jTextFieldHasta = new JTextField();
					jTextFieldHasta.setPreferredSize(new java.awt.Dimension(100, 21));
					jPanel2.add(jTextFieldHasta, new GridBagConstraints(2, 7, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 7, 0, 0), 0, 0));
					jPanel2.add(getJLabelElimina(), new GridBagConstraints(2, 10, 1, 1, 0.0, 0.0, GridBagConstraints.SOUTHEAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 45), 0, 0));
					jPanel2.add(getJLabelModifica(), new GridBagConstraints(2, 10, 1, 1, 0.0, 0.0, GridBagConstraints.SOUTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 60), 0, 0));
					jPanel2.add(getJLabelActivos(), new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jPanel2.add(getJRadioButtonDesSi(), new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
					jPanel2.add(getJRadioButtonDesaNo(), new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, new Insets(0, 0, 0, 80), 0, 0));
					scrollPane.setPreferredSize(new java.awt.Dimension(600, 150));
				}
				{
					jButtonAcceptar = new JButton();
					jPanelButtom.add(jButtonAcceptar);
					jButtonAcceptar.setText(TDSLanguageUtils.getMessage("clientePEC4.altausuario.boton1"));
					jButtonAcceptar.setLayout(null);
					jButtonAcceptar.setBounds(277, -16, 64, 21);
					
					jButtonAcceptar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								validaFormulario();
								cargaListadoCentroDocentes();
							} catch (OperationErrorDatosFormulario e3) {
								e3.showDialogError(jPanel2);
							}
						}
					});
					
				}
				{
					jButtonCancelar = new JButton();
					jPanelButtom.add(jButtonCancelar);
					jButtonCancelar.setText(TDSLanguageUtils.getMessage("clientePEC4.consultausuario.boton1"));
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
			 * Cuando clique sobre el botón modificar recogeremos el id del centro y su información
			 */
			jLabelElimina.addMouseListener(new MouseListener(){
				public void mouseClicked(MouseEvent e) {
					if(jTableRes.getSelectedRow() == -1){
						Utils.mostraMensajeInformacion(jPanel2, TDSLanguageUtils.getMessage("clientePEC4.consultausuario.INFO.MSG1"), TDSLanguageUtils.getMessage("clientePEC4.consultacentro.title"));
					}else{
						try {
							DTOCentroDocente dtoCentroDocente = getRecuperaCentroSeleccionado();
							//Comprobamos que no hayan usuarios inscritos a este centro
							Usuario usuario = new Usuario();
							usuario.setIdCentro(dtoCentroDocente.getCentroDocente().getIdCentro());
							if(!remote.usuarioCentrosVinculados(usuario)){
								remote.bajaCentroDocente(dtoCentroDocente);
								cargaListadoCentroDocentes();
								Utils.mostraMensajeInformacion(jPanel2,TDSLanguageUtils.getMessage("clientePEC4.bajacentrodocente.MSG"), TDSLanguageUtils.getMessage("clientePEC4.bajacentrodocente.title"));
							}else{
								Utils.mostraMensajeInformacion(jPanel2,TDSLanguageUtils.getMessage("clientePEC4.bajacentrodocente.MSG2"), TDSLanguageUtils.getMessage("clientePEC4.bajacentrodocente.title"));
							}
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
			 * Modificamos el centro docente
			 */
			jLabelModifica.addMouseListener(new MouseListener(){
				public void mouseClicked(MouseEvent e) {
					if(jTableRes.getSelectedRow() == -1){
						Utils.mostraMensajeInformacion(jPanel2, TDSLanguageUtils.getMessage("clientePEC4.consultausuario.INFO.MSG1"), TDSLanguageUtils.getMessage("clientePEC4.consultacentro.title"));
					}else{
						try {
							DTOCentroDocente dtoCentroDocente = getRecuperaCentroSeleccionado();
							goPantallaCentroDocente(dtoCentroDocente);
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
				throw new OperationErrorDatosFormulario(TDSLanguageUtils.getMessage("clientePEC4.altacentro.ERROR"));
			}catch(OperationErrorDatosFormulario ex){
				ex.showDialogError(jPanel2);
			}
		}
	}
	
	/*
	 * Recupera el centro docente seleccionado de la tabla
	 */
	private DTOCentroDocente getRecuperaCentroSeleccionado() throws OperationErrorDatosFormulario{
		try {
			@SuppressWarnings("unchecked")
			List<Object> lstRes = (Vector<Object>) dtm.getDataVector().get(jTableRes.getSelectedRow());
			DTOCentroDocente dtoCentroDocente = obtenCentroDocente(Integer.parseInt(lstRes.get(0).toString()));
			return dtoCentroDocente;
		} catch (NumberFormatException e1) {
			throw new OperationErrorDatosFormulario(TDSLanguageUtils.getMessage("clientePEC4.error11"));
		} catch (Exception e1) {
			throw new OperationErrorDatosFormulario(TDSLanguageUtils.getMessage("clientePEC4.altacentro.MSG5"));
		}
	}
	
	
	/*
	 * Abre la pantalla de centro docente
	 */
	private void goPantallaCentroDocente(DTOCentroDocente dtoCentroDocente){
		this.setBorder(null);
		this.removeAll();
		this.setAlignmentX(LEFT_ALIGNMENT);
		this.setAlignmentY(TOP_ALIGNMENT);
		this.add((Component)new PantallaCentroDocente(remote, dtoCentroDocente));
		this.repaint();
		this.revalidate();
		this.updateUI();
	}
	
	
	/*
	 * Obten la información de un centroDocente
	 */
	private DTOCentroDocente obtenCentroDocente(Integer idCentroDocente) throws OperationErrorDatosFormulario{
		try{
			DTOCentroDocente dtoCentroDocente = new DTOCentroDocente();
			CentroDocente centroDocente = new CentroDocente();
			centroDocente.setIdCentro(idCentroDocente);
			dtoCentroDocente.setCentroDocente(centroDocente);
			return dtoCentroDocente;
		}catch(Exception e){
			throw new OperationErrorDatosFormulario(TDSLanguageUtils.getMessage("clientePEC4.altacentro.MSG6"));
		}
	}
	
	/*
	 * Carga el listado de centros docentes
	 */
	private void cargaListadoCentroDocentes() throws OperationErrorDatosFormulario{
		try{
			dtm.getDataVector().removeAllElements();
			List<DTOCentroDocente> lstDtoCentroDoc = remote.consultaCentrosDocentes(consultaCentroDocente());
			if(lstDtoCentroDoc == null || lstDtoCentroDoc.isEmpty()){
				actualizaTabla();
				Utils.mostraMensajeInformacion(jPanel2,TDSLanguageUtils.getMessage("clientePEC4.consultausuario.INFO.MSG4"),TDSLanguageUtils.getMessage("clientePEC4.consultacentro.title2"));
			}else{
				muestraResultado(lstDtoCentroDoc);
				actualizaTabla();
			}
		}catch(Exception e){
			throw new OperationErrorDatosFormulario(TDSLanguageUtils.getMessage("clientePEC4.altacentro.MSG7"));
		}
		
	}
	
	/**
	 * Método que valida los datos introducidos en el formulario
	 * @throws OperationErrorDatosFormulario
	 */
	private void validaFormulario() throws OperationErrorDatosFormulario{
		try{
			
			if(!"".equalsIgnoreCase(jTextFieldHasta.getText()) && "".equalsIgnoreCase(jTextFieldFechaIni.getText())){
				 throw new Exception(TDSLanguageUtils.getMessage("clientePEC4.calendarioeventos.ERROR1"));
			}
			
			if(!"".equalsIgnoreCase(jTextFieldFechaIni.getText())){
				if(!Utils.parseaFecha(jTextFieldFechaIni.getText())) throw new Exception(Utils.MESSAGE_ERROR + " fecha inicio" + Utils.MESSAGE_FECHA );
			}
			
			if(!"".equalsIgnoreCase(jTextFieldHasta.getText())){
				if(!Utils.parseaFecha(jTextFieldHasta.getText())) throw new Exception(Utils.MESSAGE_ERROR + " fecha fin" + Utils.MESSAGE_FECHA );
			}
			
		}catch(Exception e){
			throw new OperationErrorDatosFormulario(TDSLanguageUtils.getMessage("clientePEC4.error14"));
		}
			
	}
	
	/**
	 * Método para cargar los combos de la pantalla centros docentes
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
			
		}catch(Exception e){
			throw new OperationErrorDatosFormulario("Error al cargar los combos relacionados con la información del centro docente");
		}
		
	}
	
	
	/**
	 * Consulta un centro Docente
	 * @return
	 * @throws OperationErrorDatosFormulario
	 */
	private DTOCentroDocenteConsulta consultaCentroDocente() throws OperationErrorDatosFormulario{
		
		DTOCentroDocenteConsulta dtoConsultaCenDoc = new DTOCentroDocenteConsulta();
		CentroDocenteViewConsulta centroDocente = new CentroDocenteViewConsulta();
		
		if(!"".equalsIgnoreCase(jTextFieldNombre.getText())) centroDocente.setNombre(jTextFieldNombre.getText());
		if(!"".equalsIgnoreCase(jTextFieldLocalidad.getText())) centroDocente.setLocalidad(jTextFieldLocalidad.getText());
		if(!"".equalsIgnoreCase(jTextFieldCP.getText())) centroDocente.setCp(Integer.parseInt(jTextFieldCP.getText()));
		
		if(jRadioButtonDesaNo.isSelected()) centroDocente.setEstado(Constantes.REGISTRO_ACTIVO);
		if(jRadioButtonDesSi.isSelected()) centroDocente.setEstado(null);	//De esta manera visualizaremos todos los usuarios activos o inactivos
		
		if(!"".equalsIgnoreCase(jTextFieldFechaIni.getText())) centroDocente.setFechaInicio(Utils.transformFecha(jTextFieldFechaIni.getText()));
		if(!"".equalsIgnoreCase(jTextFieldHasta.getText())) centroDocente.setFechaFin(Utils.transformFecha(jTextFieldHasta.getText()));
		
		Integer idUniversidad = Integer.parseInt(((MostrarCombo) jComboBoxUniver.getSelectedItem()).getID().toString());
		centroDocente.setIdUniversidad(idUniversidad==0?null:idUniversidad);	
		
		dtoConsultaCenDoc.setCentroDocenteView(centroDocente);
		
		return dtoConsultaCenDoc;
	}
	
	/*
	 * Limpia el formulario
	 */
	private void limpiaFormulario(){
		ClearForm.clearForm(jPanel2);
		dtm.getDataVector().removeAllElements();
		actualizaTabla();
	}
	
	private void actualizaTabla(){
		jTableRes.repaint();
		jTableRes.revalidate();
		jTableRes.updateUI();
	}
	
	
	/*
	 * Mostramos el resultado obtenido
	 */
	private void muestraResultado(List<DTOCentroDocente> lstDtoCentroDoc) throws OperationErrorDatosFormulario{
		
		try{
			dtm.getDataVector().removeAllElements();
		     
			Object[][] aobj = new Object[lstDtoCentroDoc.size()][5];
			int k = 0;
			
			if(lstDtoCentroDoc != null){
	       	 	
				for(DTOCentroDocente dtoCentroDocente : lstDtoCentroDoc){
					 aobj[k][0] = new String(dtoCentroDocente.getCentroDocente().getIdCentro().toString());
					 aobj[k][1] = new String(dtoCentroDocente.getCentroDocente().getNombre());
					 aobj[k][2] = new String(dtoCentroDocente.getDtoUniversidad().getUniversidad().getNombre());
	                 aobj[k][3] = new String(Utils.convertFecha(dtoCentroDocente.getCentroDocente().getFechaAlta().toString()));
	                 aobj[k][4] = new String(dtoCentroDocente.getDtoContacto().getContacto().getLocalidad());
	                 k++;
	       	 	}
	       	 
	       	 	if(aobj != null && aobj.length > 0){
	       	 		for(int row = 0; row < aobj.length; row++){
	       	 			dtm.addRow(aobj[row]);
	       	 		}
	       	 	}
			}
		}catch(Exception e){
			throw new OperationErrorDatosFormulario(TDSLanguageUtils.getMessage("clientePEC4.altacentro.MSG8"));
		}
	}
	
	private JLabel getJLabelElimina() {
		if(jLabelElimina == null) {
			jLabelElimina = new JLabel();
			jLabelElimina.setText(TDSLanguageUtils.getMessage("clientePEC4.consultausuario.label8"));
			ImageIcon icon = new ImageIcon("imagen/dcib022t.gif");
			jLabelElimina.setIcon(icon);
			jLabelElimina.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		}
		return jLabelElimina;
	}
	
	private JLabel getJLabelModifica() {
		if(jLabelModifica == null) {
			jLabelModifica = new JLabel();
			jLabelModifica.setText(TDSLanguageUtils.getMessage("clientePEC4.consultausuario.label7"));
			ImageIcon icon = new ImageIcon("imagen/dcib023t.gif");
			jLabelModifica.setIcon(icon);
			jLabelModifica.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
		return jLabelModifica;
	}
	
	private JLabel getJLabelActivos() {
		if(jLabelActivos == null) {
			jLabelActivos = new JLabel();
			jLabelActivos.setText(TDSLanguageUtils.getMessage("clientePEC4.consultausuario.label4"));
		}
		return jLabelActivos;
	}
	
	private JRadioButton getJRadioButtonDesSi() {
		if(jRadioButtonDesSi == null) {
			jRadioButtonDesSi = new JRadioButton();
			jRadioButtonDesSi.setText(TDSLanguageUtils.getMessage("clientePEC4.consultausuario.label5"));
		}
		return jRadioButtonDesSi;
	}
	
	private JRadioButton getJRadioButtonDesaNo() {
		if(jRadioButtonDesaNo == null) {
			jRadioButtonDesaNo = new JRadioButton();
			jRadioButtonDesaNo.setText(TDSLanguageUtils.getMessage("clientePEC4.consultausuario.label6"));
		}
		return jRadioButtonDesaNo;
	}

}
