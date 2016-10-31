package modele;

import java.io.Serializable;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Label implements Serializable {
	private int numLabel;
	private JLabel jLabel;
	public static int nbLabel;// va permettre de mémoriser le numéro du dernier Label ajouté, afin de gérer le numéro suivant. 

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

