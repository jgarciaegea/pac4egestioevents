package uoc.edu.tds.pec4.dtos;

import java.io.Serializable;

import uoc.edu.tds.pec4.beans.Usuario;

public abstract class DTOUsuario implements DTOInterface, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	private DTOCentroDocente dtoCentroDocente;
	private DTODocumentoIdentificacion dtoDocumentoIden;
	private DTOContacto dtoContacto;
	private DTODatosBancarios dtoDatosBancarios;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public DTOCentroDocente getDtoCentroDocente() {
		return dtoCentroDocente;
	}
	public void setDtoCentroDocente(DTOCentroDocente dtoCentroDocente) {
		this.dtoCentroDocente = dtoCentroDocente;
	}
	public DTODocumentoIdentificacion getDtoDocumentoIden() {
		return dtoDocumentoIden;
	}
	public void setDtoDocumentoIden(DTODocumentoIdentificacion dtoDocumentoIden) {
		this.dtoDocumentoIden = dtoDocumentoIden;
	}
	public DTOContacto getDtoContacto() {
		return dtoContacto;
	}
	public void setDtoContacto(DTOContacto dtoContacto) {
		this.dtoContacto = dtoContacto;
	}
	public DTODatosBancarios getDtoDatosBancarios() {
		return dtoDatosBancarios;
	}
	public void setDtoDatosBancarios(DTODatosBancarios dtoDatosBancarios) {
		this.dtoDatosBancarios = dtoDatosBancarios;
	}
	
}
