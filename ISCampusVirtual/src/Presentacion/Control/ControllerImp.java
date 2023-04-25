package Presentacion.Control;

import Negocio.Aula.SAAsignatura;
import Negocio.Aula.TransferAsignatura;
import Negocio.Usuario.SAAlumno;
import Negocio.Usuario.SAProfesor;
import Negocio.Usuario.SAUsuario;
import Negocio.Usuario.TransferAlumno;
import Negocio.Usuario.TransferProfesor;
import Negocio.Usuario.TransferUsuario;
import Presentacion.FactoriaVistas;
//siu
public class ControllerImp extends Controller{
	
	private SAUsuario saUsuario=new SAUsuario();
	private SAAsignatura saAsignatura;
	
	private IGUI currentIGUI;

	private TransferUsuario tUsuarioIniciado = null;
	
	
	@Override
	public void accion(int evento, Object datos) {

		TransferAlumno tAlumno;
		TransferProfesor tProfesor;
		TransferAsignatura tAsignatura = null;
		
		switch(evento) {
		case Events.ABRIR_INICIAR_SESION:
			currentIGUI=FactoriaVistas.getInstance().crearVista(evento, null);
			break;
		case Events.INICIAR_SESION:{
			String[]info=(String[]) datos;
			
			tUsuarioIniciado = saUsuario.iniciarSesion(info[0], info[1]);
			
			if(tUsuarioIniciado!=null) {
				currentIGUI.update(Events.INICIAR_SESION_CORRECTO, tUsuarioIniciado);
				
			}else {
				currentIGUI.update(Events.INICIAR_SESION_FALLIDO, null);
			}
			break;
		}
		case Events.ABRIR_VMOSTRAR_PARTICIPANTES_ASIGNATURA:
			tAsignatura=(TransferAsignatura) datos;
			currentIGUI=FactoriaVistas.getInstance().crearVista(evento, null);
			currentIGUI.update(Events.MOSTRAR_USUARIOS_ASIGNATURA, tAsignatura.getUsuarios());
			
			break;
			
		case Events.MOSTRAR_ALUMNOS_ASIGNATURA:
			tAsignatura=(TransferAsignatura) datos;

			if(tAsignatura!=null)
				currentIGUI.update(evento, tAsignatura.getAlumno());
			else
				currentIGUI.update(evento, null);

			break;
			
		case Events.MOSTRAR_PROFESORES_ASIGNATURA:
			tAsignatura=(TransferAsignatura) datos;

			
			if(tAsignatura!=null)
				currentIGUI.update(evento, tAsignatura.getProfesor());
			else
				currentIGUI.update(evento, null);
			break;
			
		case Events.ABRIR_VISTA_LISTA_ASIGNATURAS:
			
			currentIGUI=FactoriaVistas.getInstance().crearVista(evento,null);
			currentIGUI.update(evento,  saUsuario.getAsignaturas(tUsuarioIniciado));
			
			break;
			
		case Events.ABRIR_VISTA_ASIGNATURA:
			tAsignatura=(TransferAsignatura) datos;
			
			if(!tUsuarioIniciado.esProfesor()) {

				currentIGUI = FactoriaVistas.getInstance().crearVista(Events.ABRIR_VISTA_ASIGNATURA, null);

			}
			else {
				
				currentIGUI=FactoriaVistas.getInstance().crearVista(Events.ABRIR_VISTA_ASIGNATURA_PROFESOR, null);

			}
			currentIGUI.update(evento, tAsignatura);

			break;
		
		case Events.ABRIR_VISTA_CALENDARIO:
			
			
			currentIGUI=FactoriaVistas.getInstance().crearVista(Events.ABRIR_VISTA_CALENDARIO, null);
			currentIGUI.update(evento, saUsuario.getTareas(tUsuarioIniciado));
			
			break;

		case Events.ABRIR_VISTA_USUARIO:
			
			if(tUsuarioIniciado.esProfesor() || tUsuarioIniciado==datos) {
				
				currentIGUI=FactoriaVistas.getInstance().crearVista(Events.ABRIR_VISTA_EDITAR_USUARIO, null);
			}
			else {
			
				currentIGUI=FactoriaVistas.getInstance().crearVista(Events.ABRIR_VISTA_USUARIO, null);
			}
			
			currentIGUI.update(evento, datos);
			break;

			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		}
		

		
	
			
	}
}
	

	

