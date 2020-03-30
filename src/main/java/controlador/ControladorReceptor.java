package controlador;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Enum.ETipoMensaje;
import Utils.Utils;
import modelo.Mensaje;

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

			if (message.getTipo() == ETipoMensaje.SIMPLE) {
				receptorMensajeSimple();
			} else if (ETipoMensaje.CONALERTASONIDO != null) {
				receptorMensajeAlerta();
			} else
				receptorMensajeAvisoR();
		} catch (IOException e) {
			log.error("ERROR:", e);
		} finally {
//			if (!Objects.isNull(socketUDP)) {
//				socketUDP.close();
//			}
		}
		return message;
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
	
	public void testTCP() {
		// declaramos un objeto ServerSocket para realizar la comunicación
		 ServerSocket socket;
		// creamos una varible boolean con el valor a false
		 boolean fin = false;
		// Declaramos un bloque try y catch para controlar la ejecución del subprograma
		 try {
		// Instanciamos un ServerSocket con la dirección del destino y el
		// puerto que vamos a utilizar para la comunicación
		 socket = new ServerSocket(6000);
		// Creamos un socket_cli al que le pasamos el contenido del objeto socket después
		// de ejecutar la función accept que nos permitirá aceptar conexiones de clientes
		 Socket socket_cli = socket.accept();
		// Declaramos e instanciamos el objeto DataInputStream
		// que nos valdrá para recibir datos del cliente
		 DataInputStream in =
		 new DataInputStream(socket_cli.getInputStream());
		// Creamos un bucle do while en el que recogemos el mensaje
		// que nos ha enviado el cliente y después lo mostramos
		// por consola
		 do {
		 String mensaje ="";
		 mensaje = in.readUTF();
		 System.out.println(mensaje);
		 } while (1>0);
		 }
		// utilizamos el catch para capturar los errores que puedan surgir
		 catch (Exception e) {
		// si existen errores los mostrará en la consola y después saldrá del
		// programa
		 System.err.println(e.getMessage());
		 System.exit(1);
		 }
	}

}
