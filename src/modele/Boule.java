package modele;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controleur.Global;
public class Boule extends Objet implements Global {
	private JeuServeur jeuServeur;
	
	//constructeur
	public Boule(JeuServeur jeuServeur){
		this.jeuServeur=jeuServeur;
		super.label=new Label(Label.nbLabel++, new JLabel());
		super.label.getjLabel().setHorizontalAlignment(SwingConstants.CENTER);
		super.label.getjLabel().setVerticalAlignment(SwingConstants.CENTER);
		super.label.getjLabel().setBounds(0, 0, L_BOULE, H_BOULE);
		super.label.getjLabel().setIcon(new ImageIcon(BOULE));
		super.label.getjLabel().setVisible(false);
		
		jeuServeur.nouveauLabelJeu(super.label);//Ainsi le JLabel sera ajoutè au JPanel de Serveur
	}
	
	public void tireBoule(Joueur attaquant){
		if(attaquant.getOrientation()==GAUCHE){
			attaquant.getBoule().setPosX(attaquant.getPosX()-L_BOULE-1);
		}else{
			attaquant.getBoule().setPosX(attaquant.getPosX()+L_BOULE+1);
		}
		attaquant.getBoule().setPosY(attaquant.getPosY()+H_PERSO/2);
		new Attaque(attaquant, jeuServeur);
	}
}
