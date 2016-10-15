package vue;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Rectangle;

import controleur.Controle;
import controleur.Global;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class ChoixJoueur extends JFrame implements Global  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblFond;
	private JLabel lblPrecedent;
	private JLabel lblSuivant;
	private JLabel lblGO;
	private JTextField txtPseudo;
	private JLabel lblPersonnage;
	private int numPerso;
	private Controle controle;
	

	/**
	 * This is the default constructor
	 */
	public ChoixJoueur(Controle controle) {
		super();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		initialize();
		txtPseudo.requestFocus();
		numPerso = 1;
		this.affichePerso();
		this.controle = controle;
		
	}
	//--- Methode affichage du perso ---
	private void affichePerso(){
		//this.lblPersonnage.setIcon(new ImageIcon("media/personnages/perso1marche1d1.gif"));
		lblPersonnage.setIcon(new ImageIcon(PERSO+this.numPerso+MARCHE+"1d"+DROITE+EXTIMAGE));
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(408, 302);
		this.setContentPane(getJContentPane());
		this.setTitle("Choix Joueur");
	}
	private void lblPrecedent_clic(){
		this.numPerso = (this.numPerso + NBPERSOS + 1) % NBPERSOS + 1 ;
		affichePerso();
	}
	
	private void lblSuivant_clic(){
		
		this.numPerso = this.numPerso%NBPERSOS+1;
		affichePerso();
	}
	
	private void lblGO_clic(){
		if(txtPseudo.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Pseudo obligatoire");
			txtPseudo.requestFocus();
		}else{
			controle.evenementVue(this, PSEUDO+SEPARE+txtPseudo.getText()+SEPARE+numPerso);
		}
	}
	//--- Methode affichage souris normal ---
	@SuppressWarnings("deprecation")
	private void souris_normale(){
		contentPane.setCursor(new Cursor(DEFAULT_CURSOR));
	}
	
	//--- Methode affichage souris doigt ---
	@SuppressWarnings("deprecation")
	private void souris_doigt(){
		contentPane.setCursor(new Cursor(HAND_CURSOR));
	}
	
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (contentPane == null) {
			lblPersonnage = new JLabel();
			lblPersonnage.setBounds(new Rectangle(139, 109, 123, 122));
			lblPersonnage.setHorizontalAlignment(SwingConstants.CENTER);
			lblPersonnage.setHorizontalTextPosition(SwingConstants.CENTER);
			
			lblGO = new JLabel();
			lblGO.setBounds(new Rectangle(309, 196, 73, 49));
			lblGO.addMouseListener(new java.awt.event.MouseAdapter() {   
				public void mouseExited(java.awt.event.MouseEvent e) {    
					souris_normale();
				}
				public void mouseEntered(java.awt.event.MouseEvent e) {
					souris_doigt();
				}
				public void mouseClicked(java.awt.event.MouseEvent e) {
					lblGO_clic();
				}
			});
			lblSuivant = new JLabel();
			lblSuivant.setBounds(new Rectangle(296, 137, 37, 54));
			lblSuivant.addMouseListener(new java.awt.event.MouseAdapter() {   
				public void mouseExited(java.awt.event.MouseEvent e) {    
					souris_normale();
				}   
				public void mouseEntered(java.awt.event.MouseEvent e) {    
					souris_doigt();
				}
				public void mouseClicked(java.awt.event.MouseEvent e) {
					lblSuivant_clic();
				}
			});
			lblPrecedent = new JLabel();
			lblPrecedent.setBounds(new Rectangle(55, 134, 45, 56));
			lblPrecedent.addMouseListener(new java.awt.event.MouseAdapter() {   
				public void mouseExited(java.awt.event.MouseEvent e) {    
					souris_normale();
				}   
				public void mouseEntered(java.awt.event.MouseEvent e) {    
					souris_doigt();
				}
				public void mouseClicked(java.awt.event.MouseEvent e) {
					lblPrecedent_clic();
				}
			});
			lblFond = new JLabel();
			lblFond.setBounds(new Rectangle(0, 0, 392, 265));
			lblFond.setIcon(new ImageIcon("media/fonds/fondchoix.jpg"));
			//lblFond.setIcon(new ImageIcon(FONDCHOIX));
			contentPane = new JPanel();
			contentPane.setLayout(null);
			contentPane.add(lblPrecedent, null);
			contentPane.add(lblSuivant, null);
			contentPane.add(lblGO, null);
			contentPane.add(getTxtPseudo(), null);
			contentPane.add(lblPersonnage, null);
			contentPane.add(lblFond, BorderLayout.NORTH);
		}
		return contentPane;
	}

	/**
	 * This method initializes txtPseudo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtPseudo() {
		if (txtPseudo == null) {
			txtPseudo = new JTextField();
			txtPseudo.setBounds(new Rectangle(143, 241, 119, 20));
		}
		return txtPseudo;
	}
	
}	