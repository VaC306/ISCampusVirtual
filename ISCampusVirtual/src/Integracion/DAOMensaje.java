package Integracion;

import java.util.Date;

import Negocio.Foro.TransferMensaje;

public interface DAOMensaje {

	public TransferMensaje read(String idMensaje);

	public void create(TransferMensaje aTNew);

	public void eliminate(String idMensaje);
	
	public int count();
}
