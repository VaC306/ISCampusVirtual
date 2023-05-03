package Negocio.Usuario;

import java.util.HashMap;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Integracion.DAOAlumnoImpl;
import Integracion.DAOAsignatura;
import Integracion.DAOAsignaturaImpl;
import Integracion.DAOUsuario;
import Integracion.Factoria.FactoriaIntegracion;
import Negocio.Archivos.TransferTarea;
import Negocio.Aula.TransferAsignatura;
import Negocio.Aula.TransferTema;
import Negocio.Factoria.FactoriaUsuario;

public class SAUsuario {
	
	public TransferUsuario iniciarSesion(String correo,String password ) {
		
		TransferUsuario transfer= FactoriaUsuario.getInstance().crearTransferByCorreo(correo);

		if(transfer!=null && transfer.getPassword().equals(password)) {
			
			return transfer;
		}
		
		return null;
	}
	
	public boolean eliminarUsuario(String id) {
		
		DAOUsuario dao= new DAOAlumnoImpl();
		TransferUsuario transfer= FactoriaUsuario.getInstance().crearTransferByCorreo(id);
		
		//si no existe no se elimina
		if(transfer != null) {
			dao.eliminate(transfer.getNIF());
			
			return true;
		}
		
		return false;	
	}
	
	public boolean crearUsuario (TransferUsuario aTNew) {
		
		DAOUsuario dao= FactoriaUsuario.getInstance().crearDAO(aTNew.getId());
		TransferUsuario transfer= FactoriaUsuario.getInstance().crearTransferById(aTNew.getId());

		//como no existe se añade a la bd
		if(transfer==null) {
			
			dao.create(aTNew);
			
			return true;
		}
		return false;
	}
	
	public boolean editarUsuario (TransferUsuario aTNew) {
		
		DAOUsuario dao= FactoriaUsuario.getInstance().crearDAO(aTNew.getCorreo_electronico());
		TransferUsuario transfer= FactoriaUsuario.getInstance().crearTransferByCorreo(aTNew.getCorreo_electronico());

		//como no existe se edita la bd
		if(transfer!=null) {
			
			dao.eliminate(aTNew.getNIF());
			dao.create(aTNew);
			
			return true;
		}
		return false;
	}	
	
	public boolean editarAsignaturaUsuario (TransferUsuario aTNew,String idAsignatura) {
		
		
		if(aTNew!=null) {
			DAOUsuario dao= FactoriaUsuario.getInstance().crearDAO(aTNew.getCorreo_electronico());
			dao.updateAsignatura(aTNew.getNIF(), idAsignatura );
			
			return true;
		}
		return false;
	}	
	
	public List<TransferAsignatura> getAsignaturas(TransferUsuario aTNew){
		List<TransferAsignatura> LT = new ArrayList();
		DAOAsignatura dao = new DAOAsignaturaImpl();
		for (String s: aTNew.getAsignaturas()) {
			LT.add(dao.read(s));
		}
		return LT;
	}
	
	public List<TransferTarea> getTareas(TransferUsuario aTNew){	
		
		List<TransferTarea> ret= new ArrayList<>();
		
		DAOAsignatura daoAsignatura= FactoriaIntegracion.getInstancia().generarDAOAsignatura();
		TransferAsignatura tAsignatura;
		
		for(String idAsignatura: aTNew.getAsignaturas()) {
			
			tAsignatura=daoAsignatura.read(idAsignatura);
			
			for(TransferTema tt: tAsignatura.getTemas()) {
				
				ret.addAll(tt.getTareas());
			}
		}
		

		return ret;
	}

	public boolean crearUsuarioConAsignatura(TransferUsuario tUsuario, TransferAsignatura tAsignatura) {
		
		DAOUsuario dao= FactoriaUsuario.getInstance().crearDAO(tUsuario.getCorreo_electronico());
		
		//como no existe se añade a la bd
		if(tUsuario != null) {
			
			dao.create(tUsuario);
			
			return true;
		}
		
		tUsuario.getAsignaturas().add(tAsignatura.getID());
		
		return false;		
	}
}
