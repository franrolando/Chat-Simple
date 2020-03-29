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

	private JFrame frmReceptor;
	private JTextField textFieldAsunto;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				ViewReceptor window = new ViewReceptor();
				window.frmReceptor.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
		
	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public ViewReceptor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmReceptor = new JFrame();
		frmReceptor.setResizable(false);
		frmReceptor.setTitle("Mensaje Receptor");
		frmReceptor.setBounds(100, 100, 704, 474);
		frmReceptor.setSize(700,500);
		frmReceptor.setLocationRelativeTo(null);
		frmReceptor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmReceptor.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 230, 140));
		frmReceptor.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
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
		textFieldAsunto.setColumns(55);
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_2.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_2.setBackground(new Color(240, 230, 140));
		panel_3.add(panel_2, BorderLayout.SOUTH);
		
		JButton btnSilenciar = new JButton("Silenciar");
		btnSilenciar.setFont(new Font("Calibri", Font.BOLD, 16));
		panel_2.add(btnSilenciar);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(240, 230, 140));
		panel_3.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_1.setBackground(new Color(240, 230, 140));
		panel_5.add(panel_1, BorderLayout.NORTH);
		
		JLabel lblCuerpo = new JLabel("Cuerpo");
		lblCuerpo.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_1.add(lblCuerpo);
		
		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_6.getLayout();
		panel_6.setBackground(new Color(240, 230, 140));
		panel_5.add(panel_6, BorderLayout.CENTER);
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setRows(21);
		textArea.setColumns(61);
		panel_6.add(textArea);
	}

}
