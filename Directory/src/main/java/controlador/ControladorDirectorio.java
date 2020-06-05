package controlador;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import Configuration.Config;
import modelo.Receptor;
import observable.ReceptoresMap;

public class ControladorDirectorio {

	private static ControladorDirectorio instance = null;
	private ReceptoresMap receptores;

	private ControladorDirectorio() {
		super();
		this.receptores = new ReceptoresMap();
	}

	public static ControladorDirectorio getInstance() {
		if (Objects.isNull(instance)) {
			synchronized (ControladorDirectorio.class) {
				if (Objects.isNull(instance)) {
					instance = new ControladorDirectorio();
				}
			}
		}
		return instance;
	}

	public void listenEmisores() {
		try {
			new ObjectOutputStream(
					new ServerSocket(Config.getInstance().getPuertoEmisores()).accept().getOutputStream())
							.writeObject(listaReceptores());
		} catch (IOException e) {

		}
	}

	public void listenReceptores() {
		try {
			Socket socket = new ServerSocket(Config.getInstance().getPuertoReceptores()).accept();
			ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
			String action = (String) inputStream.readObject();
			switch (action) {
			case "estado":
				Receptor recep = (Receptor) inputStream.readObject();
				recep.setIp(socket.getInetAddress().getHostAddress());
				receptores.addReceptor(recep);
				break;
			case "nombreValido":
				new ObjectOutputStream(socket.getOutputStream()).writeObject(listaReceptores());
				break;
			}
		} catch (IOException | ClassNotFoundException e) {

		}
	}

	public void listenDirectorioReplica() {
		try {
			ServerSocket serverSocket = new ServerSocket(Config.getInstance().getPuertoDirectorioReplica());
			serverSocket.accept();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void iniciaDirectorio() {
		if (!Config.getInstance().isDirectorioReplica().equals(true)) {
			Thread tReceptores = new Thread() {

				@Override
				public void run() {
					while (true) {
						ControladorDirectorio.getInstance().listenReceptores();
					}
				}

			};
			Thread tEmisores = new Thread() {

				@Override
				public void run() {
					while (true) {
						ControladorDirectorio.getInstance().listenEmisores();
					}
				}

			};
			Thread tReplica = new Thread() {

				@Override
				public void run() {
					ControladorDirectorio.getInstance().listenDirectorioReplica();
				}

			};
			tReceptores.start();
			tEmisores.start();
			tReplica.start();
		} else {
			conexionDirectorioOrig();
		}
	}

	private void conexionDirectorioOrig() {
		Socket socket = new Socket();
		try {
			socket.setSoTimeout(1000);
			socket.connect(new InetSocketAddress(Config.getInstance().getIpDirectorioReplica(),
					Config.getInstance().getPuertoDirectorioReplica()), 1000);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Receptor> listaReceptores() {
		return receptores.getReceptores().values().stream().collect(Collectors.toList());
	}

	public ReceptoresMap getReceptores() {
		return receptores;
	}

	public void setReceptores(ReceptoresMap receptores) {
		this.receptores = receptores;
	}

}
