package Presentacion;

import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Usuario.TransferUsuario;
import Presentacion.Control.Controller;
import Presentacion.Control.Events;
import Presentacion.Control.IGUI;

public class VPerfilEditar extends JFrame implements IGUI{
	
	protected Controller ctrl;
	private JFrame ventana;
	TransferUsuario usuario;
	private JPanel fieldsPanel;
	private JPanel buttonsPanel;
	private JLabel nombre;
	private JTextField nifE;
	private JTextField nombreE;
	private JTextField correoE;
	private JLabel nif;
	private JLabel correo;
	private JButton ok;
	private JButton cancel;

	
	
	public VPerfilEditar() {
		super();
		ctrl = Controller.obtenerInstancia();	
	}

	private void initIGUI() {
		ventana = new JFrame();
		
		fieldsPanel = new JPanel();
		fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));
		
		nif = new JLabel("DNI: ");
		fieldsPanel.add(nif);
		nifE = new JTextField();
		nifE.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				usuario.setNIF(nifE.getText());
			}
		});
		fieldsPanel.add(nifE);
		
		nombre = new JLabel("Nombre: ");
		fieldsPanel.add(nombre);
		nombreE = new JTextField();
		nombreE.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				usuario.setNombre_Apellidos(nombreE.getText());
				
			}
		});
		
		fieldsPanel.add(nombreE);
		
		
		
		correo = new JLabel("Correo: ");
		fieldsPanel.add(correo);
		correoE = new JTextField();
		correoE.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//poner aqui los transfer
				usuario.setCorreo_electronico(correoE.getText());
			}
		});
		fieldsPanel.add(correoE);
		//ver si añadir mas campos
		
		
		buttonsPanel = new JPanel((LayoutManager) new FlowLayout(FlowLayout.CENTER));
		add(buttonsPanel);
		ok = new JButton("OK");
		ok.addActionListener(e->{
			
			ctrl.accion(Events.EDITAR_USUARIO, usuario);
			
		});
		buttonsPanel.add(ok);
		cancel = new JButton("Cancel");
		cancel.addChangeListener((e) -> setVisible(false));
		buttonsPanel.add(cancel);
		
	}


	@Override
	public void update(int event, Object datos) {
		
		switch(event) {
		
		case Events.ABRIR_VISTA_EDITAR_USUARIO:
			usuario = (TransferUsuario) datos;
			initIGUI();	
			break;
		
		case Events.EDITAR_USUARIO_ERROR:
			
			//TODO
		
		case Events.EDITAR_USUARIO_EXITO:
			
			setVisible(false);
			//TODO
			
		}
	
	}

}