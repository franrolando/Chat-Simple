package controlador;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import config.Config;
import modelo.Cifrador;
import modelo.Mensaje;
import modelo.Receptor;

public class ControladorReceptor {

	private static ControladorReceptor instance;
	private static ServerSocket serverSocketMensajes = null;

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
		Mensaje mensaje = null;
		try {
			mensaje = (Mensaje) new ObjectInputStream(serverSocketMensajes.accept().getInputStream()).readObject();
			Cifrador cf = new Cifrador();
			cf.descifrarMensaje(mensaje);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mensaje;
	}

	public List<Mensaje> getMensajesOffline(String nombreReceptor) {
		List<Mensaje> mensajesOffline = new ArrayList<>();
		ObjectOutputStream out;
		ObjectInputStream in;
		try {
			Socket socket = new Socket(Config.getInstance().getIpServicioComunicacion(),
					Config.getInstance().getPuertoMsjOffline());
			out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(nombreReceptor);
			in = new ObjectInputStream(socket.getInputStream());
			mensajesOffline = (List<Mensaje>) in.readObject();
			socket.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return mensajesOffline;
	}

	public static void instanciarSocketServer() {
		try {
			serverSocketMensajes = new ServerSocket(Config.getInstance().getPuertoMensajes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendStatus(Receptor receptor) {
		try {
			Socket socket = new Socket(Config.getInstance().getIpDirectorio(), Config.getInstance().getPuertoEstado());
			ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
			os.writeObject("estado");
			os.writeObject(receptor);
			os.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Boolean nombreValido(String nombre) throws IOException {
		Boolean valido = true;
		List<Receptor> contactList = new ArrayList<>();
		Socket socket = null;
		ObjectInputStream is = null;
		socket = new Socket(Config.getInstance().getIpDirectorio(), Config.getInstance().getPuertoEstado());
		new ObjectOutputStream(socket.getOutputStream()).writeObject("nombreValido");
		is = new ObjectInputStream(socket.getInputStream());
		if (socket != null && is != null) {
			try {
				contactList = (List<Receptor>) is.readObject();
				valido = !contactList.stream()
						.anyMatch(receptor -> receptor.getNombreUsuario().equals(nombre) && receptor.getConectado());
				is.close();
				socket.close();
			} catch (IOException | ClassNotFoundException e) {
				valido = false;
			}
		}
		return valido;
	}

}
