package uoc.edu.tds.pec4.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import uoc.edu.tds.pec4.beans.TipoEvento;

public class DTOTipoEvento implements DTOInterface, Serializable{
	private static final long serialVersionUID = 1L;
	
	private TipoEvento tipoEvento;
	private List<DTOTipoEventoRol> dtoTipoEventoRol;

	/**
	 * 
	 */
	public DTOTipoEvento() {
		dtoTipoEventoRol = new ArrayList<DTOTipoEventoRol>();
	}

	/**
	 * @return the dtoTipoEventoRol
	 */
	public List<DTOTipoEventoRol> getDtoTipoEventoRol() {
		return dtoTipoEventoRol;
	}

	/**
	 * @param dtoTipoEventoRol the dtoTipoEventoRol to set
	 */
	public void setDtoTipoEventoRol(List<DTOTipoEventoRol> dtoTipoEventoRol) {
		this.dtoTipoEventoRol = dtoTipoEventoRol;
	}

	/**
	 * @return the tipoEvento
	 */
	public TipoEvento getTipoEvento() {
		return tipoEvento;
	}

	/**
	 * @param tipoEvento the tipoEvento to set
	 */
	public void setTipoEvento(TipoEvento tipoEvento) {
		this.tipoEvento = tipoEvento;
	}
}
