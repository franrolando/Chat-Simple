package config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class ConfigEmitter {

	private static ConfigEmitter instance = null;
	
	private ConfigEmitter() {
		super();
	}
	
	public static ConfigEmitter getInstance() {
		if (Objects.isNull(instance)) {
			instance = new ConfigEmitter();
		}
		return instance;
	}
	
	private String getProperty(String parametro) {
		File archivo = new File ("src/main/resources/configEmitter.txt");
		String leido;
		String value = null;
		try {
			if (archivo.exists()) {
				BufferedReader leeArchivo = new BufferedReader(new FileReader(archivo));
				while ((leido = leeArchivo.readLine()) != null && value == null) {
					String[] palabras = leido.split("=");
					if (palabras[0].trim().toLowerCase().equals(parametro.toLowerCase())) {
						value = palabras[1].trim();
					}
				}		
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}
		
		
	public String getIpServicioComunicacion () {
		return getProperty("IPSERVICIOMENSAJES");
	}
	
	public Integer getPuertoDestino () {
		return Integer.parseInt(getProperty("PUERTOSERVICIOMENSAJES"));
	}

	public String getIpDirectorioAux () {
		return getProperty("IPDIRECTORIOAUX");
	}
	
	public String getIpDirectorio () {
		return getProperty("IPDIRECTORIO");
	}
	
	public Integer getPuertoContacto () {
		return Integer.parseInt(getProperty("PUERTODIRECTORIO"));
	}
	
}
