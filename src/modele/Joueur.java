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
	private static final int MAXVIE=10;//vie de départ pour tous les joueurs
	private static final int GAIN=1;//gain de points lors d'une attaque
	private static final int PERTE=2;//perte de points lors d'une attaque
	
	private String pseudo ;
	
	private int numPerso;
	private Label message;// pour l'affichage du pseudo et de la vie
	private JeuServeur jeuServeur;
	private int vie;//vie restante du joueur
	private int orientation;//tourné vers la gauche
	private int etape;//numéro d'étape dans l'animation
	private Boule boule;
	
	public Boule getBoule() {
		return boule;
	}

	
	protected Label getMessage() {
		return message;
	}

	public Joueur(JeuServeur jeuServeur){
		this.jeuServeur=jeuServeur;
		this.vie=MAXVIE;
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
	    Label.setNbLabel(Label.getNbLabel() + 1);
		message.getjLabel().setHorizontalAlignment(SwingConstants.CENTER);
		message.getjLabel().setFont(new Font("Dialog", Font.PLAIN, 10));
		jeuServeur.nouveauLabelJeu(message);
		premierePosition(lesJoueurs, lesMurs);
		
		affiche(MARCHE,etape);
		
		boule=new Boule(jeuServeur);
		
		jeuServeur.envoi(boule.label);
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
		message.getjLabel().setBounds(posX-10, posY+H_PERSO, L_PERSO+20, H_MESSAGE);
		message.getjLabel().setText(pseudo+" : "+vie);
		
		this.jeuServeur.envoi(super.label);//envoie les labels du joueur à tout le monde
		this.jeuServeur.envoi(message);
	}
	
	public String getPseudo() {
		return pseudo;
	}
	//méthode qui standarise le déplacement
	private int deplace(int action, int position, int orientation, 
					int lepas, int max, Hashtable<Connection,Joueur> lesJoueurs,
					ArrayList<Mur> lesMurs ){
	this.orientation=orientation;//(l'orientation va changer si les flèches gauche ou droite ont été utilisées
	int ancpos=position;// revient à l'ancienne position si la nouvelle n'est pas bonne 
	position=position+lepas;//permet un déplacement dans un sens ou l'autre
	 //il faut contrôler si on ne sort pas de l'arène
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

	public void action(int action, Hashtable <Connection,Joueur> lesJoueurs, ArrayList <Mur>lesMurs){ 
		switch (action){
		case GAUCHE :
			posX = deplace(action, super.posX, GAUCHE, -LEPAS, L_ARENE-(H_PERSO+H_MESSAGE),lesJoueurs, lesMurs);
			break;
		case DROITE:
			posX = deplace(action, super.posX, DROITE, LEPAS, L_ARENE-(H_PERSO+H_MESSAGE),lesJoueurs, lesMurs);
			break;
		case HAUT:
			posY = deplace(action, super.posY, orientation, -LEPAS, H_ARENE-(H_PERSO+H_MESSAGE),lesJoueurs, lesMurs);
			break;
		case BAS:
			posY = deplace(action, super.posY, orientation, LEPAS, H_ARENE-(H_PERSO+H_MESSAGE),lesJoueurs, lesMurs);
			break;
		case TIRE:
			if(!boule.getLabel().getjLabel().isVisible()){
			boule.tireBoule(this,lesMurs,lesJoueurs);
			}
			break;
		}
		affiche(MARCHE,etape);// afin qu'après l'action, le personnage soit réaffiché et envoyé à tous. La 
		//Le déplacement et les collisions finis
		
	}
	
	public int getOrientation(){
		return orientation;
	}
	//Qui ajout gain à la vie
	public void gainVie(){
		vie=vie+GAIN;
	}
	public void perteVie(){
		vie=vie-PERTE;
		if(vie < 0){
			vie = 0;
		}
	}
	
	public boolean estMort(){
		if(vie==0){
			return true;
		}
		return false;
	}
	public void departJoueur(){
		if(this.label!=null){//controler si le label du personnage existe
			this.message.getjLabel().setVisible(false);
			super.label.getjLabel().setVisible(false);
			this.boule.getLabel().getjLabel().setVisible(false);
			jeuServeur.envoi(message);
			jeuServeur.envoi(label);
			jeuServeur.envoi(boule);
		}
	}
	
	
	
}
	
	

