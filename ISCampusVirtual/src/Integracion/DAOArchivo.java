package Integracion;

import Negocio.Archivos.TransferArchivo;
import Negocio.Aula.TransferTema;

public interface DAOArchivo {
	
	public TransferArchivo read(String titulo);

	public void eliminate(TransferTema tema, String titulo);
	public abstract int num();
}
