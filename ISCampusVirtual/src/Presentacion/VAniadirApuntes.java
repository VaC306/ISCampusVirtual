package Presentacion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Integracion.DAOApuntes;
import Integracion.DAOApuntesImpl;
import Negocio.Archivos.Tipos_archivo;
import Negocio.Archivos.TransferApuntes;
import Negocio.Archivos.TransferTarea;
import Negocio.Aula.TransferAsignatura;
import Negocio.Aula.TransferTema;
import Presentacion.Control.Controller;
import Presentacion.Control.Events;
import Presentacion.Control.IGUI;

public class VAniadirApuntes extends JFrame  implements IGUI{
	
	TransferAsignatura tAsignatura;
	TransferApuntes tApuntes;
	private JTextField nombre;
	private JTextField tipo_archivo;
	private JComboBox<String> tema;
	private JButton load;
	private JButton ok;
	private JFileChooser fc;
	private JLabel file_name;
	Controller ctrl;

	public VAniadirApuntes() {
		super("Anadir Apuntes");
		fc = new JFileChooser();
		ctrl=Controller.obtenerInstancia();

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
			tApuntes= new TransferApuntes();
			DAOApuntes daoA = new DAOApuntesImpl();
			
			tApuntes.setId("AAR00"+(daoA.num()+1));
			tApuntes.setIdApuntes("AP00"+(daoA.num()+1));
			tApuntes.setNombre(nombre.getText());
			tApuntes.setTemas(tema.getSelectedItem().toString());
			tApuntes.setTipo_archivo(Tipos_archivo.valueOf(tipo_archivo.getText()));
			ctrl.accion( Events.ANADIR_APUNTES, tApuntes);
			
		});
		mainPanel.add(new JLabel("Nombre apuntes: "));
		nombre = new JTextField(8);
		mainPanel.add(nombre);
		
		mainPanel.add(new JLabel("Tipo del archivo: "));
		tipo_archivo = new JTextField(4);
		mainPanel.add(tipo_archivo);
		
		mainPanel.add(new JLabel("Tema correspondiente: "));
		
		
		DefaultComboBoxModel<String> temas_d = new DefaultComboBoxModel<>();
		
		for(TransferTema tt: tAsignatura.getTemas()) {
			
			temas_d.addElement(tt.getId());

		}
		
		tema = new JComboBox<String>(temas_d);
		mainPanel.add(tema);
		
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

	@Override
	public void update(int event, Object datos) {
		switch (event) {
		case Events.ABRIR_VISTA_ANADIR_APUNTES: 
			tAsignatura=(TransferAsignatura) datos;
		
			initIGUI();

			
		break;
		
		case Events.ANADIR_APUNTES:
			JOptionPane.showMessageDialog(this, "Apuntes Añadidos");
			setVisible(false);
			for (TransferTema tt: tAsignatura.getTemas() ) {
				if (tt.getId().equals(((TransferApuntes) datos).getTemas())){
					tt.getArchivo().add((TransferApuntes) datos);
				}
			}
			ctrl.accion(Events.ABRIR_VISTA_EDITAR_ASIGNATURA,tAsignatura );

		}

	}
}
