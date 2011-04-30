package edu.uoc.tds.gui;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**  
 * Técnicas de Desarrollo de Software
 * Universitat Oberta de Catalunya (UOC)
 * @
 * @author Jonathan Garcia Fernandez  
 * @version 1.0
 * 
 */
public class TDSFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public TDSFrame(String title, int width, int height) {
		super(title);
		getContentPane().setPreferredSize(new Dimension(width,height));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void nuevoPanel(JPanel panel) {
		getContentPane().add(panel);
	}
	
	public void completarFrame() {
		pack();	
		setVisible(true);	
	}	
}