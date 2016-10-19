package vue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controleur.Global;


import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Arene extends JFrame implements Global {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel jpnMurs;
	private JPanel jpnJeu ;

	/**
	 * Create the frame.
	 */
	public Arene() {
		setTitle("Arena");//titre de la fenêtre.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,L_ARENE+3*MARGE,H_ARENE+H_CHAT);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		 jpnJeu = new JPanel();
		jpnJeu.setBounds(0, 0, L_ARENE,H_ARENE);
		jpnJeu.setLayout(null);
		contentPane.add(jpnJeu);
		jpnJeu.setOpaque(false);
		
		jpnMurs = new JPanel();
		jpnMurs.setBounds(0, 0, L_ARENE,H_ARENE);
		jpnMurs.setLayout(null);
		contentPane.add(jpnMurs);
		jpnMurs.setOpaque(false);
		
		JLabel lblFond = new JLabel("");
		lblFond.setBounds(0,0, L_ARENE, H_ARENE);
		lblFond.setIcon(new ImageIcon(FONDARENE));
		contentPane.add(lblFond);
		
		JScrollPane jspChat = new JScrollPane();
		jspChat.setBounds(0, H_ARENE+H_SAISIE,L_ARENE,H_CHAT-H_SAISIE - 7 *MARGE);
		contentPane.add(jspChat);
		jspChat.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		JTextArea txtChat = new JTextArea();
		jspChat.setViewportView(txtChat);
	}
	
	public void ajoutMur(JLabel unMur){
		jpnMurs.add(unMur); 
		jpnMurs.repaint();
	}
	
	public void ajoutPanelMurs(JPanel unPanel){// capable de réceptionner un JPanel et de transférer tout son contenu dans le JPanel des murs
		jpnMurs.add(unPanel);//ajout cet objet au panel des murs
		jpnMurs.repaint();//redessine le JPanel
		contentPane.requestFocus();
	}

	public JPanel getJpnMurs() {
		return jpnMurs;
	}
	
	public void ajoutJoueur(JLabel unJoueur){
		jpnJeu.add(unJoueur);
		jpnJeu.repaint();
		
	}
	
	public void ajoutModifJoueur(int num, JLabel unLabel){
		try {
			this.jpnJeu.remove(num);
		} catch (ArrayIndexOutOfBoundsException ex) {}
		this.jpnJeu.add(unLabel, num);
		this.jpnJeu.repaint();
		
	}
	
}
