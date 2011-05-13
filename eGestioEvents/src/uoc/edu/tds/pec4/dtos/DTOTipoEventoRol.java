/**
 * 
 */
package uoc.edu.tds.pec4.dtos;

/**
 * @author jgarcia
 *
 */

import java.io.Serializable;
import uoc.edu.tds.pec4.beans.TipoEventoRol;

public class DTOTipoEventoRol implements DTOInterface, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private TipoEventoRol tipoEventoRol;
	private DTOTipoEvento dtoTipoEvento;
	private DTOTipoRol dtoTipoRol;
	
	/**
	 * @return the tipoEventoRol
	 */
	public TipoEventoRol getTipoEventoRol() {
		return tipoEventoRol;
	}
	/**
	 * @param tipoEventoRol the tipoEventoRol to set
	 */
	public void setTipoEventoRol(TipoEventoRol tipoEventoRol) {
		this.tipoEventoRol = tipoEventoRol;
	}
	/**
	 * @return the dtoTipoEvento
	 */
	public DTOTipoEvento getDtoTipoEvento() {
		return dtoTipoEvento;
	}
	/**
	 * @param dtoTipoEvento the dtoTipoEvento to set
	 */
	public void setDtoTipoEvento(DTOTipoEvento dtoTipoEvento) {
		this.dtoTipoEvento = dtoTipoEvento;
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
