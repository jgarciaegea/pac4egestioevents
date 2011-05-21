/**
 * 
 */
package uoc.edu.tds.pec4.beans;

import java.io.Serializable;

/**
 * @author ML019882
 * Bean de los eventos que un usuario esta inscrito
 */
public class EventoViewConsulta extends Evento implements BeanInterface,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String centroEvento;
	private String universidadEvento;
	private String estadoAsistencia;
	private String tipoEvento;
	/**
	 * @return the centroEvento
	 */
	public String getCentroEvento() {
		return centroEvento;
	}
	/**
	 * @param centroEvento the centroEvento to set
	 */
	public void setCentroEvento(String centroEvento) {
		this.centroEvento = centroEvento;
	}
	/**
	 * @return the universidadEvento
	 */
	public String getUniversidadEvento() {
		return universidadEvento;
	}
	/**
	 * @param universidadEvento the universidadEvento to set
	 */
	public void setUniversidadEvento(String universidadEvento) {
		this.universidadEvento = universidadEvento;
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
	 * @return the tipoEvento
	 */
	public String getTipoEvento() {
		return tipoEvento;
	}
	/**
	 * @param tipoEvento the tipoEvento to set
	 */
	public void setTipoEvento(String tipoEvento) {
		this.tipoEvento = tipoEvento;
	} 
	
	
}
