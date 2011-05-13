package uoc.edu.tds.pec4.iface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import uoc.edu.tds.pec4.dtos.DTOCentroDocente;
import uoc.edu.tds.pec4.dtos.DTOPais;
import uoc.edu.tds.pec4.dtos.DTOTipoDocumento;
import uoc.edu.tds.pec4.dtos.DTOTipoRol;
import uoc.edu.tds.pec4.dtos.DTOTipoTelefono;
import uoc.edu.tds.pec4.dtos.DTOUniversidad;
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
	
	/**
	 * Método para recuperar los tipos de documento existentes
	 * @return
	 * @throws RemoteException
	 * @throws OperationErrorBD
	 */
	public abstract List<DTOTipoDocumento> getTiposDocumento() throws RemoteException, OperationErrorBD;
	
	/**
	 * Método para recuperar los paises
	 * @return
	 * @throws RemoteException
	 * @throws OperationErrorBD
	 */
	public abstract List<DTOPais> getPaises() throws RemoteException, OperationErrorBD;
	
	/**
	 * Método para recuperar los tipos de telefono existentes
	 * @return
	 * @throws RemoteException
	 * @throws OperationErrorBD
	 */
	public abstract List<DTOTipoTelefono> getTiposTelefono() throws RemoteException, OperationErrorBD;
	
	/**
	 * Método para recuperar las universidades
	 * @return
	 * @throws RemoteException
	 * @throws OperationErrorBD
	 */
	public abstract List<DTOUniversidad> getUniversidades() throws RemoteException, OperationErrorBD;
	
	/**
	 * Método para recuperar los diferentes tipos de rol
	 * @return
	 * @throws RemoteException
	 * @throws OperationErrorBD
	 */
	public abstract List<DTOTipoRol> getTiposRol() throws RemoteException, OperationErrorBD;
	
	/**
	 * Método para rellenar los centros docentees mediante un id de universidad
	 * @param idUniversidad
	 * @return
	 * @throws RemoteException
	 * @throws OperationErrorBD
	 */
	public abstract List<DTOCentroDocente> rellenaCentrosByIdUniversidad(Integer idUniversidad) throws RemoteException, OperationErrorBD;
	
}
