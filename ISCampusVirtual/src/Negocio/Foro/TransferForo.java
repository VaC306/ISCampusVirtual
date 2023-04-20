package Negocio.Foro;
import java.util.ArrayList;
import java.util.List;

public class TransferForo {
	
	private int Numero_mensajes_totales=0;

	private List<TransferMensaje> mensaje = new ArrayList<TransferMensaje> ();

	public int getNumero_mensajes_totales() {
		return Numero_mensajes_totales;
	}

	public void setNumero_mensajes_totales(int numero_mensajes_totales) {
		Numero_mensajes_totales = numero_mensajes_totales;
	}

	public List<TransferMensaje> getMensaje() {
		return mensaje;
	}

	public void addMensaje(List<TransferMensaje> mensaje, TransferMensaje m) {
		this.mensaje.add(m);
	}
	
}
