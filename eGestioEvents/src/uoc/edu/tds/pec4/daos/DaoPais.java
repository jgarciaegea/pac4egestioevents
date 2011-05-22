package uoc.edu.tds.pec4.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import uoc.edu.tds.pec4.beans.Pais;

public class DaoPais extends DaoEntidad<Pais>{

	public DaoPais(Connection con) {
		super(con);
	}

	@Override
	public void insert(Pais objecte) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}

	@Override
	public List<Pais> select(Pais criteris) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Pais> lstPaises = new ArrayList<Pais>();
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT id_pais, nombre_pais ");
			sb.append("FROM pais ");
			sb.append("WHERE (1=1) ");
			if(criteris.getIdPais()!=null) sb.append("AND id_pais = ? ");
			if(criteris.getNombrePais() !=null) sb.append("AND nombre_pais = ? ");
			sb.append("order by nombre_pais");
			
			ps = con.prepareStatement(sb.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
			if(criteris.getIdPais()!=null) {ps.setInt(i, criteris.getIdPais()); i++;}
			if(criteris.getNombrePais()!=null) {ps.setString(i, criteris.getNombrePais()); i++;}
			
			rs = ps.executeQuery();
			while (rs.next()) {
				Pais pais = new Pais();
				pais.setIdPais(rs.getInt("id_pais"));
				pais.setNombrePais(rs.getString("nombre_pais"));
				lstPaises.add(pais);
			}		
			return (lstPaises.isEmpty() || lstPaises.size() == 0) ? null:lstPaises;
		}catch(Exception e){
			throw new Exception(e.getMessage());
		} finally {
			close(ps,rs);
		}
	}

	@Override
	public void update(Pais objecte) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}

	@Override
	public void delete(Pais criteris) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}

}
