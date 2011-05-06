package uoc.edu.tds.pec4.gestores;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import uoc.edu.tds.pec4.beans.TipoDocumento;
import uoc.edu.tds.pec4.daos.DaoTipoDocumento;
import uoc.edu.tds.pec4.dtos.DTOTipoDocumento;

public class GestorTipoDocumento extends GestorEntidad<DTOTipoDocumento>{

	public GestorTipoDocumento(Connection connection) throws Exception {
		super(connection);
	}
	
	@Override
	public DTOTipoDocumento consultaEntidad(DTOTipoDocumento criteris) throws Exception {
		try{
			List<DTOTipoDocumento> lstTipoDocumento = this.consultaEntidades(criteris);
			if(lstTipoDocumento != null && lstTipoDocumento.size() > 0){
				return lstTipoDocumento.get(0);
			}
		}catch(Exception e){
			throw e;
		}
		return null;
	}
	
	@Override
	public List<DTOTipoDocumento> consultaEntidades(DTOTipoDocumento criteris) throws Exception {
		try{
			DaoTipoDocumento dao = new DaoTipoDocumento(connection);
			List<TipoDocumento> lstTipoDocumentos = dao.select(criteris.getTipoDocumento());
			if(lstTipoDocumentos != null && lstTipoDocumentos.size() > 0){
				List<DTOTipoDocumento> lstDtoDocumentos = new ArrayList<DTOTipoDocumento>();
				for(TipoDocumento tipoDocumento : lstTipoDocumentos){
					DTOTipoDocumento dtoTipoDocumento = new DTOTipoDocumento();
					dtoTipoDocumento.setTipoDocumento(tipoDocumento);
					lstDtoDocumentos.add(dtoTipoDocumento);
				}
				return lstDtoDocumentos;
			}
		}catch(Exception e){
			throw new Exception();
		}
		return null;
	}
	
	public DTOTipoDocumento consultaEntidadById(Integer idTipoDocumento)  throws Exception {
		try{
			DTOTipoDocumento dtoTipoDocumento = new DTOTipoDocumento();
			TipoDocumento tipoDocumento = new TipoDocumento();
			tipoDocumento.setIdTipoDocumento(idTipoDocumento);
			dtoTipoDocumento.setTipoDocumento(tipoDocumento);
			return this.consultaEntidad(dtoTipoDocumento);
		}catch(Exception e){
			throw e;
		}
	}
	
	@Override
	public void insertaEntidad(DTOTipoDocumento newobject) {
		throw new UnsupportedOperationException("Método no implementado");
	}

	@Override
	public void modificaEntidad(DTOTipoDocumento criteris) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}

	@Override
	public void eliminaEntidad(DTOTipoDocumento criteris) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}


}
