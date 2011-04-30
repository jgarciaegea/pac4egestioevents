package edu.uoc.tds.gui;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import edu.uoc.tds.comun.ConceptoGasto;
import edu.uoc.tds.comun.Evento;
import edu.uoc.tds.comun.Factura;
import edu.uoc.tds.comun.Proveedor;
import edu.uoc.tds.i18n.TDSLanguageUtils;
import edu.uoc.tds.rmi.ClientePec3;

public class Pantalla extends JPanel implements ActionListener  {
	
	Factura Factura=new Factura();
	
	JLabel label1;
	JLabel label2;
	JLabel label3;
	JLabel label4;
	JLabel label5;
	JLabel label6;
	//Crear los objetos combobox
	JComboBox cboEvento;
	JComboBox cboProveedor;
	JComboBox cboConcepto;
	//Crear los objetos textbox
	JTextArea txtFechaValor;
	JTextArea txtDescripcion;
	JTextArea txtImporte;
	
	JButton cmdGrabar;
	JButton cmdCancelar;
	Connection conn;
	
	JPanel PanelFra;
	private Container Menu;
		
	private static final long serialVersionUID = 1L;	
		
		public Pantalla() {
			
			TDSLanguageUtils.setDefaultLanguage("edu/uoc/tds/i18n/TextosMensajes/messages");
		}
	
	/**
	* 		Jonathan. Método que pinta una pantalla para la introducción de datos de una factura
	 * @return 
	 * @throws SQLException 
    *
    */
	public void setConnection (Connection con){
		conn = con;
	}
	
	public void GuiMenu () throws SQLException  {
		TDSLanguageUtils.setDefaultLanguage("edu/uoc/tds/i18n/TextosMensajes/messages");
        //TDSLanguageUtils.setLanguage("edu/uoc/tds/i18n/TextosMensajes/messages");
        new ClientePec3().setVisible(true);
	}
	
