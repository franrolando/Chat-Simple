package controlador.directorio;

import java.io.IOException;
import java.util.List;

import modelo.Receptor;

public interface IDirectorio {

	List<Receptor> getContactList() throws IOException;
	
}
