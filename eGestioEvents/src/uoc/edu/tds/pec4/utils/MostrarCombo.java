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
	private int nID = 0;
	private String sValor = null;

	/**
	 * Constructor
	 * @param nID
	 * @param sValor
	 */
	public MostrarCombo(int nID, String sValor)
	{
		this.setID(nID);
		this.setValor(sValor);
	}
	
	/**
	 * Set ID
	 * @param nID
	 */
	public void setID(int nID) 
	{
		this.nID = nID;
	}
	/**
	 * Get ID
	 * @return int
	 */
	public int getID() 
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
