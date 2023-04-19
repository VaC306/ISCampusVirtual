package model.aula;

public class SACurso {
	
	public boolean eliminarCurso(EnumCurso Curso, String anyo){

		DAOCurso dao = new DAOCurso();
		TransferCurso transfer = dao.read(Curso, anyo);

		// si no existe no se elimina
		if (transfer != null) {

			dao.eliminate(Curso, anyo);

			return true;
		}

		return false;
	}

	public boolean crearCurso(TransferCurso aTNew) {

		DAOCurso dao = new DAOCurso();
		TransferCurso transfer = dao.read(aTNew.getCurso(), aTNew.getAnyo());
		// como no existe se añade a la bd
		if (transfer == null) {

			dao.create(aTNew);

			return true;
		}
		return false;
	}

	public boolean editarCurso(TransferCurso aTNew) {

		DAOCurso dao = new DAOCurso();
		TransferCurso transfer = dao.read(aTNew.getCurso(), aTNew.getAnyo());

		// como no existe se edita la bd
		if (transfer == null) {

			dao.eliminate(aTNew.getCurso(), aTNew.getAnyo());
			dao.create(aTNew);

			return true;
		}
		return false;
	}
}
