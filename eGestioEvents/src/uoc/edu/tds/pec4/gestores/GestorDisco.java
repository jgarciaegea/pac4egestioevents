package uoc.edu.tds.pec4.gestores;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class GestorDisco {
	
	private static final String RUTA_CONF_BBDD = "conf/configuration.properties";
	
	protected Connection connection = null;
	private static String user;
	private static String password;
	private static String url;
	
	public GestorDisco() throws Exception{
		inicializar();	
		openConnection();
	}
	
	public GestorDisco(Connection connection) throws Exception{
		if(this.connection == null){
			inicializar();	
			openConnection();
		}
		this.connection=connection;
	}
	
	private static void inicializar() throws Exception{
		try{
			Properties properties = new Properties();
		    properties.load(new FileInputStream(RUTA_CONF_BBDD));
			user =  properties.getProperty("username");
			password =	properties.getProperty("password");
			url = properties.getProperty("url");
			Class.forName("org.postgresql.Driver");
		}catch(FileNotFoundException f){
			throw new Exception(f.getMessage());
		}catch(IOException i){
			throw new Exception(i.getMessage());
		}catch(ClassNotFoundException c){
			throw new Exception(c.getMessage());
		}
	}
	
	private synchronized void openConnection() throws Exception{
		try {
			Connection cn = DriverManager.getConnection(url,user,password);
			this.connection = cn;
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}
	}
	
	public synchronized void closeConnection() throws Exception {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public synchronized Connection getConnection() throws Exception{
		return this.connection;
	}
	
	
}
