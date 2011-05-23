package uoc.edu.tds.pec4.dtos;

import java.io.Serializable;

import uoc.edu.tds.pec4.beans.CentroDocenteViewConsulta;

public class DTOCentroDocenteConsulta extends DTOCentroDocente implements DTOInterface, Serializable{

	private static final long serialVersionUID = 1L;
	private CentroDocenteViewConsulta centroDocenteView;
	
	public CentroDocenteViewConsulta getCentroDocenteView() {
		return centroDocenteView;
	}
	
	public void setCentroDocenteView(CentroDocenteViewConsulta centroDocenteView) {
		this.centroDocenteView = centroDocenteView;
	}

}
