package Integracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Negocio.Archivos.TransferArchivo;
import Negocio.Archivos.TransferTarea;
import Negocio.Aula.EnumCurso;
import Negocio.Aula.TransferCurso;

public interface DAOTarea extends DAOArchivo{
	
	public void create(TransferTarea aTNew);
	public void eliminate(String IdTarea);
	
}
