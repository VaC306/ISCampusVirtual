package Presentacion;

import java.awt.BorderLayout;

import javax.swing.*;

import Presentacion.Control.Controller;
import Presentacion.Control.Events;
import Presentacion.Control.IGUI;

public class ViniciarSesion extends JFrame implements IGUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField username;
	private JPasswordField password;
	private JLabel username_l;
	private JLabel password_l;
	private JButton ok;
	private JButton cancel;
	private JPanel panelMain;
	private Controller ctrl;

	public ViniciarSesion() {
		super("Iniciar Sesion");
		ctrl = Controller.obtenerInstancia();
		initGUI();
	}

	private void initGUI() {
		this.setSize(450, 110);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		 panelMain = new JPanel();
		panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.Y_AXIS));
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();

		setContentPane(panelMain);
		setLocationRelativeTo(getParent());
		setResizable(false);

		username_l = new JLabel("Mail");
		password_l = new JLabel("Password");

		try {
			username = new JTextField(8);
			username.setLocation(0, 0);
			username.setSize(100, 30);
			panel3.add(username_l);
			panel3.add(username);
			password = new JPasswordField(8);
			password.setEchoChar('*');
			password.setLocation(0, 40);
			password.setSize(100, 30);
			panel3.add(password_l);
			panel3.add(password);
			
			//ok button
			ok = new JButton("OK");
			
			ok.addActionListener((e) -> {
				String[] arrayS = { username.getText(), new String(password.getPassword()) };
				ctrl.accion(Events.INICIAR_SESION, arrayS);
			
			});
			panel2.add(ok);

			// cancel button
			cancel = new JButton("Cancel");
			panel2.add(cancel);
			cancel.addActionListener((e) -> {
				setVisible(false);
			});

			panelMain.add(panel3);
			panelMain.add(panel2);
			setVisible(true);

		} catch (Exception e) {

		}
	}

	@Override
	public void update(int event, Object datos) {
		switch (event) {
		case Events.INICIAR_SESION_CORRECTO:
			JOptionPane.showMessageDialog(this, "¡Inicio de sesion exitoso!");
			setVisible(false);
			ctrl.accion(Events.ABRIR_VISTA_LISTA_ASIGNATURAS, datos);
			break;

		case Events.INICIAR_SESION_FALLIDO:
			
			username.setText("");
			password.setText("");

			JOptionPane.showMessageDialog(this, "Inicio de sesion fallido");
			break;
		}

	}
}
