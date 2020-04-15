package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.FlowLayout;

public class ViewWR {

	private JFrame frmInicioReceptor;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewWR window = new ViewWR();
					window.frmInicioReceptor.setVisible(true);
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
		frmInicioReceptor = new JFrame();
		frmInicioReceptor.getContentPane().setBackground(new Color(240, 230, 140));
		frmInicioReceptor.getContentPane().setLayout(new BorderLayout(0, 0));
		frmInicioReceptor.setBackground(new Color(240, 230, 140));
		frmInicioReceptor.setTitle("Inicio SM");
		frmInicioReceptor.setBounds(100, 100, 450, 300);
		frmInicioReceptor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmInicioReceptor.setSize(450,250);
		frmInicioReceptor.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 230, 140));
		frmInicioReceptor.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblInicioReceptor = new JLabel("Inicio Receptor");
		lblInicioReceptor.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblInicioReceptor.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblInicioReceptor, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		frmInicioReceptor.getContentPane().add(panel_2, BorderLayout.SOUTH);
		panel_2.setBackground(new Color(240, 230, 140));
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_2.add(lblNombre);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_2.add(btnIngresar);
		
		btnIngresar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ABRE PANTALLA RECEPTOR
				if(!textField.getText().isEmpty()) {
					ViewReceptor vReceptor = new ViewReceptor();
				}
			}	
		});
	}

}
