package Presentacion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Presentacion.Control.Controller;
import Presentacion.Control.IGUI;

public class VAniadirTarea extends JFrame  implements IGUI{

	private JTextField nombre;
	private JTextField fecha;
	private JButton load;
	private JButton ok;
	private JFileChooser fc;
	private JLabel file_name;
	Controller ctrl;

	public VAniadirTarea() {
		super("A�adir Tarea");
		fc = new JFileChooser();
		ctrl=Controller.obtenerInstancia();
		initIGUI();

	}

	void initIGUI() {
		JPanel mainPanel = new JPanel(new FlowLayout());
		setContentPane(mainPanel);
		setResizable(false);
		setSize(700, 100);
		setLocationRelativeTo(null);
		load = new JButton("Archivo");
		file_name = new JLabel();
		mainPanel.add(load);
		mainPanel.add(file_name);
		load.addActionListener((e) -> load());
		ok = new JButton("OK");
		ok.addActionListener((e) -> valid());
		mainPanel.add(new JLabel("Nombre tarea: "));
		nombre = new JTextField(8);
		mainPanel.add(nombre);
		mainPanel.add(new JLabel("Fecha entrega: "));
		fecha = new JTextField(8);
		mainPanel.add(fecha);
		mainPanel.add(new JLabel("(Formato: dd/mm/yy)"));
		mainPanel.add(ok, BorderLayout.CENTER);
		setVisible(true);
	}

	public void load() {
		int returnval = fc.showOpenDialog(this);
		if (returnval == JFileChooser.APPROVE_OPTION) {
			try {
				file_name.setText(fc.getSelectedFile().getName());
				InputStream is = new FileInputStream(fc.getSelectedFile());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "No se ha podido abrir el archivo");
			}
		}
	}

	public void valid() {
		nombre.getText();
		fecha.getText();
		setVisible(false);
	}

	@Override
	public void update(int event, Object datos) {
		// TODO Auto-generated method stub
		
	}
}
