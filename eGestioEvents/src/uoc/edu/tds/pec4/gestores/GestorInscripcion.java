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

import uoc.edu.tds.pec4.beans.Inscripcion;
import uoc.edu.tds.pec4.daos.DaoInscripcion;
import uoc.edu.tds.pec4.dtos.DTOUsuario;
import uoc.edu.tds.pec4.dtos.DTOEvento;
import uoc.edu.tds.pec4.dtos.DTOInscripcion;

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
					/*
					//A–adimos el Usuario
					GestorUsuario gestorUsuario = new GestorUsuario(connection);
					DTOUsuario dtoUsuario = gestorUsuario.consultaEntidadById(inscripcion.getCodigo());
					if(dtoUsuario != null) dtoInscripcion.setDtoUsuario(dtoUsuario);
					*/
					lstDTOInscripcion.add(dtoInscripcion);
				}
				return lstDTOInscripcion;
			}
		}catch(Exception e){
			throw new Exception();
		}
		return null;
	}

	@Override
	public void insertaEntidad(DTOInscripcion newobject) {
		throw new UnsupportedOperationException("Método no implementado");
	}

	@Override
	public void modificaEntidad(DTOInscripcion criteris) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}

	@Override
	public void eliminaEntidad(DTOInscripcion criteris) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}


}