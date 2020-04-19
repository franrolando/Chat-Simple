package modelo;

import java.io.Serializable;

public class Receptor extends Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String ip;
	private Boolean conectado;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Boolean getConectado() {
		return conectado;
	}

	public void setConectado(Boolean conectado) {
		this.conectado = conectado;
	}

	@Override
	public String toString() {
		return nombreUsuario+"("+(conectado? "online" : "offline")+")";
	}

}
