package uoc.edu.tds.pec4.dtos;

import java.io.Serializable;

import uoc.edu.tds.pec4.beans.TipoTelefono;

public class DTOTipoTelefono implements DTOInterface, Serializable{
	private static final long serialVersionUID = 1L;
	
	private TipoTelefono tipoTelefono;

	public TipoTelefono getTipoTelefono() {
		return tipoTelefono;
	}

	public void setTipoTelefono(TipoTelefono tipoTelefono) {
		this.tipoTelefono = tipoTelefono;
	}
}
