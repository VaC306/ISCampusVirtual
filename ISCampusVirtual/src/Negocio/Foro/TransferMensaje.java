package Negocio.Foro;

import java.sql.Date;

import Negocio.Usuario.TransferUsuario;

public class TransferMensaje {

	private Date Fecha;

	private String Cuerpo;

	private String usuario;
	
	private String titulo;
	
	private String IdMensaje;
	
	private String IdForo;

	public TransferMensaje(Date fecha, String cuerpo, String usuario) {
		super();
		Fecha = fecha;
		Cuerpo = cuerpo;
		this.usuario = usuario;
	}

	public Date getFecha() {
		return Fecha;
	}

	public void setFecha(Date fecha) {
		Fecha = fecha;
	}

	public String getCuerpo() {
		return Cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		Cuerpo = cuerpo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getIdMensaje() {
		return this.IdMensaje;
	}
	
	public void setIdMensaje(String id) {
		this.IdMensaje = id;
	}
	
	public String getIdForo() {
		return this.IdForo;
	}
	
	public void setIdForo(String id) {
		this.IdForo = id;
	}

}
