/**
 * 
 */
package uoc.edu.tds.pec4.pantallas;


import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import uoc.edu.tds.pec4.resources.TDSLanguageUtils;

/**
 * @author ML019882
 *
 */
public class PantallaLogin extends JFrame {
	
	
	private JPanel panelPrincipal;
    private JButton bOK;
    private JButton bCANCEL;
    private JLabel etiquetaLogin;
    private JLabel etiquetaPwd;
    private JTextField textoLogin;
    private JTextField textoPwd;
	
	public PantallaLogin(){
		setSize(487, 280);
	    setLocation(new Point(320, 200));
	    setResizable(false);
	    setTitle(TDSLanguageUtils.getMessage("clientePEC4.frameLogin.titulo"));
	    setContentPane(Inicializar());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}
	
	
	private JPanel Inicializar(){
		
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
            
            textoPwd = new JTextField();
            textoPwd.setBounds(new Rectangle(120, 70, 180, 20));
            textoPwd.setFont(new Font("Arial", Font.BOLD, 12));

            
            panelPrincipal.setBorder(BorderFactory.createCompoundBorder(
    		        BorderFactory.createTitledBorder(TDSLanguageUtils.getMessage("ClientePEC4.panelCredenciales.titulo")), 
    		        BorderFactory.createEmptyBorder(150, 40, 20, 40)));

            panelPrincipal.add(getbOK(), null);
            panelPrincipal.add(getbCANCEL(), null);
            panelPrincipal.add(getEtiquetaPwd(), null);
            panelPrincipal.add(getEtiquetaLogin(), null);
            panelPrincipal.add(getTextoPwd(), null);
            panelPrincipal.add(getTextoLogin(), null);
            GeneraEventosPantallaLogin();

        }
        return panelPrincipal;
    }
	

	public void GeneraEventosPantallaLogin(){
		
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				if (JOptionPane.showConfirmDialog(PantallaLogin.this,TDSLanguageUtils.getMessage("ClientePEC4.cerrar"), null, JOptionPane.YES_NO_OPTION) == 0){
					//if (srvOn) desconectarServidor();
					System.exit(0);
				}
			}
		}) ; 
		
		
		bOK.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent evt) { IncializarAplicacion();	} });
		
	}
	
	
	public void IncializarAplicacion(){
		
		// TODO COMPROBAR LOGIN
		System.out.println("Inicializando Menus Aplicacion");
		PantallaPrincipal aplicacion = new PantallaPrincipal();
		this.setVisible(false);
		aplicacion.setVisible(true);
		
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
	public void setTextoPwd(JTextField textoPwd) {
		this.textoPwd = textoPwd;
	}
		
		
	}
	
	
