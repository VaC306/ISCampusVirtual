package Integracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Negocio.Aula.EnumCurso;
import Negocio.Aula.TransferClase;

public class DAOClaseImpl implements DAOClase{
	
	static String bd = "campus_virtual";
	static String login = "root";
	static String password = "grupo7";
	static String url = "jdbc:mysql://localhost/" + bd;

	@Override
	public TransferClase read(EnumCurso enumCurso, String letra) {
		TransferClase TC = null;
		
		try {
			
			String s = "SELECT * FROM clases WHERE curso = ? AND grupo = ?";
			Connection connection = DriverManager.getConnection(url, login, password);
			PreparedStatement ps = connection.prepareStatement(s);
			
			ps.setString(1, enumCurso.name());
			ps.setString(2, letra);
			
			ResultSet r = ps.executeQuery();
			
			if(r.next()) {
				TC = new TransferClase();
				TC.setId(r.getString("IdClase"));
				TC.setAnyo(r.getInt("anyo"));
				//TC.setCurso(r.getString("curso"));
				TC.setGrupo(r.getString("grupo"));
			}
			
			connection.close();
			ps.close();
			r.close();
			
		}catch(Exception e) {
			
		}
		
		return TC;
	}

	@Override
	public void create(EnumCurso enumCurso, TransferClase aTNew) {
		
		try {
			String s = "INSERT INTO clases (IdClase, anyo, curso, grupo) VALUES (?,?,?,?)";
			
			Connection connection = DriverManager.getConnection(url, login, password);
			PreparedStatement ps = connection.prepareStatement(s);
			
			ps.setString(1, aTNew.getId());
			ps.setInt(2, aTNew.getAnyo());
			ps.setString(3, aTNew.getCurso().name());
			ps.setString(4, aTNew.getGrupo());
			
			ps.executeUpdate();
			
			connection.close();
			ps.close();
			
		}catch(Exception e) {
			
		}
	}

	@Override
	public void eliminate(EnumCurso enumCurso, String letra) {
		try {
			
			String s = "DELETE FROM clases WHERE curso = ? AND grupo = ?;";
			Connection connection = DriverManager.getConnection(url, login, password);
			PreparedStatement ps = connection.prepareStatement(s);
			
			ps.setString(1, enumCurso.name());
			ps.setString(2, letra);
			ps.executeUpdate();
			
			connection.close();
			ps.close();
			
		}catch(Exception e) {
			
		}
	}

}
