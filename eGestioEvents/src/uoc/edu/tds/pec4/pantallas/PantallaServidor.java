/**
 * 
 */
package uoc.edu.tds.pec4.pantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import uoc.edu.tds.pec4.excepciones.OperationErrorBD;
import uoc.edu.tds.pec4.excepciones.OperationErrorRMI;
import uoc.edu.tds.pec4.gestores.GestorRMI;
import uoc.edu.tds.pec4.resources.TDSLanguageUtils;

/**
 * @author ML019882
 *
 */
public class PantallaServidor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane;
    private JButton bStart;
    private JButton bStop;
    private JPanel pInfo11;
    private JLabel etiqueta1;
    private GestorRMI gestorRMI = null;
    
	public PantallaServidor() throws OperationErrorRMI,OperationErrorBD{
		gestorRMI = new GestorRMI();
        System.out.println("Gestor RMI Inicializado");
        jContentPane = null;
        bStart = null;
        bStop = null;
        pInfo11 = null;
        etiqueta1 = null;
        incicializarPantalla();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

	}
    
	/**
	 * Inicializar la pantalla inicial de encender el servidor
	 * @throws OperationErrorRMI
	 */
	private void incicializarPantalla() throws OperationErrorRMI{
		setSize(487, 196);
	    setLocation(new Point(320, 100));
	    setResizable(false);
	    setTitle(TDSLanguageUtils.getMessage("servidorPEC4.frame.titulo"));
	    setContentPane(getJContentPane());
	}
	
	
	/**
	 * Método que conectará al servidor
	 * @throws OperationErrorRMI
	 */
	private void connect() throws OperationErrorRMI{
		try{
			gestorRMI.connectRMI();
			bStart.setEnabled(false);
			bStop.setEnabled(true);
			etiqueta1.setForeground(new Color(0, 0, 255));
			etiqueta1.setText(TDSLanguageUtils.getMessage("servidorPEC4.iniciado.correctamente.texto"));
		}catch (OperationErrorRMI e) {
			throw e;
		}
	}
	
	/**
	 * Método que desconectará del servidor
	 * @throws OperationErrorRMI 
	 * @throws OperationErrorRMI
	 */
	private void disconnect() throws OperationErrorRMI{
		try{
			gestorRMI.disconnectRMI();
			bStart.setEnabled(true);
			bStop.setEnabled(false);
			etiqueta1.setForeground(new Color(255, 0, 0));
			etiqueta1.setText(TDSLanguageUtils.getMessage("servidorPEC4.parado.correctamente.texto"));
		}catch (OperationErrorRMI e) {
			throw e;
		}
	}
	
	
	
	/*
	 * Configuración del panel principal
	 */
	private JPanel getJContentPane() throws OperationErrorRMI{
        if(jContentPane == null){
            jContentPane = new JPanel();
            
            //Botones Start y Stop
            bStart =  new JButton();
    		bStart.setBounds(new Rectangle(27, 67, 202, 70));
    		bStart.setText(TDSLanguageUtils.getMessage("servidorPEC4.boton1.iniciar.titulo"));
    		
    		bStop = new JButton();
    		bStop.setBounds(new Rectangle(255, 67, 202, 70));
    		bStop.setText(TDSLanguageUtils.getMessage("servidorPEC4.boton2.detener.titulo"));
    		bStop.setEnabled(false);
    		
            jContentPane.setLayout(null);
            jContentPane.add(getPInfo11(), null);
            jContentPane.add(getbStart(), null);
            jContentPane.add(getbStop(), null);

        }
        return jContentPane;
    }
	
	/*
	 * Configuración del panel
	 */
	private JPanel getPInfo11(){
		if(pInfo11 == null){
            GridBagConstraints gridbagconstraints = new GridBagConstraints();
            gridbagconstraints.gridx = 0;
            gridbagconstraints.gridy = 0;
            
            //Etiqueta1
            etiqueta1 = new JLabel();
            etiqueta1.setDisplayedMnemonic(0);
        	etiqueta1.setForeground(new Color(255, 0, 0));
			etiqueta1.setText(TDSLanguageUtils.getMessage("servidorPEC4.parado.correctamente.texto"));
			
            //Panel Info1
            pInfo11 = new JPanel();
            pInfo11.setLayout(new GridBagLayout());
            pInfo11.setBounds(new Rectangle(27, 15, 425, 40));
            pInfo11.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(null, TDSLanguageUtils.getMessage("servidorPEC4.panel1.titulo"), 0, 0, new Font("Dialog", 1, 12), new Color(51, 51, 51)), null), null));
            pInfo11.add(etiqueta1, gridbagconstraints);
        }
        return pInfo11;
    }
	
	
	
	/*
	 * Configuración del botón de encender servidor
	 */
	private JButton getbStart() throws OperationErrorRMI{
		bStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					connect();
				} catch (OperationErrorRMI e1) {
					e1.showDialogError();
				}
			}
		});
		return bStart;
	}
	
	/*
	 * Configuración del botón de parar servidor
	 */
	private JButton getbStop() {
		bStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					disconnect();
				} catch (OperationErrorRMI e1) {
					e1.showDialogError();
				}
			}
		});
		return bStop;
	}
	
}
