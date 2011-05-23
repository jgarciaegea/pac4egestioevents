package uoc.edu.tds.pec4.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uoc.edu.tds.pec4.beans.CentroDocente;
import uoc.edu.tds.pec4.beans.CentroDocenteViewConsulta;
import uoc.edu.tds.pec4.utils.Constantes;

public class DaoCentroDocente extends DaoEntidad<CentroDocente>{

	private static final String CONSULTA_CENTRO_DOCENTE = "SELECT id_centro, nombre, id_contacto, id_universidad, fecha_alta, estado, fecha_estado, motivo_estado ";
	
	public DaoCentroDocente(Connection con) {
		super(con);
	}

	@Override
	public void insert(CentroDocente objecte) throws Exception {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("INSERT INTO centrodocente (nombre, id_contacto, id_universidad, fecha_alta, estado,fecha_estado, motivo_estado) " +
			" VALUES (?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, objecte.getNombre());
			ps.setInt(2, objecte.getIdContacto());
			ps.setInt(3, objecte.getIdUniversidad());
			ps.setDate(4, new java.sql.Date(System.currentTimeMillis()));
			ps.setInt(5, Constantes.REGISTRO_ACTIVO);
			ps.setDate(6, new java.sql.Date(System.currentTimeMillis()));
			ps.setString(7, objecte.getMotivoEstado());
			ps.executeUpdate();
        } catch (SQLException e) {
        	throw new Exception(e.getMessage());
        } finally {
        	close(ps);
        }		
	}

	@Override
	public List<CentroDocente> select(CentroDocente criteris) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<CentroDocente> lstCentroDocente = new ArrayList<CentroDocente>();
		try{
			StringBuffer sb = new StringBuffer();
			sb.append(CONSULTA_CENTRO_DOCENTE);
			sb.append("FROM CENTRODOCENTE ");
			sb.append("WHERE (1=1) ");
			if(criteris.getIdCentro()!=null) sb.append("AND id_centro = ? ");
			if(criteris.getNombre()!=null) sb.append("AND nombre = ? ");
			if(criteris.getIdContacto()!=null) sb.append("AND id_contacto = ? ");
			if(criteris.getIdUniversidad()!=null) sb.append("AND id_universidad = ? ");
			if(criteris.getFechaAlta()!=null) sb.append("AND fecha_alta = ? ");
			if(criteris.getEstado()!=null) sb.append("AND estado = ? ");
			if(criteris.getFechaEstado()!=null) sb.append("AND fecha_estado = ? ");
			if(criteris.getMotivoEstado()!=null) sb.append("AND motivo_estado = ? ");
			sb.append("order by nombre");
			
			ps = con.prepareStatement(sb.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
			if(criteris.getIdCentro()!=null) {ps.setInt(i, criteris.getIdCentro()); i++;}
			if(criteris.getNombre()!=null) {ps.setString(i, criteris.getNombre()); i++;}
			if(criteris.getIdContacto()!=null) {ps.setInt(i, criteris.getIdContacto()); i++;}
			if(criteris.getIdUniversidad()!=null) {ps.setInt(i, criteris.getIdUniversidad()); i++;}
			if(criteris.getFechaAlta()!=null) {ps.setDate(i, criteris.getFechaAlta()); i++;}
			if(criteris.getEstado()!=null) {ps.setInt(i, criteris.getEstado()); i++;}
			if(criteris.getFechaEstado()!=null) {ps.setDate(i, criteris.getFechaEstado()); i++;}
			if(criteris.getMotivoEstado()!=null) {ps.setString(i, criteris.getMotivoEstado()); i++;}
			
			rs = ps.executeQuery();
			while (rs.next()) {
				CentroDocente centroDocente = getCentroDocente(rs);
				lstCentroDocente.add(centroDocente);
			}		
			return (lstCentroDocente.isEmpty() || lstCentroDocente.size() == 0) ? null:lstCentroDocente;
		}catch(Exception e){
			throw new Exception(e.getMessage());
		} finally {
			close(ps,rs);
		}
	}
	
	
	public List<CentroDocente> selectCentroDocenteSearch(CentroDocenteViewConsulta criteris)throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<CentroDocente> lstCentroDoc = new ArrayList<CentroDocente>();
		try{
			//SELECT id_centro,  id_contacto, id_universidad, fecha_alta, estado, fecha_estado, motivo_estado";
			StringBuffer sb = new StringBuffer();
			sb.append(CONSULTA_CENTRO_DOCENTE);
			sb.append("FROM v_consulta_centrodocentes ");
			sb.append("WHERE (1=1) ");
			if(criteris.getIdUniversidad() !=null) sb.append("AND id_universidad = ? ");
			if(criteris.getNombre() !=null) sb.append("AND upper(nombre) like ? ");
			if(criteris.getLocalidad() !=null) sb.append("AND upper(localidad) like ? ");
			if(criteris.getCp() !=null) sb.append("AND cp like ? ");
			
			if(criteris.getFechaInicio() != null && criteris.getFechaFin()!=null){
				sb.append("AND fecha_alta BETWEEN ? AND ?");
			}else if(criteris.getFechaInicio() != null && criteris.getFechaFin()==null){
				sb.append("AND fecha_alta >=  ? ");
			}
			
			if(criteris.getEstado() !=null) sb.append("AND estado = ? ");
			
			sb.append(" order by nombre");

			
			ps = con.prepareStatement(sb.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
			
			if(criteris.getIdUniversidad()!=null) {ps.setInt(i, criteris.getIdUniversidad()); i++;}
			if(criteris.getNombre()!=null) {ps.setString(i, "%"+criteris.getNombre().toUpperCase()+"%"); i++;}
			if(criteris.getLocalidad()!=null) {ps.setString(i, "%"+criteris.getLocalidad().toUpperCase()+"%"); i++;}
			if(criteris.getCp()!=null) {ps.setInt(i, criteris.getCp()); i++;}
			
			if(criteris.getFechaInicio() != null && criteris.getFechaFin()!=null){
				ps.setDate(i, criteris.getFechaInicio()); i++;
				ps.setDate(i, criteris.getFechaFin()); i++;
			}else if(criteris.getFechaInicio() != null && criteris.getFechaFin()==null){
				ps.setDate(i, criteris.getFechaInicio()); i++;
			}
			if(criteris.getEstado()!=null) {ps.setInt(i, criteris.getEstado()); i++;}
			
			rs = ps.executeQuery();
			while (rs.next()) {
				CentroDocente centroDocente = getCentroDocente(rs);
				lstCentroDoc.add(centroDocente);
			}		
			return (lstCentroDoc.isEmpty() || lstCentroDoc.size() == 0) ? null:lstCentroDoc;
		}catch(Exception e){
			throw new Exception(e.getMessage());
		} finally {
			close(ps,rs);
		}
	}

