package uoc.edu.tds.pec4.gestores;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import uoc.edu.tds.pec4.beans.DatosBancarios;
import uoc.edu.tds.pec4.daos.DaoDatosBancarios;
import uoc.edu.tds.pec4.dtos.DTODatosBancarios;

public class GestorDatosBancarios extends GestorEntidad<DTODatosBancarios>{

	public GestorDatosBancarios(Connection connection) throws Exception {
		super(connection);
	}

	@Override
	public void insertaEntidad(DTODatosBancarios newobject) {
		throw new UnsupportedOperationException("Método no implementado");
	}

	@Override
	public List<DTODatosBancarios> consultaEntidades(DTODatosBancarios criteris) throws Exception {
		try{
			DaoDatosBancarios dao = new DaoDatosBancarios(connection);
			List<DatosBancarios> lstDatosBancarios = dao.select(criteris.getDatosBancarios());
			if(lstDatosBancarios != null && lstDatosBancarios.size() > 0){
				List<DTODatosBancarios> lstDtoDatosBancarios = new ArrayList<DTODatosBancarios>();
				for(DatosBancarios datoBanc : lstDatosBancarios){
					DTODatosBancarios dtoDatosBancarios = new DTODatosBancarios();
					dtoDatosBancarios.setDatosBancarios(datoBanc);
					lstDtoDatosBancarios.add(dtoDatosBancarios);
				}
				return lstDtoDatosBancarios;
			}
		}catch(Exception e){
			throw new Exception();
		}
		return null;
	}

	@Override
	public DTODatosBancarios consultaEntidad(DTODatosBancarios criteris) throws Exception {
		try{
			List<DTODatosBancarios> lstDatosBancarios = this.consultaEntidades(criteris);
			if(lstDatosBancarios != null && lstDatosBancarios.size() > 0){
				return lstDatosBancarios.get(0);
			}
		}catch(Exception e){
			throw e;
		}
		return null;
	}

	@Override
	public void modificaEntidad(DTODatosBancarios criteris) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}

	@Override
	public void eliminaEntidad(DTODatosBancarios criteris) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}
	
	public DTODatosBancarios consultaEntidadById(Integer idDatosBancarios)  throws Exception {
		try{
			DTODatosBancarios dtoDatosBanc = new DTODatosBancarios();
			DatosBancarios datosBancarios = new DatosBancarios();
			datosBancarios.setIdDatosBancarios(idDatosBancarios);
			dtoDatosBanc.setDatosBancarios(datosBancarios);
			return this.consultaEntidad(dtoDatosBanc);
		}catch(Exception e){
			throw e;
		}
	}

}
