package uoc.edu.tds.pec4.gestores;

import java.sql.Connection;
import java.util.List;

import uoc.edu.tds.pec4.beans.Evento;
import uoc.edu.tds.pec4.daos.DaoEvento;

public class GestorEvento extends GestorEntidad<Evento>{

	public GestorEvento(Connection connection) throws Exception {
		super(connection);
	}

	@Override
	public List<Evento> consultaEntidades(Evento criteris) throws Exception {
		try{
			DaoEvento dao = new DaoEvento(connection);
			List<Evento> lstEvento = dao.select(criteris);
			if(lstEvento != null && lstEvento.size() > 0){
				return lstEvento;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insertaEntidad(Evento newobject) {
		try{
			DaoEvento dao = new DaoEvento(connection);
			dao.insert(newobject);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void modificaEntidad(Evento criteris) throws Exception {
		try{
			DaoEvento dao = new DaoEvento(connection);
			dao.update(criteris);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void eliminaEntidad(Evento criteris) throws Exception {
		try{
			DaoEvento dao = new DaoEvento(connection);
			dao.delete(criteris);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
