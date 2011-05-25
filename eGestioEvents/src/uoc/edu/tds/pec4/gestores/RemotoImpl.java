package uoc.edu.tds.pec4.gestores;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import uoc.edu.tds.pec4.beans.CentroDocente;
import uoc.edu.tds.pec4.beans.Pais;
import uoc.edu.tds.pec4.beans.TipoDocumento;
import uoc.edu.tds.pec4.beans.TipoEvento;
import uoc.edu.tds.pec4.beans.TipoRol;
import uoc.edu.tds.pec4.beans.TipoTelefono;
import uoc.edu.tds.pec4.beans.Universidad;
import uoc.edu.tds.pec4.beans.Usuario;
import uoc.edu.tds.pec4.dtos.DTOCentroDocente;
import uoc.edu.tds.pec4.dtos.DTOCentroDocenteConsulta;
import uoc.edu.tds.pec4.dtos.DTOEvento;
import uoc.edu.tds.pec4.dtos.DTOEventoCalendario;
import uoc.edu.tds.pec4.dtos.DTOInscripcion;
import uoc.edu.tds.pec4.dtos.DTOInscripcionConsulta;
import uoc.edu.tds.pec4.dtos.DTOPais;
import uoc.edu.tds.pec4.dtos.DTOTipoDocumento;
import uoc.edu.tds.pec4.dtos.DTOTipoEvento;
import uoc.edu.tds.pec4.dtos.DTOTipoRol;
import uoc.edu.tds.pec4.dtos.DTOTipoTelefono;
import uoc.edu.tds.pec4.dtos.DTOUniversidad;
import uoc.edu.tds.pec4.dtos.DTOUsuario;
import uoc.edu.tds.pec4.dtos.DTOUsuarioConsulta;
import uoc.edu.tds.pec4.excepciones.OperationErrorBD;
import uoc.edu.tds.pec4.excepciones.OperationErrorLogin;
import uoc.edu.tds.pec4.iface.RemoteInterface;
import uoc.edu.tds.pec4.utils.Constantes;

public class RemotoImpl extends UnicastRemoteObject implements RemoteInterface{
	
	private static final long serialVersionUID = 1L;
	private GestorDisco gestorDB;
	
	public  void testConexion() throws RemoteException{
		System.out.println("Conectado al Servidor RMI Correctamente");
	}
	
	public  void conectarBBDD()throws RemoteException,OperationErrorBD{
		try {
			gestorDB = new GestorDisco();
		} catch (Exception e) {
			throw new OperationErrorBD("Error conectando a la BBDD: " + e.getMessage());
		}
	}
	
	
	public RemotoImpl() throws RemoteException {
		super();
	}
	
	public RemotoImpl(GestorDisco gestorDB) throws RemoteException {
		super();
		this.gestorDB = gestorDB;
	}
	
	public String insertaUsuario(DTOUsuario dtoUsuario) throws RemoteException, OperationErrorBD {
		try {
			
			gestorDB.getConnection().setAutoCommit(false);
			
			//Insertamos en contacto
			GestorContacto gestorContacto = new GestorContacto(gestorDB.getConnection());
			Integer idContacto = gestorContacto.insertaEntidadRetId(dtoUsuario.getDtoContacto());
			
			GestorTelefono gestorTelefono = new GestorTelefono(gestorDB.getConnection());
			dtoUsuario.getDtoContacto().getDtoTelefono().getTelefono().setIdContacto(idContacto);
			gestorTelefono.insertaEntidad(dtoUsuario.getDtoContacto().getDtoTelefono());
			
			System.out.println("Contacto insertado correctamente");
			
			//Insertamos el documentoIdentificación
			GestorDocumentoIdentificacion gestorDocumIden = new GestorDocumentoIdentificacion(gestorDB.getConnection());
			Integer idDocumentoIden = gestorDocumIden.insertaEntidadRetId(dtoUsuario.getDtoDocumentoIden());
			dtoUsuario.getUsuario().setIdDocumentoIdentificacion(idDocumentoIden);
			System.out.println("Docucmento insertado correctamente");
			
			//Insertamos los datos bancarios
			if(dtoUsuario.getDtoDatosBancarios() != null){
				GestorDatosBancarios gestorDatosBancarios = new GestorDatosBancarios(gestorDB.getConnection());
				Integer idBancario = gestorDatosBancarios.insertaEntidadRetId(dtoUsuario.getDtoDatosBancarios());
				dtoUsuario.getUsuario().setIdDatosBancarios(idBancario);
			}
			
			//Insertamos el usuario
			GestorUsuario gestorUsuario = new GestorUsuario(gestorDB.getConnection());
			dtoUsuario.getUsuario().setIdContacto(idContacto);
			gestorUsuario.insertaEntidad(dtoUsuario);
			System.out.println("Usuario insertado correctamente: " + dtoUsuario.getUsuario().getCodigo());
			
			gestorDB.getConnection().commit();
			
			return dtoUsuario.getUsuario().getCodigo();
			
		} catch (Exception e) {
			gestorDB.rollback();
			throw new OperationErrorBD("Error al insertar el usuario: " + e.getMessage());
		}
		
	}
	
