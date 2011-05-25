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


import uoc.edu.tds.pec4.dtos.DTOAsistente;
import uoc.edu.tds.pec4.dtos.DTOEvento;
import uoc.edu.tds.pec4.dtos.DTOInscripcion;
import uoc.edu.tds.pec4.dtos.DTOUsuario;
import uoc.edu.tds.pec4.excepciones.OperationErrorBD;
import uoc.edu.tds.pec4.excepciones.OperationErrorLogin;
import uoc.edu.tds.pec4.iface.RemoteInterface;


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


	public PantallaInscripcion(RemoteInterface remote1,DTOUsuario dtousuario1, DTOEvento dtoEvento1) throws OperationErrorLogin, RemoteException, OperationErrorBD{
		super("clientePEC4.panelInscripcion.titulo",750,600);
		System.out.println("creando Pantalla inscripcion......");
		remote = remote1;
		dtousuario = dtousuario;
		dtoEvento = dtoEvento1;

		if (dtousuario == null) throw new OperationErrorLogin("La session es invalida.....");
		try {
			remote.testConexion();
		} catch (RemoteException e1) {			
			e1.printStackTrace();
		}
		
		this.crearTitulo(20, 30, 140, 20, "clientePEC4.panelInscripcion.titulo1.datosEvento", "datosEvento");
		
		this.crearJTextArea(30, 60, 600, 200, "informacionEventoInscripcion");
		this.findJTextAreaString("informacionEventoInscripcion").setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.findJTextAreaString("informacionEventoInscripcion").setEditable(false);
		
		this.crearTitulo(20, 300, 140, 20, "clientePEC4.panelInscripcion.titulo1.nombreAsistente", "nombreAsistente");
		this.crearTextField(160, 300, 250, 20,"cajaAsistente");
		this.findTextField("cajaAsistente").setText(dtousuario.getUsuario().getNombre()+ ", " + dtousuario.getUsuario().getApellidos());
		this.findTextField("cajaAsistente").setEditable(false);
		
		
		this.crearTitulo(20, 330, 140, 20, "clientePEC4.panelInscripcion.titulo1.nombreDatosBancarios", "nombreDatosBancarios");
		this.crearTextField(160, 330, 250, 20,"cajaDatosBancarios");
		this.findTextField("cajaDatosBancarios").setText("PENDIENTE DE RELLENAR CON DATOS BANCARIOS");
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
	
	
	public void crearInscripcion(){
		DTOInscripcion dtoInscripcion = new DTOInscripcion();
		dtoInscripcion.setDtoEvento(dtoEvento);
		dtoInscripcion.setDtoAsistente((DTOAsistente) dtousuario);
	}
	
	
	/**
	 * Método que genera los Eventos
	 * 
	 */
	
	public void generaEventos(){
		
		this.findBoton("botonConfirmar").addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
