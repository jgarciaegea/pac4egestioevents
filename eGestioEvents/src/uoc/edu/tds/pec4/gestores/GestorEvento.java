/**
 * 
 */
package uoc.edu.tds.pec4.gestores;

/**
 * @author jgarcia
 *
 */

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import uoc.edu.tds.pec4.beans.Evento;
import uoc.edu.tds.pec4.beans.EventoCalendario;
import uoc.edu.tds.pec4.beans.EventoViewConsulta;
import uoc.edu.tds.pec4.daos.DaoEvento;
import uoc.edu.tds.pec4.dtos.DTOCentroDocente;
import uoc.edu.tds.pec4.dtos.DTOEventoCalendario;
import uoc.edu.tds.pec4.dtos.DTOEventoRequisitos;
import uoc.edu.tds.pec4.dtos.DTOEventoRolPlazas;
import uoc.edu.tds.pec4.dtos.DTOEvento;
import uoc.edu.tds.pec4.dtos.DTOTipoEvento;

public class GestorEvento extends GestorEntidad<DTOEvento>{

	public GestorEvento(Connection connection) throws Exception {
		super(connection);
	}
	
	@Override
	public DTOEvento consultaEntidad(DTOEvento criteris) throws Exception {
		try{
			List<DTOEvento> lstDTOEvento = this.consultaEntidades(criteris);
			if(lstDTOEvento != null && lstDTOEvento.size() > 0){
				return lstDTOEvento.get(0);
			}
		}catch(Exception e){
			throw e;
		}
		return null;
	}
	
	@Override
	public List<DTOEvento> consultaEntidades(DTOEvento criteris) throws Exception {
		try{
			DaoEvento dao = new DaoEvento(connection);
			List<Evento> lstEvento = dao.select(criteris.getEvento());
			if(lstEvento != null && lstEvento.size() > 0){
				List<DTOEvento> lstDTOEvento = new ArrayList<DTOEvento>();
				for(Evento evento : lstEvento){
					//A–adimos Evento
					DTOEvento dtoEvento = new DTOEvento();
					dtoEvento.setEvento(evento);
					rellenaObjeto(dtoEvento);

					lstDTOEvento.add(dtoEvento);
				}
				return lstDTOEvento;
			}
		}catch(Exception e){
			throw new Exception();
		}
		return null;
	}

	public List<DTOEvento> consultaEventosFinalizados(DTOEvento criteris) throws Exception {
		try{
			DaoEvento dao = new DaoEvento(connection);
			List<Evento> lstEvento = dao.selectEventosFinalizados(criteris.getEvento());
			if(lstEvento != null && lstEvento.size() > 0){
				List<DTOEvento> lstDTOEvento = new ArrayList<DTOEvento>();
				for(Evento evento : lstEvento){
					//A–adimos Evento
					DTOEvento dtoEvento = new DTOEvento();
					dtoEvento.setEvento(evento);
					rellenaObjeto(dtoEvento);

					lstDTOEvento.add(dtoEvento);
				}
				return lstDTOEvento;
			}
		}catch(Exception e){
			throw new Exception();
		}
		return null;
	}
	
