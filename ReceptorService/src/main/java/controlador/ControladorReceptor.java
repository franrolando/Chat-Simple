package controlador;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import config.ConfigReceptor;
import modelo.Cifrador;
import modelo.Mensaje;
import modelo.Receptor;

public class ControladorReceptor {

	private static ControladorReceptor instance;

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
			ServerSocket serverSocket = new ServerSocket(ConfigReceptor.getInstance().getPuertoMensajes());
			mensaje = (Mensaje) new ObjectInputStream(serverSocket.accept().getInputStream()).readObject();
			Cifrador cf = new Cifrador();
			cf.descifrarMensaje(mensaje);
			serverSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mensaje;
	}

	public List<Mensaje> getMensajesOffline(String nombreReceptor) throws IOException {
		List<Mensaje> mensajesOffline = new ArrayList<>();
		ObjectOutputStream out;
		ObjectInputStream in;
		try {
			Socket socket = new Socket(ConfigReceptor.getInstance().getIpServicioComunicacion(),
					ConfigReceptor.getInstance().getPuertoMsjOffline());
			out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(nombreReceptor);
			in = new ObjectInputStream(socket.getInputStream());
			mensajesOffline = (List<Mensaje>) in.readObject();
			socket.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return mensajesOffline;
	}

	public void sendStatus(Receptor receptor) {
		Socket socket = null;
		try {
			socket = new Socket(ConfigReceptor.getInstance().getIpDirectorio(), ConfigReceptor.getInstance().getPuertoEstado());
		} catch (IOException e) {
			e.printStackTrace();
			try {
				socket = new Socket(ConfigReceptor.getInstance().getIpDirectorioAux(), ConfigReceptor.getInstance().getPuertoEstado());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		ObjectOutputStream os;
		try {
			os = new ObjectOutputStream(socket.getOutputStream());
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
		socket = new Socket(ConfigReceptor.getInstance().getIpDirectorio(), ConfigReceptor.getInstance().getPuertoEstado());
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
