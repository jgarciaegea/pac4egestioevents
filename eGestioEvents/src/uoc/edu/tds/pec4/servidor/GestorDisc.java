package uoc.edu.tds.pec4.servidor;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JOptionPane;
import uoc.edu.tds.pec4.utils.TDSLanguageUtils;

/**
 * Classe GestorDisc
 * 
 */
public class GestorDisc 
{

	public static final String SQL_STATE_UNIQUE_VIOLATION = "23505";
	
	private Connection objConn;
	private TDSLanguageUtils txt = null;
	
	/**
	 * Constructor
	 * @throws RemoteException
	 */
	public GestorDisc()
	{
		objConn = null;
	}
	/**
	 * Tanca la connexió amb la BD
	 * 
	 * @throws RemoteException
	 */
	public void tancaConnexio() throws Exception
	{
		if (objConn != null)
		{
			try
			{
				objConn.close();
			}
			catch (SQLException eSQL)
			{
				// Error al intentar tancar la BD
				JOptionPane.showMessageDialog(null, 
						txt.getMessage("bbdd.err.tancar") + "\n" + eSQL.getMessage(), 
						txt.getMessage("bbdd.err"), JOptionPane.ERROR_MESSAGE);
			}
			objConn = null;
		}
	}
	/**
	 * Conexión con BBDD.
	 * 
	 * @throws RemoteException
	 */
	public void initConnection() throws Exception
	{
		System.out.println("initconn");
		String sUrl = null;
		String sUserName = null;
		String sPassword = null;
		Properties objProperties = new Properties();
		// Obtenim les propietats de la connexió en base al fitxer de 
		// propietats 'configutation.properties'
		try 
		{
			FileInputStream objFIS = new FileInputStream("properties/configuration.properties");
			objProperties.load(objFIS);
			sUrl = objProperties.getProperty("url");
			sUserName = objProperties.getProperty("username");
			sPassword = objProperties.getProperty("password");
		}
		catch (FileNotFoundException eFnfe) 
		{
			// Fitxer no trobat
			JOptionPane.showMessageDialog(null, 
					txt.getMessage("bbdd.err.notrobat") + "\n" + eFnfe.getMessage(), 
					txt.getMessage("bbdd.err"), JOptionPane.ERROR_MESSAGE);
		}
		catch (IOException eIo) 
		{
			// Error de lectura
			JOptionPane.showMessageDialog(null, 
					txt.getMessage("bbdd.err.io") + "\n" + eIo.getMessage(), 
					txt.getMessage("bbdd.err"), JOptionPane.ERROR_MESSAGE);
		}
		catch (Exception eV)
		{
			// Error desconegut
			JOptionPane.showMessageDialog(null, 
					txt.getMessage("bbdd.err.unknown") + "\n" + eV.getMessage(), 
					txt.getMessage("bbdd.err"), JOptionPane.ERROR_MESSAGE);
		}
		// Carraguem el Driver de la BD
		try 
		{
			Class.forName("org.postgresql.Driver");
		}
		catch (ClassNotFoundException eCnfe) 
		{
			// No es pot carregar el driver de la BD
			JOptionPane.showMessageDialog(null, 
					txt.getMessage("bbdd.err.drvr") + "\n" + eCnfe.getMessage(), 
					txt.getMessage("bbdd.err"), JOptionPane.ERROR_MESSAGE);
		}
		// Obtenim la connexió amb la BD
		try 
		{
			objConn = DriverManager.getConnection(sUrl, sUserName, sPassword);
		}
		catch (SQLException eSQL) 
		{
			// Error a l'intentar obrir la connexió
			JOptionPane.showMessageDialog(null, 
					txt.getMessage("bbdd.err.open") + "\n" + eSQL.getMessage(), 
					txt.getMessage("bbdd.err"), JOptionPane.ERROR_MESSAGE);
		}
	}
	/**
	 * Obté la connexió a la BD
	 * 
	 * @return Connection
	 * @throws RemoteException
	 */
	public Connection getConnection()
	{
		return objConn;
	}
}
