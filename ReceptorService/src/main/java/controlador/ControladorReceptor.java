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
import modelo.Mensaje;
import modelo.Receptor;

public class ControladorReceptor {

	private static ControladorReceptor instance;
	private static ServerSocket socket = null;

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
			mensaje = (Mensaje) new ObjectInputStream(socket.accept().getInputStream()).readObject();
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
			socket = new ServerSocket(Config.getInstance().getPuertoMensajes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendStatus(Receptor receptor) {
		try {
			Socket socket = new Socket(Config.getInstance().getIpDirectorio(), Config.getInstance().getPuertoEstado());
			ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
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
		Socket echoSocket = null;
		ObjectInputStream is = null;
		echoSocket = new Socket(Config.getInstance().getIpDirectorio(), Config.getInstance().getPuertoNombreValido());
		is = new ObjectInputStream(echoSocket.getInputStream());
		if (echoSocket != null && is != null) {
			try {
				contactList = (List<Receptor>) is.readObject();
				valido = !contactList.stream()
						.anyMatch(receptor -> receptor.getNombreUsuario().equals(nombre) && receptor.getConectado());
				is.close();
				echoSocket.close();
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
				valido = false;
			}
		}
		return valido;
	}

}
