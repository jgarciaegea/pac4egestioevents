package uoc.edu.tds.pec4.iface;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import uoc.edu.tds.pec4.resources.TDSLanguageUtils;

public class RemotoImpl extends UnicastRemoteObject implements RemoteInterface,Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public RemotoImpl() throws RemoteException {
		super();
	}

	public String pruebaFuncionamiento() throws RemoteException {
		// TODO Auto-generated method stub
		//System.out.println("Funcionamiento OK.");
		return new String(TDSLanguageUtils.getMessage("RMT_TEST"));
	}
}
