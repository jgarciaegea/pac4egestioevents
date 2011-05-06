package uoc.edu.tds.pec4.dtos;

import java.io.Serializable;

import uoc.edu.tds.pec4.beans.PersonalSecretaria;

public class DTOPersonalSecretaria extends DTOUsuario implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private PersonalSecretaria secretaria;

	public PersonalSecretaria getSecretaria() {
		return secretaria;
	}

	public void setSecretaria(PersonalSecretaria secretaria) {
		this.secretaria = secretaria;
	}
	
}
