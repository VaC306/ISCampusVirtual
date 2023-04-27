package Negocio.Factoria;

import Negocio.Aula.SAAsignatura;

public abstract class FactoriaSA {
	private static FactoriaSAImp instancia;
	
	public static FactoriaSA getInstancia() {
		
		if (instancia==null) {
			
			instancia= new FactoriaSAImp();
		}
		
		return instancia;
	}
	
	public abstract SAAsignatura generarSAAsignatura();

}
