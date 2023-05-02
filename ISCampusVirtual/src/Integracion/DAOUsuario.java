package Integracion;

import Negocio.Usuario.TransferUsuario;

public interface DAOUsuario {

	public abstract void eliminate(String id);

	public abstract TransferUsuario readById(String id);

	public abstract TransferUsuario readByCorreo(String id);

	public abstract void create(TransferUsuario aTNew);


}
