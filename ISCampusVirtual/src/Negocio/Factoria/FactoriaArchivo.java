package Negocio.Factoria;

import java.util.Arrays;
import java.util.List;

import Negocio.Archivos.TransferApuntes;
import Negocio.Archivos.TransferArchivo;
import Negocio.Archivos.TransferTarea;
import Negocio.Usuario.TransferAlumno;
import Negocio.Usuario.TransferProfesor;
import Negocio.Usuario.TransferUsuario;

public class FactoriaArchivo {
	
	private static FactoriaArchivo instancia;
	
	private static final List<TransferArchivo> AVAILABLE_FILES = Arrays.asList(
			
			new TransferApuntes(),
			new TransferTarea()	
			
		);
	
	public static FactoriaArchivo getInstance() {
		if (instancia == null) {
			instancia = new FactoriaArchivo();
		}
		return instancia;
	}
	
	public TransferArchivo createTransferById(String idArchivo) {
		
		TransferArchivo transfer = null;
		for (TransferArchivo TA: AVAILABLE_FILES) {
			if(TA.matchFile(idArchivo)) {
				transfer = TA.read(idArchivo);
			}
		}
		
		return transfer;
	}

}
