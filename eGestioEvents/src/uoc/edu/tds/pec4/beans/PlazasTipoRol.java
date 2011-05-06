package uoc.edu.tds.pec4.beans;

import java.io.Serializable;

public class PlazasTipoRol  implements BeanInterface, Serializable{

	private static final long serialVersionUID = 1L;
	private Integer idEvento;
	private Integer idTipoRol;
	private Integer plazasAsignadas;
	
	public Integer getIdEvento() {
		return idEvento;
	}
	public void setIdEvento(Integer idEvento) {
		this.idEvento = idEvento;
	}
	public Integer getIdTipoRol() {
		return idTipoRol;
	}
	public void setIdTipoRol(Integer idTipoRol) {
		this.idTipoRol = idTipoRol;
	}
	public Integer getPlazasAsignadas() {
		return plazasAsignadas;
	}
	public void setPlazasAsignadas(Integer plazasAsignadas) {
		this.plazasAsignadas = plazasAsignadas;
	}
	
	
}
