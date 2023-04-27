package Integracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Negocio.Archivos.TransferApuntes;
import Negocio.Archivos.TransferArchivo;
import Negocio.Aula.TransferTema;

public class DAOApuntesImpl implements DAOApuntes {

	static String bd = "campus_virtual";
	static String login = "root";
	static String password = "grupo7";
	static String url = "jdbc:mysql://127.0.0.1:3306/" + bd;
	
	@Override
	public TransferArchivo read(String titulo) {
		TransferApuntes TA = null;
		
		try {
			String s = "SELECT * FROM apuntes WHERE IdArchivo = ?;";
			Connection connection = DriverManager.getConnection(url, login, password);
			PreparedStatement ps = connection.prepareStatement(s);
			ps.setString(1, titulo);
			
			ResultSet r = ps.executeQuery();
			
			if (r.next()) {
				
				TA = new TransferApuntes();
				TA.setIdApuntes(r.getString("IdApuntes"));
				TA.setId(r.getString("IdArchivo"));
				
			}
			
			connection.close();
			ps.close();
			r.close();
			
		}catch (Exception e) {
			
		}
		
		return TA;
	}

	@Override
	public void create(TransferArchivo aTNew) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminate(TransferTema tema, String titulo) {
		// TODO Auto-generated method stub
		
	}

}
