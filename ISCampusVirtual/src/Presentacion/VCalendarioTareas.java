package Presentacion;

import java.util.List;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import Negocio.Archivos.TransferTarea;
import Negocio.Aula.TransferAsignatura;
import Presentacion.Control.Controller;
import Presentacion.Control.IGUI;

public class VCalendarioTareas extends JFrame implements IGUI {
	private Controller ctrl;
	private JPanel panelCalendario;
	private List<TransferTarea> listaTareas;
	//a
	public static void main(String[] args) {
		
		new VCalendarioTareas(new ArrayList<TransferTarea>());
	}

	private static final String[] meses = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto",
			"Septiembre", "Octubre", "Noviembre", "Diciembre" };

	private static final JLabel[] diasSemana = new JLabel[] { new JLabel("Lunes", JLabel.CENTER),
			new JLabel("Martes", JLabel.CENTER), new JLabel("Miércoles", JLabel.CENTER),
			new JLabel("Jueves", JLabel.CENTER), new JLabel("Viernes", JLabel.CENTER),
			new JLabel("Sábado", JLabel.CENTER), new JLabel("Domingo", JLabel.CENTER) };

	int mes;

	public VCalendarioTareas(List<TransferTarea> lista) {
		super("");
		Calendar calendar = Calendar.getInstance();
		mes = calendar.get(Calendar.MONTH);
		ctrl = Controller.obtenerInstancia();
		this.listaTareas = lista;
		initIGUI();
	}

	private void initIGUI() {
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panelCalendario = crearPanelCalendario();

		add(panelCalendario, BorderLayout.CENTER);

		// TODO añadir combobox al layout
		HashMap<String, Integer> mapaMeses = new HashMap<>();
		JComboBox<String> comboBox = new JComboBox<>();

		int i = 0;
		for (String mes : meses) {
			comboBox.addItem(mes);
			mapaMeses.put(mes, i);
			
			i++;
		}
		comboBox.setSelectedIndex(mes);
		
		comboBox.addActionListener(e -> {

			String mesSeleccionadoString = (String) comboBox.getSelectedItem();
			mes = mapaMeses.get(mesSeleccionadoString);
			
			
			this.remove(panelCalendario);
			panelCalendario = crearPanelCalendario();
			add(panelCalendario, BorderLayout.CENTER);

			initIGUI();

		});
		
		add(comboBox, BorderLayout.PAGE_START);

		// TODO añadir boton
		JButton botonCerrar = new JButton("Cerrar");
		botonCerrar.addActionListener(e -> {

			this.setVisible(false);
		});

		add(botonCerrar,BorderLayout.PAGE_END );
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

	}

	@Override
	public void update(int event, Object datos) {

	}

	private JPanel crearPanelCalendario() {
		JPanel panelCalendario = new JPanel(new GridLayout(0, 7));
		panelCalendario.setBorder(BorderFactory.createLineBorder(Color.BLACK));




		for (JLabel dia : diasSemana) {
			dia.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			panelCalendario.add(dia);
		}

		Calendar calendario = Calendar.getInstance();
		calendario.set(Calendar.MONTH, mes);
		calendario.set(Calendar.DAY_OF_MONTH, 1);

		int diaSemana = calendario.get(Calendar.DAY_OF_WEEK);
		int diasEnMes = calendario.getActualMaximum(Calendar.DAY_OF_MONTH);

		for (int i = 1; i < diaSemana; i++) {
			panelCalendario.add(new JLabel(""));
		}

		for (int dia = 1; dia <= diasEnMes; dia++) {
			JPanel panelDia = new JPanel(new BorderLayout());
			panelDia.setBorder(BorderFactory.createLineBorder(Color.BLACK));

			JLabel etiquetaNumeroDia = new JLabel("" + dia, JLabel.CENTER);
			etiquetaNumeroDia.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			panelDia.add(etiquetaNumeroDia, BorderLayout.NORTH);

			

			for (TransferTarea tt : listaTareas) {

				if (tt.getFecha_de_entrega() == new Date(2023, mes, dia, 0, 0, 0)) {

					JLabel etiquetaX = new JLabel(tt.getNombre(), JLabel.CENTER);
					etiquetaX.setForeground(Color.RED);
					panelDia.add(etiquetaX, BorderLayout.CENTER);

				}
			}

			panelCalendario.add(panelDia);
			calendario.add(Calendar.DAY_OF_MONTH, 1);
		}

		return panelCalendario;
	}

}
