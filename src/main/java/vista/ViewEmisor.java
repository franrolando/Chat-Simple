package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.CardLayout;
import javax.swing.JComboBox;

public class ViewEmisor {

	private JFrame frmMensaje;
	private JTextField textFieldPuerto;
	private JLabel lblPuerto;
	private JPanel panel_1;
	private JButton btnEnviar;
	private JPanel panel_2;
	private JPanel panel_3;
	private JTextField textFieldAsunto;
	private JLabel lblAsunto;
	private JPanel panel_4;
	private JRadioButton rdbtnSimple;
	private JRadioButton rdbtnAlerta;
	private JRadioButton rdbtnAvisoDeRecepcion;
	private JPanel panel_5;
	private JLabel lblCuerpo;
	private JTextArea textAreaCuerpo;
	private JComboBox<String> comboBoxIP;


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
		frmMensaje = new JFrame();
		frmMensaje.getContentPane().setBackground(new Color(240, 230, 140));
		frmMensaje.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 230, 140));
		frmMensaje.getContentPane().add(panel, BorderLayout.NORTH);

		JLabel lblContactos = new JLabel("Contactos");
		lblContactos.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel.add(lblContactos);
		
		JComboBox<String> comboBoxContactos = new JComboBox<>();
		panel.add(comboBoxContactos);
		
		JLabel lblIP = new JLabel("Para");
		lblIP.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel.add(lblIP);
		
		comboBoxIP = new JComboBox<>();
		panel.add(comboBoxIP);
		
		lblPuerto = new JLabel("Puerto");
		lblPuerto.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel.add(lblPuerto);
		
		textFieldPuerto = new JTextField();
		panel.add(textFieldPuerto);
		textFieldPuerto.setColumns(10);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(240, 230, 140));
		frmMensaje.getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		btnEnviar = new JButton("Enviar");
		btnEnviar.setFont(new Font("Calibri", Font.BOLD, 18));
		panel_1.add(btnEnviar);
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(240, 230, 140));
		frmMensaje.getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		panel_3 = new JPanel();
		panel_3.setBackground(new Color(240, 230, 140));
		panel_2.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		lblAsunto = new JLabel("Asunto");
		lblAsunto.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAsunto.setHorizontalAlignment(SwingConstants.LEFT);
		panel_3.add(lblAsunto);
		
		textFieldAsunto = new JTextField();
		textFieldAsunto.setHorizontalAlignment(SwingConstants.LEFT);
		panel_3.add(textFieldAsunto);
		textFieldAsunto.setColumns(10);
		
		panel_4 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_4.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_4.setBackground(new Color(240, 230, 140));
		panel_2.add(panel_4, BorderLayout.SOUTH);
		
		rdbtnSimple = new JRadioButton("Simple");
		rdbtnSimple.setFont(new Font("Tahoma", Font.BOLD, 13));
		rdbtnSimple.setBackground(new Color(240, 230, 140));
		panel_4.add(rdbtnSimple);
		
		rdbtnAlerta = new JRadioButton("Alerta");
		rdbtnAlerta.setFont(new Font("Tahoma", Font.BOLD, 13));
		rdbtnAlerta.setBackground(new Color(240, 230, 140));
		panel_4.add(rdbtnAlerta);
		
		rdbtnAvisoDeRecepcion = new JRadioButton("Aviso de recepcion");
		rdbtnAvisoDeRecepcion.setFont(new Font("Tahoma", Font.BOLD, 13));
		rdbtnAvisoDeRecepcion.setBackground(new Color(240, 230, 140));
		panel_4.add(rdbtnAvisoDeRecepcion);
		
		panel_5 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_5.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_5.setBackground(new Color(240, 230, 140));
		panel_2.add(panel_5, BorderLayout.CENTER);
		
		lblCuerpo = new JLabel("Cuerpo");
		lblCuerpo.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_5.add(lblCuerpo);
		
		textAreaCuerpo = new JTextArea();
		textAreaCuerpo.setColumns(45);
		textAreaCuerpo.setRows(5);
		panel_5.add(textAreaCuerpo);
		frmMensaje.setTitle("Mensaje Emisor");
		frmMensaje.setBounds(100, 100, 450, 300);
		frmMensaje.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
