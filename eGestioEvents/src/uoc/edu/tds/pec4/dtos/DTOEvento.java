package uoc.edu.tds.pec4.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import uoc.edu.tds.pec4.beans.Evento;

public class DTOEvento implements DTOInterface, Serializable{
	private static final long serialVersionUID = 1L;
	
	private Evento evento;
	private DTOCentroDocente dtoCentroDocente;
	private DTOTipoEvento dtoTipoEvento;
	private List<DTOEventoRequisitos> dtoEventoRequisitos = new ArrayList<DTOEventoRequisitos>();
	private List<DTOEventoRolPlazas> dtoEventoRolPlazas = new ArrayList<DTOEventoRolPlazas>();
	
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
	/**
	 * @return the dtoEventoRequisitos
	 */
	public List<DTOEventoRequisitos> getDtoEventoRequisitos() {
		return dtoEventoRequisitos;
	}
	/**
	 * @param dtoEventoRequisitos the dtoEventoRequisitos to set
	 */
	public void setDtoEventoRequisitos(List<DTOEventoRequisitos> dtoEventoRequisitos) {
		this.dtoEventoRequisitos = dtoEventoRequisitos;
	}
	/**
	 * @return the dtoEventoRolPlazas
	 */
	public List<DTOEventoRolPlazas> getDtoEventoRolPlazas() {
		return dtoEventoRolPlazas;
	}
	/**
	 * @param dtoEventoRolPlazas the dtoEventoRolPlazas to set
	 */
	public void setDtoEventoRolPlazas(List<DTOEventoRolPlazas> dtoEventoRolPlazas) {
		this.dtoEventoRolPlazas = dtoEventoRolPlazas;
	}

}
