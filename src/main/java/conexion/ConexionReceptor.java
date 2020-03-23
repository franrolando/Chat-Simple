package conexion;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Objects;

public class ConexionReceptor {
	
	private static ConexionReceptor instance = null;
	
	private ConexionReceptor() {
		
	}
	
	public static ConexionReceptor getInstance() {
		if (Objects.isNull(instance)) {
			instance = new ConexionReceptor();
		}
		return instance;
	}
	
	public ServerSocket getListenerPort(Integer port) throws IOException {
		return new ServerSocket(port);
	}

}
