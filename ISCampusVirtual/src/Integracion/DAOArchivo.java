package Integracion;

import Negocio.Archivos.TransferArchivo;
import Negocio.Aula.TransferTema;

public interface DAOArchivo {
	
	public TransferArchivo read(TransferTema tema, String titulo);

	public void create(TransferArchivo aTNew);

	public void eliminate(TransferTema tema, String titulo);
	
}
