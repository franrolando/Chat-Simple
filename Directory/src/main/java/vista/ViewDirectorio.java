package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;

import controlador.ControladorDirectorio;

public class ViewDirectorio {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				new ViewDirectorio();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewDirectorio() {
		iniciaServidor();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	private void iniciaServidor() {
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
