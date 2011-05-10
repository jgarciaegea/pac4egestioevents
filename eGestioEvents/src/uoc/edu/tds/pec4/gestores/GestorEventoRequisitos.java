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
import uoc.edu.tds.pec4.dtos.DTOEventoRequisitos;

public class GestorEventoRequisitos extends GestorEntidad<DTOEventoRequisitos>{

	public GestorEventoRequisitos(Connection connection) throws Exception {
		super(connection);
	}

	@Override
	public void insertaEntidad(DTOEventoRequisitos newobject) {
		throw new UnsupportedOperationException("Método no implementado");
	}

	@Override
	public List<DTOEventoRequisitos> consultaEntidades(DTOEventoRequisitos criteris) throws Exception {
		try{
			DaoEventoRequisitos dao = new DaoEventoRequisitos(connection);
			List<EventoRequisitos> lstEventoRequisitos = dao.select(criteris.getEventoRequisitos());
			if(lstEventoRequisitos != null && lstEventoRequisitos.size() > 0){
				List<DTOEventoRequisitos> lstDTOEventoRequisitos = new ArrayList<DTOEventoRequisitos>();
				for(EventoRequisitos eventoRequisitos : lstEventoRequisitos){
					DTOEventoRequisitos dtoEventoRequisitos = new DTOEventoRequisitos();
					dtoEventoRequisitos.setEventoRequisitos(eventoRequisitos);
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
		throw new UnsupportedOperationException("Método no implementado");
	}

	@Override
	public void eliminaEntidad(DTOEventoRequisitos criteris) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}

}