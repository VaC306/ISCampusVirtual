package Negocio.Factoria;


public abstract class FactoriaSA {
	private static FactoriaSAImp instancia;
	
	public static FactoriaSA getInstancia() {
		
		if (instancia==null) {
			
			instancia= new FactoriaSAImp();
		}
		
		return instancia;
	}
}
