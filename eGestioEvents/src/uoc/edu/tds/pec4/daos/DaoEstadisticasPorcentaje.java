package uoc.edu.tds.pec4.daos;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import uoc.edu.tds.pec4.beans.Estadisticas;;

public class DaoEstadisticasPorcentaje extends DaoEntidad<Estadisticas>{

	public DaoEstadisticasPorcentaje(Connection con) {
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
			sb.append("SELECT v.id_universidad, ");
			if(criteris.getIdCentro()!=null) sb.append(" id_centro, ");
			if(criteris.getIdTipoEvento()!=null) sb.append(" id_tipo_evento, ");
			sb.append(" count(*) as inscritos " );
			sb.append("FROM v_porcentaje");
			sb.append("WHERE (1=1)");
			if(criteris.getIdUniversidad()!=null) sb.append("AND id_universidad = ? ");
			if(criteris.getIdCentro()!=null) sb.append("AND id_centro = ? ");
			if(criteris.getIdTipoEvento()!=null) sb.append("AND id_tipo_evento = ? ");
			if(criteris.getFechaInicio() !=null) sb.append("AND fecha_celebracion > ? ");
			if(criteris.getDuracion()!=null) sb.append("AND duracion <= ? ");
			if(criteris.getPorcentajeAsistenciaMayorDe() !=null) sb.append ("AND ");
			if(criteris.getPorcentajeAsistenciaMenorDe() !=null) sb.append ("AND ");
			sb.append("GROUP BY id_universidad ");
			if(criteris.getIdCentro()!=null) sb.append(" , id_centro");
			if(criteris.getIdTipoEvento()!=null) sb.append(" , id_tipo_evento ");
			sb.append("ORDER BY id_universidad ");
			if(criteris.getIdCentro()!=null) sb.append(" , id_centro");
			if(criteris.getIdTipoEvento()!=null) sb.append(" , id_tipo_evento ");
			
						
			ps = con.prepareStatement(sb.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
			if(criteris.getIdUniversidad()!=null) {ps.setInt(i, criteris.getIdUniversidad()); i++;}
			if(criteris.getIdCentro()!=null) {ps.setInt(i, criteris.getIdCentro()); i++;}
			if(criteris.getIdTipoEvento()!=null) {ps.setInt(i, criteris.getIdTipoEvento()); i++;}
			if(criteris.getFechaInicio()!=null) {ps.setDate(i, criteris.getFechaInicio()); i++;}
			if(criteris.getDuracion()!=null) {ps.setInt(i, criteris.getDuracion()); i++;}
			if(criteris.getPorcentajeAsistenciaMayorDe()!=null) {ps.setDouble(i, criteris.getPorcentajeAsistenciaMayorDe()); i++;}
			if(criteris.getPorcentajeAsistenciaMenorDe()!=null) {ps.setDouble(i, criteris.getPorcentajeAsistenciaMenorDe()); i++;}
			
			rs = ps.executeQuery();
			while (rs.next()) {
				Estadisticas infPorcentaje = new Estadisticas();
				infPorcentaje.setIdUniversidad(rs.getInt("id_universidad"));
				infPorcentaje.setIdCentro(rs.getInt("id_centro"));
				infPorcentaje.setIdEvento(rs.getInt("id_evento"));
				infPorcentaje.setIdTipoEvento(rs.getInt("id_tipo_evento"));
				infPorcentaje.setFechaInicio(rs.getDate("fecha_inicio"));
				
				Calendar cal= Calendar.getInstance();
				cal.setTime(rs.getDate("fecha_inicio"));
				cal.add(Calendar.DATE,rs.getInt("duracion"));
				Date fecha= cal.getTime();
				
				
				infPorcentaje.setPlazas(rs.getInt("plazas"));
				infPorcentaje.setInscritos(rs.getInt("inscritos"));
				infPorcentaje.setAsistentes(rs.getInt("asistentes"));
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
