package controlador;

import java.util.List;

import modelo.Receptor;

public class ControladorDirectorio {
	
	private static Integer puertoEmisores = 9000;
	private static Integer puertoReceptores = 9010;
	private List<Receptor> receptores;
	
	public void initDirectorio() {
		
	}
	
	public List<Receptor> getReceptores() {
		return receptores;
	}
	
	public void actualizarEstado(Receptor receptor) {
		
	}
	
}
