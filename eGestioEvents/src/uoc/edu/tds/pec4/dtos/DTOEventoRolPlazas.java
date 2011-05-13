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
	private DTOEvento dtoEvento;
	private DTOTipoRol dtoTipoRol;

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
	 * @return the dtoTipoRol
	 */
	public DTOTipoRol getDtoTipoRol() {
		return dtoTipoRol;
	}

	/**
	 * @param dtoTipoRol the dtoTipoRol to set
	 */
	public void setDtoTipoRol(DTOTipoRol dtoTipoRol) {
		this.dtoTipoRol = dtoTipoRol;
	}
	
}