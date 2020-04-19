package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Enum.ETipoMensaje;
import controlador.ControladorReceptor;
import modelo.Mensaje;
import modelo.Receptor;

public class ViewReceptor{

	private JTabbedPane tabbedPane;
	
	/**
	 * Create the application.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public ViewReceptor(Receptor receptor) {
		initialize(receptor);
		ControladorReceptor.instanciarSocketServer();
		escuchaMensaje();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Receptor receptor) {
		JFrame frmReceptor = new JFrame();
		frmReceptor.getContentPane().setBackground(new Color(240, 230, 140));
		frmReceptor.setResizable(false);
		frmReceptor.setTitle("Mensaje Receptor");
		frmReceptor.setBounds(100, 100, 704, 474);
		frmReceptor.setSize(700, 500);
		frmReceptor.setLocationRelativeTo(null);
		frmReceptor.setVisible(true);
		System.out.println(receptor.getConectado());
		frmReceptor.addWindowListener(new WindowAdapter() {
			@Override
            public void windowClosing(WindowEvent e) {
				receptor.setConectado(false);
				ControladorReceptor.getInstance().sendStatus(receptor);
				System.out.println(receptor.getConectado());
            }
		});

		ImageIcon imgReceptor = new ImageIcon("./src/main/img/email-icon.png");
		frmReceptor.setIconImage(imgReceptor.getImage());

		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 230, 140));
		frmReceptor.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 13));
		tabbedPane.setBackground(new Color(240, 230, 140));
		panel.add(tabbedPane, BorderLayout.CENTER);
		tabbedPane.setVisible(true);
	}

	private void creaNuevaTab(JTabbedPane tabbedPane, String emisor, String asunto, String mensaje,
			ETipoMensaje tipoMensaje) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 230, 140));
		tabbedPane.addTab(emisor, panel);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel1.setBackground(new Color(240, 230, 140));
		panel.add(panel1, BorderLayout.NORTH);

		JLabel lblAsunto = new JLabel("Asunto");
		lblAsunto.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel1.add(lblAsunto);

		JTextField textFieldAsunto = new JTextField();
		textFieldAsunto.setText(asunto);
		panel1.add(textFieldAsunto);
		textFieldAsunto.setColumns(55);
		textFieldAsunto.setEditable(false);

		JPanel panel2 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel2.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel2.setBackground(new Color(240, 230, 140));
		panel.add(panel2, BorderLayout.SOUTH);

		String filePath = ("./src/main/audio/Industrial Alarm.wav");
		AudioInputStream audioAlarma = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
		Clip clipAlarma = AudioSystem.getClip();
		clipAlarma.open(audioAlarma);

		if (tipoMensaje.equals(ETipoMensaje.CONALERTASONIDO)) {
			ImageIcon imgSilenciar = new ImageIcon("./src/main/img/mute.png");
			JButton btnSilenciar = new JButton("Silenciar", imgSilenciar);
			btnSilenciar.setFont(new Font("Tahoma", Font.BOLD, 14));
			panel2.add(btnSilenciar);
			clipAlarma.start();
			clipAlarma.loop(Clip.LOOP_CONTINUOUSLY);
			btnSilenciar.addActionListener(e -> clipAlarma.close());
		}

		ImageIcon imgEliminarR = new ImageIcon("./src/main/img/cruz-eliminar.png");
		JButton btnEliminar = new JButton("Eliminar", imgEliminarR);
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel2.add(btnEliminar);
		btnEliminar.addActionListener(e -> {
			clipAlarma.close();
			tabbedPane.remove(tabbedPane.getSelectedIndex());
		});

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(240, 230, 140));
		panel.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_1.setBackground(new Color(240, 230, 140));
		panel_5.add(panel_1, BorderLayout.NORTH);

		JLabel lblCuerpo = new JLabel("Cuerpo");
		lblCuerpo.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_1.add(lblCuerpo);

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(240, 230, 140));
		panel_5.add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_6.add(scrollPane);

		JTextArea textArea = new JTextArea();
		textArea.setText(mensaje);
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
	}

	private void escuchaMensaje() {
		Thread udpThread = new Thread() {

			@Override
			public void run() {
				while (true) {
					Mensaje mensajeUDP = ControladorReceptor.getInstance().receptionMessageUDP();
					try {
						creaNuevaTab(tabbedPane, mensajeUDP.getEmisor(), mensajeUDP.getAsunto(), mensajeUDP.getCuerpo(),
								mensajeUDP.getTipo());
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
						// TODO Auto-generated catch block
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
						creaNuevaTab(tabbedPane, mensajeTCP.getEmisor(), mensajeTCP.getAsunto(), mensajeTCP.getCuerpo(),
								ETipoMensaje.CONAVISORECEPCION);
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
