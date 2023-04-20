package Negocio.Usuario;

import java.util.ArrayList;
import java.util.List;

import Integracion.DAOAsignatura;
import Integracion.DAOTarea;
import Negocio.Archivos.TransferTarea;
import Negocio.Aula.TransferAsignatura;

public abstract class TransferUsuario {
	private String NIF;

	private String Nombre_Apellidos;

	private String Correo_electronico;

	private String password;
	
	private String id;
	
    private List<String> asignaturas = new ArrayList<> ();


	public String getNIF() {
		return NIF;
	}

	public void setNIF(String nIF) {
		NIF = nIF;
	}

	public String getNombre_Apellidos() {
		return Nombre_Apellidos;
	}

	public void setNombre_Apellidos(String nombre_Apellidos) {
		Nombre_Apellidos = nombre_Apellidos;
	}

	public String getCorreo_electronico() {
		return Correo_electronico;
	}

	public void setCorreo_electronico(String correo_electronico) {
		Correo_electronico = correo_electronico;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public TransferUsuario() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getAsignaturas() {
		return asignaturas;
	}

	public void setAsignaturas(List<String> asignaturas) {
		this.asignaturas = asignaturas;
	}
	
	
	public abstract boolean esProfesor() ;

}
