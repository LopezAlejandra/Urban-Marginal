package modele;

import controleur.Controle;
import outils.connexion.Connection;

public abstract class Jeu {//cette classe ne sera pas directement instanciée
	protected Controle controle;//Les 2classes filles pourront y accéder
	
	public abstract void setConnection(Connection connection);//Quand on écrit une méthode abstraite, il faut mettre abstract devant le type de la méthode et ne
															//pas mettre de corps à la méthode
	
	public abstract void reception(Connection connection,Object info);//connection =pour savoir qui a envoyé le message et info= message envoyé

	public void envoi(Connection connection, Object info){
		connection.envoi(info);
	}
	//Cet objet sera alors envoyé à l'ordinateur distant, par l'intermédiaire 
							   //de la connexion qui a été créée.
	
	public abstract void deconnection(Connection connection);//déconnection de l'ordinateur distant.
	
}