	public void bajaUsuario(DTOUsuario dtoUsuario) throws RemoteException, OperationErrorBD {
		try{
			gestorDB.getConnection().setAutoCommit(false);
			System.out.println("damos de baja el usuario.....");
			GestorUsuario gestorUsuario = new GestorUsuario(gestorDB.getConnection());
			gestorUsuario.eliminaEntidad(dtoUsuario);
			gestorDB.getConnection().commit();
		}catch(Exception e){
			gestorDB.rollback();
			throw new OperationErrorBD("Error al recuperar la información de los usuarios......");
		}
		
	}
	
	public void modificaUsuario(DTOUsuario dtoUsuario) throws RemoteException, OperationErrorBD {
		try{
			
			gestorDB.getConnection().setAutoCommit(false);
			
			//Modificamos contacto
			GestorContacto gestorContacto = new GestorContacto(gestorDB.getConnection());
			gestorContacto.modificaEntidad(dtoUsuario.getDtoContacto());
			
			//Modificamos telefono
			GestorTelefono gestorTelefono = new GestorTelefono(gestorDB.getConnection());
			gestorTelefono.modificaEntidad(dtoUsuario.getDtoContacto().getDtoTelefono());
			
			//Modificamos documentoIdentificación
			GestorDocumentoIdentificacion gestorDocumIden = new GestorDocumentoIdentificacion(gestorDB.getConnection());
			gestorDocumIden.modificaEntidad(dtoUsuario.getDtoDocumentoIden());
			
			//Modificamos los datos bancarios
			if(dtoUsuario.getDtoDatosBancarios() != null){
				GestorDatosBancarios gestorDatosBancarios = new GestorDatosBancarios(gestorDB.getConnection());
				gestorDatosBancarios.modificaEntidad(dtoUsuario.getDtoDatosBancarios());
			}
			
			//Modificamos el usuario
			GestorUsuario gestorUsuario = new GestorUsuario(gestorDB.getConnection());
			gestorUsuario.modificaEntidad(dtoUsuario);
			
			gestorDB.getConnection().commit();
			
		}catch(Exception e){
			gestorDB.rollback();
			throw new OperationErrorBD("Error al recuperar la información de los usuarios......");
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
			centroDocente.setEstado(Constantes.REGISTRO_ACTIVO); //Recogemos sólo los centros activos
			dtoCentroDocente.setCentroDocente(centroDocente);
			return gestorCentroDocente.consultaEntidades(dtoCentroDocente);
		}catch(Exception e){
			throw new OperationErrorBD("Error al recuperar los centros docentes de la universidad[" + idUniversidad +"] " +  e.getMessage());
		}
	}
	
	public  DTOUsuario getUsuario(DTOUsuario dtoUsuario) throws RemoteException, OperationErrorBD {
		try{
			System.out.println("Recuperando datos del usuario..... "+ dtoUsuario.getUsuario().getCodigo());
			GestorUsuario gestorUsuario = new GestorUsuario(gestorDB.getConnection());
			return gestorUsuario.consultaEntidad(dtoUsuario);
		}catch(Exception e){
			throw new OperationErrorBD("Error al recuperar el usuario......");
		}

	}
	
