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

		JTextField textFieldEmisor = new JTextField(emisor);
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

		JTextField textFieldAsunto = new JTextField(asunto);
		textFieldAsunto.setEditable(false);
		panel_4.add(textFieldAsunto);
		textFieldAsunto.setColumns(38);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(240, 230, 140));
		frmMensajeReceptor.getContentPane().add(panel_2, BorderLayout.SOUTH);

		botonesFooter(panel_2);

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

	protected void botonesFooter(JPanel panel_2) {
		JButton btnCerrar = new JButton("Cerrar", new ImageIcon("./src/main/img/Close-icon.png"));
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_2.add(btnCerrar);
		btnCerrar.addActionListener(e -> closeMessageAction());
	}

	protected void closeMessageAction() {
		frmMensajeReceptor.dispose();
	}

}
