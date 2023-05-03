package Negocio.Archivos;

import Integracion.DAOApuntes;
import Integracion.DAOApuntesImpl;
import Negocio.Usuario.TransferUsuario;

public class SAApuntes {
	//TODO
	public void createApuntesconCreador(TransferApuntes transferApuntes, TransferUsuario tUsuarioIniciado) {
		transferApuntes.setUsuario(tUsuarioIniciado.getNIF());
		DAOApuntes dao = new DAOApuntesImpl();
		dao.create(transferApuntes);
	
	}

	public boolean eliminarApuntes(String idApuntes1) {
		DAOApuntes dao = new DAOApuntesImpl();
		if (!idApuntes1.equals(null)) {
			dao.eliminate(idApuntes1);
			return true;
		}else
			return false;
	}

}
