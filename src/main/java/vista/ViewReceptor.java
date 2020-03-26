package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import javax.swing.JTabbedPane;

public class ViewReceptor {

	private JFrame frmMensajeReceptor;
	private JTextField textFieldPuerto;
	private JTextField textFieldAsunto;

	/**
	 * Create the application.
	 */
	public ViewReceptor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMensajeReceptor = new JFrame();
		frmMensajeReceptor.setTitle("Mensaje Receptor");
		frmMensajeReceptor.setBounds(100, 100, 450, 300);
		frmMensajeReceptor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMensajeReceptor.setVisible(true);
		
		JPanel panel = new JPanel();
		frmMensajeReceptor.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(240, 230, 140));
		panel.add(panel_1, BorderLayout.NORTH);
		
		JLabel lblPuerto = new JLabel("Puerto");
		lblPuerto.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_1.add(lblPuerto);
		
		textFieldPuerto = new JTextField();
		panel_1.add(textFieldPuerto);
		textFieldPuerto.setColumns(10);
		
		JButton btnEscuchar = new JButton("Escuchar");
		panel_1.add(btnEscuchar);
		btnEscuchar.setFont(new Font("Calibri", Font.BOLD, 16));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 13));
		tabbedPane.setBackground(new Color(240, 230, 140));
		panel.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(240, 230, 140));
		tabbedPane.addTab("New tab", null, panel_3, null);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_4.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_4.setBackground(new Color(240, 230, 140));
		panel_3.add(panel_4, BorderLayout.NORTH);
		
		JLabel lblAsunto = new JLabel("Asunto");
		lblAsunto.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_4.add(lblAsunto);
		
		textFieldAsunto = new JTextField();
		panel_4.add(textFieldAsunto);
		textFieldAsunto.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(240, 230, 140));
		panel_3.add(panel_2, BorderLayout.SOUTH);
		
		JButton btnSilenciar = new JButton("Silenciar");
		btnSilenciar.setFont(new Font("Calibri", Font.BOLD, 16));
		panel_2.add(btnSilenciar);
		
		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_5.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_5.setBackground(new Color(240, 230, 140));
		panel_3.add(panel_5, BorderLayout.CENTER);
		
		JLabel lblCuerpo = new JLabel("Cuerpo");
		lblCuerpo.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_5.add(lblCuerpo);
		
		JTextArea textAreaCuerpo = new JTextArea();
		textAreaCuerpo.setColumns(45);
		textAreaCuerpo.setRows(5);
		panel_5.add(textAreaCuerpo);
	}

}
