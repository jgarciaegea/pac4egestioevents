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
	private String universidad;
	private Integer idCentroDocente;
	private String centroDocente;
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
	 * @return the universidad
	 */
	public String getUniversidad() {
		return universidad;
	}
	/**
	 * @param universidad the universidad to set
	 */
	public void setUniversidad(String universidad) {
		this.universidad = universidad;
	}
	/**
	 * @return the idCentroDocente
	 */
	public Integer getIdCentroDocente() {
		return idCentroDocente;
	}
	/**
	 * @param idCentroDocente the idCentroDocente to set
	 */
	public void setIdCentroDocente(Integer idCentroDocente) {
		this.idCentroDocente = idCentroDocente;
	}
	/**
	 * @return the centroDocente
	 */
	public String getCentroDocente() {
		return centroDocente;
	}
	/**
	 * @param centroDocente the centroDocente to set
	 */
	public void setCentroDocente(String centroDocente) {
		this.centroDocente = centroDocente;
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
