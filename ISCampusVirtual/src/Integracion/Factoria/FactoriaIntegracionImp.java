package Integracion.Factoria;

import Integracion.*;


public class FactoriaIntegracionImp extends FactoriaIntegracion {
	
	public DAOApuntes generarDAOApuntes() {
		
		return new DAOApuntes();
	}
	
	public DAOAlumno generarDAOAlumno() {
		
		return new DAOAlumnoImpl();
	}
	
	public DAOArchivo generarDAOArchivo() {
		
		return new DAOArchivo();
	}
	
	public DAOAsignatura generarDAOAsignatura() {
		
		return new DAOAsignatura();
	}
	
	public DAOClase generarDAOClase() {
		
		return new DAOClase();
	}
	
	public DAOCurso generarDAOCurso() {
		
		return new DAOCurso();
	}
	
	public DAOMensaje generarDAOMensaje() {
		
		return new DAOMensaje();
	}
	
	public DAOProfesor generarDAOProfesor() {
		
		return new DAOProfesorImpl();
	}
	
	public DAOTarea generarDAOTarea() {
		
		return new DAOTarea();
	}
	
	public DAOTema generarDAOTema() {
		
		return new DAOTema();
	}
	
}
