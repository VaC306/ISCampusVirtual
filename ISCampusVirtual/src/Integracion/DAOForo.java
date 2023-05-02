package Integracion;

import java.util.Date;

import Negocio.Foro.TransferForo;
import Negocio.Foro.TransferMensaje;

public interface DAOForo {

	public TransferForo read(String idForo);

	public void create(TransferForo aTNew);

	public void eliminate(String idForo);
}
