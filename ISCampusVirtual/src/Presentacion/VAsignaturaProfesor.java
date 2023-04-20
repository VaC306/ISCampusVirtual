package Presentacion;

import javax.swing.JButton;

import Negocio.Aula.TransferAsignatura;
import Presentacion.Control.Events;

public class VAsignaturaProfesor extends VAsignatura{

	public VAsignaturaProfesor(TransferAsignatura data) {
		super(data);
	}
	@Override
	protected void anadirBotonEditar() {
		
		JButton editar= new JButton("EDITAR");
		editar.addActionListener(e->{
			
			//this.ctrl.accion(Events.ABRIR_VISTA_EDITAR_ASIGNATURA, this.asignatura);
		});
		
		ventana.add(editar);
	}
}
