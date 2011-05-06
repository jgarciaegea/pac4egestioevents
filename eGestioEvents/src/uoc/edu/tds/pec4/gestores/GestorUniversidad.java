package uoc.edu.tds.pec4.gestores;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import uoc.edu.tds.pec4.beans.Universidad;
import uoc.edu.tds.pec4.daos.DaoUniversidad;
import uoc.edu.tds.pec4.dtos.DTOContacto;
import uoc.edu.tds.pec4.dtos.DTOUniversidad;

public class GestorUniversidad extends GestorEntidad<DTOUniversidad>{

	public GestorUniversidad(Connection connection) throws Exception {
		super(connection);
	}
	
	@Override
	public DTOUniversidad consultaEntidad(DTOUniversidad criteris) throws Exception {
		try{
			List<DTOUniversidad> lstUnive = this.consultaEntidades(criteris);
			if(lstUnive != null && lstUnive.size() > 0){
				return lstUnive.get(0);
			}
		}catch(Exception e){
			throw e;
		}
		return null;
	}
	
	@Override
	public List<DTOUniversidad> consultaEntidades(DTOUniversidad criteris) throws Exception {
		try{
			DaoUniversidad dao = new DaoUniversidad(connection);
			List<Universidad> lstUniversidades = dao.select(criteris.getUniversidad());
			if(lstUniversidades != null && lstUniversidades.size() > 0){
				List<DTOUniversidad> lstUni = new ArrayList<DTOUniversidad>();
				for(Universidad universidad : lstUniversidades){
					
					//Añadimos contrato
					DTOUniversidad dtoUniver = new DTOUniversidad();
					dtoUniver.setUniversidad(universidad);
					
					//Añadimos el contacto
					GestorContacto gestorContacto = new GestorContacto(connection);
					DTOContacto dtoContacto = gestorContacto.consultaEntidadById(universidad.getIdContacto());
					if(dtoContacto != null) dtoUniver.setDtoContacto(dtoContacto);
					
					lstUni.add(dtoUniver);
				}
				return lstUni;
			}
		}catch(Exception e){
			throw new Exception();
		}
		return null;
	}

	@Override
	public void insertaEntidad(DTOUniversidad newobject) {
		throw new UnsupportedOperationException("Método no implementado");
	}

	@Override
	public void modificaEntidad(DTOUniversidad criteris) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}

	@Override
	public void eliminaEntidad(DTOUniversidad criteris) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}


}
