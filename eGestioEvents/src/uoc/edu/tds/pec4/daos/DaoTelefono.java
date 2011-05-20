package uoc.edu.tds.pec4.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uoc.edu.tds.pec4.beans.Telefono;
import uoc.edu.tds.pec4.utils.Constantes;

public class DaoTelefono  extends DaoEntidad<Telefono>{

	public DaoTelefono(Connection con) {
		super(con);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(Telefono objecte) throws Exception {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("INSERT INTO telefono (prefijo_pais, telefono, extension, fecha_alta, estado, fecha_estado, motivo_estado, id_tipo_telefono, id_contacto) " +
			" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, objecte.getPrefijoPais());
			ps.setString(2, objecte.getTelefono());
			ps.setInt(3, objecte.getExtension()==null?-1:objecte.getExtension());
			ps.setDate(4, new java.sql.Date(System.currentTimeMillis()));
			ps.setInt(5, Constantes.REGISTRO_ACTIVO);
			ps.setDate(6, new java.sql.Date(System.currentTimeMillis()));
			ps.setString(7, objecte.getMotivoEstado());
			ps.setInt(8, objecte.getIdTipoTelefono());
			ps.setInt(9, objecte.getIdContacto());
			ps.executeUpdate();
        } catch (SQLException e) {
        	throw new Exception(e.getMessage());
        } finally {
        	close(ps);
        }		
		
	}

	@Override
	public List<Telefono> select(Telefono criteris) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Telefono> lstTelefono = new ArrayList<Telefono>();
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT id_telefono, prefijo_pais, telefono, extension, fecha_alta, estado, fecha_estado, motivo_estado, id_tipo_telefono, id_contacto ");
			sb.append("FROM telefono ");
			sb.append("WHERE (1=1) ");
			if(criteris.getIdTelefono()!=null) sb.append("AND id_telefono = ? ");
			if(criteris.getPrefijoPais()!=null) sb.append("AND prefijo_pais = ? ");
			if(criteris.getTelefono()!=null) sb.append("AND telefono = ? ");
			if(criteris.getExtension()!=null) sb.append("AND extension = ? ");
			if(criteris.getEstado()!=null) sb.append("AND estado = ? ");
			if(criteris.getFechaEstado()!=null) sb.append("AND fecha_estado = ? ");
			if(criteris.getMotivoEstado()!=null) sb.append("AND motivo_estado = ? ");
			if(criteris.getIdTipoTelefono()!=null) sb.append("AND id_tipo_telefono = ? ");
			if(criteris.getIdContacto()!=null) sb.append("AND id_contacto = ? ");
			
			ps = con.prepareStatement(sb.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
			if(criteris.getIdTelefono()!=null) {ps.setInt(i, criteris.getIdTelefono()); i++;}
			if(criteris.getPrefijoPais()!=null) {ps.setString(i, criteris.getPrefijoPais()); i++;}
			if(criteris.getTelefono()!=null) {ps.setString(i, criteris.getTelefono()); i++;}
			if(criteris.getExtension()!=null) {ps.setInt(i, criteris.getExtension()); i++;}
			if(criteris.getEstado()!=null) {ps.setInt(i, criteris.getEstado()); i++;}
			if(criteris.getFechaEstado()!=null) {ps.setDate(i, criteris.getFechaEstado()); i++;}
			if(criteris.getMotivoEstado()!=null) {ps.setString(i, criteris.getMotivoEstado()); i++;}
			if(criteris.getIdTipoTelefono()!=null) {ps.setInt(i, criteris.getIdTipoTelefono()); i++;}
			if(criteris.getIdContacto()!=null) {ps.setInt(i, criteris.getIdContacto()); i++;}
			
			rs = ps.executeQuery();
			while (rs.next()) {
				Telefono telefono = new Telefono();
				telefono.setIdTelefono(rs.getInt("id_telefono"));
				telefono.setPrefijoPais(rs.getString("prefijo_pais"));
				telefono.setTelefono(rs.getString("telefono"));
				telefono.setExtension(rs.getInt("extension"));
				telefono.setFechaAlta(rs.getDate("fecha_alta"));
				telefono.setEstado(rs.getInt("estado"));
				telefono.setFechaEstado(rs.getDate("fecha_estado"));
				telefono.setMotivoEstado(rs.getString("motivo_estado"));
				telefono.setIdTipoTelefono(rs.getInt("id_tipo_telefono"));
				telefono.setIdContacto(rs.getInt("id_contacto"));
				lstTelefono.add(telefono);
			}		
			return (lstTelefono.isEmpty() || lstTelefono.size() == 0) ? null:lstTelefono;
		}catch(Exception e){
			throw new Exception(e.getMessage());
		} finally {
			close(ps,rs);
		}
	}

	@Override
	public void update(Telefono objecte) throws Exception {
		PreparedStatement ps = null;
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE telefono SET ");
			if(objecte.getPrefijoPais() !=null) sql.append(" prefijo_pais = ?, ");
			if(objecte.getTelefono() !=null) sql.append(" telefono = ?, ");
			if(objecte.getExtension() !=null) sql.append(" extension = ?, ");
			if(objecte.getFechaAlta() !=null) sql.append(" fecha_alta = ?, ");
			if(objecte.getEstado() !=null) sql.append(" estado = ?, ");
			if(objecte.getFechaEstado() !=null) sql.append(" fecha_estado = ?, ");
			if(objecte.getMotivoEstado() !=null) sql.append(" motivo_estado = ?, ");
			if(objecte.getIdTipoTelefono() !=null) sql.append(" id_tipo_telefono = ?, ");
			sql = new StringBuffer(sql.substring(0,sql.length()-1) +" WHERE id_telefono = ? AND id_contacto = ?");
			
			ps = con.prepareStatement(sql.toString());
			
			int i=1;
			if(objecte.getPrefijoPais()!=null) {ps.setString(i, objecte.getPrefijoPais()); i++;}
			if(objecte.getTelefono()!=null) {ps.setString(i, objecte.getTelefono()); i++;}
			if(objecte.getExtension()!=null) {ps.setInt(i, objecte.getExtension()); i++;}
			if(objecte.getFechaAlta()!=null) {ps.setDate(i, objecte.getFechaAlta()); i++;}
			if(objecte.getEstado()!=null) {ps.setInt(i, objecte.getEstado()); i++;}
			if(objecte.getFechaEstado()!=null) {ps.setDate(i, new java.sql.Date(System.currentTimeMillis())); i++;}
			if(objecte.getMotivoEstado()!=null) {ps.setString(i, objecte.getMotivoEstado()); i++;}
			if(objecte.getIdTipoTelefono()!=null) {ps.setInt(i, objecte.getIdTipoTelefono()); i++;}
			if(objecte.getIdTelefono()!=null) {ps.setInt(i, objecte.getIdTelefono()); i++;}
			if(objecte.getIdContacto()!=null) {ps.setInt(i, objecte.getIdContacto()); i++;}
			ps.executeUpdate();
			
        } catch (SQLException e) {
        	throw new Exception(e.getMessage());
        } finally {
        	close(ps);
        }		
	}
		

	@Override
	public void delete(Telefono criteris) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
