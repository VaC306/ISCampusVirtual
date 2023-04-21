package Presentacion;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

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
		initGUI();
		
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
		JLabel infoUsuatios = new JLabel("Usuarios: " + numUsuarios);
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
		
		
		//mainPanel.add(buttonPanel);
		//mainPanel.add(Box.createRigidArea(new Dimension(20, 0)));
		//mainPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		
		//Rol Label
		rol = new JLabel("Roles: ");
		topPanel.add(rol);
		
		//COMBOBOX
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(filtroRoles);
		topPanel.add(buttonPanel);
		
		
		//TextArea Nombres
		tableModel = new DefaultTableModel(new Object[] { "Nombres: ", "Roles: " }, 10 /*pillar num de columnas de num de usuarios*/);
        JTable tableAlumnos = new JTable(tableModel);
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
			if(filtroRoles.getSelectedIndex() == 0)
			{
				for(int i =0; i< alumnosMostrar.size();i++)
				{
					tableAlumnos.add(alumnosMostrar.get(i).getNombre_Apellidos(), null);
					
				}
			}
			else if(filtroRoles.getSelectedIndex() == 1)
			{
				for(int i =0; i< profesorMostrar.size();i++) 
				{
					tableAlumnos.add(profesorMostrar.get(i).getNombre_Apellidos(), null);
				}
			}
			else if(filtroRoles.getSelectedIndex() == 2)
			{
				//mostrar todos
			}
		});

		mainPanel.add(scroll);
		this.setContentPane(mainPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		
		ctrl.accion(Events.MOSTRAR_ALUMNOS_ASIGNATURA, null);
		
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
		List<TransferUsuario> lista=(List<TransferUsuario>) datos;
		
		//profesorMostrar = datos;//transferAsigunatura.profesor (Lista de profesores)
		//alumnosMostrar = datos;//transferAsigunatura.profesor (Lista de profesores)

		
	}

}