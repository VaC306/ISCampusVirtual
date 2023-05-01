package Negocio.Archivos;
import java.util.Date;

import Integracion.DAOTarea;
import Integracion.DAOTareaImpl;
import Negocio.Aula.TransferNota;

public class TransferTarea extends TransferArchivo {
	
	private Date Fecha_de_entrega;

	private TransferNota notas;
	
	private String IdTarea;
	
	public TransferTarea(String IdTarea, String IdArchivo, Date fecha) {
		this.IdTarea = IdTarea;
		this.Id = IdArchivo;
		this.Fecha_de_entrega = fecha;
	}

	public TransferTarea() {
	}

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
	
	public String getIdTarea() {
		return IdTarea;
	}
	
	public void setIdTarea(String t) {
		this.IdTarea = t;
	}

	@Override
	public boolean matchFile(String idArchivo) {
		return idArchivo.charAt(0) == 'T';
	}

	@Override
	public TransferArchivo read(String idArchivo) {
		DAOTarea dao = new DAOTareaImpl();
		return dao.read(idArchivo);
	}
	
	

}
