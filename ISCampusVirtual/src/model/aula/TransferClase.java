package model.aula;
import java.util.ArrayList;
import java.util.List;

public class TransferClase {
	
	private char Grupo;

	private List<TransferAsignatura> asignaturas = new ArrayList<TransferAsignatura> ();

	public char getGrupo() {
		return Grupo;
	}

	public void setGrupo(char grupo) {
		Grupo = grupo;
	}

	public List<TransferAsignatura> getAsignaturas() {
		return asignaturas;
	}

	public void setAsignaturas(List<TransferAsignatura> asignaturas) {
		this.asignaturas = asignaturas;
	}

	
}






























