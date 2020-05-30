package config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class Config {

	private static Config instance = null;
	
	private Config() {
		super();
	}
	
	public static Config getInstance() {
		if (Objects.isNull(instance)) {
			instance = new Config();
		}
		return instance;
	}
	
	public String getProperty(String parametro) {
		File archivo = new File ("src/main/resources/config.txt");
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
		return getProperty("ipBaseDatos");
	}
	
	public Integer getPuertoDestino () {
		return Integer.parseInt(getProperty("PUERTO"));
	}
	
	public Integer getPuertoContacto () {
		return Integer.parseInt(getProperty("PUERTOCONTACTOSENVIA"));
	}
	
	public String getIpDirectorio () {
 		return getProperty("IPDIRECTORIO");
	}
	
}
