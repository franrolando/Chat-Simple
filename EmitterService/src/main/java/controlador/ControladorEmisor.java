package controlador;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import config.ConfigEmitter;
import controlador.directorio.DirectorioImpl;
import controlador.servicioMensajes.ServicioMensajesImpl;
import modelo.Cifrador;
import modelo.Receptor;
import modelo.mensajes.Mensaje;

public class ControladorEmisor {

	private static ControladorEmisor instance;
	private List<Mensaje> colaMensajes = new ArrayList<>();

	private ControladorEmisor() {
		super();
	}

	public static ControladorEmisor getInstance() {
		if (Objects.isNull(instance)) {
			instance = new ControladorEmisor();
		}
		return instance;
	}

	public Map<String, Boolean> enviarMensaje(Mensaje mensaje, List<Receptor> destinos) {
		Cifrador cf = new Cifrador();
		cf.cifrarMensaje(mensaje);
		Map<String, Boolean> mensajesRecibidos = new HashMap<>();
		destinos.stream().forEach(destino -> {
			mensaje.setIpDestino(destino.getIp());
			mensaje.setReceptor(destino.getNombreUsuario());
			try {
				ServicioMensajesImpl.getInstance().sendMessage(mensaje);
				mensajesRecibidos.put(destino.getNombreUsuario(), true);
			} catch (Exception e) {
				mensajesRecibidos.put(destino.getNombreUsuario(), false);
				System.out.println("No se pudo mandar el mensaje");
				e.printStackTrace();
			}
		});
		return mensajesRecibidos;
	}

	public List<Receptor> getContactList() throws IOException {
		return DirectorioImpl.getInstance().getContactList();
	}

	public Boolean servicioEnvioDisponible() {
		return ServicioMensajesImpl.getInstance().servicioEnvioDisponible();
	}

	public List<Mensaje> getColaMensajes() {
		return colaMensajes;
	}

	public void setColaMensajes(List<Mensaje> colaMensajes) {
		this.colaMensajes = colaMensajes;
	}

	public void addMensajesPendientes(Mensaje mensaje, List<Receptor> destinos) {
		List<Mensaje> mensajes = new ArrayList<>();
		destinos.stream().forEach(destino -> {
			Mensaje mensajeClon;
			try {
				mensajeClon = mensaje.clone();
				mensajeClon.setIpDestino(destino.getIp());
				mensajeClon.setReceptor(destino.getNombreUsuario());
				mensajes.add(mensajeClon);
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		});
		synchronized (colaMensajes) {
			this.colaMensajes.addAll(mensajes);
		}
	}

	public List<Mensaje> enviarMensajesPendientes() {
		List<Mensaje> mensajesRecibidos = new ArrayList<>();
		synchronized (colaMensajes) {
			colaMensajes.forEach(mensaje -> {
				try {
					ServicioMensajesImpl.getInstance().sendMessage(mensaje);
					mensajesRecibidos.add(mensaje);
				} catch (Exception e) {
					System.out.println("Error al enviar mensajes en cola");
					e.printStackTrace();
				}
			});
			mensajesRecibidos.stream().forEach(mensaje -> {
				colaMensajes.remove(mensaje);
			});
		}
		return mensajesRecibidos;
	}

}
