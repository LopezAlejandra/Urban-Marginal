package modele;
import controleur.Global;
//Cette classe va exécuter un processus indépendant.
public class Attaque extends Thread implements Global{
	private Joueur attaquant;
	private JeuServeur jeuServeur;
}
