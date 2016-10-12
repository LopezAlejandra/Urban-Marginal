package modele;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controleur.Global;

public class Mur extends Object implements Global {

	public Mur(){
		super.posX = (int) Math.round(Math.random() * (L_ARENE - L_MUR)) ;
		super.posY = (int) Math.round(Math.random() * (H_ARENE - H_MUR)) ;
	super.label=new Label(-1, new JLabel());
	super.label.getJLabel().setHorizontalAlignment(SwingConstants.CENTER);
	super.label.getJLabel().setVerticalAlignment(SwingConstants.CENTER);
	super.label.getJLabel().setBounds(posX, posY, L_MUR, H_MUR);
	super.label.getJLabel().setIcon(new ImageIcon(MUR));
	}
	
	
	//Toujours dans le constructeur, on va tout de suite affecter toutes les caractéristiques
	//nécessaires au JLabel du mur (centrage, position, image). Voici comment faire :
    //Pour centrer horizontalement le contenu d'un JLabel, il faut utiliser la méthode
    //setHorizontalAlignment et lui envoyer en paramètre la constante statique CENTER de la
	//classe SwingConstants. setHorizontalAlignment est une méthode qui doit s'appliquer à
	//un JLabel, donc, en passant par la propriété label de la classe mère, accédez à son
	//JLabel (vous avez écrit un getter pour ça).
	
}
