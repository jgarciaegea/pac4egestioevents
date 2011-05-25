package uoc.edu.tds.pec4.iface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import uoc.edu.tds.pec4.beans.Usuario;
import uoc.edu.tds.pec4.dtos.DTOCentroDocente;
import uoc.edu.tds.pec4.dtos.DTOEvento;
import uoc.edu.tds.pec4.dtos.DTOEventoCalendario;
import uoc.edu.tds.pec4.dtos.DTOEventoPlus;
import uoc.edu.tds.pec4.dtos.DTOEventoRequisitos;
import uoc.edu.tds.pec4.dtos.DTOInscripcion;
import uoc.edu.tds.pec4.dtos.DTOPais;
import uoc.edu.tds.pec4.dtos.DTOTipoDocumento;
import uoc.edu.tds.pec4.dtos.DTOTipoEvento;
import uoc.edu.tds.pec4.dtos.DTOTipoRol;
import uoc.edu.tds.pec4.dtos.DTOTipoTelefono;
import uoc.edu.tds.pec4.dtos.DTOUniversidad;
import uoc.edu.tds.pec4.dtos.DTOUsuario;
import uoc.edu.tds.pec4.excepciones.OperationErrorBD;
import uoc.edu.tds.pec4.excepciones.OperationErrorLogin;

/**
 * @author jgarcia
 *
 */
public interface RemoteInterface extends Remote{
	
	/**
	 * Método que Testear la conexion
	 * 
	 * @throws RemoteException
	 * @throws 
	 */
	public abstract void testConexion() throws RemoteException;
	
	
	/**
	 * Método conexion BBDD 
	 * @throws RemoteException
	 * @throws OperationErrorBD
	 */
	public abstract void conectarBBDD() throws RemoteException,OperationErrorBD;
	
	
	/**
	 * Método que dará de alta un usuario
	 * @param dtoUsuario
	 * @throws RemoteException
	 * @throws OperationErrorBD
	 */
	public abstract String insertaUsuario(DTOUsuario dtoUsuario) throws RemoteException,OperationErrorBD;
	
	/**
	 * Recuperamos los datos de los usuarios
	 * @param dtoUsuario
	 * @return
	 * @throws RemoteException
	 * @throws OperationErrorBD
	 */
	public List<DTOUsuario> getUsuarios(DTOUsuario dtoUsuario) throws RemoteException, OperationErrorBD;
	
	/**
	 * Método para recuperar un Usuario
	 * @param codigoUsuario
	 * @return
	 * @throws RemoteException
	 * @throws OperationErrorBD
	 */
	
	public abstract  DTOUsuario getUsuario(DTOUsuario dtoUsuario) throws RemoteException, OperationErrorBD;
	
	/**
	 * Damos de baja un usuario, es decir, ponemos el usuario como inactivo (valor 3)
	 * @param dtoUsuario
	 * @throws RemoteException
	 * @throws OperationErrorBD
	 */
	public void bajaUsuario(DTOUsuario dtoUsuario) throws RemoteException, OperationErrorBD;
	
	/**
	 * Modificamos el usuario
	 * @param dtoUsuario
	 * @throws RemoteException
	 * @throws OperationErrorBD
	 */
	public void modificaUsuario(DTOUsuario dtoUsuario)  throws RemoteException, OperationErrorBD;
	
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
	
	
	public abstract List<DTOTipoEvento> getTiposEventos() throws RemoteException, OperationErrorBD;
	
	/**
	 * Realizamos el login del usuario
	 * @param codigo
	 * @param password
	 * @return
	 * @throws RemoteException
	 * @throws OperationErrorLogin
	 */
	public abstract DTOUsuario loginUsuario(Usuario userLogin) throws RemoteException,OperationErrorLogin;
	
	/**
	 * Actualiza el password del usuario
	 * @param user
	 * @throws RemoteException
	 * @throws OperationErrorLogin
	 */
	public abstract void updatePassword(DTOUsuario user) throws RemoteException,OperationErrorLogin;
	
	
	/*
	 ********************************************************************************
	 ****************************** Subsistema de Eventos ***************************
	 ********************************************************************************
	 */
	
	/*
	 * Tratamiento de Inscripciones
	 */

	/**
	 * Una vez finalizado el Evento se podr‡ notificar que el asistente ha asistido
	 * al evento mediante la realizaci—n del check-IN de su inscripci—n. 
	 * @param inscripcion
	 * @throws RemoteException
	 * @throws OperationErrorLogin
	 */
	public abstract void checkIN(DTOInscripcion inscripcion) throws RemoteException, OperationErrorBD;
	
	/**
	 * A efectos de error, que podamos desmarcar la asistencia a un evento del asistente.
	 * @param inscripcion
	 * @throws RemoteException
	 * @throws OperationErrorLogin
	 */
	public abstract void checkOUT(DTOInscripcion inscripcion) throws RemoteException, OperationErrorBD;
	
	/*
	 * Tratamiento de Eventos
	 */
	
	/**
	 * Retorna la informaci—n de eventos que cumplen los requisitos segun los valores de dtoEventoCalendario
	 * @param dtoEventoCalendario
	 * @return
	 * @throws RemoteException
	 * @throws OperationErrorBD
	 */
	public abstract List<DTOEventoCalendario> getEventosCalendario(DTOEventoCalendario dtoEventoCalendario) throws RemoteException, OperationErrorBD;
	
