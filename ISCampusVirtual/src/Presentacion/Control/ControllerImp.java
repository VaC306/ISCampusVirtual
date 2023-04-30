package Presentacion.Control;

import java.util.List;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;

import Negocio.Archivos.TransferApuntes;
import Negocio.Archivos.TransferArchivo;
import Negocio.Archivos.TransferTarea;
import Negocio.Aula.SAAsignatura;
import Negocio.Aula.TransferAsignatura;
import Negocio.Factoria.FactoriaSA;
import Negocio.Factoria.FactoriaUsuario;
import Negocio.Usuario.SAAlumno;
import Negocio.Usuario.SAProfesor;
import Negocio.Usuario.SAUsuario;
import Negocio.Usuario.TransferAlumno;
import Negocio.Usuario.TransferProfesor;
import Negocio.Usuario.TransferUsuario;
import Presentacion.FactoriaVistas;
//siu
public class ControllerImp extends Controller{
	
	private SAUsuario saUsuario= new SAUsuario();
	private SAAsignatura saAsignatura=FactoriaSA.getInstancia().generarSAAsignatura();
	
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
			
			//TODO añadir tarea mediante sa
			if(datos!=null) {
				
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
			
			
			tTarea=(TransferTarea) datos;

			//TODO eliminar tarea sa
			if(datos!=null) {
				
				currentIGUI.update(Events.TAREA_ELIMINADA_EXITO, tTarea);
			}
			else {
				
				currentIGUI.update(Events.TAREA_ELIMINADA_ERROR, tTarea);

			}
			break;

		case Events.ABRIR_VISTA_ANADIR_USUARIO:
			
			tAsignatura=(TransferAsignatura) datos;
				
			currentIGUI=FactoriaVistas.getInstance().crearVista(evento, null);
			currentIGUI.update(evento, tAsignatura);
			break;

			
		case Events.ANADIR_USUARIO:
			
			String id= (String) datos;
			tUsuario=FactoriaUsuario.getInstance().crearTransferById(id);
			
			
			//se intenta editar el usuario
			if(tUsuario!=null && saUsuario.editarUsuario(tUsuario)) {
				
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
			tUsuario = (TransferUsuario) datos;

			
			currentIGUI=FactoriaVistas.getInstance().crearVista(evento, null);
			
			break;
			
		case Events.ELIMINAR_USUARIO:
			
			String id1= (String) datos;
			
			if(saUsuario.eliminarUsuario(id1)) {
				
				currentIGUI.update(Events.ELIMINAR_USUARIO_ACIERTO, id1);
			}else {
				
				currentIGUI.update(Events.ELIMINAR_USUARIO_ERROR, id1);
			}
			
			break;

			
		case Events.ABRIR_VISTA_ANADIR_APUNTES:
			
			tAsignatura=(TransferAsignatura) datos;
			
			currentIGUI=FactoriaVistas.getInstance().crearVista(evento, null);
			currentIGUI.update(evento, tAsignatura);

			
			break;
			
		case Events.ANADIR_APUNTES:
			
			
			String idApuntes=(String) datos;
			//TODO get tApuntes by id
			
			currentIGUI.update(evento, null);

			
			break;
			
		case Events.ABRIR_VISTA_ELIMINAR_APUNTES:
			
			
			tAsignatura=(TransferAsignatura) datos;
			Pair <TransferAsignatura, List<TransferArchivo>> infoApuntes = new Pair<>(tAsignatura, saAsignatura.getApuntes(tAsignatura));

			currentIGUI=FactoriaVistas.getInstance().crearVista(evento, null);
			currentIGUI.update(evento,infoApuntes);

			
			break;
		case Events.ELIMINAR_APUNTES:
			
			String idApuntes1= (String) datos;
			//TODO eliminarapuntes(idTarea) SAApuntes
					
			if(true) {
				
				currentIGUI.update(Events.APUNTES_ELIMINADA_EXITO, null);

			}
			else {
				
				currentIGUI.update(Events.APUNTES_ELIMINADA_ERROR, null);

			}
			
			break;
			
		case Events.ABRIR_VISTA_CREAR_USUARIO:
			tAsignatura =(TransferAsignatura) datos;
			
			currentIGUI=FactoriaVistas.getInstance().crearVista(evento, datos);
			currentIGUI.update(Events.ABRIR_VISTA_CREAR_USUARIO, datos);

			
			
			break;
	
			
		case Events.CREAR_USUARIO:
			Pair <TransferAsignatura, TransferUsuario> info2 =(Pair<TransferAsignatura, TransferUsuario>) datos;

			tUsuario= info2.right;
			tAsignatura=info2.left;
			saUsuario.crearUsuarioConAsignatura(tUsuario,tAsignatura );
			saAsignatura.anadirUsuario(tAsignatura, tUsuario);
			
			if(saUsuario.crearUsuario(tUsuario)) {
				
				currentIGUI.update(Events.CREAR_USUARIO_EXITO, null);
				currentIGUI=FactoriaVistas.getInstance().crearVista(Events.ABRIR_VISTA_EDITAR_ASIGNATURA, null);

			}
			else {
				
				currentIGUI.update(Events.CREAR_USUARIO_ERROR, null);

			}

			break;
			
			
			
			
			
			
			
			
			
			
			

		}
		

			
			
		
	
			
	}
}
	

	

