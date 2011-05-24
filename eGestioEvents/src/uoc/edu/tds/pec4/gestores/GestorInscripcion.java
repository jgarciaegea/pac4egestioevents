/**
 * 
 */
package uoc.edu.tds.pec4.gestores;

/**
 * @author jgarcia
 *
 */

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import uoc.edu.tds.pec4.beans.Inscripcion;
import uoc.edu.tds.pec4.daos.DaoInscripcion;
import uoc.edu.tds.pec4.dtos.DTOAsistente;
import uoc.edu.tds.pec4.dtos.DTOEvento;
import uoc.edu.tds.pec4.dtos.DTOInscripcion;
import uoc.edu.tds.pec4.dtos.DTOInscripcionConsulta;
import uoc.edu.tds.pec4.dtos.DTOUsuario;

public class GestorInscripcion extends GestorEntidad<DTOInscripcion>{

	public GestorInscripcion(Connection connection) throws Exception {
		super(connection);
	}
	
	@Override
	public DTOInscripcion consultaEntidad(DTOInscripcion criteris) throws Exception {
		try{
			List<DTOInscripcion> lstDTOInscripcion = this.consultaEntidades(criteris);
			if(lstDTOInscripcion != null && lstDTOInscripcion.size() > 0){
				return lstDTOInscripcion.get(0);
			}
		}catch(Exception e){
			throw e;
		}
		return null;
	}
	
	@Override
	public List<DTOInscripcion> consultaEntidades(DTOInscripcion criteris) throws Exception {
		try{
			DaoInscripcion dao = new DaoInscripcion(connection);
			List<Inscripcion> lstInscripcion = dao.select(criteris.getInscripcion());
			if(lstInscripcion != null && lstInscripcion.size() > 0){
				List<DTOInscripcion> lstDTOInscripcion = new ArrayList<DTOInscripcion>();
				for(Inscripcion inscripcion : lstInscripcion){
					
					//A�adimos Inscripcion
					DTOInscripcion dtoInscripcion = new DTOInscripcion();
					dtoInscripcion.setInscripcion(inscripcion);
					
					//A�adimos el Evento
					GestorEvento gestorEvento = new GestorEvento(connection);
					DTOEvento dtoEvento = gestorEvento.consultaEntidadById(inscripcion.getIdEvento());
					if(dtoEvento != null) dtoInscripcion.setDtoEvento(dtoEvento);
					
					//A�adimos el Usuario
					GestorUsuario gestorUsuario = new GestorUsuario(connection);
					DTOAsistente dtoAsistente = (DTOAsistente) gestorUsuario.consultaEntidadById(inscripcion.getCodigo());
					if(dtoAsistente != null) dtoInscripcion.setDtoAsistente(dtoAsistente);
					
					lstDTOInscripcion.add(dtoInscripcion);
				}
				return lstDTOInscripcion;
			}
		}catch(Exception e){
			throw new Exception();
		}
		return null;
	}

	
	public List<DTOInscripcionConsulta> consultaEntidadesByDates(DTOInscripcionConsulta criteris) throws Exception {
		try{
			DaoInscripcion dao = new DaoInscripcion(connection);
			System.out.println("DAO INSCRIPCION CREADO....");
			
			List<DTOInscripcionConsulta> lstDTOInscripcionConsulta = new ArrayList<DTOInscripcionConsulta>();
			
			lstDTOInscripcionConsulta = dao.selectByDates(criteris);
			
			List<DTOInscripcionConsulta> lstDTOInscripcionConsulta1 = new ArrayList<DTOInscripcionConsulta>();
			
			if(lstDTOInscripcionConsulta != null && lstDTOInscripcionConsulta.size() > 0){
				System.out.println("Se han encontrado resultados lstDTOInscripcionConsulta no es nulo ");		
				
				for(DTOInscripcionConsulta dtoInscripcionConsulta : lstDTOInscripcionConsulta){
					
					System.out.println("dentro del for.....");
					//A�adimos el Evento					
					GestorEvento gestorEvento = new GestorEvento(connection);
					System.out.println("recogiendo el evento........." + dtoInscripcionConsulta.getInscripcion().getIdEvento());
					
					DTOEvento dtoEvento = gestorEvento.consultaEntidadById(dtoInscripcionConsulta.getInscripcion().getIdEvento());
					if(dtoEvento != null) dtoInscripcionConsulta.setDtoEvento(dtoEvento);
					else System.out.println(" ERROR recogiendo el dtoEvento.........id evento " );
					
					//A�adimos el Usuario
					GestorUsuario gestorUsuario = new GestorUsuario(connection);
					System.out.println("recogiendo el usuario....: "+ dtoInscripcionConsulta.getInscripcion().getCodigo());
					DTOUsuario dtoUsuario = gestorUsuario.consultaEntidadById(dtoInscripcionConsulta.getInscripcion().getCodigo());
					 System.out.println(" despues de encontrar el usuario....");
					
					if (dtoUsuario instanceof DTOAsistente)
						 	System.out.println("SE TRATA DE UN ASISTENTE");
					 else System.out.println("NO SE TRATA DE UN ASISTENTE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
					
						 DTOAsistente dtoAsistente = (DTOAsistente)dtoUsuario;
					if(dtoAsistente != null) dtoInscripcionConsulta.setDtoAsistente( dtoAsistente);
					else System.out.println(" ERROR recogiendo el DTOAsistente.........");
					
					lstDTOInscripcionConsulta1.add(dtoInscripcionConsulta);
					System.out.println("cargado el DTOInscripcionConsulta");
					
				}
				
				return lstDTOInscripcionConsulta1;
			}
		}catch(Exception e){
			System.out.println("ERROR GRAVE....");
			e.printStackTrace();
			throw new Exception();
			
		}
		
		return null;
	}

	
	
	@Override
	public void insertaEntidad(DTOInscripcion newobject) throws Exception{
		try {
			DaoInscripcion dao = new DaoInscripcion(connection);
			dao.insert(newobject.getInscripcion());
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void modificaEntidad(DTOInscripcion criteris) throws Exception {
		try {
			DaoInscripcion dao = new DaoInscripcion(connection);
			dao.update(criteris.getInscripcion());
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void eliminaEntidad(DTOInscripcion criteris) throws Exception {
		try {
			DaoInscripcion dao = new DaoInscripcion(connection);
			dao.delete(criteris.getInscripcion());
		} catch (Exception e) {
			throw e;
		}
	}

	/*
	 * Una vez finalizado el Evento se podr� notificar que el asistente ha asistido
	 * al evento mediante la realizaci�n del check-IN de su inscripci�n. 
	 */
	public void checkIN(DTOInscripcion inscripcion) throws Exception {
		try {
			//TODO 1: Revisar que no se pueda hacer un checkIN si no est� finalizado el evento
			DaoInscripcion dao = new DaoInscripcion(connection);
			dao.checkIN(inscripcion.getInscripcion());
		} catch (Exception e) {
			throw e;
		}
	}
	
	/*
	 * A efectos de error, que podamos desmarcar la asistencia a un evento del asistente.
	 */
	public void checkOUT(DTOInscripcion inscripcion) throws Exception {
		try {
			DaoInscripcion dao = new DaoInscripcion(connection);
			dao.checkOUT(inscripcion.getInscripcion());
		} catch (Exception e) {
			throw e;
		}
	}

}