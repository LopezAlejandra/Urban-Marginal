package controleur;
import javax.swing.JFrame;

import vue.EntreeJeu;

public class Controle {

	public static void main(String[] args) {
		new Controle();
	}	
	private EntreeJeu frmEntreeJeu;
	
	
	public Controle(){
		
		this.frmEntreeJeu= new EntreeJeu(this);//cr�ation de la frame
		frmEntreeJeu.setVisible(true);//rend visible la frame
	}
	public void evenementVue(JFrame uneFrame, Object info){
		frmEntreeJeu =(EntreeJeu) uneFrame;
		
		if (uneFrame instanceof EntreeJeu){
			evenementEntreeJeu(info);
		}
	}
	
	private void evenementEntreeJeu(Object info) {//D�marrer un jeu serveur ou un jeu client
		
		
	}
	
	
	
}

