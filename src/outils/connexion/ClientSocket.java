package outils.connexion;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class ClientSocket {
	private boolean connexionOk;

	/**
	 * Constructeur
	 * @param ip
	 * @param port
	 * @param leRecepteur
	 */
	public ClientSocket(String ip,int port, Object leRecepteur){
		//Dans le constructeur, déclarez une variable socket de type Socket : 
		//cet objet recevra le socket client créé lors de la tentative de 
		//connexion au serveur. 
		connexionOk=false;
		Socket socket;
		try {
			socket=new Socket(ip,port);
			System.out.println("La connexion au serveur a réussi");//Tout s'est bien passé
			connexionOk=true;
			new Connection(socket,leRecepteur);//lance le thread indépendant
											   //pour attendre les messages provenant du serveur
		} catch (UnknownHostException e) {//serveur n'est pas disponible
		
			JOptionPane.showMessageDialog(null, "erreur : serveur indisponible");
		} catch (IOException e) {// il y a eu un problème d'entrée/sortie
			
			JOptionPane.showMessageDialog(null, "erreur: vérifiez l'adresse ip");
		}
		
	}

	/**
	 * @return the connexionOk
	 */
	public boolean getConnexionOk() {
		return connexionOk;
	}

	
	}
