package edu.uoc.tds.comun;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.io.*;

public class Evento implements Serializable {

	//Variables
	private static final long serialVersionID = 1L;

	private int id_evento;
	private String descripcion;
	private Date fecha_inicio;
	private Date fecha_fin;
	private Date fecha_alta;
	
	//Constructor
	public Evento(int id_evento, String descripcion, Date fecha_inicio, Date fecha_fin, Date fecha_alta)
	{
		this.id_evento = id_evento;
		this.descripcion = descripcion;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.fecha_alta = fecha_alta;
		
	}
	
	public Evento (){
		
	}
	//Función que establece id_evento
	public void setIdEvento(int id_evento)
	{
		this.id_evento = id_evento;
	}
	
	//Función que devuelve id_evento
	public int getIdEvento()
	{
		return this.id_evento;
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
	
	//Función que establece fecha_inio
	public void setFechaInicio(Date fecha_inicio)
	{
		this.fecha_inicio = fecha_inicio;
	}
	
	//Función que devuelve fecha_inicio
	public Date getFechaInicio()
	{
		return this.fecha_inicio;
	}
	
	//Función que establece fecha_fin
	public void setFechaFin(Date fecha_fin)
	{
		this.fecha_fin = fecha_fin;
	}
	
	//Función que devuelve fecha_fin
	public Date getFechaFin()
	{
		return fecha_fin;
	}
	
	//Función que establece fecha_alta
	public void setFechaAlta(Date fecha_alta)
	{
		this.fecha_alta = fecha_alta;
	}
	
	//Función que devuelve fecha_alta
	public Date getFechaAlta()
	{
		return fecha_alta;
	}
	
	/**
	 * Función que devuelve una lista de los eventos, para luego 
	 * mostrarlos en el combo de la pantalla de alta de factura
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public List<Evento> getListEventos (Connection con)
	throws SQLException {

	String sentencia;
	//String evento;
	List<Evento> ListEventos = new ArrayList<Evento>(); 
	ResultSet rsEventos = null;
	
	Statement stmt = con.createStatement();
	try{
		sentencia = "SELECT id_evento,descripcion FROM evento";
		
		rsEventos = stmt.executeQuery(sentencia);
	
		while(rsEventos.next()) {
			Evento objevento = new Evento();
			objevento.setIdEvento(rsEventos.getInt("id_evento"));
			objevento.setDescripcion(rsEventos.getString("descripcion"));
			//ListEventos.add(Integer.parseInt(rsEventos.getString("id_evento")),evento);
			ListEventos.add(objevento);
		}
	} catch (SQLException e){
    		
    		try{
    			if (rsEventos != null){
    				rsEventos.close();
    			}
    			
    			if (stmt != null){
    				
    				stmt.close();
    			
    			}
    		
    		}catch(SQLException e1){
    		
    			throw e1;
    		
    		}
    		
    		throw e;	
    
	}	
	return ListEventos;
}

	public String toString()
	{
	   return descripcion;
	}

}
