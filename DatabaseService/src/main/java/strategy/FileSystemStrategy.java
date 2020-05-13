package strategy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import Enum.ETipoMensaje;
import modelo.Mensaje;
import modelo.MensajeAlerta;
import modelo.MensajeAvisoRecep;

public class FileSystemStrategy implements IPersistenciaStrategy {

	private static String SEPARADOR = ";;;";

	@Override
	public void persistirMensaje(Mensaje mensaje) {
		File directorioMensajes = new File("mensajes");
		if (!directorioMensajes.exists()) {
			directorioMensajes.mkdir();
		}
		File mensajes = new File(
				directorioMensajes.getAbsolutePath().concat("//".concat(mensaje.getReceptor()).concat(".txt")));
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(mensajes, true));
			PrintWriter wr = new PrintWriter(bw);
			wr.append(mensaje.getIpDestino() + SEPARADOR + mensaje.getEmisor() + SEPARADOR + mensaje.getAsunto()
					+ SEPARADOR + mensaje.getCuerpo() + SEPARADOR + mensaje.getTipo() + SEPARADOR + "\n");
			wr.close();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void eliminaMensajes(String nombreReceptor) {
		List<Mensaje> mensajesPendientes = new ArrayList<>();
		File mensajes = new File("mensajes//".concat(nombreReceptor).concat(".txt"));
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(mensajes);
			br = new BufferedReader(fr);
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] lineaSplit = linea.split(";;;");
				Mensaje mensaje = null;
				switch (lineaSplit[4]) {
				case ("SIMPLE"):
					mensaje = new Mensaje();
					break;
				case ("CONAVISORECEPCION"):
					mensaje = new MensajeAvisoRecep();
					break;
				default:
					mensaje = new MensajeAlerta();
				}
				mensaje.setIpDestino(lineaSplit[0]);
				mensaje.setEmisor(lineaSplit[1]);
				mensaje.setAsunto(lineaSplit[2]);
				mensaje.setCuerpo(lineaSplit[3]);
				mensajesPendientes.add(mensaje);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fr) {
					fr.close();
					mensajes.delete();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	@Override
	public List<Mensaje> getMensajes(String nombreReceptor) {
		return null;
	}

}