	/**
	 * Pasa un evento a estado cancelado
	 * @param dtoEvento
	 * @throws RemoteException
	 * @throws OperationErrorBD
	 */
	public abstract void bajaEvento(DTOEvento dtoEvento) throws RemoteException, OperationErrorBD;
	
	/**
	 * Insertamos un centro docente
	 * @param dtoCentroDocente
	 * @throws RemoteException
	 * @throws OperationErrorBD
	 */
	public void insertaCentroDocente(DTOCentroDocente dtoCentroDocente) throws RemoteException, OperationErrorBD;
	
	/**
	 * Consulta los centros docentes existentes
	 * @param dtoCentroDocente
	 * @return 
	 * @throws RemoteException
	 * @throws OperationErrorBD
	 */
	public List<DTOCentroDocente> consultaCentrosDocentes(DTOCentroDocente dtoCentroDocente) throws RemoteException, OperationErrorBD;
	
	/**
	 * Damos de baja el centro Docente
	 * @param dtoCentroDocente
	 * @throws RemoteException
	 * @throws OperationErrorBD
	 */
	public void bajaCentroDocente(DTOCentroDocente dtoCentroDocente) throws RemoteException, OperationErrorBD;
	
	/**
	 * Consultamos un centro Docente
	 * @param dtoCentroDocente
	 * @return
	 * @throws RemoteException
	 * @throws OperationErrorBD
	 */
	public DTOCentroDocente getCentroDocente(DTOCentroDocente dtoCentroDocente) throws RemoteException, OperationErrorBD;
	
	/**
	 * Modificar el centro docente
	 * @param dtoCentroDocente
	 * @throws RemoteException
	 * @throws OperationErrorBD
	 */
	public void modificaCentroDocente(DTOCentroDocente dtoCentroDocente) throws RemoteException, OperationErrorBD;
	
	
	/**
	 * Retorna un evento a partir de un dtoEvento(idEvento)
	 * @param dtoEvento
	 * @return
	 * @throws RemoteException
	 * @throws OperationErrorBD
	 */
	public abstract DTOEvento getEvento(DTOEvento dtoEvento) throws RemoteException, OperationErrorBD;
	
	/**
	 * Modfica un evento
	 * @param dtoEvento
	 * @throws RemoteException
	 * @throws OperationErrorBD
	 */
	public abstract void modificaEvento(DTOEvento dtoEvento)  throws RemoteException, OperationErrorBD;
	
	/**
	 * Genera un nuevo evento en el calendario AUE
	 * @param dtoEvento
	 * @throws RemoteException
	 * @throws OperationErrorBD
	 */
	public abstract void insertaEvento(DTOEvento dtoEvento)  throws RemoteException, OperationErrorBD;
	
	
	/**
	 * Buscar Eventos desde Sistema de conexion
	 * @param evento
	 * @throws RemoteException
	 * @throws OperationErrorBD
	 * @throws Exception 
	 */
	public abstract List<DTOEvento> getEventos(DTOEvento dtoEvento)  throws RemoteException, OperationErrorBD;
	
	/**
	 * @param dtoEvento
	 * @return
	 * @throws RemoteException
	 * @throws OperationErrorBD
	 */
	public abstract List<DTOEvento> getEventosFinalizados(DTOEvento dtoEvento)  throws RemoteException, OperationErrorBD;
	
	public abstract List<DTOInscripcion> buscaEventosInscrito(DTOInscripcion dtoInscripcion)  throws RemoteException, OperationErrorBD, Exception;

	/**
	 * Retorna las inscripciones segun un dtoInscripcion
	 * @param dtoInscripcion
	 * @return
	 * @throws RemoteException
	 * @throws OperationErrorBD
	 */
	public abstract List<DTOInscripcion> getInscripciones(DTOInscripcion dtoInscripcion) throws RemoteException, OperationErrorBD;
	
	/**
	 * Retorna las inscripciones de un evento finalizado
	 * @param dtoInscripcion
	 * @return
	 * @throws RemoteException
	 * @throws OperationErrorBD
	 */
	public abstract List<DTOInscripcion> getInscripcionesByEventoFinalizado(DTOInscripcion dtoInscripcion) throws RemoteException, OperationErrorBD;
	
	/**
	 * Comprobamos si un usuario tiene centros vinculados
	 * En esta caso no se envía el DTOUsuario ya que no sabemos el tipo de usuario y tampoco no nos interesa
	 * sólo queremos saber si un centro tiene usuarios asignados
	 * @param usuario
	 * @return
	 * @throws RemoteException
	 * @throws OperationErrorBD
	 */
	public abstract Boolean usuarioCentrosVinculados(Usuario usuario) throws RemoteException, OperationErrorBD;
	
	public abstract void insertaInscripcion(DTOInscripcion dtoinscripcion) throws RemoteException, OperationErrorBD ;
	
	public abstract DTOEventoPlus getPlazasEvento(DTOEvento criteris) throws RemoteException, OperationErrorBD;
	
	public abstract boolean validarInscripcion(DTOEvento dtoEvento, DTOUsuario dtoUsuario,DTOInscripcion dtoInscripcion ) throws Exception;
	
	public abstract List<DTOEventoRequisitos>  getRequisitosEvento(DTOEvento dtoEvento) throws Exception;
		
}
