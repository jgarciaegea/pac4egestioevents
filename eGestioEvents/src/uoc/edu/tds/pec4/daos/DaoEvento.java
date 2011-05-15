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
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("INSERT INTO EVENTO (id_evento, id_centro, nombre, descripcion, fecha_inicio_celebracion, fecha_alta," + 
                    "estado, fecha_estado, motivo_estado, id_tipo_evento, plazas, umbral, fecha_inicio_inscripcion," +
                    "fecha_fin_inscripcion, duracion, precio, fecha_fin_celebracion)" + 
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setInt(1, objecte.getIdEvento());
			ps.setInt(2, objecte.getIdCentro());
			ps.setString(3, objecte.getNombre());
			ps.setString(4, objecte.getDescripcion());
			ps.setDate(5, objecte.getFechaInicioCelebracion());
			//ps.setDate(6, objecte.getFechaAlta());
			ps.setDate(6, getDateSql(new java.util.Date()));
			//ps.setInt(7, objecte.getEstado());
			ps.setInt(7, ESTADO_ACTIVO);
			ps.setDate(8, objecte.getFechaEstado());
			ps.setString(9, objecte.getMotivoEstado());
			ps.setInt(10, objecte.getIdTipoEvento());
			ps.setInt(11, objecte.getPlazas());
			ps.setInt(12, objecte.getUmbral());
			ps.setDate(13, objecte.getFechaInicioInscripcion());
			ps.setDate(14, objecte.getFechaFinInscripcion());
			ps.setInt(15, objecte.getDuracion());
			ps.setInt(16, objecte.getPrecio());
			ps.setDate(14, objecte.getFechaFinCelebracion());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		} finally {
			close(ps);
		}
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
		PreparedStatement ps = null;
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE TIPOEVENTO SET ");
			if(objecte.getIdCentro()!=null) sql.append(" id_centro = ?,");
			if(objecte.getNombre()!=null) sql.append(" nombre = ?,");
			if(objecte.getDescripcion()!=null) sql.append(" descripcion = ?,");
			if(objecte.getFechaInicioCelebracion()!=null) sql.append(" fecha_inicio_celebracion = ?,");
			if(objecte.getFechaAlta()!=null) sql.append(" fecha_alta = ?,");
			if(objecte.getEstado()!=null) sql.append(" estado = ?,");
			if(objecte.getFechaEstado()!=null) sql.append(" fecha_estado = ?,");
			if(objecte.getMotivoEstado()!=null) sql.append(" motivo_estado = ?,");
			if(objecte.getIdTipoEvento()!=null) sql.append(" id_tipo_evento = ?,");
			if(objecte.getPlazas()!=null) sql.append(" plazas = ?,");
			if(objecte.getUmbral()!=null) sql.append(" umbral = ?,");
			if(objecte.getFechaInicioInscripcion()!=null) sql.append(" fecha_inicio_inscripcion = ?,");
			if(objecte.getFechaFinInscripcion()!=null) sql.append(" fecha_fin_inscripcion = ?,");
			if(objecte.getDuracion()!=null) sql.append(" duracion = ?,");
			if(objecte.getPrecio()!=null) sql.append(" precio = ?,");
			if(objecte.getFechaFinCelebracion()!=null) sql.append(" fecha_fin_celebracion = ?,");
			sql = new StringBuffer(sql.substring(0,sql.length()-1) +" WHERE id_evento = ?");
			
			ps = con.prepareStatement(sql.toString());
			int i=1;
			if(objecte.getIdCentro()!=null) {ps.setInt(i, objecte.getIdCentro()); i++;}
			if(objecte.getNombre()!=null) {ps.setString(i, objecte.getNombre()); i++;}
			if(objecte.getDescripcion()!=null) {ps.setString(i, objecte.getDescripcion()); i++;}
			if(objecte.getFechaInicioCelebracion()!=null) {ps.setDate(i, objecte.getFechaInicioCelebracion()); i++;}
			if(objecte.getFechaAlta()!=null) {ps.setDate(i, objecte.getFechaAlta()); i++;}
			if(objecte.getEstado()!=null) {ps.setInt(i, objecte.getEstado()); i++;}
			if(objecte.getFechaEstado()!=null) {ps.setDate(i, objecte.getFechaEstado()); i++;}
			if(objecte.getMotivoEstado()!=null) {ps.setString(i, objecte.getMotivoEstado()); i++;}
			if(objecte.getIdTipoEvento()!=null) {ps.setInt(i, objecte.getIdTipoEvento()); i++;}
			if(objecte.getPlazas()!=null) {ps.setInt(i, objecte.getPlazas()); i++;}
			if(objecte.getUmbral()!=null) {ps.setInt(i, objecte.getUmbral()); i++;}
			if(objecte.getFechaInicioInscripcion()!=null) {ps.setDate(i, objecte.getFechaInicioInscripcion()); i++;}
			if(objecte.getFechaFinInscripcion()!=null) {ps.setDate(i, objecte.getFechaFinInscripcion()); i++;}
			if(objecte.getDuracion()!=null) {ps.setInt(i, objecte.getDuracion()); i++;}
			if(objecte.getPrecio()!=null) {ps.setInt(i, objecte.getPrecio()); i++;}
			if(objecte.getFechaFinCelebracion()!=null) {ps.setDate(i, objecte.getFechaFinCelebracion()); i++;}
			ps.setInt(i, objecte.getIdEvento());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		} finally {
			close(ps);
		}
	}

	@Override
	public void delete(Evento criteris) throws Exception {
		Evento evento = new Evento();
		evento.setIdEvento(criteris.getIdEvento());
		evento.setFechaEstado(getDateSql(new java.util.Date()));
		evento.setEstado(ESTADO_BAJA);
		evento.setMotivoEstado(criteris.getMotivoEstado());
		this.update(evento);
	}

}