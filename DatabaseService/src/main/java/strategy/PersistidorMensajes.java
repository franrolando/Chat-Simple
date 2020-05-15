package strategy;

import java.util.List;

import Configuration.Config;
import modelo.Mensaje;

public class PersistidorMensajes implements IPersistenciaStrategy {

	private IPersistenciaStrategy persistidor;
	
	public void persistirMensaje(Mensaje mensaje) {
		persistidor.persistirMensaje(mensaje);
	}
	
	@Override
	public List<Mensaje> getMensajes(String nombreReceptor) {
		return persistidor.getMensajes(nombreReceptor);
	}
	
	@Override
	public void eliminaMensajes(String nombreReceptor) {
		persistidor.eliminaMensajes(nombreReceptor);
	}
	
	public void establecerDBStrategy() {
		this.persistidor = new DBStrategy();
	}
	
	public void establecerFSStrategy() {
		this.persistidor = new FileSystemStrategy();
	}

}
