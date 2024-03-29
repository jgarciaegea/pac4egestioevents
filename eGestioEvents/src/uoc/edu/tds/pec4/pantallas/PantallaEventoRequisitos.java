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

import uoc.edu.tds.pec4.beans.Evento;
import uoc.edu.tds.pec4.dtos.DTOEvento;
import uoc.edu.tds.pec4.dtos.DTOEventoRequisitos;
import uoc.edu.tds.pec4.excepciones.OperationErrorDatosFormulario;
import uoc.edu.tds.pec4.iface.RemoteInterface;
import uoc.edu.tds.pec4.resources.TDSLanguageUtils;
import uoc.edu.tds.pec4.utils.ClearForm;
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
public class PantallaEventoRequisitos extends javax.swing.JDialog {
	private static final long serialVersionUID = 939787444345394843L;
	private JPanel jPanelBase;
	private JScrollPane jScrollPane1;
	private JButton jButtonAceptar;
	private JButton jButtonCancelar;
	private JButton jButtonLimpiar;
	private JTable jTableDatos;
	private String[] columnNames = {"idEvento", TDSLanguageUtils.getMessage("clientePEC4.calendarioeventos.column2"), TDSLanguageUtils.getMessage("clientePEC4.evento.LABEL19")};
	private DefaultTableModel dtm;
	
	private RemoteInterface remote;
	private DTOEvento dtoEvento;
	private Boolean bExit = false;
	private Boolean bIsEmpty = false;

	/**
	* Auto-generated main method to display this JDialog
	*/
		
	public PantallaEventoRequisitos(JFrame frame, RemoteInterface remote1, DTOEvento dtoEvento1) {
		super(frame);
		initGUI(frame);
		this.remote = remote1;
		this.dtoEvento = dtoEvento1;
		
		if (dtoEvento != null && dtoEvento.getEvento() != null){
			try {
				cargaEventosByEventoCentro();
				checkEventos();
				bIsEmpty = (jTableDatos.getRowCount() == 0);
			} catch (OperationErrorDatosFormulario e) {
				e.showDialogError(jPanelBase);
			}
			
		}
	}
	
	public Boolean isEmpty(){
		return bIsEmpty;
	}

	private void limpiaFormulario(){
		ClearForm.clearForm(jPanelBase);
	    for (int a=0; a<jTableDatos.getRowCount(); a++){
	    	jTableDatos.setValueAt(new Boolean(false), a, 2);
	    }
	}
	
	public List<DTOEventoRequisitos> getDTOEventoRequisitos() {
		List<DTOEventoRequisitos> dtoEventoReList = new ArrayList<DTOEventoRequisitos>();
		Boolean bDatos = false;
		for (int a=0; a<jTableDatos.getRowCount(); a++){
			if (Boolean.parseBoolean((jTableDatos.getValueAt(a,2).toString()))){
				bDatos = true;
				DTOEventoRequisitos dtoEventoRequisitos = new DTOEventoRequisitos();
				DTOEvento dtoEventoReq = new DTOEvento();
				Evento eventoReq = new Evento();
				eventoReq.setIdEvento(Integer.parseInt((jTableDatos.getValueAt(a,0).toString())));
				eventoReq.setNombre((jTableDatos.getValueAt(a,1).toString()));
				dtoEventoReq.setEvento(eventoReq);
				dtoEventoRequisitos.setDtoEventoReq(dtoEventoReq);
				
				dtoEventoReList.add(dtoEventoRequisitos);
			}
		}
		return (bDatos?dtoEventoReList:null);
	}
	
	private void checkEventos() {
	      for (int a=0; a<jTableDatos.getRowCount(); a++){
	    	  int valor = Integer.parseInt((jTableDatos.getValueAt(a,0).toString()));
	    	  List<DTOEventoRequisitos> lstDTO = dtoEvento.getDtoEventoRequisitos();
	    	  if(lstDTO != null && lstDTO.size() > 0){
	    		  for(DTOEventoRequisitos dtoER : lstDTO){
	    			  if (valor == dtoER.getDtoEventoReq().getEvento().getIdEvento()){
	    				  jTableDatos.setValueAt(new Boolean(true), a, 2);
	    			  }
	    		  }
	          }
	      }
	}

	private DTOEvento consultaEvento() {
		DTOEvento dtoEventroCentro = new DTOEvento();
		Evento eventoCentro = new Evento();
		eventoCentro.setIdCentro(dtoEvento.getEvento().getIdCentro());
		eventoCentro.setIdTipoEvento(dtoEvento.getEvento().getIdTipoEvento());
		dtoEventroCentro.setEvento(eventoCentro);

		return dtoEventroCentro;
	}
	
