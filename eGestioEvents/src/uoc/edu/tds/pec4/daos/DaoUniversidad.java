package uoc.edu.tds.pec4.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uoc.edu.tds.pec4.beans.Universidad;
import uoc.edu.tds.pec4.utils.Constantes;

public class DaoUniversidad extends DaoEntidad<Universidad>{

	public DaoUniversidad(Connection con) {
		super(con);
	}

	@Override
	public void insert(Universidad objecte) throws Exception {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("INSERT INTO universidad (nombre, id_contacto, fecha_alta, estado, fecha_estado, motivo_estado) " +
			" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, objecte.getNombre());
			ps.setInt(2, objecte.getIdContacto());
			ps.setDate(3, new java.sql.Date(System.currentTimeMillis()));
			ps.setInt(4, Constantes.REGISTRO_ACTIVO);
			ps.setDate(5, new java.sql.Date(System.currentTimeMillis()));
			ps.setString(6, objecte.getMotivoEstado());
			ps.executeUpdate();
        } catch (SQLException e) {
        	throw new Exception(e.getMessage());
        } finally {
        	close(ps);
        }		
	}

	@Override
	public List<Universidad> select(Universidad criteris) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Universidad> lstUniversidades = new ArrayList<Universidad>();
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT id_universidad, nombre, id_contacto, fecha_alta, estado, fecha_estado, motivo_estado ");
			sb.append("FROM UNIVERSIDAD ");
			sb.append("WHERE (1=1) ");
			if(criteris.getIdUniversidad()!=null) sb.append("AND id_universidad = ? ");
			if(criteris.getNombre() !=null) sb.append("AND nombre = ? ");
			if(criteris.getIdContacto() !=null) sb.append("AND id_contacto = ? ");
			if(criteris.getFechaAlta() !=null) sb.append("AND fecha_alta = ? ");
			if(criteris.getEstado() !=null) sb.append("AND estado = ? ");
			if(criteris.getFechaEstado() !=null) sb.append("AND fecha_estado = ? ");
			if(criteris.getMotivoEstado() !=null) sb.append("AND motivo_estado = ? ");
			sb.append("order by nombre ");
			
			ps = con.prepareStatement(sb.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
			if(criteris.getIdUniversidad()!=null) {ps.setInt(i, criteris.getIdUniversidad()); i++;}
			if(criteris.getNombre()!=null) {ps.setString(i, criteris.getNombre()); i++;}
			if(criteris.getIdContacto()!=null) {ps.setInt(i, criteris.getIdContacto()); i++;}
			if(criteris.getFechaAlta()!=null) {ps.setDate(i, criteris.getFechaAlta()); i++;}
			if(criteris.getEstado()!=null) {ps.setInt(i, criteris.getEstado()); i++;}
			if(criteris.getFechaEstado()!=null) {ps.setDate(i, criteris.getFechaEstado()); i++;}
			if(criteris.getMotivoEstado()!=null) {ps.setString(i, criteris.getMotivoEstado()); i++;}
			
			rs = ps.executeQuery();
			while (rs.next()) {
				Universidad universidad = new Universidad();
				universidad.setIdUniversidad(rs.getInt("id_universidad"));
				universidad.setNombre(rs.getString("nombre"));
				universidad.setIdContacto(rs.getInt("id_contacto"));
				universidad.setFechaAlta(rs.getDate("fecha_alta"));
				universidad.setEstado(rs.getInt("estado"));
				universidad.setFechaEstado(rs.getDate("fecha_estado"));
				universidad.setMotivoEstado(rs.getString("motivo_estado"));
				lstUniversidades.add(universidad);
			}		
			return (lstUniversidades.isEmpty() || lstUniversidades.size() == 0) ? null:lstUniversidades;
		}catch(Exception e){
			throw new Exception(e.getMessage());
		} finally {
			close(ps,rs);
		}
	}

	@Override
	public void update(Universidad objecte) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}

	@Override
	public void delete(Universidad criteris) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}
	
	public Integer insertReturnId(Universidad universidad) throws Exception {
		try {
			 this.insert(universidad);
			 return retornaIdGenerado("seq_universidad");
        } catch (SQLException e) {
        	throw new Exception(e.getMessage());
        } 
	}

}
