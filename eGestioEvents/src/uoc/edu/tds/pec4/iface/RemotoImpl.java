package uoc.edu.tds.pec4.iface;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import uoc.edu.tds.pec4.beans.CentroDocente;
import uoc.edu.tds.pec4.beans.Pais;
import uoc.edu.tds.pec4.beans.TipoDocumento;
import uoc.edu.tds.pec4.beans.TipoRol;
import uoc.edu.tds.pec4.beans.TipoTelefono;
import uoc.edu.tds.pec4.beans.Universidad;
import uoc.edu.tds.pec4.dtos.DTOCentroDocente;
import uoc.edu.tds.pec4.dtos.DTOPais;
import uoc.edu.tds.pec4.dtos.DTOTipoDocumento;
import uoc.edu.tds.pec4.dtos.DTOTipoRol;
import uoc.edu.tds.pec4.dtos.DTOTipoTelefono;
import uoc.edu.tds.pec4.dtos.DTOUniversidad;
import uoc.edu.tds.pec4.dtos.DTOUsuario;
import uoc.edu.tds.pec4.excepciones.OperationErrorBD;
import uoc.edu.tds.pec4.gestores.GestorCentroDocente;
import uoc.edu.tds.pec4.gestores.GestorDisco;
import uoc.edu.tds.pec4.gestores.GestorPais;
import uoc.edu.tds.pec4.gestores.GestorTipoDocumento;
import uoc.edu.tds.pec4.gestores.GestorTipoRol;
import uoc.edu.tds.pec4.gestores.GestorTipoTelefono;
//import uoc.edu.tds.pec4.gestores.GestorTipoTelefono;
import uoc.edu.tds.pec4.gestores.GestorUniversidad;
import uoc.edu.tds.pec4.gestores.GestorUsuario;

public class RemotoImpl extends UnicastRemoteObject implements RemoteInterface,Serializable{
	
	private static final long serialVersionUID = 1L;
	private GestorDisco gestorDB;
	
	public  void testConexion() throws RemoteException{
		System.out.println("Conectado al Servidor RMI Correctamente");
	}
	
	public  void conectarBBDD()throws RemoteException,OperationErrorBD{
		try {
			System.out.println("Conectado a la Base de DATOS....");
			gestorDB = new GestorDisco();
		} catch (Exception e) {
			throw new OperationErrorBD("Error conectado a la BBDD: " + e.getMessage());
		}
	}
	
	
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
	
	public List<DTOTipoDocumento> getTiposDocumento() throws RemoteException, OperationErrorBD {
		try{
			GestorTipoDocumento gestorTipoDocumento = new GestorTipoDocumento(gestorDB.getConnection());
			DTOTipoDocumento dtoTipoDoc = new DTOTipoDocumento();
			TipoDocumento tipoDocumento = new TipoDocumento();
			dtoTipoDoc.setTipoDocumento(tipoDocumento);
			return gestorTipoDocumento.consultaEntidades(dtoTipoDoc);
		}catch(Exception e){
			throw new OperationErrorBD("Error al recuperar el tipo de documentos: " + e.getMessage());
		}
	}
	
	public List<DTOPais> getPaises() throws RemoteException, OperationErrorBD {
		
		try{
			GestorPais gestorPais = new GestorPais(gestorDB.getConnection());
			DTOPais dtoPais = new DTOPais();
			dtoPais.setPais(new Pais());
			return gestorPais.consultaEntidades(dtoPais);
		}catch(Exception e){
			throw new OperationErrorBD("Error al recuperar los países: " + e.getMessage());
		}
	}
	
	public List<DTOTipoTelefono> getTiposTelefono() throws RemoteException, OperationErrorBD {
		try{
			GestorTipoTelefono gestorTipoTelefono = new GestorTipoTelefono(gestorDB.getConnection());
			DTOTipoTelefono dtoTipoTelf = new DTOTipoTelefono();
			dtoTipoTelf.setTipoTelefono(new TipoTelefono());
			return gestorTipoTelefono.consultaEntidades(dtoTipoTelf);
		}catch(Exception e){
			throw new OperationErrorBD("Error al recuperar los tipos de telefono: " + e.getMessage());
		}
	}
	
	public List<DTOUniversidad> getUniversidades() throws RemoteException, OperationErrorBD {
		
		try{
			GestorUniversidad gestorUniversidad = new GestorUniversidad(gestorDB.getConnection());
			DTOUniversidad dtoUniversidad = new DTOUniversidad();
			dtoUniversidad.setUniversidad(new Universidad());
			return gestorUniversidad.consultaEntidades(dtoUniversidad);
		}catch(Exception e){
			throw new OperationErrorBD("Error al recuperar las universidades: " + e.getMessage());
		}
	}
	
	public List<DTOTipoRol> getTiposRol() throws RemoteException, OperationErrorBD {
		try{
			GestorTipoRol gestorTipoRol = new GestorTipoRol(gestorDB.getConnection());
			DTOTipoRol dtoTipoRol = new DTOTipoRol();
			dtoTipoRol.setTipoRol(new TipoRol());
			return gestorTipoRol.consultaEntidades(dtoTipoRol);
		}catch(Exception e){
			throw new OperationErrorBD("Error al recuperar los tipos de rol: " + e.getMessage());
		}
	}
	
	public List<DTOCentroDocente> rellenaCentrosByIdUniversidad(Integer idUniversidad) throws RemoteException, OperationErrorBD {
		try{
			GestorCentroDocente gestorCentroDocente = new GestorCentroDocente(gestorDB.getConnection());
			DTOCentroDocente dtoCentroDocente = new DTOCentroDocente();
			CentroDocente centroDocente = new CentroDocente();
			centroDocente.setIdUniversidad(idUniversidad);
			dtoCentroDocente.setCentroDocente(centroDocente);
			return gestorCentroDocente.consultaEntidades(dtoCentroDocente);
		}catch(Exception e){
			throw new OperationErrorBD("Error al recuperar los centros docentes de la universidad[" + idUniversidad +"] " +  e.getMessage());
		}
	}
	

}
