package Integracion;

import Negocio.Aula.EnumCurso;
import Negocio.Aula.TransferClase;

public interface DAOClase {

	public TransferClase read(EnumCurso enumCurso, String letra);
	
	public void create(EnumCurso enumCurso, TransferClase aTNew);

	public void eliminate(EnumCurso enumCurso, String letra);

}
