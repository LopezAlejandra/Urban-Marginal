package vue;//

import java.awt.BorderLayout;//
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Arene extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Arene() {
		setTitle("Arena");//titre de la fen�tre.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
	}

}
