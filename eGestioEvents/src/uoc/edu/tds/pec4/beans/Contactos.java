/**
 * 
 */
package uoc.edu.tds.pec4.beans;

import java.io.Serializable;
import java.sql.Date;
/**
 * @author ML019882
 *
 */
public class Contactos extends Contacto implements BeanInterface, Serializable {

	
	private static final long serialVersionUID = 1L;
	private String tipoContacto; 
	private String ordenPreferencia; 
	private Date FechaInactivo;
	/**
	 * 
	 */
	public  Contactos(String tipoContacto) {
		// TODO Auto-generated constructor stub
		this.setTipoContacto(tipoContacto);
	}
	

	/**
	 * @return the tipoContacto
	 */
	public String getTipoContacto() {
		return tipoContacto;
	}

	/**
	 * @param tipoContacto the tipoContacto to set
	 */
	public void setTipoContacto(String tipoContacto) {
		this.tipoContacto = tipoContacto;
	}

	/**
	 * @return the ordenPreferencia
	 */
	public String getOrdenPreferencia() {
		return ordenPreferencia;
	}

	/**
	 * @param ordenPreferencia the ordenPreferencia to set
	 */
	public void setOrdenPreferencia(String ordenPreferencia) {
		this.ordenPreferencia = ordenPreferencia;
	}


	/**
	 * @return the fechaInactivo
	 */
	public Date getFechaInactivo() {
		return FechaInactivo;
	}


	/**
	 * @param fechaInactivo the fechaInactivo to set
	 */
	public void setFechaInactivo(Date fechaInactivo) {
		FechaInactivo = fechaInactivo;
	}

}
