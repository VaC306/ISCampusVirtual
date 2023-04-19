package model.usuario;

import java.util.ArrayList;
import java.util.List;
import model.aula.TransferAsignatura;

public class TransferProfesor extends TransferUsuario {
	private String idProfesor;
	private List<TransferAsignatura> l_asignaturas  = new ArrayList<> ();
	
	public String getIdProfesor() {
		return idProfesor;
	}

	public void setIdProfesor(String idProfesor) {
		this.idProfesor = idProfesor;
	}

	public List<TransferAsignatura> getL_asignaturas() {
		return l_asignaturas;
	}

	public void setL_asignaturas(List<TransferAsignatura> l_asignaturas) {
		this.l_asignaturas = l_asignaturas;
	}

	public TransferProfesor() {

	}

}
