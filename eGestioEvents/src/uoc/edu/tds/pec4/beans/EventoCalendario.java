/**
 * 
 */
package uoc.edu.tds.pec4.beans;

import java.io.Serializable;

/**
 * @author jgarcia
 *
 */
public class EventoCalendario extends Evento implements BeanInterface,Serializable{

	private static final long serialVersionUID = 1L;
	private Integer idUniversidad;
	private Boolean eventoCancelado;
	private Boolean eventoFinalizado;
	
	/**
	 * @return the idUniversidad
	 */
	public Integer getIdUniversidad() {
		return idUniversidad;
	}
	/**
	 * @param idUniversidad the idUniversidad to set
	 */
	public void setIdUniversidad(Integer idUniversidad) {
		this.idUniversidad = idUniversidad;
	}
	/**
	 * @return the eventoFinalizado
	 */
	public Boolean getEventoFinalizado() {
		return eventoFinalizado;
	}
	/**
	 * @param eventoFinalizado the eventoFinalizado to set
	 */
	public void setEventoFinalizado(Boolean eventoFinalizado) {
		this.eventoFinalizado = eventoFinalizado;
	}
	/**
	 * @return the eventoCancelado
	 */
	public Boolean getEventoCancelado() {
		return eventoCancelado;
	}
	/**
	 * @param eventoCancelado the eventoCancelado to set
	 */
	public void setEventoCancelado(Boolean eventoCancelado) {
		this.eventoCancelado = eventoCancelado;
	}
	
}
