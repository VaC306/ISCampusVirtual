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

import Negocio.Archivos.TransferApuntes;
import Negocio.Archivos.TransferTarea;
import Negocio.Aula.TransferAsignatura;
import Presentacion.Control.Controller;
import Presentacion.Control.Events;
import Presentacion.Control.IGUI;

public class VEliminarApuntes extends JFrame  implements IGUI{
	private JComboBox<String> comboApuntes;
	private List <TransferApuntes> listaApuntes;
	private Controller ctrl;
	private TransferAsignatura tAsignatura;

	public VEliminarApuntes() {
		super("Eliminar Apuntes");
		ctrl=Controller.obtenerInstancia();
		initIGUI();

	}

	void initIGUI() {
		JPanel mainPanel = new JPanel(new FlowLayout());
		setContentPane(mainPanel);
		setSize(600, 100);
		setLocationRelativeTo(null);
		setResizable(false);

		DefaultComboBoxModel<String> apuntes = new DefaultComboBoxModel<>();
		
		for(TransferApuntes ta: listaApuntes) {
			
			apuntes.addElement(ta.getId());

		}

		comboApuntes = new JComboBox<>(apuntes);
		

		
		mainPanel.add(new JLabel("Selecciona los apuntes a eliminar: "));
		mainPanel.add(comboApuntes);
		JButton ok = new JButton("OK");
		ok.addActionListener((e) ->{
			
			ctrl.accion(Events.ELIMINAR_APUNTES,comboApuntes.getSelectedItem() );

		});
		mainPanel.add(ok);
		JButton cancel= new JButton("Cancelar");
		cancel.addActionListener(e->{
			
			setVisible(false);
			ctrl.accion(Events.ABRIR_VISTA_EDITAR_ASIGNATURA,tAsignatura );
		});
		mainPanel.add(cancel);
		setVisible(true);
	}

	void valid() {
		comboApuntes.getSelectedItem();
		setVisible(false);
	}

	@Override
	public void update(int event, Object datos) {
		 switch(event) {
		 
		 case Events.APUNTES_ELIMINADA_EXITO:
				setVisible(false);
				ctrl.accion(Events.ABRIR_VISTA_EDITAR_ASIGNATURA,tAsignatura );
				break;
		 case Events.APUNTES_ELIMINADA_ERROR:
				JOptionPane.showMessageDialog(this, "APUNTES no eliminados");

			 break;
		 
		 default:
			 Pair <TransferAsignatura, List<TransferApuntes>> info=(Pair<TransferAsignatura, List<TransferApuntes>>) datos;
			 tAsignatura=info.left;
			 listaApuntes=info.right;
		 }	
	}
}