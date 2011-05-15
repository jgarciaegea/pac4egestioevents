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
import uoc.edu.tds.pec4.beans.EventoRolPlazas;

public class DaoEventoRolPlazas extends DaoEntidad<EventoRolPlazas>{

	public DaoEventoRolPlazas(Connection con) {
		super(con);
	}

	@Override
	public void insert(EventoRolPlazas objecte) throws Exception {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("INSERT INTO EVENTOROLPLAZAS (id_evento, id_rol, plazas) VALUES (?, ?, ?)");
			ps.setInt(1, objecte.getIdEvento());
			ps.setInt(2, objecte.getIdRol());
			ps.setInt(3, objecte.getPlazas());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		} finally {
			close(ps);
		}
	}

	@Override
	public List<EventoRolPlazas> select(EventoRolPlazas criteris) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<EventoRolPlazas> lstEventoRolPlazas = new ArrayList<EventoRolPlazas>();
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT id_evento, id_rol, plazas ");
			sb.append("FROM EVENTOROLPLAZAS ");
			sb.append("WHERE (1=1) ");
			if(criteris.getIdEvento()!=null) sb.append("AND id_evento = ? ");
			if(criteris.getIdRol()!=null) sb.append("AND id_rol = ? ");
			if(criteris.getPlazas()!=null) sb.append("AND plazas = ? ");
			
			ps = con.prepareStatement(sb.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
			if(criteris.getIdEvento()!=null) {ps.setInt(i, criteris.getIdEvento()); i++;}
			if(criteris.getIdRol()!=null) {ps.setInt(i, criteris.getIdRol()); i++;}
			if(criteris.getPlazas()!=null) {ps.setInt(i, criteris.getPlazas()); i++;}
			
			rs = ps.executeQuery();
			while (rs.next()) {
				EventoRolPlazas eventoRolPlazas = new EventoRolPlazas();
				eventoRolPlazas.setIdEvento(rs.getInt("id_evento"));
				eventoRolPlazas.setIdRol(rs.getInt("id_rol"));
				eventoRolPlazas.setPlazas(rs.getInt("plazas"));
				lstEventoRolPlazas.add(eventoRolPlazas);
			}		
			return (lstEventoRolPlazas.isEmpty() || lstEventoRolPlazas.size() == 0) ? null:lstEventoRolPlazas;
		}catch(Exception e){
			throw new Exception(e.getMessage());
		} finally {
			close(ps,rs);
		}
	}

	@Override
	public void update(EventoRolPlazas objecte) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}

	@Override
	public void delete(EventoRolPlazas criteris) throws Exception {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("DELETE FROM EVENTOROLPLAZAS WHERE id_evento = ?");
			ps.setObject(1, criteris.getIdEvento());
			ps.executeUpdate();
		} catch (Exception e) {
			throw new Exception();
		} finally {
			close(ps);
		}
	}

}