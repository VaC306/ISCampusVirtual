package Presentacion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import Negocio.Aula.TransferAsignatura;
import Presentacion.Control.Controller;
import Presentacion.Control.Events;
import Presentacion.Control.IGUI;
import Presentacion.Launcher.main;

public class VAsignaturaEditar extends JFrame  implements IGUI{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TransferAsignatura tAsignatura;
	private JPanel panelDeBotones;
	private JPanel mainPanel;
	Controller ctrl;

	public VAsignaturaEditar() {
		super("Editar Asignatura");
		ctrl=Controller.obtenerInstancia();
		initIGUI();


	}

	void initIGUI() {
		setSize(800, 150);
		setLocationRelativeTo(null);
		this.setResizable(false);
		mainPanel = new JPanel(new BorderLayout());
		panelDeBotones = new JPanel(new FlowLayout());
		setContentPane(mainPanel);
		
		JPanel panelSup= new JPanel();

		JLabel asignatura = new JLabel("EDITAR ", SwingConstants.CENTER); // + tAsignatura.getNombre() TODO
		asignatura.setSize(new Dimension(70, 70));
		
		
		panelSup.add(asignatura, BorderLayout.CENTER);
		mainPanel.add(panelSup, BorderLayout.NORTH);

		JButton aniadirTarea = new JButton("A�adir Tarea");
		aniadirTarea.addActionListener((e)-> {
			setVisible(false);
			ctrl.accion(Events.ABRIR_VISTA_ANADIR_TAREA, tAsignatura);
		}
		);
		
		JButton eliminarTarea = new JButton("Eliminar Tarea");
		eliminarTarea.addActionListener((e)-> {
			setVisible(false);
			ctrl.accion(Events.ABRIR_VISTA_ELIMINAR_TAREA, tAsignatura);
		}
		);
		
		JButton aniadirUsuario = new JButton("A�adir Usuario");
		aniadirUsuario.addActionListener((e)-> {
			setVisible(false);
			ctrl.accion(Events.ABRIR_VISTA_ANADIR_USUARIO, tAsignatura);
		}
		);
		
		JButton eliminarUsuario = new JButton("Eliminar Usuario");
		eliminarUsuario.addActionListener((e)-> {
			setVisible(false);
			ctrl.accion(Events.ABRIR_VISTA_ELIMINAR_USUARIO, tAsignatura);
		}
		);
		
		JButton aniadirApuntes = new JButton("A�adir Apuntes");
		aniadirApuntes.addActionListener((e)-> {
			setVisible(false);
			ctrl.accion(Events.ABRIR_VISTA_ANADIR_APUNTES, tAsignatura);
		}
		);
		JButton eliminarApuntes = new JButton("Eliminar Apuntes");
		eliminarApuntes.addActionListener((e)-> {
			setVisible(false);
			ctrl.accion(Events.ABRIR_VISTA_ELIMINAR_APUNTES, tAsignatura);
		}
		);
		
		JPanel panelInf= new JPanel();
		
		JButton cerrar = new JButton("Cerrar");
		cerrar.addActionListener((e)-> {
			setVisible(false);
			ctrl.accion(Events.ABRIR_VISTA_ASIGNATURA, tAsignatura);
		}
		);
		panelInf.add(cerrar, BorderLayout.CENTER);

		panelDeBotones.add(aniadirTarea);
		panelDeBotones.add(eliminarTarea);
		panelDeBotones.add(aniadirUsuario);
		panelDeBotones.add(eliminarUsuario);
		panelDeBotones.add(aniadirApuntes);
		panelDeBotones.add(eliminarApuntes);
	
		mainPanel.add(panelDeBotones, BorderLayout.CENTER);
		mainPanel.add(panelInf,BorderLayout.PAGE_END );
		setVisible(true);
	}

	@Override
	public void update(int event, Object datos) {
		tAsignatura=(TransferAsignatura) datos;
	}
}
