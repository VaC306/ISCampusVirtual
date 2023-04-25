package Integracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Negocio.Aula.TransferAsignatura;
import Negocio.Aula.TransferTema;

public class DAOAsignaturaImpl implements DAOAsignatura{
	
	static String bd = "campus_virtual";
	static String login = "root";
	static String password = "grupo7";
	static String url = "jdbc:mysql://localhost/" + bd;

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
				ps2.close();
				r2.close();
			}
				
			connection.close();
			ps.close();
			r.close();
			
		}catch(Exception e) {
			
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
