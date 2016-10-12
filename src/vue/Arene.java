package vue;//

import java.awt.BorderLayout;//
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Arene extends JFrame {

	private JPanel contentPane;
	private JTextField txtSaisie;

	/**
	 * Create the frame.
	 */
	public Arene() {
		setTitle("Arena");//titre de la fenêtre.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 388);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JPanel jpnJeu = new JPanel();
		jpnJeu.setBounds(286, 49, 104, 108);
		contentPane.add(jpnJeu);
		
		JPanel jpnMurs = new JPanel();
		jpnMurs.setBounds(178, 192, 215, 65);
		contentPane.add(jpnMurs);
		
		JLabel lblFond = new JLabel("");
		lblFond.setBounds(10, 22, 234, 135);
		contentPane.add(lblFond);
		
		txtSaisie = new JTextField();
		txtSaisie.setBounds(39, 270, 354, 20);
		contentPane.add(txtSaisie);
		txtSaisie.setColumns(10);
		
		JScrollPane jspChat = new JScrollPane();
		jspChat.setBounds(39, 301, 354, 41);
		contentPane.add(jspChat);
		
		JTextArea txtChat = new JTextArea();
		jspChat.setViewportView(txtChat);
	}
}
