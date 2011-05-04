/**

 * @author jgarcia

 *

 */

package uoc.edu.tds.pec4.beans;



import java.sql.Date;



public class Evento {

	private Integer id_evento;

	private Integer id_centro;

	private TipoEvento tipoEvento;

	private Integer estado;

	private Integer plazas;

	private Integer umbral;

	private Integer duracion;

	private Integer precio;

	private String nombre;

	private String descripcion;

	private String motivo_estado;

	private Date fecha_celebracion;

	private Date fecha_alta;

	private Date fecha_estado;

	private Date fecha_inicio_inscripcion;

	private Date fecha_fin_inscripcion;

	//Constructor por defecto 

	/**

	 * @param id_evento

	 * @param id_centro

	 * @param tipoEvento

	 * @param estado

	 * @param plazas

	 * @param umbral

	 * @param duracion

	 * @param precio

	 * @param nombre

	 * @param descripcion

	 * @param motivo_estado

	 * @param fecha_celebracion

	 * @param fecha_alta

	 * @param fecha_estado

	 * @param fecha_inicio_inscripcion

	 * @param fecha_fin_inscripcion

	 */

	public Evento(Integer id_evento, Integer id_centro, TipoEvento tipoEvento,

			Integer estado, Integer plazas, Integer umbral, Integer duracion,

			Integer precio, String nombre, String descripcion,

			String motivo_estado, Date fecha_celebracion, Date fecha_alta,

			Date fecha_estado, Date fecha_inicio_inscripcion,

			Date fecha_fin_inscripcion) {

		super();

		this.id_evento = id_evento;

		this.id_centro = id_centro;

		this.tipoEvento = tipoEvento;

		this.estado = estado;

		this.plazas = plazas;

		this.umbral = umbral;

		this.duracion = duracion;

		this.precio = precio;

		this.nombre = nombre;

		this.descripcion = descripcion;

		this.motivo_estado = motivo_estado;

		this.fecha_celebracion = fecha_celebracion;

		this.fecha_alta = fecha_alta;

		this.fecha_estado = fecha_estado;

		this.fecha_inicio_inscripcion = fecha_inicio_inscripcion;

		this.fecha_fin_inscripcion = fecha_fin_inscripcion;

	}

	/**

	 * @return the id_evento

	 */

	public Integer getId_evento() {

		return id_evento;

	}

	/**

	 * @param id_evento the id_evento to set

	 */

	public void setId_evento(Integer id_evento) {

		this.id_evento = id_evento;

	}

	/**

	 * @return the id_centro

	 */

	public Integer getId_centro() {

		return id_centro;

	}

	/**

	 * @param id_centro the id_centro to set

	 */

	public void setId_centro(Integer id_centro) {

		this.id_centro = id_centro;

	}

	/**

	 * @return the estado

	 */

	public Integer getEstado() {

		return estado;

	}

	/**

	 * @param estado the estado to set

	 */

	public void setEstado(Integer estado) {

		this.estado = estado;

	}

	/**

	 * @return the plazas

	 */

	public Integer getPlazas() {

		return plazas;

	}

	/**

	 * @param plazas the plazas to set

	 */

	public void setPlazas(Integer plazas) {

		this.plazas = plazas;

	}

	/**

	 * @return the umbral

	 */

	public Integer getUmbral() {

		return umbral;

	}

	/**

	 * @param umbral the umbral to set

	 */

	public void setUmbral(Integer umbral) {

		this.umbral = umbral;

	}

	/**

	 * @return the duracion

	 */

	public Integer getDuracion() {

		return duracion;

	}

	/**

	 * @param duracion the duracion to set

	 */

	public void setDuracion(Integer duracion) {

		this.duracion = duracion;

	}

	/**

	 * @return the precio

	 */

	public Integer getPrecio() {

		return precio;

	}

	/**

	 * @param precio the precio to set

	 */

	public void setPrecio(Integer precio) {

		this.precio = precio;

	}

	/**

	 * @return the nombre

	 */

	public String getNombre() {

		return nombre;

	}

	/**

	 * @param nombre the nombre to set

	 */

	public void setNombre(String nombre) {

		this.nombre = nombre;

	}

	/**

	 * @return the descripcion

	 */

	public String getDescripcion() {

		return descripcion;

	}

	/**

	 * @param descripcion the descripcion to set

	 */

	public void setDescripcion(String descripcion) {

		this.descripcion = descripcion;

	}

	/**

	 * @return the motivo_estado

	 */

	public String getMotivo_estado() {

		return motivo_estado;

	}

	/**

	 * @param motivo_estado the motivo_estado to set

	 */

	public void setMotivo_estado(String motivo_estado) {

		this.motivo_estado = motivo_estado;

	}

	/**

	 * @return the fecha_celebracion

	 */

	public Date getFecha_celebracion() {

		return fecha_celebracion;

	}

	/**

	 * @param fecha_celebracion the fecha_celebracion to set

	 */

	public void setFecha_celebracion(Date fecha_celebracion) {

		this.fecha_celebracion = fecha_celebracion;

	}

	/**

	 * @return the fecha_alta

	 */

	public Date getFecha_alta() {

		return fecha_alta;

	}

	/**

	 * @param fecha_alta the fecha_alta to set

	 */

	public void setFecha_alta(Date fecha_alta) {

		this.fecha_alta = fecha_alta;

	}

	/**

	 * @return the fecha_estado

	 */

	public Date getFecha_estado() {

		return fecha_estado;

	}

	/**

	 * @param fecha_estado the fecha_estado to set

	 */

	public void setFecha_estado(Date fecha_estado) {

		this.fecha_estado = fecha_estado;

	}

	/**

	 * @return the fecha_inicio_inscripcion

	 */

	public Date getFecha_inicio_inscripcion() {

		return fecha_inicio_inscripcion;

	}

	/**

	 * @param fecha_inicio_inscripcion the fecha_inicio_inscripcion to set

	 */

	public void setFecha_inicio_inscripcion(Date fecha_inicio_inscripcion) {

		this.fecha_inicio_inscripcion = fecha_inicio_inscripcion;

	}

	/**

	 * @return the fecha_fin_inscripcion

	 */

	public Date getFecha_fin_inscripcion() {

		return fecha_fin_inscripcion;

	}

	/**

	 * @param fecha_fin_inscripcion the fecha_fin_inscripcion to set

	 */

	public void setFecha_fin_inscripcion(Date fecha_fin_inscripcion) {

		this.fecha_fin_inscripcion = fecha_fin_inscripcion;

	}

	/**

	 * @return the tipoEvento

	 */

	public TipoEvento getTipoEvento() {

		return tipoEvento;

	}

	/**

	 * @param tipoEvento the tipoEvento to set

	 */

	public void setTipoEvento(TipoEvento tipoEvento) {

		this.tipoEvento = tipoEvento;

	}

}

