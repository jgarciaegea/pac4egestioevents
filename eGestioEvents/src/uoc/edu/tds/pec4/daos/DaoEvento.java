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
import uoc.edu.tds.pec4.beans.Evento;

public class DaoEvento extends DaoEntidad<Evento>{

	public DaoEvento(Connection con) {
		super(con);
	}

	@Override
	public void insert(Evento objecte) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}

	@Override
	public List<Evento> select(Evento criteris) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Evento> lstEvento = new ArrayList<Evento>();
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT id_evento, id_centro, nombre, descripcion, fecha_inicio_celebracion, fecha_alta, ");
			sb.append("  estado, fecha_estado, motivo_estado, id_tipo_evento, plazas, umbral, fecha_inicio_inscripcion, ");
			sb.append("  fecha_fin_inscripcion, duracion, precio, fecha_fin_celebracion ");
			sb.append("FROM EVENTO ");
			sb.append("WHERE (1=1) ");
			if(criteris.getIdEvento()!=null) sb.append("AND id_evento = ? ");
			if(criteris.getIdCentro()!=null) sb.append("AND id_centro = ? ");
			if(criteris.getNombre()!=null) sb.append("AND nombre = ? ");
			if(criteris.getDescripcion()!=null) sb.append("AND descripcion = ? ");
			if(criteris.getFechaInicioCelebracion()!=null) sb.append("AND fecha_inicio_celebracion = ? ");
			if(criteris.getFechaAlta()!=null) sb.append("AND fecha_alta = ? ");
			if(criteris.getEstado()!=null) sb.append("AND estado = ? ");
			if(criteris.getFechaEstado()!=null) sb.append("AND fecha_estado = ? ");
			if(criteris.getMotivoEstado()!=null) sb.append("AND motivo_estado = ? ");
			if(criteris.getIdTipoEvento()!=null) sb.append("AND id_tipo_evento = ? ");
			if(criteris.getPlazas()!=null) sb.append("AND plazas = ? ");
			if(criteris.getUmbral()!=null) sb.append("AND umbral = ? ");
			if(criteris.getFechaInicioInscripcion()!=null) sb.append("AND fecha_inicio_inscripcion = ? ");
			if(criteris.getFechaFinInscripcion()!=null) sb.append("AND fecha_fin_inscripcion = ? ");
			if(criteris.getDuracion()!=null) sb.append("AND duracion = ? ");
			if(criteris.getPrecio()!=null) sb.append("AND precio = ? ");
			if(criteris.getFechaFinCelebracion()!=null) sb.append("AND fecha_fin_celebracion = ? ");
						
			ps = con.prepareStatement(sb.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
			if(criteris.getIdEvento()!=null) {ps.setInt(i, criteris.getIdEvento()); i++;}
			if(criteris.getIdCentro()!=null) {ps.setInt(i, criteris.getIdCentro()); i++;}
			if(criteris.getNombre()!=null) {ps.setString(i, criteris.getNombre()); i++;}
			if(criteris.getDescripcion()!=null) {ps.setString(i, criteris.getDescripcion()); i++;}
			if(criteris.getFechaInicioCelebracion()!=null) {ps.setDate(i, criteris.getFechaInicioCelebracion()); i++;}
			if(criteris.getFechaAlta()!=null) {ps.setDate(i, criteris.getFechaAlta()); i++;}
			if(criteris.getEstado()!=null) {ps.setInt(i, criteris.getEstado()); i++;}
			if(criteris.getFechaEstado()!=null) {ps.setDate(i, criteris.getFechaEstado()); i++;}
			if(criteris.getMotivoEstado()!=null) {ps.setString(i, criteris.getMotivoEstado()); i++;}
			if(criteris.getIdTipoEvento()!=null) {ps.setInt(i, criteris.getIdTipoEvento()); i++;}
			if(criteris.getPlazas()!=null) {ps.setInt(i, criteris.getPlazas()); i++;}
			if(criteris.getUmbral()!=null) {ps.setInt(i, criteris.getUmbral()); i++;}
			if(criteris.getFechaInicioInscripcion()!=null) {ps.setDate(i, criteris.getFechaInicioInscripcion()); i++;}
			if(criteris.getFechaFinInscripcion()!=null) {ps.setDate(i, criteris.getFechaFinInscripcion()); i++;}
			if(criteris.getDuracion()!=null) {ps.setInt(i, criteris.getDuracion()); i++;}
			if(criteris.getPrecio()!=null) {ps.setInt(i, criteris.getPrecio()); i++;}
			if(criteris.getFechaFinCelebracion()!=null) {ps.setDate(i, criteris.getFechaFinCelebracion()); i++;}

			
			rs = ps.executeQuery();
			while (rs.next()) {
				Evento evento = new Evento();
				evento.setIdEvento(rs.getInt("id_evento"));
				evento.setIdCentro(rs.getInt("id_centro"));
				evento.setNombre(rs.getString("nombre"));
				evento.setDescripcion(rs.getString("descripcion"));
				evento.setFechaInicioCelebracion(rs.getDate("fecha_inicio_celebracion"));
				evento.setFechaAlta(rs.getDate("fecha_alta"));
				evento.setEstado(rs.getInt("estado"));
				evento.setFechaEstado(rs.getDate("fecha_estado"));
				evento.setMotivoEstado(rs.getString("motivo_estado"));
				evento.setIdTipoEvento(rs.getInt("id_tipo_evento"));
				evento.setPlazas(rs.getInt("plazas"));
				evento.setUmbral(rs.getInt("umbral"));
				evento.setFechaInicioInscripcion(rs.getDate("fecha_inicio_inscripcion"));
				evento.setFechaFinInscripcion(rs.getDate("fecha_fin_inscripcion"));
				evento.setDuracion(rs.getInt("duracion"));
				evento.setPrecio(rs.getInt("precio"));
				evento.setFechaFinCelebracion(rs.getDate("fecha_fin_celebracion"));
				lstEvento.add(evento);
			}		
			return (lstEvento.isEmpty() || lstEvento.size() == 0) ? null:lstEvento;
		}catch(Exception e){
			throw new Exception(e.getMessage());
		} finally {
			close(ps,rs);
		}
	}

	@Override
	public void update(Evento objecte) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}

	@Override
	public void delete(Evento criteris) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}

}