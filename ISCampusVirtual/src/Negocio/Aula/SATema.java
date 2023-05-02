package Negocio.Aula;

import Integracion.DAOTema;
import Integracion.DAOTemaImpl;

public class SATema {
	
	public boolean eliminarTema(String idAsignatura, int numTema) {

		DAOTema dao = new DAOTemaImpl();
		TransferTema transfer = dao.read(idAsignatura);

		// si no existe no se elimina
		if (transfer != null) {

			dao.eliminate(idAsignatura);

			return true;
		}

		return false;
	}

	public boolean crearTema(TransferTema aTNew) {

		DAOTema dao = new DAOTemaImpl();
		TransferTema transfer = dao.read(aTNew.getAsignaturas().getID());
		// como no existe se añade a la bd
		if (transfer == null) {

			dao.create(aTNew);

			return true;
		}
		return false;
	}
//TODO
	public boolean editarTema(TransferTema aTNew) {

		DAOTema dao = new DAOTemaImpl();

		// como no existe se edita la bd
		if (aTNew != null) {

			dao.updateName(aTNew.getId(), aTNew.getNombre());
			return true;
		}
		return false;
	}
}
