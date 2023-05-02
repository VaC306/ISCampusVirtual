package Presentacion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Integracion.DAOTema;
import Integracion.DAOTemaImpl;
import Presentacion.Control.Events;

public class VTema_Profesor extends VTema{
	JTextField temaTitulo;
	
	public VTema_Profesor() {
		
		super();
	}
	
	
	private void initPanelSup() {
		JPanel panelSup= new JPanel();
		temaTitulo = new JTextField(tTema.getAsignaturas().getNombre() +" : "+ tTema.getNombre()); 
		temaTitulo.setSize(new Dimension(70, 70));
		
		temaTitulo.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				tTema.setNombre(temaTitulo.getText());

				ctrl.accion(Events.EDITAR_NOMBRE_TEMA,tTema );
			}
		});
		
		panelSup.add(temaTitulo, BorderLayout.CENTER);
		mainPanel.add(panelSup, BorderLayout.PAGE_START);			
	}
}
