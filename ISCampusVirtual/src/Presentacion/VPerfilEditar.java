package Presentacion;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Negocio.Usuario.TransferUsuario;
import Presentacion.Control.Controller;
import Presentacion.Control.IGUI;

public class VPerfilEditar extends JFrame implements IGUI{
	protected Controller ctrl;
	TransferUsuario usuario;
	private JPanel panelLabels;
	private JFrame ventana;
	private JLabel nombre;
	
	
	public VPerfilEditar(TransferUsuario data) {
		super();

		ctrl = Controller.obtenerInstancia();
		usuario = data;
		initIGUI();
	
	}

	private void initIGUI() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(int event, Object datos) {
		// TODO Auto-generated method stub
		
	}

}
