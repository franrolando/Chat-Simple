package controlador;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.Objects;

import Utils.Utils;
import modelo.Mensaje;
import modelo.MensajeAvisoRecep;

public class ControladorReceptor {

	private static ControladorReceptor instance;
	private final static Integer PUERTO = 8090;
	private static DatagramSocket socketUDP = null;
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

	public Mensaje receptionMessageUDP() {
		Mensaje message = null;
		try {
			byte[] buffer = new byte[1024];
			DatagramPacket pregunta = new DatagramPacket(buffer, buffer.length);
			socketUDP.receive(pregunta);
			message = Utils.toObject(pregunta.getData());
			System.out.println(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return message;
	}

	public MensajeAvisoRecep leerMensajeAvisoRecepcion() {
		MensajeAvisoRecep mensaje = null;
		try {
			ObjectInputStream in = new ObjectInputStream(socket.accept().getInputStream());
			mensaje = (MensajeAvisoRecep) in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mensaje;
	}

	public static void instanciarSocketServer() {
		try {
			socketUDP = new DatagramSocket(PUERTO);
			socket = new ServerSocket(PUERTO);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
