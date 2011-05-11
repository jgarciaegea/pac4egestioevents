package uoc.edu.tds.pec4.gestores;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import uoc.edu.tds.pec4.excepciones.OperationErrorBD;

public class GestorDisco {
	
	private static final String RUTA_CONF_BBDD = "conf/configuration.properties";
	
	protected Connection connection = null;
	private static String user;
	private static String password;
	private static String url;
	
	public GestorDisco() throws OperationErrorBD{
		inicializar();
		openConnection();
	}
	
	public GestorDisco(Connection connection) throws OperationErrorBD{
		this.connection=connection;
	}
	
	private static void inicializar() throws OperationErrorBD{
		try{
			Properties properties = new Properties();
		    properties.load(new FileInputStream(RUTA_CONF_BBDD));
			user =  properties.getProperty("username");
			password =	properties.getProperty("password");
			url = properties.getProperty("url");
			Class.forName("org.postgresql.Driver");
		}catch(FileNotFoundException f){
			throw new OperationErrorBD(f.getMessage());
		}catch(IOException i){
			throw new OperationErrorBD(i.getMessage());
		}catch(ClassNotFoundException c){
			throw new OperationErrorBD(c.getMessage());
		}
	}
	
	private synchronized void openConnection() throws OperationErrorBD{
		try {
			
			if (!isClosedConnection()) {
				return; //Si ya esta la conexion abierta no hacemos nada
			}
			
			Connection cn = DriverManager.getConnection(url,user,password);
			connection = cn;
		} catch (SQLException e) {
			throw new OperationErrorBD(e.getMessage());
		}
	}
	
	public synchronized void closeConnection() throws OperationErrorBD {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (Exception e) {
			throw new OperationErrorBD(e.getMessage());
		}
	}
	
	public synchronized Connection getConnection() throws OperationErrorBD{
		return connection;
	}
	
	public boolean isClosedConnection() throws OperationErrorBD, SQLException {
		return (connection == null || connection.isClosed());
	}	
	
	
}
