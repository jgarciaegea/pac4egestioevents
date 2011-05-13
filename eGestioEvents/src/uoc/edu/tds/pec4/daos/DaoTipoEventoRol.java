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
import uoc.edu.tds.pec4.beans.TipoEventoRol;

public class DaoTipoEventoRol extends DaoEntidad<TipoEventoRol>{

	public DaoTipoEventoRol(Connection con) {
		super(con);
	}

	@Override
	public void insert(TipoEventoRol objecte) throws Exception {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("INSERT INTO TIPOEVENTOROL (id_tipo_evento, id_rol)  VALUES (?, ?)");
			ps.setInt(1, objecte.getIdTipoEvento());
			ps.setInt(2, objecte.getIdRol());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		} finally {
			close(ps);
		}
	}

	@Override
	public List<TipoEventoRol> select(TipoEventoRol criteris) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<TipoEventoRol> lstTipoEventoRol = new ArrayList<TipoEventoRol>();
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT id_tipo_evento, id_rol ");
			sb.append("FROM TIPOEVENTOROL ");
			sb.append("WHERE (1=1) ");
			if(criteris.getIdTipoEvento()!=null) sb.append("AND id_tipo_evento = ? ");
			if(criteris.getIdRol()!=null) sb.append("AND id_rol = ? ");
			
			ps = con.prepareStatement(sb.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
			if(criteris.getIdTipoEvento()!=null) {ps.setInt(i, criteris.getIdTipoEvento()); i++;}
			if(criteris.getIdRol()!=null) {ps.setInt(i, criteris.getIdRol()); i++;}
			
			rs = ps.executeQuery();
			while (rs.next()) {
				TipoEventoRol tipoEventoRol = new TipoEventoRol();
				tipoEventoRol.setIdTipoEvento(rs.getInt("id_tipo_evento"));
				tipoEventoRol.setIdRol(rs.getInt("id_rol"));
				lstTipoEventoRol.add(tipoEventoRol);
			}		
			return (lstTipoEventoRol.isEmpty() || lstTipoEventoRol.size() == 0) ? null:lstTipoEventoRol;
		}catch(Exception e){
			throw new Exception(e.getMessage());
		} finally {
			close(ps,rs);
		}
	}

	@Override
	public void update(TipoEventoRol objecte) throws Exception {
		
		PreparedStatement ps = null;
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE TIPOEVENTOROL SET ");
			if(objecte.getIdRol() !=null) sql.append(" id_rol = ?,");
			sql = new StringBuffer(sql.substring(0,sql.length()-1) +" WHERE id_tipo_evento = ?");
			
			ps = con.prepareStatement(sql.toString());
			
			int i=1;
			if(objecte.getIdRol()!=null) {ps.setInt(i, objecte.getIdRol()); i++;}
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
	public void delete(TipoEventoRol criteris) throws Exception {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("DELETE FROM TIPOEVENTOROL WHERE id_tipo_evento = ?");
			ps.setObject(1, criteris.getIdTipoEvento());
			ps.executeUpdate();
		} catch (Exception e) {
			throw new Exception();
		} finally {
			close(ps);
		}
	}

}