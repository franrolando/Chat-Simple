package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ViewWelcome {

	private JFrame frmSendMessajes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewWelcome window = new ViewWelcome();
					window.frmSendMessajes.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewWelcome() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSendMessajes = new JFrame();
		frmSendMessajes.setBackground(new Color(240, 230, 140));
		frmSendMessajes.setTitle("Inicio SM");
		frmSendMessajes.setBounds(100, 100, 329, 195);
		frmSendMessajes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSendMessajes.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 230, 140));
		frmSendMessajes.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblBienvenido = new JLabel("Bienvenido a Send Messajes!");
		lblBienvenido.setBounds(0, 13, 317, 26);
		lblBienvenido.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 20));
		lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblBienvenido);

		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.setBackground(new Color(143, 188, 143));
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		btnIngresar.setBounds(60, 90, 100, 31);
		btnIngresar.setFont(new Font("Calibri", Font.BOLD, 18));
		panel.add(btnIngresar);

		JButton btnSalir = new JButton("Salir");
		btnSalir.setBackground(new Color(250, 128, 114));
		btnSalir.setBounds(172, 90, 80, 31);
		btnSalir.setFont(new Font("Calibri", Font.BOLD, 18));
		panel.add(btnSalir);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent click) {
				System.exit(0);
			}
		});
	}

}
