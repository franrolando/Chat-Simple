package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import controlador.ControladorEmisor;
import modelo.Mensaje;
import modelo.MensajeAlerta;
import modelo.MensajeAvisoRecep;
import modelo.Receptor;

public class ViewEmisor {

	private List<Receptor> destinos = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void AbrirEmisor() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewEmisor window = new ViewEmisor();
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
		JFrame frmMensajeEmisor = new JFrame();
		frmMensajeEmisor.setResizable(false);
		frmMensajeEmisor.setBackground(new Color(0, 0, 0));
		frmMensajeEmisor.setTitle("Mensaje Emisor");
		frmMensajeEmisor.setBounds(100, 100, 653, 462);
		frmMensajeEmisor.setSize(1000, 600);
		frmMensajeEmisor.setLocationRelativeTo(null);
		frmMensajeEmisor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMensajeEmisor.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		frmMensajeEmisor.setVisible(true);

		ImageIcon imgEmisor = new ImageIcon("./src/main/img/email-icon.png");
		frmMensajeEmisor.setIconImage(imgEmisor.getImage());

		JScrollPane scrollPane1 = new JScrollPane();
		frmMensajeEmisor.getContentPane().add(scrollPane1);

		JPanel panelM = new JPanel();
		panelM.setBackground(new Color(240, 230, 140));
		scrollPane1.setColumnHeaderView(panelM);

		JLabel lblMensajesConAviso = new JLabel("Mensajes con aviso de recepci\u00F3n:");
		lblMensajesConAviso.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelM.add(lblMensajesConAviso);