	private void cargaEventosByEventoCentro() throws OperationErrorDatosFormulario{
		try{
			dtm.getDataVector().removeAllElements();
			List<DTOEvento> lstDtoEvento = remote.getEventosFinalizados(consultaEvento());
			if(lstDtoEvento == null || lstDtoEvento.isEmpty()){
				Utils.mostraMensajeInformacion(jPanelBase, TDSLanguageUtils.getMessage("clientePEC4.evento.MSG10"), TDSLanguageUtils.getMessage("clientePEC4.evento.LABEL20"));
				return;
			}
			muestraResultado(lstDtoEvento);
			actualizaTabla();
		}catch(Exception e){
			throw new OperationErrorDatosFormulario(TDSLanguageUtils.getMessage("clientePEC4.evento.MSG11"));
		}		
	}
	
	private void actualizaTabla(){
		jTableDatos.repaint();
		jTableDatos.revalidate();
		jTableDatos.updateUI();
	}
	
	/*
	 * Mostramos el resultado obtenido
	 */
	private void muestraResultado(List<DTOEvento> lstDtoEvento) throws OperationErrorDatosFormulario{
		try{
			dtm.getDataVector().removeAllElements();
		     
			Object[][] aobj = new Object[lstDtoEvento.size()][columnNames.length];
			int k = 0;
			if(lstDtoEvento != null){
				for(DTOEvento dtoEvento : lstDtoEvento){
					 aobj[k][0] = new String(dtoEvento.getEvento().getIdEvento().toString());
					 aobj[k][1] = new String(dtoEvento.getEvento().getNombre());
					 aobj[k][2] = new Boolean(false);
					 k++;
	       	 	}
				
				if (aobj != null && aobj.length > 0){
	       	 		for(int row = 0; row < aobj.length; row++){
	       	 			dtm.addRow(aobj[row]);
	       	 		}
	       	 	}
			}
		}catch(Exception e){
			throw new OperationErrorDatosFormulario(TDSLanguageUtils.getMessage("clientePEC4.evento.MSG12"));
		}
	}
	
	private void initGUI(JFrame frame) {
		this.setTitle(TDSLanguageUtils.getMessage("clientePEC4.evento.LABEL21"));
		this.setModal(true);
		this.setLocationRelativeTo(frame);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		try {
			{
				jPanelBase = new JPanel();
				getContentPane().add(jPanelBase, BorderLayout.CENTER);
				jPanelBase.setPreferredSize(new java.awt.Dimension(618, 418));
				{
					jScrollPane1 = new JScrollPane();
					jPanelBase.add(jScrollPane1);
					jPanelBase.add(getJButtonAceptar());
					jPanelBase.add(getJButtonCancelar());
					jPanelBase.add(getJButtonLimpiar());
					jScrollPane1.setPreferredSize(new java.awt.Dimension(559, 295));
					{
						dtm = new DefaultTableModel(){
							private static final long serialVersionUID = -1L;

							@Override
						     public boolean isCellEditable (int fila, int columna) {
						         if (columna > 1)
						             return true;
						         return false;
						     }
						 };
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
			}
			this.setSize(618, 378);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private JButton getJButtonLimpiar() {
		if(jButtonLimpiar == null) {
			jButtonLimpiar = new JButton();
			jButtonLimpiar.setLayout(null);
			jButtonLimpiar.setText(TDSLanguageUtils.getMessage("clientePEC4.evento.LABEL22"));
			jButtonLimpiar.setFont(new java.awt.Font("Arial",0,10));
			jButtonLimpiar.setPreferredSize(new java.awt.Dimension(107, 40));
			jButtonLimpiar.setBounds(275, 359, 90, 25);
			jButtonLimpiar.setSize(90, 40);
			jButtonLimpiar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					limpiaFormulario();
				}
			});
		}
		return jButtonLimpiar;
	}
	
	private JButton getJButtonCancelar() {
		if(jButtonCancelar == null) {
			jButtonCancelar = new JButton();
			jButtonCancelar.setLayout(null);
			jButtonCancelar.setText(TDSLanguageUtils.getMessage("clientePEC4.consultausuario.boton1"));
			jButtonCancelar.setFont(new java.awt.Font("Arial",0,10));
			jButtonCancelar.setPreferredSize(new java.awt.Dimension(113, 40));
			jButtonCancelar.setBounds(275, 359, 90, 25);
			jButtonCancelar.setSize(90, 40);
			jButtonCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					bExit = false;
					dispose();
				}
			});
		}
		return jButtonCancelar;
	}
	
	private JButton getJButtonAceptar() {
		if(jButtonAceptar == null) {
			jButtonAceptar = new JButton();
			jButtonAceptar.setLayout(null);
			jButtonAceptar.setText(TDSLanguageUtils.getMessage("clientePEC4.consultausuario.boton"));
			jButtonAceptar.setFont(new java.awt.Font("Arial",0,10));
			jButtonAceptar.setPreferredSize(new java.awt.Dimension(119, 40));
			jButtonAceptar.setBounds(275, 359, 90, 25);
			jButtonAceptar.setSize(90, 40);
			jButtonAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					bExit = true;
					dispose();
				}
			});
		}
		return jButtonAceptar;
	}

	public Boolean getAceptar() {
		return bExit;
	}

}
