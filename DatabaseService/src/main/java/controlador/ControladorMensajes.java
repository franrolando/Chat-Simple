package controlador;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;
import java.util.stream.Collectors;

import Configuration.Config;
import DAO.MensajesDAO;

public class ControladorMensajes {

	private static ControladorMensajes instance = null;

	private static ServerSocket serverEmisores;
	private static ServerSocket serverReceptores;

	public static synchronized ControladorMensajes getInstance() {
		if (Objects.isNull(instance)) {
			instance = new ControladorMensajes();
		}
		return instance;
	}

	public static void iniciaServidor() {
		try {
			serverEmisores = new ServerSocket(Integer.parseInt(Config.getInstance().getPuertoEmisores()));
			serverReceptores = new ServerSocket(Integer.parseInt(Config.getInstance().getPuertoReceptores()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public synchronized void enviaMensaje() {

	}

	public synchronized void getMensajes() {
		try {
			Socket socket = serverReceptores.accept();
			new ObjectOutputStream(serverReceptores.accept().getOutputStream())
					.writeObject(MensajesDAO.getInstance().eliminarMensajes("asd"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
