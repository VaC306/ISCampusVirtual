package Negocio.Aula;
import java.util.ArrayList;
import java.util.List;

public class TransferClase {
	
	private String Grupo;

	private List<TransferAsignatura> asignaturas = new ArrayList<TransferAsignatura> ();

	private String Id;
	
	private EnumCurso curso;
	
	private int anyo;
	
	public String getGrupo() {
		return Grupo;
	}

	public void setGrupo(String grupo) {
		Grupo = grupo;
	}

	public List<TransferAsignatura> getAsignaturas() {
		return asignaturas;
	}

	public void setAsignaturas(List<TransferAsignatura> asignaturas) {
		this.asignaturas = asignaturas;
	}
	
	public String getId() {
		return this.Id;
	}
	
	public void setId(String id) {
		this.Id = id;
	}
	
	public EnumCurso getCurso() {
		return this.curso;
	}
	
	public void setCurso(EnumCurso curso) {
		this.curso = curso;
	}

	public int getAnyo() {
		return this.anyo;
	}
	
	public void setAnyo(int anyo) {
		this.anyo = anyo;
	}
	
}






























