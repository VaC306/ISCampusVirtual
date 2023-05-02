package Integracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Negocio.Archivos.TransferArchivo;
import Negocio.Aula.TransferTema;
import Negocio.Factoria.FactoriaArchivo;
import Negocio.Factoria.FactoriaUsuario;

public class DAOTemaImpl implements DAOTema{
	
	static String bd = "campus_virtual";
	static String login = "root";
	static String password = "grupo7";
	static String url = "jdbc:mysql://127.0.0.1:3306/" + bd;

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
				
				String s2 = "SELECT * FROM archivos WHERE IdTema = ?;";
				PreparedStatement ps2 = connection.prepareStatement(s2);
				ps2.setString(1, idTema);
				
				ResultSet r2 = ps2.executeQuery();
				
				List<TransferArchivo> LT = new ArrayList<TransferArchivo>();
				
				while (r2.next()) {
					LT.add(FactoriaArchivo.getInstance().createTransferById(r2.getString("IdArchivo")));
				}
				
				ps2.close();
				r2.close();
				
				
				
				TT = new TransferTema(
						r.getString("IdTema"),
						r.getString("Nombre"),
						r.getInt("Numero"),
						LT, // Lista de Archivo
						r.getString("IdAsignatura") // Asignatura		
						);
				System.out.println("El tema " + TT.getNombre() + " tiene " + LT.size() + " archivos " + LT.get(0).getNombre());
			}
			
			
			
			connection.close();
			ps.close();
			r.close();
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
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
