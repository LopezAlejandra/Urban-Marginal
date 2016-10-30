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
	private ArrayList <Joueur>lesJoueursDanslordre=new ArrayList<Joueur>(); ;
	//constructeur
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
	}
	@Override
	public void reception(Connection connection, Object info) { 
		String[] infos = ((String)info).split(SEPARE);//tableau
		switch(Integer.parseInt(infos[0])){
		case PSEUDO :
			controle.evenementModele(this,"envoi panel murs",connection);
			lesJoueurs.get(connection).initPerso(infos[1], Integer.parseInt(infos[2]), lesJoueurs, lesMurs);
			this.lesJoueursDanslordre.add(this.lesJoueurs.get(connection)) ; //Insére ce nouveau joueur dans la collection lesJoueursDanslordre
			break;
		}
	}
	@Override
	public void deconnection(Connection connection) {
		// TODO Auto-generated method stub
		
	}
	//Cette méthode va donc envoyer vers le controleur soit le
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
