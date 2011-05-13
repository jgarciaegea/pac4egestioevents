/**
 * 
 */
package uoc.edu.tds.pec4.dtos;

/**
 * @author jgarcia
 *
 */

import java.io.Serializable;
import uoc.edu.tds.pec4.beans.EventoRequisitos;

public class DTOEventoRequisitos implements DTOInterface, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private EventoRequisitos eventoRequisitos;
	private DTOEvento dtoEvento;
	private DTOEvento dtoEventoReq;

	/**
	 * @return the eventoRequisitos
	 */
	public EventoRequisitos getEventoRequisitos() {
		return eventoRequisitos;
	}

	/**
	 * @param eventoRequisitos the eventoRequisitos to set
	 */
	public void setEventoRequisitos(EventoRequisitos eventoRequisitos) {
		this.eventoRequisitos = eventoRequisitos;
	}

	/**
	 * @return the dtoEvento
	 */
	public DTOEvento getDtoEvento() {
		return dtoEvento;
	}

	/**
	 * @param dtoEvento the dtoEvento to set
	 */
	public void setDtoEvento(DTOEvento dtoEvento) {
		this.dtoEvento = dtoEvento;
	}

	/**
	 * @return the dtoEventoReq
	 */
	public DTOEvento getDtoEventoReq() {
		return dtoEventoReq;
	}

	/**
	 * @param dtoEventoReq the dtoEventoReq to set
	 */
	public void setDtoEventoReq(DTOEvento dtoEventoReq) {
		this.dtoEventoReq = dtoEventoReq;
	}
	
}
