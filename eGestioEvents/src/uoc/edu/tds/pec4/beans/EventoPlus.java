/**
 * 
 */
package uoc.edu.tds.pec4.beans;

import java.io.Serializable;

/**
 * @author jgarcia
 *
 */
public class EventoPlus extends Evento implements BeanInterface, Serializable{

	private static final long serialVersionUID = 1L;
	private Integer plazasLibres;
	private Integer inscritos;
	
	public Integer getPlazasLibres() {
		return plazasLibres;
	}
	public void setPlazasLibres(Integer plazasLibres) {
		this.plazasLibres = plazasLibres;
	}
	public Integer getInscritos() {
		return inscritos;
	}
	public void setInscritos(Integer inscritos) {
		this.inscritos = inscritos;
	}
	public Boolean getEventoCerrado(){
		return (this.plazasLibres <= 0);
	}
}
