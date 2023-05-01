package Negocio.Factoria;

import Negocio.Aula.SAAsignatura;
import Negocio.Aula.SAClase;
import Negocio.Aula.SACurso;
import Negocio.Aula.SATema;
import Negocio.Foro.SAMensaje;
import Negocio.Usuario.SAAlumno;
import Negocio.Usuario.SAProfesor;
import Negocio.Usuario.SAUsuario;

public abstract class FactoriaSA {
	private static FactoriaSAImp instancia;
	
	public static FactoriaSA getInstancia() {
		
		if (instancia==null) {
			
			instancia = new FactoriaSAImp();
		}
		
		return instancia;
	}
	
	public abstract SAAsignatura generarSAAsignatura();
	
	public abstract SAClase generarSAAClase();
	
	public abstract SACurso generarSACurso();
	
	public abstract SATema generarSATema();
	
	public abstract SAMensaje generarSAMensaje();
	
	public abstract SAAlumno generarSAAlumno();
	
	public abstract SAProfesor generarSAProfesor();
	
	public abstract SAUsuario generarSAUsuario();
}
