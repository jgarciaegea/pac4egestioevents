package uoc.edu.tds.pec4.beans;

import java.io.Serializable;

public class TipoTelefono implements BeanInterface, Serializable{

	private static final long serialVersionUID = 1L;
	private Integer idTipoTelefono;
	private String descripcion;
	
	public Integer getIdTipoTelefono() {
		return idTipoTelefono;
	}
	public void setIdTipoTelefono(Integer idTipoTelefono) {
		this.idTipoTelefono = idTipoTelefono;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
