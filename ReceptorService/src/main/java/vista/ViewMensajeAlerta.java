package vista;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ViewMensajeAlerta extends ViewMensaje {
	
	private Clip clipAlarma;

	/**
	 * @wbp.parser.entryPoint
	 */
	
	public ViewMensajeAlerta(String emisor, String asunto, String mensaje, String hora) {
		super(emisor, asunto, mensaje, hora);
	}

	@Override
	protected void botonesFooter(JPanel panel_2) {
		String filePath = ("./src/main/audio/Industrial Alarm.wav");
		ImageIcon imgSilenciar = new ImageIcon("./src/main/img/mute.png");
		JButton btnSilenciar = new JButton("Silenciar", imgSilenciar);
		btnSilenciar.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_2.add(btnSilenciar);
		try {
			AudioInputStream audioAlarma;
			audioAlarma = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
			clipAlarma = AudioSystem.getClip();
			clipAlarma.open(audioAlarma);
			clipAlarma.start();
			clipAlarma.loop(Clip.LOOP_CONTINUOUSLY);
			btnSilenciar.addActionListener(e -> clipAlarma.close());
			frmMensajeReceptor.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					clipAlarma.close();
				}
			});
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {

		}
		super.botonesFooter(panel_2);
	}

	@Override
	protected void closeMessageAction() {
		super.closeMessageAction();
		clipAlarma.close();
	}

}
