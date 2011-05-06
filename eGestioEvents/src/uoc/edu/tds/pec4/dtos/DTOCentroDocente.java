package uoc.edu.tds.pec4.dtos;

import java.io.Serializable;

import uoc.edu.tds.pec4.beans.CentroDocente;

public class DTOCentroDocente implements DTOInterface, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private CentroDocente centroDocente;
	private DTOContacto dtoContacto;
	private DTOUniversidad dtoUniversidad;
	
	public CentroDocente getCentroDocente() {
		return centroDocente;
	}
	public void setCentroDocente(CentroDocente centroDocente) {
		this.centroDocente = centroDocente;
	}
	public DTOContacto getDtoContacto() {
		return dtoContacto;
	}
	public void setDtoContacto(DTOContacto dtoContacto) {
		this.dtoContacto = dtoContacto;
	}
	public DTOUniversidad getDtoUniversidad() {
		return dtoUniversidad;
	}
	public void setDtoUniversidad(DTOUniversidad dtoUniversidad) {
		this.dtoUniversidad = dtoUniversidad;
	}

}
