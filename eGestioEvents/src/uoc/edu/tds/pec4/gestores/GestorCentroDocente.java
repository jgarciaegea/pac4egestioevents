package uoc.edu.tds.pec4.gestores;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import uoc.edu.tds.pec4.beans.CentroDocente;
import uoc.edu.tds.pec4.daos.DaoCentroDocente;
import uoc.edu.tds.pec4.dtos.DTOCentroDocente;
import uoc.edu.tds.pec4.dtos.DTOCentroDocenteConsulta;
import uoc.edu.tds.pec4.dtos.DTOContacto;
import uoc.edu.tds.pec4.dtos.DTOTelefono;
import uoc.edu.tds.pec4.dtos.DTOUniversidad;

public class GestorCentroDocente extends GestorEntidad<DTOCentroDocente>{

	public GestorCentroDocente(Connection connection) throws Exception {
		super(connection);
	}

	@Override
	public void insertaEntidad(DTOCentroDocente newobject) throws Exception {
		try {
			DaoCentroDocente dao = new DaoCentroDocente(connection);
			dao.insert(newobject.getCentroDocente());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
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
					
					GestorUniversidad gestorUniversidad = new GestorUniversidad(connection);
					DTOUniversidad dtoUniversidad = gestorUniversidad.consultaEntidadById(centroDocente.getIdUniversidad());
					if(dtoUniversidad != null) dtoCen.setDtoUniversidad(dtoUniversidad);
					
					//Añadimos contacto
					if(centroDocente.getIdContacto() != null){
						
						GestorContacto gestorContacto = new GestorContacto(connection);
						DTOContacto dtoContacto = gestorContacto.consultaEntidadById(centroDocente.getIdContacto());
						if(dtoContacto != null) dtoCen.setDtoContacto(dtoContacto);
						
						GestorTelefono gestorTelefono = new GestorTelefono(connection);
						DTOTelefono dtoTelefono = gestorTelefono.consultaEntidadById(centroDocente.getIdContacto());
						if(dtoTelefono != null) dtoContacto.setDtoTelefono(dtoTelefono);
					}
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
	
	public List<DTOCentroDocente> consultaEntidadesByView(DTOCentroDocenteConsulta criteris) throws Exception {
		try{
			
			DaoCentroDocente dao = new DaoCentroDocente(connection);		
			List<CentroDocente> lstCentroDocentes = dao.selectCentroDocenteSearch(criteris.getCentroDocenteView());
			if(lstCentroDocentes != null && lstCentroDocentes.size() > 0){
				List<DTOCentroDocente> lstCenDoc = new ArrayList<DTOCentroDocente>();
				for(CentroDocente cen : lstCentroDocentes){
					lstCenDoc.add(recuperaInfoComun(cen));
				}
				return lstCenDoc;
			}
		}catch(Exception e){
			throw new Exception();
		}
		return null;
	}
	
	public DTOCentroDocente consultaEntidadById(Integer idCentroDocente)  throws Exception {
		try{
			DTOCentroDocente dtoCentroDocente = new DTOCentroDocente();
			CentroDocente centroDocente = new CentroDocente();
			centroDocente.setIdCentro(idCentroDocente);
			dtoCentroDocente.setCentroDocente(centroDocente);
			return this.consultaEntidad(dtoCentroDocente);
		}catch(Exception e){
			throw e;
		}
	}

	@Override
	public void modificaEntidad(DTOCentroDocente criteris) throws Exception {
		try {
			DaoCentroDocente dao = new DaoCentroDocente(connection);
			dao.update(criteris.getCentroDocente());
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void eliminaEntidad(DTOCentroDocente criteris) throws Exception {
		try {
			DaoCentroDocente dao = new DaoCentroDocente(connection);
			dao.delete(criteris.getCentroDocente());
		} catch (Exception e) {
			throw new Exception();
		}
	}
	
	private DTOCentroDocente recuperaInfoComun(CentroDocente centroDocente) throws Exception{
		try{
			//Añadimos centro docente
			DTOCentroDocente dtoCen = new DTOCentroDocente();
			dtoCen.setCentroDocente(centroDocente);
			
			GestorUniversidad gestorUniversidad = new GestorUniversidad(connection);
			DTOUniversidad dtoUniversidad = gestorUniversidad.consultaEntidadById(centroDocente.getIdUniversidad());
			if(dtoUniversidad != null) dtoCen.setDtoUniversidad(dtoUniversidad);
			
			//Añadimos contacto
			GestorContacto gestorContacto = new GestorContacto(connection);
			DTOContacto dtoContacto = gestorContacto.consultaEntidadById(centroDocente.getIdContacto());
			if(dtoContacto != null) dtoCen.setDtoContacto(dtoContacto);
			
			return dtoCen;
			
		}catch(Exception e){
			throw new Exception(e.getMessage());
		}
	}
}
