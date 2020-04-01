package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;

public class ViewWelcome {

	private JFrame frmInicioSm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				ViewWelcome vWelcome = new ViewWelcome();
				vWelcome.frmInicioSm.setVisible(true);
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
	private void initialize()  {
		frmInicioSm = new JFrame();
		frmInicioSm.setTitle("Inicio SM");
		frmInicioSm.setBounds(100, 100, 642, 306);
		frmInicioSm.setSize(500,250);
		frmInicioSm.setLocationRelativeTo(null);
		frmInicioSm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ImageIcon imgWelcome = new ImageIcon("./src/main/img/email-icon.png");
		frmInicioSm.setIconImage(imgWelcome.getImage());
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 230, 140));
		frmInicioSm.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(240, 230, 140));
		panel.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnEmisor = new JButton("Emisor");
		btnEmisor.setFont(new Font("Calibri", Font.BOLD, 18));
		btnEmisor.setBackground(new Color(143, 188, 143));
		btnEmisor.addActionListener(e -> {
			// Abre la pantalla de emisor
			ViewEmisor vEmisor = new ViewEmisor();
		});
		panel_1.add(btnEmisor);
		
		JButton btnReceptor = new JButton("Receptor");
		btnReceptor.setFont(new Font("Calibri", Font.BOLD, 18));
		btnReceptor.setBackground(new Color(173, 216, 230));
		btnReceptor.addActionListener(e -> {
			// Abre la pantalla de receptor
			ViewReceptor vReceptor = new ViewReceptor();
		});
		panel_1.add(btnReceptor);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Calibri", Font.BOLD, 18));
		btnSalir.setBackground(new Color(255, 160, 122));
		panel_1.add(btnSalir);
		btnSalir.addActionListener(click -> System.exit(0)); // Sale del sistema
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(240, 230, 140));
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblBienvenido = new JLabel("Bienvenido a Send Message!");
		panel_2.add(lblBienvenido);
		lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenido.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 20));
		lblBienvenido.setBackground(new Color(240, 230, 140));
		
		ImageIcon imgBienvenido = new ImageIcon("./src/main/img/mail.png");
		JLabel lblIcon = new JLabel(imgBienvenido);
		lblIcon.setVerticalAlignment(SwingConstants.TOP);
		panel_2.add(lblIcon);		
	}

}
