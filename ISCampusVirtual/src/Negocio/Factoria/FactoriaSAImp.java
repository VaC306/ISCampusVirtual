package Negocio.Factoria;

import Negocio.Aula.SAAsignatura;

public class FactoriaSAImp extends FactoriaSA {
	
	public SAAsignatura generarSAAsignatura() {
		
		return new SAAsignatura();
	}
}
