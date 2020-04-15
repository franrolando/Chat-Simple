package Utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Utils {

	/**
	 * Para serializar
	 * 
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
			e.printStackTrace();
		}
		return bytes.toByteArray();
	}

}
