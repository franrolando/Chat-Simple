package controlador;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import controlador.directorio.DirectorioImpl;
import controlador.servicioMensajes.ServicioMensajesImpl;
import modelo.Receptor;
import modelo.mensajes.Mensaje;

public class ControladorReceptor {

	private static ControladorReceptor instance;

	private ControladorReceptor() {
		super();
	}

	public static ControladorReceptor getInstance() {
		if (Objects.isNull(instance)) {
			instance = new ControladorReceptor();
		}
		return instance;
	}

	public Mensaje leerMensaje() {
		return ServicioMensajesImpl.getInstance().leerMensaje();
	}

	public List<Mensaje> getMensajesOffline(String nombreReceptor) throws IOException {
		return ServicioMensajesImpl.getInstance().getMensajesOffline(nombreReceptor);
	}

	public void sendStatus(Receptor receptor) {
		DirectorioImpl.getInstance().sendStatus(receptor);
	}

	public Boolean nombreValido(String nombre) throws IOException {
		return DirectorioImpl.getInstance().nombreValido(nombre);
	}

}
