package uoc.edu.tds.pec4.beans;

/**
 * @author jgarcia
 *
 */

import java.io.Serializable;
import java.sql.Date;

public class Evento implements BeanInterface, Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer idEvento;
	private Integer idCentro;
	private Integer estado;
	private Integer duracion;
	private Integer precio;
	private Integer idTipoEvento;
	private Integer plazas;
	private Integer umbral;
	private String nombre;
	private String descripcion;
	private String motivoEstado;
	private Date fechaInicioCelebracion;
	private Date fechaFinCelebracion;
	private Date fechaAlta;
	private Date fechaEstado;
	private Date fechaInicioInscripcion;
	private Date fechaFinInscripcion;
	
	public Integer getIdEvento() {
		return idEvento;
	}
	public void setIdEvento(Integer idEvento) {
		this.idEvento = idEvento;
	}
	public Integer getIdCentro() {
		return idCentro;
	}
	public void setIdCentro(Integer idCentro) {
		this.idCentro = idCentro;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	public Date getFechaEstado() {
		return fechaEstado;
	}
	public void setFechaEstado(Date fechaEstado) {
		this.fechaEstado = fechaEstado;
	}
	public String getMotivoEstado() {
		return motivoEstado;
	}
	public void setMotivoEstado(String motivoEstado) {
		this.motivoEstado = motivoEstado;
	}
	public Integer getIdTipoEvento() {
		return idTipoEvento;
	}
	public void setIdTipoEvento(Integer idTipoEvento) {
		this.idTipoEvento = idTipoEvento;
	}
	public Integer getPlazas() {
		return plazas;
	}
	public void setPlazas(Integer plazas) {
		this.plazas = plazas;
	}
	public Integer getUmbral() {
		return umbral;
	}
	public void setUmbral(Integer umbral) {
		this.umbral = umbral;
	}
	public Date getFechaInicioInscripcion() {
		return fechaInicioInscripcion;
	}
	public void setFechaInicioInscripcion(Date fechaInicioInscripcion) {
		this.fechaInicioInscripcion = fechaInicioInscripcion;
	}
	public Date getFechaFinInscripcion() {
		return fechaFinInscripcion;
	}
	public void setFechaFinInscripcion(Date fechaFinInscripcion) {
		this.fechaFinInscripcion = fechaFinInscripcion;
	}
	public Integer getDuracion() {
		return duracion;
	}
	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}
	public Integer getPrecio() {
		return precio;
	}
	public void setPrecio(Integer precio) {
		this.precio = precio;
	}
	/**
	 * @return the fechaInicioCelebracion
	 */
	public Date getFechaInicioCelebracion() {
		return fechaInicioCelebracion;
	}
	/**
	 * @param fechaInicioCelebracion the fechaInicioCelebracion to set
	 */
	public void setFechaInicioCelebracion(Date fechaInicioCelebracion) {
		this.fechaInicioCelebracion = fechaInicioCelebracion;
	}
	/**
	 * @return the fechaFinCelebracion
	 */
	public Date getFechaFinCelebracion() {
		return fechaFinCelebracion;
	}
	/**
	 * @param fechaFinCelebracion the fechaFinCelebracion to set
	 */
	public void setFechaFinCelebracion(Date fechaFinCelebracion) {
		this.fechaFinCelebracion = fechaFinCelebracion;
	}
}
