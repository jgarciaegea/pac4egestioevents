package uoc.edu.tds.pec4.iface;

import java.rmi.Remote;
import java.rmi.RemoteException;

import uoc.edu.tds.pec4.dtos.DTOUsuario;
import uoc.edu.tds.pec4.excepciones.OperationErrorBD;

public interface RemoteInterface extends Remote{
	/**
	 * Método que dará de alta un usuario
	 * @param dtoUsuario
	 * @throws RemoteException
	 * @throws OperationErrorBD
	 */
	public abstract void insertaUsuario(DTOUsuario dtoUsuario) throws RemoteException,OperationErrorBD;
}
