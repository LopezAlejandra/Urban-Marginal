package modele;


import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import controleur.Global;

public  class Fiole extends Objet implements Global {
	private JeuServeur jeuServeur;
	public Fiole(JeuServeur jeuServeur){
		this.jeuServeur=jeuServeur;
		
		posY = (int)Math.round(Math.random() * (H_ARENE - H_FIOLE)) ;
		posX = (int) Math.round(Math.random() * (L_ARENE - L_FIOLE)) ;
		
		label = new Label(-1,new JLabel());
		label.getjLabel().setHorizontalAlignment(SwingConstants.CENTER);
		label.getjLabel().setVerticalAlignment(SwingConstants.CENTER);
		label.getjLabel().setBounds(posX, posY, L_FIOLE,H_FIOLE);
		label.getjLabel().setIcon(new ImageIcon(FIOLE));
		super.label.getjLabel().setVisible(true);
	jeuServeur.nouveauLabelJeu(super.label);
	
	
	}
	public void fioleDonneVie(Joueur joueur) {
		joueur.augmenterVie(3); //donne au joueur 3 points de vie supplémentaires 
	}
}
