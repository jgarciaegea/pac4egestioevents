package uoc.edu.tds.pec4.beans;

import java.io.Serializable;
import java.sql.Date;

public class Telefono extends Contacto implements BeanInterface, Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer idTelefono;
	private String prefijoPais;
	private String telefono;
	private Integer extension;
	private Date fechaAlta;
	private Integer estado;
	private Date fechaEstado;
	private String motivoEstado;
	private Integer idTipoTelefono;
	
	public Integer getIdTelefono() {
		return idTelefono;
	}
	public void setIdTelefono(Integer idTelefono) {
		this.idTelefono = idTelefono;
	}
	public String getPrefijoPais() {
		return prefijoPais;
	}
	public void setPrefijoPais(String prefijoPais) {
		this.prefijoPais = prefijoPais;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
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
	public Integer getIdTipoTelefono() {
		return idTipoTelefono;
	}
	public void setIdTipoTelefono(Integer idTipoTelefono) {
		this.idTipoTelefono = idTipoTelefono;
	}
	
	public Integer getExtension() {
		return extension;
	}
	public void setExtension(Integer extension) {
		this.extension = extension;
	}
	
	
}
