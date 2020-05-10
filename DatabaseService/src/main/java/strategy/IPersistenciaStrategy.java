package strategy;

import java.util.List;

import modelo.Mensaje;

public interface IPersistenciaStrategy {
	
	Mensaje persistirMensaje(Mensaje mensaje);
	List<Mensaje> eliminarMensajes(String nombreReceptor);
}
