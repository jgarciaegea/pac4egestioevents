package uoc.edu.tds.pec4.gestores;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uoc.edu.tds.pec4.beans.Administrador;
import uoc.edu.tds.pec4.beans.Asistente;
import uoc.edu.tds.pec4.beans.PersonalSecretaria;
import uoc.edu.tds.pec4.beans.Usuario;
import uoc.edu.tds.pec4.daos.DaoUsuario;
import uoc.edu.tds.pec4.dtos.DTOAdministrador;
import uoc.edu.tds.pec4.dtos.DTOAsistente;
import uoc.edu.tds.pec4.dtos.DTOCentroDocente;
import uoc.edu.tds.pec4.dtos.DTOContacto;
import uoc.edu.tds.pec4.dtos.DTODatosBancarios;
import uoc.edu.tds.pec4.dtos.DTODocumentoIdentificacion;
import uoc.edu.tds.pec4.dtos.DTOPersonalSecretaria;
import uoc.edu.tds.pec4.dtos.DTOTipoRol;
import uoc.edu.tds.pec4.dtos.DTOUsuario;

public class GestorUsuario  extends GestorEntidad<DTOUsuario>{

	public GestorUsuario(Connection connection) throws Exception {
		super(connection);
	}

	@Override
	public void insertaEntidad(DTOUsuario newobject) {
		throw new UnsupportedOperationException("Método no implementado");
	}

	@Override
	public List<DTOUsuario> consultaEntidades(DTOUsuario criteris) throws Exception {
		try{
			
			DaoUsuario dao = new DaoUsuario(connection);
			List<Usuario> lstUsuarios = dao.select(getTypeDTO(criteris));
			
			if(lstUsuarios != null && lstUsuarios.size() > 0){
				List<DTOUsuario> lstUsu = new ArrayList<DTOUsuario>();
				for(Usuario usu : lstUsuarios){
					
					lstUsu.add(getTypeBean(usu));
				}
				return lstUsu;
			}
		}catch(Exception e){
			throw new Exception();
		}
		return null;
	}

	@Override
	public DTOUsuario consultaEntidad(DTOUsuario criteris) throws Exception {
		try{
			List<DTOUsuario> lstUsuarios = this.consultaEntidades(criteris);
			if(lstUsuarios != null && lstUsuarios.size() > 0){
				return lstUsuarios.get(0);
			}
		}catch(Exception e){
			throw e;
		}
		return null;
	}
	
	@Override
	public void modificaEntidad(DTOUsuario criteris) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}

	@Override
	public void eliminaEntidad(DTOUsuario criteris) throws Exception {
		throw new UnsupportedOperationException("Método no implementado");
	}
	
	
	
	@SuppressWarnings("unchecked")
	private <B extends Usuario> B getTypeDTO(DTOUsuario criteris) throws Exception{
		try{
			if(criteris instanceof DTOAsistente){
				return (B) ((DTOAsistente) criteris).getAsistente();
			}else if(criteris instanceof DTOPersonalSecretaria){
				return (B) ((DTOPersonalSecretaria) criteris).getSecretaria();
			}else if(criteris instanceof DTOAdministrador){
				return (B) ((DTOAdministrador) criteris).getAdministrador();
			}
		}catch(Exception e){
			throw new Exception();
		}
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	private <B extends DTOUsuario> B getTypeBean(Usuario usu) throws Exception{
		try{
			if(usu instanceof Administrador){
				DTOAdministrador dtoAdministrador = new DTOAdministrador();
				dtoAdministrador.setAdministrador((Administrador)usu);
				rellenaObjeto(dtoAdministrador);
				return (B) dtoAdministrador;
				
			}else if(usu instanceof PersonalSecretaria){
				DTOPersonalSecretaria dtoPersonal = new DTOPersonalSecretaria();
				dtoPersonal.setSecretaria((PersonalSecretaria)usu);
				rellenaObjeto(dtoPersonal);
				return (B) dtoPersonal;
				
			}else if(usu instanceof Asistente){
				
				DTOAsistente dtoAsistente = new DTOAsistente();
				dtoAsistente.setAsistente((Asistente)usu);
				rellenaObjeto(dtoAsistente);
				
				//Añadimios el DTORol
				GestorTipoRol gestorTipoRol = new GestorTipoRol(connection);
				DTOTipoRol dtoTipoRol = gestorTipoRol.consultaEntidadById(dtoAsistente.getAsistente().getIdRol());
				if(dtoTipoRol != null) dtoAsistente.setDtoTipoRol(dtoTipoRol);
				
				//Añadimios el DTODatosBancarios
				GestorDatosBancarios gestorDatosBancarios = new GestorDatosBancarios(connection);
				DTODatosBancarios dtoDatosBancarios = gestorDatosBancarios.consultaEntidadById(dtoAsistente.getAsistente().getIdDatosBancarios());
				if(dtoDatosBancarios != null) dtoAsistente.setDtoDatosBancarios(dtoDatosBancarios);
				
				return (B) dtoAsistente;
				
			}
		}catch(Exception e){
			throw new Exception();
		}
		
		return null;
	}
	
	/*
	 * Rellenamos la información genérica para todos los tipos de objeto DTOUsario
	 * en este caso es el centro Docente, el contacto y el documento de identificación
	 */
	private <B extends DTOUsuario> void rellenaObjeto (B dtoUsuario) throws Exception{
		try{
			
			//Añadimos información del centro Docente
			GestorCentroDocente gestorCentroDocente = new GestorCentroDocente(connection);
			DTOCentroDocente dtoCentroDocente = gestorCentroDocente.consultaEntidadById(dtoUsuario.getUsuario().getIdCentro());
			if(dtoCentroDocente != null) dtoUsuario.setDtoCentroDocente(dtoCentroDocente);
			
			//Añadimos el dto contacto
			GestorContacto gestorContacto = new GestorContacto(connection);
			DTOContacto dtoContacto = gestorContacto.consultaEntidadById(dtoUsuario.getUsuario().getIdContacto());
			if(dtoContacto != null) dtoUsuario.setDtoContacto(dtoContacto);
			
			//Añadimos el dto documento identificacion
			GestorDocumentoIdentificacion gestorDocumentoIden = new GestorDocumentoIdentificacion(connection);
			DTODocumentoIdentificacion dtoDocumentoIden = gestorDocumentoIden.consultaEntidadById(dtoUsuario.getUsuario().getIdDocumentoIdentificacion());
			if(dtoDocumentoIden != null) dtoUsuario.setDtoDocumentoIden(dtoDocumentoIden);
			
		}catch(Exception e){
			throw new SQLException();
		}
	}
	
	
	

}
