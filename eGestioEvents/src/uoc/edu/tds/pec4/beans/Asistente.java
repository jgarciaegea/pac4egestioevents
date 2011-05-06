package uoc.edu.tds.pec4.beans;

import java.io.Serializable;

public class Asistente extends Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer idRol;
	private Integer idDatosBancarios;
	
	public Integer getIdRol() {
		return idRol;
	}
	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}
	public Integer getIdDatosBancarios() {
		return idDatosBancarios;
	}
	public void setIdDatosBancarios(Integer idDatosBancarios) {
		this.idDatosBancarios = idDatosBancarios;
	}
}
