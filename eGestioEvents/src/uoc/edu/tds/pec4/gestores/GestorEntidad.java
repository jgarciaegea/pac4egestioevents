package uoc.edu.tds.pec4.gestores;

import java.sql.Connection;
import java.util.List;

import uoc.edu.tds.pec4.dtos.DTOInterface;

abstract class GestorEntidad<E extends DTOInterface> extends GestorDisco{

	public GestorEntidad(Connection connection) throws Exception {
		super(connection);
	}
	
	public abstract void insertaEntidad(E newobject);
	public abstract List<E> consultaEntidades(E criteris) throws Exception;
	public abstract E consultaEntidad(E criteris) throws Exception;
	public abstract void modificaEntidad(E criteris) throws Exception;
	public abstract void eliminaEntidad(E criteris) throws Exception;

}
