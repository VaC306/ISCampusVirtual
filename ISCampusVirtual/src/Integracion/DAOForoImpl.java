package Integracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Negocio.Foro.TransferForo;
import Negocio.Foro.TransferMensaje;

public class DAOForoImpl implements DAOForo{
	
	static String bd = "campus_virtual";
	static String login = "root";
	static String password = "grupo7";
	static String url = "jdbc:mysql://127.0.0.1:3306/" + bd;
	

	@Override
	public TransferForo read(String idAsignatura) {
		TransferForo TF = null;
		try {
			String s = "SELECT * FROM foros WHERE IdAsignatura = ?;";
			Connection connection = DriverManager.getConnection(url, login, password);
			PreparedStatement ps = connection.prepareStatement(s);
			ps.setString(1, idAsignatura);
			
			ResultSet r = ps.executeQuery();
			
			if(r.next()) {
				TF = new TransferForo();
				List<TransferMensaje> LTM = new ArrayList<TransferMensaje>();
				TF.setNumero_mensajes_totales(r.getInt("Num_mensajes"));
				TF.setId(r.getString("IdForo"));
				String s2 = "SELECT IdMensaje FROM mensajes WHERE IdForo = ?;";
				PreparedStatement ps2 = connection.prepareStatement(s2);
				ps2.setString(1, r.getString("IdForo"));
				
				ResultSet r2 = ps2.executeQuery();
				
				while(r2.next()) {
					
					DAOMensaje daoM = new DAOMensajeImpl();
					System.out.println(r2.getString("IdMensaje"));
					TF.addMensaje(LTM, daoM.read(r2.getString("IdMensaje")));
					System.out.println(TF.getMensaje().get(0).getCuerpo());
				}
				ps2.close();
				r2.close();
			}
			
			ps.close();
			r.close();
			connection.close();
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return TF;
	}

	@Override
	public void create(TransferForo aTNew) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminate(String idForo) {
		// TODO Auto-generated method stub
		
	}

}
