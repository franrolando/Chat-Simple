package config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Config {

	public void solicitaDatos() {
		
		File archivo = new File ("./resources/config.txt");
		String leido;
		try {
			if (archivo.exists()) {
				BufferedReader leeArchivo = new BufferedReader(new FileReader(archivo));
				while ((leido = leeArchivo.readLine()) != null) {
					String[] palabras = leido.split("=");
					switch (palabras[0]) {
						case "ipBaseDatos":
							getIpDestino(palabras[1]);
							break;
						case "PUERTO":
							getPuertoDestino(Integer.parseInt(palabras[1]));
							break;
						case "PUERTOCONTACTOSENVIA":
							getPuertoContacto(Integer.parseInt(palabras[1]));
						break;
					}
				}		
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		
	
	}
		
		
	public String getIpDestino (String ipDestino) {
		return ipDestino;
	}
	
	public Integer getPuertoDestino (Integer puertoDestino) {
		return puertoDestino;
	}
	
	public Integer getPuertoContacto (Integer puertoContacto) {
		return puertoContacto;
	}
}
