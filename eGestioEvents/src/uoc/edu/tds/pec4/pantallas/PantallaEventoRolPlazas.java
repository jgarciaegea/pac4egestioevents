package uoc.edu.tds.pec4.pantallas;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import uoc.edu.tds.pec4.beans.EventoRolPlazas;
import uoc.edu.tds.pec4.beans.TipoEvento;
import uoc.edu.tds.pec4.beans.TipoRol;
import uoc.edu.tds.pec4.dtos.DTOEvento;
import uoc.edu.tds.pec4.dtos.DTOEventoRolPlazas;
import uoc.edu.tds.pec4.dtos.DTOTipoEvento;
import uoc.edu.tds.pec4.dtos.DTOTipoEventoRol;
import uoc.edu.tds.pec4.dtos.DTOTipoRol;
import uoc.edu.tds.pec4.excepciones.OperationErrorDatosFormulario;
import uoc.edu.tds.pec4.iface.RemoteInterface;
import uoc.edu.tds.pec4.utils.Utils;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class PantallaEventoRolPlazas extends javax.swing.JDialog {
	private static final long serialVersionUID = -5673875541686515197L;
	private JPanel jPanelBase;
	private JScrollPane jScrollPane1;
	private JButton jButtonLimpiar;
	private JButton jButtonCancelar;
	private JButton jButtonAceptar;
	private JTable jTableDatos;
	private String[] columnNames = {"idRol", "Rol", "Plazas"};
	private DefaultTableModel dtm;
	
	private RemoteInterface remote;
	private DTOEvento dtoEvento;
	private Boolean bExit = false;
	private Boolean bIsEmpty = false;

	public PantallaEventoRolPlazas(JFrame frame, RemoteInterface remote1, DTOEvento dtoEvento1) {
		super(frame);
		initGUI(frame);
		this.remote = remote1;
		this.dtoEvento = dtoEvento1;

		if (dtoEvento != null && dtoEvento.getEvento() != null){
			try
			{
				// TODO 1: si el tipo de evento no tiene tripoeventorol pues no hacemos nada y salimos
				cargaDatosRoles();
				cargaRolPlazasActual();
				bIsEmpty = (jTableDatos.getRowCount() == 0);
			} catch (OperationErrorDatosFormulario e3) {
				bIsEmpty = true;
				e3.showDialogError(jPanelBase);
			}
		}
	}

	public Boolean isEmpty(){
		return bIsEmpty;
	}
	
	private void cargaRolPlazasActual() {
		for (int a=0; a<jTableDatos.getRowCount(); a++){
			int valor = Integer.parseInt((jTableDatos.getValueAt(a,0).toString()));
	    	List<DTOEventoRolPlazas> lstDTO = dtoEvento.getDtoEventoRolPlazas();
	    	if(lstDTO != null && lstDTO.size() > 0){
	    		for(DTOEventoRolPlazas dtoER : lstDTO){
	    			if (valor == dtoER.getDtoTipoRol().getTipoRol().getIdRol()){ 
	    				jTableDatos.setValueAt(new Integer(dtoER.getEventoRolPlazas().getPlazas()), a, 2);
	    			}
	    		}
	        }
		}
	}
	public List<DTOEventoRolPlazas> getDTOEventoRolPlazas() {
		List<DTOEventoRolPlazas> lstDtoEventoRolPlazas = new ArrayList<DTOEventoRolPlazas>();

		for (int a=0; a<jTableDatos.getRowCount(); a++){
			DTOEventoRolPlazas dtoEventoRolPlazas = new DTOEventoRolPlazas();
			DTOTipoRol dtoTipoRol = new DTOTipoRol();
				
			TipoRol tipoRol = new TipoRol();
			tipoRol.setIdRol(Integer.parseInt((jTableDatos.getValueAt(a,0).toString())));
			tipoRol.setDescripcion(jTableDatos.getValueAt(a,1).toString());
			dtoTipoRol.setTipoRol(tipoRol);
			dtoEventoRolPlazas.setDtoTipoRol(dtoTipoRol);
				
			EventoRolPlazas eventoRolPlazas = new EventoRolPlazas();
			eventoRolPlazas.setIdRol(tipoRol.getIdRol());
			eventoRolPlazas.setPlazas(Integer.parseInt((jTableDatos.getValueAt(a,2).toString())));
			dtoEventoRolPlazas.setEventoRolPlazas(eventoRolPlazas);
				
			lstDtoEventoRolPlazas.add(dtoEventoRolPlazas);
		}
		return ((jTableDatos.getRowCount() >0)?lstDtoEventoRolPlazas:null);
	}
	
	private DTOTipoEvento consultaTipoEvento() {
		DTOTipoEvento dtoTipoEventro = new DTOTipoEvento();
		TipoEvento tipoEvento = new TipoEvento();
		tipoEvento.setIdTipoEvento(dtoEvento.getEvento().getIdTipoEvento());
		dtoTipoEventro.setTipoEvento(tipoEvento);

		return dtoTipoEventro;
	}
	
	private void cargaDatosRoles() throws OperationErrorDatosFormulario{
		try{
			dtm.getDataVector().removeAllElements();
			
			DTOTipoEvento dtoTipoEvento = remote.getTipoEvento(consultaTipoEvento());
			if(dtoTipoEvento == null){
				Utils.mostraMensajeInformacion(jPanelBase, "El Tipo de evento no tiene roles asignados", "Evento Rol/Plazas");
				return;
			}
			muestraResultado(dtoTipoEvento.getDtoTipoEventoRol());
			actualizaTabla();
			
		}catch(Exception e){
			throw new OperationErrorDatosFormulario("Error en la carga de los datos del evento en Rol/Plazas");
		}	
	}
	
	private void muestraResultado(List<DTOTipoEventoRol> lstDtoTipoEventoRol) throws OperationErrorDatosFormulario{
		try{
			dtm.getDataVector().removeAllElements();
		     
			Object[][] aobj = new Object[lstDtoTipoEventoRol.size()][columnNames.length];
			int k = 0;
			if(lstDtoTipoEventoRol != null){
				for(DTOTipoEventoRol dtoTipoEventoRol : lstDtoTipoEventoRol){
					 aobj[k][0] = new String(dtoTipoEventoRol.getDtoTipoRol().getTipoRol().getIdRol().toString());
					 aobj[k][1] = new String(dtoTipoEventoRol.getDtoTipoRol().getTipoRol().getDescripcion());
					 aobj[k][2] = new Integer(0);
					 k++;
	       	 	}
				
				if (aobj != null && aobj.length > 0){
	       	 		for(int row = 0; row < aobj.length; row++){
	       	 			dtm.addRow(aobj[row]);
	       	 		}
	       	 	}
			}
		}catch(Exception e){
			throw new OperationErrorDatosFormulario("Error en la carga de los datos del evento en Rol/Plazas en la pantalla");
		}
	}
	
	private void limpiaFormulario(){
		//ClearForm.clearForm(jPanelBase);
	    for (int a=0; a<jTableDatos.getRowCount(); a++){
	    	jTableDatos.setValueAt(new Integer(0), a, 2);
	    }
	}

	private void actualizaTabla(){
		jTableDatos.repaint();
		jTableDatos.revalidate();
		jTableDatos.updateUI();
	}
	
	private void initGUI(JFrame frame) {
		this.setTitle("Evento Rol/Plazas");
		this.setModal(true);
		this.setLocationRelativeTo(frame);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		try {
			{
				jPanelBase = new JPanel();
				getContentPane().add(jPanelBase, BorderLayout.CENTER);
				jPanelBase.setPreferredSize(new java.awt.Dimension(618, 446));
				{
					jScrollPane1 = new JScrollPane();
					jPanelBase.add(jScrollPane1);
					jScrollPane1.setPreferredSize(new java.awt.Dimension(443, 295));
					{
						dtm = new DefaultTableModel();
						{
							for(int i=0;i<columnNames.length;i++){
					        	dtm.addColumn(columnNames[i]);
					        }
							
						}
						jTableDatos = new JTable(dtm);
						jScrollPane1.setViewportView(jTableDatos);
						Utils.ocultaColumna(jTableDatos, 0);
						jTableDatos.setPreferredSize(new java.awt.Dimension(538, 278));
					}
				}
				{
					jButtonAceptar = new JButton();
					jPanelBase.add(jButtonAceptar);
					jButtonAceptar.setLayout(null);
					jButtonAceptar.setText("Aceptar");
					jButtonAceptar.setFont(new java.awt.Font("Arial",0,10));
					jButtonAceptar.setBounds(275, 359, 90, 25);
					jButtonAceptar.setPreferredSize(new java.awt.Dimension(111, 32));
					jButtonAceptar.setSize(110, 32);
					jButtonAceptar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							bExit = true;
							dispose();
						}
					});
				}
				{
					jButtonCancelar = new JButton();
					jPanelBase.add(jButtonCancelar);
					jButtonCancelar.setLayout(null);
					jButtonCancelar.setText("Cancelar");
					jButtonCancelar.setFont(new java.awt.Font("Arial",0,10));
					jButtonCancelar.setPreferredSize(new java.awt.Dimension(110, 32));
					jButtonCancelar.setBounds(275, 359, 90, 25);
					jButtonCancelar.setSize(110, 32);
					jButtonCancelar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							bExit = false;
							dispose();
						}
					});
				}
				{
					jButtonLimpiar = new JButton();
					jPanelBase.add(jButtonLimpiar);
					jButtonLimpiar.setLayout(null);
					jButtonLimpiar.setText("Inicializar plazas");
					jButtonLimpiar.setFont(new java.awt.Font("Arial",0,10));
					jButtonLimpiar.setPreferredSize(new java.awt.Dimension(117, 32));
					jButtonLimpiar.setBounds(275, 359, 90, 25);
					jButtonLimpiar.setSize(110, 32);
					jButtonLimpiar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							limpiaFormulario();
						}
					});
				}
			}
			this.setSize(482, 371);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Boolean getAceptar() {
		return bExit;
	}
}
