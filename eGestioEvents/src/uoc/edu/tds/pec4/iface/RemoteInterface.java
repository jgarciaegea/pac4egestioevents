package uoc.edu.tds.pec4.iface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteInterface extends Remote{
	
	public String pruebaFuncionamiento() throws RemoteException;
	
}
