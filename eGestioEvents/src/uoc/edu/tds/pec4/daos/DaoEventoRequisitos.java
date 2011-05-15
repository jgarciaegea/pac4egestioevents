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
import uoc.edu.tds.pec4.beans.EventoRequisitos;

public class DaoEventoRequisitos extends DaoEntidad<EventoRequisitos>{

	public DaoEventoRequisitos(Connection con) {
		super(con);
	}

	@Override
	public void insert(EventoRequisitos objecte) throws Exception {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("INSERT INTO EVENTOREQUISITOS (id_evento, id_evento_req) VALUES (?, ?)");
			ps.setInt(1, objecte.getIdEvento());
			ps.setInt(2, objecte.getIdEventoReq());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		} finally {
			close(ps);
		}
	}

	@Override
	public List<EventoRequisitos> select(EventoRequisitos criteris) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<EventoRequisitos> lstEventoRequisitos = new ArrayList<EventoRequisitos>();
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT id_evento, id_evento_req ");
			sb.append("FROM EVENTOREQUISITOS ");
			sb.append("WHERE (1=1) ");
			if(criteris.getIdEvento()!=null) sb.append("AND id_evento = ? ");
			if(criteris.getIdEventoReq()!=null) sb.append("AND id_evento_req = ? ");
			
			ps = con.prepareStatement(sb.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
			if(criteris.getIdEvento()!=null) {ps.setInt(i, criteris.getIdEvento()); i++;}
			if(criteris.getIdEventoReq()!=null) {ps.setInt(i, criteris.getIdEventoReq()); i++;}
			
			rs = ps.executeQuery();
			while (rs.next()) {
				EventoRequisitos eventoRequisitos = new EventoRequisitos();
				eventoRequisitos.setIdEvento(rs.getInt("id_evento"));
				eventoRequisitos.setIdEventoReq(rs.getInt("id_evento_req"));
				lstEventoRequisitos.add(eventoRequisitos);
			}		
			return (lstEventoRequisitos.isEmpty() || lstEventoRequisitos.size() == 0) ? null:lstEventoRequisitos;
		}catch(Exception e){
			throw new Exception(e.getMessage());
		} finally {
			close(ps,rs);
		}
	}

	@Override
	public void update(EventoRequisitos objecte) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}

	@Override
	public void delete(EventoRequisitos criteris) throws Exception {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("DELETE FROM EVENTOREQUISITOS WHERE id_evento = ?");
			ps.setObject(1, criteris.getIdEvento());
			ps.executeUpdate();
		} catch (Exception e) {
			throw new Exception();
		} finally {
			close(ps);
		}
	}

}