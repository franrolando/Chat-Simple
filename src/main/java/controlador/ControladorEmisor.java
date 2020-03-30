package controlador;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Utils.Utils;
import modelo.Mensaje;
import modelo.MensajeAlerta;
import modelo.MensajeAvisoRecep;
import modelo.Receptor;

public class ControladorEmisor {

	private final static Logger log = LoggerFactory.getLogger(ControladorEmisor.class);
	private static ControladorEmisor instance;
	private final static Integer PUERTO = 8090;

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
			InetAddress direccionServidor = InetAddress.getByName("localhost");
			socketUDP = new DatagramSocket();
			buffer = Utils.toByteArray(mensaje);
			DatagramPacket pregunta = new DatagramPacket(buffer, buffer.length, direccionServidor, PUERTO);
			log.info("Envio el datagrama");
			socketUDP.send(pregunta);
//	            DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
//	            socketUDP.receive(peticion);
//	            System.out.println("Recibo la peticion");
//	            mensaje = new Mensaje(peticion.getData());
//	            System.out.println(mensaje);
		} catch (IOException e) {
			log.error("ERROR:", e);
		} finally {
			if (!Objects.isNull(socketUDP)) {
				socketUDP.close();
			}
		}
	}

	public Map<String, Boolean> enviarMensajeAvisoRecepcion(MensajeAvisoRecep mensaje, List<Receptor> destinos) {
		Map<String, Boolean> mensajesRecibidos = new HashMap<>();
		destinos.stream().forEach(destino -> {
			try {
				Socket socket = new Socket("localhost", PUERTO);
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				out.writeObject(mensaje);
				out.close();
				socket.close();
				mensajesRecibidos.put(mensaje.getEmisor(), true);
			} catch (Exception e) {
				mensajesRecibidos.put(mensaje.getEmisor(), false);
				log.error("ERROR:", e);
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

}
