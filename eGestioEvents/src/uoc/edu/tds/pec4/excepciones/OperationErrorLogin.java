/**
 * 
 */
package uoc.edu.tds.pec4.excepciones;

/**
 * @author ML019882
 *
 */
public class OperationErrorLogin extends OperationErrorBasic {

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	public OperationErrorLogin(String s) {
		super(s);
	}

	@Override
	String seccionError() {
		return "Login incorrecto";
	}
	
}
