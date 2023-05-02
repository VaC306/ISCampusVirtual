package Integracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Negocio.Aula.TransferAsignatura;
import Negocio.Usuario.TransferAlumno;
import Negocio.Usuario.TransferProfesor;
import Negocio.Usuario.TransferUsuario;

public class DAOProfesorImpl implements DAOProfesor {
	
	static String bd = "campus_virtual";
	static String login = "root";
	static String password = "grupo7";
	static String url = "jdbc:mysql://127.0.0.1:3306/" + bd;

	@Override
	public void eliminate(String id) {
		
	try {
		String s = "DELETE FROM profesores WHERE NIF = ?";
		
		Connection connection = DriverManager.getConnection(url, login, password);
		PreparedStatement ps = connection.prepareStatement(s);
		
		ps.setString(1,id);
		ps.executeUpdate();
		
		s = "DELETE FROM usuarios WHERE NIF = ?";
		ps = connection.prepareStatement(s);
		ps.setString(1, id);
		ps.executeUpdate();
		
		connection.close();
		ps.close();
		
	}catch(Exception e) {
	}
	}

	@Override
	public TransferUsuario readById(String id) {
		TransferProfesor TA =null;
		try {
			
			String s = "SELECT * FROM profesores WHERE NIF = ?;" ;
			Connection connection = DriverManager.getConnection(url, login, password);
			PreparedStatement ps = connection.prepareStatement(s);
			ps.setString(1,  id);
			
			ResultSet r = ps.executeQuery();
			
			if(r.next()) {
				TA = new TransferProfesor();
				TA.setId(r.getString("IdProfesor"));
				TA.setNIF(r.getString("NIF"));
				ArrayList asignaturas = new ArrayList<>();
				
				String s2 = "SELECT IdAsignatura FROM profesores WHERE NIF = ?;";
				PreparedStatement ps2 = connection.prepareStatement(s2);
				ps2.setString(1, TA.getNIF());
				
				ResultSet r2 = ps2.executeQuery();
				
				while (r2.next()) {
					DAOAsignatura dao = new DAOAsignaturaImpl();
					asignaturas.add(r2.getString("IdAsignatura"));
				}
				
				TA.setAsignaturas(asignaturas);
			}
			
			s = "SELECT * FROM usuarios WHERE NIF = ?;";
			
			ps = connection.prepareStatement(s);
			
			ps.setString(1, TA.getNIF());
			r = ps.executeQuery();
			
			if(r.next()) {
				TA.setNombre_Apellidos(r.getString("Nombre") + " " + r.getString("Apellidos"));
				TA.setCorreo_electronico(r.getString("Correo_electronico"));
				TA.setPassword(r.getString("Contrasenia"));
			}
			
			connection.close();
			ps.close();
			r.close();
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return TA;
	}

	@Override
	public void create(TransferProfesor aTNew) {

		try {
			
			String s = "INSERT INTO usuarios (NIF, Nombre, Apellidos, Correo_electronico, Contrasenia) VALUES (?,?,?,?,?)";
			Connection connection = DriverManager.getConnection(url, login, password);
			PreparedStatement ps = connection.prepareStatement(s);
			
			ps.setString(1, aTNew.getNIF());
			ps.setString(2, aTNew.getNombre_Apellidos());
			ps.setString(3, aTNew.getNombre_Apellidos());
			ps.setString(4, aTNew.getCorreo_electronico());
			ps.setString(5, aTNew.getPassword());
			
			ps.executeUpdate();
		
			
		s = "INSERT INTO profesores (IdProfesor, NIF, IdAsignatura) VALUES (?,?,?)";

		ps = connection.prepareStatement(s);
		
		ps.setString(1, aTNew.getId());
		ps.setString(2, aTNew.getNIF());
		ps.setArray(3, null);
		ps.executeUpdate();
		
		connection.close();
		ps.close();
		
		}catch(Exception e) {
			
		}
		
	}

	@Override
	public TransferUsuario readByCorreo(String id) {
		TransferProfesor TP =null;
		try {
			
			String s = "SELECT * FROM usuarios WHERE correo_electronico = ?;";
			
			Connection connection = DriverManager.getConnection(url, login, password);
			PreparedStatement ps = connection.prepareStatement(s);
			ps.setString(1, id);
			
			ResultSet r = ps.executeQuery();
			
			if(r.next()) {
				TP = new TransferProfesor();
				TP.setNombre_Apellidos(r.getString("Nombre") + " " + r.getString("Apellidos"));
				TP.setCorreo_electronico(r.getString("Correo_electronico"));
				TP.setPassword(r.getString("Contrasenia"));
				TP.setNIF(r.getString("NIF"));
			}
			
			s = "SELECT * FROM profesores WHERE NIF = ?;";
			
			ps = connection.prepareStatement(s);
			
			ps.setString(1, TP.getNIF());
			r = ps.executeQuery();
			
			if(r.next()) {
				
				TP.setId(r.getString("IdProfesor"));
				
				ArrayList<String> asignaturas = new ArrayList<String>();
				
				String s2 = "SELECT IdAsignatura FROM profesores WHERE NIF = ?;";
				PreparedStatement ps2 = connection.prepareStatement(s2);
				ps2.setString(1, TP.getNIF());
				
				ResultSet r2 = ps2.executeQuery();
				
				while (r2.next()) {
					asignaturas.add(r2.getString("IdAsignatura"));
				}
				
				TP.setAsignaturas(asignaturas);
			}
			
			connection.close();
			ps.close();
			r.close();
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return TP;
	}

	@Override
	public void create(TransferUsuario aTNew) {
	TransferProfesor TP = new TransferProfesor();
		
		TP.setNIF(aTNew.getNIF());
		TP.setNombre_Apellidos(aTNew.getNombre_Apellidos());
		TP.setCorreo_electronico(aTNew.getCorreo_electronico());
		TP.setPassword(aTNew.getPassword());
		TP.setId("P005");
		
		
		this.create(TP);
	}

}
