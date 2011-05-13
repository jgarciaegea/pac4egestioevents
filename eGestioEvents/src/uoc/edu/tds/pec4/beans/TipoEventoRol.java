/**
 * 
 */
package uoc.edu.tds.pec4.beans;

import java.io.Serializable;

/**
 * @author jgarcia
 *
 */
public class TipoEventoRol implements BeanInterface, Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer idTipoEvento;
	private Integer idRol;
	
	/**
	 * @return the idTipoEvento
	 */
	public Integer getIdTipoEvento() {
		return idTipoEvento;
	}
	/**
	 * @param idTipoEvento the idTipoEvento to set
	 */
	public void setIdTipoEvento(Integer idTipoEvento) {
		this.idTipoEvento = idTipoEvento;
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
	
	

}
