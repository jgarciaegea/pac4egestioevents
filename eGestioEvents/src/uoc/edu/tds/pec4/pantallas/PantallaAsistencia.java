/**
 * 
 */
package uoc.edu.tds.pec4.pantallas;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.Vector;

/**
 * @author ML019882
 *
 */
public class PantallaAsistencia extends PanelComun{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 

	
	/**
	 * Constructor
	 */
	
	
	public PantallaAsistencia() {
		super("clientePEC4.panelAsistencia.titulo",300,500);
		// indicamos X, Y , ancho , largo , titulo (si es el caso) y el idSTring del elemento 
		// creamos elementos
		this.crearBoton(40, 50, 220, 40, "clientePEC4.panelAsistencia.boton.buscar","bBuscar");
		this.crearTitulo(40, 100, 80, 40, "clientePEC4.panelAsistencia.boton.buscar", "titulo1");
		this.crearTextField(150, 100, 80, 40,"caja1");
		 Vector vector=new Vector(); 
		 vector.addElement("uno");
		 vector.addElement("dos");
		
		this.crearCombo(40, 130, 80, 20, "combo1", vector);
		this.crearTabla(50, 200, 200, 150);
		
		// anyadimos un evento al boton
		this.findBoton("bBuscar").addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("ESTO ESS UN EVENTO QUE ANYADIMOS AL ELEMENTO BUSCADO");
			}
		});
	}
	
	

	
}

