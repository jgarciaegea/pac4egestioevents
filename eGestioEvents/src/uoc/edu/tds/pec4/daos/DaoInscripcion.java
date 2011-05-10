/**
 * 
 */
package uoc.edu.tds.pec4.daos;

/**
 * @author jgarcia
 *
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import uoc.edu.tds.pec4.beans.Inscripcion;

public class DaoInscripcion extends DaoEntidad<Inscripcion>{

	public DaoInscripcion(Connection con) {
		super(con);
	}

	@Override
	public void insert(Inscripcion objecte) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}

	@Override
	public List<Inscripcion> select(Inscripcion criteris) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Inscripcion> lstInscripcion = new ArrayList<Inscripcion>();
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT codigo, id_evento, estado, fecha_estado, motivo_estado, fecha_inscripcion, check_in, codigo_asistencia");
			sb.append("FROM INSCRIPCION ");
			sb.append("WHERE (1=1) ");
			if(criteris.getCodigo()!=null) sb.append("AND codigo = ? ");
			if(criteris.getIdEvento()!=null) sb.append("AND id_evento = ? ");
			if(criteris.getEstado()!=null) sb.append("AND estado = ? ");
			if(criteris.getFechaEstado()!=null) sb.append("AND fecha_estado = ? ");
			if(criteris.getMotivoEstado()!=null) sb.append("AND motivo_estado = ? ");
			if(criteris.getFechaInscripcion()!=null) sb.append("AND fecha_inscripcion = ? ");
			if(criteris.getCheckIn()!=null) sb.append("AND check_in = ? ");
			if(criteris.getCodigoAsistencia()!=null) sb.append("AND codigo_asistencia = ? ");
						
			ps = con.prepareStatement(sb.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
			if(criteris.getCodigo()!=null) {ps.setString(i, criteris.getCodigo()); i++;}
			if(criteris.getIdEvento()!=null) {ps.setInt(i, criteris.getIdEvento()); i++;}
			if(criteris.getEstado()!=null) {ps.setInt(i, criteris.getEstado()); i++;}
			if(criteris.getFechaEstado()!=null) {ps.setDate(i, criteris.getFechaEstado()); i++;}
			if(criteris.getMotivoEstado()!=null) {ps.setString(i, criteris.getMotivoEstado()); i++;}
			if(criteris.getFechaInscripcion()!=null) {ps.setDate(i, criteris.getFechaInscripcion()); i++;}
			if(criteris.getCheckIn()!=null) {ps.setBoolean(i, criteris.getCheckIn()); i++;}
			if(criteris.getCodigoAsistencia()!=null) {ps.setString(i, criteris.getCodigoAsistencia()); i++;}
			
			rs = ps.executeQuery();
			while (rs.next()) {
				Inscripcion inscripcion = new Inscripcion();
				inscripcion.setCodigo(rs.getString("Codigo"));
				inscripcion.setIdEvento(rs.getInt("id_evento"));
				inscripcion.setEstado(rs.getInt("estado"));
				inscripcion.setFechaEstado(rs.getDate("fecha_estado"));
				inscripcion.setMotivoEstado(rs.getString("motivo_estado"));
				inscripcion.setFechaInscripcion(rs.getDate("fecha_inscripcion"));
				inscripcion.setCheckIn(rs.getBoolean("check_in"));
				inscripcion.setCodigoAsistencia(rs.getString("codigo_asistencia"));
				lstInscripcion.add(inscripcion);
			}		
			return (lstInscripcion.isEmpty() || lstInscripcion.size() == 0) ? null:lstInscripcion;
		}catch(Exception e){
			throw new Exception(e.getMessage());
		} finally {
			close(ps,rs);
		}
	}

	@Override
	public void update(Inscripcion objecte) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}

	@Override
	public void delete(Inscripcion criteris) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}

}