	public  List<DTOUsuario> getUsuarios(DTOUsuario dtoUsuario) throws RemoteException, OperationErrorBD {
		try{
			System.out.println("Recuperando usuarios.....");
			GestorUsuario gestorUsuario = new GestorUsuario(gestorDB.getConnection());
			System.out.println("gestorUsuario Creado......");
			return gestorUsuario.consultaEntidadesByView((DTOUsuarioConsulta) dtoUsuario);
		}catch(Exception e){
			throw new OperationErrorBD("Error al recuperar la información de los usuarios......");
		}
	}
	
	
	public List<DTOTipoEvento> getTiposEventos() throws RemoteException, OperationErrorBD {
		try{
			GestorTipoEvento gestorTipoEvento = new GestorTipoEvento(gestorDB.getConnection());
			DTOTipoEvento dtoTipoEvento = new DTOTipoEvento();
			dtoTipoEvento.setTipoEvento(new TipoEvento());
			return gestorTipoEvento.consultaEntidades(dtoTipoEvento);
		}catch(Exception e){
			throw new OperationErrorBD("Error al recuperar los tipos de Evento: " + e.getMessage());
		}
	}
	
	public DTOUsuario loginUsuario(Usuario userLogin) throws RemoteException,OperationErrorLogin{
		try{
			GestorUsuario gestorUsuario = new GestorUsuario(gestorDB.getConnection());
			return gestorUsuario.loginUsuario(userLogin.getCodigo(), userLogin.getContrasena());
		}catch(Exception e){
			throw new OperationErrorLogin(e.getMessage());
		}
	}
	
	
	public void updatePassword(DTOUsuario user) throws RemoteException,OperationErrorLogin{
		try{
			gestorDB.getConnection().setAutoCommit(false);
			GestorUsuario gestorUsuario = new GestorUsuario(gestorDB.getConnection());
			gestorUsuario.updatePassword(user.getUsuario());
			gestorDB.getConnection().commit();
		}catch(Exception e){
			try {
				gestorDB.rollback();
			} catch (OperationErrorBD e1) {
				throw new OperationErrorLogin(e.getMessage());
			}
			throw new OperationErrorLogin(e.getMessage());
		}
	}

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
	public void checkIN(DTOInscripcion inscripcion) throws RemoteException, OperationErrorBD{
		try{
			gestorDB.getConnection().setAutoCommit(false);
			GestorInscripcion gestorInscripcion = new GestorInscripcion(gestorDB.getConnection());
			gestorInscripcion.checkIN(inscripcion);
			gestorDB.getConnection().commit();
		}catch(Exception e){
			gestorDB.rollback();
			throw new OperationErrorBD("Error al hacer checkIN de la inscripci—n: " + e.getMessage());
		}
	}
	
	/**
	 * A efectos de error, que podamos desmarcar la asistencia a un evento del asistente.
	 * @param inscripcion
	 * @throws RemoteException
	 * @throws OperationErrorLogin
	 */
	public void checkOUT(DTOInscripcion inscripcion) throws RemoteException, OperationErrorBD{
		try{
			gestorDB.getConnection().setAutoCommit(false);
			GestorInscripcion gestorInscripcion = new GestorInscripcion(gestorDB.getConnection());
			gestorInscripcion.checkOUT(inscripcion);
			gestorDB.getConnection().commit();
		}catch(Exception e){
			gestorDB.rollback();
			throw new OperationErrorBD("Error al hacer checkOUT de la inscripci—n: " + e.getMessage());
		}
	}

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
	public List<DTOEventoCalendario> getEventosCalendario(DTOEventoCalendario dtoEventoCalendario) throws RemoteException, OperationErrorBD {
		try{
			System.out.println("Recuperando eventos.....");
			GestorEvento gestorEvento = new GestorEvento(gestorDB.getConnection());
			return gestorEvento.getEventosCalendario(dtoEventoCalendario);
		}catch(Exception e){
			throw new OperationErrorBD("Error al recuperar la informaci— de los eventos del calendario.....");
		}
	}

