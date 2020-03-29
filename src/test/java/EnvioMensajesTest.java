import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

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
		ControladorEmisor.getInstance().enviarMensajeAvisoRecepcion(mensaje, destinos).forEach( (K, V) -> {
			assertTrue(V);
		});
	}
	
	/**
	 * Test para mensajes simples y con sonido de alarma
	 */
	@Test
	public void testMensajeSimple() {
		Mensaje mensaje = new Mensaje();
		List<Receptor> destinos = new ArrayList<>();
		Receptor rec = new Receptor();
		rec.setNombreUsuario("pepe");
		rec.setIp("192.168.0.5");
		destinos.add(rec);
		ControladorEmisor.getInstance().enviarMensajeSimple(mensaje, destinos);
	}
	
}
