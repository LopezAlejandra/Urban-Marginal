package modele;
import controleur.Global;
//Cette classe va ex�cuter un processus ind�pendant.
public class Attaque extends Thread implements Global{
	private Joueur attaquant;
	private JeuServeur jeuServeur;
}
