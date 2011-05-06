package uoc.edu.tds.pec4.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import uoc.edu.tds.pec4.beans.Contacto;

public class DaoContacto extends DaoEntidad<Contacto>{

	public DaoContacto(Connection con) {
		super(con);
	}

	@Override
	public void insert(Contacto objecte) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}

	@Override
	public List<Contacto> select(Contacto criteris) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Contacto> lstContacto = new ArrayList<Contacto>();
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT id_contacto, domicilio, cp, localidad, provincia, id_pais, email, web, fecha_alta, estado, fecha_estado, motivo_estado ");
			sb.append("FROM CONTACTO ");
			sb.append("WHERE (1=1) ");
			if(criteris.getIdPais()!=null) sb.append("AND id_contacto = ? ");
			if(criteris.getDomicilio()!=null) sb.append("AND domicilio = ? ");
			if(criteris.getCp()!=null) sb.append("AND cp = ? ");
			if(criteris.getLocalidad()!=null) sb.append("AND localidad = ? ");
			if(criteris.getProvincia()!=null) sb.append("AND provincia = ? ");
			if(criteris.getEmail()!=null) sb.append("AND email = ? ");
			if(criteris.getWeb()!=null) sb.append("AND web = ? ");
			if(criteris.getFechaAlta()!=null) sb.append("AND fecha_alta = ? ");
			if(criteris.getEstado()!=null) sb.append("AND estado = ? ");
			if(criteris.getFechaEstado()!=null) sb.append("AND fecha_estado = ? ");
			if(criteris.getMotivoEstado()!=null) sb.append("AND motivo_estado = ? ");
			
			ps = con.prepareStatement(sb.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
			if(criteris.getIdPais()!=null) {ps.setInt(i, criteris.getIdPais()); i++;}
			if(criteris.getDomicilio()!=null) {ps.setString(i, criteris.getDomicilio()); i++;}
			if(criteris.getCp()!=null) {ps.setInt(i, criteris.getCp()); i++;}
			if(criteris.getLocalidad()!=null) {ps.setString(i, criteris.getLocalidad()); i++;}
			if(criteris.getProvincia()!=null) {ps.setString(i, criteris.getProvincia()); i++;}
			if(criteris.getEmail()!=null) {ps.setString(i, criteris.getEmail()); i++;}
			if(criteris.getWeb()!=null) {ps.setString(i, criteris.getWeb()); i++;}
			if(criteris.getFechaAlta()!=null) {ps.setDate(i, criteris.getFechaAlta()); i++;}
			if(criteris.getEstado()!=null) {ps.setInt(i, criteris.getEstado()); i++;}
			if(criteris.getFechaEstado()!=null) {ps.setDate(i, criteris.getFechaEstado()); i++;}
			if(criteris.getMotivoEstado()!=null) {ps.setString(i, criteris.getMotivoEstado()); i++;}
			
			rs = ps.executeQuery();
			while (rs.next()) {
				Contacto contacto = new Contacto();
				contacto.setIdContacto(rs.getInt("id_contacto"));
				contacto.setDomicilio(rs.getString("domicilio"));
				contacto.setCp(rs.getInt("cp"));
				contacto.setLocalidad(rs.getString("localidad"));
				contacto.setProvincia(rs.getString("provincia"));
				contacto.setEmail(rs.getString("email"));
				contacto.setWeb(rs.getString("web"));
				contacto.setFechaAlta(rs.getDate("fecha_alta"));
				contacto.setEstado(rs.getInt("estado"));
				contacto.setFechaEstado(rs.getDate("fecha_estado"));
				contacto.setMotivoEstado(rs.getString("motivo_estado"));
				lstContacto.add(contacto);
			}		
			return (lstContacto.isEmpty() || lstContacto.size() == 0) ? null:lstContacto;
		}catch(Exception e){
			throw new Exception(e.getMessage());
		} finally {
			close(ps,rs);
		}
	}

	@Override
	public void update(Contacto objecte) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}

	@Override
	public void delete(Contacto criteris) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}

}