		JPanel panelMensajesConAviso = new JPanel();
		panelMensajesConAviso.setBackground(new Color(240, 230, 140));
		scrollPane1.setViewportView(panelMensajesConAviso);
		panelMensajesConAviso.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panelNuevoMensaje = new JPanel();
		panelNuevoMensaje.setBorder(new LineBorder(Color.GRAY));
		frmMensajeEmisor.getContentPane().add(panelNuevoMensaje);
		panelNuevoMensaje.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(240, 230, 140));
		panelNuevoMensaje.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BorderLayout(0, 0));

		JPanel panel_11 = new JPanel();
		panel_11.setBackground(new Color(240, 230, 140));
		FlowLayout flowLayout_3 = (FlowLayout) panel_11.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_2.add(panel_11, BorderLayout.CENTER);

		JLabel lblDestinatarios = new JLabel("Destinatarios");
		lblDestinatarios.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_11.add(lblDestinatarios);

		JTextField textFieldDestinatarios = new JTextField();
		textFieldDestinatarios.setEnabled(false);
		panel_11.add(textFieldDestinatarios);
		textFieldDestinatarios.setColumns(35);

		JPanel panel_10 = new JPanel();
		panel_2.add(panel_10, BorderLayout.NORTH);
		panel_10.setBackground(new Color(240, 230, 140));
		panel_10.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 230, 140));
		panel_10.add(panel);

		JLabel lblContactos = new JLabel("Contactos");
		panel.add(lblContactos);
		lblContactos.setFont(new Font("Tahoma", Font.BOLD, 13));

		JComboBox<Receptor> comboBoxContactos = new JComboBox<>();
		panel.add(comboBoxContactos);
		getListaContactos().stream().forEach(receptor -> {
			comboBoxContactos.addItem(receptor);
		});

		comboBoxContactos.addActionListener(event -> {
			Receptor receptor = (Receptor) comboBoxContactos.getSelectedItem();
			if (!textFieldDestinatarios.getText().contains(receptor.getNombreUsuario().toString())) {
				destinos.add(receptor);
				textFieldDestinatarios.setText(textFieldDestinatarios.getText().isEmpty() ? receptor.getNombreUsuario()
						: textFieldDestinatarios.getText() + "; " + receptor.getNombreUsuario());
			}
		});

		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_1.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel_1.setBackground(new Color(240, 230, 140));
		panel_10.add(panel_1);

		JLabel lblEmisor = new JLabel("Emisor");
		lblEmisor.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_1.add(lblEmisor);

		JTextField textFieldEmisor = new JTextField();
		panel_1.add(textFieldEmisor);
		textFieldEmisor.setColumns(39);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(240, 230, 140));
		panelNuevoMensaje.add(panel_3, BorderLayout.SOUTH);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(240, 230, 140));
		panelNuevoMensaje.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(240, 230, 140));
		FlowLayout flowLayout = (FlowLayout) panel_5.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_4.add(panel_5, BorderLayout.NORTH);

		JLabel lblAsunto = new JLabel("Asunto  ");
		lblAsunto.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_5.add(lblAsunto);

		JTextField textFieldAsunto = new JTextField();
		panel_5.add(textFieldAsunto);
		textFieldAsunto.setColumns(38);

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(240, 230, 140));
		panel_4.add(panel_6, BorderLayout.SOUTH);

		JRadioButton rdbtnSimple = new JRadioButton("Simple");
		rdbtnSimple.setSelected(true);
		ButtonGroup buttonGroup = new ButtonGroup();
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
		panel_7.add(panel_9, BorderLayout.CENTER);
		panel_9.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_9.add(scrollPane);

		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);

		ImageIcon imgEnviar = new ImageIcon("./src/main/img/ok.png");
		JButton btnEnviar = new JButton("Enviar", imgEnviar);
		btnEnviar.setEnabled(false);

		textArea.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				btnEnviar.setEnabled(!textArea.getText().isEmpty());
			}

			@Override
			public void keyPressed(KeyEvent e) {
				btnEnviar.setEnabled(!textArea.getText().isEmpty());
			}

			@Override
			public void keyReleased(KeyEvent e) {
				btnEnviar.setEnabled(!textArea.getText().isEmpty());
			}

		});

		btnEnviar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				final Mensaje mensaje;
				if (rdbtnAvisoDeRecepcion.isSelected()) {
					mensaje = new MensajeAvisoRecep();
					completeDTO(mensaje);
					Map<String, Boolean> resp = ControladorEmisor.getInstance().enviarMensajeAvisoRecepcion(mensaje,
							destinos);
					resp.forEach((emisor, recibido) -> {
						creaNuevoMensajeAviso(frmMensajeEmisor, panelMensajesConAviso, mensaje.getAsunto(), emisor,
								recibido);
					});
				} else {
					mensaje = rdbtnAlerta.isSelected() ? new MensajeAlerta() : new Mensaje();
					completeDTO(mensaje);
					ControladorEmisor.getInstance().enviarMensajeSimple(mensaje, destinos);
				}
				btnEnviar.setEnabled(false);
				textArea.setText("");
				textFieldAsunto.setText("");
				textFieldDestinatarios.setText("");
				destinos.clear();
			}

			private void completeDTO(Mensaje mensaje) {
				mensaje.setAsunto(textFieldAsunto.getText());
				mensaje.setCuerpo(textArea.getText());
				mensaje.setEmisor(textFieldEmisor.getText());
			}

		});
		btnEnviar.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_3.add(btnEnviar);
	}

	private List<Receptor> getListaContactos() {
		List<Receptor> listaContactos = new ArrayList();
		Scanner input = null;
		try {
			input = new Scanner(new File("./src/main/resources/ListaContactos"));
			while (input.hasNextLine()) {
				Receptor receptor = new Receptor();
				String line = input.nextLine();
				String[] datos = line.split(" ");
				receptor.setNombreUsuario(datos[0]);
				receptor.setIp(datos[1]);
				listaContactos.add(receptor);
				System.out.println(line);
			}
			input.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (!Objects.isNull(input)) {
				input.close();
			}
		}
		return listaContactos;
	}

	private void creaNuevoMensajeAviso(JFrame frmMensajeEmisor, JPanel panelMensajesConAviso, String asunto,
			String receptor, Boolean recibido) {

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.DARK_GRAY, 1, true));
		panelMensajesConAviso.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_13 = new JPanel();
		panel.add(panel_13, BorderLayout.NORTH);
		panel_13.setBackground(Color.LIGHT_GRAY);
		FlowLayout flowLayout_4 = (FlowLayout) panel_13.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);

		JLabel lblReceptor = new JLabel("Receptor");
		lblReceptor.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_13.add(lblReceptor);

		JTextField textFieldReceptor = new JTextField();
		textFieldReceptor.setText(receptor);
		panel_13.add(textFieldReceptor);
		textFieldReceptor.setColumns(36);

		JPanel panel_14 = new JPanel();
		panel.add(panel_14);
		panel_14.setBackground(Color.LIGHT_GRAY);
		FlowLayout flowLayout_5 = (FlowLayout) panel_14.getLayout();
		flowLayout_5.setAlignment(FlowLayout.LEFT);

		JLabel lblAsunto2 = new JLabel("Asunto ");
		lblAsunto2.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_14.add(lblAsunto2);

		JTextField textFieldAsunto2 = new JTextField();
		textFieldAsunto2.setText(asunto);
		panel_14.add(textFieldAsunto2);
		textFieldAsunto2.setColumns(37);

		JPanel panel_15 = new JPanel();
		panel.add(panel_15, BorderLayout.SOUTH);
		panel_15.setBackground(Color.LIGHT_GRAY);

		JCheckBox chckbxRecibido = new JCheckBox("Recibido");
		chckbxRecibido.setBackground(Color.LIGHT_GRAY);
		chckbxRecibido.setFont(new Font("Tahoma", Font.BOLD, 14));
		chckbxRecibido.setEnabled(false);
		chckbxRecibido.setSelected(recibido);
		panel_15.add(chckbxRecibido);
		JButton buttonEliminar = new JButton("Eliminar", new ImageIcon("./src/main/img/cruz-eliminar.png"));
		buttonEliminar.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_15.add(buttonEliminar);
		buttonEliminar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				panelMensajesConAviso.remove(panel);
				panelMensajesConAviso.validate();
				panelMensajesConAviso.repaint();
			}

		});

		frmMensajeEmisor.validate();
		frmMensajeEmisor.repaint();
	}

}
