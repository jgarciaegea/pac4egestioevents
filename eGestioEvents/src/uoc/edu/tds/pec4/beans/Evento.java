package uoc.edu.tds.pec4.beans;

import java.io.Serializable;
import java.sql.Date;

public class Evento implements BeanInterface, Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer idEvento;
	private Integer idCentro;
	private TipoEvento tipoEvento;
	private Integer estado;
	private Integer plazas;
	private Integer umbral;
	private Integer duracion;
	private Integer precio;
	private String nombre;
	private String descripcion;
	private String motivoEstado;
	private Date fechaCelebracion;
	private Date fechaAlta;
	private Date fechaEstado;
	private Date fechaInicioInscripcion;
	private Date fechaFinInscripcion;
	
	/**
	 * @return the idEvento
	 */
	public Integer getIdEvento() {
		return idEvento;
	}

	/**
	 * @param idEvento the idEvento to set
	 */
	public void setIdEvento(Integer idEvento) {
		this.idEvento = idEvento;
	}

	/**
	 * @return the idCentro
	 */
	public Integer getIdCentro() {
		return idCentro;
	}

	/**
	 * @param idCentro the idCentro to set
	 */
	public void setIdCentro(Integer idCentro) {
		this.idCentro = idCentro;
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
	 * @return the motivoEstado
	 */
	public String getMotivoEstado() {
		return motivoEstado;
	}

	/**
	 * @param motivoEstado the motivoEstado to set
	 */
	public void setMotivoEstado(String motivoEstado) {
		this.motivoEstado = motivoEstado;
	}

	/**
	 * @return the fechaCelebracion
	 */
	public Date getFechaCelebracion() {
		return fechaCelebracion;
	}

	/**
	 * @param fechaCelebracion the fechaCelebracion to set
	 */
	public void setFechaCelebracion(Date fechaCelebracion) {
		this.fechaCelebracion = fechaCelebracion;
	}

	/**
	 * @return the fechaAlta
	 */
	public Date getFechaAlta() {
		return fechaAlta;
	}

	/**
	 * @param fechaAlta the fechaAlta to set
	 */
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	/**
	 * @return the fechaEstado
	 */
	public Date getFechaEstado() {
		return fechaEstado;
	}

	/**
	 * @param fechaEstado the fechaEstado to set
	 */
	public void setFechaEstado(Date fechaEstado) {
		this.fechaEstado = fechaEstado;
	}

	/**
	 * @return the fechaInicioInscripcion
	 */
	public Date getFechaInicioInscripcion() {
		return fechaInicioInscripcion;
	}

	/**
	 * @param fechaInicioInscripcion the fechaInicioInscripcion to set
	 */
	public void setFechaInicioInscripcion(Date fechaInicioInscripcion) {
		this.fechaInicioInscripcion = fechaInicioInscripcion;
	}

	/**
	 * @return the fechaFinInscripcion
	 */
	public Date getFechaFinInscripcion() {
		return fechaFinInscripcion;
	}

	/**
	 * @param fechaFinInscripcion the fechaFinInscripcion to set
	 */
	public void setFechaFinInscripcion(Date fechaFinInscripcion) {
		this.fechaFinInscripcion = fechaFinInscripcion;
	}

	/**
	 * Consutructor con par‡metros
	 * @param idEvento
	 * @param idCentro
	 * @param tipoEvento
	 * @param estado
	 * @param plazas
	 * @param umbral
	 * @param duracion
	 * @param precio
	 * @param nombre
	 * @param descripcion
	 * @param motivoEstado
	 * @param fechaCelebracion
	 * @param fechaAlta
	 * @param fechaEstado
	 * @param fechaInicioInscripcion
	 * @param fechaFinInscripcion
	 */
	public Evento(Integer idEvento, Integer idCentro, TipoEvento tipoEvento,
			Integer estado, Integer plazas, Integer umbral, Integer duracion,
			Integer precio, String nombre, String descripcion,
			String motivoEstado, Date fechaCelebracion, Date fechaAlta,
			Date fechaEstado, Date fechaInicioInscripcion,
			Date fechaFinInscripcion) {
		super();
		this.idEvento = idEvento;
		this.idCentro = idCentro;
		this.tipoEvento = tipoEvento;
		this.estado = estado;
		this.plazas = plazas;
		this.umbral = umbral;
		this.duracion = duracion;
		this.precio = precio;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.motivoEstado = motivoEstado;
		this.fechaCelebracion = fechaCelebracion;
		this.fechaAlta = fechaAlta;
		this.fechaEstado = fechaEstado;
		this.fechaInicioInscripcion = fechaInicioInscripcion;
		this.fechaFinInscripcion = fechaFinInscripcion;
	}
	
	/**
	 * Constructor b‡sico
	 */
	public Evento() {
		super();
	}	
}
