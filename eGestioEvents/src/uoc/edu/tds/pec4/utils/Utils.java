package uoc.edu.tds.pec4.utils;

import javax.swing.*;

/**
 * Clase con utilidades varias para la aplicacion
 * @author Susana Ortega
 */
public class Utils {

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

}