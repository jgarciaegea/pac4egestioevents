/**
 * 
 */
package uoc.edu.tds.pec4.pantallas;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import uoc.edu.tds.pec4.resources.TDSLanguageUtils;

/**
 * @author ML019882
 *
 */
public abstract class PanelComun extends JPanel  {

	/**
	 * Lista de Componentes
	 */
	private static final long serialVersionUID = 1L; 
	private Map<String,JButton> botones;
	private Map<String,JTextField> cajas ;
	private Map<String,JLabel> titulos;
	private Map<String,JComboBox> combos;
	private JTable tablaResultados = null;
	private JScrollPane panelScroll = null;
	
	/**
	 * Constructor
	 */
	
	
	public PanelComun(String titulo,Integer x, Integer y ) {
		super();
		System.out.println("Constructor Panel Generico");
		botones = new HashMap<String, JButton>();
		cajas = new HashMap<String, JTextField>();
        titulos = new HashMap<String, JLabel>();
        combos = new HashMap<String, JComboBox>();
		System.out.println("Variables inicializadas");
		this.setLayout(null);
		this.setPreferredSize(new Dimension(x, y));
		this.setSize(new Dimension(x, y));
		this.setBorder(BorderFactory.createCompoundBorder(
		        BorderFactory.createTitledBorder(TDSLanguageUtils.getMessage(titulo)), 
		        BorderFactory.createEmptyBorder(10, 10, 5, 5)));
	}
	
	/**
	 * Constructor Boton
	 */	
	
	protected JButton crearBoton(Integer x,Integer y,Integer ancho, Integer alto,String clave,String nombre){
		System.out.println("Creando Boton......");
		 JButton boton = new JButton();
		 boton.setBounds(new Rectangle(x, y, ancho, alto));
		 boton.setText(TDSLanguageUtils.getMessage(clave));
		 this.addBoton(boton,nombre);
		 return boton;
	}

	/**
	 * Anadir Boton a la lista y al Panel
	 */
	
	protected void addBoton(JButton boton,String name){

		System.out.println("Anyadir Boton al Hahsmap......");
		this.botones.put(name,boton);
		System.out.println("Anyadir Boton al Panel......");
		this.add(boton,null);
		
	}
	
	/**
	 * Devuelve un Boton de la lista buscando por la clave (nombre del boton)
	 */
	
	public JButton findBoton(String name){
		return(this.botones.get(name));
		
	}
	
	/**
	 * Borra un Boton de la lista buscando por la clave (nombre del boton)
	 */
	
	protected void removeBoton(String name){
		this.botones.remove(name);
	}	
	
	/**
	 * Constructor JComboBox
	 */	
	
	protected JComboBox crearCombo(Integer x,Integer y,Integer ancho, Integer alto,String nombre,Vector vector){
		System.out.println("Creando JComboBox......");
		JComboBox combo = new JComboBox(vector);
		combo.setBounds(new Rectangle(x, y, ancho, alto));
		 this.addCombo(combo,nombre);
		 return combo;
	}

	/**
	 * Anadir JComboBox a la lista y al Panel
	 */
	
	protected void addCombo(JComboBox combo,String name){

		System.out.println("Anyadir JComboBox al Hahsmap......");
		this.combos.put(name,combo);
		System.out.println("Anyadir JComboBox al Panel......");
		this.add(combo,null);
		
	}
	
	/**
	 * Devuelve un JComboBox de la lista buscando por la clave (nombre del boton)
	 */
	
	public JComboBox findCombo(String combo){
		return(this.combos.get(combo));
	}
	
	/**
	 * Borra un Boton de la lista buscando por la clave (nombre del boton)
	 */
	
	protected void removeCombo(String name){
		this.combos.remove(name);
	}	
	

	/**
	 * Constructor JTextField
	 */	
	
	protected JTextField crearTextField(Integer x,Integer y,Integer ancho, Integer alto,/*String clave,*/String nombre){
		System.out.println("Creando JTextField......");
		JTextField caja = new JTextField();
		 caja.setBounds(new Rectangle(x, y, ancho, alto));
		// caja.setText(TDSLanguageUtils.getMessage(clave));
		 this.addTextField(caja,nombre);
		 return caja;
	}
	
	
	
	protected void addTextField(JTextField caja,String name){

		this.cajas.put(name,caja);
		System.out.println("Anyadir JTextField al Panel......");
		this.add(caja,null);
		
	}
	
	public JTextField findTextField(String name){
		return(this.cajas.get(name));
		
	}
	
	protected void removeTextField(String name){
		this.cajas.remove(name);
	}
	
	/**
	 * Constructor Titulo
	 */	
	
	protected JLabel crearTitulo(Integer x,Integer y,Integer ancho, Integer alto,String clave,String nombre){
		System.out.println("Creando JLabel......");
		JLabel titulo = new JLabel();
		titulo.setBounds(new Rectangle(x, y, ancho, alto));
		titulo.setText(TDSLanguageUtils.getMessage(clave));
		 this.addJLabel(titulo,nombre);
		 return titulo;
	}	
	
	
	protected void addJLabel(JLabel titulo,String name){

		this.titulos.put(name,titulo);
		System.out.println("Anyadir addJLabel al Panel......");
		this.add(titulo,null);
		
	}
	
	public JLabel findJLabel(String name){
		return(this.titulos.get(name));
		
	}
	
	protected void removeJLabel(String name){
		this.titulos.remove(name);
	}
	
	protected void crearTabla(Integer x,Integer y, Integer ancho, Integer alto){
		this.tablaResultados = new JTable();
		this.panelScroll = new JScrollPane();
		panelScroll.setBounds(new Rectangle(x, y, ancho, alto));		
		tablaResultados.setBounds(new Rectangle(x+10, y+10, ancho-30, alto-30));
		this.add(tablaResultados,null);
		this.add(panelScroll,null);
	}

	/**
	 * @return the tablaResultados
	 */
	public JTable getTablaResultados() {
		return tablaResultados;
	}

	/**
	 * @param tablaResultados the tablaResultados to set
	 */
	public void setTablaResultados(JTable tablaResultados) {
		this.tablaResultados = tablaResultados;
	}

	/**
	 * @return the panelScroll
	 */
	public JScrollPane getPanelScroll() {
		return panelScroll;
	}

	/**
	 * @param panelScroll the panelScroll to set
	 */
	public void setPanelScroll(JScrollPane panelScroll) {
		this.panelScroll = panelScroll;
	}
	
	
	
	

}