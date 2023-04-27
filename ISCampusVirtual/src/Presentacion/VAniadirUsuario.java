package Presentacion;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Aula.TransferAsignatura;
import Presentacion.Control.Controller;
import Presentacion.Control.Events;
import Presentacion.Control.IGUI;

public class VAniadirUsuario extends JFrame  implements IGUI{

	private TransferAsignatura tAsignatura;
	private JTextField id;
	private JButton ok;
	private String id_s;
	Controller ctrl;

	public VAniadirUsuario() {
		super("A�adir Usuario");
		ctrl=Controller.obtenerInstancia();
		
		initIGUI();

	}

	void initIGUI() {

		JPanel mainPanel = new JPanel(new FlowLayout());

		setSize(600, 100);
		setContentPane(mainPanel);
		setLocationRelativeTo(null);

		id = new JTextField(8);
		ok = new JButton("OK");
		ok.addActionListener((e) -> {
			
			ctrl.accion(Events.ANADIR_USUARIO, id.getText());
		});

		mainPanel.add(new JLabel("Id del usuario: "));
		mainPanel.add(id);
		mainPanel.add(ok);
		setVisible(true);
	}



	@Override
	public void update(int event, Object datos) {
		
		switch(event) {
		
		case Events.ANADIR_USUARIO_ERROR:
			setVisible(false);
			ctrl.accion(Events.ANADIR_USUARIO_CREADO, datos);

		break;
		
		case Events.ANADIR_USUARIO_EXITO:
			setVisible(false);
			ctrl.accion(Events.ABRIR_VISTA_EDITAR_ASIGNATURA, datos);
			
		default:
			tAsignatura=(TransferAsignatura) datos;
			
		}		
	}
}
