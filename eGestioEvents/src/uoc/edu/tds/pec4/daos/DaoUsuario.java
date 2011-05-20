package uoc.edu.tds.pec4.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uoc.edu.tds.pec4.beans.Usuario;
import uoc.edu.tds.pec4.beans.UsuarioViewConsulta;
import uoc.edu.tds.pec4.utils.Base64Coder;
import uoc.edu.tds.pec4.utils.Constantes;


public class DaoUsuario extends DaoEntidad<Usuario>{
	
	private static final String CONSULTA_USUARIO = "SELECT codigo, nombre, apellidos, sexo, fecha_nacimiento, fecha_alta, fecha_contrasena, contrasena, cambiar_contrasena, " +
	"estado, fecha_estado, motivo_estado, tipo_usuario , id_rol, id_centro, id_documento_identificacion, id_contacto, id_datos_bancarios ";
	
	public DaoUsuario(Connection con) {
		super(con);
	}

	@Override
	public void insert(Usuario objecte) throws Exception {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("INSERT INTO usuario (codigo, nombre, apellidos, sexo, fecha_nacimiento, fecha_alta, fecha_contrasena" +
					", contrasena, cambiar_contrasena, estado, fecha_estado, motivo_estado, tipo_usuario, id_rol, id_centro, id_documento_identificacion," +
					"id_contacto, id_datos_bancarios) " +
			" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			
			ps.setString(1, objecte.getCodigo());
			ps.setString(2, objecte.getNombre());
			ps.setString(3, objecte.getApellidos());
			ps.setString(4, objecte.getSexo());
			ps.setDate(5, objecte.getFechaNacimiento());
			ps.setDate(6, new java.sql.Date(System.currentTimeMillis()));
			ps.setDate(7, new java.sql.Date(System.currentTimeMillis()));
			ps.setString(8, Base64Coder.encodeString(objecte.getContrasena()));
			ps.setBoolean(9, objecte.getCambiarContrasena());
			ps.setInt(10, Constantes.REGISTRO_ACTIVO);
			ps.setDate(11,objecte.getFechaEstado());
			ps.setString(12, objecte.getMotivoEstado());
			ps.setInt(13, objecte.getTipoUsuario());
			ps.setInt(14, objecte.getIdRol());
			ps.setInt(15, objecte.getIdCentro()==null?-1:objecte.getIdCentro());
			ps.setInt(16, objecte.getIdDocumentoIdentificacion());
			ps.setInt(17, objecte.getIdContacto());
			ps.setInt(18, objecte.getIdDatosBancarios());
			ps.executeUpdate();
        
		} catch (SQLException e) {
        	throw new Exception(e.getMessage());
        } finally {
        	close(ps);
        }		
	}
	
	
	public List<Usuario> selectUsersByView(UsuarioViewConsulta criteris)throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Usuario> lstUsuarios = new ArrayList<Usuario>();
		try{
			StringBuffer sb = new StringBuffer();
			sb.append(CONSULTA_USUARIO);
			sb.append("FROM v_consulta_usuarios ");
			sb.append("WHERE (1=1) ");
			if(criteris.getTipoUsuario() !=null) sb.append("AND tipo_usuario = ? ");
			if(criteris.getNombre() !=null) sb.append("AND nombre like ? ");
			if(criteris.getApellidos() !=null) sb.append("AND apellidos like ? ");
			if(criteris.getIdDocumentoIdentificacion() !=null) sb.append("AND id_documento_identificacion = ? ");
			if(criteris.getNumeroDocumento() !=null) sb.append("AND numero_documento like ? ");
			
			if(criteris.getFechaInicio() != null && criteris.getFechaFin()!=null){
				sb.append("AND fecha_alta BETWEEN ? AND ?");
			}else if(criteris.getFechaInicio() != null && criteris.getFechaFin()==null){
				sb.append("AND fecha_alta >=  ? ");
			}
			
			if(criteris.getLocalidad() !=null) sb.append("AND localidad = ? ");
			if(criteris.getIdCentro() !=null) sb.append("AND id_centro = ? ");
			if(criteris.getIdRol() !=null) sb.append("AND id_rol = ? ");
			if(criteris.getEstado()!= null) sb.append("AND estado = ?");
			
			sb.append(" order by nombre,apellidos");

			
			ps = con.prepareStatement(sb.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
			if(criteris.getTipoUsuario()!=null) {ps.setInt(i, criteris.getTipoUsuario()); i++;}
			if(criteris.getNombre()!=null) {ps.setString(i, "%"+criteris.getNombre()+"%"); i++;}
			if(criteris.getApellidos()!=null) {ps.setString(i, "%"+criteris.getApellidos()+"%"); i++;}
			if(criteris.getIdDocumentoIdentificacion()!=null) {ps.setInt(i, criteris.getIdDocumentoIdentificacion()); i++;}
			if(criteris.getNumeroDocumento()!=null) {ps.setString(i, "%"+criteris.getNumeroDocumento()+"%"); i++;}
			
			if(criteris.getFechaInicio() != null && criteris.getFechaFin()!=null){
				ps.setDate(i, criteris.getFechaInicio()); i++;
				ps.setDate(i, criteris.getFechaFin()); i++;
			}else if(criteris.getFechaInicio() != null && criteris.getFechaFin()==null){
				ps.setDate(i, criteris.getFechaInicio()); i++;
			}
			
			if(criteris.getLocalidad()!=null) {ps.setString(i, "%"+criteris.getLocalidad()+"%"); i++;}
			if(criteris.getIdCentro()!=null) {ps.setInt(i, criteris.getIdCentro()); i++;}
			if(criteris.getIdRol()!=null) {ps.setInt(i, criteris.getIdRol()); i++;}
			if(criteris.getEstado()!=null) {ps.setInt(i, criteris.getEstado()); i++;}
			rs = ps.executeQuery();
			while (rs.next()) {
				Usuario usu = retornaUsuario(rs);
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
	public List<Usuario> select(Usuario criteris) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Usuario> lstUsuarios = new ArrayList<Usuario>();
		try{
			StringBuffer sb = new StringBuffer();
			sb.append(CONSULTA_USUARIO);
			sb.append("FROM USUARIO ");
			sb.append("WHERE (1=1) ");
			if(criteris.getCodigo() !=null) sb.append("AND codigo =  ");
			if(criteris.getNombre() !=null) sb.append("AND nombre = ? ");
			if(criteris.getApellidos() !=null) sb.append("AND apellidos = ? ");
			if(criteris.getSexo() !=null) sb.append("AND sexo = ? ");
			if(criteris.getFechaNacimiento() !=null) sb.append("AND fecha_nacimiento = ? ");
			if(criteris.getFechaAlta() !=null) sb.append("AND fecha_alta = ? ");
			if(criteris.getFechaContrasena() !=null) sb.append("AND fecha_contrasena = ? ");
			if(criteris.getContrasena() !=null) sb.append("AND contrasena = ? ");
			if(criteris.getCambiarContrasena() !=null) sb.append("AND cambiar_contrasena = ? ");
			if(criteris.getFechaEstado() !=null) sb.append("AND fecha_estado = ? ");
			if(criteris.getMotivoEstado() !=null) sb.append("AND motivo_estado = ? ");
			if(criteris.getTipoUsuario() !=null) sb.append("AND tipo_usuario = ? ");
			if(criteris.getIdCentro() !=null) sb.append("AND id_centro = ? ");
			if(criteris.getIdDocumentoIdentificacion() !=null) sb.append("AND id_documento_identificacion = ? ");
			if(criteris.getIdContacto() !=null) sb.append("AND id_contacto = ? ");
			if(criteris.getEstado() !=null) sb.append("AND estado = ? ");
			if(criteris.getIdDatosBancarios() !=null) sb.append("AND id_datos_bancarios = ? ");
			if(criteris.getIdRol() !=null) sb.append("AND id_rol = ? ");
			sb.append("order by nombre, apellidos ");
			
			ps = con.prepareStatement(sb.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
			
			if(criteris.getCodigo()!=null) {ps.setString(i, criteris.getCodigo()); i++;}
			if(criteris.getNombre()!=null) {ps.setString(i, criteris.getNombre()); i++;}
			if(criteris.getApellidos()!=null) {ps.setString(i, criteris.getApellidos()); i++;}
			if(criteris.getSexo()!=null) {ps.setString(i, criteris.getSexo()); i++;}
			if(criteris.getFechaNacimiento()!=null) {ps.setDate(i, criteris.getFechaNacimiento()); i++;}
			if(criteris.getFechaAlta()!=null) {ps.setDate(i, criteris.getFechaAlta()); i++;}
			if(criteris.getFechaContrasena()!=null) {ps.setDate(i, criteris.getFechaContrasena()); i++;}
			if(criteris.getContrasena()!=null) {ps.setString(i, criteris.getContrasena()); i++;}
			if(criteris.getCambiarContrasena()!=null) {ps.setBoolean(i, criteris.getCambiarContrasena()); i++;}
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
				Usuario usu = retornaUsuario(rs);
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
		PreparedStatement ps = null;
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE usuario SET ");
			if(objecte.getNombre() !=null) sql.append("AND nombre = ? ");
			if(objecte.getApellidos() !=null) sql.append("AND apellidos = ? ");
			if(objecte.getSexo() !=null) sql.append("AND sexo = ? ");
			if(objecte.getFechaNacimiento() !=null) sql.append("AND fecha_nacimiento = ? ");
			if(objecte.getMotivoEstado() !=null) sql.append("AND motivo_estado = ? ");
			if(objecte.getIdCentro() !=null) sql.append("AND id_centro = ? ");
			sql = new StringBuffer(sql.substring(0,sql.length()-1) +" WHERE codigo = ? ");
			
			ps = con.prepareStatement(sql.toString());
			
			int i=1;
			if(objecte.getNombre()!=null) {ps.setString(i, objecte.getNombre()); i++;}
			if(objecte.getApellidos()!=null) {ps.setString(i, objecte.getApellidos()); i++;}
			if(objecte.getSexo()!=null) {ps.setString(i, objecte.getSexo()); i++;}
			if(objecte.getFechaNacimiento()!=null) {ps.setDate(i, objecte.getFechaNacimiento()); i++;}
			if(objecte.getMotivoEstado()!=null) {ps.setString(i, objecte.getMotivoEstado()); i++;}
			if(objecte.getIdCentro()!=null) {ps.setInt(i, objecte.getIdCentro()); i++;}
			if(objecte.getCodigo()!=null) {ps.setString(i, objecte.getCodigo()); i++;}
			ps.executeUpdate();
			
        } catch (SQLException e) {
        	throw new Exception(e.getMessage());
        } finally {
        	close(ps);
        }		
	}

	@Override
	public void delete(Usuario criteris) throws Exception {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("update usuario set estado = ?, fecha_estado = ? where codigo = ?");
			ps.setInt(1, Constantes.REGISTRO_INACTIVO);
			ps.setDate(2, new java.sql.Date(System.currentTimeMillis()));
			ps.setString(3, criteris.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
        	throw new Exception(e.getMessage());
        } finally {
        	close(ps);
        }		
	}
	
	private Usuario retornaUsuario(ResultSet rs) throws SQLException{
		Usuario usu = new Usuario();
		usu.setCodigo(rs.getString("codigo"));
		usu.setNombre(rs.getString("nombre"));
		usu.setApellidos(rs.getString("apellidos"));
		usu.setSexo(rs.getString("sexo"));
		usu.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
		usu.setFechaAlta(rs.getDate("fecha_alta"));
		usu.setFechaContrasena(rs.getDate("fecha_contrasena"));
		usu.setContrasena(rs.getString("contrasena"));
		usu.setCambiarContrasena(rs.getBoolean("cambiar_contrasena"));
		usu.setFechaEstado(rs.getDate("fecha_estado"));
		usu.setMotivoEstado(rs.getString("motivo_estado"));
		usu.setTipoUsuario(rs.getInt("tipo_usuario"));
		usu.setIdCentro(rs.getInt("id_centro"));
		usu.setIdDocumentoIdentificacion(rs.getInt("id_documento_identificacion"));
		usu.setIdContacto(rs.getInt("id_contacto"));
		usu.setIdRol(rs.getInt("id_rol"));
		usu.setEstado(rs.getInt("estado"));
		usu.setIdDatosBancarios(rs.getInt("id_datos_bancarios"));
		return usu;
	}
	
}
