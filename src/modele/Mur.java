package modele;

import controleur.Global;

public class Mur extends Object implements Global {

	public Mur(){
		super.posX = (int) Math.round(Math.random() * (L_ARENE - L_MUR)) ;
		super.posY = (int) Math.round(Math.random() * (H_ARENE - H_MUR)) ;
	}
	
}