	/**
	 * Método que genera la pantalla para la alta de facturas
	 * @param PantallaPrincipal
	 * @throws SQLException
	 */
	public void GuiFactura(Container PantallaPrincipal) throws SQLException {
			this.Menu = PantallaPrincipal;
			
			PanelFra= new JPanel();
			
			//Crear los objetos labels
			label1 = new JLabel(TDSLanguageUtils.getMessage("Frame1.label1") + "  ",JLabel.RIGHT);
			label2 = new JLabel(TDSLanguageUtils.getMessage("Frame1.label2") + "  ",JLabel.RIGHT);
			label3 = new JLabel(TDSLanguageUtils.getMessage("Frame1.label3") + "  ",JLabel.RIGHT);
			label4 = new JLabel(TDSLanguageUtils.getMessage("Frame1.label4") + "  ",JLabel.RIGHT);
			label5 = new JLabel(TDSLanguageUtils.getMessage("Frame1.label5") + "  ",JLabel.RIGHT);
			label6 = new JLabel(TDSLanguageUtils.getMessage("Frame1.label6") + "  ",JLabel.RIGHT);
			//Crear los objetos textbox
			txtFechaValor = new JTextArea();
			txtDescripcion = new JTextArea();
			txtImporte = new JTextArea();
			txtFechaValor.setBorder(BorderFactory.createLineBorder(Color.black));
			txtDescripcion.setBorder(BorderFactory.createLineBorder(Color.black));
			txtImporte.setBorder(BorderFactory.createLineBorder(Color.black));
			//Crear los objtes botones
			cmdGrabar = new JButton(TDSLanguageUtils.getMessage("Frame1.button1"));
			cmdCancelar = new JButton(TDSLanguageUtils.getMessage("Frame1.button2"));
			PanelFra.setLayout(new GridLayout(7,2,30,30));
			PanelFra.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			
			Evento objEvento = new Evento();
			List<Evento> ListEventos=objEvento.getListEventos(conn);
			//Cargar los eventos de la BD en el combobox
			cboEvento = new JComboBox();
			for (Evento evento: ListEventos){
				cboEvento.addItem(evento);
			}
			//Cargar los proveedores de la BD en el combobox
			cboProveedor = new JComboBox();
			Proveedor objProveedor = new Proveedor();
			List<Proveedor> ListProveedores=objProveedor.getListProveedores(conn);
			for (Proveedor proveedor: ListProveedores){
				cboProveedor.addItem(proveedor);
			}
			//Cargar los concepos de la BD en el combobox
			cboConcepto = new JComboBox();
			ConceptoGasto objConcepto = new ConceptoGasto();
			List<ConceptoGasto> ListConceptos=objConcepto.getListConceptos(conn);
			for (ConceptoGasto concepto: ListConceptos){
				cboConcepto.addItem(concepto);
			}
			
			cboEvento.setSelectedIndex(0);  
			PanelFra.add(label1); 
			PanelFra.add(cboEvento);
			PanelFra.add(label2);
			PanelFra.add(cboProveedor);
			PanelFra.add(label3);
			PanelFra.add(cboConcepto);
			PanelFra.add(label4);
			PanelFra.add(txtFechaValor);
			PanelFra.add(label5);
			PanelFra.add(txtDescripcion);
			PanelFra.add(label6);
			PanelFra.add(txtImporte);
			PanelFra.add(cmdGrabar);
			PanelFra.add(cmdCancelar);
			
			PantallaPrincipal.add(PanelFra);
			PanelFra.setVisible(true);
			//Preparar los botones para que se ejecute el evento
			cmdGrabar.addActionListener(this);
			cmdCancelar.addActionListener(this);
			
		}
		/**
		 * Evento que se ejecuta cuando se pulsa sobre un btón
		 * @param e
		 */
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource()==cmdGrabar) {
				
				int id_evento;
				int id_proveedor;
				int id_concepto;
				String descripcion;
				java.sql.Date FechaValor;
				double importe;
				
			try {
				//Recuperar los valores de la pantalla para insertar la factura en la BD
				Evento EventoSeleccionado  = (Evento) cboEvento.getSelectedItem();	
				id_evento = Integer.parseInt(String.valueOf(EventoSeleccionado.getIdEvento()));
				
				Proveedor ProveedorSeleccionado  = (Proveedor) cboProveedor.getSelectedItem();
				
				id_proveedor = Integer.parseInt(String.valueOf(ProveedorSeleccionado.getIdProveedor())); 
				
				ConceptoGasto ConceptoSeleccionado = (ConceptoGasto) cboConcepto.getSelectedItem();
				
				id_concepto = Integer.parseInt(String.valueOf(ConceptoSeleccionado.getIdConcepto())); 
				
				
				Date utilDate = new Date();
//				DateFormat dformat;
				SimpleDateFormat dfFormat = new SimpleDateFormat("dd-MM-yyyy");
//				 parse string into a DATE format
				utilDate = dfFormat.parse(txtFechaValor.getText());
//				 convert a util.Date to milliseconds via its getTime() method
				long time = utilDate.getTime();
//				 get the long value of java.sql.Date
				FechaValor = new java.sql.Date(time);
				descripcion = txtDescripcion.getText();
				importe = Double.parseDouble(txtImporte.getText());
				Factura fra = new Factura(id_concepto,id_evento,id_proveedor,descripcion,FechaValor,importe);   				
				fra.InsertFactura(conn);
				
				JOptionPane.showMessageDialog(null, 
						TDSLanguageUtils.getMessage("Mensaje1.label"),
						TDSLanguageUtils.getMessage("Mensaje1.text"),
						JOptionPane.INFORMATION_MESSAGE);
				
				}catch (Exception ex) {
					System.out.println("Error al introducir la fecha: El formato válido es dd-mm-yyyy " + ex.getMessage());}
					JOptionPane.showMessageDialog(null, 
						TDSLanguageUtils.getMessage("Error3.label"),
						TDSLanguageUtils.getMessage("Error3.text"),
						JOptionPane.ERROR_MESSAGE);
			}
			else if (e.getSource()==cmdCancelar) {
				PanelFra.setVisible(false);
				this.Menu.getComponent(0).setVisible(true);
				}
		}
	}
