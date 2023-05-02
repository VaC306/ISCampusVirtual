package Integracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Negocio.Aula.TransferAsignatura;
import Negocio.Aula.TransferTema;
import Negocio.Foro.TransferForo;
import Negocio.Usuario.TransferAlumno;
import Negocio.Usuario.TransferProfesor;

public class DAOAsignaturaImpl implements DAOAsignatura{
	
	static String bd = "campus_virtual";
	static String login = "root";
	static String password = "grupo7";
	static String url = "jdbc:mysql://127.0.0.1:3306/" + bd;
	
	@Override
	public TransferAsignatura read(String id) {
		TransferAsignatura TA = null;
		try {
			String s = "SELECT * FROM asignaturas WHERE IdAsignatura = ?;";
			Connection connection = DriverManager.getConnection(url, login, password);
			PreparedStatement ps = connection.prepareStatement(s);
			ps.setString(1, id);
			
			ResultSet r = ps.executeQuery();
			
			if(r.next()) {
				TA = new TransferAsignatura();
				TA.setID(r.getString("IdAsignatura"));
				TA.setNombre(r.getString("Nombre"));
				
				List<TransferTema> LT = new ArrayList<TransferTema>();
				
				String s2 = "SELECT * FROM temas WHERE IdAsignatura = ?;";
				PreparedStatement ps2 = connection.prepareStatement(s2);
				ps2.setString(1, id);
				
				ResultSet r2 = ps2.executeQuery();
				
				while(r2.next()) {
					
					DAOTema daoT = new DAOTemaImpl();
					LT.add(daoT.read(r2.getString("IdTema")));
				}
				
				TA.setTemas(LT);
				
				DAOForo daoF = new DAOForoImpl();
				TA.setAvisos(daoF.read(id));
				
				
				s2 = "SELECT * FROM alumnos WHERE IdAsignatura = ?;";
				ps2 = connection.prepareStatement(s2);
				ps2.setString(1, id);
				
				r2 = ps2.executeQuery();
				
				List<TransferAlumno> LTA = new ArrayList<TransferAlumno>();
				while (r2.next()) {
					DAOAlumno daoA = new DAOAlumnoImpl();
					LTA.add((TransferAlumno) daoA.readById(r2.getString("NIF")));
				}
				
				TA.setAlumno(LTA);
				
				s2 = "SELECT * FROM profesores WHERE IdAsignatura = ?;";
				ps2 = connection.prepareStatement(s2);
				ps2.setString(1, id);
				
				r2 = ps2.executeQuery();
				
				List<TransferProfesor> LTP = new ArrayList<TransferProfesor>();
				while (r2.next()) {
					DAOProfesor daoP = new DAOProfesorImpl();
					LTP.add((TransferProfesor) daoP.readById(r2.getString("NIF")));
				}
				TA.setProfesor(LTP);
				
				ps2.close();
				r2.close();
				
			
			}
				
			connection.close();
			ps.close();
			r.close();
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return TA;
	}

	@Override
	public void create(TransferAsignatura tAnew) {
		try {
			
			String s = "INSERT INTO asignaturas (IdAsignatura, Nombre, IdClase) VALUES (?,?,?);";
			
			Connection connection = DriverManager.getConnection(url, login, password);
			PreparedStatement ps = connection.prepareStatement(s);
			
			ps.setString(1, tAnew.getID());
			ps.setString(2, tAnew.getNombre());
			ps.setString(3, null);
			ps.executeUpdate();
			
			connection.close();
			ps.close();
			
		}catch(Exception e) {
			
		}
	}

	@Override
	public void eliminate(String id) {
		try {
			String s = "DELETE FROM asignaturas WHERE IdAsignatura =?;";
			
			Connection connection = DriverManager.getConnection(url, login, password);
			PreparedStatement ps = connection.prepareStatement(s);
			
			ps.setString(1, id);
			ps.executeUpdate();
			
			connection.close();
			ps.close();
			
		}catch (Exception e) {
			
		}
	}

}
