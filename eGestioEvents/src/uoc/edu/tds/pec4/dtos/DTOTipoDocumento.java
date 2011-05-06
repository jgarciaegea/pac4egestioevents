package uoc.edu.tds.pec4.dtos;

import java.io.Serializable;

import uoc.edu.tds.pec4.beans.TipoDocumento;

public class DTOTipoDocumento implements DTOInterface, Serializable{
	private static final long serialVersionUID = 1L;
	
	private TipoDocumento tipoDocumento;

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
}
