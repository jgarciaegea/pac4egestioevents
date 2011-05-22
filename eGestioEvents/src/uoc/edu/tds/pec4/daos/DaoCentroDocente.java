package uoc.edu.tds.pec4.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uoc.edu.tds.pec4.beans.CentroDocente;
import uoc.edu.tds.pec4.utils.Constantes;

public class DaoCentroDocente extends DaoEntidad<CentroDocente>{

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
			sb.append("SELECT id_centro, nombre, id_contacto, id_universidad, fecha_alta, estado, fecha_estado, motivo_estado ");
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
				CentroDocente centroDocente = new CentroDocente();
				centroDocente.setIdCentro(rs.getInt("id_centro"));
				centroDocente.setNombre(rs.getString("nombre"));
				centroDocente.setIdContacto(rs.getInt("id_contacto"));
				centroDocente.setIdUniversidad(rs.getInt("id_universidad"));
				centroDocente.setFechaAlta(rs.getDate("fecha_alta"));
				centroDocente.setEstado(rs.getInt("estado"));
				centroDocente.setFechaEstado(rs.getDate("fecha_estado"));
				centroDocente.setMotivoEstado(rs.getString("motivo_estado"));
				lstCentroDocente.add(centroDocente);
			}		
			return (lstCentroDocente.isEmpty() || lstCentroDocente.size() == 0) ? null:lstCentroDocente;
		}catch(Exception e){
			throw new Exception(e.getMessage());
		} finally {
			close(ps,rs);
		}
	}

	@Override
	public void update(CentroDocente objecte) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}

	@Override
	public void delete(CentroDocente criteris) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}

}
