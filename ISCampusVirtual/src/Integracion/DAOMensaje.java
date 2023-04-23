package Integracion;

import java.util.Date;

import Negocio.Foro.TransferMensaje;

public interface DAOMensaje {

	public TransferMensaje read(String idUsuario, Date date);

	public void create(TransferMensaje aTNew);

	public void eliminate(String idUsuario, Date date);
}
