package uoc.edu.tds.pec4.beans;

import java.io.Serializable;
import java.sql.Date;

public class Usuario implements BeanInterface,Serializable{

	private static final long serialVersionUID = 1L;
	private Date fechaAlta;
	private Date fechaContrase�a;
	private String contrase�a;
	private Boolean cambiarContrase�a;
	private Integer estado;
	private String motivoEstado;
	private Integer idCentro;
	private Integer idDocumentoIdentificacion;
	private Integer idContacto;
	private Integer tipoUsuario;
	private String codigo;
	private String nombre;
	private String apellidos;
	private String sexo;
	private Date fechaNacimiento;
	private Date fechaEstado;
	private Integer idRol;
	private Integer idDatosBancarios;
	
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public Date getFechaContrase�a() {
		return fechaContrase�a;
	}
	public void setFechaContrase�a(Date fechaContrase�a) {
		this.fechaContrase�a = fechaContrase�a;
	}
	public String getContrase�a() {
		return contrase�a;
	}
	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}
	public Boolean getCambiarContrase�a() {
		return cambiarContrase�a;
	}
	public void setCambiarContrase�a(Boolean cambiarContrase�a) {
		this.cambiarContrase�a = cambiarContrase�a;
	}
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	public String getMotivoEstado() {
		return motivoEstado;
	}
	public void setMotivoEstado(String motivoEstado) {
		this.motivoEstado = motivoEstado;
	}
	public Integer getIdCentro() {
		return idCentro;
	}
	public void setIdCentro(Integer idCentro) {
		this.idCentro = idCentro;
	}
	public Integer getIdDocumentoIdentificacion() {
		return idDocumentoIdentificacion;
	}
	public void setIdDocumentoIdentificacion(Integer idDocumentoIdentificacion) {
		this.idDocumentoIdentificacion = idDocumentoIdentificacion;
	}
	public Integer getIdContacto() {
		return idContacto;
	}
	public void setIdContacto(Integer idContacto) {
		this.idContacto = idContacto;
	}
	public Integer getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(Integer tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public Date getFechaEstado() {
		return fechaEstado;
	}
	public void setFechaEstado(Date fechaEstado) {
		this.fechaEstado = fechaEstado;
	}
	public Integer getIdRol() {
		return idRol;
	}
	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}
	public Integer getIdDatosBancarios() {
		return idDatosBancarios;
	}
	public void setIdDatosBancarios(Integer idDatosBancarios) {
		this.idDatosBancarios = idDatosBancarios;
	}
	
}
