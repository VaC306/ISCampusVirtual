package Presentacion;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Negocio.Aula.TransferAsignatura;
import Negocio.Aula.TransferTema;
import Presentacion.Control.Controller;
import Presentacion.Control.Events;
import Presentacion.Control.IGUI;

public class VAsignatura implements IGUI {
	
	protected Controller ctrl;
	TransferAsignatura asignatura;
	protected List<JButton> listaDeBotones;
	protected List<TransferTema> listaDeTemas;
	protected JScrollPane scrollPane;
	protected JFrame ventana;
	protected JPanel panelDeTemas;
	protected JPanel panelDeBotones;


	public VAsignatura() {

		super();
		ctrl=Controller.obtenerInstancia();

	
	}
    public static void main(String[] args) {
    	
    	TransferAsignatura ta=new TransferAsignatura();
    	ta.setTemas(new ArrayList<TransferTema> ());
    	
    	VAsignaturaProfesor va= new VAsignaturaProfesor();
    	va.update(0, ta);
  
    }

	
	protected void initIGUI() {
		
		//mostrar temas (array Jbuttons)
		ventana = new JFrame("Asignaturas");
		ventana.setSize(300, 200);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        panelDeTemas = new JPanel(new GridLayout(0, 1)); // GridLayout con una columna y filas ilimitadas

		
		
		
		panelDeBotones= new JPanel();
        panelDeBotones.setLayout(new GridLayout(1, 3));
        
        JButton botonCerrar= new JButton("Cerrar");
        botonCerrar.addActionListener(e->{
        	
        	ventana.setVisible(false);
        	ctrl.accion(Events.ABRIR_VISTA_LISTA_ASIGNATURAS, null);
        });
        
		//boton para ver participantes TODO
    	JButton botonParticipantes= new JButton("VER PARTICIPANTES");
    	botonParticipantes.addActionListener(e->{
    		
    		ctrl.accion(Events.ABRIR_VMOSTRAR_PARTICIPANTES_ASIGNATURA, asignatura);
    	});


    	panelDeBotones.add(botonParticipantes);
        anadirBotonEditar();
        panelDeBotones.add(botonCerrar);

		
		ventana.add(panelDeBotones, BorderLayout.PAGE_START);
		
		

		for(TransferTema tt: listaDeTemas) {
			
        	JButton boton= new JButton(tt.getNombre());
        	
			//ctrl.accion(Events.ABRIR_VISTA_TEMA, tA);
        	
        	listaDeBotones.add(boton);
            panelDeTemas.add(boton);
		}
        scrollPane = new JScrollPane(panelDeTemas);
        ventana.add(scrollPane, BorderLayout.CENTER);

        ventana.setVisible(true);
	}

	@Override
	public void update(int event, Object datos) {
		
		asignatura=(TransferAsignatura) datos;
		listaDeTemas=((TransferAsignatura) datos).getTemas();
		initIGUI();
	}
	
	//al ser vista desde un usuario alumno no se anade el boton de editar
	protected void anadirBotonEditar() {
		
    	panelDeBotones.add(new JLabel());

	}

}
