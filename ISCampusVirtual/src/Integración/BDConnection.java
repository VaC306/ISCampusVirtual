package Integración;
import java.sql.*;
public class BDConnection {
	
	static String bd = "campus_virtual";
	static String login = "root";
	static String password = "grupo7";
	static String url = "jdbc:mysql://localhost/" + bd;
	
	Connection con = null;
	
	public BDConnection() {
		try {
			con = DriverManager.getConnection(url, login, password);
		}
		catch(SQLException e) {
			con = null;
			e.printStackTrace();
			System.out.println (" SQLException : " + e. getMessage ());
			System.out.println (" SQLState : " + e. getSQLState ());
			System.out.println (" VendorError : " + e. getErrorCode ());
		}
	}
	
	public Connection getConnection(){
		return con;
	}
	
	public void desconectar(){
		con= null;
	}
}