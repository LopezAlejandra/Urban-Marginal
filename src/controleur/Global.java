package controleur;

public interface Global {
	public static final int PORT=6666;
	public static final String SEPARATOR = "\\";
	public static final String CHEMIN="media"+SEPARATOR;
	public static final String CHEMINFONDS=CHEMIN+"fonds"+SEPARATOR;
	public static final String FONDCHOIX=CHEMINFONDS+"fondchoix.jpg";
	public static final int GAUCHE=0;
	public static final int DROITE=1;
	public static final String CHEMINPERSOS=CHEMIN+"personnages"+SEPARATOR;
	public static final String PERSO= CHEMINPERSOS+"perso";
	public static final String EXTIMAGE=".gif";
	public static final String MARCHE ="marche"; //les différents états
	public static final String BLESSE="touche";
	public static final String MORT="mort";
	public static final int NBPERSOS=3; //nombre de personnages
	public static final int H_PERSO=44; // taille de l'image du personnage
	public static final int L_PERSO=39;
	public static final int PSEUDO = 0; 
	public static final String SEPARE="~";
	public static final int H_ARENE = 600;
	public static final int L_ARENE = 800;
	public static final int H_CHAT = 200;
	public static final int H_SAISIE = 25;
	public static final int MARGE = 5; // elle va servir pour les écarts entre différents objets
	
	
	
}
