package conexion;

import java.io.IOException;
import java.net.Socket;
import java.util.Objects;

public class ConexionEmisor {
	
	private static ConexionEmisor instance = null;
	
	private ConexionEmisor() {
		
	}
	
	public static ConexionEmisor getInstance() {
		if (Objects.isNull(instance)) {
			instance = new ConexionEmisor();
		}
		return instance;
	}
	
	public Socket getConexion(String ip, Integer port) throws IOException {
		return new Socket(ip, port);
	}
	
}
