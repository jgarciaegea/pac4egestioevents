package uoc.edu.tds.pec4.beans;

/**
 * Bean de Estadisticas
 * @author SusanaUOC
 */

import java.io.Serializable;
import java.sql.Date;

public class Estadisticas implements BeanInterface, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer idUniversidad;
	private String nombreUniversidad;
	private Integer idCentro;
	private String nombreCentro;
	private Integer idEvento;
	private String nombreEvento;
	private Integer idTipoEvento;
	private String descripcionTipoEvento;
	private Date fechaInicio;
	private Date fechaFin;
	private Integer plazas;
	private Integer inscritos;
	private Integer asistentes;
	private Double porcentajeAsistencia;
	private Integer ingresos;
	private Boolean estadoEvento;
	private String estadoAsistencia;
	

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
	 * @return the nombreUniversidad
	 */
	public String getNombreUniversidad() {
		return nombreUniversidad;
	}

	/**
	 * @param nombreUniversidad the nombreUniversidad to set
	 */
	public void setNombreUniversidad(String nombre) {
		this.nombreUniversidad = nombre;
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
	 * @return the nombreCentro
	 */
	public String getNombreCentro() {
		return nombreCentro;
	}

	/**
	 * @param nombreCentro the nombreCentro to set
	 */
	public void setNombreCentro(String nombre) {
		this.nombreCentro = nombre;
	}

	/**
	 * @return the idEvento
	 */
	public Integer getIdEvento() {
		return idEvento;
	}

	/**
	 * @param idEvento the idEvento to set
	 */
	public void setIdEvento(Integer idEvento) {
		this.idEvento = idEvento;
	}

	
	/**
	 * @return the nombreEvento
	 */
	public String getNombreEvento() {
		return nombreEvento;
	}

	/**
	 * @param nombreEvento the nombreEvento to set
	 */
	public void setNombreEvento(String nombre) {
		this.nombreEvento = nombre;
	}

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
	 * @return the descripcionTipoEvento
	 */
	public String getDescripcionTipoEvento() {
		return descripcionTipoEvento;
	}

	/**
	 * @param descripcionTipoEvento the descripcionTipoEvento to set
	 */
	public void setDescripcionTipoEvento(String descripcionTipoEvento) {
		this.descripcionTipoEvento = descripcionTipoEvento;
	}	
	
	/**
	 * @return the fechaInicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}	
	
	/**
	 * @return the fechaFin
	 */
	public Date getFechaFin() {
		return fechaFin;
	}

	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * @return the plazas
	 */
	public Integer getPlazas() {
		return plazas;
	}

	/**
	 * @param plazas the plazas to set
	 */
	public void setPlazas(Integer plazas) {
		this.plazas = plazas;
	}
	
	/**
	 * @return the inscritos
	 */
	public Integer getInscritos() {
		return inscritos;
	}

	/**
	 * @param inscritos the inscritos to set
	 */
	public void setInscritos(Integer inscritos) {
		this.inscritos = inscritos;
	}
	
	/**
	 * @return the asistentes
	 */
	public Integer getAsistentes() {
		return asistentes;
	}

	/**
	 * @param asistentes the asistentes to set
	 */
	public void setAsistentes(Integer asistentes) {
		this.asistentes = asistentes;
	}
	
	/**
	 * @return the porcentajeAsistencia
	 */
	public Double getPorcentajaAsistencia() {
		return porcentajeAsistencia;
	}

	/**
	 * @param porcentajeAsistencia the porcentajeAsistencia to set
	 */
	public void setPorcentajeAsistencia(Double porcentajeAsistencia) {
		this.porcentajeAsistencia = porcentajeAsistencia;
	}
	
	/**
	 * @return the ingresos
	 */
	public Integer getIngresos() {
		return ingresos;
	}

	/**
	 * @param ingresos the ingresos to set
	 */
	public void setIngresos(Integer ingresos) {
		this.ingresos = ingresos;
	}
	
	/**
	 * @return the estadoEvento
	 */
	public Boolean getEstadoEventos() {
		return estadoEvento;
	}

	/**
	 * @param estadoEvento the estadoEvento to set
	 */
	public void setEstadoEvento(Boolean estadoEvento) {
		this.estadoEvento = estadoEvento;
	}
	/**
	 * @return the estadoAsistencia
	 */
	public String getEstadoAsistencia() {
		return estadoAsistencia;
	}

	/**
	 * @param estadoAsistencia the estadoAsistencia to set
	 */
	public void setEstadoAsistencia(String estadoAsistencia) {
		this.estadoAsistencia = estadoAsistencia;
	}

}