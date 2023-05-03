package Presentacion;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;

import Integracion.DAOAlumno;
import Integracion.DAOAlumnoImpl;
import Negocio.Archivos.TransferTarea;
import Negocio.Aula.TransferAsignatura;
import Negocio.Aula.TransferTema;
import Negocio.Usuario.TransferAlumno;
import Presentacion.Control.Controller;
import Presentacion.Control.Events;
import Presentacion.Control.IGUI;

public class VAniadirUsuario extends JFrame  implements IGUI{

	private TransferAsignatura tAsignatura;
	private JTextField id;
	private JButton ok;
	private String id_s;
	Controller ctrl;

	public VAniadirUsuario() {
		super("A�adir Usuario");
		ctrl=Controller.obtenerInstancia();
		
		initIGUI();

	}

	void initIGUI() {

		JPanel mainPanel = new JPanel(new FlowLayout());

		setSize(600, 100);
		setContentPane(mainPanel);
		setLocationRelativeTo(null);

		id = new JTextField(30);
		ok = new JButton("OK");
		ok.addActionListener((e) -> {
			Pair<String, TransferAsignatura> info= new Pair<>(id.getText(),tAsignatura );
			
			ctrl.accion(Events.ANADIR_USUARIO, info);
		});

		mainPanel.add(new JLabel("Correo del usuario: "));
		mainPanel.add(id);
		mainPanel.add(ok);
		JButton cancel= new JButton("Cancelar");
		cancel.addActionListener(e->{
			
			setVisible(false);
			ctrl.accion(Events.ABRIR_VISTA_EDITAR_ASIGNATURA,tAsignatura );
		});
		mainPanel.add(cancel);
		setVisible(true);
		setVisible(true);
	}



	@Override
	public void update(int event, Object datos) {
		
		switch(event) {
		
		case Events.ANADIR_USUARIO_ERROR:
			setVisible(false);
			JOptionPane.showMessageDialog(this, "Usuario no existente: crear usuario");

			ctrl.accion(Events.ABRIR_VISTA_CREAR_USUARIO, tAsignatura);

		break;
		
		case Events.ANADIR_USUARIO_EXITO:
			setVisible(false);
<<<<<<< HEAD
			DAOAlumno daoA = new DAOAlumnoImpl();
			((TransferAlumno)datos).setId("AL00"+ (daoA.count()+1));
			daoA.createStudent((TransferAlumno)datos, tAsignatura.getID());
=======

			ctrl.accion(Events.ABRIR_VISTA_EDITAR_ASIGNATURA, tAsignatura);
>>>>>>> branch 'master' of https://github.com/VaC306/ISCampusVirtual.git
			
			tAsignatura.getAlumno().add((TransferAlumno)datos);

			
			ctrl.accion(Events.ABRIR_VISTA_EDITAR_ASIGNATURA, tAsignatura);
			break;
		default:
			tAsignatura=(TransferAsignatura) datos;
			
		}		
	}
}
