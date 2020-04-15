package modelo;

import java.io.Serializable;
import java.util.List;

public class Emisor extends Usuario implements Serializable {

	private List<Receptor> listaContactos;

	public List<Receptor> getListaContactos() {
		return listaContactos;
	}

	public void setListaContactos(List<Receptor> listaContactos) {
		this.listaContactos = listaContactos;
	}

}
