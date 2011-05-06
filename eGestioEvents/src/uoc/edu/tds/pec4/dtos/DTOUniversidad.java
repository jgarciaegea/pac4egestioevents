package uoc.edu.tds.pec4.dtos;

import java.io.Serializable;

import uoc.edu.tds.pec4.beans.Universidad;

public class DTOUniversidad implements DTOInterface, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Universidad universidad;
	private DTOContacto dtoContacto;
	
	public Universidad getUniversidad() {
		return universidad;
	}
	public void setUniversidad(Universidad universidad) {
		this.universidad = universidad;
	}
	public DTOContacto getDtoContacto() {
		return dtoContacto;
	}
	public void setDtoContacto(DTOContacto dtoContacto) {
		this.dtoContacto = dtoContacto;
	}
}
