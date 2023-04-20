package launcher;

import control.Controller;
import control.Events;
import vista.ViniciarSesion;

public class main {

	public static void main(String[] args) {
		
		Controller.obtenerInstancia().accion(Events.ABRIR_INICIAR_SESION, null);
	}
}