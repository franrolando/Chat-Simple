package modelo;

public class Cifrador {

	private int KEY=7;
	
	public Cifrador () {
	}
	
	public void cifrarMensaje(Mensaje mensaje) {
		mensaje.setEmisor(cifraTexto(mensaje.getEmisor(),KEY));
		mensaje.setAsunto(cifraTexto(mensaje.getAsunto(),KEY));
		mensaje.setCuerpo(cifraTexto(mensaje.getCuerpo(),KEY));
	}                                                    
	
	public void descifrarMensaje(Mensaje mensaje) {
		mensaje.setEmisor(descifraTexto(mensaje.getEmisor(),KEY));
		mensaje.setAsunto(descifraTexto(mensaje.getAsunto(),KEY));
		mensaje.setCuerpo(descifraTexto(mensaje.getCuerpo(),KEY));
	}

	private String cifraTexto(String texto, int clave) {
        StringBuilder cifrado = new StringBuilder();
        clave = clave % 26;
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) >= 'a' && texto.charAt(i) <= 'z') {
                if ((texto.charAt(i) + clave) > 'z') {
                    cifrado.append((char) (texto.charAt(i) + clave - 26));
                } else {
                    cifrado.append((char) (texto.charAt(i) + clave));
                }
            } else if (texto.charAt(i) >= 'A' && texto.charAt(i) <= 'Z') {
                if ((texto.charAt(i) + clave) > 'Z') {
                    cifrado.append((char) (texto.charAt(i) + clave - 26));
                } else {
                    cifrado.append((char) (texto.charAt(i) + clave));
                }
            }
        }
        return cifrado.toString();
    }

    private String descifraTexto(String texto, int clave) {
        StringBuilder cifrado = new StringBuilder();
        clave = clave % 26;
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) >= 'a' && texto.charAt(i) <= 'z') {
                if ((texto.charAt(i) - clave) < 'a') {
                    cifrado.append((char) (texto.charAt(i) - clave + 26));
                } else {
                    cifrado.append((char) (texto.charAt(i) - clave));
                }
            } else if (texto.charAt(i) >= 'A' && texto.charAt(i) <= 'Z') {
                if ((texto.charAt(i) - clave) < 'A') {
                    cifrado.append((char) (texto.charAt(i) - clave + 26));
                } else {
                    cifrado.append((char) (texto.charAt(i) - clave));
                }
            }
        }
        return cifrado.toString();
    }
}
