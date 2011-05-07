/**
 * 
 */
package uoc.edu.tds.pec4.excepciones;
import java.awt.GridBagLayout;
import java.awt.Rectangle;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * @author ML019882
 *
 */
public abstract class OperationErrorBasic extends Exception {

	private static final long serialVersionUID = 1L;
	private String messageError = null;
	
	//Cada apartado tendrá una sección de error
	abstract String seccionError();
	/**
	 * 
	 */
	public OperationErrorBasic(String s) {
		// TODO Auto-generated constructor stub
        super(s);
        this.messageError = s;
    }
	
	//Mostrará un alert de error
	public void showDialogError(){
		if(messageError!=null && messageError.length() >0){
			JPanel panelError = new JPanel();
			panelError.setLayout(new GridBagLayout());
			panelError.setBounds(new Rectangle(60, 15, 500, 40));
			JOptionPane.showMessageDialog(panelError,messageError,seccionError(),JOptionPane.ERROR_MESSAGE);
			panelError.setVisible(true);
		}
	 }
	
}
