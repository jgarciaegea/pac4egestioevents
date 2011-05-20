package uoc.edu.tds.pec4.gestores;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import uoc.edu.tds.pec4.beans.Contacto;
import uoc.edu.tds.pec4.daos.DaoContacto;
import uoc.edu.tds.pec4.dtos.DTOContacto;
import uoc.edu.tds.pec4.dtos.DTOPais;

public class GestorContacto extends GestorEntidad<DTOContacto>{

	public GestorContacto(Connection connection) throws Exception {
		super(connection);
	}
	
	@Override
	public DTOContacto consultaEntidad(DTOContacto criteris) throws Exception {
		try{
			List<DTOContacto> lstContr = this.consultaEntidades(criteris);
			if(lstContr != null && lstContr.size() > 0){
				return lstContr.get(0);
			}
		}catch(Exception e){
			throw e;
		}
		return null;
	}
	
	@Override
	public List<DTOContacto> consultaEntidades(DTOContacto criteris) throws Exception {
		try{
			DaoContacto dao = new DaoContacto(connection);
			List<Contacto> lstContactos = dao.select(criteris.getContacto());
			if(lstContactos != null && lstContactos.size() > 0){
				List<DTOContacto> lstContr = new ArrayList<DTOContacto>();
				for(Contacto contacto : lstContactos){
					
					//Añadimos contrato
					DTOContacto dtoCon = new DTOContacto();
					dtoCon.setContacto(contacto);
					
					//Añadimos el país
					GestorPais gestorPais = new GestorPais(connection);
					DTOPais dtoPais = gestorPais.consultaEntidadById(contacto.getIdPais());
					if(dtoPais != null) dtoCon.setDtoPais(dtoPais);
					
					lstContr.add(dtoCon);
				}
				return lstContr;
			}
		}catch(Exception e){
			throw new Exception();
		}
		return null;
	}
	
	public DTOContacto consultaEntidadById(Integer idContacto) throws Exception{
		try{
			DTOContacto dtoContacto = new DTOContacto();
			Contacto contacto = new Contacto();
			contacto.setIdContacto(idContacto);
			dtoContacto.setContacto(contacto);
			return this.consultaEntidad(dtoContacto);
		}catch(Exception e){
			throw e;
		}
	}

	@Override
	public void insertaEntidad(DTOContacto newobject) throws Exception {
		try {
			DaoContacto dao = new DaoContacto(connection);
			dao.insert(newobject.getContacto());
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Integer insertaEntidadRetId(DTOContacto newobject) throws Exception {
		try {
			DaoContacto dao = new DaoContacto(connection);
			return dao.insertReturnId(newobject.getContacto());
		} catch (Exception e) {
			throw e;
		}
	}
	@Override
	public void modificaEntidad(DTOContacto criteris) throws Exception {
		try {
			DaoContacto dao = new DaoContacto(connection);
			dao.update(criteris.getContacto());
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void eliminaEntidad(DTOContacto criteris) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}


}
