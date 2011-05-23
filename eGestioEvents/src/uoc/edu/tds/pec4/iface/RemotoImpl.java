package uoc.edu.tds.pec4.iface;

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
import uoc.edu.tds.pec4.gestores.GestorCentroDocente;
import uoc.edu.tds.pec4.gestores.GestorContacto;
import uoc.edu.tds.pec4.gestores.GestorDatosBancarios;
import uoc.edu.tds.pec4.gestores.GestorDisco;
import uoc.edu.tds.pec4.gestores.GestorDocumentoIdentificacion;
import uoc.edu.tds.pec4.gestores.GestorEvento;
import uoc.edu.tds.pec4.gestores.GestorInscripcion;
import uoc.edu.tds.pec4.gestores.GestorPais;
import uoc.edu.tds.pec4.gestores.GestorTelefono;
import uoc.edu.tds.pec4.gestores.GestorTipoDocumento;
import uoc.edu.tds.pec4.gestores.GestorTipoEvento;
import uoc.edu.tds.pec4.gestores.GestorTipoRol;
import uoc.edu.tds.pec4.gestores.GestorTipoTelefono;
import uoc.edu.tds.pec4.gestores.GestorUniversidad;
import uoc.edu.tds.pec4.gestores.GestorUsuario;

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
			
			System.out.println("Damos de alta el usuario con código: " + dtoUsuario.getUsuario().getCodigo());
			gestorDB.getConnection().setAutoCommit(false);
			
			//Insertamos en contacto
			GestorContacto gestorContacto = new GestorContacto(gestorDB.getConnection());
			Integer idContacto = gestorContacto.insertaEntidadRetId(dtoUsuario.getDtoContacto());
			System.out.println("contacto dado de alta correctamente");
			
			GestorTelefono gestorTelefono = new GestorTelefono(gestorDB.getConnection());
			dtoUsuario.getDtoContacto().getDtoTelefono().getTelefono().setIdContacto(idContacto);
			gestorTelefono.insertaEntidad(dtoUsuario.getDtoContacto().getDtoTelefono());
			System.out.println("telefono dado de alta correctamente");
			
			//Insertamos el documentoIdentificación
			GestorDocumentoIdentificacion gestorDocumIden = new GestorDocumentoIdentificacion(gestorDB.getConnection());
			Integer idDocumentoIden = gestorDocumIden.insertaEntidadRetId(dtoUsuario.getDtoDocumentoIden());
			dtoUsuario.getUsuario().setIdDocumentoIdentificacion(idDocumentoIden);
			System.out.println("docucmento insertado correctamente");
			
			//Insertamos los datos bancarios
			if(dtoUsuario.getDtoDatosBancarios() != null){
				GestorDatosBancarios gestorDatosBancarios = new GestorDatosBancarios(gestorDB.getConnection());
				Integer idBancario = gestorDatosBancarios.insertaEntidadRetId(dtoUsuario.getDtoDatosBancarios());
				dtoUsuario.getUsuario().setIdDatosBancarios(idBancario);
				System.out.println("datos bancarios insertados correctamente");
			}
			
			//Insertamos el usuario
			GestorUsuario gestorUsuario = new GestorUsuario(gestorDB.getConnection());
			dtoUsuario.getUsuario().setIdContacto(idContacto);
			gestorUsuario.insertaEntidad(dtoUsuario);
			gestorDB.getConnection().commit();
			System.out.println("usuario insertado correctamente: " + dtoUsuario.getUsuario().getCodigo());
			return dtoUsuario.getUsuario().getCodigo();
			
		} catch (Exception e) {
			gestorDB.rollback();
			throw new OperationErrorBD("Error al insertar el usuario: " + e.getMessage());
		}
		
	}
	
	public void bajaUsuario(DTOUsuario dtoUsuario) throws RemoteException, OperationErrorBD {
		try{
			System.out.println("Damos de baja el usuario con código: " + dtoUsuario.getUsuario().getCodigo());
			gestorDB.getConnection().setAutoCommit(false);
			System.out.println("damos de baja el usuario.....");
			GestorUsuario gestorUsuario = new GestorUsuario(gestorDB.getConnection());
			gestorUsuario.eliminaEntidad(dtoUsuario);
			System.out.println("usuario dado de baja correctamente");
			gestorDB.getConnection().commit();
		}catch(Exception e){
			gestorDB.rollback();
			throw new OperationErrorBD("Error al dar de baja el usuario...");
		}
		
	}
	
	public void modificaUsuario(DTOUsuario dtoUsuario) throws RemoteException, OperationErrorBD {
		try{
			
			gestorDB.getConnection().setAutoCommit(false);
			
			System.out.println("Modificamos el usuario con código: " + dtoUsuario.getUsuario().getCodigo());
			
			//Modificamos contacto
			GestorContacto gestorContacto = new GestorContacto(gestorDB.getConnection());
			gestorContacto.modificaEntidad(dtoUsuario.getDtoContacto());
			System.out.println("contacto modificado correctamente");
			
			//Modificamos telefono
			GestorTelefono gestorTelefono = new GestorTelefono(gestorDB.getConnection());
			gestorTelefono.modificaEntidad(dtoUsuario.getDtoContacto().getDtoTelefono());
			System.out.println("teléfono modificado correctamente");
			
			//Modificamos documentoIdentificación
			GestorDocumentoIdentificacion gestorDocumIden = new GestorDocumentoIdentificacion(gestorDB.getConnection());
			gestorDocumIden.modificaEntidad(dtoUsuario.getDtoDocumentoIden());
			System.out.println("documento identificativo modificado correctamente");
			
			//Modificamos los datos bancarios
			if(dtoUsuario.getDtoDatosBancarios() != null){
				GestorDatosBancarios gestorDatosBancarios = new GestorDatosBancarios(gestorDB.getConnection());
				gestorDatosBancarios.modificaEntidad(dtoUsuario.getDtoDatosBancarios());
				System.out.println("datos bancarios modificados correctamente");
			}
			
			//Modificamos el usuario
			GestorUsuario gestorUsuario = new GestorUsuario(gestorDB.getConnection());
			gestorUsuario.modificaEntidad(dtoUsuario);
			System.out.println("usuario modificado correctamente");
			
			gestorDB.getConnection().commit();
			
		}catch(Exception e){
			gestorDB.rollback();
			throw new OperationErrorBD("Error al modificar la información del usuario...");
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
			System.out.println("Recuperamos la información de los paises");
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
			System.out.println("Recuperamos la información de los tipos de teléfono");
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
			System.out.println("Recuperamos la información de las universidades");
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
			System.out.println("Recuperamos la información de los tipos de roles");
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
			System.out.println("Rellenamos los centros de la universidad " +  idUniversidad);
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
	
	public  DTOUsuario getUsuario(DTOUsuario dtoUsuario) throws RemoteException, OperationErrorBD {
		try{
			System.out.println("Recuperamos la información del usuario con código " +  dtoUsuario.getUsuario().getCodigo());
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
			System.out.println("Recuperamos la información de los tipos de evento");
			GestorTipoEvento gestorTipoEvento = new GestorTipoEvento(gestorDB.getConnection());
			DTOTipoEvento dtoTipoEvento = new DTOTipoEvento();
			dtoTipoEvento.setTipoEvento(new TipoEvento());
			return gestorTipoEvento.consultaEntidades(dtoTipoEvento);
		}catch(Exception e){
			throw new OperationErrorBD("Error al recuperar los tipos de Evento: " + e.getMessage());
		}
	}
	
	public Usuario loginUsuario(Usuario userLogin) throws RemoteException,OperationErrorLogin{
		try{
			System.out.println("Login del usuario: " + userLogin.getCodigo());
			GestorUsuario gestorUsuario = new GestorUsuario(gestorDB.getConnection());
			return gestorUsuario.loginUsuario(userLogin.getCodigo(), userLogin.getContrasena());
		}catch(Exception e){
			throw new OperationErrorLogin(e.getMessage());
		}
	}
	
	
	public void updatePassword(Usuario user) throws RemoteException,OperationErrorLogin{
		try{
			System.out.println("Actualizamos password del usuario: " + user.getCodigo());
			gestorDB.getConnection().setAutoCommit(false);
			GestorUsuario gestorUsuario = new GestorUsuario(gestorDB.getConnection());
			gestorUsuario.updatePassword(user);
			gestorDB.getConnection().commit();
			System.out.println("password modificado correctamente");
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
	public void checkIN(DTOInscripcion inscripcion) throws RemoteException,OperationErrorLogin{
		try{
			GestorInscripcion gestorInscripcion = new GestorInscripcion(gestorDB.getConnection());
			gestorInscripcion.checkIN(inscripcion);
		}catch(Exception e){
			throw new OperationErrorLogin(e.getMessage());
		}
	}
	
	/**
	 * A efectos de error, que podamos desmarcar la asistencia a un evento del asistente.
	 * @param inscripcion
	 * @throws RemoteException
	 * @throws OperationErrorLogin
	 */
	public void checkOUT(DTOInscripcion inscripcion) throws RemoteException,OperationErrorLogin{
		try{
			GestorInscripcion gestorInscripcion = new GestorInscripcion(gestorDB.getConnection());
			gestorInscripcion.checkOUT(inscripcion);
		}catch(Exception e){
			throw new OperationErrorLogin(e.getMessage());
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
	public List<DTOEventoCalendario> getEventosCalendario(
			DTOEventoCalendario dtoEventoCalendario) throws RemoteException,
			OperationErrorBD {
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
			
			System.out.println("Insertamos el centro con id: " + dtoCentroDocente.getCentroDocente().getIdCentro());
			gestorDB.getConnection().setAutoCommit(false);
			
			//Insertamos en contacto
			GestorContacto gestorContacto = new GestorContacto(gestorDB.getConnection());
			Integer idContacto = gestorContacto.insertaEntidadRetId(dtoCentroDocente.getDtoContacto());
			dtoCentroDocente.getCentroDocente().setIdContacto(idContacto);
			System.out.println("contacto dato de alta correctamente");
			
			//Modificamos telefono
			dtoCentroDocente.getDtoContacto().getDtoTelefono().getTelefono().setIdContacto(idContacto);
			GestorTelefono gestorTelefono = new GestorTelefono(gestorDB.getConnection());
			gestorTelefono.insertaEntidad(dtoCentroDocente.getDtoContacto().getDtoTelefono());
			System.out.println("telefono insertado correctamente");
			
			GestorCentroDocente gestorCentroDocente = new GestorCentroDocente(gestorDB.getConnection());
			gestorCentroDocente.insertaEntidad(dtoCentroDocente);
			System.out.println("centro docente insertado correctamente");
			
			gestorDB.getConnection().commit();
			
		} catch (Exception e) {
			gestorDB.rollback();
			throw new OperationErrorBD("Error al insertar centro docente: " + e.getMessage());
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
	
}
