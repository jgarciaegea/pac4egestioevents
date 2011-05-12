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
import uoc.edu.tds.pec4.beans.EventoRolPlazas;
import uoc.edu.tds.pec4.daos.DaoEventoRolPlazas;
import uoc.edu.tds.pec4.dtos.DTOEventoRolPlazas;

public class GestorEventoRolPlazas extends GestorEntidad<DTOEventoRolPlazas>{

	public GestorEventoRolPlazas(Connection connection) throws Exception {
		super(connection);
	}

	@Override
	public void insertaEntidad(DTOEventoRolPlazas newobject) {
		throw new UnsupportedOperationException("Método no implementado");
	}

	@Override
	public List<DTOEventoRolPlazas> consultaEntidades(DTOEventoRolPlazas criteris) throws Exception {
		try{
			DaoEventoRolPlazas dao = new DaoEventoRolPlazas(connection);
			List<EventoRolPlazas> lstEventoRolPlazas = dao.select(criteris.getEventoRolPlazas());
			if(lstEventoRolPlazas != null && lstEventoRolPlazas.size() > 0){
				List<DTOEventoRolPlazas> lstDTOEventoRolPlazas = new ArrayList<DTOEventoRolPlazas>();
				for(EventoRolPlazas EventoRolPlazas : lstEventoRolPlazas){
					DTOEventoRolPlazas dtoEventoRolPlazas = new DTOEventoRolPlazas();
					dtoEventoRolPlazas.setEventoRolPlazas(EventoRolPlazas);
					lstDTOEventoRolPlazas.add(dtoEventoRolPlazas);
				}
				return lstDTOEventoRolPlazas;
			}
		}catch(Exception e){
			throw new Exception();
		}
		return null;
	}

	@Override
	public DTOEventoRolPlazas consultaEntidad(DTOEventoRolPlazas criteris) throws Exception {
		try{
			List<DTOEventoRolPlazas> lstDTOEventoRolPlazas = this.consultaEntidades(criteris);
			if(lstDTOEventoRolPlazas != null && lstDTOEventoRolPlazas.size() > 0){
				return lstDTOEventoRolPlazas.get(0);
			}
		}catch(Exception e){
			throw e;
		}
		return null;
	}
	
	public List<DTOEventoRolPlazas> consultaEntidadesById(Integer idEvento)  throws Exception {
		try{
			DTOEventoRolPlazas dtoEventoRolPlazas = new DTOEventoRolPlazas();
			EventoRolPlazas EventoRolPlazas = new EventoRolPlazas();
			EventoRolPlazas.setIdEvento(idEvento);
			dtoEventoRolPlazas.setEventoRolPlazas(EventoRolPlazas);
			return this.consultaEntidades(dtoEventoRolPlazas);
		}catch(Exception e){
			throw e;
		}
	}
	
	public DTOEventoRolPlazas consultaEntidadById(Integer idEvento)  throws Exception {
		try{
			DTOEventoRolPlazas dtoEventoRolPlazas = new DTOEventoRolPlazas();
			EventoRolPlazas EventoRolPlazas = new EventoRolPlazas();
			EventoRolPlazas.setIdEvento(idEvento);
			dtoEventoRolPlazas.setEventoRolPlazas(EventoRolPlazas);
			return this.consultaEntidad(dtoEventoRolPlazas);
		}catch(Exception e){
			throw e;
		}
	}
	
	@Override
	public void modificaEntidad(DTOEventoRolPlazas criteris) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}

	@Override
	public void eliminaEntidad(DTOEventoRolPlazas criteris) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}

}