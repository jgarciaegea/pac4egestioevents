package edu.uoc.tds.comun;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConceptoGasto implements Serializable{
	ffff
	//variables
	private static final long serialVersionID = 4L;

	private int id_concepto;
	private String descripcion;
	
	public ConceptoGasto(int id_concepto, String descripcion)
	{
		this.id_concepto = id_concepto;
		this.descripcion = descripcion;
	}
	
	public ConceptoGasto (){
		
	}
	
	//Función que establece id_concepto
	public void setIdConcepto(int id_concepto)
	{
		this.id_concepto = id_concepto;
	}
	
	//Función que devuelve id_concepto
	public int getIdConcepto()
	{
		return id_concepto;
	}
	
	//Función que establece descripcion
	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}
	
	//Función que devuelve descripcion
	public String getDescripcion()
	{
		return this.descripcion;
	}
	
	/**
	 * Función que devuelve una lista de conceptos, para luego 
	 * mostrarlos en el combo de la pantalla de alta de factura
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public List<ConceptoGasto> getListConceptos (Connection con)
	throws SQLException {

	String sentencia;
	List<ConceptoGasto> ListConceptos = new ArrayList<ConceptoGasto>(); 
	ResultSet rsConceptos = null;
	
	Statement stmt = con.createStatement();
	try{
		sentencia = "SELECT id_concepto,descripcion FROM CONCEPTO_GASTO";
		
		rsConceptos = stmt.executeQuery(sentencia);
	
		while(rsConceptos.next()) {
			ConceptoGasto objConcepto = new ConceptoGasto();
			objConcepto.setIdConcepto(rsConceptos.getInt("id_concepto"));
			objConcepto.setDescripcion(rsConceptos.getString("descripcion"));
			ListConceptos.add(objConcepto);
		}
	} catch (SQLException e){
    		
    		try{
    			if (rsConceptos != null){
    				rsConceptos.close();
    			}
    			
    			if (stmt != null){
    				
    				stmt.close();
    			
    			}
    		
    		}catch(SQLException e1){
    		
    			throw e1;
    		
    		}
    		
    		throw e;	
    
	}	
	return ListConceptos;
}

	public String toString()
	{
	   return descripcion;
	}
}
