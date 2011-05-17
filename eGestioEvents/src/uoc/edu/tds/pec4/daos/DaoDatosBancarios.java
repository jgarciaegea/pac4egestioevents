package uoc.edu.tds.pec4.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import uoc.edu.tds.pec4.beans.DatosBancarios;

public class DaoDatosBancarios  extends DaoEntidad<DatosBancarios>{

	public DaoDatosBancarios(Connection con) {
		super(con);
	}

	@Override
	public void insert(DatosBancarios objecte) throws Exception {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("INSERT INTO datosbancarios (banco, sucursal, dc, cc, fecha_alta, estado, fecha_estado, motivo_estado) " +
			" VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setInt(1, objecte.getBanco());
			ps.setInt(2, objecte.getSucursal());
			ps.setInt(3, objecte.getDc());
			ps.setInt(4, objecte.getCc());
			ps.setDate(5, new java.sql.Date(System.currentTimeMillis()));
			ps.setInt(6, objecte.getEstado());
			ps.setDate(7,objecte.getFechaEstado());
			ps.setString(8, objecte.getMotivoEstado());
			ps.executeUpdate();
        } catch (SQLException e) {
        	throw new Exception(e.getMessage());
        } finally {
        	close(ps);
        }		
	}
	
	public Integer insertReturnId(DatosBancarios objecte) throws Exception {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			 this.insert(objecte);
			 stmt = con.createStatement();
			 String query = "select currval('seq_datosbancarios')";
			 rs = stmt.executeQuery(query);
			 if ( rs.next() ) {
			    System.out.println( "Id datosbancarios = " + rs.getInt(1) );
			 }
        } catch (SQLException e) {
        	throw new Exception(e.getMessage());
        } finally {
        	close(stmt,rs);
        }	
        return null;
	}

	@Override
	public List<DatosBancarios> select(DatosBancarios criteris) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<DatosBancarios> lstDatosBancarios = new ArrayList<DatosBancarios>();
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT id_datos_bancarios, banco, sucursal, dc, cc, fecha_alta, estado, fecha_estado, motivo_estado ");
			sb.append("FROM DATOSBANCARIOS ");
			sb.append("WHERE (1=1) ");
			
			if(criteris.getIdDatosBancarios()!=null) sb.append("AND id_datos_bancarios = ? ");
			if(criteris.getBanco() !=null) sb.append("AND banco = ? ");
			if(criteris.getSucursal() !=null) sb.append("AND sucursal = ? ");
			if(criteris.getDc() !=null) sb.append("AND dc = ? ");
			if(criteris.getCc()  !=null) sb.append("AND cc = ? ");
			if(criteris.getFechaAlta() !=null) sb.append("AND fecha_alta = ? ");
			if(criteris.getEstado() !=null) sb.append("AND estado = ? ");
			if(criteris.getFechaEstado() !=null) sb.append("AND fecha_estado = ? ");
			if(criteris.getMotivoEstado() !=null) sb.append("AND motivo_estado = ? ");
			
			ps = con.prepareStatement(sb.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
			if(criteris.getIdDatosBancarios()!=null) {ps.setInt(i, criteris.getIdDatosBancarios()); i++;}
			if(criteris.getBanco()!=null) {ps.setInt(i, criteris.getBanco()); i++;}
			if(criteris.getSucursal()!=null) {ps.setInt(i, criteris.getSucursal()); i++;}
			if(criteris.getDc()!=null) {ps.setInt(i, criteris.getDc()); i++;}
			if(criteris.getCc()!=null) {ps.setInt(i, criteris.getCc()); i++;}
			if(criteris.getFechaAlta()!=null) {ps.setDate(i, criteris.getFechaAlta()); i++;}
			if(criteris.getEstado()!=null) {ps.setInt(i, criteris.getEstado()); i++;}
			if(criteris.getFechaEstado()!=null) {ps.setDate(i, criteris.getFechaEstado()); i++;}
			if(criteris.getMotivoEstado()!=null) {ps.setString(i, criteris.getMotivoEstado()); i++;}
			
			rs = ps.executeQuery();
			while (rs.next()) {
				DatosBancarios datosBancarios = new DatosBancarios();
				datosBancarios.setIdDatosBancarios(rs.getInt("id_datos_bancarios"));
				datosBancarios.setBanco(rs.getInt("banco"));
				datosBancarios.setSucursal(rs.getInt("sucursal"));
				datosBancarios.setDc(rs.getInt("dc"));
				datosBancarios.setCc(rs.getInt("cc"));
				datosBancarios.setFechaAlta(rs.getDate("fecha_alta"));
				datosBancarios.setEstado(rs.getInt("estado"));
				datosBancarios.setFechaEstado(rs.getDate("fecha_estado"));
				datosBancarios.setMotivoEstado(rs.getString("motivo_estado"));
				lstDatosBancarios.add(datosBancarios);
			}		
			return (lstDatosBancarios.isEmpty() || lstDatosBancarios.size() == 0) ? null:lstDatosBancarios;
		}catch(Exception e){
			throw new Exception(e.getMessage());
		} finally {
			close(ps,rs);
		}
	}

	@Override
	public void update(DatosBancarios objecte) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}

	@Override
	public void delete(DatosBancarios criteris) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}

}
