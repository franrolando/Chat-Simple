package modelo;

import java.io.Serializable;

public class Receptor extends Usuario implements Serializable {

	private String ip;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
		return getNombreUsuario();
	}
	
	
	
}
