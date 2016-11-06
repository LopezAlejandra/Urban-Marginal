package modele;


import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controleur.Global;
public  class Fiole extends Objet implements Global {
	//Constructeur
	JeuServeur jeuServeur;
	public Fiole(JeuServeur jeuServeur){
		this.jeuServeur=jeuServeur;
		
		super.posY = (int)Math.round(Math.random() * (H_ARENE - H_FIOLE)) ;
		super.posX = (int) Math.round(Math.random() * (L_ARENE - L_FIOLE)) ;
		
		super.label = new Label(Label.nbLabel++,new JLabel());
		super.label.getjLabel().setHorizontalAlignment(SwingConstants.CENTER);
		super.label.getjLabel().setVerticalAlignment(SwingConstants.CENTER);
		super.label.getjLabel().setBounds(posX, posY, L_FIOLE,H_FIOLE);
		super.label.getjLabel().setIcon(new ImageIcon(FIOLE));
		super.label.getjLabel().setVisible(false);
		jeuServeur.nouveauLabelJeu(super.label);
		//Ainsi le JLabel sera ajoutè au JPanel de Serveur
	}
	
	public void afficheTrue(){
		super.label.getjLabel().setVisible(true);	
		
	}

	


}



