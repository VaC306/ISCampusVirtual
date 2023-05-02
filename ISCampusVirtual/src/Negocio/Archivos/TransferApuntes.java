package Negocio.Archivos;

import Integracion.DAOApuntes;
import Integracion.DAOApuntesImpl;
import Negocio.Foro.TransferForo;

public class TransferApuntes extends TransferArchivo {
	
	TransferForo tf;
	String IdApuntes;
	//añadir fecha a lo mejor? comentarios de alumnmos y/o profesores

	@Override
	public boolean matchFile(String idArchivo) {
		return idArchivo.charAt(0) == 'A';
	}

	@Override
	public TransferArchivo read(String idArchivo) {
		DAOApuntes dao = new DAOApuntesImpl();
		return dao.read(idArchivo);
	}
	
	public String getIdApuntes() {
		return this.IdApuntes;
	}
	
	public void setIdApuntes(String id) {
		this.IdApuntes = id;
	}


	
}
