package modele;

import java.util.ArrayList;

import controleur.Controle;
import controleur.Global;
import outils.connexion.Connection;

public class JeuServeur extends Jeu implements Global{
	
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reception(Connection connection, Object info) {
		//System.out.println(info);
		
	}

	@Override
	public void deconnection(Connection connection) {
		// TODO Auto-generated method stub
		
	}

}
