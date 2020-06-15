package controlador.directorio;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import config.ConfigEmitter;
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
	
	public List<Receptor> getContactList() throws IOException {
		List<Receptor> contactList = new ArrayList<>();
		Socket echoSocket = null;
		ObjectInputStream is = null;
		try {
			echoSocket = new Socket(ConfigEmitter.getInstance().getIpDirectorio(), ConfigEmitter.getInstance().getPuertoContacto());
		} catch (IOException e) {
			e.printStackTrace();
		}
		is = new ObjectInputStream(echoSocket.getInputStream());
		if (echoSocket != null && is != null) {
			try {
				contactList = (List<Receptor>) is.readObject();
				is.close();
				echoSocket.close();
			} catch (IOException | ClassNotFoundException e) {
				System.out.println("Error al obtener lista de contactos");
				e.printStackTrace();
			}
		}
		return contactList;
	}

}
