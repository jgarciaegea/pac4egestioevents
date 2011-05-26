/**
 * 
 */
package uoc.edu.tds.pec4.gestores;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uoc.edu.tds.pec4.beans.TipoEvento;
import uoc.edu.tds.pec4.daos.DaoTipoEvento;
import uoc.edu.tds.pec4.dtos.DTOTipoEvento;
import uoc.edu.tds.pec4.dtos.DTOTipoEventoRol;

/**
 * @author ML019882
 *
 */
public class GestorTipoEvento extends GestorEntidad<DTOTipoEvento> {

	public GestorTipoEvento(Connection connection) throws Exception {
		super(connection);
	}
	
	@Override
	public void insertaEntidad(DTOTipoEvento newobject) {
		throw new UnsupportedOperationException("Método no implementado");
	}
	
	@Override
	public List<DTOTipoEvento> consultaEntidades(DTOTipoEvento criteris) throws Exception {
		try{
			
			DaoTipoEvento dao = new DaoTipoEvento(connection);
			List<TipoEvento> lstTipoEventos = dao.select(criteris.getTipoEvento());
			if(lstTipoEventos != null && lstTipoEventos.size() > 0){
				List<DTOTipoEvento> lstDTOTipoEvento = new ArrayList<DTOTipoEvento>();
				for(TipoEvento tipoEvento : lstTipoEventos){
					DTOTipoEvento dtotipoEvento = new DTOTipoEvento();
					dtotipoEvento.setTipoEvento(tipoEvento);
					rellenaObjeto(dtotipoEvento);
					
					lstDTOTipoEvento.add(dtotipoEvento);
				}
				return lstDTOTipoEvento;
			}
		}catch(Exception e){
			throw new Exception();
		}
		return null;
	}

	private void rellenaObjeto (DTOTipoEvento dtoTipoEvento) throws Exception{
		try{
			//A–adimos el TipoEventoRol
			GestorTipoEventoRol gestorTipoEventoRol = new GestorTipoEventoRol(connection);
			List<DTOTipoEventoRol> lstDtoTipoEventoRol = gestorTipoEventoRol.consultaEntidadById(dtoTipoEvento.getTipoEvento().getIdTipoEvento());
			if(lstDtoTipoEventoRol != null) dtoTipoEvento.setDtoTipoEventoRol(lstDtoTipoEventoRol);
		}catch(Exception e){
			throw new SQLException();
		}
	}
	
	@Override
	public DTOTipoEvento consultaEntidad(DTOTipoEvento criteris) throws Exception{
	try{
		List<DTOTipoEvento> lstTipoEvento = this.consultaEntidades(criteris);
		if(lstTipoEvento != null && lstTipoEvento.size() > 0){
			return lstTipoEvento.get(0);
		}
	}catch(Exception e){
		throw e;
	}
		return null;
	}

	public DTOTipoEvento consultaEntidadById(Integer idTipoEvento)  throws Exception {
		try{
			DTOTipoEvento dtoTipoEvento = new DTOTipoEvento();
			TipoEvento tipoEvento = new TipoEvento();
			tipoEvento.setIdTipoEvento(idTipoEvento);
			dtoTipoEvento.setTipoEvento(tipoEvento);
			return this.consultaEntidad(dtoTipoEvento);
		}catch(Exception e){
			throw e;
		}
	}

	@Override
	public void modificaEntidad(DTOTipoEvento criteris) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Método no implementado");
		
	}

	@Override
	public void eliminaEntidad(DTOTipoEvento criteris) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Método no implementado");
		
	}


	
	
}
