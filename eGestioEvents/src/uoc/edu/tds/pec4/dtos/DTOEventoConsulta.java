/**
 * 
 */
package uoc.edu.tds.pec4.dtos;

import java.io.Serializable;

import uoc.edu.tds.pec4.beans.EventoViewConsulta;


/**
 * @author ML019882
 *
 */
public class DTOEventoConsulta  extends DTOEvento implements DTOInterface, Serializable{

	private static final long serialVersionUID = 1L;
	private EventoViewConsulta eventoViewConsulta;
	
	/**
	 * @return the eventoViewConsulta
	 */
	public EventoViewConsulta getEventoViewConsulta() {
		return eventoViewConsulta;
	}
	/**
	 * @param eventoViewConsulta the eventoViewConsulta to set
	 */
	public void setEventoViewConsulta(EventoViewConsulta eventoViewConsulta) {
		this.eventoViewConsulta = eventoViewConsulta;
	}
	
}
