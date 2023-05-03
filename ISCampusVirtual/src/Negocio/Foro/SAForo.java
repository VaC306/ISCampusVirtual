package Negocio.Foro;

import Integracion.DAOForo;
import Integracion.DAOForoImpl;
import Integracion.DAOMensaje;
import Integracion.DAOMensajeImpl;
import Negocio.Aula.TransferAsignatura;
//a
public class SAForo {

	public void anadirMensaje(TransferForo foro, TransferMensaje mensaje) {
		foro.addMensaje(foro.getMensaje(), mensaje);
		DAOMensaje daoM = new DAOMensajeImpl();
		daoM.create(mensaje);
		DAOForo daoF = new DAOForoImpl();
		int x = foro.getNumero_mensajes_totales()+1;
		daoF.updateNum(x,foro.getID());
	}

}
