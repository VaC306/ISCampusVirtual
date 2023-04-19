package vista;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import control.IGUI;
import model.usuario.TransferAlumno;
import model.usuario.TransferProfesor;

public class VMostrarPartiAsignaturas extends JFrame implements IGUI{

	private static String nombreAs = "IS2";
	private String roles[] = { "TODOS", "ALUMNOS", "PROFESORES" };
	private JLabel nombre;
	private JLabel apellidos;
	private JLabel rol;
	private JComboBox<String> filtroRoles;
	
	JPanel centerPanel, leftPanel, rightPanel, topPanel, botPanel;
	
	private int numParticipantes = 3;
	
	private JPanel boxesPanel;
	
	private List<TransferProfesor> profesorMostrar = new ArrayList<> ();

	private List<TransferAlumno> alumnosMostrar = new ArrayList<> ();
	

	private String nomnbres[] = { "Alumno 1", "Alumno 2", "Profesor 1" };
	
	
	public VMostrarPartiAsignaturas() 
	{
		super ("Participantes de " + nombreAs );
		initGUI();
		
	}
	
	
	//en el init recibe un transferusuario con profes y alumnos
	private void initGUI() {

		setLocationRelativeTo(null);
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		centerPanel = createPanel(Color.red, 50, 50);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		
	
		rightPanel = createPanel(Color.yellow, 200, 200);
		mainPanel.add(rightPanel, BorderLayout.LINE_END);

		topPanel = createPanel(Color.WHITE, 40, 40);
		mainPanel.add(topPanel, BorderLayout.PAGE_START);

		botPanel = createPanel(Color.gray, 20, 20);
		mainPanel.add(botPanel, BorderLayout.PAGE_END);

		filtroRoles = new JComboBox<String>(roles);
		filtroRoles.setSelectedIndex(0);
		//filtroRoles.addActionListener(this);

		
		//Nombre etiqueta
		nombre = new JLabel("Nombres: ");
		nombre.setAlignmentX(SwingConstants.LEFT);

		topPanel.add(nombre);
		
		
		//COMBOBOX
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(filtroRoles);
		topPanel.add(buttonPanel);
		
		//mainPanel.add(buttonPanel);
		mainPanel.add(Box.createRigidArea(new Dimension(20, 0)));
		mainPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		
		//Rol Label
		rol = new JLabel("Roles: ");
		topPanel.add(rol);
		
		String story = "The Internet Foundation Classes (IFC) were a graphics "
				+ "library for Java originally developed by Netscape Communications "
				+ "Corporation and first released on December 16, 1996.\n\n"
				+ "On April 2, 1997, Sun Microsystems and Netscape Communications"
				+ " Corporation announced their intention to combine IFC with other"
				+ " technologies to form the Java Foundation Classes. In addition "
				+ "to the components originally provided by IFC, Swing introduced "
				+ "a mechanism that allowed the look and feel of every component "
				+ "in an application to be altered without making substantial "
				+ "changes to the application code. The introduction of support "
				+ "for a pluggable look and feel allowed Swing components to "
				+ "emulate the appearance of native components while still "
				+ "retaining the benefits of platform independence. This feature "
				+ "also makes it easy to have an individual application's appearance "
				+ "look very different from other native programs.\n\n"
				+ "Originally distributed as a separately downloadable library, "
				+ "Swing has been included as part of the Java Standard Edition "
				+ "since release 1.2. The Swing classes are contained in the "
				+ "javax.swing package hierarchy.\n\n";
		
		String story2 = "profe, alumno,profe, alumno "
				+ "profe, alumno profe, alumno profe, alumno.\n\n";
		
		
		
		
		//TextArea Nombres
		JTextArea nombresArea = new JTextArea(story);
		nombresArea.setEditable(false);
		nombresArea.setLineWrap(true);
		nombresArea.setWrapStyleWord(true);
		
		
		//TextArea Roles
		JTextArea rolessArea = new JTextArea(story2);
		rolessArea.setEditable(false);
		rolessArea.setLineWrap(true);
		rolessArea.setWrapStyleWord(true);
		
		JScrollPane area = new JScrollPane(nombresArea,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		JScrollPane areaRoles = new JScrollPane(rolessArea,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		// We then set the preferred size of the scrollpane.
		area.setPreferredSize(new Dimension(300, 200));
		
		//Bucle de lista
		
		//if seleccionado combobox 0 alumnos 1 profes 2 todos
		
		for(int i =0; i< alumnosMostrar.size();i++) 
		{
			nombresArea.add(alumnosMostrar.get(i).getNombre_Apellidos(), null);
			//nombresArea.add("Alumno");

			
		}
		
		for(int i =0; i< profesorMostrar.size();i++) 
		{
			nombresArea.add(profesorMostrar.get(i).getNombre_Apellidos(), null);
			//rolessArea.add("Profesor");
		}
		
		rightPanel.add(areaRoles);
		mainPanel.add(area);
		mainPanel.setOpaque(true);
		this.setContentPane(mainPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		
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
		
		
		//profesorMostrar = datos;//transferAsigunatura.profesor (Lista de profesores)
		//alumnosMostrar = datos;//transferAsigunatura.profesor (Lista de profesores)

		
	}

}