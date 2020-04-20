package vista;

import java.awt.Component;
import java.util.Hashtable;
import java.util.List;
import java.util.Objects;

import javax.swing.*;
import javax.swing.ImageIcon;

import modelo.Emisor;
import modelo.Receptor;

public class RenderCombo extends JLabel implements ListCellRenderer{
	
	Hashtable<Receptor, ImageIcon> elementos=null;
	
	public RenderCombo(Emisor emisor){
		elementos=new Hashtable<Receptor, ImageIcon>();
		ImageIcon imgOnline=new ImageIcon("./src/main/img/online.png");
		ImageIcon imgOffline=new ImageIcon("./src/main/img/offline.png");
		emisor.getListaContactos().stream().forEach(receptor -> {
			if(receptor.getConectado()) {
				agregarElemento(receptor,imgOnline);
			} else {
				agregarElemento(receptor,imgOffline);
			}
		});
	}
	
	public void agregarElemento(Receptor receptor,ImageIcon img){
		elementos.put(receptor, img);
	}
	
	@Override
	public Component getListCellRendererComponent(JList list, Object value,int index, boolean isSelected, boolean cellHasFocus) {
		if(!Objects.isNull(value) && elementos.get(value)!=null){
			setIcon(elementos.get(value));
			setText(""+value);
		}else{
			setIcon(null);
			setText(""+value);
		}
		return this;
	}
}
