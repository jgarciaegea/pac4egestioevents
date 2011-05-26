/**
 * 
 */
package uoc.edu.tds.pec4.gestores;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


import uoc.edu.tds.pec4.beans.TipoEventoRol;
import uoc.edu.tds.pec4.daos.DaoTipoEventoRol;
import uoc.edu.tds.pec4.dtos.DTOTipoEventoRol;



/**
 * @author ML019882
 *
 */
public class GestorTipoEventoRol extends GestorEntidad<DTOTipoEventoRol> {

	public GestorTipoEventoRol(Connection connection) throws Exception {
		super(connection);

	}

	@Override
	public void insertaEntidad(DTOTipoEventoRol newobject) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<DTOTipoEventoRol> consultaEntidades(DTOTipoEventoRol criteris)throws Exception {
		try{
			DaoTipoEventoRol dao = new DaoTipoEventoRol(connection);
			List<TipoEventoRol> lisTipoEventoRol = dao.select(criteris.getTipoEventoRol());
			if(lisTipoEventoRol != null && lisTipoEventoRol.size() > 0){
				System.out.println("hay datos de tipo evento roll.....");
				List<DTOTipoEventoRol> lstDTOTipoEventoRol = new ArrayList<DTOTipoEventoRol>();
				for(TipoEventoRol tipoEventorol : lisTipoEventoRol){
					DTOTipoEventoRol dtoTipoEventoRol = new DTOTipoEventoRol();
					dtoTipoEventoRol.setTipoEventoRol(tipoEventorol);
					//rellenaObjeto(dtoEvento);
					lstDTOTipoEventoRol.add(dtoTipoEventoRol);
				}
				return lstDTOTipoEventoRol;
			}
		}catch(Exception e){
			throw new Exception();
		}
		return null;
	}

	@Override
	public DTOTipoEventoRol consultaEntidad(DTOTipoEventoRol criteris)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modificaEntidad(DTOTipoEventoRol criteris) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminaEntidad(DTOTipoEventoRol criteris) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
