package uoc.edu.tds.pec4.beans;

import java.io.Serializable;
import java.sql.Date;

public class Inscripcion implements BeanInterface, Serializable{

	private static final long serialVersionUID = 1L;
	
	private String codigo;
	private Integer idEvento;
	private Integer estado;
	private Date fechaEstado;
	private String motivoEstado;
	private Date fechaInscripcion;
	private Boolean checkIn;
	private String codigoAsistencia;
	
	public Integer getIdEvento() {
		return idEvento;
	}
	public void setIdEvento(Integer idEvento) {
		this.idEvento = idEvento;
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
	public Date getFechaInscripcion() {
		return fechaInscripcion;
	}
	public void setFechaInscripcion(Date fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}
	public Boolean getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(Boolean checkIn) {
		this.checkIn = checkIn;
	}
	public String getCodigoAsistencia() {
		return codigoAsistencia;
	}
	public void setCodigoAsistencia(String codigoAsistencia) {
		this.codigoAsistencia = codigoAsistencia;
	}
	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	

}
