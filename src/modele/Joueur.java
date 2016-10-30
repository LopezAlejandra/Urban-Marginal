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
	private int orientation;//tourn� vers la gauche
	private int etape;//num�ro d'�tape dans l'animation
	
	protected Label getMessage() {
		return message;
	}

	public Joueur(JeuServeur jeuServeur){
		this.jeuServeur=jeuServeur;
		this.vie=10;
		this.etape=1;
		this.orientation=DROITE;//Droite
	}
	
	
	public void initPerso(String unPseudo, int unNumero,Hashtable<Connection,Joueur>lesJoueurs,ArrayList <Mur>lesMurs ){
		this.pseudo=unPseudo;
		this.numPerso=unNumero;
		label=new Label(Label.getNbLabel(),new JLabel());//contient le personnage
		Label.setNbLabel(Label.getNbLabel() + 1);
		jeuServeur.nouveauLabelJeu(label);
		label.getjLabel().setHorizontalAlignment(SwingConstants.CENTER);
		label.getjLabel().setVerticalAlignment(SwingConstants.CENTER);
		
		  message = new Label(Label.getNbLabel(), new JLabel());
		message.getjLabel().setHorizontalAlignment(SwingConstants.CENTER);
		message.getjLabel().setFont(new Font("Dialog", Font.PLAIN, 8));
		jeuServeur.nouveauLabelJeu(message);
		premierePosition(lesJoueurs, lesMurs);
		
		affiche(MARCHE,etape);
	}

	private boolean toucheJoueur( Hashtable <Connection, Joueur> lesJoueurs){//re�oit en
																				//param�tre lesJoueurs (le dictionnaire de joueurs).
		for (Joueur unJoueur:lesJoueurs.values()){
			if(!unJoueur.equals(this)){//contr�ler que le joueur r�cup�r� n'est pas le m�me que le joueur actuel (l'instance actuelle de la classe)
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
	
	public void affiche(String etat,int etape){//String qui contiendra l'�tat d'un joueur (marche,bless�,mort) et entier qui contiendra le num d'etape
		label.getjLabel().setBounds(posX, posY, L_PERSO, H_PERSO);
		label.getjLabel().setIcon(new ImageIcon(PERSO+this.numPerso+etat+etape+"d"+orientation+EXTIMAGE));
		message.getjLabel().setBounds(posX-10, posY+H_PERSO, L_PERSO+20, H_MESSAGE);
		message.getjLabel().setText(pseudo+" : "+vie);
		
		this.jeuServeur.envoi(super.label);//envoie les labels du joueur � tout le monde
		this.jeuServeur.envoi(message);
	}
	
	public String getPseudo() {
		return pseudo;
	}
	//m�thode qui standarise le d�placement
	private int deplace(int action, int position, int orientation, 
					int lepas, int max, Hashtable<Connection,Joueur> lesJoueurs,
					ArrayList<Mur> lesMurs ){
	this.orientation=orientation;//(l'orientation va changer si les fl�ches gauche ou droite ont �t� utilis�es
	int ancpos=position;// revient � l'ancienne position si la nouvelle n'est pas bonne 
	position=position+lepas;//permet un d�placement dans un sens ou l'autre
	 //il faut contr�ler si on ne sort pas de l'ar�ne
	if (position<0){
		position=0;
	}
	if(position>max){
		position=max;
	}
	
	if (action==GAUCHE||action==DROITE){
		super.posX=position;
		
	}
	else{
		super.posY=position;
	}
	if(this.toucheMur(lesMurs)||this.toucheJoueur(lesJoueurs)){
		position=ancpos;
	}
	etape++;
	if(etape== NBETATSMARCHE){
		etape= 1;
	}
	return position;
	}
}
	
	

