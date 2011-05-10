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
	
}
