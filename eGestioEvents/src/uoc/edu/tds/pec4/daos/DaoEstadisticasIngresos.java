package uoc.edu.tds.pec4.daos;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import uoc.edu.tds.pec4.beans.Estadisticas;;

public class DaoEstadisticasIngresos extends DaoEntidad<Estadisticas>{

	public DaoEstadisticasIngresos(Connection con) {
		super(con);
	}

	@Override
	public void insert(Estadisticas objecte) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}

	@Override
	public List<Estadisticas> select(Estadisticas criteris) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Estadisticas> lstPorcentajes = new ArrayList<Estadisticas>();
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT id_universidad, nombre_universidad, id_centro, nombre_centro, ");
			sb.append(" id_tipo_evento, ingresos ");
			sb.append("FROM ingresos(?,?,?,?,?) ");
						
			ps = con.prepareStatement(sb.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
			if(criteris.getIdUniversidad()!=null) {ps.setInt(i, criteris.getIdUniversidad()); i++;}
			if(criteris.getIdCentro()!=null) {ps.setInt(i, criteris.getIdCentro()); i++;}
			if(criteris.getIdTipoEvento()!=null) {ps.setInt(i, criteris.getIdTipoEvento()); i++;}
			if(criteris.getIngresos()!=null) {ps.setInt(i, criteris.getIngresos()); i++;}
			if(criteris.getIngresos()!=null) {ps.setInt(i, criteris.getIngresos()); i++;}
			rs = ps.executeQuery();
			while (rs.next()) {
				Estadisticas infPorcentaje = new Estadisticas();
				infPorcentaje.setIdUniversidad(rs.getInt("id_universidad"));
				infPorcentaje.setNombreUniversidad(rs.getString("nombre_universidad"));
				infPorcentaje.setIdCentro(rs.getInt("id_centro"));
				infPorcentaje.setNombreCentro(rs.getString("nombre_centro"));
				infPorcentaje.setIdTipoEvento(rs.getInt("id_tipo_evento"));
				infPorcentaje.setDescripcionTipoEvento(rs.getString("descripcion"));
				infPorcentaje.setIngresos(rs.getInt("ingresos"));
				lstPorcentajes.add(infPorcentaje);
			}		
			return (lstPorcentajes.isEmpty() || lstPorcentajes.size() == 0) ? null:lstPorcentajes;
		}catch(Exception e){
			throw new Exception(e.getMessage());
		} finally {
			close(ps,rs);
		}
	}

	@Override
	public void update(Estadisticas objecte) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}

	@Override
	public void delete(Estadisticas criteris) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}

}
