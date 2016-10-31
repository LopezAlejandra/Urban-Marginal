package modele;

import java.io.Serializable;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Label implements Serializable {
	private int numLabel;
	private JLabel jLabel;
	public static int nbLabel;// va permettre de m�moriser le num�ro du dernier Label ajout�, afin de g�rer le num�ro suivant. 

	public Label(int numLabel, JLabel jLabel){
		this.numLabel = numLabel;
		this.jLabel = jLabel;
	}
	
	public static int getNbLabel(){
		return nbLabel;
	}

	public static void setNbLabel(int nbLabel) {
		Label.nbLabel = nbLabel;
	}
	
	public int getNumLabel() {
		return numLabel;
	}

	public JLabel getjLabel() {
		return jLabel;
	}
	
}

