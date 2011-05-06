package uoc.edu.tds.pec4.beans;

import java.io.Serializable;

public class TipoRol implements BeanInterface, Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer idRol;
	private String descripcion;
	
	/**
	 * @return the idRol
	 */
	public Integer getIdRol() {
		return idRol;
	}
	
	/**
	 * @param idRol the idRol to set
	 */
	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
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

}
