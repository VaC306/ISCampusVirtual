package Integracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import Negocio.Usuario.TransferAlumno;
import Negocio.Usuario.TransferUsuario;

public interface DAOAlumno extends DAOUsuario {
	
	//public abstract TransferAlumno readById(String id);

	public void createStudent(TransferAlumno aTNew, String asignatura);
	public int count();
}
