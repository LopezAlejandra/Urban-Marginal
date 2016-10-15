package modele;

import java.util.ArrayList;
import java.util.Hashtable;

import controleur.Controle;
import controleur.Global;
import outils.connexion.Connection;

public class JeuServeur extends Jeu implements Global{
	//Le Hashtable va permettre de gérer un dictionnaire, avec une clé qui sera de type Connection.
	private Hashtable <Connection, Joueur> lesJoueurs=new Hashtable<Connection, Joueur>();
	private ArrayList<Mur> lesMurs=new ArrayList<Mur>();//collection des murs
	
	public JeuServeur(Controle controle) {
		super.controle = controle;
		Label.setNbLabel(0);
		}
	
	public void constructionMurs(){//va s'occuper de générer les murs.
		for(int k = 0; k<NBMURS; k++){
			lesMurs.add(new Mur());
			controle.evenementModele(this, "ajout mur", lesMurs.get(k).getLabel().getjLabel()); //exploiter l'evenement  );
			
		}
	}

	@Override
	public void setConnection(Connection connection) {
		lesJoueurs.put(connection, new Joueur(this));
			controle.evenementModele(this,"envoi panel murs",connection);//à revoir.
	}

	@Override
	public void reception(Connection connection, Object info) { 
		String[] infos = ((String)info).split(SEPARE);//tableau
		switch(Integer.parseInt(infos[0])){
		case PSEUDO :
			controle.evenementModele(this, "envoi panel murs", connection);//'un nouveau client vient d'envoyer le choix de son pseudo et du numéro de personnage
			lesJoueurs.get(connection).initPerso(infos[1], Integer.parseInt(infos[2]));
			
			break;
		}
	}

	@Override
	public void deconnection(Connection connection) {
		// TODO Auto-generated method stub
		
	}

}
