package uoc.edu.tds.pec4.dtos;

import java.io.Serializable;

import uoc.edu.tds.pec4.beans.TipoRol;

public class DTOTipoRol implements DTOInterface, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private TipoRol tipoRol;

	public TipoRol getTipoRol() {
		return tipoRol;
	}

	public void setTipoRol(TipoRol tipoRol) {
		this.tipoRol = tipoRol;
	}

}
