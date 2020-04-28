package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import Enum.ETipoMensaje;
import controlador.ControladorReceptor;
import modelo.Mensaje;
import modelo.Receptor;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.GridLayout;

import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class ViewReceptor2 {

	private JFrame frmInterfazReceptor;
	private JPanel panelMensajes;
	private List<JPanel> listSeleccionados=null;

	/**
	 * Create the application.
	 */
	public ViewReceptor2(Receptor receptor) {
		initialize(receptor);
		listSeleccionados = new ArrayList<JPanel>();
		ControladorReceptor.instanciarSocketServer();
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
		
		JLabel lblBandejaDeEntrada = new JLabel("Bandeja de entrada",new ImageIcon("./src/main/img/download-email.png"),0);
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
		
		JButton btnEliminarSeleccionados = new JButton("Eliminar Seleccionados",new ImageIcon("./src/main/img/cruz-eliminar.png"));
		btnEliminarSeleccionados.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(btnEliminarSeleccionados);
		btnEliminarSeleccionados.addActionListener(e -> {
			eliminarSeleccionados(panelMensajes);
		});
				
	}
	
	private void creaNuevoMensaje(JPanel panel, String emisor, String asunto, String mensaje,ETipoMensaje tipoMensaje) 
			throws UnsupportedAudioFileException, IOException, LineUnavailableException {
	
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(169, 169, 169));
		panel_1.setVisible(true);
		panel_1.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel labelNombre = new JLabel("Enviado por: "+emisor);
		labelNombre.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelNombre.setForeground(new Color(20,0,105));
		panel_1.add(labelNombre, BorderLayout.NORTH);
		
		ImageIcon iconAsunto = new ImageIcon("./src/main/img/arrow-forward-icon.png");
				
		JLabel labelAsunto = new JLabel("Asunto: "+asunto,iconAsunto,0);
		labelAsunto.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelAsunto.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(labelAsunto, BorderLayout.CENTER);
		
		JCheckBox chckbxLeido = new JCheckBox("Leido");
		chckbxLeido.setFont(new Font("Tahoma", Font.BOLD, 13));
		chckbxLeido.setHorizontalAlignment(SwingConstants.RIGHT);
		chckbxLeido.setBackground(new Color(169, 169, 169));
		panel_1.add(chckbxLeido, BorderLayout.EAST);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now(); 
		JLabel labelHora = new JLabel("Hora: "+dtf.format(now));
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
		
		JButton btnVerMensaje = new JButton("Ver mensaje",new ImageIcon("./src/main/img/Zoom-icon.png"));
		btnVerMensaje.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_2.add(btnVerMensaje);
		btnVerMensaje.addActionListener(e -> {
			new ViewMensaje(emisor,asunto,mensaje,tipoMensaje);
		});
		
		JButton btnEliminar = new JButton("Eliminar",new ImageIcon("./src/main/img/cruz-eliminar.png"));
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_2.add(btnEliminar);
		btnEliminar.addActionListener(e -> {
			eliminaMensaje(panel,panel_1);
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
				eliminaMensaje(panel1,panel2);
			}
			listSeleccionados.clear();
		}
	}
	
	private void escuchaMensaje() {
		Thread udpThread = new Thread() {

			@Override
			public void run() {
				while (true) {
					Mensaje mensajeUDP = ControladorReceptor.getInstance().receptionMessageUDP();
					try {
						creaNuevoMensaje(panelMensajes, mensajeUDP.getEmisor(), mensajeUDP.getAsunto(), mensajeUDP.getCuerpo(),
								mensajeUDP.getTipo());
						panelMensajes.validate();
						panelMensajes.repaint();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
						e.printStackTrace();
					}

				}
			}

		};
		Thread tcpThread = new Thread() {

			@Override
			public void run() {
				while (true) {
					Mensaje mensajeTCP = ControladorReceptor.getInstance().leerMensajeAvisoRecepcion();
					try {
						creaNuevoMensaje(panelMensajes, mensajeTCP.getEmisor(), mensajeTCP.getAsunto(), mensajeTCP.getCuerpo(),
								ETipoMensaje.CONAVISORECEPCION);
						panelMensajes.validate();
						panelMensajes.repaint();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
						e.printStackTrace();
					}
				}
			}
		};
		tcpThread.start();
		udpThread.start();
	}

}
