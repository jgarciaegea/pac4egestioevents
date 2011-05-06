/**
 * 
 */
package uoc.edu.tds.pec4.beans;

import java.io.Serializable;

/**
 * @author ML019882
 *
 */
public class PaginaWeb extends Contacto implements BeanInterface, Serializable {

	private static final long serialVersionUID = 1L;
	private String paginaWeb; 
	
	/**
	 * 
	 */
	public PaginaWeb(String paginaWeb)  {
		// TODO Auto-generated constructor stub
		this.setPaginaWeb(paginaWeb);
	}

	/**
	 * @return the paginaWeb
	 */
	public String getPaginaWeb() {
		return paginaWeb;
	}

	/**
	 * @param paginaWeb the paginaWeb to set
	 */
	public void setPaginaWeb(String paginaWeb) {
		this.paginaWeb = paginaWeb;
	}

}
