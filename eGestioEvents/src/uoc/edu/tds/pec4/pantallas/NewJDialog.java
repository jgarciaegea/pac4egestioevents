package uoc.edu.tds.pec4.pantallas;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class NewJDialog extends javax.swing.JDialog {

	/**
	* Auto-generated main method to display this JDialog
	*/
		
	public NewJDialog(JFrame frame) {
		super(frame);
		initGUI();
	}
	
	private void initGUI() {
		try {
			setSize(400, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
