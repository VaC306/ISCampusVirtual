package Integracion;

public abstract class FactoriaIntegracion {
	
	private static FactoriaIntegracion instancia;
	
	public static FactoriaIntegracion getInstancia() {
		
		if (instancia==null) {
			
			instancia= new FactoriaIntegracionImp();
		}
		
		return instancia;
	}

}
