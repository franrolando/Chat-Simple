package vista;

import java.awt.EventQueue;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JTextField;

import Enum.ETipoMensaje;
import controlador.ControladorReceptor;
import modelo.Receptor;

import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ViewMensaje {

	private JFrame frmMensajeReceptor;
	private JTextField textFieldEmisor;
	private JTextField textFieldAsunto;

	/**
	 * Create the application.
	 */
	public ViewMensaje(String emisor, String asunto, String mensaje, ETipoMensaje tipoMensaje) {
		try {
			initialize(emisor,asunto,mensaje,tipoMensaje);
		}  catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 * @throws UnsupportedAudioFileException 
	 * @throws LineUnavailableException 
	 */
	private void initialize(String emisor, String asunto, String mensaje, ETipoMensaje tipoMensaje) 
			throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		frmMensajeReceptor = new JFrame();
		frmMensajeReceptor.setTitle("Mensaje Receptor");
		frmMensajeReceptor.getContentPane().setBackground(new Color(240, 230, 140));
		frmMensajeReceptor.setBounds(100, 100, 450, 300);
		frmMensajeReceptor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMensajeReceptor.setVisible(true);
		frmMensajeReceptor.setSize(500, 300);
		frmMensajeReceptor.setResizable(false);
		frmMensajeReceptor.setLocationRelativeTo(null);
		frmMensajeReceptor.setIconImage(new ImageIcon("./src/main/img/email-icon.png").getImage());
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 230, 140));
		frmMensajeReceptor.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_3.setBackground(new Color(240, 230, 140));
		panel.add(panel_3);
		
		JLabel lblEmisor = new JLabel("Emisor: ");
		lblEmisor.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_3.add(lblEmisor);
		
		textFieldEmisor = new JTextField(emisor);
		textFieldEmisor.setEditable(false);
		panel_3.add(textFieldEmisor);
		textFieldEmisor.setColumns(38);
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_4.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_4.setBackground(new Color(240, 230, 140));
		panel.add(panel_4);
		
		JLabel lblAsunto = new JLabel("Asunto:");
		lblAsunto.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_4.add(lblAsunto);
		
		textFieldAsunto = new JTextField(asunto);
		textFieldAsunto.setEditable(false);
		panel_4.add(textFieldAsunto);
		textFieldAsunto.setColumns(38);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(240, 230, 140));
		frmMensajeReceptor.getContentPane().add(panel_2, BorderLayout.SOUTH);
		
		String filePath = ("./src/main/audio/Industrial Alarm.wav");
		AudioInputStream audioAlarma = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
		Clip clipAlarma = AudioSystem.getClip();
		clipAlarma.open(audioAlarma);

		if (tipoMensaje.equals(ETipoMensaje.CONALERTASONIDO)) {
			ImageIcon imgSilenciar = new ImageIcon("./src/main/img/mute.png");
			JButton btnSilenciar = new JButton("Silenciar", imgSilenciar);
			btnSilenciar.setFont(new Font("Tahoma", Font.BOLD, 14));
			panel_2.add(btnSilenciar);
			clipAlarma.start();
			clipAlarma.loop(Clip.LOOP_CONTINUOUSLY);
			btnSilenciar.addActionListener(e -> clipAlarma.close());
		}
		
		JButton btnCerrar = new JButton("Cerrar",new ImageIcon("./src/main/img/Close-icon.png"));
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_2.add(btnCerrar);
		btnCerrar.addActionListener(e -> {
			frmMensajeReceptor.dispose();
		});
				
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		frmMensajeReceptor.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(null);
		panel_1.setBackground(new Color(240, 230, 140));
		scrollPane.setViewportView(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_5.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel_5.setBackground(new Color(240, 230, 140));
		panel_1.add(panel_5, BorderLayout.NORTH);
		
		JLabel lblCuerpo = new JLabel("Cuerpo:");
		lblCuerpo.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_5.add(lblCuerpo);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(null);
		panel_6.setBackground(new Color(240, 230, 140));
		panel_1.add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JTextArea textArea = new JTextArea(mensaje);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		panel_6.add(textArea);
		
	}

}
