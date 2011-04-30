package edu.uoc.tds.comun;

import java.awt.Container;
import java.sql.*;
import java.util.Date;
import edu.uoc.tds.gui.*;

/**
 * Clase que representa la factura.
 *
 * @author   Jonathan Garcia Fernandez
 * @version  
 */
public class Factura
{
	//Códigos de error recibidos de la BD
	private static final int PK_ERROR = -268;
	private static final int FK_ERROR = -691;
	private static final String FACTURA_FK_EVENTO = "factura_id_evento_fkey";
    private static final String FACTURA_FK_PROVEEDOR = "factura_id_proveedor_fkey";
    private static final String FACTURA_FK_CONCEPTO = "factura_id_concepto_fkey";
	
	private int id_factura;
	private int id_evento;
	private int id_concepto;
	private int id_proveedor;
	private String descripcion;
	private Date fecha_valor;
	private double importe;
	private Date fecha_alta;
	
	Connection con;

   /**
    * Constructores 
    */
   public Factura(int id_evento,int id_concepto,int id_proveedor,String descripcion,java.sql.Date fecha_valor,double importe)
   {
		this.id_evento=id_evento;
		this.id_concepto=id_concepto;
		this.id_proveedor=id_proveedor;
		this.descripcion=descripcion;
		this.fecha_valor=fecha_valor;
		this.importe=importe;
		this.fecha_alta=getCurrentJavaSqlDate();
   }

   public Factura()
   {
   }
   
     /**
	 * Método que confirma los cambios realizados en la BD
	 * @param con
	 */
   private void commit() {
		try {
		    con.commit();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	}

	
	/**
	 * Método que anula los cambios realizados en la BD
	 * @param con
	 */
	private void rollback() {
		try {
		    con.rollback();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	}

   /**
	 * Inserta la factura en la base de datos de postrgress a list of working shifts (Compositions) to the database
	 * @param con Database connection
	 * @throws SQLException Error during the insertion
	 */
  public void InsertFactura(Connection con)
  	throws SQLException {
	
	PreparedStatement pStmt = null;
		
	//Creación de la sentencia principal con 5 campos 
	String str = "INSERT INTO FACTURA(id_evento,id_concepto,id_proveedor,descripcion,fecha_valor,importe,fecha_alta) VALUES (?, ?, ?, ?, ?, ?, ?)";
	
	pStmt = con.prepareStatement(str);
	
	con.setAutoCommit(false);		
	//Añade valores a los parametros y	ejecuta la sentencia SQL para añadir la factura a la BD
	pStmt.clearParameters();
	pStmt.setInt(1, this.id_evento);   
	pStmt.setInt(2, this.id_concepto);
	pStmt.setInt(3, this.id_proveedor);
	pStmt.setString(4, this.descripcion);
	java.sql.Date date = new java.sql.Date(this.fecha_valor.getTime());
	pStmt.setDate(5, date);
	pStmt.setDouble(6, this.importe);
	java.sql.Date date1 = new java.sql.Date(this.fecha_alta.getTime());
	pStmt.setDate(7,date1); //Fecha actual del sistema
	
	pStmt.executeUpdate();
	con.commit();
	con.setAutoCommit(true);
	pStmt.close();
	}
     
   private String excepcionSQL (SQLException e) {

		String missatge = e.getMessage(); 
        
		rollback();
		//Error violación de clave primaria
        if (e.getErrorCode() == PK_ERROR)
        	return ("ERROR: Factura existente: " + missatge);

        //Error de integridad referencial
        else if (e.getErrorCode() == FK_ERROR) 
        {     
        	
        	if (missatge != null)
            {
            	//Concretamos el campo al que se refiere el error
        		if (missatge.indexOf(FACTURA_FK_EVENTO) != -1)
            		return ("ERROR: Evento inexistente: "+ e.getMessage());
            	else if (missatge.indexOf(FACTURA_FK_PROVEEDOR) != -1)
            		return ("ERROR: Proveedor inexistente: "+ e.getMessage());            
            	else if (missatge.indexOf(FACTURA_FK_CONCEPTO) != -1)
            		return ("ERROR: Concepto inexistente: "+ e.getMessage());            
            	}
        }
        return ("ERROR (" + e.getClass() + "): " + e.getMessage());
	}

   public static java.sql.Date getCurrentJavaSqlDate() {
	    java.util.Date today = new java.util.Date();
	    return new java.sql.Date(today.getTime());
	  }
   
   
   public void PantallaFactura(Connection con,Container PantallaPrincipal) throws SQLException{
	   Pantalla pantalla=new Pantalla();
	   pantalla.setConnection(con);
	   pantalla.GuiFactura(PantallaPrincipal);
   }
   
     
    /**
	 * Main method.
	 * @param args Method parameters
     * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		Pantalla GuiFactura = new Pantalla();
		
		Factura fra = new Factura();
		Connection con = null;
		
		
		// TODO Get connection to the database
		
		GestorBBDD con_bd = new GestorBBDD();   
		boolean error = false;

		boolean conexion = false;
		//Conecta a la BD, Si no hay conexión se aborta
		if ((conexion  = con_bd.conectaBD()) == false ){
			System.exit(-1);
		}
		
		con = con_bd.conexion;
		
		GuiFactura.setConnection(con);
		//Finaliza la connexión
		con_bd.closeConnection(con);
	}
}
