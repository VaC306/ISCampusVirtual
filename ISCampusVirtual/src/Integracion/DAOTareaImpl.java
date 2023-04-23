package Integracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Negocio.Archivos.TransferTarea;

public class DAOTareaImpl implements DAOTarea {
	
	static String bd = "campus_virtual";
	static String login = "root";
	static String password = "grupo7";
	static String url = "jdbc:mysql://localhost/" + bd;

	@Override
	public TransferTarea read(String IdTarea) {
		TransferTarea TT = null;
		try {
			String s = "SELECT * FROM tareas WHERE IdTarea = ?;";
			Connection connection = DriverManager.getConnection(url, login, password);
			PreparedStatement ps = connection.prepareStatement(s);
			ps.setString(1, IdTarea);
			
			ResultSet r = ps.executeQuery();
			
			if (r.next()) {
				TT = new TransferTarea(
						r.getString("IdTarea"),
						r.getString("IdArchivo"),
						r.getDate("Fecha_entrega")	
					);
			}
			
			connection.close();
			ps.close();
			r.close();
			
		}catch (Exception e){
			
		}
		return TT;
	}

	@Override
	public void create(TransferTarea aTNew) {
		try {
			String s ="INSERT INTO tareas (IdTarea, IdArchivo, Fecha_entrega) VALUES (?,?,?);";
			
			Connection connection = DriverManager.getConnection(url, login, password);
			PreparedStatement ps = connection.prepareStatement(s);
			
			ps.setString(1, aTNew.getIdTarea());
			ps.setString(2, aTNew.getId());
			//ps.setDate(3, aTNew.getFecha_de_entrega());
			ps.executeUpdate();
			
			connection.close();
			ps.close();
		}catch(Exception e) {
			
		}
	}

	@Override
	public void eliminate(String IdTarea) {
		
		try {
			String s ="DELETE FROM tareas WHERE IdTarea = ?;";
			
			Connection connection = DriverManager.getConnection(url, login, password);
			PreparedStatement ps = connection.prepareStatement(s);
			
			ps.setString(1, IdTarea);
			ps.executeUpdate();
			
			connection.close();
			ps.close();
			
		}catch(Exception e) {
			
		}
	}

}
