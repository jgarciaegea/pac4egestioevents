package edu.uoc.tds.rmi;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import edu.uoc.tds.gui.TDSFrame;
import edu.uoc.tds.i18n.TDSLanguageUtils;

public class Servidor extends JPanel implements ActionListener
{

	JButton cmdIniciar;
	JButton cmdParar;

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Crea nueva instancia de Servidor rmi */
    public Servidor() {
//    	Crear una pantalla con 2 botones para inciar o detener el servidor
    	TDSLanguageUtils.setDefaultLanguage("edu/uoc/tds/i18n/TextosMensajes/messages");
    	String title = TDSLanguageUtils.getMessage("Servidor.title");
    	int width = 400;
		int height = 300;		
		TDSFrame ventana = new TDSFrame(title,width,height);
		
		JPanel PanelServ= new JPanel();
		JPanel PanelServ2= new JPanel();
		
		JLabel Label = new JLabel(TDSLanguageUtils.getMessage("Label1.text1"));
    	cmdIniciar = new JButton(TDSLanguageUtils.getMessage("Button1.text"));
    	cmdParar = new JButton(TDSLanguageUtils.getMessage("Button2.text"));
    	
    	cmdParar.setEnabled(false);
    	
    	PanelServ.setLayout(new GridLayout(1,1,7,7));
    	PanelServ2.setLayout(new GridLayout(1,2,7,7));
    	
    	//PanelServ.add(Label);
    	PanelServ2.add(cmdIniciar);
    	PanelServ2.add(cmdParar);
		
    	//ventana.add(PanelServ);
    	ventana.add(PanelServ2);
		//Preparar los botones para que se ejecute el evento
    	cmdIniciar.addActionListener((ActionListener) this);
    	cmdParar.addActionListener((ActionListener) this);
		//ventana.getContentPane().add(PanelFra);
		ventana.completarFrame();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       	new Servidor();
    }

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==cmdIniciar) {
			System.out.println("Bton");
		
			// Se indica a rmiregistry dónde están las clases.
			// Cambiar el paht al sitio en el que esté. Es importante
			// mantener la barra al final.
			//System.setProperty ("java.security.policy", "c:/prueba_servidor/java.policy");
			System.setProperty ("java.security.policy", "c:/users/home/documents/uoc/tdp/pac3/pac3/edu/uoc/tds/rmi/java.policy");
			
			
			System.setProperty(
				"java.rmi.server.codebase",
				"file://localhost/pac3/edu/uoc/tds/rmi/");
			
			
			/*
				System.setProperty ("java.security.policy", "c:/prueba_servidor/java.policy");
				System.setProperty ("java.rmi.server.codebase",
	            "file://localhost/prueba_servidor/");
			*/
			try 
		    {
				
	            /* Se instala un SecurityManager si no lo hay. Sin él no podremos
	             * registrar nuestro ObjetoRemoto por falta de permisos. */
	            if (System.getSecurityManager()==null)
	                System.setSecurityManager(new RMISecurityManager());
	            
	            /* Se instancia un ObjetoRemoto */
	            ObjetoRemoto objetoRemoto = new ObjetoRemoto();
	            
	            /* Se registra el ObjetoRemoto */
	            Naming.rebind ("ObjetoRemoto", objetoRemoto);
	            //Habilitar el botón cmdPara
	            cmdParar.setEnabled(true);
	        
		    } catch (Exception error)
	        {
	            error.printStackTrace();
	        }
		} else if (e.getSource()==cmdParar) {
				JOptionPane.showMessageDialog(null, 
						TDSLanguageUtils.getMessage("Panel3.dialog2"),
						TDSLanguageUtils.getMessage("Panel3.dialogsTitle"),
						JOptionPane.ERROR_MESSAGE);
			}
	}
}
