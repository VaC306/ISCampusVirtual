package Negocio.Aula;
import java.util.ArrayList;
import java.util.List;

import Integracion.DAOAsignatura;
import Integracion.DAOAsignaturaImpl;
import Negocio.Archivos.TransferApuntes;
import Negocio.Archivos.TransferArchivo;
import Negocio.Archivos.TransferTarea;
import Negocio.Foro.TransferForo;
import Negocio.Usuario.SAAlumno;
import Negocio.Usuario.SAProfesor;
import Negocio.Usuario.TransferAlumno;
import Negocio.Usuario.TransferProfesor;
import Negocio.Usuario.TransferUsuario;

public class SAAsignatura {
	
	public TransferAsignatura getById(String id) {
		
		DAOAsignatura dao = new DAOAsignaturaImpl();
		TransferAsignatura transfer = dao.read(id);
		
		return transfer;
	}
	
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
		
		for(TransferTema tt: aTNew.getTemas()) {
			for(TransferArchivo t: tt.getArchivo()) {
				if(t.addTareas() != null) {
					ret.add((TransferTarea)t.addTareas());
				}
			}
		}
		

		return ret;
	}

	public List<TransferApuntes> getApuntes(TransferAsignatura tAsignatura) {
		
		List<TransferApuntes> ret= new ArrayList<>();

		for(TransferTema tt: tAsignatura.getTemas()) {
			
			for(TransferArchivo t: tt.getArchivo()) {
				if(t.addApuntes() != null) {
					ret.add((TransferApuntes)t.addApuntes());
				}
			}

		}
		
		return ret;
	}

	public void anadirUsuario(TransferAsignatura tAsignatura, TransferUsuario tUsuario) {
		if(!tAsignatura.getAlumno().contains(tUsuario)) {
			tAsignatura.getAlumno().add((TransferAlumno)tUsuario);
			editarAsignatura(tAsignatura);
		}
	}
	
	public boolean eliminarUsuario(TransferAsignatura tAsignatura, String id) {
		
		for(TransferAlumno ta: tAsignatura.getAlumno()) {
			
			if(ta.getId().equals(id)) {
				
				tAsignatura.getAlumno().remove(ta);
				editarAsignatura(tAsignatura);
				return true;
			}
		}
		
		return false;
	}
	
}
