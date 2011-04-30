package edu.uoc.tds.comun;

import java.sql.*;
import javax.swing.table.*;
import java.util.List;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class GastosPorEvento {

	private int id_evento;
	private int id_proveedor;
	private int id_concepto;
	private String nombre_entidad;
	private String descripcion;
	private String concepto_gasto;
	
	private StringBuffer sql = new StringBuffer();
	private ResultSet rs = null;
	private Statement st = null;
	
	private StringBuffer sql2 = new StringBuffer();
	private PreparedStatement pSt = null;
	private ResultSet rs2 = null;
	
	private StringBuffer sql3 = new StringBuffer();
	private PreparedStatement pSt2 = null;
	private ResultSet rs3 = null;
	
	private DefaultTableModel model;
	
	/**
	 * Constructor mediante un evento concreto
	 * @param evento
	 */
	public GastosPorEvento(String evento)
	{
		this.descripcion = evento;
	}
	
	/**
	 * Constructor mediante evento y proveedor
	 * @param evento
	 * @param proveedor
	 */
	public GastosPorEvento(String evento, String proveedor)
	{
		this.descripcion = evento;
		this.nombre_entidad = proveedor;
	}
	
	/**
	 * Lista los gastos de un evento de todos los proveedores
	 * @param conn
	 * @throws SQLException
	 */
	public DefaultTableModel ListarEv(Connection conn) throws SQLException
	{
		model = new DefaultTableModel();
		model.addColumn("Fecha valor");
		model.addColumn("Proveedor");
		model.addColumn("Concepto");
		model.addColumn("Descripcion");
		model.addColumn("Importe");
		
		int id_proveedor = 0;
		String proveedor = "";
		
		try
		{
			sql.append("select distinct F.fecha_valor, F.id_proveedor, F.id_concepto, F.descripcion, F.importe from factura F, evento E," +
					"concepto_gasto C");
			sql.append(" where F.id_evento = E.id_evento");
			sql.append(" and F.id_concepto = C.id_concepto");
			sql.append(" and E.descripcion = '" + descripcion + "';");
			
			sql2.append("select nombre_entidad from proveedor where id_proveedor=?;");
			
			sql3.append("select descripcion from concepto_gasto where id_concepto=?;");
			
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			
			pSt = conn.prepareStatement(sql2.toString());
			pSt2 = conn.prepareStatement(sql3.toString());
			
			while(rs.next())
			{
				id_proveedor = rs.getInt(2);
				id_concepto = rs.getInt(3);
				
				//Adquirir nombre del proveedor a partir de su id
				pSt.clearParameters();				
				pSt.setInt(1, id_proveedor);
				rs2 = pSt.executeQuery();
				rs2.next();
				proveedor = rs2.getString(1);
				
				//Adquirir descripcion del concepto a partir de su id
				pSt2.clearParameters();
				pSt2.setInt(1, id_concepto);
				rs3 = pSt2.executeQuery();
				rs3.next();
				concepto_gasto = rs3.getString(1);
				
				Object[] filas = new Object[5];
				filas[0] = rs.getDate(1);
				filas[1] = proveedor;
				filas[2] = concepto_gasto;
				filas[3] = rs.getString(4);
				filas[4] = rs.getFloat(5);
				model.addRow(filas);
			}
			
			//Cierre de los ResultSet
			rs.close();
			//rs2.close();
			//rs3.close();
			
			//cierre de los Statement
			st.close();
			pSt.close();
			pSt2.close();
		}
		catch (SQLException se)
		{
			try
			{
				if (rs != null)
					rs.close();
				
				if (rs2 != null)
					rs2.close();
				
				if (rs3 != null)
					rs3.close();
				
				if (st != null)
					st.close();
				
				if (pSt != null)
					pSt.close();
				
				if (pSt2 != null)
					pSt2.close();
				
			}
			catch (SQLException se2)
			{
				se2.printStackTrace();
			}
			se.printStackTrace();
		}
		
		return model;
	}
	
	/**
	 * Lista los gastos de un evento y proveedor concretos
	 * @param conn
	 * @throws SQLException
	 */
	public DefaultTableModel ListarEvProv(Connection conn) throws SQLException
	{
		model = new DefaultTableModel();
		model.addColumn("Evento");
		model.addColumn("Fecha valor");
		model.addColumn("Proveedor");
		model.addColumn("Descripcion");
		model.addColumn("Importe");
		
		int id_proveedor = 0;
		String proveedor = "";
		
		try
		{
			sql.append("select distinct F.fecha_valor, F.id_proveedor, F.id_concepto, F.descripcion, F.importe from factura F, evento E," +
					"concepto_gasto C");
			sql.append(" where F.id_evento = E.id_evento");
			sql.append(" and F.id_concepto = C.id_concepto");
			sql.append(" and E.descripcion = '" + descripcion + "';");
			
			sql2.append("select nombre_entidad from proveedor where id_proveedor=?;");
			
			sql3.append("select descripcion from concepto_gasto where id_concepto=?;");
			
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			
			pSt = conn.prepareStatement(sql2.toString());
			pSt2 = conn.prepareStatement(sql3.toString());
			
			while(rs.next())
			{
				id_proveedor = rs.getInt(2);
				id_concepto = rs.getInt(3);
				
				//Adquirir nombre del proveedor a partir de su id
				pSt.clearParameters();				
				pSt.setInt(1, id_proveedor);
				rs2 = pSt.executeQuery();
				rs2.next();
				proveedor = rs2.getString(1);
				
				if (proveedor.compareTo(nombre_entidad) == 0)
				{
					//Adquirir descripcion del concepto a partir de su id
					pSt2.clearParameters();
					pSt2.setInt(1, id_concepto);
					rs3 = pSt2.executeQuery();
					rs3.next();
					concepto_gasto = rs3.getString(1);
				
					Object[] filas = new Object[5];
					filas[0] = rs.getDate(1);
					filas[1] = proveedor;
					filas[2] = concepto_gasto;
					filas[3] = rs.getString(4);
					filas[4] = rs.getFloat(5);
					model.addRow(filas);
				}
			}
			
			//Cierre de los ResultSet
			rs.close();
			rs2.close();
			rs3.close();
			
			//cierre de los Statement
			st.close();
			pSt.close();
			pSt2.close();
		}
		catch (SQLException se)
		{
			try
			{
				if (rs != null)
					rs.close();
				
				if (rs2 != null)
					rs2.close();
				
				if (rs3 != null)
					rs3.close();
				
				if (st != null)
					st.close();
				
				if (pSt != null)
					pSt.close();
				
				if (pSt2 != null)
					pSt2.close();
				
			}
			catch (SQLException se2)
			{
				se2.printStackTrace();
			}
			se.printStackTrace();
		}
		
		return model;

	}
	

}
