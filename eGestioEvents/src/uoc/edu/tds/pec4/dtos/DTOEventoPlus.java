/**
 * 
 */
package uoc.edu.tds.pec4.dtos;

import java.io.Serializable;

import uoc.edu.tds.pec4.beans.EventoPlus;

/**
 * @author jgarcia
 *
 */
public class DTOEventoPlus extends DTOEvento implements DTOInterface, Serializable{

	private static final long serialVersionUID = 1L;
	private EventoPlus eventoPlus;
	
	/**
	 * @return the eventoPlus
	 */
	public EventoPlus getEventoPlus() {
		return eventoPlus;
	}
	/**
	 * @param eventoPlus the eventoPlus to set
	 */
	public void setEventoPlus(EventoPlus eventoPlus) {
		this.eventoPlus = eventoPlus;
	}
}
