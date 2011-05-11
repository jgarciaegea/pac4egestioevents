package uoc.edu.tds.pec4.gestores;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import uoc.edu.tds.pec4.beans.TipoTelefono;
import uoc.edu.tds.pec4.daos.DaoTipoTelefono;
import uoc.edu.tds.pec4.dtos.DTOTipoTelefono;

public class GestorTipoTelefono extends GestorEntidad<DTOTipoTelefono>{

	public GestorTipoTelefono(Connection connection) throws Exception {
		super(connection);
	}

	@Override
	public void insertaEntidad(DTOTipoTelefono newobject) {
		throw new UnsupportedOperationException("Método no implementado");
	}

	@Override
	public List<DTOTipoTelefono> consultaEntidades(DTOTipoTelefono criteris)
			throws Exception {
		try{
			DaoTipoTelefono dao = new DaoTipoTelefono(connection);
			List<TipoTelefono> lstTipoTelefonos = dao.select(criteris.getTipoTelefono());
			if(lstTipoTelefonos != null && lstTipoTelefonos.size() > 0){
				List<DTOTipoTelefono> lstDtoTipoTelf = new ArrayList<DTOTipoTelefono>();
				for(TipoTelefono tipoTelf : lstTipoTelefonos){
					DTOTipoTelefono dtoTipoTelf = new DTOTipoTelefono();
					dtoTipoTelf.setTipoTelefono(tipoTelf);
					lstDtoTipoTelf.add(dtoTipoTelf);
				}
				return lstDtoTipoTelf;
			}
		}catch(Exception e){
			throw new Exception();
		}
		return null;
	}

	@Override
	public DTOTipoTelefono consultaEntidad(DTOTipoTelefono criteris)
			throws Exception {
		try{
			List<DTOTipoTelefono> lstTipoTelf = this.consultaEntidades(criteris);
			if(lstTipoTelf != null && lstTipoTelf.size() > 0){
				return lstTipoTelf.get(0);
			}
		}catch(Exception e){
			throw e;
		}
		return null;
	}

	@Override
	public void modificaEntidad(DTOTipoTelefono criteris) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}

	@Override
	public void eliminaEntidad(DTOTipoTelefono criteris) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}

}
