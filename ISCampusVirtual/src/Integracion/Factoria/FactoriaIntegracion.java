package Integracion.Factoria;

import Integracion.DAOAsignatura;

public abstract class FactoriaIntegracion {
	
	private static FactoriaIntegracion instancia;
	
	public static FactoriaIntegracion getInstancia() {
		
		if (instancia==null) {
			
			instancia= new FactoriaIntegracionImp();
		}
		
		return instancia;
	}

	public abstract DAOAsignatura generarDAOAsignatura();

}
