package edu.uoc.tds.comun;

import java.sql.*;
import javax.swing.table.*;

public class GastosPorConcepto {
	
	private String descripcion;
	private String nombre_entidad;
	private String descEvento;
	private int id_evento;
	private int id_proveedor;
	
	private DefaultTableModel model;
	
	/**
	 * 
	 * @param concepto
	 */
	public GastosPorConcepto(String concepto)
	{
		this.descripcion = concepto;
	}

	/**
	 * 
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public DefaultTableModel Listar(Connection conn) throws SQLException //antes void
	{
		model = new DefaultTableModel();
		model.addColumn("Evento");
		model.addColumn("Fecha valor");
		model.addColumn("Proveedor");
		model.addColumn("Descripcion");
		model.addColumn("Importe");
		
		StringBuffer sql = new StringBuffer();
		ResultSet rs = null;
		Statement st = null;
		
		StringBuffer sql2 = new StringBuffer();
		PreparedStatement pSt = null;
		ResultSet rs2 = null;
		
		StringBuffer sql3 = new StringBuffer();
		PreparedStatement pSt2 = null;
		ResultSet rs3 = null;
		
		try
		{
			sql.append("select distinct F.id_evento, F.fecha_valor, F.id_proveedor, F.descripcion, F.importe from factura F, concepto_gasto C");
			sql.append(" where F.id_concepto = C.id_concepto");
			sql.append(" and C.descripcion = '" + descripcion + "';");
			
			sql2.append("select descripcion from evento where id_evento=?;");
			
			sql3.append("select nombre_entidad from proveedor where id_proveedor=?;");
			
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			
			pSt = conn.prepareStatement(sql2.toString());
			pSt2 = conn.prepareStatement(sql3.toString());
			
			while(rs.next())
			{
				id_evento = rs.getInt(1);
				id_proveedor = rs.getInt(3);
				
				//Adquirir nombre del proveedor a partir de su id
				pSt.clearParameters();				
				pSt.setInt(1, id_evento);
				rs2 = pSt.executeQuery();
				rs2.next();
				descEvento = rs2.getString(1);
				
				//Adquirir descripcion del concepto a partir de su id
				pSt2.clearParameters();
				pSt2.setInt(1, id_proveedor);
				rs3 = pSt2.executeQuery();
				rs3.next();
				nombre_entidad = rs3.getString(1);
				
				Object[] filas = new Object[5];
				filas[0] = descEvento;
				filas[1] = rs.getDate(2);
				filas[2] = nombre_entidad;
				filas[3] = rs.getString(4);
				filas[4] = rs.getFloat(5);
				model.addRow(filas);
				
				
			}
			
			//Cierre de los ResultSet
			rs.close();
//			rs2.close();
//			rs3.close();
			
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
