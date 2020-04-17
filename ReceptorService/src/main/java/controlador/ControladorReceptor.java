package controlador;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import Utils.Utils;
import modelo.Mensaje;
import modelo.MensajeAvisoRecep;
import modelo.Receptor;

public class ControladorReceptor {

	private static ControladorReceptor instance;
	private String ipDirectorio;
	private final static Integer PUERTO = 8090;
	private static final Integer PUERTORECEPTORES = 9000;
	private static final Integer PUERTOESTADO = 9010;
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

	public void setIpDirectorio(String ipDirectorio) {
		this.ipDirectorio = ipDirectorio;
	}
	
	public Boolean sendStatus(Receptor receptor) {
		Boolean valido = true;
		try {
			valido = nombreValido(receptor.getNombreUsuario());
			if (valido) {
				Socket socket = new Socket(ipDirectorio, PUERTOESTADO);
				ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
				receptor.setIp(socket.getLocalAddress().getHostAddress());
				os.writeObject(receptor);
				os.close();
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return valido;
	}
	
	private Boolean nombreValido(String nombre) {
		List<Receptor> contactList = new ArrayList<>();
		Socket echoSocket = null;
        ObjectInputStream is = null;
        try {
            echoSocket = new Socket(ipDirectorio, PUERTORECEPTORES);
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
		return !contactList.stream().anyMatch(receptor -> receptor.getNombreUsuario().equals(nombre));
	}

}
