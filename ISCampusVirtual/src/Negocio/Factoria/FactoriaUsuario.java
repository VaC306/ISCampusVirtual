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
	
	public TransferUsuario crearTransferById (String id) {
		
		DAOUsuario dao;
		TransferUsuario transfer=null;
		
		if (!id.isEmpty()) {

			switch (id.charAt(0)) {
			case ('a'): 

				dao = new DAOAlumnoImpl();
				transfer = (TransferAlumno) dao.readById(id);
				break;
			
			case ('p'): 

				dao = new DAOProfesorImpl();
				transfer = (TransferUsuario) dao.readById(id);
				break;
			}
			
		}

		return transfer;
	}
	
	public TransferUsuario crearTransferByCorreo (String correo) {
		
		DAOUsuario dao;
		TransferUsuario transfer=null;

		if (!correo.isEmpty()) {

			dao = new DAOAlumnoImpl();
			transfer = (TransferAlumno) dao.readByCorreo(correo);

			if (transfer.getId().charAt(0)=='p') {

				dao = new DAOProfesorImpl();
				transfer = (TransferUsuario) dao.readByCorreo(correo);
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
