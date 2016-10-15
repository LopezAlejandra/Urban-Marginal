package modele;

import java.io.Serializable;

import javax.swing.JLabel;

public class Label implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numLabel;
	private JLabel jLabel;
	private static int nbLabel;// va permettre de m�moriser le num�ro du dernier Label ajout�, afin de g�rer le num�ro suivant. 

	public Label(int numLabel, JLabel jLabel){
		this.numLabel = numLabel;
		this.jLabel = jLabel;
	}
	public int getNumLabel() {
		return numLabel;
	}

	public JLabel getjLabel() {
		return jLabel;
	}
	
	public static int getNbLabel() {
		return nbLabel;
	}

	public static void setNbLabel(int nbLabel) {
		Label.nbLabel = nbLabel;
	}
	}

