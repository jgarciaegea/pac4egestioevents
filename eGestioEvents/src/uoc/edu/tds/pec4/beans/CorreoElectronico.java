/**
 * 
 */
package uoc.edu.tds.pec4.beans;

import java.io.Serializable;

/**
 * @author ML019882
 *
 */
public class CorreoElectronico extends Contacto implements BeanInterface, Serializable {

	private static final long serialVersionUID = 1L;
	private String correoElectronico; 
	
	/**
	 * 
	 */
	public CorreoElectronico(String correoElectronico) {
		// TODO Auto-generated constructor stub
		this.setCorreoElectronico(correoElectronico);
	}

	/**
	 * @return the correoElectronico
	 */
	public String getCorreoElectronico() {
		return correoElectronico;
	}

	/**
	 * @param correoElectronico the correoElectronico to set
	 */
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

}
