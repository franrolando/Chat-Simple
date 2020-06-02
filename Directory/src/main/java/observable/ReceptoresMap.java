package observable;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.TreeMap;

import modelo.Receptor;

public class ReceptoresMap extends Observable {

	private Map<String, Receptor> receptores = new TreeMap<>();

	public ReceptoresMap() {
		
	}

	public Map<String, Receptor> getReceptores() {
		return receptores;
	}

	public void setReceptores(Map<String, Receptor> receptores) {
		this.receptores = receptores;
	}

	public void addReceptor(Receptor receptor) {
		this.receptores.put(receptor.getNombreUsuario().toLowerCase(), receptor);
		setChanged();
		notifyObservers();
	}

}
