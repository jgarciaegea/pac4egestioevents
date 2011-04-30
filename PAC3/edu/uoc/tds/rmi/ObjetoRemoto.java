package edu.uoc.tds.rmi;

/*
 * ObjetoRemoto.java
 *
 * Created on 27 de abril de 2004, 21:18
 */
import java.io.Serializable;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

import edu.uoc.tds.comun.GestorBBDD;
import edu.uoc.tds.gui.*;

/**
 * @author  Jonathan Garcia
 */


//java.rmi.server.UnicastRemoteObject
public class ObjetoRemoto extends UnicastRemoteObject 
    implements InterfaceRemota,Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Construye una instancia de ObjetoRemoto
     * @throws RemoteException
     */
    public ObjetoRemoto () throws RemoteException
    {
        super();
    }
    
    public void Menupral () {
    	Pantalla Menupral = new Pantalla();
    	try {
			Menupral.GuiMenu();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public boolean conectaBD() {
    	GestorBBDD BDPec3 = new GestorBBDD();
    	return BDPec3.conectaBD();
    	
    }
    
}
