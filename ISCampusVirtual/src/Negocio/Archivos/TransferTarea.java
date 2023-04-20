package Negocio.Archivos;
import java.util.Date;

import Negocio.Aula.TransferNota;

public class TransferTarea extends TransferArchivo {
	
	private Date Fecha_de_entrega;

	private TransferNota notas;

	public Date getFecha_de_entrega() {
		return Fecha_de_entrega;
	}

	public void setFecha_de_entrega(Date fecha_de_entrega) {
		Fecha_de_entrega = fecha_de_entrega;
	}

	public TransferNota getNotas() {
		return notas;
	}

	public void setNotas(TransferNota notas) {
		this.notas = notas;
	}
	
	

}
