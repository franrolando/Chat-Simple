package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import javax.swing.ButtonGroup;
import javax.swing.border.LineBorder;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JList;
import com.jgoodies.forms.factories.DefaultComponentFactory;

public class ViewEmisor {

	private JFrame frmMensajeEmisor;
	private JTextField textFieldAsunto;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textFieldDestinatarios;
	private JTextField textFieldAsunto2;
	private JTextField textFieldDestinatarios2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewEmisor window = new ViewEmisor();
					window.frmMensajeEmisor.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
		frmMensajeEmisor = new JFrame();
		frmMensajeEmisor.setResizable(false);
		frmMensajeEmisor.setBackground(new Color(0, 0, 0));
		frmMensajeEmisor.setTitle("Mensaje Emisor");
		frmMensajeEmisor.setBounds(100, 100, 653, 462);
		frmMensajeEmisor.setSize(1000, 600);
		frmMensajeEmisor.setLocationRelativeTo(null);
		frmMensajeEmisor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMensajeEmisor.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.GRAY));
		panel.setBackground(new Color(240, 230, 140));
		frmMensajeEmisor.getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 6, 0, 0));
		
		JLabel lblDestinatarios2 = new JLabel("Destinatarios");
		lblDestinatarios2.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel.add(lblDestinatarios2);
		
		textFieldDestinatarios2 = new JTextField();
		panel.add(textFieldDestinatarios2);
		textFieldDestinatarios2.setColumns(10);
		
		JLabel lblAsunto2 = new JLabel("Asunto");
		lblAsunto2.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel.add(lblAsunto2);
		
		textFieldAsunto2 = new JTextField();
		panel.add(textFieldAsunto2);
		textFieldAsunto2.setColumns(10);
		
		JCheckBox chckbxRecibido = new JCheckBox("Recibido");
		chckbxRecibido.setFont(new Font("Tahoma", Font.BOLD, 13));
		chckbxRecibido.setBackground(new Color(240, 230, 140));
		panel.add(chckbxRecibido);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEliminar.setBackground(new Color(240, 230, 140));
		panel.add(btnEliminar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.GRAY));
		frmMensajeEmisor.getContentPane().add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(240, 230, 140));
		panel_1.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_10 = new JPanel();
		panel_10.setBackground(new Color(240, 230, 140));
		panel_2.add(panel_10, BorderLayout.NORTH);
		
		JLabel lblContactos = new JLabel("Contactos");
		lblContactos.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_10.add(lblContactos);
		
		JComboBox comboBoxContactos = new JComboBox();
		panel_10.add(comboBoxContactos);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBackground(new Color(240, 230, 140));
		FlowLayout flowLayout_3 = (FlowLayout) panel_11.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_2.add(panel_11, BorderLayout.CENTER);
		
		JLabel lblDestinatarios = new JLabel("Destinatarios");
		lblDestinatarios.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_11.add(lblDestinatarios);
		
		textFieldDestinatarios = new JTextField();
		panel_11.add(textFieldDestinatarios);
		textFieldDestinatarios.setColumns(35);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(240, 230, 140));
		panel_1.add(panel_3, BorderLayout.SOUTH);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_3.add(btnEnviar);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(240, 230, 140));
		panel_1.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(240, 230, 140));
		FlowLayout flowLayout = (FlowLayout) panel_5.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_4.add(panel_5, BorderLayout.NORTH);
		
		JLabel lblAsunto = new JLabel("Asunto  ");
		lblAsunto.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_5.add(lblAsunto);
		
		textFieldAsunto = new JTextField();
		panel_5.add(textFieldAsunto);
		textFieldAsunto.setColumns(38);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(240, 230, 140));
		panel_4.add(panel_6, BorderLayout.SOUTH);
		
		JRadioButton rdbtnSimple = new JRadioButton("Simple");
		rdbtnSimple.setSelected(true);
		buttonGroup.add(rdbtnSimple);
		rdbtnSimple.setBackground(new Color(240, 230, 140));
		rdbtnSimple.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_6.add(rdbtnSimple);
		
		JRadioButton rdbtnAlerta = new JRadioButton("Alerta");
		buttonGroup.add(rdbtnAlerta);
		rdbtnAlerta.setBackground(new Color(240, 230, 140));
		rdbtnAlerta.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_6.add(rdbtnAlerta);
		
		JRadioButton rdbtnAvisoDeRecepcion = new JRadioButton("Aviso de Recepcion");
		buttonGroup.add(rdbtnAvisoDeRecepcion);
		rdbtnAvisoDeRecepcion.setBackground(new Color(240, 230, 140));
		rdbtnAvisoDeRecepcion.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_6.add(rdbtnAvisoDeRecepcion);
		
		JPanel panel_7 = new JPanel();
		panel_4.add(panel_7, BorderLayout.CENTER);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(new Color(240, 230, 140));
		FlowLayout flowLayout_1 = (FlowLayout) panel_8.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_7.add(panel_8, BorderLayout.NORTH);
		
		JLabel lblCuerpo = new JLabel("Cuerpo");
		lblCuerpo.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_8.add(lblCuerpo);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(new Color(240, 230, 140));
		FlowLayout flowLayout_2 = (FlowLayout) panel_9.getLayout();
		panel_7.add(panel_9, BorderLayout.CENTER);
		
		JTextArea textAreaCuerpo = new JTextArea();
		textAreaCuerpo.setLineWrap(true);
		textAreaCuerpo.setRows(25);
		textAreaCuerpo.setColumns(43);
		panel_9.add(textAreaCuerpo);
	}

}
