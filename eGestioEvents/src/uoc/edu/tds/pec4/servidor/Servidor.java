/**
 * 
 */
package uoc.edu.tds.pec4.servidor;

import java.util.Locale;

import javax.swing.SwingUtilities;

import uoc.edu.tds.pec4.excepciones.OperationErrorBD;
import uoc.edu.tds.pec4.excepciones.OperationErrorRMI;
import uoc.edu.tds.pec4.pantallas.PantallaServidor;
import uoc.edu.tds.pec4.resources.TDSLanguageUtils;

/**
 * @author ML019882
 *
 */
public class Servidor {

	/**
	 * @param args
	 */
    public static void main(String argv[]) throws OperationErrorRMI{

    	if(argv.length==0) {
    		//TDSLanguageUtils.setDefaultLanguage();
    		Locale locale = new Locale("ca_ES"); 
    		TDSLanguageUtils.setLanguage(locale);
    	}
    	if(argv.length==1) {
    		Locale locale = new Locale(argv[0]); 
    		TDSLanguageUtils.setLanguage(locale);
    	}
				

			SwingUtilities.invokeLater(new Runnable() {
	            public void run(){
					try {
						PantallaServidor servidorpec4 = new PantallaServidor();
						servidorpec4.setDefaultCloseOperation(3);
						servidorpec4.setVisible(true);
					} catch (OperationErrorRMI e1) {
						e1.showDialogError();
					} catch (OperationErrorBD e) {
						e.showDialogError();
					}
	            }
	        });
	    }

}
