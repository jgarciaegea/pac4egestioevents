package uoc.edu.tds.pec4.gestores;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uoc.edu.tds.pec4.beans.Usuario;
import uoc.edu.tds.pec4.daos.DaoUsuario;
import uoc.edu.tds.pec4.dtos.DTOAdministrador;
import uoc.edu.tds.pec4.dtos.DTOAsistente;
import uoc.edu.tds.pec4.dtos.DTOCentroDocente;
import uoc.edu.tds.pec4.dtos.DTOContacto;
import uoc.edu.tds.pec4.dtos.DTODatosBancarios;
import uoc.edu.tds.pec4.dtos.DTODocumentoIdentificacion;
import uoc.edu.tds.pec4.dtos.DTOPersonalSecretaria;
import uoc.edu.tds.pec4.dtos.DTOTelefono;
import uoc.edu.tds.pec4.dtos.DTOTipoRol;
import uoc.edu.tds.pec4.dtos.DTOUsuario;
import uoc.edu.tds.pec4.dtos.DTOUsuarioConsulta;

public class GestorUsuario  extends GestorEntidad<DTOUsuario>{

	public GestorUsuario(Connection connection) throws Exception {
		super(connection);
	}

	@Override
	public void insertaEntidad(DTOUsuario newobject) throws Exception {
		try {
			DaoUsuario dao = new DaoUsuario(connection);
			dao.insert(newobject.getUsuario());
		} catch (Exception e) {
			throw new Exception();
		}
	}

	@Override
	public List<DTOUsuario> consultaEntidades(DTOUsuario criteris) throws Exception {
		try{
			
			DaoUsuario dao = new DaoUsuario(connection);
			List<Usuario> lstUsuarios = dao.select(criteris.getUsuario());
			
			if(lstUsuarios != null && lstUsuarios.size() > 0){
				List<DTOUsuario> lstUsu = new ArrayList<DTOUsuario>();
				for(Usuario usu : lstUsuarios){
					lstUsu.add(getTypeDTO(usu));
				}
				return lstUsu;
			}
		}catch(Exception e){
			throw new Exception();
		}
		return null;
	}
	
