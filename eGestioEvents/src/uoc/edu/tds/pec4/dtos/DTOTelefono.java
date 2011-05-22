package uoc.edu.tds.pec4.dtos;

import java.io.Serializable;

import uoc.edu.tds.pec4.beans.Telefono;

public class DTOTelefono extends DTOContacto implements DTOInterface, Serializable{
	private static final long serialVersionUID = 1L;
	
	private Telefono telefono;
	
	public Telefono getTelefono() {
		return telefono;
	}
	public void setTelefono(Telefono telefono) {
		this.telefono = telefono;
	}
	
}
