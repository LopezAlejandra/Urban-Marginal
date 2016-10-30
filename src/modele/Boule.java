package modele;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controleur.Global;
public class Boule extends Objet implements Global {
	
	//constructeur
	public Boule(){
		super.label=new Label(Label.nbLabel++, new JLabel());
		super.label.getjLabel().setHorizontalAlignment(SwingConstants.CENTER);
		super.label.getjLabel().setVerticalAlignment(SwingConstants.CENTER);
		
	}
}
