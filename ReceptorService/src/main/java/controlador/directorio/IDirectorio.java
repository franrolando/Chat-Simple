package controlador.directorio;

import java.io.IOException;

import modelo.Receptor;

public interface IDirectorio {

	void sendStatus(Receptor receptor);
	Boolean nombreValido(String nombre) throws IOException;

}
