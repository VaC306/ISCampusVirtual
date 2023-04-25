package Presentacion;

import javax.swing.*;

import Presentacion.Control.IGUI;

import java.awt.*;
import java.awt.event.*;

public class VForo extends JFrame implements IGUI{

    private JTextArea areaTexto;
    private JTextField campoTexto;


    public VForo() {
        
    }
    
    void initIGUI() {
    	
    	// Configurar la ventana principal
        setTitle("Foro de discusión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setResizable(false);
        setLocationRelativeTo(null);

        // Crear el panel del foro
        JPanel panelForo = new JPanel(new BorderLayout());
        areaTexto = new JTextArea();
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
                }
            }
        });

        // Agregar el panel del foro a la ventana principal
        getContentPane().add(panelForo);

        // Mostrar la ventana principal
        setVisible(true);
    }

	@Override
	public void update(int event, Object datos) {
		// TODO Auto-generated method stub
		
	}
}
