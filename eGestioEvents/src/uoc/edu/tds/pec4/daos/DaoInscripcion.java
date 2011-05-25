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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uoc.edu.tds.pec4.beans.Inscripcion;
import uoc.edu.tds.pec4.utils.Constantes;

public class DaoInscripcion extends DaoEntidad<Inscripcion>{

	public DaoInscripcion(Connection con) {
		super(con);
	}

	@Override
	public void insert(Inscripcion objecte) throws Exception {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("INSERT INTO INSCRIPCION (codigo, id_evento, estado, fecha_estado, motivo_estado, fecha_inscripcion, check_in, codigo_asistencia ) " + 
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, objecte.getCodigo());
			ps.setInt(2, objecte.getIdEvento());
			ps.setInt(3, Constantes.REGISTRO_ACTIVO);
			ps.setDate(4, new java.sql.Date(System.currentTimeMillis()));
			ps.setString(5, Constantes.REGISTRO_ACTIVO_MOTIVO);
			//ps.setDate(6, objecte.getFechaInscripcion());
			ps.setDate(6, new java.sql.Date(System.currentTimeMillis()));
			ps.setBoolean(7, objecte.getCheckIn());
			ps.setString(8, objecte.getCodigoAsistencia());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		} finally {
			close(ps);
		}
	}

	@Override
	public List<Inscripcion> select(Inscripcion criteris) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Inscripcion> lstInscripcion = new ArrayList<Inscripcion>();
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT codigo, id_evento, estado, fecha_estado, motivo_estado, fecha_inscripcion, check_in, codigo_asistencia ");
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
			System.out.println(ps.toString());
			while (rs.next()) {
				Inscripcion inscripcion = retornaInscripcion(rs);
				lstInscripcion.add(inscripcion);
			}		
			return (lstInscripcion.isEmpty() || lstInscripcion.size() == 0) ? null:lstInscripcion;
		}catch(Exception e){
			throw new Exception(e.getMessage());
		} finally {
			close(ps,rs);
		}
	}

	public List<Inscripcion> selectByEventoFinalizado(Inscripcion criteris) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Inscripcion> lstInscripcion = new ArrayList<Inscripcion>();
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT codigo, id_evento, estado, fecha_estado, motivo_estado, fecha_inscripcion, check_in, codigo_asistencia ");
			sb.append("FROM v_consulta_inscipciones_eventos_celebrados ");
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
				Inscripcion inscripcion = retornaInscripcion(rs);
				lstInscripcion.add(inscripcion);
			}		
			return (lstInscripcion.isEmpty() || lstInscripcion.size() == 0) ? null:lstInscripcion;
		}catch(Exception e){
			throw new Exception(e.getMessage());
		} finally {
			close(ps,rs);
		}
	}
	
	private Inscripcion retornaInscripcion(ResultSet rs) throws SQLException{
		Inscripcion inscripcion = new Inscripcion();
		inscripcion.setCodigo(rs.getString("codigo"));
		inscripcion.setIdEvento(rs.getInt("id_evento"));
		inscripcion.setEstado(rs.getInt("estado"));
		inscripcion.setFechaEstado(rs.getDate("fecha_estado"));
		inscripcion.setMotivoEstado(rs.getString("motivo_estado"));
		inscripcion.setFechaInscripcion(rs.getDate("fecha_inscripcion"));
		inscripcion.setCheckIn(rs.getBoolean("check_in"));
		inscripcion.setCodigoAsistencia(rs.getString("codigo_asistencia"));
		return inscripcion;
	}
	

	
	
	@Override
	public void update(Inscripcion objecte) throws Exception {
		PreparedStatement ps = null;
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE INSCRIPCION SET ");

			if(objecte.getEstado()!=null) sql.append(" estado = ?,");
			if(objecte.getFechaEstado()!=null) sql.append(" fecha_estado = ?,");
			if(objecte.getMotivoEstado()!=null) sql.append(" motivo_estado = ?,");
			if(objecte.getFechaInscripcion()!=null) sql.append(" fecha_inscripcion = ?,");
			if(objecte.getCheckIn()!=null) sql.append(" check_in = ? ");
			if(objecte.getCodigoAsistencia()!=null) sql.append(" codigo_asistencia = ?,");
			sql = new StringBuffer(sql.substring(0,sql.length()-1) +" WHERE codigo = ? AND id_evento = ?");		
			
			ps = con.prepareStatement(sql.toString());
			int i=1;
			if(objecte.getEstado()!=null) {ps.setInt(i, objecte.getEstado()); i++;}
			if(objecte.getFechaEstado()!=null) {ps.setDate(i, objecte.getFechaEstado()); i++;}
			if(objecte.getMotivoEstado()!=null) {ps.setString(i, objecte.getMotivoEstado()); i++;}
			if(objecte.getFechaInscripcion()!=null) {ps.setDate(i, objecte.getFechaInscripcion()); i++;}
			if(objecte.getCheckIn()!=null) {ps.setBoolean(i, objecte.getCheckIn()); i++;}
			if(objecte.getCodigoAsistencia()!=null) {ps.setString(i, objecte.getCodigoAsistencia()); i++;}
			ps.setString(i, objecte.getCodigo());
			ps.setInt(i++, objecte.getIdEvento());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		} finally {
			close(ps);
		}
	}

	@Override
	public void delete(Inscripcion criteris) throws Exception {
		Inscripcion inscripcion = new Inscripcion();
		inscripcion.setCodigo(criteris.getCodigo());
		inscripcion.setIdEvento(criteris.getIdEvento());
		inscripcion.setFechaEstado(new java.sql.Date(System.currentTimeMillis()));
		inscripcion.setEstado(Constantes.REGISTRO_INACTIVO);
		inscripcion.setMotivoEstado(Constantes.REGISTRO_INACTIVO_MOTIVO);
		this.update(inscripcion);
	}
	
	public void checkIN(Inscripcion inscripcion) throws Exception {
		Inscripcion insCheckIN = new Inscripcion();
		insCheckIN.setCodigo(inscripcion.getCodigo());
		insCheckIN.setIdEvento(inscripcion.getIdEvento());
		insCheckIN.setCheckIn(true);
		this.update(insCheckIN);
	}
	
	public void checkOUT(Inscripcion inscripcion) throws Exception {
		Inscripcion insCheckIN = new Inscripcion();
		insCheckIN.setCodigo(inscripcion.getCodigo());
		insCheckIN.setIdEvento(inscripcion.getIdEvento());
		insCheckIN.setCheckIn(false);
		this.update(insCheckIN);
	}
}