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
	
	
	//Toujours dans le constructeur, on va tout de suite affecter toutes les caract�ristiques
	//n�cessaires au JLabel du mur (centrage, position, image). Voici comment faire :
    //Pour centrer horizontalement le contenu d'un JLabel, il faut utiliser la m�thode
    //setHorizontalAlignment et lui envoyer en param�tre la constante statique CENTER de la
	//classe SwingConstants. setHorizontalAlignment est une m�thode qui doit s'appliquer �
	//un JLabel, donc, en passant par la propri�t� label de la classe m�re, acc�dez � son
	//JLabel (vous avez �crit un getter pour �a).
	
}
