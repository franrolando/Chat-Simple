package controlador;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;

import Configuration.Config;
import modelo.Receptor;

public class ControladorDirectorio {

	private static ControladorDirectorio instance = null;
	private Map<String, Receptor> receptores = new TreeMap<>();
	private static ServerSocket serverEmisores;
	private static ServerSocket serverReceptores;

	private ControladorDirectorio() {
		super();
	}

	public static ControladorDirectorio getInstance() {
		if (Objects.isNull(instance)) {
			synchronized (ControladorDirectorio.class) {
				if (Objects.isNull(instance)) {
					instance = new ControladorDirectorio();
				}
			}
		}
		return instance;
	}

	public static void initDirectorio() {
		try {
			serverEmisores = new ServerSocket(Config.getInstance().getPuertoEmisores());
			serverReceptores = new ServerSocket(Config.getInstance().getPuertoReceptores());
		} catch (IOException e) {
			
		}
	}

	public void getReceptores() {
		try {
			new ObjectOutputStream(serverEmisores.accept().getOutputStream())
					.writeObject(receptores.values().stream().collect(Collectors.toList()));
		} catch (IOException e) {
			
		}
	}

	public void actualizarEstado() {
		try {
			Socket socket = serverReceptores.accept();
			Receptor recep = (Receptor) new ObjectInputStream(socket.getInputStream()).readObject();
			recep.setIp(socket.getInetAddress().getHostAddress());
			receptores.put(recep.getNombreUsuario().toLowerCase(), recep);
		} catch (IOException | ClassNotFoundException e) {

		}
	}

	public List<Receptor> listaReceptores() {
		return receptores.values().stream().collect(Collectors.toList());
	}
}
