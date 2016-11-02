package modele;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controleur.Global;

public class Fiole extends Objet implements Global {
	
	
	public Fiole(){
		
		
		super.posX =(int) Math.round(Math.random() * (L_ARENE - L_FIOLE)) ;
		super.posY =(int) Math.round(Math.random() * (H_ARENE - H_FIOLE));
		super.label= new Label(-1,new JLabel ());
		super.label.getjLabel().setHorizontalAlignment(SwingConstants.CENTER) ;//Pour centrer horizontalement le contenu 
		super.label.getjLabel().setVerticalAlignment(SwingConstants.CENTER) ;//Pour centrer verticalement le contenu 
		super.label.getjLabel().setBounds(new Rectangle (posX, posY, L_FIOLE, H_FIOLE));
		super.label.getjLabel().setIcon(new ImageIcon(FIOLE));
		
		
		
	}
	
	
	
	
}
