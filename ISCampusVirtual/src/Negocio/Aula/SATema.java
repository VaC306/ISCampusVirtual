package Negocio.Aula;

import Integracion.DAOTema;

public class SATema {
	
	public boolean eliminarTema(String idAsignatura, int numTema) {

		DAOTema dao = new DAOTema();
		TransferTema transfer = dao.read(idAsignatura, numTema);

		// si no existe no se elimina
		if (transfer != null) {

			dao.eliminate(idAsignatura, numTema);

			return true;
		}

		return false;
	}

	public boolean crearTema(TransferTema aTNew) {

		DAOTema dao = new DAOTema();
		TransferTema transfer = dao.read(aTNew.getAsignaturas().getID(), aTNew.getNumero());
		// como no existe se añade a la bd
		if (transfer == null) {

			dao.create(aTNew);

			return true;
		}
		return false;
	}

	public boolean editarTema(TransferTema aTNew) {

		DAOTema dao = new DAOTema();
		TransferTema transfer = dao.read(aTNew.getAsignaturas().getID(), aTNew.getNumero());

		// como no existe se edita la bd
		if (transfer == null) {

			dao.eliminate(aTNew.getAsignaturas().getID(), aTNew.getNumero());
			dao.create(aTNew);

			return true;
		}
		return false;
	}
}
