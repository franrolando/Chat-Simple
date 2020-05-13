package DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import Configuration.Config;
import modelo.Mensaje;
import strategy.PersistidorMensajes;

public class MensajesDAO {

	private PersistidorMensajes persistidor = new PersistidorMensajes();
	
	private static MensajesDAO instance = null;

	private MensajesDAO() {
		switch (Config.getInstance().getStrategy().toLowerCase()) {
		case "filesystem":
			persistidor.establecerFSStrategy();
			break;
		case "database":
			persistidor.establecerDBStrategy();
			break;
		default:
		}
	}
	
	public static synchronized MensajesDAO getInstance() {
		if (Objects.isNull(instance)) {
			instance = new MensajesDAO();
		}
		return instance;
	}

	public synchronized void insertarMensaje(Mensaje mensaje) {
		persistidor.persistirMensaje(mensaje);
	}

	public synchronized List<Mensaje> eliminarMensajes(String nombreReceptor) {
		List<Mensaje> mensajesPendientes = new ArrayList<>();
		mensajesPendientes = persistidor.eliminarMensajes(nombreReceptor);
		return mensajesPendientes;
	}

}
