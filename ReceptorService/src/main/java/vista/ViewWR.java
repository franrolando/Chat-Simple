package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controlador.ControladorReceptor;
import modelo.Receptor;

public class ViewWR {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new ViewWR();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewWR() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		JFrame frmInicioReceptor = new JFrame();
		frmInicioReceptor.getContentPane().setBackground(new Color(240, 230, 140));
		frmInicioReceptor.getContentPane().setLayout(new BorderLayout(0, 0));
		frmInicioReceptor.setBackground(new Color(240, 230, 140));
		frmInicioReceptor.setTitle("Inicio SM");
		frmInicioReceptor.setBounds(100, 100, 450, 300);
		frmInicioReceptor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmInicioReceptor.setSize(450, 250);
		frmInicioReceptor.setLocationRelativeTo(null);
		frmInicioReceptor.setVisible(true);

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

		JLabel lblNombre = new JLabel("        Nombre:");
		panel_1.add(lblNombre);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 14));

		JTextField textFieldNombre = new JTextField();
		panel_1.add(textFieldNombre);
		textFieldNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldNombre.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldNombre.setColumns(10);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(240, 230, 140));
		panelDatos.add(panel_3);

		JLabel lblDirectorio = new JLabel("IP Directorio:");
		lblDirectorio.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_3.add(lblDirectorio);

		JTextField txtDirectorio = new JTextField();
		txtDirectorio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_3.add(txtDirectorio);
		txtDirectorio.setColumns(10);

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
			if (!textFieldNombre.getText().isEmpty() && !txtDirectorio.getText().isEmpty()) {
				ControladorReceptor.getInstance().setIpDirectorio(txtDirectorio.getText());
				Receptor receptor = new Receptor();
				receptor.setNombreUsuario(textFieldNombre.getText());
				receptor.setConectado(true);
				new ViewReceptor(receptor);
				ControladorReceptor.getInstance().sendStatus(receptor);
				frmInicioReceptor.dispose();
			}
		});
	}

}
