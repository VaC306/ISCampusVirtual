package Negocio.Usuario;

import java.util.ArrayList;
import java.util.List;

import Negocio.Aula.TransferAsignatura;

public class TransferProfesor extends TransferUsuario {



	public TransferProfesor() {

	}

	@Override
	public boolean esProfesor() {
		return true;
	}

}
