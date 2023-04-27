package Presentacion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;

import Negocio.Aula.TransferAsignatura;
import Negocio.Aula.TransferTema;
import Presentacion.Control.Controller;
import Presentacion.Control.Events;
import Presentacion.Control.IGUI;

public class VTema extends JFrame  implements IGUI{
	
	private TransferAsignatura tAsignatura;
	private TransferTema tTema;
	private Controller ctrl;
	JPanel mainPanel;
	
	public VTema() {
		super("Vista Tema");
		ctrl=Controller.obtenerInstancia();
		
		
	}
	
	private void initIGUI() {
		
		setSize(800, 150);
		setLocationRelativeTo(null);
		this.setResizable(false);
		mainPanel = new JPanel(new BorderLayout());
		setContentPane(mainPanel);
		
		JPanel panelSup= new JPanel();

		JLabel temaTitulo = new JLabel(tAsignatura.getNombre() +" : "+ tTema.getNombre(), SwingConstants.CENTER); 
		temaTitulo.setSize(new Dimension(70, 70));
		
		
		panelSup.add(temaTitulo, BorderLayout.CENTER);
		mainPanel.add(panelSup, BorderLayout.NORTH);
		
		
		
		
		
		
		
		JPanel panelInf= new JPanel();
		JButton cerrar = new JButton("Cerrar");
		cerrar.addActionListener((e)-> {
			setVisible(false);
			ctrl.accion(Events.ABRIR_VISTA_ASIGNATURA, tAsignatura);
		}
		);
		panelInf.add(cerrar, BorderLayout.CENTER);
		mainPanel.add(panelInf, BorderLayout.PAGE_END);
		setVisible(true);
	}

	@Override
	public void update(int event, Object datos) {
		
		switch(event) {
		
		case Events.ABRIR_VISTA_TEMA:
			
			Pair <TransferTema, TransferAsignatura>info=(Pair<TransferTema, TransferAsignatura>) datos;
			
			tAsignatura=info.right;
			tTema=info.left;
			break;
		}
	}

}
