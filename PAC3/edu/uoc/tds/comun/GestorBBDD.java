package edu.uoc.tds.comun;
 
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.IOException;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JOptionPane;

import edu.uoc.tds.i18n.TDSLanguageUtils;

 public class GestorBBDD {
 	public Connection conexion;
 	final String propertiesFile = new File(".").getAbsolutePath()+"/properties/configuration.properties";
 	final String driver = "org.postgresql.Driver";
 
 	public GestorBBDD(){
 		super();
 	}
 
 	public boolean conectaBD(){
		
		System.out.println("Realizando la conexion");
 		//	Clase que hospeda la conexion
 		try {			
 			//Recoger el driver JDBC especifico de Postgres1l
 			Class.forName(driver);
 			//Recuperar información del fichero de propiedades
 			Properties prop = new Properties();
 			prop.load(new FileInputStream(propertiesFile));       
 			String url = prop.getProperty("url");
 			String usuario = prop.getProperty("username");
 			String clave= prop.getProperty("password");
 			//Montar la conexion a la BBDD
 			conexion = DriverManager.getConnection(url, usuario, clave);
 			System.out.println("ESTAMOS CONECTADOS A POSTGRESQL!");
 		} catch (ClassNotFoundException exc) {
 			//No se encuentra el driver JDBC de Postgre en el classpath
 			JOptionPane.showMessageDialog(null, 
					TDSLanguageUtils.getMessage("Error4.label"),
					TDSLanguageUtils.getMessage("Error4.text"),
					JOptionPane.ERROR_MESSAGE);
 			//exc.printStackTrace();
 			return false;
 		} catch (FileNotFoundException exc) {
 			//No se encuentra el archivo "configuration.properties"
 			JOptionPane.showMessageDialog(null, 
					TDSLanguageUtils.getMessage("Error2.label"),
					TDSLanguageUtils.getMessage("Error2.text"),
					JOptionPane.ERROR_MESSAGE);
 			//exc.printStackTrace();
 			return false;
 		} catch (IOException exc) {
 			//El fichero de properties no es correcto
 			JOptionPane.showMessageDialog(null, 
					TDSLanguageUtils.getMessage("Error5.label"),
					TDSLanguageUtils.getMessage("Error5.text"),
					JOptionPane.ERROR_MESSAGE);
 			//exc.printStackTrace();
 			return false;
 		} catch (SQLException exc) {
 			//La base de dades no existe, está parada, o login incorrecto
 			JOptionPane.showMessageDialog(null, 
					TDSLanguageUtils.getMessage("Error6.label"),
					TDSLanguageUtils.getMessage("Error6.text"),
					JOptionPane.ERROR_MESSAGE);
 			//exc.printStackTrace();
 			return false;
 		} catch (Exception exc){
 			//Excepción inesperada
 			exc.printStackTrace();
 			return false;
 		} 
 		return true;
 	} 
	
	public static void closeConnection( Connection con ) {
		
		if (con != null){ 	
	    	try {
	    		//Cierra la conexión
	    		con.close();
			}
	    	catch (SQLException e){
	    		System.out.println("ERROR: Cerrando conexión SGBD");
	    		System.err.println( e.getMessage());
	    	}	 
	
	    }
	}
 }