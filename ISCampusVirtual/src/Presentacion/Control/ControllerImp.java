package Presentacion.Control;

import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;

import Negocio.Archivos.SAApuntes;
import Negocio.Archivos.SATarea;
import Negocio.Archivos.TransferApuntes;
import Negocio.Archivos.TransferArchivo;
import Negocio.Archivos.TransferTarea;
import Negocio.Aula.SAAsignatura;
import Negocio.Aula.SAClase;
import Negocio.Aula.SACurso;
import Negocio.Aula.SATema;
import Negocio.Aula.TransferAsignatura;
import Negocio.Aula.TransferTema;
import Negocio.Factoria.FactoriaSA;
import Negocio.Factoria.FactoriaUsuario;
import Negocio.Foro.SAForo;
import Negocio.Foro.SAMensaje;
import Negocio.Foro.TransferForo;
import Negocio.Foro.TransferMensaje;
import Negocio.Usuario.SAAlumno;
import Negocio.Usuario.SAProfesor;
import Negocio.Usuario.SAUsuario;
import Negocio.Usuario.TransferAlumno;
import Negocio.Usuario.TransferProfesor;
import Negocio.Usuario.TransferUsuario;
import Presentacion.FactoriaVistas;
//siu
public class ControllerImp extends Controller{
	
	private SAUsuario saUsuario = FactoriaSA.getInstancia().generarSAUsuario();
	private SAAsignatura saAsignatura = FactoriaSA.getInstancia().generarSAAsignatura();
	private SAApuntes saApuntes = FactoriaSA.getInstancia().generarSAApuntes();
	private SAMensaje saMensaje = FactoriaSA.getInstancia().generarSAMensaje();
	private SAForo saForo = FactoriaSA.getInstancia().generarSAForo();
	private SATema saTema = FactoriaSA.getInstancia().generarSATema();
	private SAAlumno saAlumno = FactoriaSA.getInstancia().generarSAAlumno();
	private SAProfesor saProfesor = FactoriaSA.getInstancia().generarSAProfesor();
	private SACurso saCurso = FactoriaSA.getInstancia().generarSACurso();
	private SATarea saTarea = FactoriaSA.getInstancia().generarSATarea();

	private IGUI currentIGUI;

