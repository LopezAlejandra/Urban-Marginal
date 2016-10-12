package modele;

import java.io.Serializable;

import javax.swing.JLabel;

public class Label implements Serializable {
	private static int nbLabel;//va permettre de m�moriser le num�ro du dernier Label ajout�
	private int numLabel;
	private JLabel jLabel;
	
	public Label(int numLabel,JLabel jLabel){
		this.numLabel=numLabel;
		this.jLabel=jLabel;
		
	}
	
	public static int getNbLabel() {
		return nbLabel;
	}
	public JLabel getJLabel(){
		return jLabel;
	}

	}

