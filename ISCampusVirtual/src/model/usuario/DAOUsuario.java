package model.usuario;

public abstract class DAOUsuario {

	public abstract TransferUsuario readById(String id);
	
	public abstract void create(TransferUsuario aTNew);

	public abstract void eliminate(String id);

}
