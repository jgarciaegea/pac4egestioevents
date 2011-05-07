/**
 * 
 */
package uoc.edu.tds.pec4.excepciones;

import uoc.edu.tds.pec4.resources.TDSLanguageUtils;

/**
 * @author ML019882
 *
 */
public class OperationErrorRMI extends OperationErrorBasic{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	public OperationErrorRMI(String s) {
		// TODO Auto-generated constructor stub
        super(s);
        System.out.print(s);
    }
	
	@Override
	String seccionError() {
		return TDSLanguageUtils.getMessage("servidorPEC4.error.rmi");
	}

}
