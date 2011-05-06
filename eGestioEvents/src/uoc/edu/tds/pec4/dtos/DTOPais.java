package uoc.edu.tds.pec4.dtos;

import java.io.Serializable;

import uoc.edu.tds.pec4.beans.Pais;

public class DTOPais implements DTOInterface, Serializable{
	
	private static final long serialVersionUID = 1L;
	private Pais pais;

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}
}
