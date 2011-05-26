/**
 * 
 */
package uoc.edu.tds.pec4.gestores;

/**
 * @author jgarcia
 *
 */

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import uoc.edu.tds.pec4.beans.Inscripcion;
import uoc.edu.tds.pec4.daos.DaoInscripcion;
import uoc.edu.tds.pec4.dtos.DTOAsistente;
import uoc.edu.tds.pec4.dtos.DTOEvento;
import uoc.edu.tds.pec4.dtos.DTOEventoPlus;
import uoc.edu.tds.pec4.dtos.DTOInscripcion;
import uoc.edu.tds.pec4.excepciones.OperationErrorDatosFormulario;
import uoc.edu.tds.pec4.utils.Utils;

public class GestorInscripcion extends GestorEntidad<DTOInscripcion>{

	public GestorInscripcion(Connection connection) throws Exception {
		super(connection);
	}
	
	@Override
	public DTOInscripcion consultaEntidad(DTOInscripcion criteris) throws Exception {
		try{
			List<DTOInscripcion> lstDTOInscripcion = this.consultaEntidades(criteris);
			if(lstDTOInscripcion != null && lstDTOInscripcion.size() > 0){
				return lstDTOInscripcion.get(0);
			}
		}catch(Exception e){
			throw e;
		}
		return null;
	}
	
	@Override
	public List<DTOInscripcion> consultaEntidades(DTOInscripcion criteris) throws Exception {
		try{
			DaoInscripcion dao = new DaoInscripcion(connection);
			List<Inscripcion> lstInscripcion = dao.select(criteris.getInscripcion());
			if(lstInscripcion != null && lstInscripcion.size() > 0){
				List<DTOInscripcion> lstDTOInscripcion = new ArrayList<DTOInscripcion>();
				for(Inscripcion inscripcion : lstInscripcion){
					//A–adimos Inscripcion
					
					DTOInscripcion dtoInscripcion = new DTOInscripcion();
					dtoInscripcion.setInscripcion(inscripcion);
					
					//A–adimos el Evento
					GestorEvento gestorEvento = new GestorEvento(connection);
					DTOEvento dtoEvento = gestorEvento.consultaEntidadById(inscripcion.getIdEvento());
					if(dtoEvento != null) dtoInscripcion.setDtoEvento(dtoEvento);
					
					//Añadimos el Usuario
					GestorUsuario gestorUsuario = new GestorUsuario(connection);
					DTOAsistente dtoAsistente = (DTOAsistente) gestorUsuario.consultaEntidadById(inscripcion.getCodigo());
					if(dtoAsistente != null) dtoInscripcion.setDtoAsistente(dtoAsistente);
					
					lstDTOInscripcion.add(dtoInscripcion);
				}
				return lstDTOInscripcion;
			}
		}catch(Exception e){
			throw new Exception();
		}
		return null;
	}

	public List<DTOInscripcion> consultaEntidadesByEventoFinalizado(DTOInscripcion criteris) throws Exception {
		try{
			DaoInscripcion dao = new DaoInscripcion(connection);
			List<Inscripcion> lstInscripcion = dao.selectByEventoFinalizado(criteris.getInscripcion());
			if(lstInscripcion != null && lstInscripcion.size() > 0){
				List<DTOInscripcion> lstDTOInscripcion = new ArrayList<DTOInscripcion>();
				for(Inscripcion inscripcion : lstInscripcion){
					
					//A–adimos Inscripcion
					DTOInscripcion dtoInscripcion = new DTOInscripcion();
					dtoInscripcion.setInscripcion(inscripcion);
					
					//A–adimos el Evento
					GestorEvento gestorEvento = new GestorEvento(connection);
					DTOEvento dtoEvento = gestorEvento.consultaEntidadById(inscripcion.getIdEvento());
					if(dtoEvento != null) dtoInscripcion.setDtoEvento(dtoEvento);
					
					//Añadimos el Usuario
					GestorUsuario gestorUsuario = new GestorUsuario(connection);
					DTOAsistente dtoAsistente = (DTOAsistente) gestorUsuario.consultaEntidadById(inscripcion.getCodigo());
					if(dtoAsistente != null) dtoInscripcion.setDtoAsistente(dtoAsistente);
					
					lstDTOInscripcion.add(dtoInscripcion);
				}
				return lstDTOInscripcion;
			}
		}catch(Exception e){
			throw new Exception();
		}
		return null;
	}
	
	
	public List<DTOInscripcion> consultaEntidadesByDatesV2(DTOInscripcion criteris) throws Exception {
		try{
			DaoInscripcion dao = new DaoInscripcion(connection);
			List<Inscripcion> lstInscripcion = dao.select(criteris.getInscripcion());
			if(lstInscripcion != null && lstInscripcion.size() > 0){
				List<DTOInscripcion> lstDTOInscripcion = new ArrayList<DTOInscripcion>();
				for(Inscripcion inscripcion : lstInscripcion){
					//A–adimos Inscripcion
					
					DTOInscripcion dtoInscripcion = new DTOInscripcion();
					dtoInscripcion.setInscripcion(inscripcion);
					
					//A–adimos el Evento
					GestorEvento gestorEvento = new GestorEvento(connection);
					DTOEvento dtoEvento = gestorEvento.consultaEntidadById(inscripcion.getIdEvento());
					if(dtoEvento != null) dtoInscripcion.setDtoEvento(dtoEvento);
					System.out.println("Dto evento recibido...."+criteris.getDtoEvento().getEvento().getIdTipoEvento());
					System.out.println("Dto evento encontrado...."+dtoEvento.getEvento().getIdTipoEvento());
					//Añadimos el Usuario
					GestorUsuario gestorUsuario = new GestorUsuario(connection);
					DTOAsistente dtoAsistente = (DTOAsistente) gestorUsuario.consultaEntidadById(inscripcion.getCodigo());
					if(dtoAsistente != null) dtoInscripcion.setDtoAsistente(dtoAsistente);
					
					if ((
							((dtoEvento.getEvento().getFechaInicioCelebracion().after(criteris.getDtoEvento().getEvento().getFechaInicioCelebracion())
										|| dtoEvento.getEvento().getFechaInicioCelebracion().equals(criteris.getDtoEvento().getEvento().getFechaInicioCelebracion()))
							
									&& ((dtoEvento.getEvento().getFechaInicioCelebracion().before(criteris.getDtoEvento().getEvento().getFechaFinCelebracion()))
											||(dtoEvento.getEvento().getFechaInicioCelebracion().equals(criteris.getDtoEvento().getEvento().getFechaFinCelebracion()))))
											
							 || ((dtoEvento.getEvento().getFechaFinCelebracion().after(criteris.getDtoEvento().getEvento().getFechaInicioCelebracion())
										|| dtoEvento.getEvento().getFechaFinCelebracion().equals(criteris.getDtoEvento().getEvento().getFechaInicioCelebracion()))
										
										&& ((dtoEvento.getEvento().getFechaFinCelebracion().before(criteris.getDtoEvento().getEvento().getFechaFinCelebracion()))
												||(dtoEvento.getEvento().getFechaFinCelebracion().equals(criteris.getDtoEvento().getEvento().getFechaFinCelebracion()))))		
									
							))  {
							
							System.out.println("Se encuentra entre las fechas......");
							if (criteris.getDtoEvento().getEvento().getIdTipoEvento().equals(dtoEvento.getEvento().getIdTipoEvento()))
									lstDTOInscripcion.add(dtoInscripcion);
					}
					
				}
				return lstDTOInscripcion;
			}
		}catch(Exception e){
			throw new Exception();
		}
		return null;
	}
	

