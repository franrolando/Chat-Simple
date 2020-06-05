package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import Enum.ETipoMensaje;
import controlador.ControladorReceptor;
import creator.ViewMensajeCreator;
import modelo.Mensaje;
import modelo.Receptor;

public class ViewReceptor {

	private JPanel panelMensajes;
	private List<JPanel> listSeleccionados = null;
	private JFrame frmInterfazReceptor;

	/**
	 * Create the application.
	 */
	public ViewReceptor(Receptor receptor) {
		initialize(receptor);
		listSeleccionados = new ArrayList<>();
		List<Mensaje> mensajesOffline;
		try {
			mensajesOffline = ControladorReceptor.getInstance().getMensajesOffline(receptor.getNombreUsuario());
			mensajesOffline.stream().forEach(mensaje -> {
				creaNuevoMensaje(panelMensajes, mensaje.getEmisor(), mensaje.getAsunto(), mensaje.getCuerpo(),
						mensaje.getTipo(), mensaje.getHora());
				panelMensajes.validate();
				panelMensajes.repaint();
			});
		} catch (IOException e) {
			JOptionPane.showMessageDialog(frmInterfazReceptor,
					"No se pudo establecer conexion con el servidor de mensajes ", "SERVER ERROR",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		escuchaMensaje();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Receptor receptor) {
		frmInterfazReceptor = new JFrame();
		frmInterfazReceptor.setResizable(false);
		frmInterfazReceptor.setBackground(new Color(240, 230, 140));
		frmInterfazReceptor.getContentPane().setBackground(new Color(240, 230, 140));
		frmInterfazReceptor.setTitle("Interfaz Receptor");
		frmInterfazReceptor.setBounds(100, 100, 704, 474);
		frmInterfazReceptor.setSize(700, 500);
		frmInterfazReceptor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmInterfazReceptor.setLocationRelativeTo(null);
		frmInterfazReceptor.setVisible(true);
		frmInterfazReceptor.setIconImage(new ImageIcon("./src/main/img/email-icon.png").getImage());
		frmInterfazReceptor.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				receptor.setConectado(false);
				ControladorReceptor.getInstance().sendStatus(receptor);
				System.exit(0);
			}
		});

		JLabel lblBandejaDeEntrada = new JLabel("Bandeja de entrada",
				new ImageIcon("./src/main/img/download-email.png"), 0);
		lblBandejaDeEntrada.setBackground(UIManager.getColor("Button.background"));
		lblBandejaDeEntrada.setHorizontalAlignment(SwingConstants.CENTER);
		lblBandejaDeEntrada.setFont(new Font("Tahoma", Font.BOLD, 15));
		frmInterfazReceptor.getContentPane().add(lblBandejaDeEntrada, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane();
		frmInterfazReceptor.getContentPane().add(scrollPane, BorderLayout.CENTER);

		panelMensajes = new JPanel();
		panelMensajes.setBackground(new Color(240, 230, 140));
		scrollPane.setViewportView(panelMensajes);
		panelMensajes.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 230, 140));
		frmInterfazReceptor.getContentPane().add(panel, BorderLayout.SOUTH);

		JButton btnEliminarSeleccionados = new JButton("Eliminar Seleccionados",
				new ImageIcon("./src/main/img/cruz-eliminar.png"));
		btnEliminarSeleccionados.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(btnEliminarSeleccionados);
		btnEliminarSeleccionados.addActionListener(e -> {
			eliminarSeleccionados(panelMensajes);
		});

	}

	private void creaNuevoMensaje(JPanel panel, String emisor, String asunto, String mensaje, ETipoMensaje tipoMensaje,
			String hora) {

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(169, 169, 169));
		panel_1.setVisible(true);
		panel_1.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JLabel labelNombre = new JLabel("Enviado por: " + emisor);
		labelNombre.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelNombre.setForeground(new Color(20, 0, 105));
		panel_1.add(labelNombre, BorderLayout.NORTH);

		ImageIcon iconAsunto = new ImageIcon("./src/main/img/arrow-forward-icon.png");

		JLabel labelAsunto = new JLabel("Asunto: " + asunto, iconAsunto, 0);
		labelAsunto.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelAsunto.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(labelAsunto, BorderLayout.CENTER);

		JCheckBox chckbxLeido = new JCheckBox("Leido");
		chckbxLeido.setFont(new Font("Tahoma", Font.BOLD, 13));
		chckbxLeido.setHorizontalAlignment(SwingConstants.RIGHT);
		chckbxLeido.setBackground(new Color(169, 169, 169));
		panel_1.add(chckbxLeido, BorderLayout.EAST);

		JLabel labelHora = new JLabel("Hora: " + hora);
		labelHora.setFont(new Font("Tahoma", Font.BOLD, 13));
		labelHora.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(labelHora, BorderLayout.WEST);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(169, 169, 169));
		panel_2.setVisible(true);
		panel_1.add(panel_2, BorderLayout.SOUTH);

		chckbxLeido.addActionListener(e -> {
			if (chckbxLeido.isSelected()) {
				panel_1.setBackground(new Color(220, 220, 220));
				panel_2.setBackground(new Color(220, 220, 220));
				chckbxLeido.setBackground(new Color(220, 220, 220));
				listSeleccionados.add(panel_1);
			} else {
				panel_1.setBackground(new Color(169, 169, 169));
				panel_2.setBackground(new Color(169, 169, 169));
				chckbxLeido.setBackground(new Color(169, 169, 169));
				listSeleccionados.remove(panel_1);
			}
		});

		JButton btnVerMensaje = new JButton("Ver mensaje", new ImageIcon("./src/main/img/Zoom-icon.png"));
		btnVerMensaje.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_2.add(btnVerMensaje);
		btnVerMensaje.addActionListener(e -> {
			ViewMensajeCreator.getViewMensaje(emisor, asunto, mensaje, tipoMensaje, hora);
		});

		JButton btnEliminar = new JButton("Eliminar", new ImageIcon("./src/main/img/cruz-eliminar.png"));
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_2.add(btnEliminar);
		btnEliminar.addActionListener(e -> {
			eliminaMensaje(panel, panel_1);
		});

	}

	private void eliminaMensaje(JPanel panel1, JPanel panel2) {
		panel1.remove(panel2);
		panel1.validate();
		panel1.repaint();
	}

	private void eliminarSeleccionados(JPanel panel1) {
		if (!listSeleccionados.isEmpty()) {
			for (JPanel panel2 : listSeleccionados) {
				eliminaMensaje(panel1, panel2);
			}
			listSeleccionados.clear();
		}
	}

	private void escuchaMensaje() {
		Thread tMensajes = new Thread() {

			@Override
			public void run() {
				while (true) {
					Mensaje mensaje = ControladorReceptor.getInstance().leerMensaje();
					creaNuevoMensaje(panelMensajes, mensaje.getEmisor(), mensaje.getAsunto(), mensaje.getCuerpo(),
							mensaje.getTipo(), mensaje.getHora());
					panelMensajes.validate();
					panelMensajes.repaint();
				}
			}
		};
		tMensajes.start();
	}

}
