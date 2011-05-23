package uoc.edu.tds.pec4.beans;

import java.io.Serializable;
import java.sql.Date;

public class DatosBancarios implements BeanInterface, Serializable{	
	
	private static final long serialVersionUID = 1L;
	private Integer idDatosBancarios;
	private Integer banco;
	private Integer sucursal;
	private Integer dc;
	private String cc;
	private Date fechaAlta;
	private Integer estado;
	private Date fechaEstado;
	private String motivoEstado;
	
	public Integer getIdDatosBancarios() {
		return idDatosBancarios;
	}
	public void setIdDatosBancarios(Integer idDatosBancarios) {
		this.idDatosBancarios = idDatosBancarios;
	}
	public Integer getSucursal() {
		return sucursal;
	}
	public void setSucursal(Integer sucursal) {
		this.sucursal = sucursal;
	}
	public Integer getDc() {
		return dc;
	}
	public void setDc(Integer dc) {
		this.dc = dc;
	}
	
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	public Date getFechaEstado() {
		return fechaEstado;
	}
	public void setFechaEstado(Date fechaEstado) {
		this.fechaEstado = fechaEstado;
	}
	public String getMotivoEstado() {
		return motivoEstado;
	}
	public void setMotivoEstado(String motivoEstado) {
		this.motivoEstado = motivoEstado;
	}
	public Integer getBanco() {
		return banco;
	}
	public void setBanco(Integer banco) {
		this.banco = banco;
	}
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}

}
