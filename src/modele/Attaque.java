package modele;
import controleur.Global;
//Cette classe va exécuter un processus indépendant.
public class Attaque extends Thread implements Global{
	private Joueur attaquant;
	private JeuServeur jeuServeur;
	
	public Attaque(Joueur attaquant, JeuServeur jeuServeur){
		this.attaquant=attaquant;
		this.jeuServeur=jeuServeur;
		super.start();//va permettre de lancer le processus
	}
}