	/**
	 * Pasa un evento a estado cancelado
	 * @param dtoEvento
	 * @throws RemoteException
	 * @throws OperationErrorBD
	 */
	public void bajaEvento(DTOEvento dtoEvento) throws RemoteException, OperationErrorBD {
		try{
			gestorDB.getConnection().setAutoCommit(false);
			System.out.println("Damos de baja el evento.....");
			GestorEvento gestorEvento = new GestorEvento(gestorDB.getConnection());
			gestorEvento.eliminaEntidad(dtoEvento);
			gestorDB.getConnection().commit();
		}catch(Exception e){
			gestorDB.rollback();
			throw new OperationErrorBD("Error al dar de baja el evento......");
		}
	}
	
	public void insertaCentroDocente(DTOCentroDocente dtoCentroDocente) throws RemoteException, OperationErrorBD {
		try {
			
			gestorDB.getConnection().setAutoCommit(false);
			
			//Insertamos en contacto
			GestorContacto gestorContacto = new GestorContacto(gestorDB.getConnection());
			Integer idContacto = gestorContacto.insertaEntidadRetId(dtoCentroDocente.getDtoContacto());
			
			//Insertamos la universidad
			//GestorUniversidad gestorUniversidad = new GestorUniversidad(gestorDB.getConnection());
			//Integer idUniversidad = gestorUniversidad.insertaEntidadRetId(dtoCentroDocente.getDtoUniversidad());
			
			dtoCentroDocente.getCentroDocente().setIdContacto(idContacto);
			//dtoCentroDocente.getCentroDocente().setIdUniversidad(idUniversidad);
			
			GestorCentroDocente gestorCentroDocente = new GestorCentroDocente(gestorDB.getConnection());
			gestorCentroDocente.insertaEntidad(dtoCentroDocente);
			
			gestorDB.getConnection().commit();
			
		} catch (Exception e) {
			gestorDB.rollback();
			throw new OperationErrorBD("Error al insertar el usuario: " + e.getMessage());
		}
		
	}
	
	/**
	 * Retorna un evento a partir de un dtoEvento(idEvento)
	 * @param dtoEvento
	 * @return
	 * @throws RemoteException
	 * @throws OperationErrorBD
	 */
	public DTOEvento getEvento(DTOEvento dtoEvento) throws RemoteException, OperationErrorBD {
		try{
			System.out.println("Recuperando datos del evento..... " + dtoEvento.getEvento().getIdEvento().toString());
			GestorEvento gestorEvento = new GestorEvento(gestorDB.getConnection());
			return gestorEvento.consultaEntidad(dtoEvento);
		}catch(Exception e){
			throw new OperationErrorBD("Error al recuperar el evento......");
		}

	}

	/**
	 * Genera un nuevo evento en el calendario AUE
	 * @param dtoEvento
	 * @throws RemoteException
	 * @throws OperationErrorBD
	 */
	public void insertaEvento(DTOEvento dtoEvento) throws RemoteException, OperationErrorBD {
		try {
			
			gestorDB.getConnection().setAutoCommit(false);
			
			// TODO 1: Insertamos en requisitos
			// TODO 2: Insertamos en rol/plazas
			
			//Insertamos el evento
			GestorEvento gestorEvento = new GestorEvento(gestorDB.getConnection());
			gestorEvento.insertaEntidad(dtoEvento);
			System.out.println("Evento insertado correctamente: ");
			gestorDB.getConnection().commit();			
		} catch (Exception e) {
			gestorDB.rollback();
			throw new OperationErrorBD("Error al insertar el evento: " + e.getMessage());
		}
	}	

