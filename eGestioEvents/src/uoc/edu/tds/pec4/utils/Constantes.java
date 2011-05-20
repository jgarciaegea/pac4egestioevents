package uoc.edu.tds.pec4.utils;

import uoc.edu.tds.pec4.resources.TDSLanguageUtils;

public class Constantes {
	
	public static final int ADMINISTRADOR = 1;
	public static final int SECRETARIA = 2;
	public static final int ASISTENTE = 3;
	public static final int TODOS = 0;
	
	public static final String NOMBRE_TODOS = "Todos";
	public static final String NOMBRE_ADMINISTRADOR = "Administrador";
	public static final String NOMBRE_SECRETARIA = "Secretaria";
	public static final String NOMBRE_ASISTENTE = "Asistente";
	
	public static final Integer REGISTRO_ACTIVO = 1;
	public static final Integer REGISTRO_INACTIVO = 3;
	
	public static final String REGISTRO_ACTIVO_MOTIVO = TDSLanguageUtils.getMessage("constantes.registroactivo.motivo");
	public static final String REGISTRO_INACTIVO_MOTIVO = TDSLanguageUtils.getMessage("constantes.registroinactivo.motivo");
	
}
