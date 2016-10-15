package controleur;//package controleur///


import javax.swing.JLabel;

import modele.Jeu;
import modele.JeuClient;
import modele.JeuServeur;
import outils.connexion.ClientSocket;
import outils.connexion.Connection;
import outils.connexion.ServeurSocket;
import vue.Arene;
import vue.ChoixJoueur;
import vue.EntreeJeu;

public class Controle implements Global {

	public static void main(String[] args) {
		new Controle();
	}	
	private EntreeJeu frmEntreeJeu;
	private Jeu leJeu;
	private Arene frmArene;
	private ChoixJoueur frmChoixJoueur;
	private Connection connection;

	
	//Constructeur
	public Controle(){//
		this.frmEntreeJeu= new EntreeJeu(this);//création de la frame
		frmEntreeJeu.setVisible(true);//rend visible la frame	
	}
	
	public void evenementVue(Object uneFrame, Object info){//permet de récupérer et traiter les événements provenant de la vue.
		if (uneFrame instanceof EntreeJeu){
			evenementEntreeJeu(info);
		}
		if(uneFrame instanceof ChoixJoueur){
			evenementChoixJoueur(info);
			
		}
	}//
	

	private void evenementEntreeJeu(Object info) {//Démarrer un jeu serveur ou un jeu client
		
		
		if((String) info=="serveur"){
			new ServeurSocket(this,PORT);//this est l'instance actuelle de Controle.
			leJeu= new JeuServeur(this);//Un jeu de type serveur va ainsi être créé.
			frmEntreeJeu.dispose();//La frame d'entrée peut être fermée.
			frmArene=new Arene();
			frmArene.setVisible(true);
		}
		else{
			
			(new ClientSocket((String)info,PORT,this)).getConnexionOk();//Creation d'un objet en lui appliquant une méthode
			leJeu=new JeuClient(this);
			leJeu.setConnection(connection);//Pour que le client puisse envoyer des informations
											//au serveur.
			frmArene=new Arene();
			frmArene.dispose();
			frmChoixJoueur=new ChoixJoueur(this);
			frmChoixJoueur.setVisible(true);
			
		}
		System.out.println((String)info);
		
	}
	private void evenementChoixJoueur(Object info){
		((JeuClient)leJeu).envoi(info);
		frmChoixJoueur.dispose();
		frmArene.setVisible(true);
	}
	
	
	public void evenementModele(Object unJeu, String ordre, Object info){
		if (unJeu instanceof JeuServeur){
			evenementJeuServeur(ordre,info);// méthode à créer.
			
		}
		
	}
	
	
	 
	
	private void evenementJeuServeur(String ordre, Object info) {
		if(ordre=="ajout mur"){ //test sur ordre pour voir s'il contient la chaîne "ajout mur".
			frmArene.ajoutMur((JLabel) info);
		}
		
		
	}

	public void receptionInfo(Connection connection, Object info){
		leJeu.reception(connection, info);
	}
	
	
	public void setConnection(Connection connection){
		this.connection=connection;
	}
	
	
	
	
}









