package modele;
import controleur.Controle;
import outils.connexion.Connection;

public class JeuClient extends Jeu{
	private Connection connection;
	
	/**
	 * 
	 * @param controle
	 */
	public JeuClient(Controle controle){
		super.controle=controle;//valorise la propri�t� prot�g�e controle de la classe m�re, avec le param�tre
	}

	@Override
	public void setConnection(Connection connection) {
		// TODO Auto-generated method stub
		this.connection=connection;
	}

	@Override
	public void reception(Connection connection, Object info) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deconnection(Connection connection) {
		// TODO Auto-generated method stub
		
	}
	public void envoi(Object info){
		super.envoi(connection, info);
		
	}

}
