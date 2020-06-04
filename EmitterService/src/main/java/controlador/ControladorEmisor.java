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
	private List<Mensaje> colaMensajes = new ArrayList<>();

	private ControladorEmisor() {
		super();
	}

	public static ControladorEmisor getInstance() {
		if (Objects.isNull(instance)) {
			instance = new ControladorEmisor();
		}
		return instance;
	}

	public Map<String, Boolean> enviarMensaje(Mensaje mensaje, List<Receptor> destinos) {
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

	private void sendMessage(Mensaje mensaje) throws IOException {
		Socket socket = null;
		Boolean resp = false;
		try {
			socket = new Socket(Config.getInstance().getIpServicioComunicacion(),
					Config.getInstance().getPuertoDestino());
		} catch (IOException e) {
			System.out.println("No se pudo establecer conexion al directorio original");
			e.printStackTrace();
			try {
				socket = new Socket(Config.getInstance().getIpDirectorioAux(),
						Config.getInstance().getPuertoDestino());
			} catch (IOException e2) {
				System.out.println("No se pudo establecer conexion al directorio alternativo");
				e2.printStackTrace();
			}
		}
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
				System.out.println("Error al obtener lista de contactos");
				e.printStackTrace();
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
			System.out.println("Servicio mensajes no disponible");
			e.printStackTrace();
			disponible = false;
		}
		return disponible;
	}

	public List<Mensaje> getColaMensajes() {
		return colaMensajes;
	}

	public void setColaMensajes(List<Mensaje> colaMensajes) {
		this.colaMensajes = colaMensajes;
	}
	
	public void addMensajesPendientes(Mensaje mensaje, List<Receptor> destinos) {
		List<Mensaje> mensajes = new ArrayList<>();
		destinos.stream().forEach(destino -> {
			Mensaje mensajeClon;
			try {
				mensajeClon = mensaje.clone();
				mensajeClon.setIpDestino(destino.getIp());
				mensajeClon.setReceptor(destino.getNombreUsuario());
				mensajes.add(mensajeClon);
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		});
		synchronized (colaMensajes) {
			this.colaMensajes.addAll(mensajes);
		}
	}
	
	public List<Mensaje> enviarMensajesPendientes() {
		List<Mensaje> mensajesRecibidos = new ArrayList<>();
		synchronized (colaMensajes) {
			colaMensajes.forEach(mensaje -> {
				try {
					sendMessage(mensaje);
					mensajesRecibidos.add(mensaje);
				} catch (Exception e) {
					System.out.println("Error al enviar mensajes en cola");
					e.printStackTrace();
				}
			});
			mensajesRecibidos.stream().forEach(mensaje -> {
				colaMensajes.remove(mensaje);
			});
		}
		return mensajesRecibidos;
	}

}
