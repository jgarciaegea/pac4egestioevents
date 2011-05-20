package uoc.edu.tds.pec4.gestores;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import uoc.edu.tds.pec4.beans.Estadisticas;
import uoc.edu.tds.pec4.daos.DaoEstadisticasPorcentaje;
import uoc.edu.tds.pec4.dtos.DTOEstadisticas;

public class GestorEstadisticasPorcentaje extends GestorEntidad<DTOEstadisticas>{

	public GestorEstadisticasPorcentaje(Connection connection) throws Exception {
		super(connection);
	}
	
	@Override
	public DTOEstadisticas consultaEntidad(DTOEstadisticas criteris) throws Exception {
		try{
			List<DTOEstadisticas> lstEstad = this.consultaEntidades(criteris);
			if(lstEstad != null && lstEstad.size() > 0){
				return lstEstad.get(0);
			}
		}catch(Exception e){
			throw e;
		}
		return null;
	}
	
	@Override
	public List<DTOEstadisticas> consultaEntidades(DTOEstadisticas criteris) throws Exception {
		try{
			DaoEstadisticasPorcentaje dao = new DaoEstadisticasPorcentaje(connection);
			List<Estadisticas> lstEstadisticas = dao.select(criteris.getEstadisticas());
			if(lstEstadisticas != null && lstEstadisticas.size() > 0){
				List<DTOEstadisticas> lstEst = new ArrayList<DTOEstadisticas>();
				for(Estadisticas estadisticas : lstEstadisticas){
					
					//Añadimos contrato
					DTOEstadisticas dtoEstad = new DTOEstadisticas();
					dtoEstad.setEstadisticas(estadisticas);
					
					lstEst.add(dtoEstad);
				}
				return lstEst;
			}
		}catch(Exception e){
			throw new Exception();
		}
		return null;
	}

	@Override
	public void insertaEntidad(DTOEstadisticas newobject) {
		throw new UnsupportedOperationException("Método no implementado");
	}

	@Override
	public void modificaEntidad(DTOEstadisticas criteris) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}

	@Override
	public void eliminaEntidad(DTOEstadisticas criteris) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}


}
