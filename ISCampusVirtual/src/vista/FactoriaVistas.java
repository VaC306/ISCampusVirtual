package vista;

import java.util.ArrayList;

import control.Events;
import control.IGUI;
import model.aula.TransferAsignatura;

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
			
		case Events.ABRIR_VISTA_LISTA_ASIGNATURAS:
			return new VMostrarAsignaturas((ArrayList<TransferAsignatura>) data);
			
		case Events.ABRIR_VISTA_ASIGNATURA:
			return new VAsignatura((TransferAsignatura) data);
		
		case Events.ABRIR_VISTA_ASIGNATURA_PROFESOR:
			return new VAsignaturaProfesor((TransferAsignatura) data);
			
		case Events.ABRIR_VMOSTRAR_PARTICIPANTES_ASIGNATURA:
			return new VMostrarPartiAsignaturas();
			
		}
		return null;
	}

}