	/*
	 * Rellenamos la información genérica para todos los tipos de objeto DTOUsario
	 * en este caso es el centro Docente, el contacto y el documento de identificación
	 */
	private void rellenaObjeto (DTOEvento dtoEvento) throws Exception{
		try{
			//A–adimos el CentroDocente
			if(dtoEvento.getEvento().getIdCentro() != null){
				GestorCentroDocente gestorCentroDocente = new GestorCentroDocente(connection);
				DTOCentroDocente dtoCentroDocente = gestorCentroDocente.consultaEntidadById(dtoEvento.getEvento().getIdCentro());
				if(dtoCentroDocente != null) dtoEvento.setDtoCentroDocente(dtoCentroDocente);
			}
			
			//A–adimos el TipoEvento
			if(dtoEvento.getEvento().getIdTipoEvento() != null){
				GestorTipoEvento gestorTipoEvento = new GestorTipoEvento(connection);
				DTOTipoEvento dtoTipoEvento = gestorTipoEvento.consultaEntidadById(dtoEvento.getEvento().getIdTipoEvento());
				if(dtoTipoEvento != null) dtoEvento.setDtoTipoEvento(dtoTipoEvento);
			}
			//A–adimos el EventoRequisitos
			GestorEventoRequisitos gestorEventoRequisitos = new GestorEventoRequisitos(connection);
			List<DTOEventoRequisitos> dtoEventoRequisitos = gestorEventoRequisitos.consultaEntidadById(dtoEvento.getEvento().getIdEvento());
			if(dtoEventoRequisitos != null) dtoEvento.setDtoEventoRequisitos(dtoEventoRequisitos);
			
			//A–adimos el EventoRequisitos
			GestorEventoRolPlazas gestorEventoRolPlazas = new GestorEventoRolPlazas(connection);
			List<DTOEventoRolPlazas> dtoEventoRolPlazas = gestorEventoRolPlazas.consultaEntidadesById(dtoEvento.getEvento().getIdEvento());
			if(dtoEventoRolPlazas != null) dtoEvento.setDtoEventoRolPlazas(dtoEventoRolPlazas);
		}catch(Exception e){
			throw new SQLException();
		}
	}

	public DTOEvento consultaEntidadById(Integer idEvento)  throws Exception {
		try{
			DTOEvento dtoEvento = new DTOEvento();
			Evento evento = new Evento();
			evento.setIdEvento(idEvento);
			dtoEvento.setEvento(evento);
			return this.consultaEntidad(dtoEvento);
		}catch(Exception e){
			throw e;
		}
	}
	
	@Override
	public void insertaEntidad(DTOEvento newobject) throws Exception{
		try {
			DaoEvento dao = new DaoEvento(connection);
			dao.insert(newobject.getEvento());
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void modificaEntidad(DTOEvento criteris) throws Exception {
		try {
			DaoEvento dao = new DaoEvento(connection);
			dao.update(criteris.getEvento());
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void eliminaEntidad(DTOEvento criteris) throws Exception {
		try {
			DaoEvento dao = new DaoEvento(connection);
			dao.delete(criteris.getEvento());
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<EventoViewConsulta> consultaEventosUsuario(EventoViewConsulta criteris) throws Exception {
		try{
			DaoEvento dao = new DaoEvento(connection);		
			List<EventoViewConsulta> lstEventoViewConsulta = dao.selectEventosUserByView(criteris);
			if(lstEventoViewConsulta != null && lstEventoViewConsulta.size() > 0){
				return lstEventoViewConsulta;
				// falta crear el DTO y devolverlo
			}
		}catch(Exception e){
			throw new Exception();
		}
		return null;
	}
	
	public List<DTOEventoCalendario> getEventosCalendario(DTOEventoCalendario criteris) throws Exception {
		try{
			
			DaoEvento dao = new DaoEvento(connection);		
			List<EventoCalendario> lstEventoCalendario = dao.selectEventosCalendario(criteris.getEventoCalendario());
			if(lstEventoCalendario != null && lstEventoCalendario.size() > 0){
				List<DTOEventoCalendario> lstDtoEventoCalendario = new ArrayList<DTOEventoCalendario>();
				for(EventoCalendario eventoCalendario : lstEventoCalendario){
					//A–adimos Evento
					DTOEventoCalendario dtoEventoCalendario = new DTOEventoCalendario();
					dtoEventoCalendario.setEvento(eventoCalendario);
					dtoEventoCalendario.setEventoCalendario(eventoCalendario);
					rellenaObjeto(dtoEventoCalendario);
					lstDtoEventoCalendario.add(dtoEventoCalendario);
				}
				return lstDtoEventoCalendario;
			}
		}catch(Exception e){
			throw new Exception();
		}
		return null;
	}
}