/**
 * 
 */
package uoc.edu.tds.pec4.pantallas;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import uoc.edu.tds.pec4.beans.Usuario;
import uoc.edu.tds.pec4.dtos.DTOTipoEvento;
import uoc.edu.tds.pec4.excepciones.OperationErrorBD;
import uoc.edu.tds.pec4.gestores.GestorRMI;
import uoc.edu.tds.pec4.iface.RemoteInterface;
import uoc.edu.tds.pec4.utils.MostrarCombo;

/**
 * @author ML019882
 *
 */
public class PantallaInformeEventosAsistente extends PanelComun implements Pantallas {

	private static final long serialVersionUID = 1L; 
	private RemoteInterface remote;
	private String[] columnNames = {"Universidad ","Centro ","Evento", "Tipo Evento", "Fecha inicio", "Fecha fin", "Cerrado" , "Estado "};
	private Usuario usuario;

	/**
	 * @throws OperationErrorBD 
	 * @throws RemoteException 
	 * 
	 */
	public PantallaInformeEventosAsistente(GestorRMI gestorRMI,RemoteInterface remote1,Usuario usu) throws RemoteException, OperationErrorBD {
		
		super("clientePEC4.panelInformeEventosAsistencia.titulo",800,700);
		remote = remote1;
		usuario = usu;
		
		this.crearTitulo(20, 30, 140, 20, "clientePEC4.panelInformeEventosAsistencia.titulo1.Asistente", "tAsistente");
		this.crearTextField(160, 30, 200, 20,"cajaAsistente");
		this.findTextField("cajaAsistente").setText(usuario.getApellidos() + ", "+ usuario.getNombre());
		this.findTextField("cajaAsistente").setEditable(false);
		
		
		this.crearTitulo(20, 60, 140, 20, "clientePEC4.panelInformeEventosAsistencia.titulo1.TipoEvento", "tTipoEvento");
		
	
		this.crearCombo(160, 60, 200, 20, "comboTipoEvento", recuperarTiposEvento());
		
		this.crearTitulo(20, 90, 80, 20, "clientePEC4.panelInformeEventosAsistencia.titulo1.FechaDesde", "tFechaDesde");
		this.crearTextField(100, 90, 90, 20,"cajaFechaDeste");
		
		this.crearTitulo(220, 90, 80, 20, "clientePEC4.panelInformeEventosAsistencia.titulo1.FechaHasta", "tFechaHasta");
		this.crearTextField(300, 90, 90, 20,"cajaFechaHasta");
		

		this.crearTabla(20, 150, 700, 300,columnNames);

	}
	
	private  List<MostrarCombo> recuperarTiposEvento() throws RemoteException, OperationErrorBD{
		
		List<DTOTipoEvento> lstdtoTipoEvento = remote.getTiposEventos();
		List<MostrarCombo> lstComoTipoEvento = new ArrayList<MostrarCombo>();
		for(DTOTipoEvento dtoTipoEventoRes : lstdtoTipoEvento){
			lstComoTipoEvento.add(new MostrarCombo(dtoTipoEventoRes.getTipoEvento().getEstado(),
					dtoTipoEventoRes.getTipoEvento().getDescripcion()));
		
		
		}
		return lstComoTipoEvento;
	}

}
