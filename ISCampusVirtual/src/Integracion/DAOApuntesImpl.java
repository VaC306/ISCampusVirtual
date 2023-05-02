package Integracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Negocio.Archivos.Tipos_archivo;
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
			
			s = "SELECT * FROM archivos WHERE IdArchivo = ?";
			ps = connection.prepareStatement(s);
			ps.setString(1, titulo);
			r = ps.executeQuery();
			
			if(r.next()) {
				TA.setNombre(r.getString("Nombre"));
				TA.setTipo_archivo(Tipos_archivo.valueOf(r.getString("tipo_archivo")));
				TA.setTemas(r.getString("IdTema"));
			}
			
			connection.close();
			ps.close();
			r.close();
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return TA;
	}

	@Override
	public void create(TransferApuntes aTNew) {
		try {
			
			String s = "INSERT INTO archivos (IdArchivo, Nombre, tipo_archivo) VALUES (?,?,?);";
			
			Connection connection = DriverManager.getConnection(url, login, password);
			PreparedStatement ps = connection.prepareStatement(s);
			
			ps.setString(1, aTNew.getId());
			ps.setString(2, aTNew.getNombre());
			ps.setString(3, aTNew.getTipo_archivo().name());
			ps.executeUpdate();
			
			s = "INSERT INTO Apuntes (IdApuntes, IdArchivo) VALUES (?,?);";
			ps = connection.prepareStatement(s);
			
			ps.setString(1, aTNew.getIdApuntes());
			ps.setString(2, aTNew.getId());
			ps.executeUpdate();
			
			connection.close();
			ps.close();
			
		}catch(Exception e) {
			
		}
	}

	@Override
	public void eliminate(TransferTema tema, String titulo) {
		// TODO Auto-generated method stub
		
	}

}
