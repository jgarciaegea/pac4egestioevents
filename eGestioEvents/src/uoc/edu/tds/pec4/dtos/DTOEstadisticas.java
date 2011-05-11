package uoc.edu.tds.pec4.dtos;

import java.io.Serializable;

import uoc.edu.tds.pec4.beans.Estadisticas;
public class DTOEstadisticas implements DTOInterface, Serializable{
	private static final long serialVersionUID = 1L; 
	
		private Estadisticas estadisticas;
		private DTOUniversidad dtoUniversidad;
		private DTOCentroDocente dtoCentro;
		private DTOEvento dtoEvento;
		private DTOTipoEvento dtoTipoEvento;
		private DTOTipoRol dtoTipoRol;
		private DTODocumentoIdentificacion dtoDocumentoIdentificacion;
		private DTOUsuario dtoUsuario;

		public Estadisticas getEstadisticas() {
			return estadisticas;
		}

		public void setEstadisticas(Estadisticas estadisticas) {
			this.estadisticas = estadisticas;
		}
		
		/**
		 * @return the dtoUniversidad
		 */
		public DTOUniversidad getDTOUniversidad() {
			return dtoUniversidad;
		}
		
		/**
		 * @param dtoUniversidad the dtoUniversidad to set
		 */
		public void setDTOUniversidad(DTOUniversidad dtoUniversidad) {
			this.dtoUniversidad = dtoUniversidad;
		}
		
		/**
		 * @return the dtoCentro
		 */
		public DTOCentroDocente getDTOCentro() {
			return dtoCentro;
		}
		
		/**
		 * @param dtoCentro the dtoCentro to set
		 */
		public void setDTOCentro(DTOCentroDocente dtoCentro) {
			this.dtoCentro = dtoCentro;
		}

		/**
		 * @return the dtoEventos
		 */
		public DTOEvento getDTOEvento() {
			return dtoEvento;
		}
		
		/**
		 * @param dtoEvento the dtoEvento to set
		 */
		public void setDTOEvento(DTOEvento dtoEvento) {
			this.dtoEvento = dtoEvento;
		}

		/**
		 * @return the dtoTipoEvento
		 */
		public DTOTipoEvento getDTOTipoEvento() {
			return dtoTipoEvento;
		}
		
		/**
		 * @param dtoTipoEvento the dtoTipoEvento to set
		 */
		public void setDTOTipoEvento(DTOTipoEvento dtoTipoEvento) {
			this.dtoTipoEvento = dtoTipoEvento;
		}

		/**
		 * @return the dtoTipoRol
		 */
		public DTOTipoRol getDTOTipoRol() {
			return dtoTipoRol;
		}
		
		/**
		 * @param dtoTipoRol the dtoTipoRol to set
		 */
		public void setTipoRol(DTOTipoRol dtoTipoRol) {
			this.dtoTipoRol = dtoTipoRol;
		}

		/**
		 * @return the dtoDocumentoIdentificacion
		 */
		public DTODocumentoIdentificacion getDTODocumentoIdentificacion() {
			return dtoDocumentoIdentificacion;
		}
		
		/**
		 * @param dtoDocumentoIdentificacion the dtoDocumentoIdentificacion to set
		 */
		public void setDTODocumentoIdentificacion(DTODocumentoIdentificacion dtoDocumentoIdentificacion) {
			this.dtoDocumentoIdentificacion = dtoDocumentoIdentificacion;
		}
		
		/**
		 * @return the dtoUsuario
		 */
		public DTOUsuario getDTOUsuario() {
			return dtoUsuario;
		}
		/**
		 * @param dtoUsuario the dtoUsuario to set
		 */
		public void setDTOUsuario(DTOUsuario dtoUsuario) {
			this.dtoUsuario = dtoUsuario;
		}
	}



	
	