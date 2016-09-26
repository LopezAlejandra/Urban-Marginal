package controleur;//package controleur
import javax.swing.JFrame;

import modele.Jeu;
import modele.JeuServeur;
import outils.connexion.ClientSocket;
import outils.connexion.ServeurSocket;
import vue.Arene;
import vue.EntreeJeu;

public class Controle {

	public static void main(String[] args) {
		new Controle();
	}	
	private EntreeJeu frmEntreeJeu;
	private Jeu leJeu;
	private Arene frmArene;

	
	//Constructeur
	public Controle(){
		this.frmEntreeJeu= new EntreeJeu(this);//création de la frame
		frmEntreeJeu.setVisible(true);//rend visible la frame	
	}
	public void evenementVue(Object uneFrame, Object info){
		if (uneFrame instanceof EntreeJeu){
			evenementEntreeJeu(info);
		}
	}
	
	private void evenementEntreeJeu(Object info) {//Démarrer un jeu serveur ou un jeu client
		
		
		if((String) info=="serveur"){
			new ServeurSocket(this,6666);//this est l'instance actuelle de Controle.
			leJeu= new JeuServeur(this);//Un jeu de type serveur va ainsi être créé.
			frmEntreeJeu.dispose();//La frame d'entrée peut être fermée.
			frmArene=new Arene();
			frmArene.setVisible(true);
		}
		else{
			(new ClientSocket((String)info,6666,this)).getConnexionOk();//Creation d'un objet en lui appliquant une méthode
		}
		System.out.println((String)info);
		
	}
	
}









