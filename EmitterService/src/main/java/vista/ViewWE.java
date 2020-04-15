package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
		frmInicioEmisor.setSize(675, 250);
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

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelDatos.add(lblNombre);

		JTextField txtNombre = new JTextField();
		txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNombre.setColumns(10);
		panelDatos.add(txtNombre);

		panelDatos.setBackground(new Color(240, 230, 140));
		JLabel lblDirectorio = new JLabel("IP Directorio");
		lblDirectorio.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelDatos.add(lblDirectorio);
		JTextField txtDirectorio = new JTextField();
		txtDirectorio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDirectorio.setColumns(10);
		panelDatos.add(txtDirectorio);

		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelDatos.add(btnIngresar);

		btnIngresar.addActionListener(e -> {
			if (!txtNombre.getText().isEmpty()) {
				Emisor emisor = new Emisor();
				emisor.setNombreUsuario(txtNombre.getText());
				emisor.setListaContactos(ControladorEmisor.getInstance().getContactList());
				new ViewEmisor(emisor);
				frmInicioEmisor.dispose();
			}
		});

	}

}
