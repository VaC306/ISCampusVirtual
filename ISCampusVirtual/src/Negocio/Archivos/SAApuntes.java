package Negocio.Archivos;

import Integracion.DAOApuntes;
import Negocio.Usuario.TransferUsuario;

public class SAApuntes {
	//TODO
	public static void createApuntesconCreador(TransferApuntes transferApuntes, TransferUsuario tUsuarioIniciado) {
		transferApuntes.setId(null);
		transferApuntes.setIdApuntes(null);
		transferApuntes.setUsuario(tUsuarioIniciado);
		
		
	}

}
