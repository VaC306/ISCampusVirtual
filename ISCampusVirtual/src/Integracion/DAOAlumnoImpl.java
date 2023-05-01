package Integracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Negocio.Usuario.TransferAlumno;
import Negocio.Usuario.TransferUsuario;

public class DAOAlumnoImpl implements DAOAlumno{
	
	static String bd = "campus_virtual";
	static String login = "root";
	static String password = "grupo7";
	static String url = "jdbc:mysql://127.0.0.1:3306/" + bd ;
	
	
	@Override
	public void eliminate(String id) {
		
		try {		
			
			String s = "DELETE FROM alumnos WHERE NIF = ?";
			 
			Connection connection = DriverManager.getConnection(url, login, password);
			PreparedStatement ps = connection.prepareStatement(s);
			ps.setString(1, id);
			ps.executeUpdate();
			 
			s = "DELETE FROM usuarios WHERE NIF = ?";
			ps = connection.prepareStatement(s);
			
			ps.setString(1,id);
			ps.executeUpdate();
			
			connection.close();
			ps.close();
			
		}catch(Exception e) {
		 System.out.println(e.getMessage());
		}
		
	}
	
	@Override
	public TransferUsuario readById(String id) {
		TransferAlumno TA =null;
		try {
			
			String s = "SELECT * FROM alumnos WHERE NIF = ?;" ;
			Connection connection = DriverManager.getConnection(url, login, password);
			PreparedStatement ps = connection.prepareStatement(s);
			ps.setString(1,  id);
			
			ResultSet r = ps.executeQuery();
			
			if(r.next()) {
				TA = new TransferAlumno();
				TA.setId(r.getString("IdAlumno"));
				TA.setDelegado(r.getBoolean("Delegado"));
				TA.setNIF(r.getString("NIF"));
				ArrayList asignaturas = new ArrayList<>();
				
				String s2 = "SELECT IdAsignatura FROM alumnos WHERE NIF = ?;";
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
	public void create(TransferAlumno aTNew) {
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
			
			s = "INSERT INTO alumnos (IdAlumno, NIF, Delegado, IdAsignatura) VALUES (?,?,?,?)";

			ps = connection.prepareStatement(s);
			
			ps.setString(1, aTNew.getId());
			ps.setString(2, aTNew.getNIF());
			ps.setBoolean(3, aTNew.isDelegado());
			ps.setString(4, "A001");
			ps.executeUpdate();
			
			connection.close();
			ps.close();
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public TransferUsuario readByCorreo(String id) {
		TransferAlumno TA =null;
		try {
			
			String s = "SELECT * FROM usuarios WHERE correo_electronico = ?;" ;
			Connection connection = DriverManager.getConnection(url, login, password);
			PreparedStatement ps = connection.prepareStatement(s);
			ps.setString(1,  id);
			
			ResultSet r = ps.executeQuery();
			
			if(r.next()) {
				TA = new TransferAlumno();
				TA.setNIF(r.getString("NIF"));
				TA.setNombre_Apellidos(r.getString("Nombre") + " " + r.getString("Apellidos"));
				TA.setCorreo_electronico(r.getString("Correo_electronico"));
				TA.setPassword(r.getString("Contrasenia"));
			}
			
			s = "SELECT * FROM alumnos WHERE NIF = ?;";
			
			ps = connection.prepareStatement(s);
			
			ps.setString(1, TA.getNIF());
			r = ps.executeQuery();
			
			if(r.next()) {
				TA.setId(r.getString("IdAlumno"));
				TA.setDelegado(r.getBoolean("Delegado"));
				ArrayList asignaturas = new ArrayList<>();
				
				String s2 = "SELECT IdAsignatura FROM alumnos WHERE NIF = ?;";
				PreparedStatement ps2 = connection.prepareStatement(s2);
				ps2.setString(1, TA.getNIF());
				
				ResultSet r2 = ps2.executeQuery();
				
				while (r2.next()) {
					DAOAsignatura dao = new DAOAsignaturaImpl();
					asignaturas.add(r2.getString("IdAsignatura"));
				}
				
				TA.setAsignaturas(asignaturas);
				ps2.close();
				r2.close();
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
	public void create(TransferUsuario aTNew) {
		
		TransferAlumno TA = new TransferAlumno();
		
		TA.setNIF(aTNew.getNIF());
		TA.setNombre_Apellidos(aTNew.getNombre_Apellidos());
		TA.setCorreo_electronico(aTNew.getCorreo_electronico());
		TA.setPassword(aTNew.getPassword());
		TA.setId("AL005");
		
		
		this.create(TA);
		
		
	}
}
