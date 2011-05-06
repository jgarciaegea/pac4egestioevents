package uoc.edu.tds.pec4.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import uoc.edu.tds.pec4.beans.TipoDocumento;

public class DaoTipoDocumento extends DaoEntidad<TipoDocumento>{

	public DaoTipoDocumento(Connection con) {
		super(con);
	}

	@Override
	public void insert(TipoDocumento objecte) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}

	@Override
	public List<TipoDocumento> select(TipoDocumento criteris) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<TipoDocumento> lstTiposDocumentos = new ArrayList<TipoDocumento>();
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT id_tipo_documento, descripcion_documento ");
			sb.append("FROM TIPODOCUMENTO ");
			sb.append("WHERE (1=1) ");
			if(criteris.getIdTipoDocumento()!=null) sb.append("AND id_tipo_documento = ? ");
			if(criteris.getDescripcionDocumento() !=null) sb.append("AND descripcion_documento = ? ");
			
			ps = con.prepareStatement(sb.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
			if(criteris.getIdTipoDocumento()!=null) {ps.setInt(i, criteris.getIdTipoDocumento()); i++;}
			if(criteris.getDescripcionDocumento()!=null) {ps.setString(i, criteris.getDescripcionDocumento()); i++;}
			
			rs = ps.executeQuery();
			while (rs.next()) {
				TipoDocumento tipoDocumento = new TipoDocumento();
				tipoDocumento.setIdTipoDocumento(rs.getInt("id_tipo_documento"));
				tipoDocumento.setDescripcionDocumento(rs.getString("descripcion_documento"));
				lstTiposDocumentos.add(tipoDocumento);
			}		
			return (lstTiposDocumentos.isEmpty() || lstTiposDocumentos.size() == 0) ? null:lstTiposDocumentos;
		}catch(Exception e){
			throw new Exception(e.getMessage());
		} finally {
			close(ps,rs);
		}
	}

	@Override
	public void update(TipoDocumento objecte) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}

	@Override
	public void delete(TipoDocumento criteris) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}

}
