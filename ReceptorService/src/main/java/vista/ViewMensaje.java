package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ViewMensaje {

	protected JFrame frmMensajeReceptor;

	/**
	 * Create the application.
	 */
	public ViewMensaje(String emisor, String asunto, String mensaje) {
		initialize(emisor, asunto, mensaje);
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws IOException
	 * @throws UnsupportedAudioFileException
	 * @throws LineUnavailableException
	 */
	protected void initialize(String emisor, String asunto, String mensaje) {
		frmMensajeReceptor = new JFrame();
		frmMensajeReceptor.setTitle("Mensaje Receptor");
		frmMensajeReceptor.getContentPane().setBackground(new Color(240, 230, 140));
		frmMensajeReceptor.setBounds(100, 100, 450, 300);
		frmMensajeReceptor.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmMensajeReceptor.setVisible(true);
		frmMensajeReceptor.setSize(500, 300);
		frmMensajeReceptor.setResizable(false);
		frmMensajeReceptor.setLocationRelativeTo(null);
		frmMensajeReceptor.setIconImage(new ImageIcon("./src/main/img/email-icon.png").getImage());

		JPanel panelHeader = new JPanel();
		panelHeader.setBackground(new Color(240, 230, 140));
		frmMensajeReceptor.getContentPane().add(panelHeader, BorderLayout.NORTH);
		panelHeader.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panelEmisor = new JPanel();
		FlowLayout fl_panelEmisor = (FlowLayout) panelEmisor.getLayout();
		fl_panelEmisor.setAlignment(FlowLayout.LEFT);
		panelEmisor.setBackground(new Color(240, 230, 140));
		panelHeader.add(panelEmisor);

		JLabel lblEmisor = new JLabel("Emisor: ");
		lblEmisor.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelEmisor.add(lblEmisor);

		JTextField textFieldEmisor = new JTextField(emisor);
		textFieldEmisor.setEditable(false);
		panelEmisor.add(textFieldEmisor);
		textFieldEmisor.setColumns(38);

		JPanel panelAsunto = new JPanel();
		FlowLayout fl_panelAsunto = (FlowLayout) panelAsunto.getLayout();
		fl_panelAsunto.setAlignment(FlowLayout.LEFT);
		panelAsunto.setBackground(new Color(240, 230, 140));
		panelHeader.add(panelAsunto);

		JLabel lblAsunto = new JLabel("Asunto:");
		lblAsunto.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelAsunto.add(lblAsunto);

		JTextField textFieldAsunto = new JTextField(asunto);
		textFieldAsunto.setEditable(false);
		panelAsunto.add(textFieldAsunto);
		textFieldAsunto.setColumns(38);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		frmMensajeReceptor.getContentPane().add(scrollPane, BorderLayout.CENTER);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(null);
		panel_1.setBackground(new Color(240, 230, 140));
		scrollPane.setViewportView(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel panelCuerpo = new JPanel();
		FlowLayout fl_panelCuerpo = (FlowLayout) panelCuerpo.getLayout();
		fl_panelCuerpo.setAlignment(FlowLayout.LEFT);
		panelCuerpo.setBackground(new Color(240, 230, 140));
		panel_1.add(panelCuerpo, BorderLayout.NORTH);

		JLabel lblCuerpo = new JLabel("Cuerpo:");
		lblCuerpo.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelCuerpo.add(lblCuerpo);

		JPanel panelTextArea = new JPanel();
		panelTextArea.setBorder(null);
		panelTextArea.setBackground(new Color(240, 230, 140));
		panel_1.add(panelTextArea, BorderLayout.CENTER);
		panelTextArea.setLayout(new BorderLayout(0, 0));

		JTextArea textArea = new JTextArea(mensaje);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		panelTextArea.add(textArea);

		JPanel panelFooter = new JPanel();
		panelFooter.setBackground(new Color(240, 230, 140));
		frmMensajeReceptor.getContentPane().add(panelFooter, BorderLayout.SOUTH);

		botonesFooter(panelFooter);
	}

	protected void botonesFooter(JPanel panelFooter) {
		JButton btnCerrar = new JButton("Cerrar", new ImageIcon("./src/main/img/Close-icon.png"));
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelFooter.add(btnCerrar);
		btnCerrar.addActionListener(e -> closeMessageAction());
	}

	protected void closeMessageAction() {
		frmMensajeReceptor.dispose();
	}

}
