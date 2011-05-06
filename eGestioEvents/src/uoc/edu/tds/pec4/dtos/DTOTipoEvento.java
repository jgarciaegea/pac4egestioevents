package uoc.edu.tds.pec4.dtos;

import java.io.Serializable;

import uoc.edu.tds.pec4.beans.TipoEvento;

public class DTOTipoEvento implements DTOInterface, Serializable{
	private static final long serialVersionUID = 1L;
	
	private TipoEvento tipoEvento;

	public TipoEvento getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(TipoEvento tipoEvento) {
		this.tipoEvento = tipoEvento;
	}
}
