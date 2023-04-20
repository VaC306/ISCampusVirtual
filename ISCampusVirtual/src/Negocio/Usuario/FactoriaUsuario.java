package Negocio.Usuario;

import Integracion.DAOAlumno;
import Integracion.DAOProfesor;
import Integracion.DAOUsuario;

public class FactoriaUsuario {

	private static FactoriaUsuario instancia;

	public static FactoriaUsuario getInstance() {
		if (instancia == null) {
			instancia = new FactoriaUsuario();
		}
		return instancia;
	}
	
	public TransferUsuario crearTransfer (String id) {
		
		DAOUsuario dao;
		TransferUsuario transfer=null;
		
		
		switch (id.charAt(0)) {
		case ('a'): {

			dao = new DAOAlumno();
			transfer = (TransferAlumno) dao.readById(id);
			break;
		}
		case ('p'): {

			dao = new DAOProfesor();
			transfer = (TransferUsuario) dao.readById(id);
			break;
		}
		}
		
		return transfer;
	}
	
	public DAOUsuario crearDAO (String id) {
		DAOUsuario dao=null;

		switch (id.charAt(0)) {
		case ('a'): {

			dao = new DAOAlumno();
			break;
		}
		case ('p'): {

			dao = new DAOProfesor();
			break;
		}
		}
		
		return dao;
	}

}
