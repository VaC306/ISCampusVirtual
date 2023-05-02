package Presentacion;

import javax.swing.*;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;

import Negocio.Aula.TransferAsignatura;
import Negocio.Foro.TransferForo;
import Negocio.Foro.TransferMensaje;
import Negocio.Usuario.TransferUsuario;
import Presentacion.Control.Controller;
import Presentacion.Control.Events;
import Presentacion.Control.IGUI;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Date;


public class VForo extends JFrame implements IGUI{

    private JTextArea areaTexto;
    private JTextField campoTexto;
    private TransferAsignatura tAsignatura;
    private TransferUsuario tUsuario; 
    private List<TransferMensaje> mensajes;
	private Controller ctrl;


    public VForo() {
		super("Foro");
		ctrl=Controller.obtenerInstancia();

    }
    
    void initIGUI() {
    	
    	// Configurar la ventana principal
        setTitle("Foro de " + tAsignatura.getNombre());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setResizable(false);
        setLocationRelativeTo(null);

        // Crear el panel del foro
        JPanel panelForo = new JPanel(new BorderLayout());
        areaTexto = new JTextArea();
        
        
        updateForo();
        
        
        areaTexto.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaTexto);
        campoTexto = new JTextField(30);
        JButton botonEnviar = new JButton("Enviar");

        // Agregar los componentes al panel del foro
        panelForo.add(scrollPane, BorderLayout.CENTER);
        JPanel panelEnviar = new JPanel();
        panelEnviar.add(campoTexto);
        panelEnviar.add(botonEnviar);
        panelForo.add(panelEnviar, BorderLayout.SOUTH);

        // Crear un listener para el botón de enviar
        botonEnviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String mensaje = campoTexto.getText();
                if (!mensaje.equals("")) {
                    areaTexto.append("Usuario: " + mensaje + "\n");
                    campoTexto.setText("");
                    
                    TransferMensaje tm=new TransferMensaje(new Date(), mensaje, tUsuario.getNombre_Apellidos());
        			Pair <TransferMensaje, TransferForo> info = new Pair<>(tm, tAsignatura.getAvisos());

                    ctrl.accion(Events.ANADIR_MENSAJE, info);
                }
            }
        });

        // Agregar el panel del foro a la ventana principal
        getContentPane().add(panelForo);
		JButton cancel= new JButton("Cancelar");
		cancel.addActionListener(e->{
			
			setVisible(false);
			ctrl.accion(Events.ABRIR_VISTA_ASIGNATURA,tAsignatura );
		});
		getContentPane().add(cancel);
        // Mostrar la ventana principal
        setVisible(true);
    }

	private void updateForo() {
		for(TransferMensaje tm: mensajes) {
            areaTexto.append("Usuario: " + tm.getUsuario() +" at "+ tm.getFecha().toString() + "\n");
            areaTexto.append(tm.getCuerpo() + "\n"+ "\n");
		}
	}

	@Override
	public void update(int event, Object datos) {
		switch(event) {
		case  Events.ANADIR_MENSAJE: 
			JOptionPane.showMessageDialog(this, "Mensaje enviado");

		
		default:
			
			Pair <TransferAsignatura, TransferUsuario> info =(Pair<TransferAsignatura, TransferUsuario>) datos ;
			tUsuario=info.right;
			tAsignatura=info.left;
			mensajes=tAsignatura.getAvisos().getMensaje();
			initIGUI();
		}
	}
}