	public List<DTOUsuario> consultaEntidadesByView(DTOUsuarioConsulta criteris) throws Exception {
		try{
			
			DaoUsuario dao = new DaoUsuario(connection);		
			List<Usuario> lstUsuarios = dao.selectUsersByView(criteris.getUsuarioViewConsulta());
			if(lstUsuarios != null && lstUsuarios.size() > 0){
				List<DTOUsuario> lstUsu = new ArrayList<DTOUsuario>();
				for(Usuario usu : lstUsuarios){
					lstUsu.add(getTypeDTO(usu));
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
			throw new Exception();
		}
		return null;
	}
	
	@Override
	public void modificaEntidad(DTOUsuario criteris) throws Exception {
		try {
			DaoUsuario dao = new DaoUsuario(connection);
			dao.update(criteris.getUsuario());
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void eliminaEntidad(DTOUsuario criteris) throws Exception {
		try {
			DaoUsuario dao = new DaoUsuario(connection);
			dao.delete(criteris.getUsuario());
		} catch (Exception e) {
			throw new Exception();
		}
	}
	
	private DTOUsuario getTypeDTO(Usuario usu) throws Exception{
		try{
			
			if(usu.getTipoUsuario()==null) throw new Exception("El tipo de usuario ha de estar informado");
			
			DTOUsuario dtoUsuario = FactoriaUsuario.getUsuario(usu.getTipoUsuario()); 
			
			if(dtoUsuario instanceof DTOAdministrador){
				DTOAdministrador dtoAdministrador = new DTOAdministrador();
				dtoAdministrador.setUsuario(usu);
				rellenaObjeto(dtoAdministrador);
				return dtoAdministrador;
			}else if(dtoUsuario instanceof DTOPersonalSecretaria){
				DTOPersonalSecretaria dtoPersonal = new DTOPersonalSecretaria();
				dtoPersonal.setUsuario(usu);
				rellenaObjeto(dtoPersonal);
				return dtoPersonal;
			}else if(dtoUsuario instanceof DTOAsistente){
				DTOAsistente dtoAsistente = new DTOAsistente();
				dtoAsistente.setUsuario(usu);
				rellenaObjeto(dtoAsistente);
				
				//Añadimios el DTORol
				if(dtoAsistente.getUsuario().getIdRol() != null){
					GestorTipoRol gestorTipoRol = new GestorTipoRol(connection);
					DTOTipoRol dtoTipoRol = gestorTipoRol.consultaEntidadById(dtoAsistente.getUsuario().getIdRol());
					if(dtoTipoRol != null) dtoAsistente.setDtoTipoRol(dtoTipoRol);
				}
				
				//Añadimios el DTODatosBancarios
				if(dtoAsistente.getUsuario().getIdDatosBancarios() != null){
					GestorDatosBancarios gestorDatosBancarios = new GestorDatosBancarios(connection);
					DTODatosBancarios dtoDatosBancarios = gestorDatosBancarios.consultaEntidadById(dtoAsistente.getUsuario().getIdDatosBancarios());
					if(dtoDatosBancarios != null) dtoAsistente.setDtoDatosBancarios(dtoDatosBancarios);
				}
				
				return dtoAsistente;
			}
			
			throw new Exception("El tipo de usuario " + usu.getTipoUsuario() + " no está contemplado");
			
		}catch(Exception e){
			throw new Exception();
		}
		
	}
	
	/*
	 * Rellenamos la información genérica para todos los tipos de objeto DTOUsario
	 * en este caso es el centro Docente, el contacto y el documento de identificación
	 */
	private void rellenaObjeto (DTOUsuario dtoUsuario) throws Exception{
		try{
			
			//Añadimos información del centro Docente
			if(dtoUsuario.getUsuario().getIdCentro() != null){
				GestorCentroDocente gestorCentroDocente = new GestorCentroDocente(connection);
				DTOCentroDocente dtoCentroDocente = gestorCentroDocente.consultaEntidadById(dtoUsuario.getUsuario().getIdCentro());
				if(dtoCentroDocente != null) dtoUsuario.setDtoCentroDocente(dtoCentroDocente);
			}
			
			//Añadimos el dto contacto
			if(dtoUsuario.getUsuario().getIdContacto() != null){
				GestorContacto gestorContacto = new GestorContacto(connection);
				DTOContacto dtoContacto = gestorContacto.consultaEntidadById(dtoUsuario.getUsuario().getIdContacto());
				if(dtoContacto != null) dtoUsuario.setDtoContacto(dtoContacto);
				
				GestorTelefono gestorTelefono = new GestorTelefono(connection);
				DTOTelefono dtoTelefono = gestorTelefono.consultaEntidadById(dtoUsuario.getUsuario().getIdContacto());
				if(dtoTelefono != null) dtoUsuario.setDtoTelefono(dtoTelefono);
				
			}
			
			
			//Añadimos el dto documento identificacion
			if(dtoUsuario.getUsuario().getIdDocumentoIdentificacion() != null){
				GestorDocumentoIdentificacion gestorDocumentoIden = new GestorDocumentoIdentificacion(connection);
				DTODocumentoIdentificacion dtoDocumentoIden = gestorDocumentoIden.consultaEntidadById(dtoUsuario.getUsuario().getIdDocumentoIdentificacion());
				if(dtoDocumentoIden != null) dtoUsuario.setDtoDocumentoIden(dtoDocumentoIden);
			}
			
			//Añadimos los datos bancarios
			if(dtoUsuario.getUsuario().getIdDatosBancarios() != null){
				GestorDatosBancarios gestorDatosBancarios = new GestorDatosBancarios(connection);
				DTODatosBancarios dtoDatosBancarios = gestorDatosBancarios.consultaEntidadById(dtoUsuario.getUsuario().getIdDatosBancarios());
				if(dtoDatosBancarios != null) dtoUsuario.setDtoDatosBancarios(dtoDatosBancarios);
			}
			
		}catch(Exception e){
			throw new SQLException();
		}
	}
	
	
	

}
