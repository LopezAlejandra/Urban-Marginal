package modele;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import controleur.Global;
import outils.connexion.Connection;

public class Joueur extends Objet implements Global {
	private String pseudo ;
	private int numPerso;
	private Label message;// pour l'affichage du pseudo et de la vie
	private JeuServeur jeuServeur;
	private int vie;//vie restante du joueur
	private int orientation;//tourné vers la gauche
	private int etape;//numéro d'étape dans l'animation
	
	protected Label getMessage() {
		return message;
	}

	public Joueur(JeuServeur jeuServeur){
		this.jeuServeur=jeuServeur;
		this.vie=10;
		this.etape=1;
		this.orientation=1;//Droite
	}
	
	
	public void initPerso(String unPseudo, int unNumero,Hashtable<Connection,Joueur>lesJoueurs,ArrayList <Mur>lesMurs ){
		this.pseudo=unPseudo;
		this.numPerso=unNumero;
		label=new Label(Label.getNbLabel()+1,new JLabel());//contient le personnage
		Label.setNbLabel(Label.getNbLabel() + 1);
		jeuServeur.nouveauLabelJeu(label);
		label.getjLabel().setHorizontalAlignment(SwingConstants.CENTER);
		label.getjLabel().setVerticalAlignment(SwingConstants.CENTER);
		
		message = new Label(Label.getNbLabel()+1, new JLabel());
		message.getjLabel().setHorizontalAlignment(SwingConstants.CENTER);
		message.getjLabel().setFont(new Font("Dialog", Font.PLAIN, 8));
		jeuServeur.nouveauLabelJeu(message);
		premierePosition(lesJoueurs, lesMurs);
		
		affiche(MARCHE,etape);
	}

	private boolean toucheJoueur( Hashtable <Connection, Joueur> lesJoueurs){//reçoit en
																				//paramètre lesJoueurs (le dictionnaire de joueurs).
		for (Joueur unJoueur:lesJoueurs.values()){
			if(!unJoueur.equals(this)){//contrôler que le joueur récupéré n'est pas le même que le joueur actuel (l'instance actuelle de la classe)
				if(super.toucheObjet(unJoueur)){
					return true;//il y a collision,
				}
			}
		}
		return false;//aucune collision
		
	}
	
	private boolean toucheMur(ArrayList <Mur>lesMurs){
		for (Mur unMur:lesMurs){
			if(super.toucheObjet(unMur)){
				return true;
			}
		}
		return false;
	}
	
	private void premierePosition(Hashtable<Connection,Joueur>lesJoueurs,ArrayList <Mur>lesMurs){
		label.getjLabel().setBounds(0, 0, L_PERSO, H_PERSO);//affecter une taille au JLabel du personnage
		do{
			super.posX = (int)Math.round(Math.random()* (L_ARENE-L_PERSO)) ; 
			super.posY = (int)Math.round(Math.random()* (H_ARENE-H_PERSO-H_MESSAGE)) ; 
		}
		while(toucheJoueur(lesJoueurs)||toucheMur(lesMurs));//tant quel'instance actuelle touche un mur ou un autre joueur.
	}
	
	public void affiche(String etat,int etape){//String qui contiendra l'état d'un joueur (marche,blessé,mort) et entier qui contiendra le num d'etape
		label.getjLabel().setBounds(posX, posY, L_PERSO, H_PERSO);
		label.getjLabel().setIcon(new ImageIcon(PERSO+this.numPerso+etat+etape+"d"+orientation+EXTIMAGE));
		message.getjLabel().setBounds(posX-10, posY, L_PERSO+0, H_MESSAGE);
		message.getjLabel().setText(pseudo+" : "+vie);
		
		this.jeuServeur.envoi(super.label);//envoie les labels du joueur à tout le monde
		this.jeuServeur.envoi(this.message);
	}
	
}
	
	

