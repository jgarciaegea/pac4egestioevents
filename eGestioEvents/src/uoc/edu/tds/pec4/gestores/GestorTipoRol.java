package uoc.edu.tds.pec4.gestores;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import uoc.edu.tds.pec4.beans.TipoRol;
import uoc.edu.tds.pec4.daos.DaoTipoRol;
import uoc.edu.tds.pec4.dtos.DTOTipoRol;

public class GestorTipoRol extends GestorEntidad<DTOTipoRol>{

	public GestorTipoRol(Connection connection) throws Exception {
		super(connection);
	}

	@Override
	public void insertaEntidad(DTOTipoRol newobject) {
		throw new UnsupportedOperationException("Método no implementado");
	}

	@Override
	public List<DTOTipoRol> consultaEntidades(DTOTipoRol criteris) throws Exception {
		try{
			DaoTipoRol dao = new DaoTipoRol(connection);
			List<TipoRol> lstRoles = dao.select(criteris.getTipoRol());
			if(lstRoles != null && lstRoles.size() > 0){
				List<DTOTipoRol> lstDTORol = new ArrayList<DTOTipoRol>();
				for(TipoRol rol : lstRoles){
					DTOTipoRol dtoRol = new DTOTipoRol();
					dtoRol.setTipoRol(rol);
					lstDTORol.add(dtoRol);
				}
				return lstDTORol;
			}
		}catch(Exception e){
			throw new Exception();
		}
		return null;
	}

	@Override
	public DTOTipoRol consultaEntidad(DTOTipoRol criteris) throws Exception {
		try{
			List<DTOTipoRol> lstTipoRol = this.consultaEntidades(criteris);
			if(lstTipoRol != null && lstTipoRol.size() > 0){
				return lstTipoRol.get(0);
			}
		}catch(Exception e){
			throw e;
		}
		return null;
	}
	
	public DTOTipoRol consultaEntidadById(Integer idRol)  throws Exception {
		try{
			DTOTipoRol dtoTipoRol = new DTOTipoRol();
			TipoRol tipoRol = new TipoRol();
			tipoRol.setIdRol(idRol);
			dtoTipoRol.setTipoRol(tipoRol);
			return this.consultaEntidad(dtoTipoRol);
		}catch(Exception e){
			throw e;
		}
	}
	
	@Override
	public void modificaEntidad(DTOTipoRol criteris) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}

	@Override
	public void eliminaEntidad(DTOTipoRol criteris) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}

}
