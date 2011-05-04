package uoc.edu.tds.pec4.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import uoc.edu.tds.pec4.beans.Evento;

public class DaoEvento extends DaoEntidad<Evento>{
	
	public DaoEvento(Connection con) {
		super(con);
	}
	
	@Override
	public void delete(Evento objecte) throws Exception {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("DELETE FROM EVENTO WHERE ID_EVENTO = ?");
			ps.setObject(1, objecte.getIdEvento());
			ps.executeUpdate();
		} catch (Exception e) {
			throw new Exception();
		} finally {
			close(ps);
		}
	}

	@Override
	public void insert(Evento objecte) throws Exception {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("INSERT INTO EVENTO (id_evento, descripcion, fecha_inicio, fecha_fin, fecha_alta)  VALUES (?, ?, ?, ?, ?)");
			ps.setInt(1, objecte.getIdEvento());
			ps.setString(2, objecte.getDescripcion());
			ps.setDate(3, objecte.getFechaInicio());
			ps.setDate(4, objecte.getFechaFin());
			ps.setDate(5, objecte.getFechaAlta());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		} finally {
			close(ps);
		}
	}

	@Override
	public List<Evento> select(Evento criteris) throws Exception {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Evento> lstEventos = new ArrayList<Evento>();
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT id_evento, descripcion, fecha_inicio, fecha_fin, fecha_alta ");
			sb.append("FROM evento ");
			sb.append("WHERE (1=1) ");
			if(criteris.getIdEvento()!=null) sb.append("AND id_evento = ? ");
			if(criteris.getDescripcion() !=null) sb.append("AND descripcion = ? ");
			if(criteris.getFechaInicio() !=null) sb.append("AND fecha_inicio = ? ");
			if(criteris.getFechaFin() !=null) sb.append("AND fecha_fin = ? ");
			if(criteris.getFechaAlta() !=null) sb.append("AND fecha_alta = ? ");
			
			ps = con.prepareStatement(sb.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
			if(criteris.getIdEvento()!=null) {ps.setInt(i, criteris.getIdEvento()); i++;}
			if(criteris.getDescripcion()!=null) {ps.setString(i, criteris.getDescripcion()); i++;}
			if(criteris.getFechaInicio()!=null) {ps.setDate(i, criteris.getFechaInicio()); i++;}
			if(criteris.getFechaFin()!=null) {ps.setDate(i, criteris.getFechaFin()); i++;}
			if(criteris.getFechaAlta()!=null) {ps.setDate(i, criteris.getFechaAlta()); i++;}
			
			rs = ps.executeQuery();
			while (rs.next()) {
				Evento evento = new Evento();
				evento.setIdEvento(rs.getInt("id_evento"));
				evento.setDescripcion(rs.getString("descripcion"));
				evento.setFechaInicio(rs.getDate("fecha_inicio"));
				evento.setFechaFin(rs.getDate("fecha_fin"));
				evento.setFechaAlta(rs.getDate("fecha_alta"));
				lstEventos.add(evento);
			}		
			return (lstEventos.isEmpty() || lstEventos.size() == 0) ? null:lstEventos;
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} finally {
			close(ps,rs);
		}
	}

	@Override
	public void update(Evento objecte) throws Exception {
		
		PreparedStatement ps = null;
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE EVENTO SET ");
			if(objecte.getDescripcion() !=null) sql.append(" descripcion = ?,");
			if(objecte.getFechaInicio() !=null) sql.append(" fecha_inicio = ?,");
			if(objecte.getFechaFin() !=null) sql.append(" fecha_fin = ?,");
			if(objecte.getFechaAlta() !=null) sql.append(" fecha_alta = ?,");
			sql = new StringBuffer(sql.substring(0,sql.length()-1) +" WHERE id_evento=?");
			
			ps = con.prepareStatement(sql.toString());
			
			int i=1;
			if(objecte.getDescripcion()!=null) {ps.setString(i, objecte.getDescripcion()); i++;}
			if(objecte.getFechaInicio()!=null) {ps.setDate(i, objecte.getFechaInicio()); i++;}
			if(objecte.getFechaFin()!=null) {ps.setDate(i, objecte.getFechaFin()); i++;}
			if(objecte.getFechaAlta()!=null) {ps.setDate(i, objecte.getFechaAlta()); i++;}
			if(objecte.getIdEvento()!=null) {ps.setInt(i, objecte.getIdEvento()); i++;}
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		} finally {
			close(ps);
		}
	}

}
