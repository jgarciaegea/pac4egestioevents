package uoc.edu.tds.pec4.beans;

import java.io.Serializable;

public class Pais implements BeanInterface, Serializable{

	private static final long serialVersionUID = 1L;	
	
	private Integer idPais;
	private String nombrePais;
	
	public Integer getIdPais() {
		return idPais;
	}
	public void setIdPais(Integer idPais) {
		this.idPais = idPais;
	}
	public String getNombrePais() {
		return nombrePais;
	}
	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}
	
	
	
}
