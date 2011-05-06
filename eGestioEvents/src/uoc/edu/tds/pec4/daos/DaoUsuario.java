package uoc.edu.tds.pec4.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import uoc.edu.tds.pec4.beans.Usuario;


public class DaoUsuario extends DaoEntidad<Usuario>{

	public DaoUsuario(Connection con) {
		super(con);
	}

	@Override
	public void insert(Usuario objecte) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}

	@Override
	public List<Usuario> select(Usuario criteris) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Usuario> lstUsuarios = new ArrayList<Usuario>();
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT codigo, nombre, apellidos, sexo, fecha_nacimiento, fecha_alta, fecha_contrasena, contrasena, cambiar_contrasena, " +
					"estado, fecha_estado, motivo_estado, tipo_usuario , id_rol, id_centro, id_documento_identificacion, id_contacto, id_datos_bancarios  ");
			sb.append("FROM USUARIO ");
			sb.append("WHERE (1=1) ");
			if(criteris.getCodigo() !=null) sb.append("AND codigo = ? ");
			if(criteris.getNombre() !=null) sb.append("AND nombre = ? ");
			if(criteris.getApellidos() !=null) sb.append("AND apellidos = ? ");
			if(criteris.getSexo() !=null) sb.append("AND sexo = ? ");
			if(criteris.getFechaNacimiento() !=null) sb.append("AND fecha_nacimiento = ? ");
			if(criteris.getFechaAlta() !=null) sb.append("AND fecha_alta = ? ");
			if(criteris.getFechaContraseña() !=null) sb.append("AND fecha_contrasena = ? ");
			if(criteris.getContraseña() !=null) sb.append("AND contrasena = ? ");
			if(criteris.getCambiarContraseña() !=null) sb.append("AND cambiar_contrasena = ? ");
			if(criteris.getFechaEstado() !=null) sb.append("AND fecha_estado = ? ");
			if(criteris.getMotivoEstado() !=null) sb.append("AND motivo_estado = ? ");
			if(criteris.getTipoUsuario() !=null) sb.append("AND tipo_usuario = ? ");
			if(criteris.getIdCentro() !=null) sb.append("AND id_centro = ? ");
			if(criteris.getIdDocumentoIdentificacion() !=null) sb.append("AND id_documento_identificacion = ? ");
			if(criteris.getIdContacto() !=null) sb.append("AND id_contacto = ? ");
			if(criteris.getEstado() !=null) sb.append("AND estado = ? ");
			if(criteris.getIdDatosBancarios() !=null) sb.append("AND id_datos_bancarios = ? ");
			if(criteris.getIdRol() !=null) sb.append("AND id_rol = ? ");
			
			ps = con.prepareStatement(sb.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
			
			if(criteris.getCodigo()!=null) {ps.setString(i, criteris.getCodigo()); i++;}
			if(criteris.getNombre()!=null) {ps.setString(i, criteris.getNombre()); i++;}
			if(criteris.getApellidos()!=null) {ps.setString(i, criteris.getApellidos()); i++;}
			if(criteris.getSexo()!=null) {ps.setString(i, criteris.getSexo()); i++;}
			if(criteris.getFechaNacimiento()!=null) {ps.setDate(i, criteris.getFechaNacimiento()); i++;}
			if(criteris.getFechaAlta()!=null) {ps.setDate(i, criteris.getFechaAlta()); i++;}
			if(criteris.getFechaContraseña()!=null) {ps.setDate(i, criteris.getFechaContraseña()); i++;}
			if(criteris.getContraseña()!=null) {ps.setString(i, criteris.getContraseña()); i++;}
			if(criteris.getCambiarContraseña()!=null) {ps.setBoolean(i, criteris.getCambiarContraseña()); i++;}
			if(criteris.getFechaEstado()!=null) {ps.setDate(i, criteris.getFechaEstado()); i++;}
			if(criteris.getMotivoEstado()!=null) {ps.setString(i, criteris.getMotivoEstado()); i++;}
			if(criteris.getTipoUsuario()!=null) {ps.setInt(i, criteris.getTipoUsuario()); i++;}
			if(criteris.getIdCentro()!=null) {ps.setInt(i, criteris.getIdCentro()); i++;}
			if(criteris.getIdDocumentoIdentificacion()!=null) {ps.setInt(i, criteris.getIdDocumentoIdentificacion()); i++;}
			if(criteris.getIdContacto()!=null) {ps.setInt(i, criteris.getIdContacto()); i++;}
			if(criteris.getEstado()!=null) {ps.setInt(i, criteris.getEstado()); i++;}
			if(criteris.getIdDatosBancarios()!=null) {ps.setInt(i, criteris.getIdDatosBancarios()); i++;}
			if(criteris.getIdRol()!=null) {ps.setInt(i, criteris.getIdRol()); i++;}
			
			rs = ps.executeQuery();
			while (rs.next()) {
				Usuario usu = new Usuario();
				usu.setCodigo(rs.getString("codigo"));
				usu.setNombre(rs.getString("nombre"));
				usu.setApellidos(rs.getString("apellidos"));
				usu.setSexo(rs.getString("sexo"));
				usu.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
				usu.setFechaAlta(rs.getDate("fecha_alta"));
				usu.setFechaContraseña(rs.getDate("fecha_contrasena"));
				usu.setContraseña(rs.getString("contrasena"));
				usu.setCambiarContraseña(rs.getBoolean("cambiar_contrasena"));
				usu.setFechaEstado(rs.getDate("fecha_estado"));
				usu.setMotivoEstado(rs.getString("motivo_estado"));
				usu.setTipoUsuario(rs.getInt("tipo_usuario"));
				usu.setIdCentro(rs.getInt("id_centro"));
				usu.setIdDocumentoIdentificacion(rs.getInt("id_documento_identificacion"));
				usu.setIdContacto(rs.getInt("id_contacto"));
				usu.setIdRol(rs.getInt("id_rol"));
				usu.setEstado(rs.getInt("estado"));
				usu.setIdDatosBancarios(rs.getInt("id_datos_bancarios"));
				lstUsuarios.add(usu);
			}		
			return (lstUsuarios.isEmpty() || lstUsuarios.size() == 0) ? null:lstUsuarios;
		}catch(Exception e){
			throw new Exception(e.getMessage());
		} finally {
			close(ps,rs);
		}
	}

	@Override
	public void update(Usuario objecte) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}

	@Override
	public void delete(Usuario criteris) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}
	
}
