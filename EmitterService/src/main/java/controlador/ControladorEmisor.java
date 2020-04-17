package controlador;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import Utils.Utils;
import modelo.Mensaje;
import modelo.MensajeAlerta;
import modelo.Receptor;

public class ControladorEmisor {

	private static ControladorEmisor instance;
	private String ipDirectorio;
	private static final Integer PUERTO = 8090;
	private static final Integer PUERTOCONTACTOSENVIA = 9000;

	private ControladorEmisor() {
		super();
	}

	public static ControladorEmisor getInstance() {
		if (Objects.isNull(instance)) {
			instance = new ControladorEmisor();
		}
		return instance;
	}

	public void enviarMensajeSimple(Mensaje mensaje, List<Receptor> destinos) {
		destinos.stream().forEach(destino -> {
			mensaje.setIpDestino(destino.getIp());
			sendMessage(mensaje);
		});
	}

	private void sendMessage(Mensaje mensaje) {
		DatagramSocket socketUDP = null;
		try {
			byte[] buffer = new byte[1024];
			InetAddress direccionServidor = InetAddress.getByName(mensaje.getIpDestino());
			socketUDP = new DatagramSocket();
			buffer = Utils.toByteArray(mensaje);
			DatagramPacket pregunta = new DatagramPacket(buffer, buffer.length, direccionServidor, PUERTO);
			socketUDP.send(pregunta);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (!Objects.isNull(socketUDP)) {
				socketUDP.close();
			}
		}
	}

	public Map<String, Boolean> enviarMensajeAvisoRecepcion(Mensaje mensaje, List<Receptor> destinos) {
		Map<String, Boolean> mensajesRecibidos = new HashMap<>();
		destinos.stream().forEach(destino -> {
			try {
				Socket socket = new Socket(destino.getIp(), PUERTO);
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				out.writeObject(mensaje);
				out.close();
				socket.close();
				mensajesRecibidos.put(destino.getNombreUsuario(), true);
			} catch (Exception e) {
				mensajesRecibidos.put(destino.getNombreUsuario(), false);
				e.printStackTrace();
			}
		});
		return mensajesRecibidos;
	}

	public void enviarMensajeAlertaSonido(MensajeAlerta mensaje, List<Receptor> destinos) {
		destinos.stream().forEach(destino -> {
			mensaje.setIpDestino(destino.getIp());
			sendMessage(mensaje);
		});
	}

	public List<Receptor> getContactList() {
		List<Receptor> contactList = new ArrayList<>();
		Socket echoSocket = null;
		ObjectInputStream is = null;
		try {
			echoSocket = new Socket(ipDirectorio, PUERTOCONTACTOSENVIA);
			is = new ObjectInputStream(echoSocket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (echoSocket != null && is != null) {
			try {
				contactList = (List<Receptor>) is.readObject();
				is.close();
				echoSocket.close();
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return contactList;
	}

	public void setIpDirectorio(String ipDirectorio) {
		this.ipDirectorio = ipDirectorio;
	}

}
