package controlador.servicioMensajes;

import java.io.IOException;

import modelo.mensajes.Mensaje;

public interface IServicioMensajes {

	Boolean servicioEnvioDisponible();
	void sendMessage(Mensaje mensaje) throws IOException;
	
}
