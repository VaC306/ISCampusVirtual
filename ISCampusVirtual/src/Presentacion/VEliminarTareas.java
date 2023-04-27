package Presentacion;

import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Presentacion.Control.Controller;
import Presentacion.Control.IGUI;

public class VEliminarTareas extends JFrame  implements IGUI{
	private JComboBox<String> tareas;
	Controller ctrl;

	public VEliminarTareas() {
		super("Eliminar Tareas");
		ctrl=Controller.obtenerInstancia();
		initIGUI();

	}

	void initIGUI() {
		JPanel mainPanel = new JPanel(new FlowLayout());
		setContentPane(mainPanel);
		setSize(600, 100);
		setLocationRelativeTo(null);
		setResizable(false);
		DefaultComboBoxModel<String> tareas_d = new DefaultComboBoxModel<>();
		tareas_d.addElement("Tarea1");
		tareas_d.addElement("Tarea2");
		tareas_d.addElement("Tarea3");
		tareas = new JComboBox<String>(tareas_d);
		mainPanel.add(new JLabel("Selecciona la tarea a eliminar: "));
		mainPanel.add(tareas);
		JButton ok = new JButton("OK");
		ok.addActionListener((e) -> valid());
		mainPanel.add(ok);
		setVisible(true);
	}

	void valid() {
		tareas.getSelectedItem();
		setVisible(false);
	}

	@Override
	public void update(int event, Object datos) {
		// TODO Auto-generated method stub
		
	}
}
