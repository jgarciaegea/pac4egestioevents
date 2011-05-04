package uoc.edu.tds.pec4.daos;

import java.sql.Connection;
import java.util.List;

import uoc.edu.tds.pec4.beans.BeanInterface;

public abstract class DaoEntidad<O extends BeanInterface> extends DaoBasic {
	
	public DaoEntidad(Connection con) {
		super(con);
	}
	
	public abstract void insert(O objecte) throws Exception;
	public abstract List<O> select(O criteris) throws Exception;
	public abstract void update(O objecte) throws Exception;
	public abstract void deleteById(O criteris) throws Exception;
}
