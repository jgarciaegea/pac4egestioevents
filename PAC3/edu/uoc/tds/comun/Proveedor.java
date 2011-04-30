package edu.uoc.tds.comun;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.io.*;

public class Proveedor implements Serializable {
	
	//variables
	private static final long serialVersionID = 2L;
	
	private int id_proveedor;
	private String nombre_entidad;
	private String cif;
	private Date fecha_alta;
	
	public Proveedor(int id_proveedor, String nombre_entidad, String cif, Date fecha_alta)
	{
		this.id_proveedor = id_proveedor;
		this.nombre_entidad = nombre_entidad;
		this.cif = cif;
		this.fecha_alta = fecha_alta;
		
	}
	public Proveedor(){
		
	}
	
	//Funcion que establece id_proveedor
	public void setIdProveedor(int id_proveedor)
	{
		this.id_proveedor = id_proveedor;
	}
	
	//Funcion que devuelve id_proveedor
	public int getIdProveedor()
	{
		return this.id_proveedor;
	}
	
	//Funcion que establece nombre_entidad
	public void setNombreEntidad(String nombre_entidad)
	{
		this.nombre_entidad = nombre_entidad;
	}
	
	//Funcion que devuelve nombre_entidad
	public String getNombreEntidad()
	{
		return this.nombre_entidad;
	}
	
	//Funcion que establece cif
	public void setCif(String cif)
	{
		this.cif = cif;
	}
	
	//Funcion que devuelve cif
	public String getCif()
	{
		return this.cif;
	}
	
	//Funcion que establece fecha_alta
	public void setFechaAlta(Date fecha_alta)
	{
		this.fecha_alta = fecha_alta;
	}
	
	//Funcion que devuelve fecha_alta
	public Date getFechaAlta()
	{
		return this.fecha_alta;
	}
	
	/**
	 * Función que devuelve una lista de proveedores, para luego 
	 * mostrarlos en el combo de la pantalla de alta de factura
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public List<Proveedor> getListProveedores (Connection con)
	throws SQLException {

	String sentencia;
	//String evento;
	List<Proveedor> ListProveedores = new ArrayList<Proveedor>(); 
	ResultSet rsProveedores = null;
	
	Statement stmt = con.createStatement();
	try{
		sentencia = "SELECT id_proveedor,nombre_entidad FROM PROVEEDOR";
		
		rsProveedores = stmt.executeQuery(sentencia);
	
		while(rsProveedores.next()) {
			Proveedor objProveedor = new Proveedor();
			objProveedor.setIdProveedor(rsProveedores.getInt("id_proveedor"));
			objProveedor.setNombreEntidad(rsProveedores.getString("nombre_entidad"));
			ListProveedores.add(objProveedor);
		}
	} catch (SQLException e){
    		
    		try{
    			if (rsProveedores != null){
    				rsProveedores.close();
    			}
    			
    			if (stmt != null){
    				stmt.close();
    			}
    		
    		}catch(SQLException e1){
    			throw e1;
    		}
    		
    		throw e;	
    
	}	
	return ListProveedores;
}

	public String toString()
	{
	   return nombre_entidad;
	}
}
