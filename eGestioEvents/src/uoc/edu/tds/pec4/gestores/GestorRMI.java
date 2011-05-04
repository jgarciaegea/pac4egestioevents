package uoc.edu.tds.pec4.gestores;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import uoc.edu.tds.pec4.iface.RemoteInterface;
import uoc.edu.tds.pec4.iface.RemotoImpl;
import uoc.edu.tds.pec4.resources.TDSLanguageUtils;

public class GestorRMI {
	
	private static final String UNAME_URL_RMI= "RemotoImpl";
	private static final String UNAME_URL_RMI_ALL= "rmi://localhost/RemotoImpl";
	private RemotoImpl remote;
	
	public GestorRMI() throws Exception{
		try {
			this.remote = new RemotoImpl();
		} catch (RemoteException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}
	
	public synchronized RemoteInterface lookup() throws Exception{
        try {
			return (RemoteInterface) Naming.lookup(UNAME_URL_RMI_ALL);
		} catch (RemoteException e) {
			throw new Exception(TDSLanguageUtils.getMessage("servidorPEC3.error.rmi1"));
		}catch (MalformedURLException e) {
			throw new Exception(TDSLanguageUtils.getMessage("servidorPEC3.error.rmi2"));
		} catch (NotBoundException e) {
			throw new Exception(TDSLanguageUtils.getMessage("servidorPEC3.error.rmi3"));
		}
	}
	
	/**
	 * Método para conectar al servidor mediante RMI
	 * @throws OperationErrorRMI
	 */
	public synchronized void connectRMI() throws Exception{
        try {
			Naming.rebind(UNAME_URL_RMI_ALL, remote);
		} catch (RemoteException e) {
			throw new Exception(TDSLanguageUtils.getMessage("servidorPEC3.error.rmi1"));
		} catch (MalformedURLException e) {
			throw new Exception(TDSLanguageUtils.getMessage("servidorPEC3.error.rmi2"));
		}
	}
	
	/**
	 * Método para desconectar
	 * @throws OperationErrorRMI
	 */
	public synchronized void disconnectRMI() throws Exception{
		try {
	        Naming.unbind(UNAME_URL_RMI);
		} catch (RemoteException e) {
			throw new Exception(TDSLanguageUtils.getMessage("servidorPEC3.error.rmi1"));
		} catch (MalformedURLException e) {
			throw new Exception(TDSLanguageUtils.getMessage("servidorPEC3.error.rmi2"));
		}catch (NotBoundException e) {
			throw new Exception(TDSLanguageUtils.getMessage("servidorPEC3.error.rmi3"));
		}
		
	}
	
}
