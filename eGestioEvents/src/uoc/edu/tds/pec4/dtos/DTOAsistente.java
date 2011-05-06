package uoc.edu.tds.pec4.dtos;

import java.io.Serializable;

public class DTOAsistente extends DTOUsuario implements Serializable{

	private static final long serialVersionUID = 1L;
	private DTOTipoRol dtoTipoRol;
	private DTODatosBancarios dtoDatosBancarios;
	
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
