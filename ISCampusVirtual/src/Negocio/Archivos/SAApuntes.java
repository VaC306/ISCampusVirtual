package Negocio.Archivos;

import Integracion.DAOApuntes;
import Integracion.DAOApuntesImpl;
import Negocio.Usuario.TransferUsuario;

public class SAApuntes {
	//TODO
	public void createApuntesconCreador(TransferApuntes transferApuntes, TransferUsuario tUsuarioIniciado) {
		transferApuntes.setId("AAR00" + "");
		transferApuntes.setIdApuntes("AP00"+"");
		transferApuntes.setUsuario(tUsuarioIniciado);
		DAOApuntes dao = new DAOApuntesImpl();
		dao.create(transferApuntes);
	
	}

	public boolean eliminarApuntes(String idApuntes1) {
		// TODO Auto-generated method stub
		return false;
	}

}
