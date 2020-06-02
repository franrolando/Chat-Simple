package controlador;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import config.Config;
import modelo.Mensaje;
import modelo.Receptor;

public class ControladorEmisor {

	private static ControladorEmisor instance;

	private ControladorEmisor() {
		super();
	}

	public static ControladorEmisor getInstance() {
		if (Objects.isNull(instance)) {
			instance = new ControladorEmisor();
		}
		return instance;
	}

	public void enviarMensaje(Mensaje mensaje, List<Receptor> destinos) {
		destinos.stream().forEach(destino -> {
			mensaje.setIpDestino(destino.getIp());
			mensaje.setReceptor(destino.getNombreUsuario());
			try {
				sendMessage(mensaje);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	public Map<String, Boolean> enviarMensajeAvisoRecepcion(Mensaje mensaje, List<Receptor> destinos) {
		Map<String, Boolean> mensajesRecibidos = new HashMap<>();
		destinos.stream().forEach(destino -> {
			mensaje.setIpDestino(destino.getIp());
			mensaje.setReceptor(destino.getNombreUsuario());
			try {
				sendMessage(mensaje);
				mensajesRecibidos.put(destino.getNombreUsuario(), true);
			} catch (Exception e) {
				mensajesRecibidos.put(destino.getNombreUsuario(), false);
				e.printStackTrace();
			}
		});
		return mensajesRecibidos;
	}

	private void sendMessage(Mensaje mensaje) throws IOException {
		Socket socket = new Socket(Config.getInstance().getIpServicioComunicacion(),
				Config.getInstance().getPuertoDestino());
		ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
		out.writeObject("envioMensaje");
		out.writeObject(mensaje);
		ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
		Boolean resp = null;
		try {
			resp = (Boolean) in.readObject();
		} catch (ClassNotFoundException | IOException e) {

		}
		out.close();
		socket.close();
		if (!resp) {
			throw new IOException();
		}
	}

	public List<Receptor> getContactList() throws IOException {
		List<Receptor> contactList = new ArrayList<>();
		Socket echoSocket = null;
		ObjectInputStream is = null;
		echoSocket = new Socket(Config.getInstance().getIpDirectorio(), Config.getInstance().getPuertoContacto());
		is = new ObjectInputStream(echoSocket.getInputStream());
		if (echoSocket != null && is != null) {
			try {
				contactList = (List<Receptor>) is.readObject();
				is.close();
				echoSocket.close();
			} catch (IOException | ClassNotFoundException e) {

			}
		}
		return contactList;
	}
	
	public Boolean servicioEnvioDisponible() {
		Boolean disponible = true;
		try {
			Socket socket = new Socket(Config.getInstance().getIpServicioComunicacion(),
					Config.getInstance().getPuertoDestino());
			new ObjectOutputStream(socket.getOutputStream()).writeObject("disponible");
			socket.close();
		} catch (IOException e) {
			disponible = false;
		}
		return disponible;
	}

}
