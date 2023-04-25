package Presentacion;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Negocio.Usuario.TransferUsuario;
import Presentacion.Control.Controller;
import Presentacion.Control.Events;
import Presentacion.Control.IGUI;

public class VPerfil extends JFrame implements IGUI{
	
	protected Controller ctrl;
	TransferUsuario usuario;
	private JPanel panelLabels;
	private JFrame ventana;
	private JLabel nombre;
	
	
	public VPerfil() {
		super();
		ctrl = Controller.obtenerInstancia();

	}
	
	
	protected void initIGUI()
	{
		ventana = new JFrame();
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panelLabels = new JPanel();
		panelLabels.setLayout(new BoxLayout(panelLabels, BoxLayout.Y_AXIS));
		
		nombre = new JLabel("Nombre: " + usuario.getNombre_Apellidos());
		panelLabels.add(nombre);
		
		
		
		
		JButton botonEditar= new JButton("Editar");
		botonEditar.addActionListener(e->{
			
			ctrl.accion(Events.ABRIR_VISTA_EDITAR_USUARIO, usuario);
		});
		
		
		JButton botonCerrar= new JButton("Cerrar");
		botonCerrar.addActionListener(e->{
			
			this.setVisible(false);
		});
	}
	
	@Override
	public void update(int event, Object datos) {
		
		usuario=(TransferUsuario) datos;
		initIGUI();
	}
	
	
	
}
