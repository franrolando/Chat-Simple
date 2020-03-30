package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

public class ViewReceptor implements ActionListener{

	private JFrame frmReceptor;
	private JTextField textFieldAsunto;
	private int i=1;
	
	public static void AbrirReceptor() {
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
		frmReceptor.getContentPane().setBackground(new Color(240, 230, 140));
		frmReceptor.setResizable(false);
		frmReceptor.setTitle("Mensaje Receptor");
		frmReceptor.setBounds(100, 100, 704, 474);
		frmReceptor.setSize(700,500);
		frmReceptor.setLocationRelativeTo(null);
		frmReceptor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmReceptor.setVisible(true);
		
		ImageIcon imgReceptor = new ImageIcon("C:\\Users\\Lautaro\\Documents\\Facultad\\4� A�o\\An�lisis y Dise�o de Sistemas II\\AyD2\\src\\main\\img\\email-icon.png");
		frmReceptor.setIconImage(imgReceptor.getImage());
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 230, 140));
		frmReceptor.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 13));
		tabbedPane.setBackground(new Color(240, 230, 140));
		panel.add(tabbedPane, BorderLayout.CENTER);
		tabbedPane.setVisible(true);
		
		this.creaNuevaTab(tabbedPane,i);
		this.creaNuevaTab(tabbedPane,i);
		this.creaNuevaTab(tabbedPane,++i);
		this.creaNuevaTab(tabbedPane,++i);
	}
	
	public void creaNuevaTab(JTabbedPane tabbedPane, int i) {
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 230, 140));
		tabbedPane.addTab("Emisor-"+i, panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel1.setBackground(new Color(240, 230, 140));
		panel.add(panel1, BorderLayout.NORTH);
		
		JLabel lblAsunto = new JLabel("Asunto");
		lblAsunto.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel1.add(lblAsunto);
		
		textFieldAsunto = new JTextField();
		panel1.add(textFieldAsunto);
		textFieldAsunto.setColumns(55);
		
		JPanel panel2 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel2.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel2.setBackground(new Color(240, 230, 140));
		panel.add(panel2, BorderLayout.SOUTH);
		
		ImageIcon imgSilenciar = new ImageIcon("C:\\Users\\Lautaro\\Documents\\Facultad\\4� A�o\\An�lisis y Dise�o de Sistemas II\\AyD2\\src\\main\\img\\mute.png");
		JButton btnSilenciar = new JButton("Silenciar",imgSilenciar);
		btnSilenciar.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel2.add(btnSilenciar);
		
		ImageIcon imgEliminarR = new ImageIcon("C:\\Users\\Lautaro\\Documents\\Facultad\\4� A�o\\An�lisis y Dise�o de Sistemas II\\AyD2\\src\\main\\img\\cruz-eliminar.png");
		JButton btnEliminar = new JButton("Eliminar",imgEliminarR);
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel2.add(btnEliminar);
		btnEliminar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tabbedPane.remove(tabbedPane.getSelectedIndex());
			}
			
		});
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(240, 230, 140));
		panel.add(panel_5, BorderLayout.CENTER);
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
		panel_6.setBackground(new Color(240, 230, 140));
		panel_5.add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_6.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
