package Negocio.Aula;
import java.util.ArrayList;
import java.util.List;

import Integracion.DAOAsignatura;
import Integracion.DAOAsignaturaImpl;
import Negocio.Archivos.TransferTarea;
import Negocio.Foro.TransferForo;
import Negocio.Usuario.SAAlumno;
import Negocio.Usuario.SAProfesor;

public class SAAsignatura {
	
	public boolean eliminarAsignatura(String id){

		DAOAsignatura dao = new DAOAsignaturaImpl();
		TransferAsignatura transfer = dao.read(id);

		// si no existe no se elimina
		if (transfer != null) {

			dao.eliminate(id);

			return true;
		}

		return false;
	}

	public boolean crearAsignatura(TransferAsignatura aTNew) {

		DAOAsignatura dao = new DAOAsignaturaImpl();
		TransferAsignatura transfer = dao.read(aTNew.getID());
		// como no existe se añade a la bd
		if (transfer == null) {

			dao.create(aTNew);

			return true;
		}
		return false;
	}

	public boolean editarAsignatura(TransferAsignatura aTNew) {

		DAOAsignatura dao = new DAOAsignaturaImpl();
		TransferAsignatura transfer = dao.read(aTNew.getID());

		// como no existe se edita la bd
		if (transfer == null) {

			dao.eliminate(aTNew.getID());
			dao.create(aTNew);

			return true;
		}
		return false;
	}
	
	
	public List<TransferTarea> getTareas(TransferAsignatura aTNew){	
		
		List<TransferTarea> ret= new ArrayList<>();
		
		/* TODO for(TransferTema tt: aTNew.getTemas()) {
			
			ret.addAll(tt.getTareas());
		}*/
		

		return ret;
	}
	
}