	@Override
	public void update(CentroDocente objecte) throws Exception {
		PreparedStatement ps = null;
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE CENTRODOCENTE SET ");
			if(objecte.getNombre() !=null) sql.append(" nombre = ?,");
			sql = new StringBuffer(sql.substring(0,sql.length()-1) +" WHERE id_centro = ?");
			
			ps = con.prepareStatement(sql.toString());
			
			int i=1;
			if(objecte.getNombre()!=null) {ps.setString(i, objecte.getNombre()); i++;}
			if(objecte.getIdCentro()!=null) {ps.setInt(i, objecte.getIdCentro()); i++;}
			ps.executeUpdate();
			
        } catch (SQLException e) {
        	throw new Exception(e.getMessage());
        } finally {
        	close(ps);
        }		
	}

	@Override
	public void delete(CentroDocente criteris) throws Exception {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("update centrodocente set estado = ?, fecha_estado = ? where id_centro = ?");
			ps.setInt(1, Constantes.REGISTRO_INACTIVO);
			ps.setDate(2, new java.sql.Date(System.currentTimeMillis()));
			ps.setInt(3, criteris.getIdCentro());
			ps.executeUpdate();
		} catch (SQLException e) {
        	throw new Exception(e.getMessage());
        } finally {
        	close(ps);
        }		
	}
	
	private CentroDocente getCentroDocente(ResultSet rs) throws SQLException{
		CentroDocente centroDocente = new CentroDocente();
		centroDocente.setIdCentro(rs.getInt("id_centro"));
		centroDocente.setNombre(rs.getString("nombre"));
		centroDocente.setIdContacto(rs.getInt("id_contacto"));
		centroDocente.setIdUniversidad(rs.getInt("id_universidad"));
		centroDocente.setFechaAlta(rs.getDate("fecha_alta"));
		centroDocente.setEstado(rs.getInt("estado"));
		centroDocente.setFechaEstado(rs.getDate("fecha_estado"));
		centroDocente.setMotivoEstado(rs.getString("motivo_estado"));
		return centroDocente;
	}

}
