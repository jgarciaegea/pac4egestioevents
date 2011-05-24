/**
 * 
 */
package uoc.edu.tds.pec4.pantallas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import uoc.edu.tds.pec4.beans.Evento;
import uoc.edu.tds.pec4.beans.Inscripcion;
import uoc.edu.tds.pec4.beans.Usuario;
import uoc.edu.tds.pec4.dtos.DTOEvento;
import uoc.edu.tds.pec4.dtos.DTOInscripcion;
import uoc.edu.tds.pec4.dtos.DTOInscripcionConsulta;
import uoc.edu.tds.pec4.dtos.DTOTipoEvento;
import uoc.edu.tds.pec4.excepciones.OperationErrorBD;
import uoc.edu.tds.pec4.excepciones.OperationErrorDatosFormulario;
import uoc.edu.tds.pec4.gestores.GestorRMI;
import uoc.edu.tds.pec4.iface.RemoteInterface;
import uoc.edu.tds.pec4.resources.TDSLanguageUtils;
import uoc.edu.tds.pec4.utils.MostrarCombo;
import uoc.edu.tds.pec4.utils.Utils;

/**
 * @author ML019882
 *
 */
public class PantallaInformeEventosAsistente extends PanelComun implements Pantallas {

	private static final long serialVersionUID = 1L; 
	private RemoteInterface remote;
	private String[] columnNames = {"Universidad ","Centro ","Evento", "Tipo Evento", "Fecha inicio", "Fecha fin", "Cerrado" , "Estado "};
	private Usuario usuario;

	/**
	 * @throws OperationErrorBD 
	 * @throws RemoteException 
	 * 
	 */
	public PantallaInformeEventosAsistente(GestorRMI gestorRMI,RemoteInterface remote1,Usuario usu) throws RemoteException, OperationErrorBD {
		
		super("clientePEC4.panelInformeEventosAsistencia.titulo",800,600);
		remote = remote1;
		usuario = usu;
		
		this.crearTitulo(20, 30, 140, 20, "clientePEC4.panelInformeEventosAsistencia.titulo1.Asistente", "tAsistente");
		this.crearTextField(160, 30, 200, 20,"cajaAsistente");
		this.findTextField("cajaAsistente").setText(usuario.getApellidos() + ", "+ usuario.getNombre());
		this.findTextField("cajaAsistente").setEditable(false);
		this.crearTitulo(20, 60, 140, 20, "clientePEC4.panelInformeEventosAsistencia.titulo1.TipoEvento", "tTipoEvento");
		this.crearCombo(160, 60, 200, 20, "comboTipoEvento", recuperarTiposEvento());
		this.crearTitulo(20, 90, 80, 20, "clientePEC4.panelInformeEventosAsistencia.titulo1.FechaDesde", "tFechaDesde");
		this.crearTextField(100, 90, 90, 20,"cajaFechaDeste");
		this.crearTitulo(220, 90, 80, 20, "clientePEC4.panelInformeEventosAsistencia.titulo1.FechaHasta", "tFechaHasta");
		this.crearTextField(300, 90, 90, 20,"cajaFechaHasta");
		this.crearTabla(20, 150, 700, 300,columnNames);
	
		this.crearBoton(50, 500, 120, 30, "clientePEC4.panelInformeEventosAsistencia.boton.bBuscar","bBuscar");		
		this.crearBoton(200, 500, 120, 30, "clientePEC4.panelInformeEventosAsistencia.boton.bCancel", "bCancel");
		generaEventos();
	}
	
	private  List<MostrarCombo> recuperarTiposEvento() throws RemoteException, OperationErrorBD{
		
		List<DTOTipoEvento> lstdtoTipoEvento = remote.getTiposEventos();
		List<MostrarCombo> lstComoTipoEvento = new ArrayList<MostrarCombo>();
		for(DTOTipoEvento dtoTipoEventoRes : lstdtoTipoEvento){
			lstComoTipoEvento.add(new MostrarCombo(dtoTipoEventoRes.getTipoEvento().getEstado(),
					dtoTipoEventoRes.getTipoEvento().getDescripcion()));
		
		
		}
		return lstComoTipoEvento;
	}
	
	
	/**
	 * Método que genera los Eventos
	 * 
	 */
	
