package uoc.edu.tds.pec4.pantallas;

import uoc.edu.tds.pec4.beans.CentroDocente;
import uoc.edu.tds.pec4.beans.TipoEvento;
import uoc.edu.tds.pec4.beans.Universidad;
import uoc.edu.tds.pec4.dtos.DTOCentroDocente;
import uoc.edu.tds.pec4.dtos.DTOTipoEvento;
import uoc.edu.tds.pec4.dtos.DTOUniversidad;
import uoc.edu.tds.pec4.excepciones.OperationErrorBD;
import uoc.edu.tds.pec4.excepciones.OperationErrorDatosFormulario;
import uoc.edu.tds.pec4.excepciones.OperationErrorRMI;
import uoc.edu.tds.pec4.gestores.GestorCentroDocente;
import uoc.edu.tds.pec4.gestores.GestorDisco;
import uoc.edu.tds.pec4.gestores.GestorRMI;
import uoc.edu.tds.pec4.gestores.GestorTipoEvento;
import uoc.edu.tds.pec4.gestores.GestorUniversidad;
import uoc.edu.tds.pec4.iface.RemoteInterface;
import uoc.edu.tds.pec4.utils.ClearForm;
import uoc.edu.tds.pec4.utils.MostrarCombo;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

/**
 * 
 * @author SusanaUOC
 *
 */
public class PantallaEstadisticasIngresos extends javax.swing.JPanel implements Pantallas {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private RemoteInterface remote;

	private JPanel jPanelIngresos = null;  //  @jve:decl-index=0:visual-constraint="1,127"

	private JPanel jPanelCriteris = null;  //  @jve:decl-index=0:visual-constraint="1,-6"

	private JLabel jLabelUniversidad = null;

	private JComboBox jComboBoxUniversidad = null;

	private ArrayList lstComboUniv = null;

	private JLabel jLabelCentroDocente = null;

	private JComboBox jComboBoxCentroDocente = null;

	private ArrayList lstComboCentroDocente = null;

	private JLabel jLabelTipoEvento = null;

	private JComboBox jComboBoxTipoEvento = null;

	private ArrayList lstComboTipoEvento = null;

	private JTextField jTextIngresoMayor = null;

	private JLabel jLabelIngresoMayor = null;

	private JTextField jTextIngresoMenor = null;

	private JLabel jLabelIngresoMenor = null;

	private JScrollPane jScrollPaneIngresos = null;
	private DefaultTableModel dtm=null;
	private String[] columnNames = {"Universidad", "Centro", "Tipo Evento","Ingresos"};

	private JTable jTableIngresos = null;

	private JPanel jPanelButtons = null;  //  @jve:decl-index=0:visual-constraint="3,389"
	
	private JButton jButtonLimpiar = null;

	private JButton jButtonBuscar = null;
	
	private JButton jButtonImprimir = null;

	

