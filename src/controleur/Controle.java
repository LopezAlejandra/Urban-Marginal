package controleur;
import javax.swing.JFrame;
import javax.swing.JLabel;
import modele.Label;
import javax.swing.JPanel;
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
	
	public void evenementVue(JFrame uneFrame, Object info){//permet de récupérer et traiter les événements provenant de la vue.
		if (uneFrame instanceof EntreeJeu){
			evenementEntreeJeu(info);
		}
		if(uneFrame instanceof ChoixJoueur){
			evenementChoixJoueur(info);
		}
		if (uneFrame instanceof Arene){
			evenementArene(info);
		}
		
	}
	
	
	
	

	private void evenementEntreeJeu(Object info) {//Démarrer un jeu serveur ou un jeu client
		
		if((String) info=="serveur"){
			new ServeurSocket(this,PORT);//this est l'instance actuelle de Controle.
			leJeu= new JeuServeur(this);//Un jeu de type serveur va ainsi être créé.
			frmEntreeJeu.dispose();//La frame d'entrée peut être fermée.
			frmArene=new Arene("serveur");
			((JeuServeur) this.leJeu).constructionMurs();
			frmArene.setVisible(true);
		}
		else{
			if((new ClientSocket((String)info,PORT,this)).getConnexionOk()){//Creation d'un objet en lui appliquant une méthode
			leJeu=new JeuClient(this);
			frmArene=new Arene("client");
			leJeu.setConnection(connection);//Pour que le client puisse envoyer des informations
											//au serveur.
			
			frmEntreeJeu.dispose();
			frmChoixJoueur=new ChoixJoueur(this);
			frmChoixJoueur.setVisible(true);
		}
		}
		
	}
	private void evenementChoixJoueur(Object info){
		((JeuClient)leJeu).envoi(info);
		frmChoixJoueur.dispose();
		frmArene.setVisible(true);
	}
	public void evenementModele(Object unJeu, String ordre, Object info){
		if (unJeu instanceof JeuServeur){
			evenementJeuServeur(ordre,info);
		}
		if (unJeu instanceof JeuClient){
			evenementJeuClient(ordre,info);
		}	
	}
	
	private void evenementArene(Object info) {
		// TODO Auto-generated method stub
		
	}
	
	private void evenementJeuClient(String ordre, Object info) {
		if(ordre=="ajout panel murs"){
			frmArene.ajoutPanelMurs((JPanel)info);
		}
		if (ordre == "ajout joueur") {
			frmArene.ajoutModifJoueur( ((Label) info).getNumLabel(), ((Label) info).getjLabel());
			}
	}
	
	private void evenementJeuServeur(String ordre, Object info) {
		if(ordre=="ajout mur"){ //test sur ordre pour voir s'il contient la chaîne "ajout mur".
			frmArene.ajoutMur((JLabel) info);
		}
		if(ordre == "envoi panel murs"){
			((JeuServeur)leJeu).envoi((Connection)info, frmArene.getJpnMurs());
		}
		if(ordre=="ajout joueur"){
			frmArene.ajoutJoueur((JLabel) info);
		}
	}
	
	public void receptionInfo(Connection connection, Object info){
		this.leJeu.reception(connection, info);
	}
	
	public void setConnection(Connection connection){
		this.connection=connection;
		if (leJeu instanceof JeuServeur){
			leJeu.setConnection(connection);
		}
	}
}









