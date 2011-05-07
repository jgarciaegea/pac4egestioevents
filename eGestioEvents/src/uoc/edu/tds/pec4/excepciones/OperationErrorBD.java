/**
 * 
 */
package uoc.edu.tds.pec4.excepciones;

import uoc.edu.tds.pec4.resources.TDSLanguageUtils;

/**
 * @author ML019882
 *
 */
public class OperationErrorBD extends OperationErrorBasic{

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	public OperationErrorBD(String s) {
		// TODO Auto-generated constructor stub
		super(s);
	}
	
	@Override
	String seccionError() {
		return TDSLanguageUtils.getMessage("servidorPEC4.error.bd");
	}

}
