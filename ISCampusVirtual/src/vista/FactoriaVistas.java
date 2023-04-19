package vista;

import control.Events;
import control.IGUI;

public class FactoriaVistas {
	
	private static FactoriaVistas instancia;

	public static FactoriaVistas getInstance() {
		if (instancia == null) {
			instancia = new FactoriaVistas();
		}
		return instancia;
	}
	
	public IGUI crearVista(int event, Object data) {
		
		switch(event) {
		case Events.ABRIR_INICIAR_SESION:
			return new ViniciarSesion() ;
		case Events.ABRIR_VMOSTRAR_PARTICIPANTES_ASIGNATURA:
			return new VMostrarPartiAsignaturas();
		}
		return null;
	}

}
