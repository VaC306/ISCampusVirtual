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
		
		//mostrar temas
		//boton para ver participantes
	}

	@Override
	public void update(int event, Object datos) {
		// TODO Auto-generated method stub
		
	}

}
