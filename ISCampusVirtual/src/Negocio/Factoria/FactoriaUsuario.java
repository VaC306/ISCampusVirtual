package Negocio.Factoria;

import Integracion.DAOAlumno;
import Integracion.DAOAlumnoImpl;
import Integracion.DAOProfesor;
import Integracion.DAOProfesorImpl;
import Integracion.DAOUsuario;
import Negocio.Usuario.TransferAlumno;
import Negocio.Usuario.TransferUsuario;

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

			dao = new DAOAlumnoImpl();
			transfer = (TransferAlumno) dao.readById(id);
			break;
		}
		case ('p'): {

			dao = new DAOProfesorImpl();
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

			dao = new DAOAlumnoImpl();
			break;
		}
		case ('p'): {

			dao = new DAOProfesorImpl();
			break;
		}
		}
		
		return dao;
	}

}
