package strategy;

import java.util.ArrayList;
import java.util.List;

import modelo.Mensaje;

public class DBStrategy implements IPersistenciaStrategy {

	@Override
	public void persistirMensaje(Mensaje mensaje) {
		
	}

	@Override
	public List<Mensaje> eliminarMensajes(String nombreReceptor) {
		List<Mensaje> mensajesPendientes = new ArrayList<>();
		return mensajesPendientes;
	}

}
