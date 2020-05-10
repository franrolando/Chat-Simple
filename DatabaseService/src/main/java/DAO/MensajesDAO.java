package DAO;

import java.util.ArrayList;
import java.util.List;

import modelo.Mensaje;
import strategy.PersistidorMensajes;

public class MensajesDAO {
	
	private PersistidorMensajes persistidor;
	
	public synchronized void insertarMensaje(Mensaje mensaje) {
		persistidor.persistirMensaje(mensaje);
	}
	
	public synchronized List<Mensaje> eliminarMensajes(String nombreReceptor) {
		List<Mensaje> mensajesPendientes = new ArrayList<>();
		mensajesPendientes = persistidor.eliminarMensajes(nombreReceptor);
		return mensajesPendientes;
	}

}
