package Presentacion;

import java.awt.FlowLayout;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;

import Negocio.Archivos.TransferTarea;
import Negocio.Aula.TransferAsignatura;
import Presentacion.Control.Controller;
import Presentacion.Control.Events;
import Presentacion.Control.IGUI;

public class VEliminarTareas extends JFrame  implements IGUI{
	private JComboBox<String> tareas;
	private JButton cancel;
	private List <TransferTarea> tareasList;
	private TransferAsignatura tAsignatura;
	private Controller ctrl;

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
		
		for(TransferTarea idTareas: tareasList) {
			
			tareas_d.addElement(idTareas.getId());

		}

		tareas = new JComboBox<String>(tareas_d);
		mainPanel.add(new JLabel("Selecciona la tarea a eliminar: "));
		mainPanel.add(tareas);
		JButton ok = new JButton("OK");
		ok.addActionListener((e) ->{
			
			ctrl.accion(Events.TAREA_ELIMINADA, tareas_d.getSelectedItem());
		});
		mainPanel.add(ok);
		
		cancel= new JButton("Cancelar");
		cancel.addActionListener(e->{
			
			setVisible(false);
			ctrl.accion(Events.ABRIR_VISTA_EDITAR_ASIGNATURA,tAsignatura );
		});
		mainPanel.add(cancel);
		setVisible(true);
	}

	void valid() {
		tareas.getSelectedItem();
		setVisible(false);
	}

	@Override
	public void update(int event, Object datos) {
		 switch(event) {
		 
		 case Events.TAREA_ELIMINADA_EXITO:
				setVisible(false);
				ctrl.accion(Events.ABRIR_VISTA_EDITAR_ASIGNATURA,tAsignatura );
				break;
		 case Events.TAREA_ELIMINADA_ERROR:
				JOptionPane.showMessageDialog(this, "Tarea no eliminada");

			 break;
		 
		 default:
			 Pair <TransferAsignatura, List<TransferTarea>> info=(Pair<TransferAsignatura, List<TransferTarea>>) datos;
			 tAsignatura=info.left;
			 tareasList=info.right;
		 }
		
	}
}
