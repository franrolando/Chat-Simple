package controlador.directorio;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import config.ConfigReceptor;
import modelo.Receptor;

public class DirectorioImpl implements IDirectorio {

	private static DirectorioImpl instance = null;

	private DirectorioImpl() {
		super();
	}

	public static DirectorioImpl getInstance() {
		if (Objects.isNull(instance)) {
			instance = new DirectorioImpl();
		}
		return instance;
	}

	@Override
	public void sendStatus(Receptor receptor) {
		Socket socket = null;
		try {
			socket = new Socket(ConfigReceptor.getInstance().getIpDirectorio(),
					ConfigReceptor.getInstance().getPuertoEstado());
		} catch (IOException e) {
			e.printStackTrace();
			try {
				socket = new Socket(ConfigReceptor.getInstance().getIpDirectorioAux(),
						ConfigReceptor.getInstance().getPuertoEstado());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		ObjectOutputStream os;
		try {
			os = new ObjectOutputStream(socket.getOutputStream());
			os.writeObject("estado");
			os.writeObject(receptor);
			os.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Boolean nombreValido(String nombre) throws IOException {
		Boolean valido = true;
		List<Receptor> contactList = new ArrayList<>();
		Socket socket = null;
		ObjectInputStream is = null;
		socket = new Socket(ConfigReceptor.getInstance().getIpDirectorio(),
				ConfigReceptor.getInstance().getPuertoEstado());
		new ObjectOutputStream(socket.getOutputStream()).writeObject("nombreValido");
		is = new ObjectInputStream(socket.getInputStream());
		if (socket != null && is != null) {
			try {
				contactList = (List<Receptor>) is.readObject();
				valido = !contactList.stream()
						.anyMatch(receptor -> receptor.getNombreUsuario().equals(nombre) && receptor.getConectado());
				is.close();
				socket.close();
			} catch (IOException | ClassNotFoundException e) {
				valido = false;
			}
		}
		return valido;
	}

}
