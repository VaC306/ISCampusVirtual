package Negocio.Aula;

import java.util.ArrayList;
import java.util.List;

import Negocio.Foro.TransferForo;
import Negocio.Usuario.TransferAlumno;
import Negocio.Usuario.TransferProfesor;
import Negocio.Usuario.TransferUsuario;

public class TransferAsignatura {
	private String ID;

	private String Nombre;

	private List<TransferProfesor> profesor = new ArrayList<> ();

	private List<TransferAlumno> alumno = new ArrayList<> ();

	private List<TransferTema> temas = new ArrayList<TransferTema> ();

	private TransferForo avisos;

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public List<TransferProfesor> getProfesor() {
		return profesor;
	}

	public void setProfesor(List<TransferProfesor> profesor) {
		this.profesor = profesor;
	}

	public List<TransferAlumno> getAlumno() {
		return alumno;
	}

	public void setAlumno(List<TransferAlumno> alumno) {
		this.alumno = alumno;
	}

	public List<TransferTema> getTemas() {
		return temas;
	}

	public void setTemas(List<TransferTema> temas) {
		this.temas = temas;
	}

	public TransferForo getAvisos() {
		return avisos;
	}

	public void setAvisos(TransferForo avisos) {
		
		this.avisos = avisos;
	}
	
	public List<TransferUsuario> getUsuarios(){
		
		List<TransferUsuario> ret=new ArrayList(alumno);
		ret.addAll(profesor);
		
		return ret;
	}
	
	
}
