package controlador;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Utils.Utils;
import modelo.Mensaje;
import modelo.MensajeAvisoRecep;

public class ControladorReceptor {
	private final static Logger log = LoggerFactory.getLogger(ControladorReceptor.class);
	private static ControladorReceptor instance;
	private final static Integer PUERTO = 8090;

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
		DatagramSocket socketUDP = null;
		Mensaje message = null;
		try {
			byte[] buffer = new byte[1024];
			socketUDP = new DatagramSocket(PUERTO);
			int cont = 0;
			do {
				DatagramPacket pregunta = new DatagramPacket(buffer, buffer.length);
				socketUDP.receive(pregunta);
				log.info("Recepcion de datagrama");
				message = Utils.toObject(pregunta.getData());
				System.out.println(message);
//	           	    DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
//	                socketUDP.receive(peticion);
//	                System.out.println("Recibo la peticion");
//	                mensaje = new Mensaje(peticion.getData());
//	                System.out.println(mensaje)
				cont++;
			} while (message == null && cont < 3);
		} catch (IOException e) {
			log.error("ERROR:", e);
		} finally {
			if (!Objects.isNull(socketUDP)) {
				socketUDP.close();
			}
		}
		return message;
	}

	public MensajeAvisoRecep leerMensajeAvisoRecepcion() {
		ServerSocket socket;
		MensajeAvisoRecep mensaje = null;
		try {
			socket = new ServerSocket(8090);
			ObjectInputStream in = new ObjectInputStream(socket.accept().getInputStream());
			do {
				mensaje = (MensajeAvisoRecep) in.readObject();
				log.info(mensaje.toString());
			} while (mensaje == null);
		} catch (Exception e) {
			log.error("ERROR:", e);
		}
		return mensaje;
	}

	public Mensaje receptorMensajeSimple() {
		return null;
	}

	public Mensaje receptorMensajeAlerta() {
		return null;
	}

	public Mensaje receptorMensajeAvisoR() {
		return null;
	}

}
