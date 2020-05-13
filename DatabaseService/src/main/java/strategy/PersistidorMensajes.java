package strategy;

import java.util.List;

import modelo.Mensaje;

public class PersistidorMensajes {

	private IPersistenciaStrategy persistidor;
	
	public void persistirMensaje(Mensaje mensaje) {
		persistidor.persistirMensaje(mensaje);
	}
	
	public void eliminarMensajes(String nombreReceptor){
	}
	
	public void establecerDBStrategy() {
		this.persistidor = new DBStrategy();
	}
	
	public void establecerFSStrategy() {
		this.persistidor = new FileSystemStrategy();
	}
	
}
