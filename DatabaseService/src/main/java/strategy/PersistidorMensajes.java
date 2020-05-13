package strategy;

import java.util.List;

import Configuration.Config;
import modelo.Mensaje;

public class PersistidorMensajes {

	private IPersistenciaStrategy persistidor;
	
	public void persistirMensaje(Mensaje mensaje) {
		persistidor.persistirMensaje(mensaje);
	}
	
	public List<Mensaje> eliminarMensajes(String nombreReceptor){
		return persistidor.eliminarMensajes(nombreReceptor);
	}
	
	public void establecerDBStrategy() {
		this.persistidor = new DBStrategy();
	}
	
	public void establecerFSStrategy() {
		this.persistidor = new FileSystemStrategy();
	}
	
}