	/**
	 * Modfica un evento
	 * @param dtoEvento
	 * @throws RemoteException
	 * @throws OperationErrorBD
	 */
	public void modificaEvento(DTOEvento dtoEvento) throws RemoteException, OperationErrorBD {
		try{
			
			gestorDB.getConnection().setAutoCommit(false);
			
			// TODO 1: Modificamos en requisitos
			//GestorEventoRequisitos gestorEventoRequisitos = new GestorEventoRequisitos(gestorDB.getConnection());
			//gestorEventoRequisitos.modificaEntidad(dtoEvento.getDtoEventoRequisitos());
			
			// TODO 2: Modificamos en rol/plazas
			//GestorEventoRolPlazas gestorEventoRolPlazas = new GestorEventoRolPlazas(gestorDB.getConnection());
			//update
			
			//Modificamos el evento
			GestorEvento gestorEvento = new GestorEvento(gestorDB.getConnection());
			gestorEvento.modificaEntidad(dtoEvento);
			
			gestorDB.getConnection().commit();
		}catch(Exception e){
			gestorDB.rollback();
			throw new OperationErrorBD("Error al modificar el evento: " + e.getMessage());
		}
		
	}
	
	
	public List<DTOEvento> getEventos(DTOEvento dtoEvento) throws RemoteException, OperationErrorBD{
		try{
			GestorEvento gestorEvento = new GestorEvento(gestorDB.getConnection());
			return (gestorEvento.consultaEntidades(dtoEvento));
		}catch(Exception e){
			throw new OperationErrorBD("Error al recuperar la informaci—n de los eventos......");
		}
	}

	public List<DTOCentroDocente> consultaCentrosDocentes(DTOCentroDocente dtoCentroDocente) throws RemoteException, OperationErrorBD{
		try{
			System.out.println("Consultamos el centro docente con id: " + ((DTOCentroDocenteConsulta) dtoCentroDocente).getCentroDocenteView().getIdCentro());
			GestorCentroDocente getorCentroDocente = new GestorCentroDocente(gestorDB.getConnection());
			return getorCentroDocente.consultaEntidadesByView((DTOCentroDocenteConsulta) dtoCentroDocente);
		}catch(Exception e){
			throw new OperationErrorBD("Error al recuperar la información de los centros docentes......");
		}
	}
	
	
	public  DTOCentroDocente getCentroDocente(DTOCentroDocente dtoCentroDocente) throws RemoteException, OperationErrorBD {
		try{
			System.out.println("Damos de baja el centro docente con id: " + dtoCentroDocente.getCentroDocente().getIdCentro());
			GestorCentroDocente gestorCentroDocente = new GestorCentroDocente(gestorDB.getConnection());
			return gestorCentroDocente.consultaEntidad(dtoCentroDocente);
		}catch(Exception e){
			throw new OperationErrorBD("Error al recuperar el centro docente...");
		}

	}
	
	public void bajaCentroDocente(DTOCentroDocente dtoCentroDocente) throws RemoteException, OperationErrorBD {
		try{
			System.out.println("Damos de baja el centro docente con id: " + dtoCentroDocente.getCentroDocente().getIdCentro());
			gestorDB.getConnection().setAutoCommit(false);
			GestorCentroDocente gestorCenDoc = new GestorCentroDocente(gestorDB.getConnection());
			gestorCenDoc.eliminaEntidad(dtoCentroDocente);
			gestorDB.getConnection().commit();
			System.out.println("centro docente eliminado correctamente");
		}catch(Exception e){
			gestorDB.rollback();
			throw new OperationErrorBD("Error al dar de baja el centro docente...");
		}
	}
	
