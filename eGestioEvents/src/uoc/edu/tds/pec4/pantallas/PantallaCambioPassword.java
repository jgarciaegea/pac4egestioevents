/**
 * 
 */
package uoc.edu.tds.pec4.pantallas;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import uoc.edu.tds.pec4.dtos.DTOUsuario;
import uoc.edu.tds.pec4.excepciones.OperationErrorDatosFormulario;
import uoc.edu.tds.pec4.excepciones.OperationErrorLogin;
import uoc.edu.tds.pec4.iface.RemoteInterface;
import uoc.edu.tds.pec4.utils.Base64Coder;
import uoc.edu.tds.pec4.utils.Utils;

/**
 * @author ML019882
 *
 */
public class PantallaCambioPassword extends PanelComun implements Pantallas{

	private static final long serialVersionUID = 1L; 
	private RemoteInterface remote;
	private DTOUsuario dtoUsuario;
	
	/**
	 * 
	 */
	public PantallaCambioPassword(RemoteInterface remote1,DTOUsuario dtoUsuario1)throws OperationErrorLogin {
		super("clientePEC4.panelCambioPassword.titulo",400,210);
		remote = remote1;
		dtoUsuario = dtoUsuario1;
		if (dtoUsuario == null) throw new OperationErrorLogin("La session es invalida.....");
		try {
			remote.testConexion();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		this.crearTitulo(20, 30, 160, 20, "clientePEC4.panelCamioPassword.titulo1.Usuario", "tUsuario");
		this.crearTextField(200, 30, 140, 20,"cajaUsuario");
		this.findTextField("cajaUsuario").setText(dtoUsuario.getUsuario().getCodigo());
		this.findTextField("cajaUsuario").setEditable(false);
		
		this.crearTitulo(20, 60, 160, 20, "clientePEC4.panelCamioPassword.titulo2.Pwd", "tPwd");
		this.crearJPasswordField(200, 60, 140, 20,"cajaPwd");
		
		this.crearTitulo(20, 90, 160, 20, "clientePEC4.panelCamioPassword.titulo3.PwdNew", "tPwdNew");
		this.crearJPasswordField(200, 90, 140, 20,"cajaPwdNew");
		
		this.crearTitulo(20, 120, 160, 20, "clientePEC4.panelCamioPassword.titulo4.RPwdNew", "RPwdNew");
		this.crearJPasswordField(200, 120, 140, 20,"cajaRPwdNew");
		
		this.crearBoton(20, 160, 120, 30, "clientePEC4.panelCambioPassword.boton.bOK","bOK");
		this.crearBoton(200, 160, 120, 30, "clientePEC4.panelCambioPassword.boton.bCancel", "bCancel");
		this.setAlignmentX(RIGHT_ALIGNMENT);
		generaEventos();
		
		
	}
	
	public void acutalizaPassword() throws OperationErrorLogin, RemoteException, OperationErrorDatosFormulario{
		validaFormulario();
		
		String passNueva = this.findJPasswordField("cajaRPwdNew").getText();
		passNueva = Base64Coder.encodeString(passNueva);
		
		String passActual = this.findJPasswordField("cajaPwd").getText();
		passActual =  Base64Coder.encodeString(passActual);
		
		if (!passActual.equals(dtoUsuario.getUsuario().getContrasena())){
			throw new OperationErrorLogin("Contrasenya Actual incorrecta");
		}  
		dtoUsuario.getUsuario().setContrasena(passNueva);
		remote.updatePassword(dtoUsuario);
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
				removeAll();
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
