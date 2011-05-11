package uoc.edu.tds.pec4.utils;


/**
 * Classe per mostrar combos amb identificador i valor
 * 
 * @author Xavier
 *
 */

public class MostrarCombo implements java.io.Serializable 
{
	private static final long serialVersionUID = 1L;
	private Object nID = null;
	private String sValor = null;

	/**
	 * Constructor
	 * @param nID
	 * @param sValor
	 */
	public MostrarCombo(Object nID, String sValor)
	{
		this.setID(nID);
		this.setValor(sValor);
	}
	
	/**
	 * Set ID
	 * @param nID
	 */
	public void setID(Object nID) 
	{
		this.nID = nID;
	}
	/**
	 * Get ID
	 * @return int
	 */
	public Object getID() 
	{
		return nID;
	}

	/**
	 * Set Valor
	 * @param sValor
	 */
	public void setValor(String sValor) 
	{
		this.sValor = sValor;
	}
	/**
	 * Get Valor
	 * @return String
	 */
	public String getValor() 
	{
		return sValor;
	}
	/**
	 * Redifinicio del metode toString().
	 *  
	 */
	public String toString()
	{
		return sValor;
	}
}
