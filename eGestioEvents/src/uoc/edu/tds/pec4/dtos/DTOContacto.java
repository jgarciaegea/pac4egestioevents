package uoc.edu.tds.pec4.dtos;

import java.io.Serializable;

import uoc.edu.tds.pec4.beans.Contacto;

public class DTOContacto implements DTOInterface, Serializable{
	private static final long serialVersionUID = 1L;
	
	private Contacto contacto;
	private DTOPais dtoPais;
	private DTOTelefono dtoTelefono;
	
	public Contacto getContacto() {
		return contacto;
	}
	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
	}
	public DTOPais getDtoPais() {
		return dtoPais;
	}
	public void setDtoPais(DTOPais dtoPais) {
		this.dtoPais = dtoPais;
	}
	public DTOTelefono getDtoTelefono() {
		return dtoTelefono;
	}
	public void setDtoTelefono(DTOTelefono dtoTelefono) {
		this.dtoTelefono = dtoTelefono;
	}
	
}
