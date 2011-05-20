package uoc.edu.tds.pec4.gestores;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import uoc.edu.tds.pec4.beans.DocumentoIdentificacion;
import uoc.edu.tds.pec4.daos.DaoDocumentoIdentificacion;
import uoc.edu.tds.pec4.dtos.DTODocumentoIdentificacion;
import uoc.edu.tds.pec4.dtos.DTOPais;
import uoc.edu.tds.pec4.dtos.DTOTipoDocumento;

public class GestorDocumentoIdentificacion extends GestorEntidad<DTODocumentoIdentificacion>{

	public GestorDocumentoIdentificacion(Connection connection) throws Exception {
		super(connection);
	}

	@Override
	public void insertaEntidad(DTODocumentoIdentificacion newobject)  throws Exception {
		try {
			DaoDocumentoIdentificacion dao = new DaoDocumentoIdentificacion(connection);
			dao.insert(newobject.getDocumentoIdentificacion());
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Integer insertaEntidadRetId(DTODocumentoIdentificacion newobject) throws Exception {
		DaoDocumentoIdentificacion dao = new DaoDocumentoIdentificacion(connection);
		try {
			return dao.insertReturnId(newobject.getDocumentoIdentificacion());
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Override
	public List<DTODocumentoIdentificacion> consultaEntidades(DTODocumentoIdentificacion criteris) throws Exception {
		try{
			DaoDocumentoIdentificacion dao = new DaoDocumentoIdentificacion(connection);
			List<DocumentoIdentificacion> lstDocIdentifica = dao.select(criteris.getDocumentoIdentificacion());
			if(lstDocIdentifica != null && lstDocIdentifica.size() > 0){
				List<DTODocumentoIdentificacion> lstDtoDocIden = new ArrayList<DTODocumentoIdentificacion>();
				for(DocumentoIdentificacion docIden : lstDocIdentifica){
					
					DTODocumentoIdentificacion dtoDocIden = new DTODocumentoIdentificacion();
					dtoDocIden.setDocumentoIdentificacion(docIden);
					
					//Añadimos dto tipo documento
					GestorTipoDocumento gestorTipoDocumento = new GestorTipoDocumento(connection);
					DTOTipoDocumento dtoTipoDoc = gestorTipoDocumento.consultaEntidadById(docIden.getIdTipoDocumento());
					if(dtoTipoDoc != null) dtoDocIden.setDtoTipoDocumento(dtoTipoDoc);
					
					//Añadimos el dto país
					GestorPais gestorPais = new GestorPais(connection);
					DTOPais dtoPais = gestorPais.consultaEntidadById(docIden.getIdPais());
					if(dtoPais != null) dtoDocIden.setDtoPais(dtoPais);
					
					lstDtoDocIden.add(dtoDocIden);
				}
				return lstDtoDocIden;
			}
		}catch(Exception e){
			throw new Exception();
		}
		return null;
	}

	@Override
	public DTODocumentoIdentificacion consultaEntidad(DTODocumentoIdentificacion criteris) throws Exception {
		try{
			List<DTODocumentoIdentificacion> lstDtoDocIden = this.consultaEntidades(criteris);
			if(lstDtoDocIden != null && lstDtoDocIden.size() > 0){
				return lstDtoDocIden.get(0);
			}
		}catch(Exception e){
			throw e;
		}
		return null;
	}
	
	public DTODocumentoIdentificacion consultaEntidadById(Integer idDocIden)  throws Exception {
		try{
			DTODocumentoIdentificacion dtoTipoDocIden = new DTODocumentoIdentificacion();
			DocumentoIdentificacion docIden = new DocumentoIdentificacion();
			docIden.setIdDocumentoIdentificacion(idDocIden);
			dtoTipoDocIden.setDocumentoIdentificacion(docIden);
			return this.consultaEntidad(dtoTipoDocIden);
		}catch(Exception e){
			throw e;
		}
	}

	@Override
	public void modificaEntidad(DTODocumentoIdentificacion criteris) throws Exception {
		try {
			DaoDocumentoIdentificacion dao = new DaoDocumentoIdentificacion(connection);
			dao.update(criteris.getDocumentoIdentificacion());
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void eliminaEntidad(DTODocumentoIdentificacion criteris) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}

}
