package controlador;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import Configuration.Config;
import modelo.Receptor;
import observable.ReceptoresMap;

public class ControladorDirectorio {

	private static ControladorDirectorio instance = null;
	private ReceptoresMap receptores;
	private static ServerSocket serverEmisores;
	private static ServerSocket serverReceptores;

	private ControladorDirectorio() {
		super();
		this.receptores = new ReceptoresMap();
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

	public void listenEmisores() {
		try {
			new ObjectOutputStream(serverEmisores.accept().getOutputStream()).writeObject(listaReceptores());
		} catch (IOException e) {

		}
	}

	public void listenReceptores() {
		try {
			Socket socket = serverReceptores.accept();
			ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
			String action = (String) inputStream.readObject();
			switch (action) {
			case "estado":
				Receptor recep = (Receptor) inputStream.readObject();
				recep.setIp(socket.getInetAddress().getHostAddress());
				receptores.addReceptor(recep);
				break;
			case "nombreValido":
				new ObjectOutputStream(socket.getOutputStream()).writeObject(listaReceptores());
				break;
			}
		} catch (IOException | ClassNotFoundException e) {

		}
	}

	public List<Receptor> listaReceptores() {
		return receptores.getReceptores().values().stream().collect(Collectors.toList());
	}

	public ReceptoresMap getReceptores() {
		return receptores;
	}

	public void setReceptores(ReceptoresMap receptores) {
		this.receptores = receptores;
	}
	
}
