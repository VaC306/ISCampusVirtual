package Negocio.Foro;

import java.util.Date;

import Integracion.DAOMensaje;
import Integracion.DAOMensajeImpl;

public class SAMensaje {
	
	public boolean eliminarMensaje (String idUsuario, Date date) {

		DAOMensaje dao = new DAOMensajeImpl();
		TransferMensaje transfer = dao.read(idUsuario, date);

		// si no existe no se elimina
		if (transfer != null) {

			dao.eliminate(idUsuario, date);

			return true;
		}

		return false;
	}
	
	public boolean crearMensaje (TransferMensaje aTNew) {
		
		DAOMensaje dao= new DAOMensajeImpl();
		TransferMensaje transfer = dao.read(aTNew.getUsuario(), aTNew.getFecha());

		//como no existe se añade a la bd
		if(transfer==null) {
			
			dao.create(aTNew);
			
			return true;
		}
		return false;
	}
}
