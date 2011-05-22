/**
 * 
 */
package uoc.edu.tds.pec4.pantallas;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;


import uoc.edu.tds.pec4.beans.Usuario;
import uoc.edu.tds.pec4.excepciones.OperationErrorDatosFormulario;
import uoc.edu.tds.pec4.excepciones.OperationErrorLogin;
import uoc.edu.tds.pec4.gestores.GestorRMI;
import uoc.edu.tds.pec4.iface.RemoteInterface;
import uoc.edu.tds.pec4.resources.TDSLanguageUtils;
import uoc.edu.tds.pec4.utils.Base64Coder;
import uoc.edu.tds.pec4.utils.Utils;

/**
 * @author ML019882
 *
 */
public class PantallaCambioPassword extends PanelComun implements Pantallas{

	private static final long serialVersionUID = 1L; 
	private RemoteInterface remote;
	private Usuario usuario;
	
	/**
	 * 
	 */
	public PantallaCambioPassword(GestorRMI gestorRMI,RemoteInterface remote1,Usuario usuario1)throws OperationErrorLogin {
		// TODO Auto-generated constructor stub
		super("clientePEC4.panelCambioPassword.titulo",450,260);
		remote = remote1;
		usuario = usuario1;
		if (usuario1 == null) throw new OperationErrorLogin("La session es invalida.....");
		try {
			remote.testConexion();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.crearTitulo(20, 30, 140, 20, "clientePEC4.panelCamioPassword.titulo1.Usuario", "tUsuario");
		this.crearTextField(160, 30, 140, 20,"cajaUsuario");
		this.findTextField("cajaUsuario").setText(usuario.getCodigo());
		this.findTextField("cajaUsuario").setEditable(false);
		
		this.crearTitulo(20, 60, 140, 20, "clientePEC4.panelCamioPassword.titulo2.Pwd", "tPwd");
		this.crearJPasswordField(160, 60, 140, 20,"cajaPwd");
		
		this.crearTitulo(20, 90, 140, 20, "clientePEC4.panelCamioPassword.titulo3.PwdNew", "tPwdNew");
		this.crearJPasswordField(160, 90, 140, 20,"cajaPwdNew");
		
		this.crearTitulo(20, 120, 140, 20, "clientePEC4.panelCamioPassword.titulo4.RPwdNew", "RPwdNew");
		this.crearJPasswordField(160, 120, 140, 20,"cajaRPwdNew");
		
		this.crearBoton(50, 160, 80, 40, "clientePEC4.panelCambioPassword.boton.bOK","bOK");
		this.crearBoton(160, 160, 80, 40, "clientePEC4.panelCambioPassword.boton.bCancel", "bCancel");
		this.setAlignmentX(RIGHT_ALIGNMENT);
		generaEventos();
		
		
	}
	
	public void acutalizaPassword() throws OperationErrorLogin, RemoteException, OperationErrorDatosFormulario{
		validaFormulario();
		
		String passNueva = this.findJPasswordField("cajaRPwdNew").getText();
		passNueva = Base64Coder.encodeString(passNueva);
		
		String passActual = this.findJPasswordField("cajaPwd").getText();
		passActual =  Base64Coder.encodeString(passActual);
		
		if (!passActual.equals(usuario.getContrasena())){
			throw new OperationErrorLogin("Contrasenya Actual incorrecta");
		}  
		usuario.setContrasena(passNueva);
		remote.updatePassword(usuario);
	}
	
	
	
	/**
	 * Método que genera los Eventos
	 * 
	 */
	

	public void generaEventos(){
		
		this.findBoton("bOK").addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					acutalizaPassword();
				} catch (OperationErrorLogin e1) {
					// TODO Auto-generated catch block
					e1.showDialogError();
				} catch (RemoteException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (OperationErrorDatosFormulario e3) {
					// TODO Auto-generated catch block
						e3.showDialogError();
				}
			}
		});
		
		this.findBoton("bCancel").addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				System.out.println("EVENTO PARA EL Cancel");
			}
		});
	}
	
	
	/**
	 * Método que valida los datos introducidos en el formulario
	 * @throws OperationErrorDatosFormulario
	 */
	

	private void validaFormulario() throws OperationErrorDatosFormulario{
		try{
					
		if(Utils.valorisNull(this.findJPasswordField("cajaPwd").getText())) throw new OperationErrorDatosFormulario(Utils.MESSAGE_ERROR + " Password Actual");
		if(Utils.valorisNull(this.findJPasswordField("cajaPwdNew").getText())) throw new OperationErrorDatosFormulario(Utils.MESSAGE_ERROR + " Password Nova");
		if(Utils.valorisNull(this.findJPasswordField("cajaRPwdNew").getText())) throw new OperationErrorDatosFormulario(Utils.MESSAGE_ERROR + " Repetir Password ");
		if (!this.findJPasswordField("cajaPwdNew").getText().equals(this.findJPasswordField("cajaRPwdNew").getText())){
			throw new OperationErrorDatosFormulario(Utils.MESSAGE_ERROR + " la Nueva Password no coincide ");
		}
			
		}catch(OperationErrorDatosFormulario ex){
			throw new OperationErrorDatosFormulario(ex.getMessage());
		}
			
	}
	
	public void borrarPanel(){
		this.setBorder(null);
		this.removeAll();
		this.setAlignmentX(LEFT_ALIGNMENT);
		this.setAlignmentY(TOP_ALIGNMENT);
		this.repaint();
		this.revalidate();
		this.updateUI();
	}
	
	
}
