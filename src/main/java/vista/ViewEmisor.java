package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ViewEmisor {

	private JFrame frmMessaje;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the application.
	 */
	public ViewEmisor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMessaje = new JFrame();
		frmMessaje.setTitle("Mensaje");
		frmMessaje.setBounds(100, 100, 432, 328);
		frmMessaje.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMessaje.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 230, 140));
		frmMessaje.getContentPane().add(panel);
		panel.setLayout(null);

		textField = new JTextField();
		textField.setFont(new Font("Calibri", Font.PLAIN, 15));
		textField.setBounds(107, 64, 116, 22);
		panel.add(textField);
		textField.setColumns(10);

		JLabel lblAsunto = new JLabel("Asunto");
		lblAsunto.setFont(new Font("Calibri", Font.BOLD, 16));
		lblAsunto.setBounds(12, 67, 56, 16);
		panel.add(lblAsunto);

		JLabel lblCuerpo = new JLabel("Cuerpo");
		lblCuerpo.setFont(new Font("Calibri", Font.BOLD, 16));
		lblCuerpo.setBounds(12, 101, 56, 16);
		panel.add(lblCuerpo);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Calibri", Font.PLAIN, 15));
		textField_1.setBounds(107, 30, 116, 22);
		panel.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblDestinatario = new JLabel("Destinatario");
		lblDestinatario.setFont(new Font("Calibri", Font.BOLD, 16));
		lblDestinatario.setBounds(12, 32, 94, 19);
		panel.add(lblDestinatario);

		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Calibri", Font.PLAIN, 14));
		textArea.setBounds(107, 98, 271, 104);
		panel.add(textArea);

		JRadioButton rdbtnSimple = new JRadioButton("Simple");
		rdbtnSimple.setBackground(new Color(240, 230, 140));
		rdbtnSimple.setFont(new Font("Calibri", Font.PLAIN, 14));
		rdbtnSimple.setBounds(107, 211, 74, 25);
		panel.add(rdbtnSimple);

		JRadioButton rdbtnAlerta = new JRadioButton("Alerta");
		rdbtnAlerta.setBackground(new Color(240, 230, 140));
		rdbtnAlerta.setFont(new Font("Calibri", Font.PLAIN, 14));
		rdbtnAlerta.setBounds(185, 211, 63, 25);
		panel.add(rdbtnAlerta);

		JRadioButton rdbtnAvisoDeRecepcion = new JRadioButton("Aviso de recepcion");
		rdbtnAvisoDeRecepcion.setBackground(new Color(240, 230, 140));
		rdbtnAvisoDeRecepcion.setFont(new Font("Calibri", Font.PLAIN, 14));
		rdbtnAvisoDeRecepcion.setBounds(251, 211, 140, 25);
		panel.add(rdbtnAvisoDeRecepcion);

		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setFont(new Font("Calibri", Font.BOLD, 18));
		btnEnviar.setBounds(107, 245, 97, 25);
		panel.add(btnEnviar);
		ImageIcon img = new ImageIcon("C:\\Users\\Lautaro\\eclipse-workspace\\tp_ayd1\\img\\flecha2.png");
	}
}
