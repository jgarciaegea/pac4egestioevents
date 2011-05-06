package uoc.edu.tds.pec4.dtos;

import java.io.Serializable;

import uoc.edu.tds.pec4.beans.Inscripcion;

public class DTOInscripcion implements DTOInterface, Serializable{
	private static final long serialVersionUID = 1L;
	
	private Inscripcion inscripcion;
	private DTOEvento dtoEvento;
	
	public Inscripcion getInscripcion() {
		return inscripcion;
	}
	public void setInscripcion(Inscripcion inscripcion) {
		this.inscripcion = inscripcion;
	}
	public DTOEvento getDtoEvento() {
		return dtoEvento;
	}
	public void setDtoEvento(DTOEvento dtoEvento) {
		this.dtoEvento = dtoEvento;
	}
	

}
