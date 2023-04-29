package Presentacion;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;

import Negocio.Archivos.TransferTarea;
import Negocio.Aula.TransferAsignatura;
import Negocio.Usuario.TransferAlumno;
import Negocio.Usuario.TransferUsuario;
import Presentacion.Control.Controller;
import Presentacion.Control.Events;
import Presentacion.Control.IGUI;

public class VUsuarioCrear extends JFrame implements IGUI{
	
	protected Controller ctrl;
	TransferUsuario usuario;
	private JPanel fieldsPanel;
	private JPanel buttonsPanel;
	private JLabel nombre;
	private JTextField nifE;
	private JTextField nombreE;
	private JTextField correoE;
	private JLabel nif;
	private JLabel correo;
	private JLabel contrasena;
	private JTextField contrasenaE;
	private TransferAsignatura tAsignatura;

	
	private JButton ok;
	private JButton cancel;

	
	
	public VUsuarioCrear() {
		super();
		usuario=new TransferAlumno();
		ctrl = Controller.obtenerInstancia();	
		initIGUI();	

	}

	private void initIGUI() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);
		
		fieldsPanel = new JPanel();
		fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));
		mainPanel.add(fieldsPanel);
		
		JPanel nifpanel = new JPanel(new FlowLayout());
		
		nifpanel.add(new JLabel("DNI: "));
		nifE = new JTextField();
		nifE.setPreferredSize(new Dimension(100, 20));
		nifE.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				usuario.setNIF(nifE.getText());
			}
		});
		nifpanel.add(nifE);
		fieldsPanel.add(nifpanel);
		
		
		JPanel nombrepanel = new JPanel(new FlowLayout());
		
		nombrepanel.add(new JLabel("Nombre: "));
		nombreE = new JTextField();
		nombreE.setPreferredSize(new Dimension(100, 20));
		nombreE.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				usuario.setNombre_Apellidos(nombreE.getText());
				
			}
		});
		
		nombrepanel.add(nombreE);
		fieldsPanel.add(nombrepanel);
		
		
		JPanel correopanel = new JPanel(new FlowLayout());
		
		correopanel.add(new JLabel("Correo: "));
		correoE = new JTextField();
		correoE.setPreferredSize(new Dimension(300, 20));
		correoE.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//poner aqui los transfer
				usuario.setId("a"+correoE.getText());
				usuario.setCorreo_electronico(correoE.getText());
			}
		});
		correopanel.add(correoE);
		fieldsPanel.add(correopanel);
		
		JPanel contarenaPanel = new JPanel(new FlowLayout());
		
		contarenaPanel.add(new JLabel("contrasena: "));
		contrasenaE = new JTextField();
		contrasenaE.setPreferredSize(new Dimension(300, 20));
		contrasenaE.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//poner aqui los transfer
				usuario.setPassword(contrasenaE.getText());
			}
		});
		contarenaPanel.add(contrasenaE);
		fieldsPanel.add(contarenaPanel);
		
		buttonsPanel = new JPanel((LayoutManager) new FlowLayout(FlowLayout.CENTER));
		mainPanel.add(buttonsPanel, BorderLayout.PAGE_END);
		ok = new JButton("OK");
		ok.addActionListener(e->{
			
			
			if(	usuario.getNombre_Apellidos()==null ||usuario.getCorreo_electronico()==null || usuario.getNIF()==null || usuario.getPassword()==null) {
				
				JOptionPane.showMessageDialog(this, "Error: pulse intro al introducir cada texto");

			}
			else {
				Pair <TransferAsignatura, TransferUsuario> info = new Pair<>(tAsignatura,usuario);

				ctrl.accion(Events.CREAR_USUARIO, info);
			}
			
		});
		buttonsPanel.add(ok);
		cancel = new JButton("Cancel");
		cancel.addActionListener((e) -> setVisible(false));
		buttonsPanel.add(cancel);
		
		setLocationRelativeTo(null);
		pack();
		setVisible(true);
	}


	@Override
	public void update(int event, Object datos) {

		switch (event) {
		case Events.ABRIR_VISTA_CREAR_USUARIO:
			tAsignatura = (TransferAsignatura) datos;

			break;
		case Events.CREAR_USUARIO_ERROR:

			JOptionPane.showMessageDialog(this, "Error al crear");
			nifE.setText("");
			correoE.setText("");
			nombreE.setText("");
			contrasenaE.setText("");
			break;

		case Events.CREAR_USUARIO_EXITO:

			setVisible(false);
			JOptionPane.showMessageDialog(this, "Usuario creado");

		}
	
	}

}