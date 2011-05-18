package uoc.edu.tds.pec4.dtos;

import java.io.Serializable;

import uoc.edu.tds.pec4.beans.UsuarioViewConsulta;

public class DTOUsuarioConsulta extends DTOUsuario implements DTOInterface, Serializable{

	private static final long serialVersionUID = 1L;
	private UsuarioViewConsulta usuarioViewConsulta;
	
	public UsuarioViewConsulta getUsuarioViewConsulta() {
		return usuarioViewConsulta;
	}
	public void setUsuarioViewConsulta(UsuarioViewConsulta usuarioViewConsulta) {
		this.usuarioViewConsulta = usuarioViewConsulta;
	}

}
