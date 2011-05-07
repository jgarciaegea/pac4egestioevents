package uoc.edu.tds.pec4.resources;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class TDSLanguageUtils  {
	
	private static ResourceBundle resourceBundle;
	private static final String currentBaseName = "i18n/messages";
	private static Locale currentLocale;
	
	
	/**
	 * Configura el idioma por defecto en el gestor de idiomas TDSLanguageUtils.* 
	 * De esta manera, siempre se sacarán los mensajes en el idioma por defecto del sistema operativo.
	 * Este viene referenciado por Locale.getDefault()
	 *
	 * @param  baseName  dirección donde se encuentra el fichero de idiomas
	 * @throws NullPointerException si baseName es nulo
	 * @return un booleano indicando si la operación ha ido bien 
	 */
	
	
	public static synchronized boolean setDefaultLanguage() {
		try {
			resourceBundle = ResourceBundle.getBundle(currentBaseName, Locale.getDefault());
			return true;
		}catch(MissingResourceException e){
			return false;
		}
	}
	
	public static synchronized boolean setLanguage(Locale locale) {
		try {
			resourceBundle = ResourceBundle.getBundle(currentBaseName,locale);
			return true;
		}catch(MissingResourceException e){
			return false;
		}
	}	
	
	public static String getMessage(String key)  {		
		try {
			return resourceBundle.getString(key);
		}catch(MissingResourceException e) {
			return new String("VALOR SIN DEFINIR");
		}		
	}
	
	public static String getCurrentBaseName() {
		return currentBaseName;
	}
	
	public static Locale getCurrentLocale() {
		return currentLocale;
	}

}
