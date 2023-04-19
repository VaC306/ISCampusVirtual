package model.usuario;

public abstract class SAUsuario {
	
	public boolean iniciarSesion(String password,String id ) {
		
		TransferUsuario transfer= FactoriaUsuario.getInstance().crearTransfer(id);
		
		if(transfer!=null && transfer.getPassword()==password) {
			
			return true;
		}
		
		return false;
	}
	
	public boolean eliminarUsuario(String id) {
		
		DAOUsuario dao= FactoriaUsuario.getInstance().crearDAO(id);
		TransferUsuario transfer= FactoriaUsuario.getInstance().crearTransfer(id);

		//si no existe no se elimina
		if(transfer!=null) {
			
			dao.eliminate(id);
			
			return true;
		}
		
		return false;	
	}
	
	public boolean crearUsuario (TransferUsuario aTNew) {
		
		DAOUsuario dao= FactoriaUsuario.getInstance().crearDAO(aTNew.getId());
		TransferUsuario transfer= FactoriaUsuario.getInstance().crearTransfer(aTNew.getId());

		//como no existe se añade a la bd
		if(transfer==null) {
			
			dao.create(aTNew);
			
			return true;
		}
		return false;
	}
	
	public boolean editarUsuario (TransferUsuario aTNew) {
		
		DAOUsuario dao= FactoriaUsuario.getInstance().crearDAO(aTNew.getId());
		TransferUsuario transfer= FactoriaUsuario.getInstance().crearTransfer(aTNew.getId());

		//como no existe se edita la bd
		if(transfer==null) {
			
			dao.eliminate(aTNew.getId());
			dao.create(aTNew);
			
			return true;
		}
		return false;
	}	
}
