package vista;

import control.Controller;
import control.IGUI;
import model.aula.TransferAsignatura;

public class VAsignatura implements IGUI {
	
	private Controller ctrl;
	TransferAsignatura asignatura;

	public VAsignatura(TransferAsignatura data) {

		super();
		ctrl=Controller.obtenerInstancia();
		asignatura=data;
	
	}
	
	private void initIGUI() {
		
		//mostrar temas (array Jbuttons)
		
		//boton para ver participantes
		anadirBotonEditar();
	}

	@Override
	public void update(int event, Object datos) {
		// TODO Auto-generated method stub
		
	}
	
	//al ser vista desde un usuario alumno no se anade el boton de editar
	protected void anadirBotonEditar() {}

}