	public DTOInscripcion generarCodigoAsistencia(DTOInscripcion dtoInscripcion){
		
		String codigoUsuario = dtoInscripcion.getDtoAsistente().getUsuario().getCodigo();
		Integer codigoUniv = dtoInscripcion.getDtoEvento().getDtoCentroDocente().getDtoUniversidad().getUniversidad().getIdUniversidad();
		Integer codigoCentro = dtoInscripcion.getDtoEvento().getDtoCentroDocente().getCentroDocente().getIdCentro();
		Integer codigoTipoEvento = dtoInscripcion.getDtoEvento().getEvento().getIdTipoEvento();
		Integer codigoEvento = dtoInscripcion.getDtoEvento().getEvento().getIdEvento();
        Date today = new java.util.Date(System.currentTimeMillis());
        String codigoAsistencia ="";
			codigoAsistencia = (codigoUsuario +"-"+ codigoUniv+"-"+codigoCentro+"-"+codigoTipoEvento+"-"+codigoEvento+"-"+
					today.getTime());

        dtoInscripcion.getInscripcion().setCodigoAsistencia(codigoAsistencia);
        return(dtoInscripcion);
	}
	
		
	@Override
	public void insertaEntidad(DTOInscripcion newobject) throws Exception{
		try {
			GestorEvento gestorEvento = new GestorEvento(connection);
			DTOEventoPlus dtoEventoPlus = gestorEvento.getPlazasEvento(newobject.getDtoEvento());
			
			if(dtoEventoPlus == null || dtoEventoPlus.getEventoPlus().getEventoCerrado()) {
				throw new Exception("No quedan plazas libres.");
			}	
			DaoInscripcion dao = new DaoInscripcion(connection);
			DTOInscripcion dtoInscripcion = generarCodigoAsistencia(newobject);
			dao.insert(dtoInscripcion.getInscripcion());
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void modificaEntidad(DTOInscripcion criteris) throws Exception {
		try {
			DaoInscripcion dao = new DaoInscripcion(connection);
			dao.update(criteris.getInscripcion());
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void eliminaEntidad(DTOInscripcion criteris) throws Exception {
		try {
			DaoInscripcion dao = new DaoInscripcion(connection);
			dao.delete(criteris.getInscripcion());
		} catch (Exception e) {
			throw e;
		}
	}

	/*
	 * Una vez finalizado el Evento se podr‡ notificar que el asistente ha asistido
	 * al evento mediante la realizaci—n del check-IN de su inscripci—n. 
	 */
	public void checkIN(DTOInscripcion inscripcion) throws Exception {
		try {
			//TODO 1: Revisar que no se pueda hacer un checkIN si no est‡ finalizado el evento
			DaoInscripcion dao = new DaoInscripcion(connection);
			dao.checkIN(inscripcion.getInscripcion());
		} catch (Exception e) {
			throw e;
		}
	}
	
	/*
	 * A efectos de error, que podamos desmarcar la asistencia a un evento del asistente.
	 */
	public void checkOUT(DTOInscripcion inscripcion) throws Exception {
		try {
			DaoInscripcion dao = new DaoInscripcion(connection);
			dao.checkOUT(inscripcion.getInscripcion());
		} catch (Exception e) {
			throw e;
		}
	}


	
	
	
}