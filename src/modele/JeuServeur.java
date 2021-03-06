package modele;

import java.util.ArrayList;
import java.util.Hashtable;

import controleur.Controle;
import controleur.Global;
import outils.connexion.Connection;

public class JeuServeur extends Jeu implements Global{
	//Le Hashtable va permettre de g�rer un dictionnaire, avec une cl� qui sera de type Connection.
	private Hashtable <Connection, Joueur> lesJoueurs=new Hashtable<>();
	private ArrayList<Mur> lesMurs=new ArrayList<>();//collection des murs
	private ArrayList <Joueur>lesJoueursDanslordre=new ArrayList<>(); ;
	private String laPhrase;
		//constructeur
	public JeuServeur(Controle controle) {
		super.controle = controle;
		Label.setNbLabel(0);
		}
	
	public void constructionMurs(){//va s'occuper de g�n�rer les murs.
		for(int k = 0; k<NBMURS; k++){
			lesMurs.add(new Mur());
			controle.evenementModele(this, "ajout mur", lesMurs.get(k).getLabel().getjLabel()); //exploiter l'evenement  );
		}
	}
	
	
	
	@Override
	public void setConnection(Connection connection) {
		lesJoueurs.put(connection, new Joueur(this));
	}
	@Override
	public void reception(Connection connection, Object info) { 
		String[] infos = ((String)info).split(SEPARE);//tableau
		switch(Integer.parseInt(infos[0])){
		case PSEUDO :
			controle.evenementModele(this,"envoi panel murs",connection);
		//Envoi de tous les JLabels
		for(Joueur unJoueur : lesJoueursDanslordre){
				super.envoi(connection, unJoueur.getLabel());
				super.envoi(connection, unJoueur.getMessage());
				super.envoi(connection, unJoueur.getBoule().getLabel());//Envoi aussi le label de la boule.
				super.envoi(connection, unJoueur.getFiole().getLabel());	
		}
			lesJoueurs.get(connection).initPerso(infos[1], Integer.parseInt(infos[2]), lesJoueurs, lesMurs);
			this.lesJoueursDanslordre.add(this.lesJoueurs.get(connection)) ; //Ins�re ce nouveau joueur dans la collection lesJoueursDanslordre
			controle.evenementModele(this, "ajout phrase", "*** "+lesJoueurs.get(connection).getPseudo()+" vient de se connecter ***");
			break;
		case CHAT:
			laPhrase=lesJoueurs.get(connection).getPseudo()+ " > " + infos[1];
			controle.evenementModele(this, "ajout phrase", laPhrase);
			break;
		case ACTION:
			if(!lesJoueurs.get(connection).estMort()) {
				lesJoueurs.get(connection).action(Integer.parseInt(infos[1]), lesJoueurs, lesMurs);
			}
			
			
			break;
		}
	}
	
	
	
	
	
	@Override
	public void deconnection(Connection connection) {
		lesJoueurs.get(connection).departJoueur();
		lesJoueurs.remove(connection);
		
	}
	//Cette m�thode va donc envoyer vers le controleur soit le
	//label du personnage, soit le Label du message.
	public void nouveauLabelJeu(Label label){
		controle.evenementModele(this,"ajout joueur", label.getjLabel());
	}


	public void envoi(Object info) {
		for(Connection unJoueur:lesJoueurs.keySet()){
			super.envoi(unJoueur, info);
		}
		
	}

}
