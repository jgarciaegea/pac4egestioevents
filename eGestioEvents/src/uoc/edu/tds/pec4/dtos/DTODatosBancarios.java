package uoc.edu.tds.pec4.dtos;

import java.io.Serializable;

import uoc.edu.tds.pec4.beans.DatosBancarios;

public class DTODatosBancarios implements DTOInterface, Serializable{
	private static final long serialVersionUID = 1L;
	
	private DatosBancarios datosBancarios;

	public DatosBancarios getDatosBancarios() {
		return datosBancarios;
	}
	
	public String getCCCompleta() {
		return  (getDatosBancarios().getBanco()+ "-"  + getDatosBancarios().getSucursal()+ "-"
		+ getDatosBancarios().getDc()+ "-"+ getDatosBancarios().getCc());
	}

	public void setDatosBancarios(DatosBancarios datosBancarios) {
		this.datosBancarios = datosBancarios;
	}
	
}
