package uoc.edu.tds.pec4.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import uoc.edu.tds.pec4.beans.TipoTelefono;

public class DaoTipoTelefono extends DaoEntidad<TipoTelefono>{

	public DaoTipoTelefono(Connection con) {
		super(con);
	}

	@Override
	public void insert(TipoTelefono objecte) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}

	@Override
	public List<TipoTelefono> select(TipoTelefono criteris) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<TipoTelefono> lstTipoTelefonos = new ArrayList<TipoTelefono>();
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT id_tipo_telefono, descripcion ");
			sb.append("FROM tipotelefono ");
			sb.append("WHERE (1=1) ");
			if(criteris.getIdTipoTelefono()!=null) sb.append("AND id_tipo_telefono = ? ");
			if(criteris.getDescripcion() !=null) sb.append("AND descripcion = ? ");
			
			ps = con.prepareStatement(sb.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
			if(criteris.getIdTipoTelefono()!=null) {ps.setInt(i, criteris.getIdTipoTelefono()); i++;}
			if(criteris.getDescripcion()!=null) {ps.setString(i, criteris.getDescripcion()); i++;}
			
			rs = ps.executeQuery();
			while (rs.next()) {
				TipoTelefono tipoTelefono = new TipoTelefono();
				tipoTelefono.setIdTipoTelefono(rs.getInt("id_tipo_telefono"));
				tipoTelefono.setDescripcion(rs.getString("descripcion"));
				lstTipoTelefonos.add(tipoTelefono);
			}		
			return (lstTipoTelefonos.isEmpty() || lstTipoTelefonos.size() == 0) ? null:lstTipoTelefonos;
		}catch(Exception e){
			throw new Exception(e.getMessage());
		} finally {
			close(ps,rs);
		}
	}

	@Override
	public void update(TipoTelefono objecte) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}

	@Override
	public void delete(TipoTelefono criteris) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}

}
