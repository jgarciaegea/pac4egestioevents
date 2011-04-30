package edu.uoc.tds.rmi;

import java.io.File;
import java.rmi.*;

public class Cliente {

    /** Crea nueva instancia de Cliente */
    public Cliente() 
    {
        boolean conectado;
    	
    	try
        {
		// Lugar en el que está el objeto remoto.
		// Naming.lookup() obtiene el objeto remoto
    		String PolicyFile = new File(".").getAbsolutePath()+"/java.policy";
            System.setProperty("java.security.policy", PolicyFile);
            if (System.getSecurityManager() == null) 
                System.setSecurityManager(new RMISecurityManager());

            //System.setProperty("java.security.policy", "c:/prueba_cliente/java.policy");
            //if (System.getSecurityManager() == null) 
            //System.setSecurityManager(new RMISecurityManager());
            InterfaceRemota objetoRemoto = 
                (InterfaceRemota)Naming.lookup ("//localhost/ObjetoRemoto");
            
            //Se pinta el menu principal
            objetoRemoto.Menupral();
            //Conectamos a la base de datos
            conectado=objetoRemoto.conectaBD();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Cliente();
    }

}
