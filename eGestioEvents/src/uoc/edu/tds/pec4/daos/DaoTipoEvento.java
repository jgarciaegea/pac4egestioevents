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

import uoc.edu.tds.pec4.beans.TipoEvento;
import uoc.edu.tds.pec4.utils.Constantes;

public class DaoTipoEvento extends DaoEntidad<TipoEvento>{

	public DaoTipoEvento(Connection con) {
		super(con);
	}

	@Override
	public void insert(TipoEvento objecte) throws Exception {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("INSERT INTO TipoEvento (id_tipo_evento, descripcion, descripcion_ampliada, estado, fecha_estado, motivo_estado)  VALUES (?, ?, ?, ?, ?, ?)");
			ps.setInt(1, objecte.getIdTipoEvento());
			ps.setString(2, objecte.getDescripcion());
			ps.setString(3, objecte.getDescripcionAmpliada());
			ps.setInt(4, Constantes.REGISTRO_ACTIVO);
			ps.setDate(5, new java.sql.Date(System.currentTimeMillis()));
			ps.setString(6, Constantes.REGISTRO_ACTIVO_MOTIVO);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		} finally {
			close(ps);
		}
	}

	@Override
	public List<TipoEvento> select(TipoEvento criteris) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<TipoEvento> lstTipoEvento = new ArrayList<TipoEvento>();
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT id_tipo_evento ");
			sb.append("FROM TIPOEVENTO ");
			sb.append("WHERE (1=1) ");
			if(criteris.getIdTipoEvento()!=null) sb.append("AND id_tipo_evento = ? ");
			if(criteris.getDescripcion()!=null) sb.append("AND descripcion = ? ");
			if(criteris.getDescripcionAmpliada()!=null) sb.append("AND descripcion_ampliada = ? ");
			if(criteris.getEstado()!=null) sb.append("AND estado = ? ");
			if(criteris.getFechaEstado()!=null) sb.append("AND fecha_estado = ? ");
			if(criteris.getMotivoEstado()!=null) sb.append("AND motivo_estado = ? ");
			
			ps = con.prepareStatement(sb.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
			if(criteris.getIdTipoEvento()!=null) {ps.setInt(i, criteris.getIdTipoEvento()); i++;}
			if(criteris.getDescripcion()!=null) {ps.setString(i, criteris.getDescripcion()); i++;}
			if(criteris.getDescripcionAmpliada()!=null) {ps.setString(i, criteris.getDescripcionAmpliada()); i++;}
			if(criteris.getEstado()!=null) {ps.setInt(i, criteris.getEstado()); i++;}
			if(criteris.getFechaEstado()!=null) {ps.setDate(i, criteris.getFechaEstado()); i++;}
			if(criteris.getMotivoEstado()!=null) {ps.setString(i, criteris.getMotivoEstado()); i++;}
			
			rs = ps.executeQuery();
			while (rs.next()) {
				TipoEvento tipoEvento = new TipoEvento();
				tipoEvento.setIdTipoEvento(rs.getInt("id_tipo_evento"));
				tipoEvento.setDescripcion(rs.getString("descripcion"));
				tipoEvento.setDescripcionAmpliada(rs.getString("descripcion_ampliada"));
				tipoEvento.setEstado(rs.getInt("estado"));
				tipoEvento.setFechaEstado(rs.getDate("fecha_estado"));
				tipoEvento.setMotivoEstado(rs.getString("motivo_estado"));
				lstTipoEvento.add(tipoEvento);
			}		
			return (lstTipoEvento.isEmpty() || lstTipoEvento.size() == 0) ? null:lstTipoEvento;
		}catch(Exception e){
			throw new Exception(e.getMessage());
		} finally {
			close(ps,rs);
		}
	}

	@Override
	public void update(TipoEvento objecte) throws Exception {
		
		PreparedStatement ps = null;
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE TIPOEVENTO SET ");
			if(objecte.getDescripcion() !=null) sql.append(" descripcion = ?,");
			if(objecte.getDescripcionAmpliada() !=null) sql.append(" descripcion_ampliada = ?,");
			if(objecte.getEstado() !=null) sql.append(" estado = ?,");
			if(objecte.getFechaEstado() !=null) sql.append(" fecha_estado = ?,");
			if(objecte.getMotivoEstado() !=null) sql.append(" motivo_estado = ?,");
			sql = new StringBuffer(sql.substring(0,sql.length()-1) +" WHERE id_tipo_evento = ?");
			
			ps = con.prepareStatement(sql.toString());
			
			int i=1;
			if(objecte.getDescripcion()!=null) {ps.setString(i, objecte.getDescripcion()); i++;}
			if(objecte.getDescripcionAmpliada()!=null) {ps.setString(i, objecte.getDescripcionAmpliada()); i++;}
			if(objecte.getEstado()!=null) {ps.setInt(i, objecte.getEstado()); i++;}
			if(objecte.getFechaEstado()!=null) {ps.setDate(i, objecte.getFechaEstado()); i++;}
			if(objecte.getMotivoEstado()!=null) {ps.setString(i, objecte.getMotivoEstado()); i++;}
			if(objecte.getIdTipoEvento()!=null) {ps.setInt(i, objecte.getIdTipoEvento()); i++;}
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		} finally {
			close(ps);
		}
	}

	@Override
	public void delete(TipoEvento criteris) throws Exception {
		TipoEvento tipoEvento = new TipoEvento();
		tipoEvento.setIdTipoEvento(criteris.getIdTipoEvento());
		tipoEvento.setFechaEstado(new java.sql.Date(System.currentTimeMillis()));
		tipoEvento.setEstado(Constantes.REGISTRO_INACTIVO);
		tipoEvento.setMotivoEstado(Constantes.REGISTRO_INACTIVO_MOTIVO);
		this.update(tipoEvento);
	}

}