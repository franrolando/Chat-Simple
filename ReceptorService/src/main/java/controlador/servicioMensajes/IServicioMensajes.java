package controlador.servicioMensajes;

import java.io.IOException;
import java.util.List;

import modelo.mensajes.Mensaje;

public interface IServicioMensajes {

	Mensaje leerMensaje();
	List<Mensaje> getMensajesOffline(String nombreReceptor) throws IOException;

}
