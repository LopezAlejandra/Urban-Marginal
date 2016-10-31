package vue;

import java.awt.Cursor;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import controleur.Controle;
import controleur.Global;
import outils.son.Son;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


public class ChoixJoueur extends JFrame implements Global  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private JTextField txtPseudo;
	private JLabel lblPersonnage;
	private int numPerso;
	private Controle controle;
	private Son precedent;
	private Son suivant;
	private Son go;
	private Son welcome;

	/**
	 * This is the default constructor
	 */
	public ChoixJoueur(Controle controle){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 416, 313);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.controle = controle;

	
		JLabel lblPrecedent = new JLabel("");
		lblPrecedent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				lblPrecedent_clic();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			souris_doigt();
			}

			public void mouseExited(MouseEvent e) {
				souris_normale();
			}
	});

	lblPrecedent.setBounds(62, 144, 41, 43);
	contentPane.add(lblPrecedent);

	/**
	 * 
	 */
	JLabel lblSuivant = new JLabel("");
	lblSuivant.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			lblSuivant_clic();
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			souris_doigt();
		}

		public void mouseExited(MouseEvent e) {
			souris_normale();
		}
	});

	lblSuivant.setBounds(296, 144, 41, 43);
	contentPane.add(lblSuivant);

	
	JLabel lblGo = new JLabel("");
	lblGo.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			lblGO_clic();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			souris_doigt();
		}

		@Override
		public void mouseExited(MouseEvent e) {
			souris_normale();
		}
	});

	lblGo.setBounds(318, 204, 49, 59);
	contentPane.add(lblGo);

	/**
	 * 
	 */

	txtPseudo = new JTextField();
	txtPseudo.setBounds(143, 243, 119, 20);
	contentPane.add(txtPseudo);
	txtPseudo.setColumns(10);

	lblPersonnage = new JLabel("");
	lblPersonnage.setHorizontalAlignment(SwingConstants.CENTER);
	lblPersonnage.setBounds(143, 116, 119, 116);
	contentPane.add(lblPersonnage);
	
		JLabel lblFond_1 = new JLabel("");
		lblFond_1.setBounds(0, 0, 400, 275);
		lblFond_1.setIcon(new ImageIcon(FONDCHOIX));
		contentPane.add(lblFond_1);

	txtPseudo.requestFocus();
	numPerso = 1;
	affichePerso();
	
	this.precedent=new Son (SONPRECEDENT);
	this.suivant=new Son(SONSUIVANT);
	this.go=new Son(SONGO);
	this.welcome =new Son(SONWELCOME);
	this.welcome.play();
}
	
	private void affichePerso(){
	
		lblPersonnage.setIcon(new ImageIcon(PERSO+this.numPerso+MARCHE+"1d"+DROITE+EXTIMAGE));
	}

	
	private void lblPrecedent_clic(){
		this.precedent.play();
		this.numPerso = (this.numPerso + NBPERSOS + 1) % NBPERSOS + 1 ;
		affichePerso();
	}
	
	private void lblSuivant_clic(){
		this.suivant.play();
		this.numPerso = this.numPerso%NBPERSOS+1;
		affichePerso();
	}
	
	private void lblGO_clic(){
		
		if(txtPseudo.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Pseudo obligatoire");
			txtPseudo.requestFocus();
		}else{
			this.go.play();
			controle.evenementVue(this, PSEUDO+SEPARE+txtPseudo.getText()+SEPARE+numPerso);
		}
	}

	@SuppressWarnings("deprecation")
	private void souris_normale(){
		contentPane.setCursor(new Cursor(DEFAULT_CURSOR));
	}
	
	//--- Methode affichage souris doigt ---
	@SuppressWarnings("deprecation")
	private void souris_doigt(){
		contentPane.setCursor(new Cursor(HAND_CURSOR));
	}
	
}	