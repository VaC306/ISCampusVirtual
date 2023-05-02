package Negocio.Archivos;

import Integracion.DAOTarea;
import Integracion.DAOTareaImpl;

public class SATarea {

	public static boolean eliminateById(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	public static boolean add(TransferTarea tTarea) {
		tTarea.setId("TAR00"+"");
		tTarea.setIdTarea("T00"+ "");
		
		
		if(tTarea != null) {
			return true;
		}
		else return false;
	}

}
