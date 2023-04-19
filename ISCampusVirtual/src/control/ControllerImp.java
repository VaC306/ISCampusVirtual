package control;

import model.aula.SAAsignatura;
import model.aula.TransferAsignatura;
import model.usuario.SAAlumno;
import model.usuario.SAProfesor;
import model.usuario.SAUsuario;
import model.usuario.TransferAlumno;
import model.usuario.TransferProfesor;
import vista.FactoriaVistas;
//siu
public class ControllerImp extends Controller{
	
	private SAUsuario saUsuario;
	private SAAsignatura saAsignatura;
	
	private IGUI currentIGUI;

	@Override
	public void accion(int evento, Object datos) {
		
		TransferAlumno tAlumno;
		TransferProfesor tProfesor;
		TransferAsignatura tAsignatura = null;
		
		switch(evento) {
		case Events.ABRIR_INICIAR_SESION:
			currentIGUI=FactoriaVistas.getInstance().crearVista(evento, datos);
			break;
		case Events.INICIAR_SESION:{
			String[]info=(String[]) datos;
			
			if(saUsuario.iniciarSesion(info[0], info[1])) {
				currentIGUI.update(Events.INICIAR_SESION_CORRECTO, datos);
			}else {
				currentIGUI.update(Events.INICIAR_SESION_FALLIDO, datos);
			}
		}
		case Events.ABRIR_VMOSTRAR_PARTICIPANTES_ASIGNATURA:
			tAsignatura=(TransferAsignatura) datos;
			currentIGUI=FactoriaVistas.getInstance().crearVista(evento, null);
			currentIGUI.update(Events.MOSTRAR_USUARIOS_ASIGNATURA, tAsignatura.getUsuarios());
			
			
		case Events.MOSTRAR_ALUMNOS_ASIGNATURA:
			if(tAsignatura!=null)
				currentIGUI.update(evento, tAsignatura.getAlumno());
			else
				currentIGUI.update(evento, null);

			
		case Events.MOSTRAR_PROFESORES_ASIGNATURA:
			
			if(tAsignatura!=null)
				currentIGUI.update(evento, tAsignatura.getProfesor());
			else
				currentIGUI.update(evento, null);
		
		}
		
		
		
	
			
	}
	

	
}
