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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import uoc.edu.tds.pec4.beans.Evento;
import uoc.edu.tds.pec4.beans.EventoCalendario;
import uoc.edu.tds.pec4.beans.EventoViewConsulta;
import uoc.edu.tds.pec4.utils.Constantes;

public class DaoEvento extends DaoEntidad<Evento>{
	
	
	private static final String CONSULTA_EVENTO = "i.codigo, e.id_centro,e.descripcion,e.id_tipo_evento,fecha_inicio_celebracion,e.fecha_fin_celebracion,e.estado,check_in,i.estado as estado_asistencia ";
	
	
	public DaoEvento(Connection con) {
		super(con);
	}

	@Override
	public void insert(Evento objecte) throws Exception {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("INSERT INTO EVENTO (id_centro, nombre, descripcion, fecha_inicio_celebracion, fecha_alta," + 
                    "estado, fecha_estado, motivo_estado, id_tipo_evento, plazas, umbral, fecha_inicio_inscripcion," +
                    "fecha_fin_inscripcion, duracion, precio, fecha_fin_celebracion) " + 
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setInt(1, objecte.getIdCentro());
			ps.setString(2, objecte.getNombre());
			ps.setString(3, objecte.getDescripcion());
			ps.setDate(4, objecte.getFechaInicioCelebracion());
			ps.setDate(5, new java.sql.Date(System.currentTimeMillis())); //getDateSql(new java.sql.Date(System.currentTimeMillis())));
			ps.setInt(6, Constantes.REGISTRO_ACTIVO);
			ps.setDate(7, new java.sql.Date(System.currentTimeMillis()));
			ps.setString(8, Constantes.REGISTRO_ACTIVO_MOTIVO);
			ps.setInt(9, objecte.getIdTipoEvento());
			ps.setInt(10, objecte.getPlazas());
			ps.setInt(11, objecte.getUmbral());
			ps.setDate(12, objecte.getFechaInicioInscripcion());
			ps.setDate(13, objecte.getFechaFinInscripcion());
			ps.setInt(14, objecte.getDuracion());
			ps.setInt(15, objecte.getPrecio());
			ps.setDate(16, objecte.getFechaFinCelebracion());
			System.out.println(ps.toString());
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
				Evento evento = retornaEvento(rs);				
				lstEvento.add(evento);
			}		
			return (lstEvento.isEmpty() || lstEvento.size() == 0) ? null:lstEvento;
		}catch(Exception e){
			throw new Exception(e.getMessage());
		} finally {
			close(ps,rs);
		}
	}

	private Evento retornaEvento(ResultSet rs) throws SQLException{
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
		return evento;
	}
	
	@Override
	public void update(Evento objecte) throws Exception {
		PreparedStatement ps = null;
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE EVENTO SET ");
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
		evento.setFechaEstado(new java.sql.Date(System.currentTimeMillis())); //getDateSql(new java.util.Date()));
		evento.setEstado(Constantes.REGISTRO_INACTIVO);
		evento.setMotivoEstado(Constantes.REGISTRO_INACTIVO_MOTIVO); //criteris.getMotivoEstado());
		this.update(evento);
	}
	
	public List<EventoViewConsulta> selectEventosUserByView(EventoViewConsulta criteris)throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<EventoViewConsulta> lstEventoViewConsulta = new ArrayList<EventoViewConsulta>();
		try{
			StringBuffer sb = new StringBuffer();
			sb.append(CONSULTA_EVENTO);
			sb.append("FROM v_consulta_eventos_usuario ");
			sb.append("WHERE (1=1) ");
			if(criteris.getCodigo() !=null) sb.append("AND codigo = ? ");
			if(criteris.getIdTipoEvento() !=null) sb.append("AND id_tipo_evento = ? ");

			
			if(criteris.getFechaInicioCelebracion() != null && criteris.getFechaFinCelebracion()!=null){
				sb.append("AND fecha_inicio_celebracion BETWEEN ? AND ?");
			}else if(criteris.getFechaInicioCelebracion() != null && criteris.getFechaFinCelebracion()==null){
				sb.append("AND e.fecha_fin_celebracion >=  ? ");
			}			
			sb.append(" order by fecha_inicio_celebracion");
			
			ps = con.prepareStatement(sb.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
			if(criteris.getCodigo()!=null) {ps.setString(i, "%"+criteris.getCodigo().toUpperCase()+"%"); i++;}
			if(criteris.getIdTipoEvento()!=null) {ps.setInt(i, criteris.getIdTipoEvento()); i++;}		
			if(criteris.getFechaInicioCelebracion() != null && criteris.getFechaFinCelebracion()!=null){
				ps.setDate(i, criteris.getFechaInicioCelebracion()); i++;
				ps.setDate(i, criteris.getFechaFinCelebracion()); i++;
			}else if(criteris.getFechaInicioCelebracion() != null && criteris.getFechaFinCelebracion()==null){
				ps.setDate(i, criteris.getFechaInicioCelebracion()); i++;
			}
			
			rs = ps.executeQuery();
			while (rs.next()) {
				EventoViewConsulta evento = retornaEventoUsuario(rs);
				lstEventoViewConsulta.add(evento);
			}		
			return (lstEventoViewConsulta.isEmpty() || lstEventoViewConsulta.size() == 0) ? null:lstEventoViewConsulta;
		}catch(Exception e){
			throw new Exception(e.getMessage());
		} finally {
			close(ps,rs);
		}
	}
	
	private EventoViewConsulta retornaEventoUsuario(ResultSet rs) throws SQLException{
		EventoViewConsulta evento = new EventoViewConsulta();
		evento.setCodigo(rs.getString("codigo"));
		evento.setIdCentro(rs.getInt("id_centro"));
		evento.setDescripcion(rs.getString("descripcion"));
		evento.setIdTipoEvento(rs.getInt("id_tipo_evento"));
		evento.setFechaInicioCelebracion(rs.getDate("fecha_inicio_celebracion"));
		evento.setFechaFinCelebracion(rs.getDate("fecha_fin_celebracion"));
		evento.setEstado(rs.getInt("estado"));
		evento.setEstadoAsistencia(rs.getString("estado_asistencia"));		
		return evento;
	}
	
	public List<EventoCalendario> selectEventosCalendario(EventoCalendario criteris)throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<EventoCalendario> lstEventoCalendario = new ArrayList<EventoCalendario>();
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT id_centro, id_evento, evento, fecha_inicio_celebracion, umbral, estado, eventoCancelado, eventoFinalizado ");
			sb.append("FROM v_consulta_eventos_calendario ");
			sb.append("WHERE (1=1) ");
			if(criteris.getIdCentro() !=null) sb.append("AND id_centro = ? ");
			if(criteris.getIdUniversidad() !=null) sb.append("AND id_universidad = ? ");		
			if(criteris.getFechaInicioCelebracion() != null) sb.append("AND fecha_inicio_celebracion >=  ? ");
			if(criteris.getFechaFinCelebracion() != null) sb.append("AND fecha_fin_celebracion <=  ? ");
			if(criteris.getEstado() != null) sb.append("AND estado =  ? ");
			if(criteris.getEventoFinalizado() != null) sb.append("AND eventoFinalizado =  ? ");
					
			sb.append(" order by fecha_inicio_celebracion, evento");
			
			ps = con.prepareStatement(sb.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
			if(criteris.getIdCentro()!=null) {ps.setInt(i, criteris.getIdCentro()); i++;}
			if(criteris.getIdUniversidad()!=null) {ps.setInt(i, criteris.getIdUniversidad()); i++;}
			if(criteris.getFechaInicioCelebracion() != null){ps.setDate(i, criteris.getFechaInicioCelebracion()); i++;}			
			if(criteris.getFechaFinCelebracion() != null){ps.setDate(i, criteris.getFechaFinCelebracion()); i++;}
			if(criteris.getEstado()!=null) {ps.setInt(i, criteris.getEstado()); i++;}
			if(criteris.getEventoFinalizado()!=null) {ps.setBoolean(i, criteris.getEventoFinalizado()); i++;}
			
			System.out.println(ps.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				EventoCalendario eventoCalendario = new EventoCalendario();
				eventoCalendario.setIdEvento(rs.getInt("id_evento"));
				eventoCalendario.setNombre(rs.getString("evento"));
				eventoCalendario.setFechaInicioCelebracion(rs.getDate("fecha_inicio_celebracion"));
				eventoCalendario.setUmbral(rs.getInt("umbral"));
				eventoCalendario.setIdCentro(rs.getInt("id_centro"));
				eventoCalendario.setEventoCancelado(rs.getBoolean("eventoCancelado"));
				eventoCalendario.setEventoFinalizado(rs.getBoolean("eventoFinalizado"));
				lstEventoCalendario.add(eventoCalendario);
			}		
			return (lstEventoCalendario.isEmpty() || lstEventoCalendario.size() == 0) ? null:lstEventoCalendario;
		}catch(Exception e){
			throw new Exception(e.getMessage());
		} finally {
			close(ps,rs);
		}
	}
	
	public List<Evento> selectEventosFinalizados(Evento criteris)throws Exception {
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
			sb.append("AND fecha_fin_celebracion < ? ");
					
			sb.append(" order by evento");
			
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
			ps.setDate(i, new java.sql.Date(System.currentTimeMillis())); i++;
			
			System.out.println(ps.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				Evento evento = retornaEvento(rs);				
				lstEvento.add(evento);
			}		
			return (lstEvento.isEmpty() || lstEvento.size() == 0) ? null:lstEvento;
		}catch(Exception e){
			throw new Exception(e.getMessage());
		} finally {
			close(ps,rs);
		}
	}

}