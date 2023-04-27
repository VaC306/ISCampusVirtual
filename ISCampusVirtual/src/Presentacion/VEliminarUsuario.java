package Presentacion;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Presentacion.Control.Controller;
import Presentacion.Control.IGUI;

public class VEliminarUsuario extends JFrame  implements IGUI{
	private JTextField id;
	private JButton ok;
	private String id_s;
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
		ok.addActionListener((e) -> valid());

		mainPanel.add(new JLabel("Id del usuario: "));
		mainPanel.add(id);
		mainPanel.add(ok);
		setVisible(true);
	}

	void valid() {
		id_s = id.getText();
		setVisible(false);
	}

	@Override
	public void update(int event, Object datos) {
		// TODO Auto-generated method stub
		
	}
}
