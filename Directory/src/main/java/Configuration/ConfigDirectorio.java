package Configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Objects;

public class ConfigDirectorio {
	
	private static ConfigDirectorio instance = null;
	
	public synchronized static ConfigDirectorio getInstance() {
		if (Objects.isNull(instance)) {
			instance = new ConfigDirectorio();
		}
		return instance;
	}

	public Integer getPuertoEmisores() {
		return Integer.parseInt(getProperty("PUERTOEMISORES"));
	}

	public Integer getPuertoReceptores() {
		return Integer.parseInt(getProperty("PUERTORECEPTORES"));
	}
	
	public Boolean isDirectorioReplica() {
		return Boolean.parseBoolean(getProperty("DirectorioReplica"));
	}
	
	public String getIpDirectorioReplica() {
		return getProperty("IPDIRECTORIOREPLICA");
	}
	
	public Integer getPuertoDirectorioReplica() {
		return Integer.parseInt(getProperty("PuertoDirectorioReplica"));
	}
	
	
	private String getProperty(String property) {
		File archivo = null;
		String value = null;
		FileReader fr = null;
		BufferedReader br = null;
		try {
			archivo = new File("src/main/resources/configDirectory.txt");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			String linea;
			while ((linea = br.readLine()) != null) {
				if (!linea.startsWith("#")) {
					if (linea.subSequence(0, linea.lastIndexOf("=")).toString().trim().equalsIgnoreCase(property)) {
						value = linea.substring(linea.lastIndexOf("=")+1).trim();
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
				
			}
		}
		return value;
	}

}
