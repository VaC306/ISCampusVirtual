package Negocio.Usuario;

import java.util.ArrayList;
import java.util.List;

import Negocio.Aula.TransferNota;


public class TransferAlumno extends TransferUsuario{
	

    private boolean Delegado;

    private List<TransferNota> notas = new ArrayList<TransferNota> ();
	

	public boolean isDelegado() {
		return Delegado;
	}

	public void setDelegado(boolean delegado) {
		Delegado = delegado;
	}

	public List<TransferNota> getNotas() {
		return notas;
	}

	public void setNotas(List<TransferNota> notas) {
		this.notas = notas;
	}

	public TransferAlumno() {

	}

	@Override
	public boolean esProfesor() {
		return false;
	}

}