	/**
	 * 
	 * @param gestorDB
	 * @param gestorRMI
	 */
	public PantallaEstadisticasIngresos(GestorRMI gestorRMI,RemoteInterface remote1) {
		super();
		//this.gestorDB = gestorDB;
		try {
			remote = gestorRMI.lookup();
			System.out.print("Para quitar el warning que sale si no se utiliza es provisional " + remote.toString());
		} catch (OperationErrorRMI e) {
			e.showDialogError();
		}
		initGUI();
	}
	/**
	 * 
	 */
	private void initGUI() {
		try{
		this.setPreferredSize(new java.awt.Dimension(784, 565));
		{
			jPanelCriteris = new JPanel();
			this.add(jPanelCriteris);
			jPanelCriteris.setLayout(new GridBagLayout());
			jPanelCriteris.setPreferredSize(new Dimension(724, 130));
			
			{
				jLabelIngresoMenor = new JLabel();
				jLabelIngresoMenor.setLayout(null);
				jLabelIngresoMenor.setText("Ingreso Menor");
			}
			{	
				jLabelIngresoMayor = new JLabel();
				jLabelIngresoMayor.setLayout(null);
				jLabelIngresoMayor.setText("Ingreso Mayor");
			}
			{	
				jLabelTipoEvento = new JLabel();
				jLabelTipoEvento.setLayout(null);
				jLabelTipoEvento.setText("Tipo de Evento");
				// Carga los tipos de eventos existentes	
		/*					
					List<DTOTipoEvento> lstDtoTipoEvento = remote.getTiposEventos();
					List<MostrarCombo> lstComboTipoEvento = new ArrayList<MostrarCombo>();
					if(lstDtoTipoEvento != null){
						for(DTOTipoEvento dtoTipoEvRec : lstDtoTipoEvento){
							lstComboTipoEvento.add(new MostrarCombo(dtoTipoEvRec.getTipoEvento().getIdTipoEvento(),
									dtoTipoEvRec.getTipoEvento().getDescripcion()));
						}
					}
					getJComboBoxTipoEvento();
			*/		
			}
			{
				jLabelUniversidad = new JLabel();
				jLabelUniversidad.setText("Universidad");
				//Carga la lista de Universidades existentes
				
				List<DTOUniversidad> lstDtoUniversidad = remote.getUniversidades();
				List<MostrarCombo> lstComboUniv = new ArrayList<MostrarCombo>();
				if(lstDtoUniversidad != null){
					for(DTOUniversidad dtoUniverRec : lstDtoUniversidad){
						lstComboUniv.add(new MostrarCombo(dtoUniverRec.getUniversidad().getIdUniversidad(),
								dtoUniverRec.getUniversidad().getNombre()));
					}
				}
				getJComboBoxUniversidad();	
				jComboBoxUniversidad.addItemListener(new ItemListener(){
					
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

				jComboBoxCentroDocente = new JComboBox();	
				jComboBoxCentroDocente.setPreferredSize(new java.awt.Dimension(200, 21));
				
				//Empezamos seleccionando el primer objeto cargado
				if(jComboBoxUniversidad.getItemCount() > 0) rellenaCentrosDocentes(((MostrarCombo)jComboBoxUniversidad.getSelectedItem()).getID());
				
				jComboBoxUniversidad.addItemListener(new ItemListener(){
					
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
			jPanelCriteris.add(jLabelUniversidad, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(4, 0, 0, 0), 0, 0));
			jPanelCriteris.add(getJComboBoxUniversidad(), new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.SOUTHWEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 15), 0, 0));
			jPanelCriteris.add(jLabelCentroDocente, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(6, 0, 0, 0), 0, 0));
			jPanelCriteris.add(getJComboBoxCentroDocente(), new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(2, 8, 0, 0), 0, 0));
			jPanelCriteris.add(jLabelTipoEvento, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			jPanelCriteris.add(getJComboBoxTipoEvento(), new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));		
			jPanelCriteris.add(getJTextIngresoMayor(), new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
			jPanelCriteris.add(jLabelIngresoMayor, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
			jPanelCriteris.add(getJTextIngresoMenor(), new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
			jPanelCriteris.add(jLabelIngresoMenor, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(3, 0, 0, 0), 0, 0));
			
		}
		{
			jPanelIngresos = new JPanel();
			this.add(jPanelIngresos);
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.weighty = 1.0;
			gridBagConstraints.gridx = 0;
			
			//array de String's con los títulos de las columnas
	        dtm= new DefaultTableModel();
	        JTable jtable = new JTable(dtm);
	        
	        for(int i=0;i<columnNames.length;i++){
	        	dtm.addColumn(columnNames[i]);
	        }
	       
	        jScrollPaneIngresos = new JScrollPane(jtable);
			
			jPanelIngresos.setLayout(new GridBagLayout());
			jPanelIngresos.setSize(new Dimension(724, 255));
			jPanelIngresos.add(getJScrollPaneIngresos(), gridBagConstraints);
			
		}
		{
			jPanelButtons = new JPanel();
			this.add(jPanelButtons);
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(6, 0, 0, 0), 0, 0);
			gridBagConstraints5.insets = new Insets(6, 0, 0, 56);
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(6, 0, 0, 0), 0, 0);
			gridBagConstraints4.insets = new Insets(6, 0, 0, 152);
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(6, 0, 0, 0), 0, 0);
			gridBagConstraints3.insets = new Insets(2, 0, 0, 0);
			gridBagConstraints3.anchor = GridBagConstraints.SOUTHEAST;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(4, 0, 0, 0), 0, 0);
			gridBagConstraints2.anchor = GridBagConstraints.WEST;
			gridBagConstraints2.insets = new Insets(4, 0, 0, 180);
			gridBagConstraints2.gridheight = 1;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.gridy = 0;			
			jPanelButtons.setLayout(new GridBagLayout());
			jPanelButtons.setSize(new Dimension(722, 39));
			jPanelButtons.add(getJButtonBuscar(), gridBagConstraints2);
			jPanelButtons.add(getJButtonLimpiar(), gridBagConstraints4);
			jPanelButtons.add(getJButtonImprimir(), gridBagConstraints5);
		
		}
				
		} catch (Exception e) {
			try{
				throw new OperationErrorDatosFormulario(e.getMessage());
			}catch(OperationErrorDatosFormulario ex){
				ex.showDialogError(jPanelCriteris);
			}
		  }
	}
	
		

	/**
	 * Rellena los centros docentes dependiendo de la Universidad seleccionada
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
	 * This method initializes jComboBoxUniversidad	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBoxUniversidad() {
		if (jComboBoxUniversidad == null) {
			DefaultComboBoxModel jComboBoxUniverModel = new DefaultComboBoxModel(getLstComboUniv().toArray());
			jComboBoxUniversidad = new JComboBox();
			jComboBoxUniversidad.setPreferredSize(new Dimension(200, 21));
			jComboBoxUniversidad.setModel(jComboBoxUniverModel);
		}
		return jComboBoxUniversidad;
	}
	/**
	 * This method initializes lstComboUniv	
	 * 	
	 * @return java.util.ArrayList	
	 */
	private ArrayList getLstComboUniv() {
		if (lstComboUniv == null) {
			lstComboUniv = new ArrayList();
		}
		return lstComboUniv;
	}
	/**
	 * This method initializes jComboBoxCentroDocente	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBoxCentroDocente() {
		if (jComboBoxCentroDocente == null) {
			DefaultComboBoxModel jComboBoxCentroDocModel = new DefaultComboBoxModel(getLstComboCentroDocente().toArray());
			jComboBoxCentroDocente = new JComboBox();
			jComboBoxCentroDocente.setPreferredSize(new Dimension(200, 21));
			jComboBoxCentroDocente.setModel(jComboBoxCentroDocModel);
		}
		return jComboBoxCentroDocente;
	}
	/**
	 * This method initializes lstComboCentroDocente	
	 * 	
	 * @return java.util.ArrayList	
	 */
	private ArrayList getLstComboCentroDocente() {
		if (lstComboCentroDocente == null) {
			lstComboCentroDocente = new ArrayList();
		}
		return lstComboCentroDocente;
	}
	/**
	 * This method initializes jComboBoxTipoEvento	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBoxTipoEvento() {
		if (jComboBoxTipoEvento == null) {
			DefaultComboBoxModel jComboBoxTipoEvModel = new DefaultComboBoxModel(getLstComboTipoEvento().toArray());
			jComboBoxTipoEvento = new JComboBox();
			jComboBoxTipoEvento.setPreferredSize(new Dimension(200, 21));
			jComboBoxTipoEvento.setModel(jComboBoxTipoEvModel);
		}
		return jComboBoxTipoEvento;
	}
	/**
	 * This method initializes lstComboTipoEvento	
	 * 	
	 * @return java.util.ArrayList	
	 */
	private ArrayList getLstComboTipoEvento() {
		if (lstComboTipoEvento == null) {
			lstComboTipoEvento = new ArrayList();
		}
		return lstComboTipoEvento;
	}
	/**
	 * This method initializes jTextIngresoMayor	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextIngresoMayor() {
		if (jTextIngresoMayor == null) {
			jTextIngresoMayor = new JTextField();
			jTextIngresoMayor.setPreferredSize(new Dimension(200, 21));
		}
		return jTextIngresoMayor;
	}
	/**
	 * This method initializes jTextIngresoMenor	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextIngresoMenor() {
		if (jTextIngresoMenor == null) {
			jTextIngresoMenor = new JTextField();
			jTextIngresoMenor.setPreferredSize(new Dimension(200, 21));
		}
		return jTextIngresoMenor;
	}
	/**
	 * This method initializes jScrollPaneIngresos	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPaneIngresos() {
		if (jScrollPaneIngresos == null) {
			jScrollPaneIngresos = new JScrollPane();
			jScrollPaneIngresos.setViewportView(getJTableIngresos());
		}
		return jScrollPaneIngresos;
	}
	/**
	 * This method initializes jTableIngresos	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTableIngresos() {
		if (jTableIngresos == null) {
			jTableIngresos = new JTable();
		}
		return jTableIngresos;
	}

	/**
	 * This method initializes jButtonBuscar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonBuscar() {
		if (jButtonBuscar == null) {
			jButtonBuscar = new JButton();
			jButtonBuscar.setText("Buscar");
		}
		return jButtonBuscar;
	}
	/**
	 * This method initializes jButtonLimpiar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonLimpiar() {
		if (jButtonLimpiar == null) {
			jButtonLimpiar = new JButton();
			jButtonLimpiar.setText("Limpiar");
		}
		return jButtonLimpiar;
	}
	/**
	 * This method initializes jButtonImprimir	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonImprimir() {
		if (jButtonImprimir == null) {
			jButtonImprimir = new JButton();
			jButtonImprimir.setText("Imprimir");
		}
		return jButtonImprimir;
	}

}
