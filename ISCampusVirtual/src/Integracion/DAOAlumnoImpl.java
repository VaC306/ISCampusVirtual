package Integracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Negocio.Usuario.TransferAlumno;
import Negocio.Usuario.TransferUsuario;

public class DAOAlumnoImpl implements DAOAlumno{
	
	static String bd = "campus_virtual";
	static String login = "root";
	static String password = "grupo7";
	static String url = "jdbc:mysql://localhost/" + bd;
	
	@Override
	public void eliminate(String id) {
		
		try {				
			String s = "DELETE FROM alumnos WHERE IdAlumno = ?";
			
			Connection connection = DriverManager.getConnection(url, login, password);
			PreparedStatement ps = connection.prepareStatement(s);
			
			ps.setString(1,id);
			ps.executeUpdate();
			
			connection.close();
			ps.close();
			
		}catch(Exception e) {
		}
		
	}
	
	@Override
	public TransferUsuario readById(String id) {
		TransferAlumno TA =null;
		try {
			
			String s = "SELECT * FROM alumnos WHERE IdAlumno = ?;";
			
			Connection connection = DriverManager.getConnection(url, login, password);
			PreparedStatement ps = connection.prepareStatement(s);
			
			ps.setString(1, id);
			ResultSet r = ps.executeQuery();
			
			if(r.next()) {
				TA = new TransferAlumno();
				TA.setId(r.getString("IdAlumno"));
				TA.setNIF(r.getString("NIF"));
				TA.setDelegado(r.getBoolean("Delegado"));
				TA.setAsignaturas(null);
			}
			
			connection.close();
			ps.close();
			r.close();
			
		}catch(Exception e) {
			
		}
		return TA;
	}
	
	@Override
	public void create(TransferAlumno aTNew) {
		try {
			
			String s = "INSERT INTO usuarios (IdAlumno, NIF, Delegao, IdAsignatura) VALUES (?,?,?,?)";

			Connection connection = DriverManager.getConnection(url, login, password);
			PreparedStatement ps = connection.prepareStatement(s);
			
			ps.setString(1, aTNew.getId());
			ps.setString(2, aTNew.getNIF());
			ps.setBoolean(3, aTNew.isDelegado());
			ps.setArray(4, null);
			ps.executeUpdate();
			
			connection.close();
			ps.close();
			
		}catch(Exception e) {
			
		}
	}

	@Override
	public void create(TransferUsuario aTNew) {
		// TODO Auto-generated method stub
		
	}
}
