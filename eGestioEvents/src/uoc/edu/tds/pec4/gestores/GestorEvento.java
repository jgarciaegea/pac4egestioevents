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
import uoc.edu.tds.pec4.beans.Evento;
import uoc.edu.tds.pec4.daos.DaoEvento;
import uoc.edu.tds.pec4.dtos.DTOCentroDocente;
import uoc.edu.tds.pec4.dtos.DTOEventoRequisitos;
import uoc.edu.tds.pec4.dtos.DTOEventoRolPlazas;
import uoc.edu.tds.pec4.dtos.DTOEvento;

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

					//A–adimos el CentroDocente
					GestorCentroDocente gestorCentroDocente = new GestorCentroDocente(connection);
					DTOCentroDocente dtoCentroDocente = gestorCentroDocente.consultaEntidadById(evento.getIdEvento());
					if(dtoCentroDocente != null) dtoEvento.setDtoCentroDocente(dtoCentroDocente);

					/*			
					 * TODO 1: Pendiente de tener el gestor tipo evento	
					//A–adimos el TipoEvento
					GestorTipoEvento gestorTipoEvento = new GestorTipoEvento(connection);
					DTOTipoEvento dtoTipoEvento = gestorTipoEvento.consultaEntidadById(evento.getIdEvento());
					if(dtoTipoEvento != null) dtoEvento.setDtoTipoEvento(dtoTipoEvento);
					*/
					
					//A–adimos el EventoRequisitos
					GestorEventoRequisitos gestorEventoRequisitos = new GestorEventoRequisitos(connection);
					List<DTOEventoRequisitos> dtoEventoRequisitos = gestorEventoRequisitos.consultaEntidadById(evento.getIdEvento());
					if(dtoEventoRequisitos != null) dtoEvento.setDtoEventoRequisitos(dtoEventoRequisitos);
					
					//A–adimos el EventoRequisitos
					GestorEventoRolPlazas gestorEventoRolPlazas = new GestorEventoRolPlazas(connection);
					List<DTOEventoRolPlazas> dtoEventoRolPlazas = gestorEventoRolPlazas.consultaEntidadesById(evento.getIdEvento());
					if(dtoEventoRolPlazas != null) dtoEvento.setDtoEventoRolPlazas(dtoEventoRolPlazas);

					lstDTOEvento.add(dtoEvento);
				}
				return lstDTOEvento;
			}
		}catch(Exception e){
			throw new Exception();
		}
		return null;
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
	public void insertaEntidad(DTOEvento newobject) {
		throw new UnsupportedOperationException("Método no implementado");
	}

	@Override
	public void modificaEntidad(DTOEvento criteris) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}

	@Override
	public void eliminaEntidad(DTOEvento criteris) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}
}