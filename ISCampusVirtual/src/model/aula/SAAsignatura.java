package model.aula;
import java.util.ArrayList;
import java.util.List;

import model.foro.TransferForo;
import model.usuario.SAAlumno;
import model.usuario.SAProfesor;

public class SAAsignatura {
	
	public boolean eliminarAsignatura(String id){

		DAOAsignatura dao = new DAOAsignatura();
		TransferAsignatura transfer = dao.read(id);

		// si no existe no se elimina
		if (transfer != null) {

			dao.eliminate(id);

			return true;
		}

		return false;
	}

	public boolean crearAsignatura(TransferAsignatura aTNew) {

		DAOAsignatura dao = new DAOAsignatura();
		TransferAsignatura transfer = dao.read(aTNew.getID());
		// como no existe se añade a la bd
		if (transfer == null) {

			dao.create(aTNew);

			return true;
		}
		return false;
	}

	public boolean editarAsignatura(TransferAsignatura aTNew) {

		DAOAsignatura dao = new DAOAsignatura();
		TransferAsignatura transfer = dao.read(aTNew.getID());

		// como no existe se edita la bd
		if (transfer == null) {

			dao.eliminate(aTNew.getID());
			dao.create(aTNew);

			return true;
		}
		return false;
	}
}
