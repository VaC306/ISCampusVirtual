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
	public TransferMensaje read(String idUsuario, Date date) {
		TransferMensaje TM = null;
		
		try {
			
			String s = "SELECT * FROM mensajes WHERE IdMensaje = ?;";
			Connection connection = DriverManager.getConnection(url, login, password);
			PreparedStatement ps = connection.prepareStatement(s);
			// NO TENGO ID USUARIO EN CADA MENSAJE, CAMBIAR TODO
			//ps.setString(1, id);
			
			ResultSet r = ps.executeQuery();
			
			if(r.next()) {
				TM = new TransferMensaje(
						r.getDate("Fecha"), 
						r.getString("Cuerpo"),
						r.getString("Titulo"),
						//r.getUsuario()
						null
					);
			}
			
			connection.close();
			ps.close();
			r.close();
			
		}catch(Exception e) {
			
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
	public void eliminate(String idUsuario, Date date) {
		try {
			String s = "DELETE FROM mensajes WHERE IdUsuario = ?;";
			
			Connection connection = DriverManager.getConnection(url, login, password);
			PreparedStatement ps = connection.prepareStatement(s);
			
			ps.setString(1, idUsuario);
			ps.executeUpdate();
			
			connection.close();
			ps.close();
			
		}catch(Exception e) {
			
		}
	}

}
