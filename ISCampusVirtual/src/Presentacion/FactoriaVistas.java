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
	//prueba
	public IGUI crearVista(int event, Object data) {
		
		switch(event) {
		case Events.ABRIR_INICIAR_SESION:
			return new ViniciarSesion() ;
			
		case Events.ABRIR_VISTA_LISTA_ASIGNATURAS:
			return new VMostrarListaAsignaturas();
			
		case Events.ABRIR_VISTA_ASIGNATURA:
			return new VAsignatura();
		
		case Events.ABRIR_VISTA_ASIGNATURA_PROFESOR:
			return new VAsignaturaProfesor();
			
		case Events.ABRIR_VMOSTRAR_PARTICIPANTES_ASIGNATURA:
			return new VMostrarPartiAsignaturas();
		
		case Events.ABRIR_VISTA_CALENDARIO:
			return new VCalendarioTareas();
			
		case Events.ABRIR_VISTA_USUARIO:
			return new VUsuario();
			
		case Events.ABRIR_VISTA_EDITAR_USUARIO:
			return new VUsuarioEditar();
			
		case Events.ABRIR_VISTA_EDITAR_ASIGNATURA:
			return new VAsignaturaEditar();
			
		case Events.ABRIR_VISTA_ANADIR_TAREA:
			return new VAniadirTarea();
			
		case Events.ABRIR_VISTA_ELIMINAR_TAREA:
			return new VEliminarTareas();
		
		case Events.ABRIR_VISTA_ANADIR_USUARIO:
			return new VAniadirUsuario();
		
		case Events.ABRIR_VISTA_ELIMINAR_USUARIO:
			return new VEliminarUsuario();
		
		case Events.ABRIR_VISTA_ANADIR_APUNTES:
			return new VAniadirApuntes();
		
		case Events.ABRIR_VISTA_ELIMINAR_APUNTES:
			return new VEliminarApuntes();
		
		case Events.ABRIR_VISTA_TEMA:
			return new VTema();
		
		case Events.ABRIR_VISTA_CREAR_USUARIO:
			return new VUsuarioCrear();
			
		case Events.ABRIR_VISTA_FORO:
			return new VForo();

		}
		return null;
	}

}
