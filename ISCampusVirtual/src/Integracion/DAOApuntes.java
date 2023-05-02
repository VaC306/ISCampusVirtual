package Integracion;

import Negocio.Archivos.TransferApuntes;

public interface DAOApuntes extends DAOArchivo{
	public void create(TransferApuntes aTNew);
}
