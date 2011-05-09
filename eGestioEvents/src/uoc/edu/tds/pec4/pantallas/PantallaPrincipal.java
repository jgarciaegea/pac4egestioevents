package uoc.edu.tds.pec4.pantallas;

import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import uoc.edu.tds.pec4.resources.TDSLanguageUtils;

public class PantallaPrincipal extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuBar barraMenu;
	private JMenu menuMantenimiento,menuEstadisticas,menuProgramacionEvento,menuConexion,menuSalir,menuAyuda = null;
	private JMenuItem CambioPwd,InscripcionEvento,HistoricoEventos,ValidarAsistenciaEvento = null;
	private JMenuItem InformePorcentajes,InformeIngresos, InformeAsistentes, InformeEventos = null;
	
	public PantallaPrincipal(){
		setSize(700, 600);
	    setLocation(new Point(320, 200));
	    setResizable(false);
	    setTitle(TDSLanguageUtils.getMessage("clientePEC4.framePrincipal.titulo"));
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        crearMenuBar();
        setJMenuBar(barraMenu);
        generaEventosPantallaPrincipal();
	}
	
	private void crearMenuBar(){
		try{
			System.out.println("Creando Objecto Menu Bar");
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
	        
	        CambioPwd = new JMenuItem(TDSLanguageUtils.getMessage("clientePEC4.framePrincipal.titulo.menu4.item1"));	    
	        InscripcionEvento = new JMenuItem(TDSLanguageUtils.getMessage("clientePEC4.framePrincipal.titulo.menu4.item2"));	   
	        HistoricoEventos = new JMenuItem(TDSLanguageUtils.getMessage("clientePEC4.framePrincipal.titulo.menu4.item3"));	   
	        ValidarAsistenciaEvento = new JMenuItem(TDSLanguageUtils.getMessage("clientePEC4.framePrincipal.titulo.menu4.item4"));
	        
	        InformePorcentajes = new JMenuItem(TDSLanguageUtils.getMessage("clientePEC4.framePrincipal.titulo.menu2.item1"));	    
	        InformeIngresos = new JMenuItem(TDSLanguageUtils.getMessage("clientePEC4.framePrincipal.titulo.menu2.item2"));
	        InformeAsistentes = new JMenuItem(TDSLanguageUtils.getMessage("clientePEC4.framePrincipal.titulo.menu2.item3"));	   
	        InformeEventos= new JMenuItem(TDSLanguageUtils.getMessage("clientePEC4.framePrincipal.titulo.menu2.item4"));
	        
	        menuConexion.add(CambioPwd);
	        menuConexion.add(InscripcionEvento);
	        menuConexion.add(HistoricoEventos);
	        menuConexion.add(ValidarAsistenciaEvento);
	               
	        menuEstadisticas.add(InformePorcentajes);
	        menuEstadisticas.add(InformeIngresos);
	        menuEstadisticas.add(InformeAsistentes);
	        menuEstadisticas.add(InformeEventos);
	        
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
	
	public void generaEventosPantallaPrincipal(){
		
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				if (JOptionPane.showConfirmDialog(PantallaPrincipal.this,TDSLanguageUtils.getMessage("ClientePEC4.cerrar"), null, JOptionPane.YES_NO_OPTION) == 0){
					//if (srvOn) desconectarServidor();
					System.exit(0);
				}
			}
		}) ; 
		
		
	}
	
	
	

}
