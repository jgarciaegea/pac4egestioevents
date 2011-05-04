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

public class CentroDocente implements BeanInterface, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer idCentro;
	private Integer estado;
	private String nombre;
	private String motivoEstado;
//	private Contacto contacto; 
	private Universidad universidad;
	private Date fechaAlta;
	private Date fechaEstado;

	/**
	 * @param idCentro
	 * @param estado
	 * @param nombre
	 * @param motivoEstado
	 * @param universidad
	 * @param fechaAlta
	 * @param fechaEstado
	 */
	public CentroDocente(Integer idCentro, Integer estado, String nombre,
			String motivoEstado, Universidad universidad, Date fechaAlta,
			Date fechaEstado) {
		super();
		this.idCentro = idCentro;
		this.estado = estado;
		this.nombre = nombre;
		this.motivoEstado = motivoEstado;
		this.universidad = universidad;
		this.fechaAlta = fechaAlta;
		this.fechaEstado = fechaEstado;
	}
	

	/**
	 * 
	 */
	public CentroDocente() {
		super();
	}


	/**
	 * @return the idCentro
	 */
	public Integer getIdCentro() {
		return idCentro;
	}


	/**
	 * @param idCentro the idCentro to set
	 */
	public void setIdCentro(Integer idCentro) {
		this.idCentro = idCentro;
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
	 * @return the universidad
	 */
	public Universidad getUniversidad() {
		return universidad;
	}


	/**
	 * @param universidad the universidad to set
	 */
	public void setUniversidad(Universidad universidad) {
		this.universidad = universidad;
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
