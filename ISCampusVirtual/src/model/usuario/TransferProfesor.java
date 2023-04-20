package model.usuario;

import java.util.ArrayList;
import java.util.List;
import model.aula.TransferAsignatura;

public class TransferProfesor extends TransferUsuario {



	public TransferProfesor() {

	}

	@Override
	public boolean esProfesor() {
		return true;
	}

}
