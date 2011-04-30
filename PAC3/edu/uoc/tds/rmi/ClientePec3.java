package edu.uoc.tds.rmi;

import edu.uoc.tds.comun.*;
import edu.uoc.tds.i18n.TDSLanguageUtils;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import java.sql.*;

import java.util.*;

import javax.swing.event.MenuListener;
import java.awt.event.*;
import javax.swing.table.*;


public class ClientePec3 extends JFrame implements ActionListener{
	
	//
	private static Connection con = null;
    private JPanel jPanelPrincipal;
    private JLabel label1;
    private JMenuBar jMenuBar;
    private JMenu jMenu1;
    private JMenu jMenu2;
    private JMenuItem jMenuItem1;
    private JMenuItem jMenuItem2;
    private JMenuItem jMenuItem3;

    private JTable jTablaConcepto;
    private DefaultTableModel jTableModelConcepto;
    private JScrollPane jScrollPane1;
    
    private static GestorBBDD db = null;
    //}
    
	public ClientePec3()
	{
		inicializarControles(); 

	}
	
	public void inicializarControles()
	{
		//Ventana principal
		setDefaultCloseOperation(3);
        //setResizable(false);
        setLocation(new Point(320, 100));
        setSize(600, 400);       
        setTitle(TDSLanguageUtils.getMessage("Cliente.title"));
        
        jPanelPrincipal = new JPanel();
        jPanelPrincipal.setLayout(new GridLayout(0,1));
        getContentPane().add(jPanelPrincipal);
        
        label1 = new JLabel(TDSLanguageUtils.getMessage("Label1.text"));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setFont(new Font("Dialog", 1 , 18));
        
        //Menu1
        jMenu1 = new JMenu(TDSLanguageUtils.getMessage("Menu1.text"));
        
        //Menu2
        jMenu2 = new JMenu(TDSLanguageUtils.getMessage("Menu2.text"));
        jMenuItem1 = new JMenuItem(TDSLanguageUtils.getMessage("Menu3.text"));
        jMenuItem2 = new JMenuItem(TDSLanguageUtils.getMessage("Menu4.text"));
        jMenuItem3 = new JMenuItem(TDSLanguageUtils.getMessage("Menu5.text"));
        jMenuItem1.addActionListener(this);
        jMenuItem2.addActionListener(this);
        jMenuItem3.addActionListener(this);
        jMenu2.add(jMenuItem1);
        jMenu2.add(jMenuItem2);
        jMenu2.add(jMenuItem3);
        jMenu2.setEnabled(false);
        
        jMenu1.addMenuListener(new MenuListener()
        {
        	public void menuSelected(MenuEvent e) {
                jMenu2.setEnabled(true);
                jMenu1.setEnabled(false);
              }

			public void menuDeselected(MenuEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void menuCanceled(MenuEvent arg0) {
				// TODO Auto-generated method stub
				
			}
        });
        
        jMenuBar = new JMenuBar();
        jMenuBar.add(jMenu1);
        jMenuBar.add(jMenu2);
        
        setJMenuBar(jMenuBar);
        jPanelPrincipal.add(label1, BorderLayout.CENTER);
        
        //Crear tabla para consulta de conceptos
        CrearTablaConceptos();
	}
	
	public void PruebaConcepto()
	{
		GastosPorConcepto gastos = new GastosPorConcepto("Personal");
		try
		{
			jTableModelConcepto = gastos.Listar(db.conexion);
			jTablaConcepto.setModel(jTableModelConcepto);
			jScrollPane1.setVisible(true);
		}
		catch (SQLException ex)
		{
			
		}
	}
	
	public void PruebaEvento()
	{
		GastosPorEvento gastos = new GastosPorEvento("Evento1");
		try
		{
			jTableModelConcepto = gastos.ListarEv(db.conexion);
			jTablaConcepto.setModel(jTableModelConcepto);
			jScrollPane1.setVisible(true);
		}
		catch (SQLException ex)
		{
			
		}
	}
	
	
	public static void main(String args[])
    {

        if(args.length == 0)
            TDSLanguageUtils.setDefaultLanguage("edu/uoc/tds/i18n/TextosMensajes/messages");
        if(args.length == 1)
        {
            Locale locale = new Locale(args[0]);
            TDSLanguageUtils.setLanguage("edu/uoc/tds/i18n/TextosMensajes/messages", locale);
        }
        new ClientePec3().setVisible(true);
        
        
        //conectar bd
        
    		db = new GestorBBDD();
    		boolean conexion = false;
    		//Conecta a la BD, Si no hay conexión se aborta
    		if ((conexion  = db.conectaBD()) == false ){
    			System.exit(-1);
    		}
    		con = db.conexion;
        
    }
	
	public void actionPerformed(ActionEvent event)
	{
		if (event.getSource() == jMenuItem2){
			PruebaEvento();
		}
		if (event.getSource() == jMenuItem3)
		{
			PruebaConcepto();
		}else if (event.getSource() == jMenuItem1){
			jPanelPrincipal.setVisible(false);			
			
			//Mostrar el frame de factura
			Factura fra = new Factura();
			try {
				fra.PantallaFactura(con,getContentPane());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	public void CrearTablaConceptos()
	{
        jTableModelConcepto = new DefaultTableModel();

        jTablaConcepto = new JTable();
        jTablaConcepto.setModel(jTableModelConcepto);
        
        jScrollPane1 = new JScrollPane(jTablaConcepto);
        jPanelPrincipal.add(jScrollPane1);
        jScrollPane1.setVisible(false);

	}


}

