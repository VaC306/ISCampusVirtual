package Negocio.Factoria;

import Negocio.Archivos.SAApuntes;
import Negocio.Archivos.SATarea;
import Negocio.Aula.SAAsignatura;
import Negocio.Aula.SAClase;
import Negocio.Aula.SACurso;
import Negocio.Aula.SATema;
import Negocio.Foro.SAMensaje;
import Negocio.Usuario.SAAlumno;
import Negocio.Usuario.SAProfesor;
import Negocio.Usuario.SAUsuario;

public class FactoriaSAImp extends FactoriaSA {
	
	@Override
	public SAAsignatura generarSAAsignatura() {
		
		return new SAAsignatura();
	}

	@Override
	public SAClase generarSAAClase() {
		
		return new SAClase();
	}

	@Override
	public SACurso generarSACurso() {
		
		return new SACurso();
	}

	@Override
	public SATema generarSATema() {
		
		return new SATema();
	}

	@Override
	public SAMensaje generarSAMensaje() {
		
		return new SAMensaje();
	}

	@Override
	public SAAlumno generarSAAlumno() {
		
		return new SAAlumno();
	}

	@Override
	public SAProfesor generarSAProfesor() {
		
		return new SAProfesor();
	}

	@Override
	public SAUsuario generarSAUsuario() {
		
		return new SAUsuario();
	}

	@Override
	public SAApuntes generarSAApuntes() {

		return new SAApuntes();
	}

	@Override
	public SATarea generarSATarea() {
		
		return new SATarea();
	}
}
