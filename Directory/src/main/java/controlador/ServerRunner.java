package controlador;

public class ServerRunner {

	public static void main(String[] args) {
		ControladorDirectorio.initDirectorio();
		Thread tReceptores = new Thread() {

			@Override
			public void run() {
				while (true) {
					ControladorDirectorio.getInstance().actualizarEstado();
				}
			}
			
		};
		Thread tEmisores = new Thread() {

			@Override
			public void run() {
				while (true) {
					ControladorDirectorio.getInstance().getReceptores();
				}
			}
			
		};
		tReceptores.start();
		tEmisores.start();
	}

}
