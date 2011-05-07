package uoc.edu.tds.pec4.gestores;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import uoc.edu.tds.pec4.excepciones.OperationErrorBD;
import uoc.edu.tds.pec4.excepciones.OperationErrorRMI;
import uoc.edu.tds.pec4.iface.RemoteInterface;
import uoc.edu.tds.pec4.iface.RemotoImpl;
import uoc.edu.tds.pec4.resources.TDSLanguageUtils;

public class GestorRMI {
	
	private static final String UNAME_URL_RMI= "RemotoImpl";
	private static final String UNAME_URL_RMI_ALL= "rmi://localhost/RemotoImpl";
	private Registry registry;
	private RemotoImpl remote;
	
	public GestorRMI() throws OperationErrorRMI, OperationErrorBD{
		try {
			this.remote = new RemotoImpl();
		} catch (RemoteException e) {
			e.printStackTrace();
			//throw new Exception(e.getMessage());
			throw new OperationErrorRMI(e.getMessage());
		}
	}
	
	public synchronized RemoteInterface lookup() throws OperationErrorRMI{
        try {
			return (RemoteInterface) Naming.lookup(UNAME_URL_RMI_ALL);
		} catch (RemoteException e) {
			throw new OperationErrorRMI(TDSLanguageUtils.getMessage("servidorPEC4.error.rmi1"));
		}catch (MalformedURLException e) {
			throw new OperationErrorRMI(TDSLanguageUtils.getMessage("servidorPEC4.error.rmi2"));
		} catch (NotBoundException e) {
			throw new OperationErrorRMI(TDSLanguageUtils.getMessage("servidorPEC4.error.rmi3"));
		}
	}
	
	/**
	 * Método para conectar al servidor mediante RMI
	 * @throws OperationErrorRMI
	 */
	public synchronized void connectRMI() throws OperationErrorRMI{
        try {
    		if (registry == null) registry = LocateRegistry.createRegistry(1099);    
			Naming.rebind(UNAME_URL_RMI_ALL, remote);
    		registry.rebind(UNAME_URL_RMI_ALL, remote);
		} catch (RemoteException e) {
			throw new OperationErrorRMI(TDSLanguageUtils.getMessage("servidorPEC4.error.rmi1"));
		} catch (MalformedURLException e) {
			throw new OperationErrorRMI(TDSLanguageUtils.getMessage("servidorPEC4.error.rmi2"));
		}
	}
	
	/**
	 * Método para desconectar
	 * @throws OperationErrorRMI
	 */
	public synchronized void disconnectRMI() throws OperationErrorRMI{
		try {
	        Naming.unbind(UNAME_URL_RMI);
		} catch (RemoteException e) {
			throw new OperationErrorRMI(TDSLanguageUtils.getMessage("servidorPEC4.error.rmi1"));
		} catch (MalformedURLException e) {
			throw new OperationErrorRMI(TDSLanguageUtils.getMessage("servidorPEC4.error.rmi2"));
		}catch (NotBoundException e) {
			throw new OperationErrorRMI(TDSLanguageUtils.getMessage("servidorPEC4.error.rmi3"));
		}
		
	}
	
}
