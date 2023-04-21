package Presentacion;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Negocio.Usuario.TransferUsuario;
import Presentacion.Control.Controller;
import Presentacion.Control.IGUI;

public class VPerfil implements IGUI{
	
	protected Controller ctrl;
	TransferUsuario usuario;
	private JPanel panelLabels;
	private JFrame ventana;
	private JLabel nombre;
	
	
	public VPerfil(TransferUsuario data) {
		super();
		ctrl = Controller.obtenerInstancia();
		usuario = data;
		initIGUI();
	}
	
	
	protected void initIGUI()
	{
		ventana = new JFrame();
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panelLabels = new JPanel();
		panelLabels.setLayout(new BoxLayout(panelLabels, BoxLayout.Y_AXIS));
		
		nombre = new JLabel("Nombre: " + usuario.getNombre_Apellidos());
		panelLabels.add(nombre);
		
		
		
		
	}
	
	@Override
	public void update(int event, Object datos) {
		
	}
	
	
	
}
