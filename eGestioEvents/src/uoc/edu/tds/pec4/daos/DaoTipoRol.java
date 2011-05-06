package uoc.edu.tds.pec4.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import uoc.edu.tds.pec4.beans.TipoRol;

public class DaoTipoRol extends DaoEntidad<TipoRol>{

	public DaoTipoRol(Connection con) {
		super(con);
	}

	@Override
	public void insert(TipoRol objecte) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}

	@Override
	public List<TipoRol> select(TipoRol criteris) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<TipoRol> lstRoles = new ArrayList<TipoRol>();
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT id_rol, descripcion ");
			sb.append("FROM tiporol ");
			sb.append("WHERE (1=1) ");
			if(criteris.getIdRol()!=null) sb.append("AND id_rol = ? ");
			if(criteris.getDescripcion() !=null) sb.append("AND descripcion = ? ");
			
			ps = con.prepareStatement(sb.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
			if(criteris.getIdRol()!=null) {ps.setInt(i, criteris.getIdRol()); i++;}
			if(criteris.getDescripcion()!=null) {ps.setString(i, criteris.getDescripcion()); i++;}
			
			rs = ps.executeQuery();
			while (rs.next()) {
				TipoRol tipoRol = new TipoRol();
				tipoRol.setIdRol(rs.getInt("id_rol"));
				tipoRol.setDescripcion(rs.getString("descripcion"));
				lstRoles.add(tipoRol);
			}		
			return (lstRoles.isEmpty() || lstRoles.size() == 0) ? null:lstRoles;
		}catch(Exception e){
			throw new Exception(e.getMessage());
		} finally {
			close(ps,rs);
		}
	}

	@Override
	public void update(TipoRol objecte) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}

	@Override
	public void delete(TipoRol criteris) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}

}
