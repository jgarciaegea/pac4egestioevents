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
}
