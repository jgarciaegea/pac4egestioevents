package uoc.edu.tds.pec4.dtos;

import java.io.Serializable;

import uoc.edu.tds.pec4.beans.PlazasTipoRol;

public class DTOPlazasTipoRol implements DTOInterface, Serializable{
	private static final long serialVersionUID = 1L;
	
	private PlazasTipoRol plazasTipoRol;
	private DTOTipoRol dtoTipoRol;
	
	public PlazasTipoRol getPlazasTipoRol() {
		return plazasTipoRol;
	}
	public void setPlazasTipoRol(PlazasTipoRol plazasTipoRol) {
		this.plazasTipoRol = plazasTipoRol;
	}
	public DTOTipoRol getDtoTipoRol() {
		return dtoTipoRol;
	}
	public void setDtoTipoRol(DTOTipoRol dtoTipoRol) {
		this.dtoTipoRol = dtoTipoRol;
	}
	
}
