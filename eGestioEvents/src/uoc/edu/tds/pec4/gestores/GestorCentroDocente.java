package uoc.edu.tds.pec4.gestores;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import uoc.edu.tds.pec4.beans.CentroDocente;
import uoc.edu.tds.pec4.daos.DaoCentroDocente;
import uoc.edu.tds.pec4.dtos.DTOCentroDocente;
import uoc.edu.tds.pec4.dtos.DTOContacto;

public class GestorCentroDocente extends GestorEntidad<DTOCentroDocente>{

	public GestorCentroDocente(Connection connection) throws Exception {
		super(connection);
	}

	@Override
	public void insertaEntidad(DTOCentroDocente newobject) {
		throw new UnsupportedOperationException("Método no implementado");
	}

	@Override
	public List<DTOCentroDocente> consultaEntidades(DTOCentroDocente criteris) throws Exception {
		try{
			DaoCentroDocente dao = new DaoCentroDocente(connection);
			List<CentroDocente> lstCentrosDocentes = dao.select(criteris.getCentroDocente());
			if(lstCentrosDocentes != null && lstCentrosDocentes.size() > 0){
				List<DTOCentroDocente> lstCentroDoc = new ArrayList<DTOCentroDocente>();
				for(CentroDocente centroDocente : lstCentrosDocentes){
					
					//Añadimos centro docente
					DTOCentroDocente dtoCen = new DTOCentroDocente();
					dtoCen.setCentroDocente(centroDocente);
					
					//Añadimos contacto
					GestorContacto gestorContacto = new GestorContacto(connection);
					DTOContacto dtoContacto = gestorContacto.consultaEntidadById(centroDocente.getIdContacto());
					if(dtoContacto != null) dtoCen.setDtoContacto(dtoContacto);
					lstCentroDoc.add(dtoCen);
				}
				return lstCentroDoc;
			}
		}catch(Exception e){
			throw new Exception();
		}
		return null;
	}

	@Override
	public DTOCentroDocente consultaEntidad(DTOCentroDocente criteris) throws Exception {
		try{
			List<DTOCentroDocente> lstCentroDoc = this.consultaEntidades(criteris);
			if(lstCentroDoc != null && lstCentroDoc.size() > 0){
				return lstCentroDoc.get(0);
			}
		}catch(Exception e){
			throw e;
		}
		return null;
	}

	@Override
	public void modificaEntidad(DTOCentroDocente criteris) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}

	@Override
	public void eliminaEntidad(DTOCentroDocente criteris) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}

}
