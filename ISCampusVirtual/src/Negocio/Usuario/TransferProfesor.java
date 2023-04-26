package Negocio.Usuario;

import java.util.ArrayList;
import java.util.List;

import Integracion.DAOProfesor;
import Integracion.DAOProfesorImpl;
import Negocio.Aula.TransferAsignatura;

public class TransferProfesor extends TransferUsuario {



	public TransferProfesor() {

	}

	@Override
	public boolean esProfesor() {
		return true;
	}

	@Override
	public boolean matchUser(String correo) {
		return correo.toLowerCase().charAt(0) == 'p';
	}

	@Override
	public TransferUsuario read(String correo) {
		DAOProfesor dao = new DAOProfesorImpl();
		return dao.readByCorreo(correo);
	}

}
