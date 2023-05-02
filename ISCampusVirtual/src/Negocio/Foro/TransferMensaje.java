package Negocio.Foro;

import java.sql.Date;

import Negocio.Usuario.TransferUsuario;

public class TransferMensaje {

	private Date Fecha;

	private String Cuerpo;

	private String usuario;
	
	private String titulo;

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

}
