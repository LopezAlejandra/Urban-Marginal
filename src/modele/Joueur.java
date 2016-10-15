package modele;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controleur.Global;
public class Joueur extends Objet implements Global {
	private String pseudo ;
	private int numPerso;
	private Label message;// pour l'affichage du pseudo et de la vie
	
	protected Label getMessage() {
		return message;
	}

	public Joueur(JeuServeur jeuServeur){
		
	}
	
	public void initPerso(String unPseudo, int unNumero){
		this.pseudo=unPseudo;
		this.numPerso=unNumero;
		label=new Label(Label.getNbLabel()+1,new JLabel());
		label.getjLabel().setHorizontalAlignment(SwingConstants.CENTER);
		label.getjLabel().setVerticalAlignment(SwingConstants.CENTER);
		
		message = new Label(Label.getNbLabel()+1, new JLabel());
		message.getjLabel().setHorizontalAlignment(SwingConstants.CENTER);
		message.getjLabel().setFont(new Font("Dialog", Font.PLAIN, 8));
	}
}
