package uoc.edu.tds.pec4.gestores;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import uoc.edu.tds.pec4.excepciones.OperationErrorBD;
import uoc.edu.tds.pec4.excepciones.OperationErrorRMI;
import uoc.edu.tds.pec4.iface.RemoteInterface;
import uoc.edu.tds.pec4.resources.TDSLanguageUtils;
import java.rmi.registry.*;


public class GestorRMI {
	
	private static final String UNAME_URL_RMI= "RemotoImpl";
	private static final String UNAME_URL_RMI_ALL= "rmi://localhost/RemotoImpl";
	private RemotoImpl remote;
	private Registry registry;

	
	/*public GestorRMI() throws OperationErrorRMI, OperationErrorBD{
		try {
    		 
			this.remote = new RemotoImpl();
		} catch (RemoteException e) {
			e.printStackTrace();
			throw new OperationErrorRMI(e.getMessage());
		}
	}*/
	
	public GestorRMI(String tipo) throws OperationErrorRMI, OperationErrorBD{
		try {
			if (tipo == ("SERVER")){
				// levantamos el servidor
				if (registry == null) registry = LocateRegistry.createRegistry(1099);
			}			
			this.remote = new RemotoImpl();
		} catch (RemoteException e) {
			e.printStackTrace();
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
	 * M�todo para conectar al servidor mediante RMI
	 * @throws OperationErrorRMI
	 */
	public synchronized void connectRMI() throws OperationErrorRMI{
        try { 
			Naming.rebind(UNAME_URL_RMI_ALL, remote);
		} catch (RemoteException e) {
			throw new OperationErrorRMI(TDSLanguageUtils.getMessage("servidorPEC4.error.rmi1"));
		} catch (MalformedURLException e) {
			throw new OperationErrorRMI(TDSLanguageUtils.getMessage("servidorPEC4.error.rmi2"));
		}
	}
	
	/**
	 * M�todo para desconectar
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
