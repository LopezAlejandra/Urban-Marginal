package modele;

import javax.swing.JPanel;

import controleur.Controle;
import outils.connexion.Connection;

public class JeuClient extends Jeu{
	private Connection connection;
	
	/**
	 * 
	 * @param controle
	 */
	public JeuClient(Controle controle){
		super.controle=controle;//valorise la propriété protégée controle de la classe mère, avec le paramètre
	}

	@Override
	public void setConnection(Connection connection) {
		// TODO Auto-generated method stub
		this.connection=connection;
	}

	@Override
	public void reception(Connection connection, Object info) {
		if(info instanceof JPanel){
			controle.evenementModele(this, "ajout panel murs", info);
		}
		if(info instanceof Label){//Contrôle si l'objet reçu est de type Label
			controle.evenementModele(this,"ajout joueur",info);
		}
		
	}

	@Override
	public void deconnection(Connection connection) {
	// TODO Auto-generated method stub
		
	}
	public void envoi(Object info){
		super.envoi(connection, info);
		
	}
	
	

	
	


}
