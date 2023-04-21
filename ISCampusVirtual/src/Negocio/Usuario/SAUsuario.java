package Negocio.Usuario;

import java.util.ArrayList;
import java.util.List;

import Integracion.DAOAsignatura;
import Integracion.DAOUsuario;
import Integracion.Factoria.FactoriaIntegracion;
import Negocio.Archivos.TransferTarea;
import Negocio.Aula.TransferAsignatura;
import Negocio.Aula.TransferTema;
import Negocio.Factoria.FactoriaUsuario;

public class SAUsuario {
	
	public TransferUsuario iniciarSesion(String password,String id ) {
		
		TransferUsuario transfer= FactoriaUsuario.getInstance().crearTransfer(id);
		
		if(transfer!=null && transfer.getPassword()==password) {
			
			return transfer;
		}
		
		return null;
	}
	
	public boolean eliminarUsuario(String id) {
		
		DAOUsuario dao= FactoriaUsuario.getInstance().crearDAO(id);
		TransferUsuario transfer= FactoriaUsuario.getInstance().crearTransfer(id);

		//si no existe no se elimina
		if(transfer!=null) {
			
			dao.eliminate(id);
			
			return true;
		}
		
		return false;	
	}
	
	public boolean crearUsuario (TransferUsuario aTNew) {
		
		DAOUsuario dao= FactoriaUsuario.getInstance().crearDAO(aTNew.getId());
		TransferUsuario transfer= FactoriaUsuario.getInstance().crearTransfer(aTNew.getId());

		//como no existe se añade a la bd
		if(transfer==null) {
			
			dao.create(aTNew);
			
			return true;
		}
		return false;
	}
	
	public boolean editarUsuario (TransferUsuario aTNew) {
		
		DAOUsuario dao= FactoriaUsuario.getInstance().crearDAO(aTNew.getId());
		TransferUsuario transfer= FactoriaUsuario.getInstance().crearTransfer(aTNew.getId());

		//como no existe se edita la bd
		if(transfer==null) {
			
			dao.eliminate(aTNew.getId());
			dao.create(aTNew);
			
			return true;
		}
		return false;
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
}