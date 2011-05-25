/**
 * 
 */
package uoc.edu.tds.pec4.pantallas;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.BorderFactory;



import uoc.edu.tds.pec4.beans.Inscripcion;
import uoc.edu.tds.pec4.dtos.DTOAsistente;
import uoc.edu.tds.pec4.dtos.DTOEvento;
import uoc.edu.tds.pec4.dtos.DTOInscripcion;
import uoc.edu.tds.pec4.dtos.DTOUsuario;
import uoc.edu.tds.pec4.excepciones.OperationErrorBD;
import uoc.edu.tds.pec4.excepciones.OperationErrorDatosFormulario;
import uoc.edu.tds.pec4.excepciones.OperationErrorLogin;
import uoc.edu.tds.pec4.iface.RemoteInterface;
import uoc.edu.tds.pec4.utils.Constantes;
import uoc.edu.tds.pec4.utils.Utils;


/**
 * @author ML019882
 *
 */
public class PantallaInscripcion extends PanelComun implements Pantallas{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RemoteInterface remote;
	private DTOUsuario dtousuario;
	private DTOEvento dtoEvento;


	public PantallaInscripcion(RemoteInterface remote1,DTOUsuario dtousuario1, DTOEvento dtoEvento1) throws OperationErrorLogin, RemoteException, OperationErrorBD, OperationErrorDatosFormulario{
		super("clientePEC4.panelInscripcion.titulo",750,500);
		System.out.println("creando Pantalla inscripcion......");
		remote = remote1;
		dtousuario = dtousuario1;
		dtoEvento = dtoEvento1;
		cargarEvento();
		
		if (dtousuario == null) throw new OperationErrorLogin("La session es invalida.....");
		try {
			remote.testConexion();
		} catch (RemoteException e1) {			
			e1.printStackTrace();
		}
		
		this.crearTitulo(20, 30, 140, 20, "clientePEC4.panelInscripcion.titulo1.datosEvento", "datosEvento");
		
		this.crearJTextArea(30, 60, 600, 200, "informacionEventoInscripcion");
		this.findJTextAreaString("informacionEventoInscripcion").setBorder(BorderFactory.createLineBorder(Color.BLACK));

		// TODO HAY QUE METER MAS DATOS EN LA INFOM DEL EVENTO
		System.out.println("evento...."+dtoEvento.getEvento().getDescripcion());
		this.findJTextAreaString("informacionEventoInscripcion").setText(mostrarInfoEvento());
		this.findJTextAreaString("informacionEventoInscripcion").setEditable(false);
		this.crearTitulo(20, 300, 140, 20, "clientePEC4.panelInscripcion.titulo1.nombreAsistente", "nombreAsistente");
		this.crearTextField(160, 300, 250, 20,"cajaAsistente");
		this.findTextField("cajaAsistente").setText(dtousuario.getUsuario().getNombre()+ ", " + dtousuario.getUsuario().getApellidos());
		this.findTextField("cajaAsistente").setEditable(false);
		
		
		this.crearTitulo(20, 330, 140, 20, "clientePEC4.panelInscripcion.titulo1.nombreDatosBancarios", "nombreDatosBancarios");
		this.crearTextField(160, 330, 250, 20,"cajaDatosBancarios");
		if (dtousuario.getDtoDatosBancarios().getCCCompleta()!=null)
			this.findTextField("cajaDatosBancarios").setText(dtousuario.getDtoDatosBancarios().getCCCompleta());
		this.findTextField("cajaDatosBancarios").setEditable(false);
		
		
		this.crearBoton(20, 360, 100, 30,"clientePEC4.panelInscripcion.boton1.botonConfirmar","botonConfirmar");
		this.crearBoton(150, 360, 100, 30, "clientePEC4.panelInscripcion.boton1.botonVolver", "botonVolver");
		generaEventos();
	}
	
	
	public void goPantallaBuscarEvento() throws OperationErrorLogin, RemoteException, OperationErrorBD{
		
		this.setBorder(null);
		this.removeAll();
		this.setAlignmentX(LEFT_ALIGNMENT);
		this.setAlignmentY(TOP_ALIGNMENT);
		this.add((Component)new PantallaCalendarioEventos(remote, dtousuario));
		this.repaint();
		this.revalidate();
		this.updateUI();
	}
	
	public void cargarEvento(){
		try {
			dtoEvento = remote.getEvento(dtoEvento);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (OperationErrorBD e) {
			e.printStackTrace();
		}
	}
	


	public String mostrarInfoEvento() throws OperationErrorDatosFormulario{
		String info = new String();
		info = Constantes.SALTO_LINEA;
		
		if (dtoEvento.getEvento().getFechaInicioCelebracion() != null)
		info = info + "Dia  " + Utils.convertFecha(dtoEvento.getEvento().getFechaInicioCelebracion().toString()) + Constantes.SALTO_LINEA;
		
		if (dtoEvento.getEvento().getDescripcion() != null)
		info = info + "Evento: " + dtoEvento.getEvento().getDescripcion() + Constantes.SALTO_LINEA;
		
		if (dtoEvento.getDtoCentroDocente().getDtoUniversidad().getUniversidad() != null)	
			info = info + "          Universidad: " + dtoEvento.getDtoCentroDocente().getDtoUniversidad().getUniversidad().getNombre() + Constantes.SALTO_LINEA;
		
		if (dtoEvento.getDtoCentroDocente().getCentroDocente().getNombre() != null)
		info = info + "                     Centro Docente: " + dtoEvento.getDtoCentroDocente().getCentroDocente().getNombre()+ Constantes.SALTO_LINEA;
		
		if (dtoEvento.getDtoCentroDocente().getDtoContacto().getContacto()!= null)
		info = info + "                     Direccion : " + dtoEvento.getDtoCentroDocente().getDtoContacto().getContacto().getDomicilio()+ Constantes.SALTO_LINEA;
		
		info = info + Constantes.SALTO_LINEA;
		
		if (dtoEvento.getEvento().getPrecio() != null)
		info = info + "Precio: " + dtoEvento.getEvento().getPrecio() + Constantes.SALTO_LINEA;
		
		info = info + Constantes.SALTO_LINEA;
		info = info + "Prerequisitos: " +Constantes.SALTO_LINEA;
		info = info + Constantes.SALTO_LINEA;
		info = info + "Quedan: " +Constantes.SALTO_LINEA;
		return info;
	}
	
	
	public void crearInscripcion(){
		DTOInscripcion dtoInscripcion = new DTOInscripcion();
		dtoInscripcion.setDtoEvento(dtoEvento);
		dtoInscripcion.setDtoAsistente((DTOAsistente) dtousuario);
		Inscripcion ins = new Inscripcion();
		ins.setCodigo(dtousuario.getUsuario().getCodigo());
		ins.setEstado(1);
		ins.setIdEvento(dtoEvento.getEvento().getIdEvento());
		ins.setMotivoEstado("ALTA INSCRIPCION");
		//ins.setFechaInscripcion(fechaInscripcion);
		//ins.setFechaEstado(fechaEstado);
		dtoInscripcion.setInscripcion(ins);
		try {
			remote.insertaInscripcion(dtoInscripcion);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OperationErrorBD e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Método que genera los Eventos
	 * 
	 */
	
	public void generaEventos(){
		
		this.findBoton("botonConfirmar").addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearInscripcion();
				System.out.println("EVENTO PARA CONFIRMAR LA INSCRIPCION.....");
			}
		});
		
		this.findBoton("botonVolver").addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					goPantallaBuscarEvento();
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
	}
	
	
	
	

	
	
}
