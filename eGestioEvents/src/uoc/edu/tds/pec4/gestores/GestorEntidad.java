package uoc.edu.tds.pec4.gestores;

import java.sql.Connection;
import java.util.List;

import uoc.edu.tds.pec4.beans.BeanInterface;

abstract class GestorEntidad<E extends BeanInterface> extends GestorDisco{

	public GestorEntidad(Connection connection) throws Exception {
		super(connection);
	}
	
	public abstract void insertaEntidad(E newobject);
	public abstract List<E> consultaEntidades(E criteris) throws Exception;
	public abstract void modificaEntidad(E criteris) throws Exception;
	public abstract void eliminaEntidad(E criteris) throws Exception;

}
