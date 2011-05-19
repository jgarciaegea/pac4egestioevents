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
	private Integer idCentro;
	private Integer idEvento;
	private Integer idTipoEvento;
	private Date fechaInicio;
	private Integer duracion;
	private Integer plazas;
	private Integer inscritos;
	private Integer asistentes;
	private Double porcentajeAsistenciaMayorDe;
	private Double porcentajeAsistenciaMenorDe;
	private Double porcentajeAsistencia;
	private Integer ingresosMayorDe;
	private Integer ingresosMenorDe;
	private Integer ingresos;
	private String estadoEvento;
	private String estadoAsistencia;
	private Integer idAsistente;
	private Integer idRol;
	private Integer idDocumentoIdentificacion;

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
	 * @return the duracion
	 */
	public Integer getDuracion() {
		return duracion;
	}

	/**
	 * @param duracion the duracion to set
	 */
	public void setDuracion (Integer duracion) {
		this.duracion = duracion;
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
	public Double getPorcentajeAsistencia() {
		return porcentajeAsistencia;
	}

	/**
	 * @param porcentajeAsistencia the porcentajeAsistencia to set
	 */
	public void setPorcentajeAsistencia(Double porcentajeAsistencia) {
		this.porcentajeAsistencia = porcentajeAsistencia;
	}
	
	/**
	 * @return the porcentajeAsistenciaMayorDe
	 */
	public Double getPorcentajeAsistenciaMayorDe() {
		return porcentajeAsistenciaMayorDe;
	}

	/**
	 * @param porcentajeAsistenciaMayorDe the porcentajeAsistenciaMayorDe to set
	 */
	public void setPorcentajeAsistenciaMayorDe(Double porcentajeAsistenciaMayorDe) {
		this.porcentajeAsistenciaMayorDe = porcentajeAsistenciaMayorDe;
	}

	/**
	 * @return the porcentajeAsistenciaMenorDe
	 */
	public Double getPorcentajeAsistenciaMenorDe() {
		return porcentajeAsistenciaMenorDe;
	}

	/**
	 * @param porcentajeAsistenciaMenorDe the porcentajeAsistenciaMenorDe to set
	 */
	public void setPorcentajeAsistenciaMenorDe(Double porcentajeAsistenciaMenorDe) {
		this.porcentajeAsistenciaMenorDe = porcentajeAsistenciaMenorDe;
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
	 * @return the ingresosMayorDe
	 */
	public Integer getIngresosMayorDe() {
		return ingresosMayorDe;
	}

	/**
	 * @param ingresosMenorDe the ingresosMenorDe to set
	 */
	public void setIngresosMenorDe(Integer ingresosMenorDe) {
		this.ingresosMenorDe = ingresosMenorDe;
	}

	/**
	 * @return the ingresosMenorDe
	 */
	public Integer getIngresosMenorDe() {
		return ingresosMenorDe;
	}

	/**
	 * @param ingresosMayorDe the ingresosMayorDe to set
	 */
	public void setIngresosMayorDe(Integer ingresosMayorDe) {
		this.ingresosMayorDe = ingresosMayorDe;
	}
	
	/**
	 * @return the estadoEvento
	 */
	public String getEstadoEventos() {
		return estadoEvento;
	}

	/**
	 * @param estadoEvento the estadoEvento to set
	 */
	public void setEstadoEvento(String estadoEvento) {
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
	/**
	 * @return the idAsistente
	 */
	public Integer getIdAsistente() {
		return idAsistente;
	}

	/**
	 * @param idAsistente the idAsistente to set
	 */
	public void setIdAsistente(Integer idAsistente) {
		this.idAsistente = idAsistente;
	}
	
	/**
	 * @return the idRol
	 */
	public Integer getIdRol() {
		return idRol;
	}

	/**
	 * @param idRol the idRol to set
	 */
	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}

	/**
	 * @return the idDocumentoIdentificacion
	 */
	public Integer getDocumentoIdentificacion() {
		return idDocumentoIdentificacion;
	}

	/**
	 * @param idDocumentoIdentificacion the idDocumentoIdentificacion to set
	 */
	public void setIdDocumentoIdentificacion(Integer idDocumentoIdentificacion) {
		this.idDocumentoIdentificacion = idDocumentoIdentificacion;
	}
	
}