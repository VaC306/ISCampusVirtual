package Integracion;

import Negocio.Aula.TransferAsignatura;

public interface DAOAsignatura {

	public TransferAsignatura read(String id);

	public void create(TransferAsignatura tAnew);

	public void eliminate(String id);
	
}
