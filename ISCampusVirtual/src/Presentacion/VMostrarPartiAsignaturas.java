package Presentacion;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Negocio.Aula.TransferAsignatura;
import Negocio.Usuario.TransferAlumno;
import Negocio.Usuario.TransferProfesor;
import Negocio.Usuario.TransferUsuario;
import Presentacion.Control.Controller;
import Presentacion.Control.Events;
import Presentacion.Control.IGUI;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VMostrarPartiAsignaturas extends JFrame implements IGUI{
	
	private Controller ctrl;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String nombreAs = "IS2";
	private String roles[] = { "TODOS", "ALUMNOS", "PROFESORES" };
	private JLabel nombre;
	private JLabel apellidos;
	private JLabel rol;
	private JComboBox<String> filtroRoles;
	private int numUsuarios;
	private static DefaultTableModel tableModel;
	private Border _blackBorder = BorderFactory.createLineBorder(Color.black, 2);
	private JTable tableAlumnos;
	JPanel centerPanel, leftPanel, rightPanel, topPanel, botPanel;
	
	private int numParticipantes = 3;
	
	private JPanel boxesPanel;
	
	private List<TransferProfesor> profesorMostrar = new ArrayList<> ();

	private List<TransferAlumno> alumnosMostrar = new ArrayList<> ();
	

	private String nombres[] = { "Alumno 1", "Alumno 2", "Profesor 1" };
	
	public VMostrarPartiAsignaturas() 
	{
		super ("Participantes de " + nombreAs );
		ctrl=Controller.obtenerInstancia();
		
	}
	
	
	//en el init recibe un transferusuario con profes y alumnos
	private void initGUI() {

		setLocationRelativeTo(null);
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		centerPanel = createPanel(Color.red, 50, 50);
		mainPanel.add(centerPanel, BorderLayout.CENTER);

		topPanel = createPanel(Color.WHITE, 40, 40);
		mainPanel.add(topPanel, BorderLayout.PAGE_START);

		botPanel = createPanel(Color.gray, 40, 40);
		mainPanel.add(botPanel, BorderLayout.PAGE_END);
		
		
		JPanel panelInfo = new JPanel();
		JLabel infoUsuatios = new JLabel("Usuarios: " + alumnosMostrar.size()+profesorMostrar.size());
		panelInfo.add(infoUsuatios);
		panelInfo.setAlignmentX(LEFT_ALIGNMENT);
		botPanel.add(panelInfo);
		
		
		
		filtroRoles = new JComboBox<String>(roles);
		filtroRoles.setSelectedIndex(0);
		
		JPanel Etiquetas = new JPanel();
		
		//Nombre etiqueta
		nombre = new JLabel("Filtros => ");
		nombre.setAlignmentX(SwingConstants.LEFT);
		Etiquetas.add(nombre);
		
		topPanel.add(Etiquetas, LEFT_ALIGNMENT);
		
		
		//Rol Label
		rol = new JLabel("Roles: ");
		topPanel.add(rol);
		
		//COMBOBOX
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(filtroRoles);
		topPanel.add(buttonPanel);
		
		
		//TextArea Nombres
		tableModel = new DefaultTableModel(new Object[] { "Nombres: ", "Roles: " }, 10 /*pillar num de columnas de num de usuarios*/);
        tableAlumnos = new JTable(tableModel);
        numUsuarios = tableAlumnos.getRowCount();
		centerPanel.setBorder(BorderFactory.createTitledBorder(_blackBorder, "Usuarios: ", TitledBorder.LEFT, TitledBorder.TOP));
		
		
		
		JScrollPane scroll = new JScrollPane(tableAlumnos,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		

		// We then set the preferred size of the scrollpane.
		scroll.setPreferredSize(new Dimension(600, 400));
		
		//Bucle de lista
		
		//if seleccionado combobox 0 alumnos 1 profes 2 todos
		filtroRoles.addActionListener((e) -> 
		{
			if(filtroRoles.getSelectedIndex() == 1)
			{
				tableModel.setRowCount(alumnosMostrar.size());
				for(int i =0; i< alumnosMostrar.size();i++)
				{
					tableAlumnos.setValueAt(alumnosMostrar.get(i).getNombre_Apellidos(), i, 0);
					tableAlumnos.setValueAt("Alumno", i, 1);

				}
				
				numUsuarios = alumnosMostrar.size();
			}
			else if(filtroRoles.getSelectedIndex() == 2)
			{	
				tableModel.setRowCount(profesorMostrar.size());
				for(int i =0; i< profesorMostrar.size();i++) 
				{
					tableAlumnos.setValueAt(profesorMostrar.get(i).getNombre_Apellidos(), i, 0);
					tableAlumnos.setValueAt("Profesor", i, 1);

				}
				
				numUsuarios = profesorMostrar.size();
			}
			else if(filtroRoles.getSelectedIndex() == 0)
			{
				
				mostrarAmbos();
				
			}
		});

		mainPanel.add(scroll);
		
		JButton cerrar = new JButton("Cerrar");
		cerrar.addActionListener((e)-> {
			setVisible(false);
		}
		);
		
		mainPanel.add(cerrar, BorderLayout.PAGE_END);
		this.setContentPane(mainPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		
	}

	private void mostrarAmbos() {
		
		tableModel.setRowCount(profesorMostrar.size()+alumnosMostrar.size());
		
		for(int i =0; i< alumnosMostrar.size();i++)
		{
			tableAlumnos.setValueAt(alumnosMostrar.get(i).getNombre_Apellidos(), i, 0);
			tableAlumnos.setValueAt("Alumno", i, 1);

		}
		int j= 0;
		for(int i = alumnosMostrar.size(); i< profesorMostrar.size()+alumnosMostrar.size();i++) 
		{
			tableAlumnos.setValueAt(profesorMostrar.get(j).getNombre_Apellidos(), i, 0);
			tableAlumnos.setValueAt("Profesor", i, 1);

			j++;
		}
		numUsuarios = alumnosMostrar.size() + profesorMostrar.size();		
	}


	private JPanel createPanel(Color color, int x, int y) {

		JPanel panel;
		panel = new JPanel();
		panel.setBackground(color);
		panel.setPreferredSize(new Dimension(x, y));
		return panel;
	}
	
	//dependiendo del event, devuelve una lista con alumnos o profesors (datos puede ser null)
	@Override
	public void update(int event, Object datos) {
		
		profesorMostrar = ((TransferAsignatura)datos).getProfesor();//transferAsigunatura.profesor (Lista de profesores)
		alumnosMostrar = ((TransferAsignatura)datos).getAlumno();//transferAsigunatura.profesor (Lista de profesores)

		initGUI();
		mostrarAmbos();
	}

}