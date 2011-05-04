package uoc.edu.tds.pec4.utils;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class TDSLanguageUtils  {
	
	private static ResourceBundle resourceBundle;
	private static final String currentBaseName = "i18n/messages";
	private static Locale currentLocale;
	
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
			return new String();
		}		
	}
	
	public static String getCurrentBaseName() {
		return currentBaseName;
	}
	
	public static Locale getCurrentLocale() {
		return currentLocale;
	}

}
