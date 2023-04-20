package Negocio.Foro;
import java.util.Date;

import Negocio.Usuario.TransferUsuario;

public class TransferMensaje {
	
	private Date Fecha;

	private String Cuerpo;

	private String Titulo;

	private TransferUsuario usuario;


	
	public TransferMensaje(Date fecha, String cuerpo, String titulo, TransferUsuario usuario) {
		super();
		Fecha = fecha;
		Cuerpo = cuerpo;
		Titulo = titulo;
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

	public String getTitulo() {
		return Titulo;
	}

	public void setTitulo(String titulo) {
		Titulo = titulo;
	}

	public TransferUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(TransferUsuario usuario) {
		this.usuario = usuario;
	}
	
	

}
