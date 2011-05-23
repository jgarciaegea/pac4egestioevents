package uoc.edu.tds.pec4.utils;

import java.awt.Component;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.*;

import uoc.edu.tds.pec4.excepciones.OperationErrorDatosFormulario;

/**
 * Clase con utilidades varias para la aplicacion
 */
public class Utils {
	
	public static final String MESSAGE_ERROR = "Falta por introducir el campo ";
	public static final String MESSAGE_EMAIL = "El formato del campo mail es incorrecto ";
	public static final String MESSAGE_NUMERIC = ". El formato ha de ser numérico";
	public static final String MESSAGE_FECHA = ". El formato del campo ha de ser una fecha dd/mm/yyyy";
	
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
    
    public static void mostraMensajeInformacion(Component component, String texto, String titulo) {
    	  JOptionPane.showMessageDialog (
    			component,
  				texto,
  				titulo,
  				JOptionPane.INFORMATION_MESSAGE
              );
    }
    
    /**
     * Muestra un mensaje de informacion al usuario
     * @param texto: texto del mensaje
     * @param titulo: titulo
     */
    public static void mostraMensajeInformacion(String texto, String titulo) {
       mostraMensajeInformacion(null,texto,titulo);
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
    		Float.parseFloat(getValor(campo));
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
    
    public static String convertFecha(String fechaConvertir)throws OperationErrorDatosFormulario{
    	try{
    		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
    		long milliseconds = formatter.parse(fechaConvertir).getTime();
    		java.util.Date fecha = new java.util.Date( milliseconds);
    		DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
    		String reportDate = df.format(fecha);
    		return reportDate;
    	}catch(Exception exception){
    		throw new OperationErrorDatosFormulario("Error al convertir la fecha al formato dd/mm/yyyy");
    	}
    }
    
    public static Boolean validaCuenta(String texto) throws OperationErrorDatosFormulario{
    	if(texto!= null && texto.trim().matches("[0-9]{12}")){
    		return true;
    	}
    	return false;

    }
    
    public static void ocultaColumna(JTable tbl, int columna){
    	
    	if(tbl != null && tbl.getColumnCount() >0){
    		tbl.getColumnModel().getColumn(columna).setMaxWidth(0);
    		tbl.getColumnModel().getColumn(columna).setMinWidth(0);
    		tbl.getTableHeader().getColumnModel().getColumn(columna).setMaxWidth(0);
    		tbl.getTableHeader().getColumnModel().getColumn(columna).setMinWidth(0);
    	}
    }
    
}