	private void generaEventos(){
		
		this.findBoton("bBuscar").addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					validaFormulario();
					try {
						//buscarEventos();
						buscarEventosOK();
					} catch (RemoteException e1) {
						e1.printStackTrace();
					} catch (OperationErrorBD e1) {
						e1.printStackTrace();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} catch (OperationErrorDatosFormulario ef) {
					ef.printStackTrace();
					ef.showDialogError();
				}
			}
		});

	}
	
	
	private void buscarEventos() throws RemoteException, OperationErrorBD, Exception{
		DTOInscripcionConsulta DTOinscrp = new DTOInscripcionConsulta();
		DTOinscrp.setUsuario(usuario.getCodigo());
		DTOinscrp.setFechaInicioBusqueda(Utils.transformFecha(this.findTextField("cajaFechaDeste").getText()));
		DTOinscrp.setFechaFinBusqueda(Utils.transformFecha(this.findTextField("cajaFechaHasta").getText()));
		muestraResultado(remote.buscaInscripcionesUsuario(DTOinscrp));
		
	}
	
	
	private void buscarEventosOK() throws RemoteException, OperationErrorBD, Exception{
		
		DTOInscripcion dtoinscrp = new DTOInscripcion();
		Inscripcion inscripcion = new Inscripcion();
		inscripcion.setCodigo(usuario.getCodigo());
		
		DTOEvento dtoevento = new DTOEvento();
		Evento evento = new Evento();
		evento.setFechaInicioCelebracion(Utils.transformFecha(this.findTextField("cajaFechaDeste").getText()));
		evento.setFechaFinCelebracion(Utils.transformFecha(this.findTextField("cajaFechaHasta").getText()));
		dtoevento.setEvento(evento);
		
		dtoinscrp.setInscripcion(inscripcion);
		dtoinscrp.setDtoEvento(dtoevento);
		remote.buscaEventosInscrito(dtoinscrp);
		
	}
	
	
	/*
	 * Mostramos el resultado obtenido
	 */
	
	private void muestraResultado(List<DTOInscripcionConsulta> lstDtoInscripcionConsult) throws OperationErrorDatosFormulario{
		
		try{
			this.getDtm().getDataVector().removeAllElements();
			Object[][] aobj = new Object[lstDtoInscripcionConsult.size()][7];
			int k = 0;			
			if(lstDtoInscripcionConsult != null){
				for(DTOInscripcionConsulta dtoInscripcionConsulta : lstDtoInscripcionConsult){
					 aobj[k][0] = new String(dtoInscripcionConsulta.getDtoEvento().getDtoCentroDocente().getDtoUniversidad().getUniversidad().getNombre());
					 aobj[k][1] = new String(dtoInscripcionConsulta.getDtoEvento().getDtoCentroDocente().getCentroDocente().getNombre());
					 aobj[k][2] = new String(dtoInscripcionConsulta.getDtoEvento().getEvento().getNombre());
	                 aobj[k][3] = new String(dtoInscripcionConsulta.getDtoEvento().getDtoTipoEvento().toString());
	                 aobj[k][4] = new String(Utils.convertFecha(dtoInscripcionConsulta.getDtoEvento().getEvento().getFechaInicioCelebracion().toString()));
	                 aobj[k][4] = new String(Utils.convertFecha(dtoInscripcionConsulta.getDtoEvento().getEvento().getFechaFinCelebracion().toString()));
	                 aobj[k][5] = new String("");
	                 aobj[k][6] = new String(dtoInscripcionConsulta.getInscripcion().getEstado().toString());
	                 k++;
	       	 	}				
				if(aobj != null && aobj.length > 0){
	       	 		for(int row = 0; row < aobj.length; row++){
	       	 		this.getDtm().addRow(aobj[row]);
	       	 		}
	       	 	}
	       	 	
			}
		}catch(Exception e){
			throw new OperationErrorDatosFormulario(TDSLanguageUtils.getMessage("clientePEC4.error15"));
		}
	}
	
	
	
	
	
	/**
	 * Método que valida los datos introducidos en el formulario
	 * @throws OperationErrorDatosFormulario
	 */
	private void validaFormulario() throws OperationErrorDatosFormulario{
		try{
			

			if(Utils.valorisNull(this.findCombo("comboTipoEvento").getSelectedItem())) throw new Exception(Utils.MESSAGE_ERROR + " Tipo de Evento" );
			if(!"".equalsIgnoreCase(this.findTextField("cajaFechaHasta").getText()) && "".equalsIgnoreCase(this.findTextField("cajaFechaDeste").getText())){
				 throw new Exception("No puede introducir la fecha final sin previamente informar la fecha de inicio");
			}
			if(!"".equalsIgnoreCase(this.findTextField("cajaFechaDeste").getText())){
				if(!Utils.parseaFecha(this.findTextField("cajaFechaDeste").getText())) throw new Exception(Utils.MESSAGE_ERROR + " fecha inicio" + Utils.MESSAGE_FECHA );
			}
			if(!"".equalsIgnoreCase(this.findTextField("cajaFechaHasta").getText())){
				if(!Utils.parseaFecha(this.findTextField("cajaFechaHasta").getText())) throw new Exception(Utils.MESSAGE_ERROR + " fecha fin" + Utils.MESSAGE_FECHA );
			}
		
		}catch(Exception e){
			throw new OperationErrorDatosFormulario(e.getMessage());
		}
			
	}
	
	
}
