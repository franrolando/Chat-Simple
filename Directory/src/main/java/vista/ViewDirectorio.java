package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controlador.ControladorDirectorio;
import modelo.Receptor;

public class ViewDirectorio {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				new ViewDirectorio();
			} catch (Exception e) {

			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public ViewDirectorio() {
		iniciaServidor();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		JFrame frmDirectorio = new JFrame();
		frmDirectorio.setResizable(false);
		frmDirectorio.setTitle("Directorio");
		frmDirectorio.getContentPane().setBackground(new Color(240, 230, 140));
		frmDirectorio.setBounds(100, 100, 450, 300);
		frmDirectorio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDirectorio.setSize(450, 250);
		frmDirectorio.setLocationRelativeTo(null);
		frmDirectorio.setVisible(true);
		frmDirectorio.getContentPane().setLayout(new BorderLayout(0, 0));

		ImageIcon imgDirectorio = new ImageIcon("./src/main/img/email-icon.png");
		frmDirectorio.setIconImage(imgDirectorio.getImage());

		JPanel panelBtn = new JPanel();
		panelBtn.setBackground(new Color(240, 230, 140));
		frmDirectorio.getContentPane().add(panelBtn, BorderLayout.SOUTH);
		panelBtn.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panelActualizar = new JPanel();
		panelActualizar.setBackground(new Color(240, 230, 140));
		panelBtn.add(panelActualizar);

		JPanel panelContactos = new JPanel();
		frmDirectorio.getContentPane().add(panelContactos, BorderLayout.CENTER);
		panelContactos.setBackground(new Color(240, 230, 140));

		JLabel lblContactos = new JLabel("Contactos Registrados");
		lblContactos.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelContactos.add(lblContactos);

		JComboBox<Receptor> comboBox = new JComboBox<>();
		panelContactos.add(comboBox);

		JButton btnActualizarContactos = new JButton("Actualizar Contactos",
				new ImageIcon("./src/main/img/Refresh.png"));
		panelActualizar.add(btnActualizarContactos);
		btnActualizarContactos.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnActualizarContactos.addActionListener(e -> {
			comboBox.removeAllItems();
			List<Receptor> listaReceptores = ControladorDirectorio.getInstance().listaReceptores();
			RenderCombo rc = new RenderCombo(listaReceptores);
			listaReceptores.forEach(receptor -> {
				comboBox.addItem(receptor);
			});
			comboBox.setRenderer(rc);
		});

		JPanel panelExit = new JPanel();
		panelExit.setBackground(new Color(240, 230, 140));
		panelBtn.add(panelExit);

		JButton btnApagarDirectorio = new JButton("Apagar Directorio", new ImageIcon("./src/main/img/danger-icon.png"));
		panelExit.add(btnApagarDirectorio);
		btnApagarDirectorio.setFont(new Font("Tahoma", Font.BOLD, 14));

		btnApagarDirectorio.addActionListener(e -> System.exit(0));

	}

	private void iniciaServidor() {
		ControladorDirectorio.initDirectorio();
		Thread tReceptores = new Thread() {

			@Override
			public void run() {
				while (true) {
					ControladorDirectorio.getInstance().actualizarEstado();
				}
			}

		};
		Thread tEmisores = new Thread() {

			@Override
			public void run() {
				while (true) {
					ControladorDirectorio.getInstance().getReceptores();
				}
			}

		};
		tReceptores.start();
		tEmisores.start();
	}
}
