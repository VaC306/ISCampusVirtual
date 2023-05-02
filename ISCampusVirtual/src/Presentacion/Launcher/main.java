package Presentacion.Launcher;

import Negocio.Aula.TransferAsignatura;
import Presentacion.ViniciarSesion;
import Presentacion.Control.Controller;
import Presentacion.Control.Events;

public class main {

	public static void main(String[] args) {
		
		Controller.obtenerInstancia().accion(Events.ABRIR_VISTA_PERFIL_PROPIO, null);

		//Controller.obtenerInstancia().accion(Events.ABRIR_VISTA_EDITAR_ASIGNATURA, new TransferAsignatura());

	}
}