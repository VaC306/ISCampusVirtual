package Integracion;

import Negocio.Aula.TransferTema;

public interface DAOTema {

	public TransferTema read(String idTema);

	public void create(TransferTema aTNew);

	public void eliminate(String idTema);

	public void updateName(String id, String nombre);
}