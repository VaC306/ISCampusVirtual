package Negocio.Archivos;

import java.io.File;

import Negocio.Aula.TransferTema;
import Negocio.Usuario.SAUsuario;
import Negocio.Usuario.TransferUsuario;

public abstract class TransferArchivo {
	
	private String Nombre;
	
	protected String Id;

	private Tipos_archivo tipo_archivo;

	private TransferUsuario usuario;

	private String temas;


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

	public String getTemas() {
		return temas;
	}

	public void setTemas(String temas) {
		this.temas = temas;
	}
	
	public String getId() {
		return this.Id;
	}
	
	public void setId(String id) {
		this.Id = id;
	}

	public abstract boolean matchFile(String idArchivo);

	public abstract TransferArchivo read(String idArchivo);

	
	
	
}
