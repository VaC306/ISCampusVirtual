package vista;

import control.Controller;
import control.Events;
import control.IGUI;

import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import model.aula.*;


public class VMostrarAsignaturas extends JFrame implements IGUI{

	private List<JButton> listaDeBotones;
	private List<TransferAsignatura> listaDeAsignaturas;
	private JScrollPane scrollPane;

	private Controller ctrl;
	
	public VMostrarAsignaturas() {
		super("");
		ctrl=Controller.obtenerInstancia();
		listaDeBotones= new ArrayList<>();
		listaDeAsignaturas= new ArrayList<>();
		initIGUI();
	}
	
	private void initIGUI() {
		
        JFrame ventana = new JFrame("Asignaturas");
        ventana.setSize(300, 200);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        updateScrollPane();
        
        ventana.add(scrollPane);
        
        // Mostrar la ventana principal
        ventana.setVisible(true);
	}
	
	@Override
	public void update(int event, Object datos) {
		listaDeAsignaturas= (List<TransferAsignatura>) datos;
	
        updateScrollPane();
        scrollPane.revalidate();
        scrollPane.repaint();
	}
	
	
	
	
	private void updateScrollPane() {
		
        JPanel panelDeBotones = new JPanel(new GridLayout(0, 1)); // GridLayout con una columna y filas ilimitadas
        listaDeBotones.clear();
        
        for (TransferAsignatura tA: listaDeAsignaturas) {
				
        	JButton boton= new JButton(tA.getNombre());
			
        	boton.addActionListener((e)->{
				ctrl.accion(Events.ABRIR_VISTA_ASIGNATURA, tA);
			});            
			listaDeBotones.add(boton);
            panelDeBotones.add(boton);
        }
        scrollPane = new JScrollPane(panelDeBotones);

               
	}
	
	
}
