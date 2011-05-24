/**
 * 
 */
package uoc.edu.tds.pec4.dtos;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author ML019882
 *
 */
public class DTOInscripcionConsulta extends DTOInscripcion implements DTOInterface,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date fechaInicioBusqueda;
	private Date fechaFinBusqueda;
	private String usuario;
	/**
	 * @return the fechaInicioBusqueda
	 */
	public Date getFechaInicioBusqueda() {
		return fechaInicioBusqueda;
	}
	/**
	 * @param fechaInicioBusqueda the fechaInicioBusqueda to set
	 */
	public void setFechaInicioBusqueda(Date fechaInicioBusqueda) {
		this.fechaInicioBusqueda = fechaInicioBusqueda;
	}
	/**
	 * @return the fechaFinBusqueda
	 */
	public Date getFechaFinBusqueda() {
		return fechaFinBusqueda;
	}
	/**
	 * @param fechaFinBusqueda the fechaFinBusqueda to set
	 */
	public void setFechaFinBusqueda(Date fechaFinBusqueda) {
		this.fechaFinBusqueda = fechaFinBusqueda;
	}
	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}
