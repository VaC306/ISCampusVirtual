package Presentacion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Integracion.DAOTarea;
import Integracion.DAOTareaImpl;
import Negocio.Archivos.Tipos_archivo;
import Negocio.Archivos.TransferTarea;
import Negocio.Aula.TransferAsignatura;
import Presentacion.Control.Controller;
import Presentacion.Control.Events;
import Presentacion.Control.IGUI;

public class VAniadirTarea extends JFrame  implements IGUI{
	
	private TransferAsignatura tAsignatura;
	private JTextField nombre;
	private JTextField fecha;
	private JTextField tipo_archivo;
	private JButton load;
	private JButton ok;
	private JFileChooser fc;
	private JLabel file_name;
	Controller ctrl;

	public VAniadirTarea() {
		super("Anadir Tarea");
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
		ok.addActionListener((e) -> {
			TransferTarea tt= new TransferTarea();
			
			tt.setNombre(nombre.getText());
			tt.setFecha_de_entrega(new Date(fecha.getText()));
			tt.setTipo_archivo(Tipos_archivo.valueOf(tipo_archivo.getText()));
			ctrl.accion(Events.TAREA_ANADIR, tt);
		});
		
		mainPanel.add(new JLabel("Nombre tarea: "));
		nombre = new JTextField(8);
		mainPanel.add(nombre);
		mainPanel.add(new JLabel("Tipo del Archivo: "));
		tipo_archivo = new JTextField(4);
		mainPanel.add(tipo_archivo);
		mainPanel.add(new JLabel("Fecha entrega: "));
		fecha = new JTextField(8);
		mainPanel.add(fecha);
		mainPanel.add(new JLabel("(Formato: dd/mm/yy)"));
		mainPanel.add(ok);
		JButton cancel= new JButton("Cancelar");
		cancel.addActionListener(e->{
			
			setVisible(false);
			ctrl.accion(Events.ABRIR_VISTA_EDITAR_ASIGNATURA,tAsignatura );
		});
		mainPanel.add(cancel);
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
		
		
	}

	@Override
	public void update(int event, Object datos) {
		
		switch(event) {
		
		case Events.TAREA_ANADIDA_EXITO:
			DAOTarea dao = new DAOTareaImpl();
			dao.create((TransferTarea) datos);
			setVisible(false);
			ctrl.accion(Events.ABRIR_VISTA_EDITAR_ASIGNATURA, tAsignatura);
		break;
			
		case Events.TAREA_ANADIDA_ERROR:
			fecha.setText("");
			nombre.setText("");	
		break;
		
		default:
			tAsignatura=(TransferAsignatura) datos;
			
		}

		
		
	}
}
