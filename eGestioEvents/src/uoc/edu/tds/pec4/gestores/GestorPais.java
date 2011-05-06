package uoc.edu.tds.pec4.gestores;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import uoc.edu.tds.pec4.beans.Pais;
import uoc.edu.tds.pec4.daos.DaoPais;
import uoc.edu.tds.pec4.dtos.DTOPais;

public class GestorPais extends GestorEntidad<DTOPais>{

	public GestorPais(Connection connection) throws Exception {
		super(connection);
	}
	
	@Override
	public DTOPais consultaEntidad(DTOPais criteris) throws Exception {
		List<DTOPais> lstPais = this.consultaEntidades(criteris);
		if(lstPais != null && lstPais.size() > 0){
			return lstPais.get(0);
		}
		return null;
	}
	
	@Override
	public List<DTOPais> consultaEntidades(DTOPais criteris) throws Exception {
		try{
			DaoPais dao = new DaoPais(connection);
			List<Pais> lstPais = dao.select(criteris.getPais());
			if(lstPais != null && lstPais.size() > 0){
				List<DTOPais> lstDtoPais = new ArrayList<DTOPais>();
				for(Pais pais : lstPais){
					DTOPais dtoPais = new DTOPais();
					dtoPais.setPais(pais);
					lstDtoPais.add(dtoPais);
				}
				return lstDtoPais;
			}
		}catch(Exception e){
			throw new Exception();
		}
		return null;
	}
	
	public DTOPais consultaEntidadById(Integer idPais)  throws Exception {
		DTOPais dtoPais = new DTOPais();
		Pais pais = new Pais();
		pais.setIdPais(idPais);
		dtoPais.setPais(pais);
		return this.consultaEntidad(dtoPais);
	}
	
	@Override
	public void insertaEntidad(DTOPais newobject) {
		throw new UnsupportedOperationException("Método no implementado");
	}

	@Override
	public void modificaEntidad(DTOPais criteris) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}

	@Override
	public void eliminaEntidad(DTOPais criteris) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}


}
