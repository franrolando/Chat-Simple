package vista;

import java.awt.Component;
import java.util.Hashtable;
import java.util.List;
import java.util.Objects;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import modelo.Receptor;

public class RenderCombo extends JLabel implements ListCellRenderer<Receptor> {

	private Hashtable<Receptor, ImageIcon> elementos = null;

	public RenderCombo(List<Receptor> listaContactos) {
		elementos = new Hashtable<Receptor, ImageIcon>();
		ImageIcon imgOnline = new ImageIcon("./src/main/img/online.png");
		ImageIcon imgOffline = new ImageIcon("./src/main/img/offline.png");
		listaContactos.stream().forEach(receptor -> {
			if (receptor.getConectado()) {
				agregarElemento(receptor, imgOnline);
			} else {
				agregarElemento(receptor, imgOffline);
			}
		});
	}

	public void agregarElemento(Receptor receptor, ImageIcon img) {
		elementos.put(receptor, img);
	}

	@Override
	public Component getListCellRendererComponent(JList list, Receptor value, int index, boolean isSelected,
			boolean cellHasFocus) {
		if (!Objects.isNull(value) && elementos.get(value) != null) {
			setIcon(elementos.get(value));
			setText("" + value);
		} else {
			setIcon(null);
			setText("" + value);
		}
		return this;
	}
}
