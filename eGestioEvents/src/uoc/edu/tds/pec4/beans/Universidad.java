/**
 * 
 */
package uoc.edu.tds.pec4.beans;

/**
 * @author jgarcia
 *
 */

import java.io.Serializable;
import java.sql.Date;

public class Universidad implements BeanInterface, Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer idUniversidad;
	private String nombre;
	private Integer idContacto;
	private Date fechaAlta;
	private Integer estado;
	private Date fechaEstado;
	private String motivoEstado;
	
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
	 * @return the estado
	 */
	public Integer getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the motivoEstado
	 */
	public String getMotivoEstado() {
		return motivoEstado;
	}

	/**
	 * @param motivoEstado the motivoEstado to set
	 */
	public void setMotivoEstado(String motivoEstado) {
		this.motivoEstado = motivoEstado;
	}

	/**
	 * @return the fechaAlta
	 */
	public Date getFechaAlta() {
		return fechaAlta;
	}

	/**
	 * @param fechaAlta the fechaAlta to set
	 */
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	/**
	 * @return the fechaEstado
	 */
	public Date getFechaEstado() {
		return fechaEstado;
	}

	/**
	 * @param fechaEstado the fechaEstado to set
	 */
	public void setFechaEstado(Date fechaEstado) {
		this.fechaEstado = fechaEstado;
	}

	public Integer getIdContacto() {
		return idContacto;
	}

	public void setIdContacto(Integer idContacto) {
		this.idContacto = idContacto;
	}
	
}
