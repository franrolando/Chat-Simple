package modelo;

import Enum.ETipoMensaje;

public class MensajeAvisoRecep extends Mensaje {
	
	public MensajeAvisoRecep() {
		super();
	}
	
	public ETipoMensaje getTipo() {
		return ETipoMensaje.CONAVISORECEPCION;
	}

}
