package uoc.edu.tds.pec4.dtos;

import java.io.Serializable;

import uoc.edu.tds.pec4.beans.Estadisticas;
public class DTOEstadisticas implements DTOInterface, Serializable{
	private static final long serialVersionUID = 1L; 
	
		private Estadisticas estadisticas;

		public Estadisticas getEstadisticas() {
			return estadisticas;
		}

		public void setEstadisticas(Estadisticas estadisticas) {
			this.estadisticas = estadisticas;
		}
	}



	
	