package Negocio.Foro;

import Negocio.Aula.TransferAsignatura;
//a
public class SAForo {

	public void anadirMensaje(TransferForo foro, TransferMensaje mensaje) {
		foro.addMensaje(foro.getMensaje(), mensaje);
	}

}
