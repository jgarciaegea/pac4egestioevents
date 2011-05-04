package uoc.edu.tds.pec4.iface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemotoImpl extends UnicastRemoteObject implements RemoteInterface{
	
	private static final long serialVersionUID = 1L;
	
	public RemotoImpl() throws RemoteException {
		super();
	}

	
}
