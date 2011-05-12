/**
 * 
 */
package uoc.edu.tds.pec4.cliente;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Locale;

import javax.swing.SwingUtilities;

import uoc.edu.tds.pec4.excepciones.OperationErrorRMI;
import uoc.edu.tds.pec4.pantallas.PantallaLogin;
import uoc.edu.tds.pec4.resources.TDSLanguageUtils;


/**
 * @author ML019882
 *
 */
public class Cliente {

	/**
	 * @param args
	 * @throws NotBoundException 
	 * @throws RemoteException 
	 * @throws MalformedURLException 
	 */
	public static void main(String argv[]) throws OperationErrorRMI, MalformedURLException, RemoteException, NotBoundException {
    	
		if(argv.length==0) TDSLanguageUtils.setLanguage(new Locale("ca_ES"));
    	if(argv.length==1) TDSLanguageUtils.setLanguage(new Locale(argv[0]));
    	
    	SwingUtilities.invokeLater(new Runnable() {
            public void run(){
				PantallaLogin pantallalogin = new PantallaLogin();
				System.out.println("Pantalla Login Creada");
				pantallalogin.setVisible(true);
            }
        });
    	
	}
	
}
