package Negocio.Aula;
import java.util.ArrayList;
import java.util.List;

public class TransferCurso {
	
	private String anyo;

	private EnumCurso Curso;

	private List<TransferClase> clase = new ArrayList<TransferClase> ();

	public String getAnyo() {
		return anyo;
	}

	public void setAnyo(String anyo) {
		this.anyo = anyo;
	}

	public EnumCurso getCurso() {
		return Curso;
	}

	public void setCurso(EnumCurso curso) {
		Curso = curso;
	}

	public List<TransferClase> getClase() {
		return clase;
	}

	public void setClase(List<TransferClase> clase) {
		this.clase = clase;
	}
	
	
}
