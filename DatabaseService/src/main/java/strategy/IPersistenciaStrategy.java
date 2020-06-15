package strategy;

import java.util.List;

import modelo.mensajes.Mensaje;

public interface IPersistenciaStrategy {
	
	void persistirMensaje(Mensaje mensaje);
	List<Mensaje> getMensajes(String nombreReceptor);
	
}
