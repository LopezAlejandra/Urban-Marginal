package modele;
import controleur.Controle;
import outils.connexion.Connection;

public class JeuClient extends Jeu{
	
	
	/**
	 * 
	 * @param controle
	 */
	public JeuClient(Controle controle){
		super(controle)=controle;//valorise la propriété protégée controle de la classe mère, avec le paramètre
	}

	@Override
	public void setConnection(Connection connection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reception(Connection connection, Object info) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deconnection(Connection connection) {
		// TODO Auto-generated method stub
		
	}

}
