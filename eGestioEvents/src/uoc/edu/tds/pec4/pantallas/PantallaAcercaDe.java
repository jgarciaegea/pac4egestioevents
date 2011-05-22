package uoc.edu.tds.pec4.pantallas;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//PRIMERO CREAMOS UNA CLASE QUE HERE DE LA CLASE JDialog
class PantallaAcercaDe extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//AHORA CREAMOS LOS COMPONENTES QUE NECESITAMOS
	JLabel programa = new JLabel("Gestión de Eventos de Universidades", JLabel.CENTER);
	JLabel autor = new JLabel("DESARROLLADO POR: Albert Soldevila", JLabel.CENTER);
	JLabel autor2 = new JLabel("                  Jonathan Garcia", JLabel.CENTER);
	JLabel autor3 = new JLabel("                  José Garcia", JLabel.CENTER);
	JLabel autor4 = new JLabel("                  Miquel Luis", JLabel.CENTER);
	JLabel autor5 = new JLabel("                  Susana Roig", JLabel.CENTER);
	
	JLabel derechos = new JLabel("Copyright 2011. Todos los derechos reservados", JLabel.CENTER);
	JButton aceptar = new JButton("Aceptar");

	JPanel principal = new JPanel(new BorderLayout());
	JPanel info = new JPanel(new GridLayout(7, 1));
	JPanel boton = new JPanel(new FlowLayout());


	//CONSTRUCTOR DE LA CLASE
	public PantallaAcercaDe(){
		super(new Frame(), "Acerca de...", true);

		//getContentPane().setLayout(new GridLayout(3, 1));
		//AGREGAMOS AL PANEL info, LAS TRES ETIQUETAS QUE CREAMOS
		info.add(programa);
		info.add(autor);
		info.add(autor2);
		info.add(autor3);
		info.add(autor4);
		info.add(autor5);
		info.add(derechos);

		boton.add(aceptar);

		principal.add("Center", info);
		principal.add("South", boton);

		
		getContentPane().add(principal);
		pack();

		//INDICAMOS QUE NO PUEDAN CAMBIAR EL TAMA¥O DEL DIALOGO CON EL MOUSE
		setResizable(false);

		//CENTRAMOS EL DIALOGO EN LA PANTALLA
		Dimension pantalla, cuadro;
		pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		cuadro = this.getSize();

		this.setLocation(((pantalla.width - cuadro.width)/2), (pantalla.height - cuadro.height)/2);


		//LE AGREGAMOS EL EVENTO AL BOTON
		aceptar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				dispose();
			}
		});

	}//FIN DEL CONSTRUCTOR DE LA CLASE Dialogo

}//FIN DE LA CLASE Dialogo

