package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ViewWelcome {

	private JFrame frmInicioSm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				ViewWelcome window = new ViewWelcome();
				window.frmInicioSm.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint 
	 */
	public ViewWelcome() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmInicioSm = new JFrame();
		frmInicioSm.setTitle("Inicio SM");
		frmInicioSm.setBounds(100, 100, 642, 306);
		frmInicioSm.setSize(500,250);
		frmInicioSm.setLocationRelativeTo(null);
		frmInicioSm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 230, 140));
		frmInicioSm.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblBienvenido = new JLabel("Bienvenido a Send Message!");
		lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenido.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 20));
		lblBienvenido.setBackground(new Color(240, 230, 140));
		panel.add(lblBienvenido, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(240, 230, 140));
		panel.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnEmisor = new JButton("Emisor");
		btnEmisor.setFont(new Font("Calibri", Font.BOLD, 18));
		btnEmisor.setBackground(new Color(143, 188, 143));
		btnEmisor.addActionListener( e -> {
			new ViewEmisor();
			frmInicioSm.dispose();
		});
		panel_1.add(btnEmisor);
		
		JButton btnReceptor = new JButton("Receptor");
		btnReceptor.setFont(new Font("Calibri", Font.BOLD, 18));
		btnReceptor.setBackground(new Color(173, 216, 230));
		btnReceptor.addActionListener( e -> {
			new ViewReceptor();
			frmInicioSm.dispose();
		});
		panel_1.add(btnReceptor);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Calibri", Font.BOLD, 18));
		btnSalir.setBackground(new Color(255, 160, 122));
		panel_1.add(btnSalir);
		btnSalir.addActionListener(click -> System.exit(0));
	}

}
