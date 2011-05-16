/**
 * 
 */
package uoc.edu.tds.pec4.excepciones;

/**
 * @author ML019882
 *
 */
public class OperationErrorDatosFormulario extends OperationErrorBasic{

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	public OperationErrorDatosFormulario(String s) {
		super(s);
	}
	
	@Override
	String seccionError() {
		return "Datos formulario";
	}

}
