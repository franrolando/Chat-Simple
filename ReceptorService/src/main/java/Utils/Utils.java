package Utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Utils {

	/**
	 * Para deserializar
	 * 
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
			e.printStackTrace();
		}
		return aux;
	}

}
