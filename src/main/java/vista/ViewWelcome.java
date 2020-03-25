package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;

public class ViewWelcome {

	private JFrame frmInicioSm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewWelcome window = new ViewWelcome();
					window.frmInicioSm.setVisible(true);
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
		frmInicioSm = new JFrame();
		frmInicioSm.setTitle("Inicio SM");
		frmInicioSm.setBounds(100, 100, 440, 168);
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
		panel_1.add(btnEmisor);
		
		JButton btnReceptor = new JButton("Receptor");
		btnReceptor.setFont(new Font("Calibri", Font.BOLD, 18));
		btnReceptor.setBackground(new Color(173, 216, 230));
		panel_1.add(btnReceptor);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Calibri", Font.BOLD, 18));
		btnSalir.setBackground(new Color(255, 160, 122));
		panel_1.add(btnSalir);
		btnSalir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent click) {
				System.exit(0);
			}
		});
	}

}
