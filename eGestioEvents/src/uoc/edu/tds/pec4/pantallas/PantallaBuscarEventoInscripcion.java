/**
 * 
 */
package uoc.edu.tds.pec4.pantallas;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;

import uoc.edu.tds.pec4.beans.Evento;
import uoc.edu.tds.pec4.beans.Usuario;
import uoc.edu.tds.pec4.dtos.DTOEvento;
import uoc.edu.tds.pec4.dtos.DTOTipoEvento;
import uoc.edu.tds.pec4.excepciones.OperationErrorBD;
import uoc.edu.tds.pec4.excepciones.OperationErrorDatosFormulario;
import uoc.edu.tds.pec4.excepciones.OperationErrorLogin;
import uoc.edu.tds.pec4.iface.RemoteInterface;
import uoc.edu.tds.pec4.utils.EmailValidator;
import uoc.edu.tds.pec4.utils.MostrarCombo;
import uoc.edu.tds.pec4.utils.Utils;

/**
 * @author ML019882
 *
 */
public class PantallaBuscarEventoInscripcion  extends PanelComun implements Pantallas{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RemoteInterface remote;
	private Usuario usuario;
	private String[] columnNames = {"Evento","Universidad ","Centro ","Tipo Evento"};
	
	public PantallaBuscarEventoInscripcion(RemoteInterface remote1,Usuario usuario1) throws OperationErrorLogin, RemoteException, OperationErrorBD{
		super("clientePEC4.panelBuscarEventoInscripcion.titulo",750,600);
		remote = remote1;
		usuario = usuario1;
		if (usuario1 == null) throw new OperationErrorLogin("La session es invalida.....");
		try {
			remote.testConexion();
		} catch (RemoteException e1) {			
			e1.printStackTrace();
		}
		
		this.crearTitulo(20, 30, 140, 20, "clientePEC4.panelInformeEventosAsistencia.titulo1.tipoEvento", "tipoEvento");		
		this.crearCombo(20, 60, 150, 20, "comboTipoEvento", recuperarTiposEvento());		
		this.crearTitulo(20, 100, 50, 20, "clientePEC4.panelInformeEventosAsistencia.titulo1.fechaEvento", "fechaEvento");		
		this.crearTextField(80, 100, 70, 20, "cajaFechaEvento");	
		this.crearBoton(20, 150, 90, 30, "clientePEC4.panelInscripcion.boton1.botonBuscar","botonBuscar");
		
		this.crearBoton(20, 200, 90, 30, "clientePEC4.panelInscripcion.boton1.botonInscripcion","botonInscripcion");
		
		this.crearTitulo(200, 30, 140, 20, "clientePEC4.panelInformeEventosAsistencia.titulo1.eventos", "eventos");	
		this.crearTabla(200, 60, 450, 200,columnNames);
		this.crearTitulo(200, 310, 140, 20, "clientePEC4.panelInformeEventosAsistencia.titulo1.informacionEvento", "informacionEvento");	
		this.crearJTextArea(200, 350, 450, 200, "informacionEvento");
		this.findJTextAreaString("informacionEvento").setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.findJTextAreaString("informacionEvento").setEnabled(false);
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
	
	public void generaEventos(){
		
		this.findBoton("botonInscripcion").addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					goPantallaInscripcion();
				} catch (OperationErrorLogin e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (OperationErrorBD e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		this.findBoton("botonBuscar").addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					buscarEventos();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (OperationErrorBD e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	
	
	
	public void buscarEventos() throws RemoteException, OperationErrorBD, Exception{
		try {
			validaFormulario();
			Evento evento = new Evento();
			evento.setFechaInicioCelebracion(Utils.transformFecha(this.findTextField("cajaFechaEvento").getText()));
			evento.setIdTipoEvento(Integer.parseInt(((MostrarCombo) this.findCombo("comboTipoEvento").getSelectedItem()).getID().toString()));
			remote.buscarEvento(evento);
		} catch (OperationErrorDatosFormulario e) {
			e.printStackTrace();
			e.showDialogError();
		}
		
	}
	
	
	/**
	 * Método que valida los datos introducidos en el formulario
	 * @throws OperationErrorDatosFormulario
	 */
	private void validaFormulario() throws OperationErrorDatosFormulario{
		try{
			

			if(Utils.valorisNull(this.findCombo("comboTipoEvento").getSelectedItem())) throw new Exception(Utils.MESSAGE_ERROR + " Tipo de Evento" );
			if(Utils.valorisNull(this.findTextField("cajaFechaEvento").getText())) throw new Exception(Utils.MESSAGE_ERROR + " Fecha Evento" );

		
		}catch(Exception e){
			throw new OperationErrorDatosFormulario(e.getMessage());
		}
			
	}
	
	
	
	public void goPantallaInscripcion() throws OperationErrorLogin, RemoteException, OperationErrorBD{
		System.out.println("Repintando Pantalla inscripcion");
		this.setBorder(null);
		this.removeAll();
		this.setAlignmentX(LEFT_ALIGNMENT);
		this.setAlignmentY(TOP_ALIGNMENT);
		this.add((Component)new PantallaInscripcion(remote,usuario));
		this.repaint();
		this.revalidate();
		this.updateUI();
	}
	
	
	

}
