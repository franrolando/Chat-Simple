package controlador.servicioMensajes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import config.ConfigReceptor;
import modelo.Cifrador;
import modelo.mensajes.Mensaje;

public class ServicioMensajesImpl implements IServicioMensajes {

	private static ServicioMensajesImpl instance = null;

	private ServicioMensajesImpl() {
		super();
	}

	public static ServicioMensajesImpl getInstance() {
		if (Objects.isNull(instance)) {
			instance = new ServicioMensajesImpl();
		}
		return instance;
	}

	@Override
	public Mensaje leerMensaje() {
		Mensaje mensaje = null;
		try {
			ServerSocket serverSocket = new ServerSocket(ConfigReceptor.getInstance().getPuertoMensajes());
			mensaje = (Mensaje) new ObjectInputStream(serverSocket.accept().getInputStream()).readObject();
			Cifrador cf = new Cifrador();
			cf.descifrarMensaje(mensaje);
			serverSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mensaje;
	}

	@Override
	public List<Mensaje> getMensajesOffline(String nombreReceptor) throws IOException {
		List<Mensaje> mensajesOffline = new ArrayList<>();
		ObjectOutputStream out;
		ObjectInputStream in;
		try {
			Socket socket = new Socket(ConfigReceptor.getInstance().getIpServicioComunicacion(),
					ConfigReceptor.getInstance().getPuertoMsjOffline());
			out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(nombreReceptor);
			in = new ObjectInputStream(socket.getInputStream());
			mensajesOffline = (List<Mensaje>) in.readObject();
			socket.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return mensajesOffline;
	}


}
