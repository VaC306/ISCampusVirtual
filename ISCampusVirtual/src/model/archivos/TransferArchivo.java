package model.archivos;

import model.aula.TransferTema;
import model.usuario.SAUsuario;
import model.usuario.TransferUsuario;

public class TransferArchivo {
	
	private String Nombre;

	private Tipos_archivo tipo_archivo;

	private TransferUsuario usuario;

	private TransferTema temas;

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public Tipos_archivo getTipo_archivo() {
		return tipo_archivo;
	}

	public void setTipo_archivo(Tipos_archivo tipo_archivo) {
		this.tipo_archivo = tipo_archivo;
	}

	public TransferUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(TransferUsuario usuario) {
		this.usuario = usuario;
	}

	public TransferTema getTemas() {
		return temas;
	}

	public void setTemas(TransferTema temas) {
		this.temas = temas;
	}
	
	
}
