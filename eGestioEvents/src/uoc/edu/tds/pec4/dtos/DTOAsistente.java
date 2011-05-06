package uoc.edu.tds.pec4.dtos;

import java.io.Serializable;

import uoc.edu.tds.pec4.beans.Asistente;

public class DTOAsistente extends DTOUsuario implements Serializable{

	private static final long serialVersionUID = 1L;
	private Asistente asistente;
	private DTOTipoRol dtoTipoRol;
	private DTODatosBancarios dtoDatosBancarios;
	
	public Asistente getAsistente() {
		return asistente;
	}
	public void setAsistente(Asistente asistente) {
		this.asistente = asistente;
	}
	public DTOTipoRol getDtoTipoRol() {
		return dtoTipoRol;
	}
	public void setDtoTipoRol(DTOTipoRol dtoTipoRol) {
		this.dtoTipoRol = dtoTipoRol;
	}
	public DTODatosBancarios getDtoDatosBancarios() {
		return dtoDatosBancarios;
	}
	public void setDtoDatosBancarios(DTODatosBancarios dtoDatosBancarios) {
		this.dtoDatosBancarios = dtoDatosBancarios;
	}

}