	public void modificaCentroDocente(DTOCentroDocente dtoCentroDocente) throws RemoteException, OperationErrorBD{
		try{
			
			gestorDB.getConnection().setAutoCommit(false);
			
			System.out.println("Modificamos el centro docente con id: " + dtoCentroDocente.getCentroDocente().getIdCentro());
			
			//Modificamos contacto
			GestorContacto gestorContacto = new GestorContacto(gestorDB.getConnection());
			gestorContacto.modificaEntidad(dtoCentroDocente.getDtoContacto());
			System.out.println("contacto modificado correctamente");
			
			//Modificamos telefono
			GestorTelefono gestorTelefono = new GestorTelefono(gestorDB.getConnection());
			gestorTelefono.modificaEntidad(dtoCentroDocente.getDtoContacto().getDtoTelefono());
			System.out.println("telefono modificado correctamente");
			
			//Modificamos el centro docente
			GestorCentroDocente gestorCentroDocente = new GestorCentroDocente(gestorDB.getConnection());
			gestorCentroDocente.modificaEntidad(dtoCentroDocente);
			
			gestorDB.getConnection().commit();
			System.out.println("centro docente modificado correctamente");
			
		}catch(Exception e){
			gestorDB.rollback();
			throw new OperationErrorBD("Error al modificar el centro docente...");
		}
	}
	
	
	public  List<DTOInscripcionConsulta> buscaInscripcionesUsuario(DTOInscripcionConsulta dtoInscripcionConsulta)  throws RemoteException, OperationErrorBD, Exception{
		try{
			System.out.println("Recuperando Inscripciones dle usuario.....");
			GestorInscripcion gestorInscipcion = new GestorInscripcion(gestorDB.getConnection());
			System.out.println("gestorInscripcion Creado......");
			return gestorInscipcion.consultaEntidadesByDates(dtoInscripcionConsulta);
		}catch(Exception e){
			throw new OperationErrorBD("Error al recuperar la información de las Inscripciones de usuario......");
		}
	}

	/**
	 * Retorna las inscripciones segun un dtoInscripcion
	 * @param dtoInscripcion
	 * @return
	 * @throws RemoteException
	 * @throws OperationErrorBD
	 */
	public List<DTOInscripcion> getInscripciones(DTOInscripcion dtoInscripcion) throws RemoteException, OperationErrorBD {
		try{
			System.out.println("Recuperando inscripciones.....");
			GestorInscripcion gestorInscripcion = new GestorInscripcion(gestorDB.getConnection());
			System.out.println("gestorinscripcion Creado......");
			return gestorInscripcion.consultaEntidades(dtoInscripcion);
		}catch(Exception e){
			throw new OperationErrorBD("Error al recuperar la informaci—n de las inscripciones......");
		}
	}

	/**
	 * Retorna las inscripciones de un evento finalizado
	 * @param dtoInscripcion
	 * @return
	 * @throws RemoteException
	 * @throws OperationErrorBD
	 */
	public List<DTOInscripcion> getInscripcionesByEventoFinalizado(DTOInscripcion dtoInscripcion) throws RemoteException, OperationErrorBD {
		try{
			System.out.println("Recuperando inscripciones.....");
			GestorInscripcion gestorInscripcion = new GestorInscripcion(gestorDB.getConnection());
			System.out.println("gestorinscripcion Creado......");
			return gestorInscripcion.consultaEntidadesByEventoFinalizado(dtoInscripcion);
		}catch(Exception e){
			throw new OperationErrorBD("Error al recuperar la informaci—n de las inscripciones......");
		}
	}

	public  List<DTOInscripcion> buscaEventosInscrito(DTOInscripcion dtoInscripcion)  throws RemoteException, OperationErrorBD, Exception{
		try{
			System.out.println("Recuperando Inscripciones dle usuario por fecha Evento");
			GestorInscripcion gestorInscipcion = new GestorInscripcion(gestorDB.getConnection());
			System.out.println("gestorInscripcion Creado......");
			return gestorInscipcion.consultaEntidadesByDatesV2(dtoInscripcion);
		}catch(Exception e){
			throw new OperationErrorBD("Error al recuperar la información de las Inscripciones de usuario......");
		}
	}
	
	
	public Boolean usuarioCentrosVinculados(Usuario usuario) throws RemoteException, OperationErrorBD{
		try{
			GestorUsuario gestorUsuario = new GestorUsuario(gestorDB.getConnection());
			return gestorUsuario.usuarioCentrosVinculados(usuario);
		}catch(Exception e){
			throw new OperationErrorBD("Error al recuperar la información de las Inscripciones de usuario......");
		}
	}

	public List<DTOEvento> getEventosFinalizados(DTOEvento dtoEvento) throws RemoteException, OperationErrorBD {
		try{
			GestorEvento gestorEvento = new GestorEvento(gestorDB.getConnection());
			return (gestorEvento.consultaEventosFinalizados(dtoEvento));
		}catch(Exception e){
			throw new OperationErrorBD("Error al recuperar la informaci—n de los eventos......");
		}
	}
		
}
