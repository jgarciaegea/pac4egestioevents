package uoc.edu.tds.pec4.daos;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import uoc.edu.tds.pec4.beans.Estadisticas;;

public class DaoEstadisticasAsistentes extends DaoEntidad<Estadisticas>{

	public DaoEstadisticasAsistentes(Connection con) {
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
			sb.append("SELECT codigo, nombre_usuario, id_rol, descripcion_rol, ");
			sb.append("id_documento_identificacion, id_centro, nombre_centro, ");
			sb.append("estado_asistencia ");
			sb.append("FROM asistentes(?,?) ");
						
			ps = con.prepareStatement(sb.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
			if(criteris.getIdEvento()!=null) {ps.setInt(i, criteris.getIdEvento()); i++;}
			//El estado de asistencia podrá tener los siguientes valores:
			// "si": seleccionar los asistentes al evento
			// "no": seleccionar los NO asistentes al evento que si estaban inscritos
			// "ambos": seleccionar todos los inscritos al evento (hayan asistido o no)
			if(criteris.getEstadoAsistencia()!=null) {ps.setString(i, criteris.getEstadoAsistencia()); i++;}
									
			rs = ps.executeQuery();
			while (rs.next()) {
				Estadisticas infPorcentaje = new Estadisticas();
				infPorcentaje.setIdAsistente(rs.getInt("codigo"));
				infPorcentaje.setNombreAsistente(rs.getString("nombre_usuario"));
				infPorcentaje.setIdRol(rs.getInt("id_rol"));
				infPorcentaje.setDescripcionRol(rs.getString("descripcion_rol"));
				infPorcentaje.setIdDocumentoIdentificacion(rs.getInt("id_documento_identificacion"));
				infPorcentaje.setIdCentro(rs.getInt("id_centro"));
				infPorcentaje.setNombreCentro(rs.getString("nombre_centro"));
				infPorcentaje.setEstadoAsistencia(rs.getString("estado_asistencia"));
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
