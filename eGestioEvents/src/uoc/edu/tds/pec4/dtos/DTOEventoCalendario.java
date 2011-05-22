/**
 * @author jgarcia
 *
 */
package uoc.edu.tds.pec4.dtos;

import java.io.Serializable;
import uoc.edu.tds.pec4.beans.EventoCalendario;

public class DTOEventoCalendario extends DTOEvento implements DTOInterface, Serializable{

	private static final long serialVersionUID = 1L;
	private EventoCalendario eventoCalendario;
	
	/**
	 * @return the eventoCalendario
	 */
	public EventoCalendario getEventoCalendario() {
		return eventoCalendario;
	}
	/**
	 * @param eventoCalendario the eventoCalendario to set
	 */
	public void setEventoCalendario(EventoCalendario eventoCalendario) {
		this.eventoCalendario = eventoCalendario;
	}
	
}
