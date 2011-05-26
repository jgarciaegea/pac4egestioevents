/**
 * 
 */
package uoc.edu.tds.pec4.pantallas;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import uoc.edu.tds.pec4.beans.Evento;
import uoc.edu.tds.pec4.beans.Inscripcion;
import uoc.edu.tds.pec4.dtos.DTOEvento;
import uoc.edu.tds.pec4.dtos.DTOInscripcion;
import uoc.edu.tds.pec4.dtos.DTOUsuario;
import uoc.edu.tds.pec4.dtos.DTOTipoEvento;
import uoc.edu.tds.pec4.excepciones.OperationErrorBD;
import uoc.edu.tds.pec4.excepciones.OperationErrorDatosFormulario;
import uoc.edu.tds.pec4.excepciones.OperationErrorLogin;
import uoc.edu.tds.pec4.iface.RemoteInterface;
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
	private DTOUsuario dtoUsuario;

	/**
	 * @throws OperationErrorBD 
	 * @throws RemoteException 
	 * @throws OperationErrorLogin 
	 * 
	 */
	
	
	public PantallaInformeEventosAsistente(RemoteInterface remote1,DTOUsuario dtoUsuario) throws RemoteException, OperationErrorBD, OperationErrorLogin {
		
		super("clientePEC4.panelInformeEventosAsistencia.titulo",800,520);
		remote = remote1;
		this.dtoUsuario = dtoUsuario;
		if (dtoUsuario == null) throw new OperationErrorLogin("La session es invalida.....");
		
		this.crearTitulo(20, 30, 140, 20, "clientePEC4.panelInformeEventosAsistencia.titulo1.Asistente", "tAsistente");
		this.crearTextField(100, 30, 200, 20,"cajaAsistente");
		this.findTextField("cajaAsistente").setText(dtoUsuario.getUsuario().getApellidos() + ", "+ dtoUsuario.getUsuario().getNombre());
		this.findTextField("cajaAsistente").setEditable(false);
		this.crearTitulo(20, 60, 140, 20, "clientePEC4.panelInformeEventosAsistencia.titulo1.TipoEvento", "tTipoEvento");
		this.crearCombo(100, 60, 200, 20, "comboTipoEvento", recuperarTiposEvento());

		
		this.findCombo("comboTipoEvento").setSelectedIndex(-1);
		
		this.crearTitulo(20, 90, 80, 20, "clientePEC4.panelInformeEventosAsistencia.titulo1.FechaDesde", "tFechaDesde");
		

		
		this.crearTextField(100, 90, 90, 20,"cajaFechaDeste");
		this.crearTitulo(220, 90, 80, 20, "clientePEC4.panelInformeEventosAsistencia.titulo1.FechaHasta", "tFechaHasta");
		this.crearTextField(300, 90, 90, 20,"cajaFechaHasta");
		
		this.crearTabla(20, 150, 675, 300,columnNames);
	
		
		this.crearBoton(20, 470, 120, 30, "clientePEC4.panelInformeEventosAsistencia.boton.bBuscar","bBuscar");		
		this.crearBoton(200, 470, 120, 30, "clientePEC4.panelInformeEventosAsistencia.boton.bLimpliar", "bLimpliar");
		this.crearBoton(380, 470, 120, 30, "clientePEC4.panelInformeEventosAsistencia.boton.bCancel", "bCancel");
		generaEventos();
	}
	
	
	
	/**************************************************************
	 * Método para mostrar/rellenar el combo de tipos de evento**
	 **************************************************************/
	
	private  List<MostrarCombo> recuperarTiposEvento() throws RemoteException, OperationErrorBD{
		
		List<DTOTipoEvento> lstdtoTipoEvento = remote.getTiposEventos();
		List<MostrarCombo> lstComoTipoEvento = new ArrayList<MostrarCombo>();
		for(DTOTipoEvento dtoTipoEventoRes : lstdtoTipoEvento){
			lstComoTipoEvento.add(new MostrarCombo(dtoTipoEventoRes.getTipoEvento().getIdTipoEvento(),
					dtoTipoEventoRes.getTipoEvento().getDescripcion()));
		

		}
		return lstComoTipoEvento;
	}
	
	

	
	
	
	
	/**************************************************************
	 * Método para generar los eventos de la pantalla**
	 **************************************************************/
	
	private void generaEventos(){
		
		
		this.findBoton("bLimpliar").addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiaFormulario();
			}});
		
		
		this.findBoton("bCancel").addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				removeAll();
			}});
		
		this.findBoton("bBuscar").addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					validaFormulario();
					limpiaFormulario();
					buscarEventos();
				} catch (OperationErrorDatosFormulario ef) {
					ef.showDialogError();
				} catch (RemoteException e1) {
					e1.printStackTrace();					
				} catch (OperationErrorBD e1) {
					e1.printStackTrace();
					e1.showDialogError();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

	}	

	/**************************************************************
	 * Método para lanzar la consulta de eventos*************
	 **************************************************************/	
	
	private void buscarEventos() throws RemoteException, OperationErrorBD, Exception{
		
		DTOInscripcion dtoinscrp = new DTOInscripcion();
		Inscripcion inscripcion = new Inscripcion();
		inscripcion.setCodigo(dtoUsuario.getUsuario().getCodigo());
		
		DTOEvento dtoevento = new DTOEvento();
		Evento evento = new Evento();
		evento.setFechaInicioCelebracion(Utils.transformFecha(this.findTextField("cajaFechaDeste").getText()));
		evento.setFechaFinCelebracion(Utils.transformFecha(this.findTextField("cajaFechaHasta").getText()));
		evento.setIdTipoEvento(Integer.parseInt(((MostrarCombo) this.findCombo("comboTipoEvento").getSelectedItem()).getID().toString()));
		dtoevento.setEvento(evento);
		
		dtoinscrp.setInscripcion(inscripcion);
		dtoinscrp.setDtoEvento(dtoevento);
		muestraResultado(remote.buscaEventosInscrito(dtoinscrp));
		
	}
	
	/**************************************************************
	 * Método para mostrar el resultado de la consulta*************
	 **************************************************************/	
	
	private void muestraResultado(List<DTOInscripcion> lstDtoInscripcion) throws OperationErrorDatosFormulario{
		
		try{
			this.getDtm().getDataVector().removeAllElements();

			Object[][] aobj = new Object[lstDtoInscripcion.size()][8];
			int k = 0;			
			if(lstDtoInscripcion == null || lstDtoInscripcion.size() == 0 ){
				throw new Exception("NO SE HAN ENCONTRADO RESULTADOS PARA LOS CRITERIOS INTRODUCIDOS");
			}
			
			if(lstDtoInscripcion != null){
				for(DTOInscripcion dtoInscripcion : lstDtoInscripcion){
					 aobj[k][0] = new String(dtoInscripcion.getDtoEvento().getDtoCentroDocente().getDtoUniversidad().getUniversidad().getNombre());
					 aobj[k][1] = new String(dtoInscripcion.getDtoEvento().getDtoCentroDocente().getCentroDocente().getNombre());
					 aobj[k][2] = new String(dtoInscripcion.getDtoEvento().getEvento().getNombre());
	                 aobj[k][3] = new String(dtoInscripcion.getDtoEvento().getDtoTipoEvento().getTipoEvento().getDescripcion());
	                 aobj[k][4] = new String(Utils.convertFecha(dtoInscripcion.getDtoEvento().getEvento().getFechaInicioCelebracion().toString()));
	                 aobj[k][5] = new String(Utils.convertFecha(dtoInscripcion.getDtoEvento().getEvento().getFechaFinCelebracion().toString()));
	                 Date today = new java.util.Date(System.currentTimeMillis());
	                 // entendemos CERRADO CUANDO YA NO SE PUEDEN INSCRIBIR
	                 if (dtoInscripcion.getDtoEvento().getEvento().getFechaFinInscripcion().before(today) || 
	                		 dtoInscripcion.getDtoEvento().getEvento().getFechaFinInscripcion().equals(today)){
	                	 aobj[k][6] = new String("SI");
	                 }else 
	                	 aobj[k][6] = new String("NO");
	                 if (dtoInscripcion.getInscripcion().getCheckIn())
	                	 aobj[k][7] = new String("Asistio");
	                 else 
	                	 aobj[k][7] = new String("NO Asistio");
	                 k++;
	       	 	}
				if(aobj != null && aobj.length > 0){
	       	 		for(int row = 0; row < aobj.length; row++){
	       	 		this.getDtm().addRow(aobj[row]);
	       	 		}
	       	 	}
	       	 	this.getTablaResultados().setEnabled(false);
			} 

		}catch(Exception e){
			throw new OperationErrorDatosFormulario(e.getMessage());
		}
	}
	
	
	
	/**************************************************************
	 * Método para validar el formulario**
	 **************************************************************/
	
	private void validaFormulario() throws OperationErrorDatosFormulario{
		try{
			
			if(Utils.valorisNull(this.findTextField("cajaFechaHasta").getText())) throw new Exception(Utils.MESSAGE_ERROR + " Fecha Hasta " );
			
			if(Utils.valorisNull(this.findTextField("cajaFechaDeste").getText())) throw new Exception(Utils.MESSAGE_ERROR + " Fecha Desde " );
			
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
		
			if (Utils.transformFecha(this.findTextField("cajaFechaDeste").getText()).after(Utils.transformFecha(this.findTextField("cajaFechaHasta").getText()))){
				throw new Exception( " La fecha Desde es mayor que la Fecha Hasta" );
			}
			
			if (Utils.transformFecha(this.findTextField("cajaFechaHasta").getText()).before(Utils.transformFecha(this.findTextField("cajaFechaDeste").getText()))){
				throw new Exception( " La fecha Hasta es Menor que la Fecha Desde");
			}
			
		}catch(Exception e){
			throw new OperationErrorDatosFormulario(e.getMessage());
		}
			
	}
	
	
}
