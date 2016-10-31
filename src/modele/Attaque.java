package modele;

import java.util.ArrayList;
import java.util.Hashtable;

import controleur.Global;
import outils.connexion.Connection;
//Cette classe va exécuter un processus indépendant.
public class Attaque extends Thread implements Global{
	private Joueur attaquant;
	private JeuServeur jeuServeur;
	private ArrayList<Mur>lesMurs;
	private Hashtable <Connection,Joueur>lesJoueurs;
	
	
	public Attaque(Joueur attaquant, JeuServeur jeuServeur, ArrayList<Mur>lesMurs, Hashtable <Connection,Joueur>lesJoueurs){
		this.attaquant=attaquant;
		this.jeuServeur=jeuServeur;
		this.lesMurs=lesMurs;
		this.lesJoueurs=lesJoueurs;
		super.start();//va permettre de lancer le processus
		
	}
	
	public void run(){
		Boule laboule=attaquant.getBoule();
		int orientation=attaquant.getOrientation();
		laboule.label.getjLabel().setVisible(true);
		Joueur victime=null;
		do{
			if(orientation==GAUCHE){
				laboule.setPosX(laboule.getPosX()-LEPAS);
			}else{
				laboule.setPosX(laboule.getPosX()+LEPAS);
			}
			laboule.label.getjLabel().setBounds(laboule.getPosX(), laboule.getPosY(), L_BOULE, H_BOULE);
			this.pause(10);
			jeuServeur.envoi(laboule.getLabel());//envoi de la position à tous les joueurs
			victime=toucheJoueur();
		}
		
		while(laboule.getPosX()>0 &&laboule.getPosX()<L_ARENE && toucheMur()==false && victime==null);
		if(victime!=null){
			for(int i=1; i < NBETATSBLESSE; i++){
				victime.affiche(BLESSE, i);
				this.pause(80);
			}
			victime.affiche(MARCHE, 1);
		}
		
		laboule.getLabel().getjLabel().setVisible(false);
		jeuServeur.envoi(laboule.getLabel());
	}
	public void pause(long milli){
		try {
			Thread.sleep(milli);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private boolean toucheMur(){
		for(Mur unMur:lesMurs){
			if(attaquant.getBoule().toucheObjet(unMur)){
				return true;
			}
		}
		return false;
	}
	
	private Joueur toucheJoueur(){
		for(Joueur unJoueur : lesJoueurs.values()){
			if(attaquant.getBoule().toucheObjet(unJoueur)){
				return unJoueur;
			}
		}
		return null;
	}
}
