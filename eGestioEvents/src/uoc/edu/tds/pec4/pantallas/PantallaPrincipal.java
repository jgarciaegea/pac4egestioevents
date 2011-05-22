package uoc.edu.tds.pec4.pantallas;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import uoc.edu.tds.pec4.beans.Usuario;
import uoc.edu.tds.pec4.dtos.DTOAdministrador;
import uoc.edu.tds.pec4.dtos.DTOAsistente;
import uoc.edu.tds.pec4.dtos.DTOPersonalSecretaria;
import uoc.edu.tds.pec4.dtos.DTOUsuario;
import uoc.edu.tds.pec4.excepciones.OperationErrorBD;
import uoc.edu.tds.pec4.excepciones.OperationErrorDatosFormulario;
import uoc.edu.tds.pec4.excepciones.OperationErrorLogin;
import uoc.edu.tds.pec4.excepciones.OperationErrorRMI;
import uoc.edu.tds.pec4.gestores.FactoriaUsuario;
import uoc.edu.tds.pec4.gestores.GestorRMI;
import uoc.edu.tds.pec4.iface.RemoteInterface;
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
	private RemoteInterface remote;
	private  Usuario usuario;
	
	public PantallaPrincipal(GestorRMI gestorRMI,RemoteInterface remote,Usuario usuario1){
       try {
		this.usuario = usuario1;
		this.gestorRMI = gestorRMI;
		this.remote = remote;
		setSize(784, 600);
	    setResizable(false);
	    setLocationRelativeTo(null);
	    setTitle(TDSLanguageUtils.getMessage("clientePEC4.framePrincipal.titulo"));
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        crearMenuBar();
        gestionarPermisos();
        setJMenuBar(barraMenu);
        generaEventosPantallaPrincipal();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private void gestionarPermisos() throws Exception{
		try {
		DTOUsuario dtoUsuario = FactoriaUsuario.getUsuario(usuario.getTipoUsuario());
		if(dtoUsuario instanceof DTOAdministrador){
	        menuMantenimiento.setEnabled(true);
	        menuConexion.setEnabled(true); //test
		}else if(dtoUsuario instanceof DTOPersonalSecretaria){
	        menuEstadisticas.setEnabled(true);
	        menuConexion.setEnabled(true);
	        menuProgramacionEvento.setEnabled(true);
		}else if(dtoUsuario instanceof DTOAsistente){
	        menuConexion.setEnabled(true);
		}
        menuSalir.setEnabled(true);
        menuAyuda.setEnabled(true);
		} catch (Exception e) {
			throw new OperationErrorDatosFormulario(e.getMessage());
		}
		//throw new OperationErrorDatosFormulario("El tipo de usuario " + usuario.getTipoUsuario() + " no está contemplado");
	}

	
	
	private void crearMenuBar(){
		try{
			
			System.out.println("Creando Objecto Menu Bar");
			panelPrincipal = new JPanel();
			super.getContentPane().add(panelPrincipal);
			
			//Crear el logo eGestioEvents en la pantalla principal
			//panelPrincipal.setLayout(new GridLayout(3, 3));
			panelPrincipal.setBackground(Color.white);
			JLabel logo = new JLabel("",JLabel.CENTER);
	        ImageIcon fondo = new ImageIcon("images/eGestio.jpg");
			logo.setIcon(fondo);
	        //panelPrincipal.add(logo,Component.CENTER_ALIGNMENT);      
			panelPrincipal.add(logo);
			
	        barraMenu = new JMenuBar();
	        menuMantenimiento = new JMenu(TDSLanguageUtils.getMessage("clientePEC4.framePrincipal.titulo.menu1"));
	        menuMantenimiento.setEnabled(false);
	        menuEstadisticas = new JMenu(TDSLanguageUtils.getMessage("clientePEC4.framePrincipal.titulo.menu2"));
	        menuEstadisticas.setEnabled(false);
	        menuProgramacionEvento = new JMenu(TDSLanguageUtils.getMessage("clientePEC4.framePrincipal.titulo.menu3"));
	        menuProgramacionEvento.setEnabled(false);
	        menuConexion = new JMenu(TDSLanguageUtils.getMessage("clientePEC4.framePrincipal.titulo.menu4"));
	        menuConexion.setEnabled(false);
	        menuSalir = new JMenu(TDSLanguageUtils.getMessage("clientePEC4.framePrincipal.titulo.menu5"));
	        menuSalir.setEnabled(true);
	        menuAyuda = new JMenu(TDSLanguageUtils.getMessage("clientePEC4.framePrincipal.titulo.menu6"));
	        menuAyuda.setEnabled(false);
	        
	        // items menu Conexion
	        
	        cambioPwd = new JMenuItem(TDSLanguageUtils.getMessage("clientePEC4.framePrincipal.titulo.menu4.item1"));
	        cambioPwd.addActionListener(new ActionListener() { 
	        	public void actionPerformed(ActionEvent evt) { 
	        		try {
						showPanel(new PantallaCambioPassword(gestorRMI,remote,usuario));
					} catch (OperationErrorLogin e) {
						e.showDialogError();
						e.printStackTrace();
					} 
	        	} }); 
	        inscripcionEvento = new JMenuItem(TDSLanguageUtils.getMessage("clientePEC4.framePrincipal.titulo.menu4.item2"));	   
	        historicoEventos = new JMenuItem(TDSLanguageUtils.getMessage("clientePEC4.framePrincipal.titulo.menu4.item3"));	
	        historicoEventos.addActionListener(new ActionListener() { 
	        	public void actionPerformed(ActionEvent evt) { 
	        		try {
						showPanel(new PantallaInformeEventosAsistente(gestorRMI,remote));
					} catch (RemoteException e) {
						e.printStackTrace();
					} catch (OperationErrorBD e) {
						e.showDialogError();
						e.printStackTrace();
					} 
	        	} }); 	        
	        
	        validarAsistenciaEvento = new JMenuItem(TDSLanguageUtils.getMessage("clientePEC4.framePrincipal.titulo.menu4.item4"));
	        validarAsistenciaEvento.addActionListener(new ActionListener() { 
	        	public void actionPerformed(ActionEvent evt) { 
	        		showPanel(new PantallaAsistencia(gestorRMI,remote)); 
	        	} }); 
	        
	     // items estadisticas
	        
	        informePorcentajes = new JMenuItem(TDSLanguageUtils.getMessage("clientePEC4.framePrincipal.titulo.menu2.item1"));	    
	        informeIngresos = new JMenuItem(TDSLanguageUtils.getMessage("clientePEC4.framePrincipal.titulo.menu2.item2"));
	        informeAsistentes = new JMenuItem(TDSLanguageUtils.getMessage("clientePEC4.framePrincipal.titulo.menu2.item3"));	   
	        informeEventos= new JMenuItem(TDSLanguageUtils.getMessage("clientePEC4.framePrincipal.titulo.menu2.item4"));
	        
	// items mantenimiento
	        
	        altaUsuario = new JMenuItem("Alta usuario");
	        consultaUsuario = new JMenuItem("Consulta usuario");
	        
	        altaUsuario.addActionListener(new ActionListener() { 
	        	public void actionPerformed(ActionEvent evt) { 
	        		showPanel(new PantallaUsuario(gestorRMI,remote)); 
	        	} }); 
	        
	        consultaUsuario.addActionListener(new ActionListener() { 
	        	public void actionPerformed(ActionEvent evt) { 
	        		showPanel(new PantallaUsuarioConsulta(gestorRMI,remote)); 
	        	} }); 

	        
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
	


}
