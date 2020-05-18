package creator;

import java.util.HashMap;
import java.util.Map;

import Enum.ETipoMensaje;
import vista.ViewMensaje;
import vista.ViewMensajeAlerta;

public class ViewMensajeCreator {
	
	@FunctionalInterface 
	private interface IViewMensajeFactory {
		ViewMensaje create(String emisor, String asunto, String mensaje, String hora);
	}
	
	private static Map<ETipoMensaje, IViewMensajeFactory> factoryMap = new HashMap<>();
	
	public static ViewMensaje getViewMensaje(String emisor, String asunto, String mensaje, ETipoMensaje tipoMensaje,String hora) {
		return factoryMap.get(tipoMensaje).create(emisor, asunto, mensaje, hora);
	}
	
	static {
		factoryMap.put(ETipoMensaje.SIMPLE, ViewMensaje::new);
		factoryMap.put(ETipoMensaje.CONALERTASONIDO, ViewMensajeAlerta::new);
		factoryMap.put(ETipoMensaje.CONAVISORECEPCION, ViewMensaje::new);
	}
	
}
