package uoc.edu.tds.pec4.gestores;

import uoc.edu.tds.pec4.dtos.DTOAdministrador;
import uoc.edu.tds.pec4.dtos.DTOAsistente;
import uoc.edu.tds.pec4.dtos.DTOPersonalSecretaria;
import uoc.edu.tds.pec4.dtos.DTOUsuario;
import uoc.edu.tds.pec4.utils.Constantes;

public class FactoriaUsuario {
	
	public static DTOUsuario getUsuario(Integer tipoUsuario) throws Exception{	
		if(tipoUsuario == null) throw new Exception("El tipo de usuario no está informado");
		switch(tipoUsuario){
			case Constantes.ADMINISTRADOR:
				return new DTOAdministrador();
			case Constantes.SECRETARIA:
				return new DTOPersonalSecretaria();
			case Constantes.ASISTENTE:
				return new DTOAsistente();
		}
		throw new Exception("El tipo de usuario no es válido");
	}
	
}


