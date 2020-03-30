import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import Utils.Utils;
import controlador.ControladorEmisor;
import modelo.Mensaje;
import modelo.MensajeAvisoRecep;
import modelo.Receptor;

public class EnvioMensajesTest {

	@Test
	public void testMensajeAvisoRecepcion() {
		MensajeAvisoRecep mensaje = new MensajeAvisoRecep();
		List<Receptor> destinos = new ArrayList<>();
		Receptor rec = new Receptor();
		rec.setNombreUsuario("pepe");
		rec.setIp("192.168.0.5");
		destinos.add(rec);
		ControladorEmisor.getInstance().enviarMensajeAvisoRecepcion(mensaje, destinos).forEach((K, V) -> {
			assertTrue(V);
		});
	}

	/**
	 * Test para mensajes simples y con sonido de alarma
	 */
	@Test
	public void testMensajeSimple() {
		DatagramSocket socketUDP = null;
		ServerSocket socket = null;
		try {
			socketUDP = new DatagramSocket(8090);
			socket = new ServerSocket(8090);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Mensaje mensaje = new MensajeAvisoRecep();
		MensajeAvisoRecep mensaje2 = new MensajeAvisoRecep();
		mensaje.setCuerpo("dasdsadas");
		mensaje.setAsunto("ddd");
		mensaje2.setCuerpo("aaaaa");
		mensaje2.setAsunto("bbb");
		List<Receptor> destinos = new ArrayList<>();
		Receptor rec = new Receptor();
		rec.setNombreUsuario("pepe");
		rec.setIp("192.168.0.5");
		destinos.add(rec);
		ControladorEmisor.getInstance().enviarMensajeAvisoRecepcion(mensaje2, destinos);
		ControladorEmisor.getInstance().enviarMensajeSimple(mensaje, destinos);
		byte[] buffer = new byte[1024];
		Mensaje message;
		try {
			Socket socket_cli = socket.accept();
			ObjectInputStream in = new ObjectInputStream(socket_cli.getInputStream());
			try {
				System.out.println(((Mensaje) in.readObject()).getTipo());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			do {
				DatagramPacket pregunta = new DatagramPacket(buffer, buffer.length);

				socketUDP.receive(pregunta);

				message = Utils.toObject(pregunta.getData());
				System.out.println(message.getTipo());
				DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
				socketUDP.receive(peticion);
				System.out.println("Recibo la peticion");
				mensaje = Utils.toObject(peticion.getData());
				System.out.println(mensaje);
			} while (message == null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
