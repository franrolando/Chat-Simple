package strategy;

import java.util.ArrayList;
import java.util.List;

import modelo.Mensaje;

public class FileSystemStrategy implements IPersistenciaStrategy {

	@Override
	public Mensaje persistirMensaje(Mensaje mensaje) {
		return null;
	}

	@Override
	public List<Mensaje> eliminarMensajes(String nombreReceptor) {
		List<Mensaje> mensajesPendientes = new ArrayList<>();
		return mensajesPendientes;
	}

}