	private TransferUsuario tUsuarioIniciado = null;
	
	
	@Override
	public void accion(int evento, Object datos) {
		
		TransferUsuario tUsuario;
		TransferAlumno tAlumno;
		TransferProfesor tProfesor;
		TransferAsignatura tAsignatura;
		TransferTarea tTarea;
		TransferApuntes tApuntes;
		TransferTema tTema;
		
		
		
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
			currentIGUI.update(Events.MOSTRAR_USUARIOS_ASIGNATURA, tAsignatura);
			
			break;
			
		case Events.MOSTRAR_ALUMNOS_ASIGNATURA:
			tAsignatura=saAsignatura.getById((String) datos);

			if(tAsignatura!=null)
				currentIGUI.update(evento, tAsignatura);
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
		case Events.ABRIR_VISTA_PERFIL_PROPIO:
			
			currentIGUI=FactoriaVistas.getInstance().crearVista(Events.ABRIR_VISTA_EDITAR_USUARIO, null);
			currentIGUI.update(evento, tUsuarioIniciado);

			
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
						
		case Events.ABRIR_VISTA_CALENDARIO_ASIGNATURA:
			
			tAsignatura=(TransferAsignatura) datos;
			currentIGUI=FactoriaVistas.getInstance().crearVista(Events.ABRIR_VISTA_CALENDARIO, null);
			currentIGUI.update(evento, saAsignatura.getTareas(tAsignatura));
			

		case Events.ABRIR_VISTA_USUARIO:
			
			if(tUsuarioIniciado.esProfesor() || tUsuarioIniciado==datos) {
				
				currentIGUI=FactoriaVistas.getInstance().crearVista(Events.ABRIR_VISTA_EDITAR_USUARIO, null);
			}
			else {
			
				currentIGUI=FactoriaVistas.getInstance().crearVista(Events.ABRIR_VISTA_USUARIO, null);
			}
			
			currentIGUI.update(evento, datos);
			break;

			
		case Events.EDITAR_USUARIO:
			
			tUsuario=(TransferUsuario) datos;
			if(saUsuario.editarUsuario(tUsuario)) {
				
				currentIGUI.update(Events.EDITAR_USUARIO_EXITO, tUsuario);
			}
			else {
				currentIGUI.update(Events.EDITAR_USUARIO_ERROR, tUsuario);

			}
			
			break;
			
		case Events.ABRIR_VISTA_EDITAR_ASIGNATURA: 
			
			tAsignatura=(TransferAsignatura) datos;
			
			currentIGUI=FactoriaVistas.getInstance().crearVista(evento, null);
			currentIGUI.update(evento, tAsignatura);
			
			break;
			
		case Events.ABRIR_VISTA_ANADIR_TAREA:
			
			tAsignatura=(TransferAsignatura) datos;
			currentIGUI=FactoriaVistas.getInstance().crearVista(evento, null);
			currentIGUI.update(evento, tAsignatura);
			
			
			break;
			
		case Events.TAREA_ANADIR:
			tTarea=(TransferTarea) datos;
			tTarea.setUsuario(tUsuarioIniciado.getNIF());
			if(SATarea.add(tTarea)) {
				
				currentIGUI.update(Events.TAREA_ANADIDA_EXITO, tTarea);
			}
			else {
				
				currentIGUI.update(Events.TAREA_ANADIDA_ERROR, tTarea);

			}
			break;
		case Events.ABRIR_VISTA_ELIMINAR_TAREA:
			
			tAsignatura=(TransferAsignatura) datos;
			Pair <TransferAsignatura, List<TransferTarea>> info = new Pair<>(tAsignatura, saAsignatura.getTareas(tAsignatura));
		
			
			currentIGUI=FactoriaVistas.getInstance().crearVista(evento, null);
			currentIGUI.update(evento, info);
			
			break;

		case Events.TAREA_ELIMINADA:
			
			
			String id1 = (String) datos;
			
			if(SATarea.eliminateById(id1)) {
				
				currentIGUI.update(Events.TAREA_ELIMINADA_EXITO, id1);
			}
			else {
				
				currentIGUI.update(Events.TAREA_ELIMINADA_ERROR, id1);

			}
			break;

		case Events.ABRIR_VISTA_ANADIR_USUARIO:
			
			tAsignatura=(TransferAsignatura) datos;
				
			currentIGUI=FactoriaVistas.getInstance().crearVista(evento, null);
			currentIGUI.update(evento, tAsignatura);
			break;

			
		case Events.ANADIR_USUARIO:
			Pair<String, TransferAsignatura> info6=(Pair<String, TransferAsignatura>) datos;
			String id= info6.left;
			tAsignatura=info6.right;
			tUsuario=FactoriaUsuario.getInstance().crearTransferByCorreo(id);
			
			
			//se intenta editar el usuario
			if(tUsuario!=null && saUsuario.editarAsignaturaUsuario(tUsuario,tAsignatura.getID())) {
				saAsignatura.anadirUsuario(tAsignatura, tUsuario);
				saUsuario.anadirAsignatura(tAsignatura, tUsuario);
				currentIGUI.update(Events.ANADIR_USUARIO_EXITO, tUsuario);
			}
			else {
				
				currentIGUI.update(Events.ANADIR_USUARIO_ERROR, tUsuario);
			}
			
			break;

		case Events.ANADIR_USUARIO_CREADO:
			
			tAsignatura=(TransferAsignatura) datos;
			
			currentIGUI=FactoriaVistas.getInstance().crearVista(Events.ABRIR_VISTA_CREAR_USUARIO, datos);
			currentIGUI.update(Events.ABRIR_VISTA_CREAR_USUARIO, tAsignatura);
			

			
			break;
			
			

		case Events.ABRIR_VISTA_ELIMINAR_USUARIO:
			tAsignatura=(TransferAsignatura) datos;
			
			currentIGUI=FactoriaVistas.getInstance().crearVista(evento, null);
			currentIGUI.update(evento, tAsignatura);
			break;
			
		case Events.ELIMINAR_USUARIO:
			Pair <String, TransferAsignatura> info7= (Pair<String, TransferAsignatura>) datos;

			String id2= info7.left;
			tAsignatura=info7.right;
			
			if(saAsignatura.eliminarUsuario(tAsignatura, id2)) {
				
				saUsuario.eliminarDeAsignatura(id2, tAsignatura);
				currentIGUI.update(Events.ELIMINAR_USUARIO_ACIERTO, id2);
			}else {
				
				currentIGUI.update(Events.ELIMINAR_USUARIO_ERROR, id2);
			}
			
			break;

			
		case Events.ABRIR_VISTA_ANADIR_APUNTES:
			
			tAsignatura=(TransferAsignatura) datos;
			
			
			currentIGUI=FactoriaVistas.getInstance().crearVista(evento, null);
			currentIGUI.update(evento, tAsignatura);

			
			break;
			
		case Events.ANADIR_APUNTES:
			
			
			TransferApuntes transferApuntes= (TransferApuntes) datos;
			saApuntes.createApuntesconCreador(transferApuntes, tUsuarioIniciado);
			
			currentIGUI.update(evento, datos);

			
			break;
			
		case Events.ABRIR_VISTA_ELIMINAR_APUNTES:
			
			
			tAsignatura=(TransferAsignatura) datos;
			Pair <TransferAsignatura, List<TransferApuntes>> infoApuntes = new Pair<>(tAsignatura, saAsignatura.getApuntes(tAsignatura));

			currentIGUI=FactoriaVistas.getInstance().crearVista(evento, null);
			currentIGUI.update(evento,infoApuntes);

			
			break;
		case Events.ELIMINAR_APUNTES:
			
			String idApuntes1= (String) datos;
			
			if(saApuntes.eliminarApuntes(idApuntes1)) {
				
				currentIGUI.update(Events.APUNTES_ELIMINADA_EXITO, null);

			}
			else {
				
				currentIGUI.update(Events.APUNTES_ELIMINADA_ERROR, null);

			}
			
			break;
			
		case Events.ABRIR_VISTA_CREAR_USUARIO:
			tAsignatura =(TransferAsignatura) datos;
			
			currentIGUI=FactoriaVistas.getInstance().crearVista(evento, datos);
			currentIGUI.update(Events.ABRIR_VISTA_CREAR_USUARIO, tAsignatura);

			
			
			break;
	
			
		case Events.CREAR_USUARIO:
			Pair <TransferAsignatura, TransferUsuario> info2 =(Pair<TransferAsignatura, TransferUsuario>) datos;

			tUsuario= info2.right;
			tAsignatura=info2.left;
			
			// Leer el usuario que hemos creado?
		
			if(saUsuario.crearUsuarioConAsignatura(tUsuario,tAsignatura )) {
				saAsignatura.anadirUsuario(tAsignatura, tUsuario);
				currentIGUI.update(Events.CREAR_USUARIO_EXITO, null);
				currentIGUI=FactoriaVistas.getInstance().crearVista(Events.ABRIR_VISTA_EDITAR_ASIGNATURA, tAsignatura);

			}
			else {
				
				currentIGUI.update(Events.CREAR_USUARIO_ERROR, null);

			}

			break;
		
		case Events.ABRIR_VISTA_FORO:
			tAsignatura=(TransferAsignatura) datos;
			
			currentIGUI=FactoriaVistas.getInstance().crearVista(evento, null);
			currentIGUI.update(evento, new Pair <TransferAsignatura, TransferUsuario>(tAsignatura, tUsuarioIniciado));
			break;

			
		case Events.ANADIR_MENSAJE:
			Pair <TransferMensaje, TransferForo> info3 = (Pair<TransferMensaje, TransferForo>) datos;

			saForo.anadirMensaje(info3.right, info3.left);
			
			currentIGUI.update( Events.ANADIR_MENSAJE, null);
			break;

		case Events.EDITAR_NOMBRE_TEMA:
			
			tTema =(TransferTema) datos;
			saTema.editarTema(tTema);
			break;
			
		case Events.ABRIR_VISTA_TEMA:
					
    		Pair <TransferAsignatura, TransferTema> info5= (Pair<TransferAsignatura, TransferTema>) datos ;
			
			if(!tUsuarioIniciado.esProfesor()) {

				currentIGUI = FactoriaVistas.getInstance().crearVista(Events.ABRIR_VISTA_TEMA, null);

			}
			else {
				
				currentIGUI=FactoriaVistas.getInstance().crearVista(Events.ABRIR_VISTA_TEMA_PROFESOR, null);

			}
			currentIGUI.update(evento, info5);

			break;
			
			
		case Events.EDITAR_NOMBRE_ASIGNATURA:
			tAsignatura=(TransferAsignatura) datos;
			saAsignatura.editarAsignatura(tAsignatura);
			
			break;
		}
		


			
		
	
			
	}
}
	

	

