package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import controlador.ControladorReceptor;
import modelo.Receptor;

public class ViewWR {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				new ViewWR();
			} catch (Exception e) {

			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public ViewWR() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		JFrame frmInicioReceptor = new JFrame();
		frmInicioReceptor.setResizable(false);
		frmInicioReceptor.getContentPane().setBackground(new Color(240, 230, 140));
		frmInicioReceptor.getContentPane().setLayout(new BorderLayout(0, 0));
		frmInicioReceptor.setBackground(new Color(240, 230, 140));
		frmInicioReceptor.setTitle("Inicio SM");
		frmInicioReceptor.setBounds(100, 100, 450, 300);
		frmInicioReceptor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmInicioReceptor.setSize(500, 200);
		frmInicioReceptor.setLocationRelativeTo(null);
		frmInicioReceptor.setVisible(true);

		ImageIcon imgReceptor = new ImageIcon("./src/main/img/email-icon.png");
		frmInicioReceptor.setIconImage(imgReceptor.getImage());

		JPanel panelLblInicio = new JPanel();
		panelLblInicio.setBackground(new Color(240, 230, 140));
		frmInicioReceptor.getContentPane().add(panelLblInicio, BorderLayout.CENTER);
		panelLblInicio.setLayout(new BorderLayout(0, 0));

		JLabel lblInicioReceptor = new JLabel("Inicio Receptor");
		lblInicioReceptor.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblInicioReceptor.setHorizontalAlignment(SwingConstants.CENTER);
		panelLblInicio.add(lblInicioReceptor, BorderLayout.CENTER);

		JPanel panelDatos = new JPanel();
		frmInicioReceptor.getContentPane().add(panelDatos, BorderLayout.SOUTH);
		panelDatos.setBackground(new Color(240, 230, 140));
		panelDatos.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(240, 230, 140));
		panelDatos.add(panel_1);

		JLabel lblNombre = new JLabel("Nombre:");
		panel_1.add(lblNombre);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 14));

		JTextField textFieldNombre = new JTextField();
		panel_1.add(textFieldNombre);
		textFieldNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldNombre.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldNombre.setColumns(10);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(240, 230, 140));
		panelDatos.add(panel_4);

		JButton btnIngresar = new JButton("Ingresar", new ImageIcon("./src/main/img/flecha.png"));
		panel_4.add(btnIngresar);
		btnIngresar.setFont(new Font("Tahoma", Font.BOLD, 14));

		JButton btnSalir = new JButton("Salir", new ImageIcon("./src/main/img/exit.png"));
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_4.add(btnSalir);
		btnSalir.addActionListener(e -> System.exit(0));

		btnIngresar.addActionListener(e -> {
			if (!textFieldNombre.getText().isEmpty()) {
				Receptor receptor = new Receptor();
				receptor.setNombreUsuario(textFieldNombre.getText());
				receptor.setConectado(true);
				UIManager.put("OptionPane.background", new Color(205, 122, 122));
				UIManager.put("OptionPane.messageFont", new Font("Tahoma", Font.BOLD, 13));
				UIManager.put("Panel.background", new Color(205, 122, 122));
				UIManager.put("Button.font", new Font("Tahoma", Font.BOLD, 13));
				try {
					if (ControladorReceptor.getInstance().nombreValido(receptor.getNombreUsuario())) {
						ControladorReceptor.getInstance().sendStatus(receptor);
						new ViewReceptor(receptor);
						frmInicioReceptor.dispose();
					} else {
						JOptionPane.showMessageDialog(frmInicioReceptor,
								"El nombre ingresado ya esta cargado en el directorio.", "SERVER ERROR",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(frmInicioReceptor,
							"Ocurrieron problemas al conectarse con el servicio del directorio.", "SERVER ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

}
