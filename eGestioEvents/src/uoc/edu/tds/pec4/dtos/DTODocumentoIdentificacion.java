package uoc.edu.tds.pec4.dtos;

import java.io.Serializable;

import uoc.edu.tds.pec4.beans.DocumentoIdentificacion;

public class DTODocumentoIdentificacion implements DTOInterface, Serializable{
	private static final long serialVersionUID = 1L;
	
	private DocumentoIdentificacion documentoIdentificacion;
	private DTOTipoDocumento dtoTipoDocumento;
	private DTOPais dtoPais;
	
	public DocumentoIdentificacion getDocumentoIdentificacion() {
		return documentoIdentificacion;
	}
	public void setDocumentoIdentificacion(
			DocumentoIdentificacion documentoIdentificacion) {
		this.documentoIdentificacion = documentoIdentificacion;
	}
	public DTOPais getDtoPais() {
		return dtoPais;
	}
	public void setDtoPais(DTOPais dtoPais) {
		this.dtoPais = dtoPais;
	}
	public DTOTipoDocumento getDtoTipoDocumento() {
		return dtoTipoDocumento;
	}
	public void setDtoTipoDocumento(DTOTipoDocumento dtoTipoDocumento) {
		this.dtoTipoDocumento = dtoTipoDocumento;
	}
	
}
