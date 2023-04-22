package Presentacion;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import Negocio.Aula.TransferAsignatura;
import Presentacion.Control.Controller;
import Presentacion.Control.Events;
import Presentacion.Control.IGUI;


public class VMostrarListaAsignaturas extends JFrame implements IGUI{

	private List<JButton> listaDeBotones;
	private List<TransferAsignatura> listaDeAsignaturas;
	private JScrollPane scrollPane;
	private JFrame ventana;
	private JPanel panelDeBotones;
	private Controller ctrl;
	
    JButton cerrarSesion;
	
	
	public VMostrarListaAsignaturas(ArrayList<TransferAsignatura> listaAsig) {
		super("");
		ctrl=Controller.obtenerInstancia();
		listaDeBotones= new ArrayList<>();
		listaDeAsignaturas= listaAsig;
		initIGUI();
	}
	
	private void initIGUI() {
		
        ventana = new JFrame("Asignaturas");
        ventana.setSize(300, 200);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        updateScrollPane();
        
        ventana.add(scrollPane, BorderLayout.CENTER);
        
        cerrarSesion= new JButton("Cerrar Sesion");
        cerrarSesion.addActionListener(e->{
        	
			ventana.setVisible(false);
			ctrl.accion(Events.ABRIR_INICIAR_SESION, null);
        });
        
        
		ventana.add(cerrarSesion, BorderLayout.PAGE_START);
        
        // Mostrar la ventana principal
        ventana.setVisible(true);
	}
	
	@Override
	public void update(int event, Object datos) {

		//switch (event) {
		//case Events.ABRIR_VISTA_LISTA_ASIGNATURAS: {
			listaDeAsignaturas = (List<TransferAsignatura>) datos;

			updateScrollPane();
			scrollPane.revalidate();
			scrollPane.repaint();
		//}

			
		//}
	}
	
	
	
	
	private void updateScrollPane() {
		
		
        panelDeBotones = new JPanel(new GridLayout(0, 1)); // GridLayout con una columna y filas ilimitadas
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
	
	public static void main(String[] args) {
		
		new VMostrarListaAsignaturas(new ArrayList<TransferAsignatura>());
	}
	
}
