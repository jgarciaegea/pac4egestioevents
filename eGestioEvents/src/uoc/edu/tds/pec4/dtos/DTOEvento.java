package uoc.edu.tds.pec4.dtos;

import java.io.Serializable;

import uoc.edu.tds.pec4.beans.Evento;

public class DTOEvento implements DTOInterface, Serializable{
	private static final long serialVersionUID = 1L;
	
	private Evento evento;
	private DTOCentroDocente dtoCentroDocente;
	private DTOTipoEvento dtoTipoEvento;
	
	public Evento getEvento() {
		return evento;
	}
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	public DTOCentroDocente getDtoCentroDocente() {
		return dtoCentroDocente;
	}
	public void setDtoCentroDocente(DTOCentroDocente dtoCentroDocente) {
		this.dtoCentroDocente = dtoCentroDocente;
	}
	public DTOTipoEvento getDtoTipoEvento() {
		return dtoTipoEvento;
	}
	public void setDtoTipoEvento(DTOTipoEvento dtoTipoEvento) {
		this.dtoTipoEvento = dtoTipoEvento;
	}

}
