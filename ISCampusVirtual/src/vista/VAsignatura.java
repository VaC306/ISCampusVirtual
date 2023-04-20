package vista;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import control.Controller;
import control.IGUI;
import model.aula.TransferAsignatura;
import model.aula.TransferTema;

public class VAsignatura implements IGUI {
	
	protected Controller ctrl;
	TransferAsignatura asignatura;
	protected List<JButton> listaDeBotones;
	protected List<TransferTema> listaDeTemas;
	protected JScrollPane scrollPane;
	protected JFrame ventana;
	protected JPanel panelDeBotones;

	public VAsignatura(TransferAsignatura data) {

		super();
		ctrl=Controller.obtenerInstancia();
		asignatura=data;
		listaDeTemas=data.getTemas();
		initIGUI();
	
	}
	
	protected void initIGUI() {
		
		//mostrar temas (array Jbuttons)
		ventana = new JFrame("Asignaturas");
		ventana.setSize(300, 200);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        panelDeBotones = new JPanel(new GridLayout(0, 1)); // GridLayout con una columna y filas ilimitadas

		
		for(TransferTema tt: listaDeTemas) {
			
        	JButton boton= new JButton(tt.getNombre());
        	
			//ctrl.accion(Events.ABRIR_VISTA_TEMA, tA);
        	
        	listaDeBotones.add(boton);
            panelDeBotones.add(boton);
		}
        scrollPane = new JScrollPane(panelDeBotones);
        ventana.add(scrollPane);
        ventana.setVisible(true);

		
		//boton para ver participantes
		anadirBotonEditar();
	}

	@Override
	public void update(int event, Object datos) {}
	
	//al ser vista desde un usuario alumno no se anade el boton de editar
	protected void anadirBotonEditar() {}

}
