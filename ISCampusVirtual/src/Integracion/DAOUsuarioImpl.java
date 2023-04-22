package Integracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Negocio.Usuario.TransferProfesor;
import Negocio.Usuario.TransferUsuario;

public class DAOUsuarioImpl implements DAOUsuario {

	static String bd = "campus_virtual";
	static String login = "root";
	static String password = "grupo7";
	static String url = "jdbc:mysql://localhost/" + bd;
	
	@Override
	public void eliminate(String id) {
		try {
		String s = "DELETE FROM usuarios WHERE NIF = ?";
			
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
	public void create(TransferUsuario aTNew) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TransferUsuario readById(String id) {
		
		return null;
	}

	
	@Override
	public TransferUsuario readByCorreo(String id) {
		
		return null;
	}

	
}
