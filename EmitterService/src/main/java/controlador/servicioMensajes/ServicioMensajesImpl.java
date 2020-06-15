package controlador.servicioMensajes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import config.ConfigEmitter;
import modelo.Cifrador;
import modelo.Receptor;
import modelo.mensajes.Mensaje;

public class ServicioMensajesImpl implements IServicioMensajes {
	
	private static ServicioMensajesImpl instance = null;

	private ServicioMensajesImpl() {
		super();
	}
	
	public static ServicioMensajesImpl getInstance() {
		if (Objects.isNull(instance)) {
			instance = new ServicioMensajesImpl();
		}
		return instance;
	}

	public Map<String, Boolean> enviarMensaje(Mensaje mensaje, List<Receptor> destinos) {
		Cifrador cf = new Cifrador();
		cf.cifrarMensaje(mensaje);
		Map<String, Boolean> mensajesRecibidos = new HashMap<>();
		destinos.stream().forEach(destino -> {
			mensaje.setIpDestino(destino.getIp());
			mensaje.setReceptor(destino.getNombreUsuario());
			try {
				sendMessage(mensaje);
				mensajesRecibidos.put(destino.getNombreUsuario(), true);
			} catch (Exception e) {
				mensajesRecibidos.put(destino.getNombreUsuario(), false);
				System.out.println("No se pudo mandar el mensaje");
				e.printStackTrace();
			}
		});
		return mensajesRecibidos;
	}
	
	@Override
	public void sendMessage(Mensaje mensaje) throws IOException {
		Socket socket = null;
		Boolean resp = false;
		socket = new Socket(ConfigEmitter.getInstance().getIpServicioComunicacion(), ConfigEmitter.getInstance().getPuertoDestino());
		if (socket != null) {
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject("envioMensaje");
			out.writeObject(mensaje);
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			try {
				resp = (Boolean) in.readObject();
			} catch (ClassNotFoundException | IOException e) {
				System.out.println("Error al leer desde el servicio de mensajes");
				e.printStackTrace();
			}
			out.close();
			socket.close();
		}
		if (!resp) {
			throw new IOException();
		}
	}
	

	@Override
	public Boolean servicioEnvioDisponible() {
		Boolean disponible = true;
		try {
			Socket socket = new Socket(ConfigEmitter.getInstance().getIpServicioComunicacion(),
					ConfigEmitter.getInstance().getPuertoDestino());
			new ObjectOutputStream(socket.getOutputStream()).writeObject("disponible");
			socket.close();
		} catch (IOException e) {
			System.out.println("Servicio mensajes no disponible");
			e.printStackTrace();
			disponible = false;
		}
		return disponible;
	}

}
