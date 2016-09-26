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
		this.frmEntreeJeu= new EntreeJeu(this);//cr�ation de la frame
		frmEntreeJeu.setVisible(true);//rend visible la frame	
	}
	public void evenementVue(Object uneFrame, Object info){
		if (uneFrame instanceof EntreeJeu){
			evenementEntreeJeu(info);
		}
	}
	
	private void evenementEntreeJeu(Object info) {//D�marrer un jeu serveur ou un jeu client
		
		
		if((String) info=="serveur"){
			new ServeurSocket(this,6666);//this est l'instance actuelle de Controle.
			leJeu= new JeuServeur(this);//Un jeu de type serveur va ainsi �tre cr��.
			frmEntreeJeu.dispose();//La frame d'entr�e peut �tre ferm�e.
			frmArene=new Arene();
			frmArene.setVisible(true);
		}
		else{
			(new ClientSocket((String)info,6666,this)).getConnexionOk();//Creation d'un objet en lui appliquant une m�thode
		}
		System.out.println((String)info);
		
	}
	
}









