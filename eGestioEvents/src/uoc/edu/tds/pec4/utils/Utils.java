package uoc.edu.tds.pec4.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.*;

import uoc.edu.tds.pec4.excepciones.OperationErrorDatosFormulario;

/**
 * Clase con utilidades varias para la aplicacion
 * @author Susana Ortega
 */
public class Utils {
	
	public static final String MESSAGE_ERROR = "Falta por introducir el campo ";
	public static final String MESSAGE_NUMERIC = ".El formato ha de ser numérico";
	public static final String MESSAGE_FECHA = ".El formato del campo ha de ser una fecha dd/mm/yyyy";
	
    /**
     * Muestra un mensaje de error al usuario
     * @param texto: texto del mensaje
     * @param titulo: titulo
     */
    public static void mostraMensajeError(String texto, String titulo) {
        JOptionPane.showMessageDialog (
				null,
				texto,
				titulo,
				JOptionPane.ERROR_MESSAGE
            );
    }

    /**
     * Muestra un mensaje de informacion al usuario
     * @param texto: texto del mensaje
     * @param titulo: titulo
     */
    public static void mostraMensajeInformacion(String texto, String titulo) {
        JOptionPane.showMessageDialog (
				null,
				texto,
				titulo,
				JOptionPane.INFORMATION_MESSAGE
            );
    }
    
    /**
     * Si no tiene valor retornamos false
     * @param campo
     * @return
     */
    public static Boolean valorisNull(Object campo){
    	String valor = getValor(campo);
    	if(valor == null || "".equalsIgnoreCase(valor)){
			return true;
		}
    	return false;
    }
    
	public static Boolean validaNumerico(Object campo) throws OperationErrorDatosFormulario{
    	try{
    		Integer.parseInt(getValor(campo));
    	}catch(NumberFormatException e){
    		return false;
    	}
    	return true;
    }
    
    private static String getValor(Object campo){
    	if(campo != null){
    		if(campo instanceof String){
        		return (String)campo;
        	}else if(campo instanceof MostrarCombo){
        		return (((MostrarCombo) campo).getValor());
        	}
    	}
    	return null;
    }
    
    public static Boolean parseaFecha(String fecha) throws OperationErrorDatosFormulario{
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try{
			formatter.parse(fecha);
        }catch(ParseException parseexception){
        	return false;
        }
        return true;
	}
    
    public static java.sql.Date transformFecha(String fecha)  throws OperationErrorDatosFormulario{
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try{
			if(fecha!=null){
				return new java.sql.Date(formatter.parse(fecha).getTime());
			}
        }catch(ParseException parseexception){
        	throw new OperationErrorDatosFormulario(parseexception.getMessage());
        }
        return null;
	}
    
}