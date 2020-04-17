package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;
import java.net.ConnectException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controlador.ControladorEmisor;
import modelo.Emisor;

public class ViewWE {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				new ViewWE();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public ViewWE() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		JFrame frmInicioEmisor = new JFrame();
		frmInicioEmisor.getContentPane().setBackground(new Color(240, 230, 140));
		frmInicioEmisor.setTitle("Inicio SM");
		frmInicioEmisor.setBounds(100, 100, 450, 300);
		frmInicioEmisor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmInicioEmisor.setSize(450, 250);
		frmInicioEmisor.setLocationRelativeTo(null);
		frmInicioEmisor.setVisible(true);

		JPanel panelLblInicio = new JPanel();
		panelLblInicio.setBackground(new Color(240, 230, 140));
		frmInicioEmisor.getContentPane().add(panelLblInicio, BorderLayout.CENTER);
		panelLblInicio.setLayout(new BorderLayout(0, 0));

		JLabel lblInicioEmisor = new JLabel("Inicio Emisor");
		lblInicioEmisor.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblInicioEmisor.setHorizontalAlignment(SwingConstants.CENTER);
		panelLblInicio.add(lblInicioEmisor);

		JPanel panelDatos = new JPanel();
		panelDatos.setBackground(new Color(240, 230, 140));
		frmInicioEmisor.getContentPane().add(panelDatos, BorderLayout.SOUTH);
		panelDatos.setLayout(new GridLayout(0, 1, 0, 0));

		panelDatos.setBackground(new Color(240, 230, 140));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 230, 140));
		panelDatos.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblNombre = new JLabel("        Nombre:");
		panel.add(lblNombre);
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 14));

		JTextField txtNombre = new JTextField();
		panel.add(txtNombre);
		txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNombre.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(240, 230, 140));
		panelDatos.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblDirectorio = new JLabel("IP Directorio:");
		lblDirectorio.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1.add(lblDirectorio);
		lblDirectorio.setFont(new Font("Tahoma", Font.BOLD, 14));

		JTextField txtDirectorio = new JTextField();
		panel_1.add(txtDirectorio);
		txtDirectorio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDirectorio.setColumns(10);

		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		panel_2.setBackground(new Color(240, 230, 140));
		panelDatos.add(panel_2);

		JButton btnIngresar = new JButton("Ingresar", new ImageIcon("./src/main/img/flecha.png"));
		btnIngresar.setVerticalAlignment(SwingConstants.BOTTOM);
		btnIngresar.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_2.add(btnIngresar);
		btnIngresar.setFont(new Font("Tahoma", Font.BOLD, 14));

		JButton btnSalir = new JButton("Salir", new ImageIcon("./src/main/img/exit.png"));
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_2.add(btnSalir);
		btnSalir.addActionListener(e -> System.exit(0));

		btnIngresar.addActionListener(e -> {
			if (!txtNombre.getText().isEmpty() && !txtDirectorio.getText().isEmpty()) {
				ControladorEmisor.getInstance().setIpDirectorio(txtDirectorio.getText());
				Emisor emisor = new Emisor();
				emisor.setNombreUsuario(txtNombre.getText());
				try {
					emisor.setListaContactos(ControladorEmisor.getInstance().getContactList());
					new ViewEmisor(emisor);
					frmInicioEmisor.dispose();
				} catch (IOException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(frmInicioEmisor, "Ocurrieron problemas al conectar con el servicio del directorio");
				}
			}
		});
	}

}
