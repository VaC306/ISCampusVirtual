package Integracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import Negocio.Foro.TransferMensaje;

public class DAOMensajeImpl implements DAOMensaje{
	
	static String bd = "campus_virtual";
	static String login = "root";
	static String password = "grupo7";
	static String url = "jdbc:mysql://127.0.0.1:3306/" + bd;

	@Override
	public TransferMensaje read(String idMensaje) {
		TransferMensaje TM = null;
		
		try {
			
			String s = "SELECT * FROM mensajes WHERE IdMensaje = ?;";
			Connection connection = DriverManager.getConnection(url, login, password);
			PreparedStatement ps = connection.prepareStatement(s);
			ps.setString(1, idMensaje);
			
			ResultSet r = ps.executeQuery();
			
			if(r.next()) {
				TM = new TransferMensaje(
						r.getDate("Fecha"), 
						r.getString("Cuerpo"),
						null
						//r.getUsuario()
						//null
					);
			}
			
			connection.close();
			ps.close();
			r.close();
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return TM;
	}

	@Override
	public void create(TransferMensaje aTNew) {
		try {
			
			String s = "INSERT INTO mensajes () VALUES (?,?,?,?)";
			
		}catch(Exception e) {
			
		}
	}

	@Override
	public void eliminate(String idMensaje) {
		try {
			String s = "DELETE FROM mensajes WHERE IdMensaje = ?;";
			
			Connection connection = DriverManager.getConnection(url, login, password);
			PreparedStatement ps = connection.prepareStatement(s);
			
			ps.setString(1, idMensaje);
			ps.executeUpdate();
			
			connection.close();
			ps.close();
			
		}catch(Exception e) {
			
		}
	}

}
