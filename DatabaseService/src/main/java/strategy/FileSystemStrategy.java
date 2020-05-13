package strategy;

import java.util.ArrayList;
import java.util.List;

import modelo.Mensaje;

public class FileSystemStrategy implements IPersistenciaStrategy {

	@Override
	public void persistirMensaje(Mensaje mensaje) {
	}

	@Override
	public List<Mensaje> getMensajes(String nombreReceptor) {
		return null;
	}

	@Override
	public void eliminaMensajes(String nombreReceptor) {
	}


}
