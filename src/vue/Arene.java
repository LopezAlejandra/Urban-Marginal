package vue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controleur.Controle;
import controleur.Global;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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

	private JTextArea txtChat;
	private JTextField txtSaisie;
	private boolean client;//Cette propri�t� permettra de savoir si c'est l'ar�ne du client ou non.
	private Controle controle;//instance de Controle.
	/**
	 * Create the frame.
	 */
	public Arene(String typeJeu,Controle controle) {
		this.client = (typeJeu == "client");//true si typeJeu contient "client"
		this.controle=controle;	
		setTitle("Arena");//titre de la fen�tre.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,L_ARENE+3*MARGE,H_ARENE+H_CHAT);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		if(client){
			contentPane.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					contentPane_keyPressed(arg0);
				}
			});
		}
		
		
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
		
		if (client) {//Si client==true : affichage de la zone de saisie seulement dans la partie client
			txtSaisie = new JTextField();
			
			txtSaisie.setBounds(0, H_ARENE, L_ARENE, H_SAISIE);
			contentPane.add(txtSaisie);
			//envoi de la phrase au serveur
			txtSaisie.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					txtSaisie_keyPressed(arg0);
				}
			});
			
		}
		
		
		JScrollPane jspChat = new JScrollPane();
		jspChat.setBounds(0, H_ARENE+H_SAISIE,L_ARENE,H_CHAT-H_SAISIE - 7 *MARGE);
		contentPane.add(jspChat);
		jspChat.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		 txtChat = new JTextArea();
		jspChat.setViewportView(txtChat);
	}
	
	private void txtSaisie_keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {//Retourne le code de la touche
			if (txtSaisie.getText() != "") {//Controle si la zone de saisie n'est pas vide
				controle.evenementVue(this, CHAT + SEPARE + txtSaisie.getText());
				txtSaisie.setText("");//vide le contenu de txtSaisie
			}
			contentPane.requestFocus();
		}	
	}
	
	
	public void ajoutMur(JLabel unMur){
		jpnMurs.add(unMur); 
		jpnMurs.repaint();
	}
	
	public void ajoutPanelMurs(JPanel unPanel){// capable de r�ceptionner un JPanel et de transf�rer tout son contenu dans le JPanel des murs
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
	


	public void ajoutModifJoueur(int num, JLabel unLabel) {
		try { 
			this.jpnJeu.remove(num);
		} 
		catch (ArrayIndexOutOfBoundsException e) {}
		this.jpnJeu.add(unLabel, num);
		this.jpnJeu.repaint();
	
		
	}
	
	//M�thode qui va permettre de mettre � jour la zone d'affichage de la conversation
	public void ajoutChat(String unePhrase){
			txtChat.setText(unePhrase+"\r\n"+txtChat.getText());
	}

	public String getTxtChat() {
		return txtChat.getText();
	}

	public void remplaceChat(String remplaceTxtChat){
		txtChat.setText(remplaceTxtChat);
	}
}
