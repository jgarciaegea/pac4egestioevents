package uoc.edu.tds.pec4.servidor;
import java.rmi.*;
import java.rmi.server.*;

import javax.swing.JOptionPane;

import uoc.edu.tds.pec4.servidor.GestorDisc;
import uoc.edu.tds.pec4.utils.TDSLanguageUtils;

import uoc.edu.tds.pec4.iface.RemotoInterface;
/**
 * Classe que implementa la interface InscripcionsInterface
 * @author SusanaUOC
 *
 */

public class RemotoImpl extends UnicastRemoteObject implements RemotoInterface {
	/**
	 * serialUID
	 */
	private static final long serialVersionUID = 6560204892629578456L;
	
	GestorDisc gDisc = null;

	public RemotoImpl() throws RemoteException {
		super();
		try {
			gDisc = new GestorDisc();
			gDisc.initConnection();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					TDSLanguageUtils.getMessage("bd.err.conn"),
					TDSLanguageUtils.getMessage("bd.err"),				
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
