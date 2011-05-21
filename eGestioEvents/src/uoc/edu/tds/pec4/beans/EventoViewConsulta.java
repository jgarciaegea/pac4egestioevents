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
	//private String centroEvento;
	//private String universidadEvento;
	private String estadoAsistencia;
	//private String tipoEvento;
	private String codigo;
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
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	
	
}
