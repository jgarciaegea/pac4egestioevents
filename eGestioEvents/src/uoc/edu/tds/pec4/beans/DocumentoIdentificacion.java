package uoc.edu.tds.pec4.beans;

import java.io.Serializable;

public class DocumentoIdentificacion implements BeanInterface, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer idDocumentoIdentificacion;
	private Integer idTipoDocumento;
	private Integer idPais;
	private String numeroDocumento;
	public Integer getIdDocumentoIdentificacion() {
		return idDocumentoIdentificacion;
	}
	public void setIdDocumentoIdentificacion(Integer idDocumentoIdentificacion) {
		this.idDocumentoIdentificacion = idDocumentoIdentificacion;
	}
	public Integer getIdTipoDocumento() {
		return idTipoDocumento;
	}
	public void setIdTipoDocumento(Integer idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}
	public Integer getIdPais() {
		return idPais;
	}
	public void setIdPais(Integer idPais) {
		this.idPais = idPais;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

}
