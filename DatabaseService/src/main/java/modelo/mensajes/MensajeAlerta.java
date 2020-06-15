package modelo.mensajes;

import Enum.ETipoMensaje;

public class MensajeAlerta extends Mensaje {
	
	public MensajeAlerta() {
		super();
	}

	public ETipoMensaje getTipo() {
		return ETipoMensaje.CONALERTASONIDO;

	}
}
