package launcher;

import Presentacion.ViniciarSesion;
import Presentacion.Control.Controller;
import Presentacion.Control.Events;

public class main {

	public static void main(String[] args) {
		
		Controller.obtenerInstancia().accion(Events.ABRIR_INICIAR_SESION, null);
	}
}