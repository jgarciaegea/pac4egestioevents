/**
 * 
 */
package uoc.edu.tds.pec4.beans;

/**
 * @author jgarcia
 *
 */
import java.io.Serializable;

public class EventoRolPlazas implements BeanInterface, Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer idEvento;
	private Integer idRol;
	private Integer plazas;
	
	/**
	 * @return the idEvento
	 */
	public Integer getIdEvento() {
		return idEvento;
	}
	/**
	 * @param idEvento the idEvento to set
	 */
	public void setIdEvento(Integer idEvento) {
		this.idEvento = idEvento;
	}
	/**
	 * @return the idRol
	 */
	public Integer getIdRol() {
		return idRol;
	}
	/**
	 * @param idRol the idRol to set
	 */
	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}
	/**
	 * @return the plazas
	 */
	public Integer getPlazas() {
		return plazas;
	}
	/**
	 * @param plazas the plazas to set
	 */
	public void setPlazas(Integer plazas) {
		this.plazas = plazas;
	}
}