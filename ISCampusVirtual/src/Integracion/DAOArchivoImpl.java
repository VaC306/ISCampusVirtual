package Integracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Negocio.Archivos.TransferArchivo;
import Negocio.Aula.TransferTema;

public class DAOArchivoImpl implements DAOArchivo{

	static String bd = "campus_virtual";
	static String login = "root";
	static String password = "grupo7";
	static String url = "jdbc:mysql://127.0.0.1:3306/" + bd;
	
	@Override
	public TransferArchivo read(String titulo) {
		TransferArchivo TA = null;
		
		try {
			String s = "SELECT * FROM archivos WHERE Nombre = ?;";
			Connection connection = DriverManager.getConnection(url, login, password);
			PreparedStatement ps = connection.prepareStatement(s);
			ps.setString(1, titulo);
			
			ResultSet r = ps.executeQuery();
			
			if(r.next()) {
				//TA = new TransferArchivo();
				TA.setId(r.getString("IdArchivo"));
				TA.setNombre(r.getString("Nombre"));
				TA.setTipo_archivo(null);
			}
		}catch(Exception e) {
			
		}
		
		return TA;
	}

	@Override
	public void create(TransferArchivo aTNew) {
		try {
			
			String s = "INSERT INTO archivos (IdArchivo, Nombre, tipo_archivo) VALUES (?,?,?);";
			
			Connection connection = DriverManager.getConnection(url, login, password);
			PreparedStatement ps = connection.prepareStatement(s);
			
			ps.setString(1, aTNew.getId());
			ps.setString(2, aTNew.getNombre());
			ps.setString(3, aTNew.getTipo_archivo().name());
			ps.executeUpdate();
			
			connection.close();
			ps.close();
			
		}catch(Exception e) {
			
		}
	}

	@Override
	public void eliminate(TransferTema tema, String titulo) {
		try {
			
			String s = "DELETE FROM archivos WHERE Nombre = ?;";
			
			Connection connection = DriverManager.getConnection(url, login, password);
			PreparedStatement ps = connection.prepareStatement(s);
			
			ps.setString(1, titulo);
			ps.executeUpdate();
			
			connection.close();
			ps.close();
			
		}catch(Exception e) {
			
		}
	}

}
