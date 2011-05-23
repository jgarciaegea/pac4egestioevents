package uoc.edu.tds.pec4.beans;

import java.io.Serializable;
import java.sql.Date;

public class CentroDocenteViewConsulta extends CentroDocente implements BeanInterface,Serializable{

	private static final long serialVersionUID = 1L;
	private Integer cp;
	private String localidad;
	private Date fechaInicio;
	private Date fechaFin;
	
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
	public Integer getCp() {
		return cp;
	}
	public void setCp(Integer cp) {
		this.cp = cp;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

}
