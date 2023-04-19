package model.aula;
import java.util.ArrayList;
import java.util.List;

import model.archivos.TransferArchivo;

public class TransferTema {
	private String Nombre;

    private int Numero;

    private TransferAsignatura asignatura ;

    private List<TransferArchivo> archivo = new ArrayList<TransferArchivo> ();

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public int getNumero() {
		return Numero;
	}

	public void setNumero(int numero) {
		Numero = numero;
	}

	public TransferAsignatura getAsignaturas() {
		return asignatura;
	}

	public void setAsignatura(TransferAsignatura asignaturas) {
		this.asignatura = asignaturas;
	}

	public List<TransferArchivo> getArchivo() {
		return archivo;
	}

	public void setArchivo(List<TransferArchivo> archivo) {
		this.archivo = archivo;
	}
    
    

}
