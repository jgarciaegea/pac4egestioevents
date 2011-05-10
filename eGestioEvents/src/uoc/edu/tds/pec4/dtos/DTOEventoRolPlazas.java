/**
 * 
 */
package uoc.edu.tds.pec4.dtos;

/**
 * @author jgarcia
 *
 */
import java.io.Serializable;
import uoc.edu.tds.pec4.beans.EventoRolPlazas;

public class DTOEventoRolPlazas implements DTOInterface, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private EventoRolPlazas eventoRolPlazas;

	/**
	 * @return the eventoRolPlazas
	 */
	public EventoRolPlazas getEventoRolPlazas() {
		return eventoRolPlazas;
	}

	/**
	 * @param eventoRolPlazas the eventoRolPlazas to set
	 */
	public void setEventoRolPlazas(EventoRolPlazas eventoRolPlazas) {
		this.eventoRolPlazas = eventoRolPlazas;
	}
	
}