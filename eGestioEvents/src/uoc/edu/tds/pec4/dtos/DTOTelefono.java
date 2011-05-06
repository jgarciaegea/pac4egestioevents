package uoc.edu.tds.pec4.dtos;

import java.io.Serializable;

import uoc.edu.tds.pec4.beans.Telefono;

public class DTOTelefono implements DTOInterface, Serializable{
	private static final long serialVersionUID = 1L;
	
	private Telefono telefono;
	private DTOTipoTelefono dtoTipoTelefono;
	private DTOContacto dtoContacto;
	
	public Telefono getTelefono() {
		return telefono;
	}
	public void setTelefono(Telefono telefono) {
		this.telefono = telefono;
	}
	public DTOTipoTelefono getDtoTipoTelefono() {
		return dtoTipoTelefono;
	}
	public void setDtoTipoTelefono(DTOTipoTelefono dtoTipoTelefono) {
		this.dtoTipoTelefono = dtoTipoTelefono;
	}
	public DTOContacto getDtoContacto() {
		return dtoContacto;
	}
	public void setDtoContacto(DTOContacto dtoContacto) {
		this.dtoContacto = dtoContacto;
	}
	
}
