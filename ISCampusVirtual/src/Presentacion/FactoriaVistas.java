package Presentacion;

import java.util.ArrayList;
import java.util.List;

import Negocio.Archivos.TransferTarea;
import Negocio.Aula.TransferAsignatura;
import Negocio.Usuario.TransferUsuario;
import Presentacion.Control.Events;
import Presentacion.Control.IGUI;

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
			return new VMostrarListaAsignaturas((ArrayList<TransferAsignatura>) data);
			
		case Events.ABRIR_VISTA_ASIGNATURA:
			return new VAsignatura((TransferAsignatura) data);
		
		case Events.ABRIR_VISTA_ASIGNATURA_PROFESOR:
			return new VAsignaturaProfesor((TransferAsignatura) data);
			
		case Events.ABRIR_VMOSTRAR_PARTICIPANTES_ASIGNATURA:
			return new VMostrarPartiAsignaturas();
		
		case Events.ABRIR_VISTA_CALENDARIO:
			return new VCalendarioTareas((List<TransferTarea>) data);
			
		case Events.ABRIR_VISTA_USUARIO:
			return new VPerfil((TransferUsuario) data);
			
		case Events.ABRIR_VISTA_EDITAR_USUARIO:
			return new VPerfilEditar((TransferUsuario) data);
		}
		return null;
	}

}
