package Presentacion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;

import Negocio.Archivos.TransferArchivo;
import Negocio.Aula.TransferAsignatura;
import Negocio.Aula.TransferTema;
import Presentacion.Control.Controller;
import Presentacion.Control.Events;
import Presentacion.Control.IGUI;

public class VTema extends JFrame  implements IGUI{
	

	protected TransferTema tTema;
	protected Controller ctrl;
	protected JPanel mainPanel;
	protected TransferAsignatura tAsignatura;
	
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
		
		initPanelSup();
		
		
		
		//tabla archivos
		

		
		DefaultTableModel modeloArchivos = new DefaultTableModel();
		modeloArchivos.addColumn("Archivo");
		modeloArchivos.addColumn("Usuario");
		
		
		for(TransferArchivo tA:tTema.getArchivo()) {
		
			modeloArchivos.addRow(new String[] { tA.getNombre(), tA.getUsuario()});	
		}

		JTable tabla = new JTable(modeloArchivos);
		JScrollPane scrollPane = new JScrollPane(tabla);
		
		add(scrollPane);
		
		
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

	public void initPanelSup() {
		JPanel panelSup= new JPanel();
		JLabel temaTitulo = new JLabel(tTema.getNombre(), SwingConstants.CENTER); 
		temaTitulo.setSize(new Dimension(70, 70));
		panelSup.add(temaTitulo, BorderLayout.CENTER);
		mainPanel.add(panelSup, BorderLayout.PAGE_START);			
	}

	@Override
	public void update(int event, Object datos) {
		
		switch(event) {
		
		case Events.ABRIR_VISTA_TEMA:
    		Pair <TransferAsignatura, TransferTema> info= (Pair<TransferAsignatura, TransferTema>) datos ;

			tTema=info.right;
			tAsignatura=info.left;
			initIGUI();
			break;
		}
	}

}
