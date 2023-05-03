package Negocio.Archivos;

import Integracion.DAOTarea;
import Integracion.DAOTareaImpl;

public class SATarea {

	public static boolean eliminateById(String id) {
		DAOTarea daoT = new DAOTareaImpl();
		daoT.eliminate(id);
		return true;
	}

	public static boolean add(TransferTarea tTarea) {		
		
		if(tTarea != null) {
			return true;
		}
		else return false;
	}

}
