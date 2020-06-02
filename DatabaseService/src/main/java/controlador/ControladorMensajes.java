package controlador;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Objects;

import Configuration.Config;
import DAO.MensajesDAO;
import modelo.Mensaje;

public class ControladorMensajes {

	private static ControladorMensajes instance = null;

	private static ServerSocket serverEmisores;
	private static ServerSocket serverReceptores;

	public static ControladorMensajes getInstance() {
		if (Objects.isNull(instance)) {
			synchronized (ControladorMensajes.class) {
				if (Objects.isNull(instance)) {
					instance = new ControladorMensajes();
				}
			}
		}
		return instance;
	}

	public static void iniciaServidor() {
		try {
			serverEmisores = new ServerSocket(Integer.parseInt(Config.getInstance().getPuertoEmisores()));
			serverReceptores = new ServerSocket(Integer.parseInt(Config.getInstance().getPuertoMsjOffline()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void enviaMensaje() {
		Socket socketRecibeMsj = null;
		ObjectOutputStream out = null;
		Mensaje mensaje = null;
		try {
			socketRecibeMsj = serverEmisores.accept();
			ObjectInputStream is = new ObjectInputStream(socketRecibeMsj.getInputStream());
			try {
				String action = (String) is.readObject();
				switch (action) {
				case ("disponible"):
					socketRecibeMsj.close();
					break;
				case ("envioMensaje"):
					mensaje = (Mensaje) is.readObject();
					Socket socketEnvioMsj = new Socket(mensaje.getIpDestino(),
							Integer.parseInt(Config.getInstance().getPuertoReceptores()));
					out = new ObjectOutputStream(socketEnvioMsj.getOutputStream());
					out.writeObject(mensaje);
					socketEnvioMsj.close();
					out = new ObjectOutputStream(socketRecibeMsj.getOutputStream());
					out.writeObject(true);
					break;
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
			try {
				out = new ObjectOutputStream(socketRecibeMsj.getOutputStream());
				out.writeObject(false);
				out.close();
				if (!Objects.isNull(mensaje)) {
					MensajesDAO.getInstance().insertarMensaje(mensaje);
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void getMensajes() {
		try {
			Socket socket = serverReceptores.accept();
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			String nombreReceptor = (String) in.readObject();
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(MensajesDAO.getInstance().getMensajes(nombreReceptor));
			socket.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
