package Presentacion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import Integracion.DAOTarea;
import Integracion.DAOTareaImpl;
import Negocio.Archivos.TransferTarea;
import Negocio.Aula.TransferAsignatura;
import Negocio.Aula.TransferTema;
import Presentacion.Control.Controller;
import Presentacion.Control.Events;
import Presentacion.Control.IGUI;

public class VAniadirTarea extends JFrame  implements IGUI{
	
	private TransferAsignatura tAsignatura;
	private JTextField nombre;
	private JTextField fecha;
	private JButton load;
	private JButton ok;
	private JFileChooser fc;
	private JLabel file_name;
	JComboBox tema;
	Controller ctrl;

	public VAniadirTarea() {
		super("Anadir Tarea");
		fc = new JFileChooser();
		ctrl=Controller.obtenerInstancia();

	}

	void initIGUI() {
		JPanel mainPanel = new JPanel(new FlowLayout());
		setContentPane(mainPanel);
		setResizable(false);
		setSize(700, 125);
		setLocationRelativeTo(null);
		load = new JButton("Archivo");
		file_name = new JLabel();
		mainPanel.add(load);
		mainPanel.add(file_name);
		load.addActionListener((e) -> load());
		ok = new JButton("OK");
		ok.addActionListener((e) -> {

			if (fecha.getText() != "") {

				TransferTarea tt = new TransferTarea();
				// new TransferTarea;

				// Primero crear Archivo TODO
				// tt.setNombre(nombre.getText());
				tt.setId("AR010");
				tt.setIdTarea("T004");
				tt.setFecha_de_entrega(new Date(fecha.getText()));
				tt.setTemas(tema.getSelectedItem().toString());
				// VER COMO AÑADIR ESTO TODO
				ctrl.accion(Events.TAREA_ANADIR, tt);
			}

		});
		
		
		
		
		
		mainPanel.add(new JLabel("Nombre tarea: "));
		nombre = new JTextField(8);
		mainPanel.add(nombre);
		mainPanel.add(new JLabel("Fecha entrega: "));
		fecha = new JTextField(8);
		mainPanel.add(fecha);
		mainPanel.add(new JLabel("(Formato: dd/mm/yy)"));
		
		mainPanel.add(new JLabel("Tema:"));

		DefaultComboBoxModel<String> temas_d = new DefaultComboBoxModel<>();
		
		for(TransferTema tt: tAsignatura.getTemas()) {
			
			temas_d.addElement(tt.getId());

		}
		
		tema = new JComboBox<String>(temas_d);
		mainPanel.add(tema);
		
		
		JPanel panelOkCancel =new JPanel();
		
		JButton cancel= new JButton("Cancelar");
		cancel.addActionListener(e->{
			
			setVisible(false);
			ctrl.accion(Events.ABRIR_VISTA_EDITAR_ASIGNATURA,tAsignatura );
		});
		panelOkCancel.add(ok);
		panelOkCancel.add(cancel);
		
		mainPanel.add(panelOkCancel, BorderLayout.PAGE_END);
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
			initIGUI();

		}

		
		
	}
}
