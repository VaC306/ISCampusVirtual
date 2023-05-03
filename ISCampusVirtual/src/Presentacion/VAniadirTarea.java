package Presentacion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.sql.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.Border;

import Integracion.DAOApuntes;
import Integracion.DAOApuntesImpl;
import Integracion.DAOArchivo;
import Integracion.DAOArchivoImpl;
import Integracion.DAOTarea;
import Integracion.DAOTareaImpl;
import Negocio.Archivos.Tipos_archivo;
import Negocio.Archivos.TransferTarea;
import Negocio.Aula.TransferAsignatura;
import Negocio.Aula.TransferTema;
import Presentacion.Control.Controller;
import Presentacion.Control.Events;
import Presentacion.Control.IGUI;

public class VAniadirTarea extends JFrame  implements IGUI{
	
	private TransferAsignatura tAsignatura;
	private JTextField nombre;
	private JTextField tipo_archivo;
	private JButton load;
	private JButton ok;
	private JFileChooser fc;
	private JLabel file_name;
	private JSpinner spinnerFecha;
	private JComboBox tema;
	private Controller ctrl;

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


			TransferTarea tt2 = new TransferTarea();
				
			DAOTarea daoT = new DAOTareaImpl();
			DAOApuntes daoA = new DAOApuntesImpl();
				tt2.setId("TAR00" + daoT.num());
				tt2.setIdTarea("T00"+ daoT.num());
				//tt2.setFecha_de_entrega((Date) spinnerFecha.getValue());
				tt2.setTemas((String) tema.getSelectedItem());
				tt2.setNombre(nombre.getText());
				tt2.setTipo_archivo(Tipos_archivo.valueOf(tipo_archivo.getText()));
				
				ctrl.accion(Events.TAREA_ANADIR, tt2);
		

		});
		
		mainPanel.add(new JLabel("Nombre tarea: "));
		nombre = new JTextField(8);
		mainPanel.add(nombre);
		mainPanel.add(new JLabel("Tipo del Archivo: "));
		tipo_archivo = new JTextField(4);
		mainPanel.add(tipo_archivo);
		mainPanel.add(new JLabel("Fecha entrega: "));
		
		Date fechaActual = new Date(System.currentTimeMillis());
        SpinnerDateModel spinnerModel = new SpinnerDateModel(fechaActual, null, null, java.util.Calendar.DAY_OF_MONTH);
         spinnerFecha = new JSpinner(spinnerModel);
        spinnerFecha.setEditor(new JSpinner.DateEditor(spinnerFecha, "dd/MM/yyyy"));
		
		
		mainPanel.add(spinnerFecha);
		
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
			nombre.setText("");	
		break;
		
		default:
			tAsignatura=(TransferAsignatura) datos;
			initIGUI();

		}

		
		
	}
}
