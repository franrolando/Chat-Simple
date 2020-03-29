package Utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controlador.ControladorEmisor;

public class Utils {

	private final static Logger log = LoggerFactory.getLogger(ControladorEmisor.class);
	
	/**
	 * Para serializar
	 * @param <T>
	 * @param object
	 * @return
	 */
	public static <T> byte[] toByteArray(T object) {
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		try {
			ObjectOutputStream os = new ObjectOutputStream(bytes);
			os.writeObject(object);
			os.close();
		} catch (IOException e) {
			log.error("ERROR: ", e);
		}
		return bytes.toByteArray();
	}

	/**
	 * Para deserializar
	 * @param <T>
	 * @param bytes
	 * @return
	 */
	public static <T> T toObject(byte[] bytes) {
		ByteArrayInputStream byteArray = new ByteArrayInputStream(bytes);
		ObjectInputStream is;
		T aux = null;
		try {
			is = new ObjectInputStream(byteArray);
			aux = (T) is.readObject();
			is.close();
		} catch (IOException | ClassNotFoundException e) {
			log.error("ERROR: ", e);
		}
		return aux;
	}

}
