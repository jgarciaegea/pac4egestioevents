/**
 * 
 */
package uoc.edu.tds.pec4.cliente;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Locale;

import javax.swing.SwingUtilities;

import uoc.edu.tds.pec4.excepciones.OperationErrorBD;
import uoc.edu.tds.pec4.excepciones.OperationErrorRMI;
import uoc.edu.tds.pec4.iface.RemoteInterface;
import uoc.edu.tds.pec4.pantallas.PantallaLogin;
import uoc.edu.tds.pec4.pantallas.PantallaServidor;
import uoc.edu.tds.pec4.resources.TDSLanguageUtils;


/**
 * @author ML019882
 *
 */
public class Cliente {

	private static RemoteInterface remote;
	private final static String UNAME_URL_RMI_ALL= "rmi://localhost/RemotoImpl";
	
	/**
	 * @param args
	 * @throws NotBoundException 
	 * @throws RemoteException 
	 * @throws MalformedURLException 
	 */
	public  static void main(String argv[]) throws OperationErrorRMI, MalformedURLException, RemoteException, NotBoundException {
		// TODO Auto-generated method stub
    	if(argv.length==0) {
    		//TDSLanguageUtils.setDefaultLanguage();
    		Locale locale = new Locale("ca_ES"); 
    		TDSLanguageUtils.setLanguage(locale);
    	}
    	if(argv.length==1) {
    		Locale locale = new Locale(argv[0]); 
    		TDSLanguageUtils.setLanguage(locale);
    	}
    	
    	Cliente cl = new Cliente();
	}
		
    public Cliente () throws MalformedURLException, RemoteException, NotBoundException{
    	remote=(RemoteInterface)Naming.lookup(UNAME_URL_RMI_ALL);
    	String respuesta = remote.pruebaFuncionamiento();
    	System.out.println(respuesta);
		SwingUtilities.invokeLater(new Runnable() {
            public void run(){
				PantallaLogin pantallalogin = new PantallaLogin();
				System.out.println("Pantalla Login Creada");
				pantallalogin.setVisible(true);
            }
        });

    }
		
		
	
}
