package uoc.edu.tds.pec4.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import uoc.edu.tds.pec4.beans.DocumentoIdentificacion;

public class DaoDocumentoIdentificacion extends DaoEntidad<DocumentoIdentificacion>{

	public DaoDocumentoIdentificacion(Connection con) {
		super(con);
	}

	@Override
	public void insert(DocumentoIdentificacion objecte) throws Exception {
		throw new UnsupportedOperationException("M�todo no implementado");
	}

	@Override
	public List<DocumentoIdentificacion> select( DocumentoIdentificacion criteris) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<DocumentoIdentificacion> lstDocumentoIdentificacion = new ArrayList<DocumentoIdentificacion>();
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT id_documento_identificacion, id_tipo_documento, id_pais, numero_documento ");
			sb.append("FROM DOCUMENTOIDENTIFICACION ");
			sb.append("WHERE (1=1) ");
			if(criteris.getIdDocumentoIdentificacion()!=null) sb.append("AND id_documento_identificacion = ? ");
			if(criteris.getIdTipoDocumento() !=null) sb.append("AND id_tipo_documento = ? ");
			if(criteris.getIdPais() !=null) sb.append("AND id_pais = ? ");
			if(criteris.getNumeroDocumento() !=null) sb.append("AND numero_documento = ? ");
			
			ps = con.prepareStatement(sb.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
			if(criteris.getIdDocumentoIdentificacion()!=null) {ps.setInt(i, criteris.getIdDocumentoIdentificacion()); i++;}
			if(criteris.getIdTipoDocumento()!=null) {ps.setInt(i, criteris.getIdTipoDocumento()); i++;}
			if(criteris.getIdPais()!=null) {ps.setInt(i, criteris.getIdPais()); i++;}
			if(criteris.getNumeroDocumento()!=null) {ps.setString(i, criteris.getNumeroDocumento()); i++;}
			
			rs = ps.executeQuery();
			while (rs.next()) {
				DocumentoIdentificacion documentoIden = new DocumentoIdentificacion();
				documentoIden.setIdDocumentoIdentificacion(rs.getInt("id_documento_identificacion"));
				documentoIden.setIdTipoDocumento(rs.getInt("id_tipo_documento"));
				documentoIden.setIdPais(rs.getInt("id_pais"));
				documentoIden.setNumeroDocumento(rs.getString("numero_documento"));
				lstDocumentoIdentificacion.add(documentoIden);
			}		
			return (lstDocumentoIdentificacion.isEmpty() || lstDocumentoIdentificacion.size() == 0) ? null:lstDocumentoIdentificacion;
		}catch(Exception e){
			throw new Exception(e.getMessage());
		} finally {
			close(ps,rs);
		}
	}

	@Override
	public void update(DocumentoIdentificacion objecte) throws Exception {
		throw new UnsupportedOperationException("M�todo no implementado");
	}

	@Override
	public void delete(DocumentoIdentificacion criteris) throws Exception {
		throw new UnsupportedOperationException("M�todo no implementado");
	}

}