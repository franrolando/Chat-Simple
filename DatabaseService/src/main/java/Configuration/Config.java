package Configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Objects;

public class Config {
	
	private static Config instance = null;
	
	public synchronized static Config getInstance() {
		if (Objects.isNull(instance)) {
			instance = new Config();
		}
		return instance;
	}

	public String getStrategy() {
		return getProperty("STRATEGY");
	}

	public String getControlador() {
		return getProperty("CONTROLADOR");
	}

	public String getUsuario() {
		return getProperty("USUARIO");
	}

	public String getClave() {
		return getProperty("CLAVE");
	}

	public String getURL() {
		return getProperty("URL");
	}
	
	public String getPuertoEmisores() {
		return getProperty("PUERTOEMISORES");
	}

	public String getPuertoReceptores() {
		return getProperty("PUERTORECEPTORES");
	}
	
	public String getPuertoMsjOffline() {
		return getProperty("PUERTOMSJOFFLINE");
	}

	private String getProperty(String property) {
		File archivo = null;
		String value = null;
		FileReader fr = null;
		BufferedReader br = null;
		try {
			archivo = new File("config.txt");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			String linea;
			while ((linea = br.readLine()) != null) {
				if (!linea.startsWith("#")) {
					if (linea.subSequence(0, linea.indexOf("=")).toString().trim().equalsIgnoreCase(property)) {
						value = linea.substring(linea.indexOf("=")+1).trim();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return value;
	}

}
