package uoc.edu.tds.pec4.beans;

import java.io.Serializable;

public class TipoDocumento  implements BeanInterface, Serializable{

	private static final long serialVersionUID = 1L;
	private Integer idTipoDocumento;
	private String descripcionDocumento;
	
	public Integer getIdTipoDocumento() {
		return idTipoDocumento;
	}
	public void setIdTipoDocumento(Integer idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}
	public String getDescripcionDocumento() {
		return descripcionDocumento;
	}
	public void setDescripcionDocumento(String descripcionDocumento) {
		this.descripcionDocumento = descripcionDocumento;
	}

}
