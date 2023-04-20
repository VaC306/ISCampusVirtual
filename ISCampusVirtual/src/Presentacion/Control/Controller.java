package Presentacion.Control;

public abstract class Controller {
	private static Controller instancia;

	public static Controller obtenerInstancia() {
		if (instancia == null) {
			instancia = new ControllerImp();
		}
		return instancia;
	}
	
	public abstract void accion(int evento, Object datos);

}
