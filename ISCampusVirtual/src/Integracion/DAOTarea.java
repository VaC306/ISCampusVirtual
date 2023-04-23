package Integracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Negocio.Archivos.TransferTarea;
import Negocio.Aula.EnumCurso;
import Negocio.Aula.TransferCurso;

public interface DAOTarea {
	
	public TransferTarea read(String IdTarea);
	public void create(TransferTarea aTNew);
	public void eliminate(String IdTarea);
	
}
