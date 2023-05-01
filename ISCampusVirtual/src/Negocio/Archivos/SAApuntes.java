package Negocio.Archivos;

import Integracion.DAOApuntes;
import Negocio.Usuario.TransferUsuario;

public class SAApuntes {
	//TODO
	public void createApuntesconCreador(TransferApuntes transferApuntes, TransferUsuario tUsuarioIniciado) {
		transferApuntes.setId(null);
		transferApuntes.setIdApuntes(null);
		transferApuntes.setUsuario(tUsuarioIniciado);
		
		
	}

	public boolean eliminarApuntes(String idApuntes1) {
		// TODO Auto-generated method stub
		return false;
	}

}
