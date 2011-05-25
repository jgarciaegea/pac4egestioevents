/**
 * 
 */
package uoc.edu.tds.pec4.pantallas;


import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import uoc.edu.tds.pec4.beans.Usuario;
import uoc.edu.tds.pec4.dtos.DTOUsuario;
import uoc.edu.tds.pec4.excepciones.OperationErrorBD;
import uoc.edu.tds.pec4.excepciones.OperationErrorDatosFormulario;
import uoc.edu.tds.pec4.excepciones.OperationErrorLogin;
import uoc.edu.tds.pec4.excepciones.OperationErrorRMI;
import uoc.edu.tds.pec4.gestores.GestorRMI;
import uoc.edu.tds.pec4.iface.RemoteInterface;
import uoc.edu.tds.pec4.resources.TDSLanguageUtils;
import uoc.edu.tds.pec4.utils.Base64Coder;
import uoc.edu.tds.pec4.utils.JTextFieldLimit;
import uoc.edu.tds.pec4.utils.Utils;
/**
 * @author ML019882
 *
 */
public class PantallaLogin extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel panelPrincipal;
    private JButton bOK;
    private JButton bCANCEL;
    private JLabel etiquetaLogin;
    private JLabel etiquetaPwd;
    private JTextField textoLogin;
    private JTextField textoPwd;
	private GestorRMI gestorRMI;
	private RemoteInterface remote;
	private Usuario usuario;
	private DTOUsuario dtoUsuarioEncontrado;
	public static final String CLIENT = "CLIENT";

	
	public PantallaLogin(){
		setSize(487, 280);
	    setLocation(new Point(320, 200));
	    setResizable(false);
	    setTitle(TDSLanguageUtils.getMessage("clientePEC4.frameLogin.titulo"));
	    setContentPane(inicializar());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}
	
	
	private JPanel inicializar(){
		
        if(panelPrincipal == null){
        	
        	panelPrincipal = new JPanel();
            panelPrincipal.setLayout(null);

            bOK =  new JButton();
            bOK.setBounds(new Rectangle(27, 120, 202, 70));
            bOK.setText(TDSLanguageUtils.getMessage("clientePEC4.frameLogin.bOK"));
    		
            bCANCEL = new JButton();
            bCANCEL.setBounds(new Rectangle(255, 120, 202, 70));
            bCANCEL.setText(TDSLanguageUtils.getMessage("clientePEC4.frameLogin.bCANCEL"));
    		

            etiquetaLogin = new JLabel();
            etiquetaLogin.setBounds(new Rectangle(27, 40, 80, 20));
            etiquetaLogin.setFont(new Font("Arial", Font.BOLD, 12));
            etiquetaLogin.setText(TDSLanguageUtils.getMessage("ClientePEC4.login.texto"));
            
            textoLogin = new JTextField();
            textoLogin.setBounds(new Rectangle(120, 40, 180, 20));
            textoLogin.setFont(new Font("Arial", Font.BOLD, 12));

            
            etiquetaPwd = new JLabel();
            etiquetaPwd.setBounds(new Rectangle(27, 70, 80, 20));
            etiquetaPwd.setFont(new Font("Arial", Font.BOLD, 12));
            etiquetaPwd.setText(TDSLanguageUtils.getMessage("ClientePEC4.pwd.texto"));
            
            textoPwd = new JPasswordField();
            textoPwd.setBounds(new Rectangle(120, 70, 180, 20));
            textoPwd.setFont(new Font("Arial", Font.BOLD, 12));
            textoPwd.setDocument(new JTextFieldLimit(8));
            
            panelPrincipal.setBorder(BorderFactory.createCompoundBorder(
    		        BorderFactory.createTitledBorder(TDSLanguageUtils.getMessage("ClientePEC4.panelCredenciales.titulo")), 
    		        BorderFactory.createEmptyBorder(150, 40, 20, 40)));

            panelPrincipal.add(getbOK(), null);
            panelPrincipal.add(getbCANCEL(), null);
            panelPrincipal.add(getEtiquetaPwd(), null);
            panelPrincipal.add(getEtiquetaLogin(), null);
            panelPrincipal.add(getTextoPwd(), null);
            panelPrincipal.add(getTextoLogin(), null);
            generaEventosPantallaLogin();

        }
        return panelPrincipal;
    }
	

	public void generaEventosPantallaLogin()  {
		
		
		bCANCEL.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent evt) { 	
			if (JOptionPane.showConfirmDialog(PantallaLogin.this,TDSLanguageUtils.getMessage("ClientePEC4.cerrar"), null, JOptionPane.YES_NO_OPTION) == 0){
				System.exit(0);
			}
			} });
		
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				if (JOptionPane.showConfirmDialog(PantallaLogin.this,TDSLanguageUtils.getMessage("ClientePEC4.cerrar"), null, JOptionPane.YES_NO_OPTION) == 0){
					//if (srvOn) desconectarServidor();
					System.exit(0);
				}
			}
		}) ; 

		bOK.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent evt) { 
			try {
				validaFormulario();
				connectRMI();
				inicializarAplicacion();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (NotBoundException e) {
				e.printStackTrace();
			} catch (OperationErrorBD e) {
				e.showDialogError();
				e.printStackTrace();
			} catch (OperationErrorDatosFormulario e) {
					e.showDialogError();
			} catch (OperationErrorLogin e) {
				System.out.println("error...sup");
				e.showDialogError();
			} catch (Exception e) {
				e.printStackTrace();
			}	} });
		
	}
	
	/**
	 * Inicializar Aplicacion
	 * @throws RemoteException 
	 * @throws OperationErrorLogin 
	 */
	public void inicializarAplicacion() throws Exception,RemoteException,MalformedURLException,NotBoundException,OperationErrorBD {
		try{
			authenticate(); 	
    		if(dtoUsuarioEncontrado == null){
    			throw new OperationErrorLogin(TDSLanguageUtils.getMessage("clientePEC4.error.login2"));
			} 	
    		if (!dtoUsuarioEncontrado.getUsuario().getContrasena().equals(Base64Coder.encodeString(usuario.getContrasena()))){
    			throw new OperationErrorLogin(TDSLanguageUtils.getMessage("clientePEC4.error.login1"));
			} 	
			PantallaPrincipal aplicacion = new PantallaPrincipal(gestorRMI,remote,dtoUsuarioEncontrado);
			this.setVisible(false);
			this.removeAll();
			aplicacion.setVisible(true);
			//Utils.mostraMensajeInformacion(panelPrincipal, "Usuario logineado correctamente", "Login usuario");
		}catch(OperationErrorLogin ex){
			ex.showDialogError();
		}catch(OperationErrorRMI oL){
			oL.showDialogError();
		}
	}
	
	
	/**
	 * Comprobar Datos
	 * @throws RemoteException 
	 * @throws OperationErrorLogin OperationErrorRMI
	 * @throws OperationErrorRMI 
	 */
	
	public void authenticate() throws OperationErrorLogin, OperationErrorRMI  {
    	try {
		
			usuario = new Usuario();
    		usuario.setCodigo(textoLogin.getText().toUpperCase());
    		usuario.setContrasena(textoPwd.getText());
    		dtoUsuarioEncontrado = remote.loginUsuario(usuario);
		} catch (RemoteException e) {
			throw new OperationErrorRMI(e.getMessage());
		} catch(OperationErrorLogin oL){
			oL.showDialogError();
		}
    }
	
	
	/**
	 * Conexion RMI
	 */
	
	private void connectRMI() throws RemoteException{
		try {
			gestorRMI = new GestorRMI("CLIENT");
			remote = gestorRMI.lookup();
			remote.testConexion();
	    	System.out.println("conectar BBDD");
	    	remote.conectarBBDD();
	    	System.out.println("Conectado a BBDD!");
			
		} catch (OperationErrorRMI e) {
			e.showDialogError();
		} catch (OperationErrorBD e) {
			e.showDialogError();
		}
	}
	
	
	/**
	 * Método que valida los datos introducidos en el formulario
	 * @throws OperationErrorDatosFormulario
	 */
	private void validaFormulario() throws OperationErrorDatosFormulario{
		try{
			if(Utils.valorisNull(textoLogin.getText())) throw new OperationErrorDatosFormulario(Utils.MESSAGE_ERROR + " Login " );
			if(Utils.valorisNull(textoPwd.getText())) throw new OperationErrorDatosFormulario(Utils.MESSAGE_ERROR + " PWD " );
		}catch(OperationErrorDatosFormulario ex){
			ex.showDialogError();
		}
	}
	
	/**
	 * @return the panelPrincipal
	 */
	public JPanel getPanelPrincipal() {
		return panelPrincipal;
	}


	/**
	 * @param panelPrincipal the panelPrincipal to set
	 */
	public void setPanelPrincipal(JPanel panelPrincipal) {
		this.panelPrincipal = panelPrincipal;
	}


	/**
	 * @return the bOK
	 */
	public JButton getbOK() {
		return bOK;
	}


	/**
	 * @param bOK the bOK to set
	 */
	public void setbOK(JButton bOK) {
		this.bOK = bOK;
	}


	/**
	 * @return the bCANCEL
	 */
	public JButton getbCANCEL() {
		return bCANCEL;
	}


	/**
	 * @param bCANCEL the bCANCEL to set
	 */
	public void setbCANCEL(JButton bCANCEL) {
		this.bCANCEL = bCANCEL;
	}


	/**
	 * @return the etiquetaLogin
	 */
	public JLabel getEtiquetaLogin() {
		return etiquetaLogin;
	}


	/**
	 * @param etiquetaLogin the etiquetaLogin to set
	 */
	public void setEtiquetaLogin(JLabel etiquetaLogin) {
		this.etiquetaLogin = etiquetaLogin;
	}


	/**
	 * @return the etiquetaPwd
	 */
	public JLabel getEtiquetaPwd() {
		return etiquetaPwd;
	}


	/**
	 * @param etiquetaPwd the etiquetaPwd to set
	 */
	public void setEtiquetaPwd(JLabel etiquetaPwd) {
		this.etiquetaPwd = etiquetaPwd;
	}


	/**
	 * @return the textoLogin
	 */
	public JTextField getTextoLogin() {
		return textoLogin;
	}


	/**
	 * @param textoLogin the textoLogin to set
	 */
	public void setTextoLogin(JTextField textoLogin) {
		this.textoLogin = textoLogin;
	}


	/**
	 * @return the textoPwd
	 */
	public JTextField getTextoPwd() {
		return textoPwd;
	}

	/**
	 * @param textoPwd the textoPwd to set
	 */
	public void setTextoPwd(JPasswordField textoPwd) {
		this.textoPwd = textoPwd;
	}
		
}
	
	
