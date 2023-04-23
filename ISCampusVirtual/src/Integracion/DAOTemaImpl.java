package Integracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Negocio.Aula.TransferTema;

public class DAOTemaImpl implements DAOTema{
	
	static String bd = "campus_virtual";
	static String login = "root";
	static String password = "grupo7";
	static String url = "jdbc:mysql://localhost/" + bd;

	@Override
	public TransferTema read(String idTema) {
		TransferTema TT = null;
		
		try {
			String s = "SELECT * FROM temas WHERE IdTema = ?;";
			Connection connection = DriverManager.getConnection(url, login, password);
			PreparedStatement ps = connection.prepareStatement(s);
			ps.setString(1, idTema);
			
			ResultSet r = ps.executeQuery();
			
			if(r.next()) {
				TT = new TransferTema(
						r.getString("IdTema"),
						r.getString("Nombre"),
						r.getInt("Numero"),
						null, // Archivo
						null // Asignatura		
						);
			}
			
			connection.close();
			ps.close();
			r.close();
			
		}catch(Exception e) {
			
		}
		
		return TT;
	}

	@Override
	public void create(TransferTema aTNew) {
		try {
			
			String s = "INSERT INTO tareas (IdTema, Nombre, Numero, idArchivo, IdAsignatura) VALUES (?,?,?,?,?);";
		
			Connection connection = DriverManager.getConnection(url, login, password);
			PreparedStatement ps = connection.prepareStatement(s);
			
			ps.setString(1, aTNew.getId());
			ps.setString(2, aTNew.getNombre());
			ps.setInt(3, aTNew.getNumero());
			ps.setString(4, aTNew.getArchivo().get(0).getId());
			ps.setString(5, aTNew.getAsignaturas().getID());
			ps.executeUpdate();
			
			connection.close();
			ps.close();
			
		}catch(Exception e) {
			
		}
	}

	@Override
	public void eliminate(String idTema) {
		try {
			String s = "DELETE FROM tareas WHERE IdTema = ?;";
			
			Connection connection = DriverManager.getConnection(url, login, password);
			PreparedStatement ps = connection.prepareStatement(s);
			
			ps.setString(1, idTema);
			ps.executeUpdate();
			
			connection.close();
			ps.close();
		}catch (Exception e) {
			
		}
	}

}
