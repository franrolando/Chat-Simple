package controlador;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Objects;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Enum.ETipoMensaje;
import Utils.Utils;
import modelo.Mensaje;

public class ControladorReceptor {
	private final static Logger log = LoggerFactory.getLogger(ControladorReceptor.class);
	private static ControladorReceptor instance;
	

	private ControladorReceptor() {
		super();
	}
	
	public static ControladorReceptor getInstance() {
		if (Objects.isNull(instance)) {
			instance = new ControladorReceptor();
		}
		return instance;
	}
	
	private Mensaje receptionMessageUDP() {
		DatagramSocket socketUDP = null;		
		try {
			byte[] buffer = new byte[1024];
			Mensaje message;
			socketUDP = new DatagramSocket();
			
				do {
					DatagramPacket pregunta = new DatagramPacket(buffer, buffer.length);
				    socketUDP.receive(pregunta);
		   		    log.info("Recepcion de datagrama");
		   		    message = Utils.toObject(pregunta.getData());
		   		    System.out.println (message);
//	           	    DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
//	                socketUDP.receive(peticion);
//	                System.out.println("Recibo la peticion");
//	                mensaje = new Mensaje(peticion.getData());
//	                System.out.println(mensaje)
				}while (message != null);
				
				if (message.getTipo() == ETipoMensaje.SIMPLE) {
					receptorMensajeSimple();
				}
				else if (ETipoMensaje.CONALERTASONIDO != null) {
					receptorMensajeAlerta();
				}
				else receptorMensajeAvisoR();
			}
			catch (IOException e) {
				log.error("ERROR:", e);
			} finally {
			if (!Objects.isNull(socketUDP)) {
				socketUDP.close();
			}
		}
	}
	
	
	
	public Mensaje  receptorMensajeSimple() {
		
	}
	public Mensaje receptorMensajeAlerta() {
		
	}
	public Mensaje receptorMensajeAvisoR() {
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}



