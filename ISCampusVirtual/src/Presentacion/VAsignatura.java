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

import com.mysql.cj.conf.ConnectionUrlParser.Pair;

import Negocio.Aula.TransferAsignatura;
import Negocio.Aula.TransferTema;
import Negocio.Foro.TransferForo;
import Negocio.Foro.TransferMensaje;
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
	protected JPanel panelDeBotonesSup;
	protected JPanel panelDeBotonesInf;



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
		ventana = new JFrame(asignatura.getNombre());
		ventana.setSize(300, 200);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        panelDeTemas = new JPanel(new GridLayout(0, 1)); // GridLayout con una columna y filas ilimitadas

		
		
		
		panelDeBotonesSup= new JPanel();
        panelDeBotonesSup.setLayout(new GridLayout(1, 3));
        
        JButton botonCerrar= new JButton("Cerrar");
        botonCerrar.addActionListener(e->{
        	
        	ventana.setVisible(false);
        	ctrl.accion(Events.ABRIR_VISTA_LISTA_ASIGNATURAS, null);
        });
        
		//boton para ver participantes TODO
    	JButton botonParticipantes= new JButton("PARTICIPANTES");
    	botonParticipantes.addActionListener(e->{
    		
    		ctrl.accion(Events.ABRIR_VMOSTRAR_PARTICIPANTES_ASIGNATURA, asignatura);
    	});


    	panelDeBotonesSup.add(botonParticipantes);
        anadirBotonEditar();
        panelDeBotonesSup.add(botonCerrar);

		
		ventana.add(panelDeBotonesSup, BorderLayout.PAGE_START);
		
		listaDeBotones = new ArrayList<JButton>();

		for(TransferTema tt: listaDeTemas) {
			
        	JButton boton= new JButton(tt.getNombre());
        	
        	boton.addActionListener(e->	{
                ventana.setVisible(false);

        		Pair <TransferAsignatura, TransferTema> info= new Pair<>(asignatura, tt) ;
        		ctrl.accion(Events.ABRIR_VISTA_TEMA, info);
        		
        	});
        	
        	listaDeBotones.add(boton);
            panelDeTemas.add(boton);
		}
        scrollPane = new JScrollPane(panelDeTemas);
        ventana.add(scrollPane, BorderLayout.CENTER);
        
        
        panelDeBotonesInf= new JPanel();
        panelDeBotonesInf.setLayout(new GridLayout(1, 2));
        
        JButton botonForo= new JButton("Foro");
        botonForo.addActionListener(e->{
            ventana.setVisible(false);

        	ctrl.accion(Events.ABRIR_VISTA_FORO, asignatura);
        });
        panelDeBotonesInf.add(botonForo);

        JButton botonCalendario= new JButton("Calendario");
        botonCalendario.addActionListener(e->{
        	
        	
        	ctrl.accion(Events.ABRIR_VISTA_CALENDARIO, asignatura);
        });
        
        panelDeBotonesInf.add(botonCalendario);
        ventana.add(panelDeBotonesInf, BorderLayout.PAGE_END);
        
        
        
        
        
        

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
		
    	panelDeBotonesSup.add(new JLabel());

	}

}
