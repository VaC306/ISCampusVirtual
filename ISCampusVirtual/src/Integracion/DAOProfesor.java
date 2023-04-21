package Integracion;

import Negocio.Usuario.TransferAlumno;
import Negocio.Usuario.TransferProfesor;
import Negocio.Usuario.TransferUsuario;

public interface DAOProfesor extends DAOUsuario {	

	public abstract void create(TransferProfesor aTNew);	
	
}
