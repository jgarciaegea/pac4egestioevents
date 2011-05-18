package uoc.edu.tds.pec4.beans;

import java.io.Serializable;
import java.sql.Date;

public class UsuarioViewConsulta extends Usuario implements BeanInterface,Serializable{
	
	private static final long serialVersionUID = 1L;
	private String numeroDocumento;
	private String localidad;
	private Date fechaInicio;
	private Date fechaFin;
	
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	
}
