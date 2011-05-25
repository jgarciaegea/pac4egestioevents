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
import java.util.List;
import uoc.edu.tds.pec4.beans.EventoRequisitos;
import uoc.edu.tds.pec4.daos.DaoEventoRequisitos;
import uoc.edu.tds.pec4.dtos.DTOEvento;
import uoc.edu.tds.pec4.dtos.DTOEventoRequisitos;

public class GestorEventoRequisitos extends GestorEntidad<DTOEventoRequisitos>{

	public GestorEventoRequisitos(Connection connection) throws Exception {
		super(connection);
	}

	@Override
	public void insertaEntidad(DTOEventoRequisitos newobject) throws Exception{
		try {
			DaoEventoRequisitos dao = new DaoEventoRequisitos(connection);
			dao.insert(newobject.getEventoRequisitos());
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<DTOEventoRequisitos> consultaEntidades(DTOEventoRequisitos criteris) throws Exception {
		try{
			DaoEventoRequisitos dao = new DaoEventoRequisitos(connection);
			List<EventoRequisitos> lstEventoRequisitos = dao.select(criteris.getEventoRequisitos());
			if(lstEventoRequisitos != null && lstEventoRequisitos.size() > 0){
				List<DTOEventoRequisitos> lstDTOEventoRequisitos = new ArrayList<DTOEventoRequisitos>();
				GestorEvento gestorEvento = new GestorEvento(connection);
				for(EventoRequisitos eventoRequisitos : lstEventoRequisitos){
					DTOEventoRequisitos dtoEventoRequisitos = new DTOEventoRequisitos();
					dtoEventoRequisitos.setEventoRequisitos(eventoRequisitos);
					//DTOEvento dtoEvento = gestorEvento.consultaEntidadById(eventoRequisitos.getIdEvento())
					DTOEvento dtoEventoReq = gestorEvento.consultaEntidadById(eventoRequisitos.getIdEventoReq());
					//if(dtoEvento != null) dtoEventoRequisitos.setDtoEvento(dtoEvento);
					if(dtoEventoReq != null) dtoEventoRequisitos.setDtoEventoReq(dtoEventoReq);
					
					lstDTOEventoRequisitos.add(dtoEventoRequisitos);
				}
				return lstDTOEventoRequisitos;
			}
		}catch(Exception e){
			throw new Exception();
		}
		return null;
	}

	@Override
	public DTOEventoRequisitos consultaEntidad(DTOEventoRequisitos criteris) throws Exception {
		try{
			List<DTOEventoRequisitos> lstDTOEventoRequisitos = this.consultaEntidades(criteris);
			if(lstDTOEventoRequisitos != null && lstDTOEventoRequisitos.size() > 0){
				return lstDTOEventoRequisitos.get(0);
			}
		}catch(Exception e){
			throw e;
		}
		return null;
	}
	
	public List<DTOEventoRequisitos> consultaEntidadById(Integer idEvento)  throws Exception {
		try{
			DTOEventoRequisitos dtoEventoRequisitos = new DTOEventoRequisitos();
			EventoRequisitos eventoRequisitos = new EventoRequisitos();
			eventoRequisitos.setIdEvento(idEvento);
			dtoEventoRequisitos.setEventoRequisitos(eventoRequisitos);
			return this.consultaEntidades(dtoEventoRequisitos);
		}catch(Exception e){
			throw e;
		}
	}
	
	@Override
	public void modificaEntidad(DTOEventoRequisitos criteris) throws Exception {
		try {
			DaoEventoRequisitos dao = new DaoEventoRequisitos(connection);
			dao.update(criteris.getEventoRequisitos());
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void eliminaEntidad(DTOEventoRequisitos criteris) throws Exception {
		try {
			DaoEventoRequisitos dao = new DaoEventoRequisitos(connection);
			dao.delete(criteris.getEventoRequisitos());
		} catch (Exception e) {
			throw e;
		}
	}

}