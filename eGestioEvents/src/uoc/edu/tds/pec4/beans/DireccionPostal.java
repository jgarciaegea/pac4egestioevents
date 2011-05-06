/**
 * 
 */
package uoc.edu.tds.pec4.beans;

import java.io.Serializable;


/**
 * @author ML019882
 *
 */
public class DireccionPostal extends Contacto implements BeanInterface, Serializable  {

	private static final long serialVersionUID = 1L;
	private String domicilio; 
	private Integer pais;
	private Integer localidad;
	private Integer cp;

	
	/**
	 * 
	 */
	public DireccionPostal(String domicilio,Integer localidad, Integer cp, Integer pais) {
		// TODO Auto-generated constructor stub
		this.setCp(cp);
		this.setDomicilio(domicilio);
		this.setLocalidad(localidad);
		this.setPais(pais);
	}


	/**
	 * @return the domicilio
	 */
	public String getDomicilio() {
		return domicilio;
	}


	/**
	 * @param domicilio the domicilio to set
	 */
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}


	/**
	 * @return the pais
	 */
	public Integer getPais() {
		return pais;
	}


	/**
	 * @param pais the pais to set
	 */
	public void setPais(Integer pais) {
		this.pais = pais;
	}


	/**
	 * @return the localidad
	 */
	public Integer getLocalidad() {
		return localidad;
	}


	/**
	 * @param localidad the localidad to set
	 */
	public void setLocalidad(Integer localidad) {
		this.localidad = localidad;
	}


	/**
	 * @return the cp
	 */
	public Integer getCp() {
		return cp;
	}


	/**
	 * @param cp the cp to set
	 */
	public void setCp(Integer cp) {
		this.cp = cp;
	}

}
