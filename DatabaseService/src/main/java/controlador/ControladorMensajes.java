package controlador;

import java.util.Objects;

public class ControladorMensajes {
	
	private static ControladorMensajes instance = null;
	
	public static synchronized ControladorMensajes getInstance() {
		if (Objects.isNull(instance)) {
			instance = new ControladorMensajes();
		}
		return instance;
	}
	
	public void iniciaServidor() {
		
	}
	
	public synchronized void enviaMensaje() {
		
	}
	
	public synchronized void getMensajes() {
		
	}
	
}
