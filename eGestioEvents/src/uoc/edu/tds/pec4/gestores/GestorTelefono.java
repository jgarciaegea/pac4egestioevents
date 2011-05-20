package uoc.edu.tds.pec4.gestores;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import uoc.edu.tds.pec4.beans.Telefono;
import uoc.edu.tds.pec4.daos.DaoTelefono;
import uoc.edu.tds.pec4.dtos.DTOContacto;
import uoc.edu.tds.pec4.dtos.DTOTelefono;
import uoc.edu.tds.pec4.dtos.DTOTipoTelefono;

public class GestorTelefono extends GestorEntidad<DTOTelefono>{

	public GestorTelefono(Connection connection) throws Exception {
		super(connection);
	}

	@Override
	public void insertaEntidad(DTOTelefono newobject) throws Exception {
		try {
			DaoTelefono dao = new DaoTelefono(connection);
			dao.insert(newobject.getTelefono());
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<DTOTelefono> consultaEntidades(DTOTelefono criteris) throws Exception {
		try{
			DaoTelefono dao = new DaoTelefono(connection);
			List<Telefono> lstTelefonos = dao.select(criteris.getTelefono());
			if(lstTelefonos != null && lstTelefonos.size() > 0){
				List<DTOTelefono> lstDtoTelfs = new ArrayList<DTOTelefono>();
				for(Telefono telefono : lstTelefonos){
					DTOTelefono dtoTelefono = new DTOTelefono();
					
					//Añadimos tipo de telefono
					GestorTipoTelefono gestorTipoTelefono = new GestorTipoTelefono(connection);
					DTOTipoTelefono dtoTipoTelefono = gestorTipoTelefono.consultaEntidadById(telefono.getIdTipoTelefono());
					if(dtoTipoTelefono != null) dtoTelefono.setDtoTipoTelefono(dtoTipoTelefono);
					
					GestorContacto gestorContacto = new GestorContacto(connection);
					DTOContacto dtoContacto = gestorContacto.consultaEntidadById(telefono.getIdContacto());
					if(dtoContacto != null) dtoTelefono.setDtoContacto(dtoContacto);
					
					lstDtoTelfs.add(dtoTelefono);
				}
				return lstDtoTelfs;
			}
		}catch(Exception e){
			throw new Exception();
		}
		return null;
	}

	@Override
	public DTOTelefono consultaEntidad(DTOTelefono criteris) throws Exception {
		try{
			List<DTOTelefono> lstTelf = this.consultaEntidades(criteris);
			if(lstTelf != null && lstTelf.size() > 0){
				return lstTelf.get(0);
			}
		}catch(Exception e){
			throw e;
		}
		return null;
	}
	
	public DTOTelefono consultaEntidadById(Integer idTelefono) throws Exception{
		try{
			DTOTelefono dtoTelefono = new DTOTelefono();
			Telefono telefono = new Telefono();
			telefono.setIdTelefono(idTelefono);
			dtoTelefono.setTelefono(telefono);
			return this.consultaEntidad(dtoTelefono);
		}catch(Exception e){
			throw e;
		}
	}

	@Override
	public void modificaEntidad(DTOTelefono criteris) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminaEntidad(DTOTelefono criteris) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
