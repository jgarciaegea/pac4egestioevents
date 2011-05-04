package uoc.edu.tds.pec4.beans;

import java.io.Serializable;
import java.sql.Date;

public class TipoEvento implements BeanInterface, Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer idTipoEvento;
	private Integer estado;
	private String descripcion;
	private String descripcionAmpliada;
	private String motivoEstado;
	private Date fechaEstado;
	
	/**
	 * Constructor con par‡metros
	 * @param idTipoEvento
	 * @param estado
	 * @param descripcion
	 * @param descripcionAmpliada
	 * @param motivoEstado
	 * @param fechaEstado
	 */
	public TipoEvento(Integer idTipoEvento, Integer estado, String descripcion,
			String descripcionAmpliada, String motivoEstado, Date fechaEstado) {
		super();
		this.idTipoEvento = idTipoEvento;
		this.estado = estado;
		this.descripcion = descripcion;
		this.descripcionAmpliada = descripcionAmpliada;
		this.motivoEstado = motivoEstado;
		this.fechaEstado = fechaEstado;
	}

	/**
	 * Constructor b‡sico
	 */
	public TipoEvento() {
		super();
	}
	
	/**
	 * @return the idTipoEvento
	 */
	public Integer getIdTipoEvento() {
		return idTipoEvento;
	}

	/**
	 * @param idTipoEvento the idTipoEvento to set
	 */
	public void setIdTipoEvento(Integer idTipoEvento) {
		this.idTipoEvento = idTipoEvento;
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
	 * @return the descripcionAmpliada
	 */
	public String getDescripcionAmpliada() {
		return descripcionAmpliada;
	}

	/**
	 * @param descripcionAmpliada the descripcionAmpliada to set
	 */
	public void setDescripcionAmpliada(String descripcionAmpliada) {
		this.descripcionAmpliada = descripcionAmpliada;
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

}
