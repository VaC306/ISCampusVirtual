package Negocio.Aula;
import java.util.ArrayList;
import java.util.List;

import Negocio.Archivos.TransferArchivo;
import Negocio.Archivos.TransferTarea;

public class TransferTema {
	private String Id;
	
	private String Nombre;

    private int Numero;

    private TransferAsignatura asignatura ;

    private List<TransferArchivo> archivo = new ArrayList<> ();
    
    private List<TransferTarea> tareas = new ArrayList<> ();

    public TransferTema(String id, String nombre, int numero, List<TransferArchivo> idAr, String idAs) {
    	this.Id = id;
    	this.Nombre = nombre;
    	this.Numero = numero;
    	this.archivo = idAr;
    	this.asignatura = null;
    }

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

	public List<TransferTarea> getTareas() {
		return tareas;
	}

	public void setTareas(List<TransferTarea> tareas) {
		this.tareas = tareas;
	}
    
    public String getId() {
    	return this.Id;
    }
    
    public void setId(String id) {
    	this.Id = id;
    }

}
