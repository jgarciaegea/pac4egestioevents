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
			sb.append("SELECT id_universidad, ");
			if(criteris.getIdCentro()!=null) sb.append(" id_centro, ");
			if(criteris.getIdTipoEvento()!=null) sb.append(" id_tipo_evento, ");
			sb.append("sum(ingresos) as ingresos ");
			sb.append("FROM v_ingresos ");
			sb.append("WHERE (1=1)");
			if(criteris.getIdUniversidad()!=null) sb.append("AND id_universidad = ? ");
			if(criteris.getIdCentro()!=null) sb.append("AND id_centro = ? ");
			if(criteris.getIdTipoEvento()!=null) sb.append("AND id_tipo_evento = ? ");
			if(criteris.getIngresosMayorDe()!=null) sb.append("AND SUM(precio)> ? ");
			if(criteris.getIngresosMenorDe()!=null) sb.append("AND SUM(precio)< ? ");
			sb.append("GROUP BY id_universidad ");
			if(criteris.getIdCentro()!=null) sb.append(" , id_centro");
			if(criteris.getIdTipoEvento()!=null) sb.append(" , id_tipo_evento ");
			
						
			ps = con.prepareStatement(sb.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
			if(criteris.getIdUniversidad()!=null) {ps.setInt(i, criteris.getIdUniversidad()); i++;}
			if(criteris.getIdCentro()!=null) {ps.setInt(i, criteris.getIdCentro()); i++;}
			if(criteris.getIdTipoEvento()!=null) {ps.setInt(i, criteris.getIdTipoEvento()); i++;}
			if(criteris.getIngresosMayorDe()!=null) {ps.setInt(i, criteris.getIngresosMayorDe()); i++;}
			if(criteris.getIngresosMenorDe()!=null) {ps.setInt(i, criteris.getIngresosMenorDe()); i++;}
			rs = ps.executeQuery();
			while (rs.next()) {
				Estadisticas infPorcentaje = new Estadisticas();
				infPorcentaje.setIdUniversidad(rs.getInt("id_universidad"));
				infPorcentaje.setIdCentro(rs.getInt("id_centro"));
				infPorcentaje.setIdTipoEvento(rs.getInt("id_tipo_evento"));
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
