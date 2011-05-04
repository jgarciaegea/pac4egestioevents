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
	private Integer id_contacto;
	private Integer estado;
	private String nombre;
	private String motivoEstado;
	private Date fechaAlta;
	private Date fechaEstado;
	
	/**
	 * Constructor con par‡metros
	 * @param idUniversidad
	 * @param id_contacto
	 * @param estado
	 * @param nombre
	 * @param motivoEstado
	 * @param fechaAlta
	 * @param fechaEstado
	 */
	public Universidad(Integer idUniversidad, Integer id_contacto,
			Integer estado, String nombre, String motivoEstado, Date fechaAlta,
			Date fechaEstado) {
		super();
		this.idUniversidad = idUniversidad;
		this.id_contacto = id_contacto;
		this.estado = estado;
		this.nombre = nombre;
		this.motivoEstado = motivoEstado;
		this.fechaAlta = fechaAlta;
		this.fechaEstado = fechaEstado;
	}

	/**
	 * Constructor b‡sico
	 */
	public Universidad() {
		super();
	}

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
	 * @return the id_contacto
	 */
	public Integer getId_contacto() {
		return id_contacto;
	}

	/**
	 * @param id_contacto the id_contacto to set
	 */
	public void setId_contacto(Integer id_contacto) {
		this.id_contacto = id_contacto;
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
	
}
