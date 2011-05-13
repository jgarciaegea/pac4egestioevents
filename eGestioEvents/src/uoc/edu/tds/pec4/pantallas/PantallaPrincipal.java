package uoc.edu.tds.pec4.pantallas;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import uoc.edu.tds.pec4.excepciones.OperationErrorBD;
import uoc.edu.tds.pec4.excepciones.OperationErrorRMI;
import uoc.edu.tds.pec4.gestores.GestorDisco;
import uoc.edu.tds.pec4.gestores.GestorRMI;
import uoc.edu.tds.pec4.resources.TDSLanguageUtils;

public class PantallaPrincipal extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JMenuBar barraMenu;
	private JMenu menuMantenimiento;
	private JMenu menuEstadisticas;
	private JMenu menuProgramacionEvento;
	private JMenu menuConexion;
	private JMenu menuSalir;
	private JMenu menuAyuda;
	private JMenuItem cambioPwd;
	private JMenuItem inscripcionEvento;
	private JMenuItem historicoEventos;
	private JMenuItem validarAsistenciaEvento;
	private JMenuItem informePorcentajes;
	private JMenuItem informeIngresos;
	private JMenuItem informeAsistentes;
	private JMenuItem informeEventos;
	private JMenuItem altaUsuario;
	private JMenuItem consultaUsuario;
	private JPanel panelPrincipal;
	private GestorRMI gestorRMI;
	private GestorDisco gestorDB;
	
	public PantallaPrincipal(){
		setSize(784, 600);
	    setResizable(false);
	    setLocationRelativeTo(null);
	    setTitle(TDSLanguageUtils.getMessage("clientePEC4.framePrincipal.titulo"));
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        crearMenuBar();
        setJMenuBar(barraMenu);
        generaEventosPantallaPrincipal();
        connectDB();
        connectRMI();
	}
	
	private void crearMenuBar(){
		try{
			
			System.out.println("Creando Objecto Menu Bar");
			panelPrincipal = new JPanel();
			super.getContentPane().add(panelPrincipal);
			
	        barraMenu = new JMenuBar();
	        menuMantenimiento = new JMenu(TDSLanguageUtils.getMessage("clientePEC4.framePrincipal.titulo.menu1"));
	        menuMantenimiento.setEnabled(true);
	        menuEstadisticas = new JMenu(TDSLanguageUtils.getMessage("clientePEC4.framePrincipal.titulo.menu2"));
	        menuEstadisticas.setEnabled(true);
	        menuProgramacionEvento = new JMenu(TDSLanguageUtils.getMessage("clientePEC4.framePrincipal.titulo.menu3"));
	        menuProgramacionEvento.setEnabled(true);
	        menuConexion = new JMenu(TDSLanguageUtils.getMessage("clientePEC4.framePrincipal.titulo.menu4"));
	        menuConexion.setEnabled(true);
	        menuSalir = new JMenu(TDSLanguageUtils.getMessage("clientePEC4.framePrincipal.titulo.menu5"));
	        menuSalir.setEnabled(true);
	        menuAyuda = new JMenu(TDSLanguageUtils.getMessage("clientePEC4.framePrincipal.titulo.menu6"));
	        menuAyuda.setEnabled(true);
	        
	        cambioPwd = new JMenuItem(TDSLanguageUtils.getMessage("clientePEC4.framePrincipal.titulo.menu4.item1"));	    
	        inscripcionEvento = new JMenuItem(TDSLanguageUtils.getMessage("clientePEC4.framePrincipal.titulo.menu4.item2"));	   
	        historicoEventos = new JMenuItem(TDSLanguageUtils.getMessage("clientePEC4.framePrincipal.titulo.menu4.item3"));	   
	        validarAsistenciaEvento = new JMenuItem(TDSLanguageUtils.getMessage("clientePEC4.framePrincipal.titulo.menu4.item4"));
	        validarAsistenciaEvento.addActionListener(new ActionListener() { 
	        	public void actionPerformed(ActionEvent evt) { 
	        		showPanel(new PantallaAsistencia()); 
	        	} }); 
	        
	        informePorcentajes = new JMenuItem(TDSLanguageUtils.getMessage("clientePEC4.framePrincipal.titulo.menu2.item1"));	    
	        informeIngresos = new JMenuItem(TDSLanguageUtils.getMessage("clientePEC4.framePrincipal.titulo.menu2.item2"));
	        informeAsistentes = new JMenuItem(TDSLanguageUtils.getMessage("clientePEC4.framePrincipal.titulo.menu2.item3"));	   
	        informeEventos= new JMenuItem(TDSLanguageUtils.getMessage("clientePEC4.framePrincipal.titulo.menu2.item4"));
	        
	        //Pantalla Cliente
	        altaUsuario = new JMenuItem("Alta usuario");
	        
	        altaUsuario.addActionListener(new ActionListener() { 
	        	public void actionPerformed(ActionEvent evt) { 
	        		showPanel(new PantallaUsuario(gestorRMI)); 
	        	} }); 
	        
	        consultaUsuario = new JMenuItem("Consulta usuario");
	        
	        menuConexion.add(cambioPwd);
	        menuConexion.add(inscripcionEvento);
	        menuConexion.add(historicoEventos);
	        menuConexion.add(validarAsistenciaEvento);
	               
	        menuEstadisticas.add(informePorcentajes);
	        menuEstadisticas.add(informeIngresos);
	        menuEstadisticas.add(informeAsistentes);
	        menuEstadisticas.add(informeEventos);
	        
	        menuMantenimiento.add(altaUsuario);
	        menuMantenimiento.add(consultaUsuario);
	        
	        barraMenu.add(menuMantenimiento);
	        barraMenu.add(menuEstadisticas);
	        barraMenu.add(menuProgramacionEvento);
	        barraMenu.add(menuConexion);
	        barraMenu.add(menuSalir);
	        barraMenu.add(menuAyuda);
			 
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Método para salir de la pantalla del cliente
	 */
	private void generaEventosPantallaPrincipal(){
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				if (JOptionPane.showConfirmDialog(PantallaPrincipal.this,TDSLanguageUtils.getMessage("ClientePEC4.cerrar"), null, JOptionPane.YES_NO_OPTION) == 0){
					System.exit(0);
					
					//Cerramos la conexión RMI
					try {
						gestorRMI.disconnectRMI();
					} catch (OperationErrorRMI e1) {
						e1.showDialogError();
					}
					
					//Cerramos la conexión BBDD
					try {
						gestorDB.closeConnection();
					} catch (OperationErrorBD e1) {
						e1.showDialogError();
					}
					
				}
			}
		}) ; 
	}
	
	/**
	 * Método genérico que utilizaremos para mostrar las diferentes pantallas
	 * @param <B>
	 * @param pantalla
	 */
	private <B extends Pantallas> void showPanel(B pantalla){
		panelPrincipal.removeAll();
		panelPrincipal.add((Component)pantalla);
		super.pack();
	}
	
	private void connectDB(){
		try {
			gestorDB = new GestorDisco();
		} catch (OperationErrorBD e) {
			e.showDialogError();
		}
	}
	
	private void connectRMI(){
		try {
			gestorRMI = new GestorRMI(gestorDB);
			gestorRMI.connectRMI();
		} catch (OperationErrorRMI e) {
			e.showDialogError();
		} catch (OperationErrorBD e) {
			e.showDialogError();
		}
	}

}
