/**
 * 
 */
package uoc.edu.tds.pec4.beans;

/**
 * @author jgarcia
 *
 */

import java.io.Serializable;

public class EventoRequisitos implements BeanInterface, Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer idEvento;
	private Integer idEventoReq;
	
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
	 * @return the idEventoReq
	 */
	public Integer getIdEventoReq() {
		return idEventoReq;
	}
	/**
	 * @param idEventoReq the idEventoReq to set
	 */
	public void setIdEventoReq(Integer idEventoReq) {
		this.idEventoReq = idEventoReq;
	}
}