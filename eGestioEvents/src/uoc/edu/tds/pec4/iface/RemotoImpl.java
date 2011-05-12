package uoc.edu.tds.pec4.iface;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import uoc.edu.tds.pec4.dtos.DTOUsuario;
import uoc.edu.tds.pec4.excepciones.OperationErrorBD;
import uoc.edu.tds.pec4.gestores.GestorDisco;
import uoc.edu.tds.pec4.gestores.GestorUsuario;

public class RemotoImpl extends UnicastRemoteObject implements RemoteInterface,Serializable{
	
	private static final long serialVersionUID = 1L;
	private GestorDisco gestorDB;
	
	public RemotoImpl() throws RemoteException {
		super();
	}
	
	public RemotoImpl(GestorDisco gestorDB) throws RemoteException {
		super();
		this.gestorDB = gestorDB;
	}
	
	public void insertaUsuario(DTOUsuario dtoUsuario) throws RemoteException, OperationErrorBD {
		try {
			GestorUsuario gestorUsuario = new GestorUsuario(gestorDB.getConnection());
			gestorUsuario.insertaEntidad(dtoUsuario);
		} catch (Exception e) {
			throw new OperationErrorBD("Error al insertar el usuario: " + e.getMessage());
		}
	}

}
