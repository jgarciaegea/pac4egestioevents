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
		throw new UnsupportedOperationException("M�todo no implementado");
	}

	@Override
	public List<Estadisticas> select(Estadisticas criteris) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Estadisticas> lstPorcentajes = new ArrayList<Estadisticas>();
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT id_universidad, nombre_universidad, id_centro, nombre_centro, ");
			sb.append("id_evento, nombre_evento, id_tipo_evento, descripcion, ");
			sb.append("fecha_inicio, duracion, plazas, inscritos, asistentes, asistencia");
			sb.append("FROM porcentaje(?,?,?,?,?,?) ");
						
			ps = con.prepareStatement(sb.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
			if(criteris.getIdUniversidad()!=null) {ps.setInt(i, criteris.getIdUniversidad()); i++;}
			if(criteris.getIdCentro()!=null) {ps.setInt(i, criteris.getIdCentro()); i++;}
			if(criteris.getIdTipoEvento()!=null) {ps.setInt(i, criteris.getIdTipoEvento()); i++;}
			if(criteris.getFechaInicio()!=null) {ps.setDate(i, criteris.getFechaInicio()); i++;}
			if(criteris.getFechaFin()!=null) {ps.setDate(i, criteris.getFechaFin()); i++;}
			if(criteris.getPorcentajeAsistencia()!=null) {ps.setDouble(i, criteris.getPorcentajeAsistencia()); i++;}
			if(criteris.getPorcentajeAsistencia()!=null) {ps.setDouble(i, criteris.getPorcentajeAsistencia()); i++;}
			
			rs = ps.executeQuery();
			while (rs.next()) {
				Estadisticas infPorcentaje = new Estadisticas();
				infPorcentaje.setIdUniversidad(rs.getInt("id_universidad"));
				infPorcentaje.setNombreUniversidad(rs.getString("nombre_universidad"));
				infPorcentaje.setIdCentro(rs.getInt("id_centro"));
				infPorcentaje.setNombreCentro(rs.getString("nombre_centro"));
				infPorcentaje.setIdEvento(rs.getInt("id_evento"));
				infPorcentaje.setNombreEvento(rs.getString("nombre_evento"));
				infPorcentaje.setIdTipoEvento(rs.getInt("id_tipo_evento"));
				infPorcentaje.setDescripcionTipoEvento(rs.getString("descripcion"));
				infPorcentaje.setFechaInicio(rs.getDate("fecha_inicio"));
				
				Calendar cal= Calendar.getInstance();
				cal.setTime(rs.getDate("fecha_inicio"));
				cal.add(Calendar.DATE,rs.getInt("duracion"));
				Date fecha= cal.getTime();
				infPorcentaje.setFechaFin((java.sql.Date) fecha);
				
				infPorcentaje.setPlazas(rs.getInt("plazas"));
				infPorcentaje.setInscritos(rs.getInt("inscritos"));
				infPorcentaje.setAsistentes(rs.getInt("asistentes"));
				infPorcentaje.setPorcentajeAsistencia(rs.getDouble("asistencia"));
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
		throw new UnsupportedOperationException("M�todo no implementado");
	}

	@Override
	public void delete(Estadisticas criteris) throws Exception {
		throw new UnsupportedOperationException("M�todo no implementado");
	}

}