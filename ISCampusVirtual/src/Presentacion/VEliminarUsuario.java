package Presentacion;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Aula.TransferAsignatura;
import Presentacion.Control.Controller;
import Presentacion.Control.Events;
import Presentacion.Control.IGUI;

public class VEliminarUsuario extends JFrame  implements IGUI{
	private JTextField id;
	private JButton ok;
	private JButton cancel;
	private TransferAsignatura tAsignatura;
 

	Controller ctrl;

	public VEliminarUsuario() {
		super("Eliminar Usuario");
		ctrl=Controller.obtenerInstancia();
		initIGUI();

	}

	void initIGUI() {
		setSize(600, 100);
		setLocationRelativeTo(null);
		JPanel mainPanel = new JPanel(new FlowLayout());
		setContentPane(mainPanel);

		id = new JTextField(8);
		ok = new JButton("OK");
		ok.addActionListener((e) -> {
			
			ctrl.accion(Events.ELIMINAR_USUARIO, id.getText());
		});
		
		cancel= new JButton("Cancelar");
		cancel.addActionListener(e->{
			
			setVisible(false);
			ctrl.accion(Events.ABRIR_VISTA_EDITAR_ASIGNATURA, tAsignatura);
		});

		mainPanel.add(new JLabel("Id del usuario: "));
		mainPanel.add(id);
		mainPanel.add(ok);
		mainPanel.add(cancel);

		setVisible(true);
	}

	void valid() {
		id.getText();
		setVisible(false);
	}

	@Override
	public void update(int event, Object datos) {
		switch (event) {
		case Events.ELIMINAR_USUARIO_ERROR: 
			JOptionPane.showMessageDialog(this, "Id no valida");
			id.setText("");
			break;
			
		case Events.ELIMINAR_USUARIO_ACIERTO: 
			
			setVisible(false);
			ctrl.accion(Events.ABRIR_VISTA_EDITAR_ASIGNATURA, datos);
			
			break;
			
		default:
			
			tAsignatura=(TransferAsignatura) datos;			
		}
	}
}
