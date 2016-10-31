package modele;
import controleur.Global;
//Cette classe va exécuter un processus indépendant.
public class Attaque extends Thread implements Global{
	private Joueur attaquant;
	private JeuServeur jeuServeur;
	
	public Attaque(Joueur attaquant, JeuServeur jeuServeur){
		this.attaquant=attaquant;
		this.jeuServeur=jeuServeur;
		super.start();//va permettre de lancer le processus
	}
	
	public void run(){
		Boule laboule=attaquant.getBoule();
		int orientation=attaquant.getOrientation();
		laboule.label.getjLabel().setVisible(true);
		
		do{
			if(orientation==GAUCHE){
				laboule.setPosX(laboule.getPosX()-LEPAS);
			}else{
				laboule.setPosX(laboule.getPosX()+LEPAS);
			}
			laboule.label.getjLabel().setBounds(laboule.getPosX(), laboule.getPosY(), L_BOULE, H_BOULE);
			this.pause(10);
			jeuServeur.envoi(laboule.getLabel());//envoi de la position à tous les joueurs
			
		}
		while(laboule.getPosX()>0 &&laboule.getPosX()<L_ARENE);
		laboule.getLabel().getjLabel().setVisible(false);
		jeuServeur.envoi(laboule.getLabel());
	}
	public void pause(long milli){
		try {
			sleep(milli);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
