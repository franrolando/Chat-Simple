package modelo;

public class Cifrador {

	private int KEY=7;
	
	public Cifrador () {
	}
	
	public void cifrarMensaje(Mensaje mensaje) {
		mensaje.setEmisor(cifraTexto(mensaje.getEmisor(),KEY));
		mensaje.setAsunto(cifraTexto(mensaje.getAsunto(),KEY));
		mensaje.setCuerpo(cifraTexto(mensaje.getCuerpo(),KEY));
		mensaje.setHora(cifraTexto(mensaje.getHora(),KEY));
	}                                                 
	
	public void descifrarMensaje(Mensaje mensaje) {
		mensaje.setEmisor(descifraTexto(mensaje.getEmisor(),KEY));
		mensaje.setAsunto(descifraTexto(mensaje.getAsunto(),KEY));
		mensaje.setCuerpo(descifraTexto(mensaje.getCuerpo(),KEY));
		mensaje.setHora(descifraTexto(mensaje.getHora(),KEY));
	}
	
	private String cifraTexto(String texto, int clave) {
		StringBuilder cifrado = new StringBuilder();
		for (int i = 0; i < texto.length(); i++) {
			cifrado.append((char) (texto.charAt(i) + clave));
		}
		return cifrado.toString();
	}
	
	private String descifraTexto(String texto, int clave) {
		StringBuilder cifrado = new StringBuilder();
		for (int i = 0; i < texto.length(); i++) {
			cifrado.append((char) (texto.charAt(i) - clave));
		}
		return cifrado.toString();
	}
	
}
