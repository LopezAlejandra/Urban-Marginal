package modele;

import controleur.Controle;
import outils.connexion.Connection;

public abstract class Jeu {//cette classe ne sera pas directement instanci�e
	protected Controle controle;//Les 2classes filles pourront y acc�der
	
	public abstract void setConnection(Connection connection);//Quand on �crit une m�thode abstraite, il faut mettre abstract devant le type de la m�thode et ne
															//pas mettre de corps � la m�thode
	
	public abstract void reception(Connection connection,Object info);//connection =pour savoir qui a envoy� le message et info= message envoy�

	public void envoi(Connection connection, Object info){
		connection.envoi(info);
	}
	//Cet objet sera alors envoy� � l'ordinateur distant, par l'interm�diaire 
							   //de la connexion qui a �t� cr��e.
	
	public abstract void deconnection(Connection connection);//d�connection de l'ordinateur distant.
	
}
