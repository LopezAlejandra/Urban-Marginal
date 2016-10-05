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
		//Dans le constructeur, d�clarez une variable socket de type Socket : 
		//cet objet recevra le socket client cr�� lors de la tentative de 
		//connexion au serveur. 
		connexionOk=false;
		Socket socket;
		try {
			socket=new Socket(ip,port);
			System.out.println("La connexion au serveur a r�ussi");//Tout s'est bien pass�
			connexionOk=true;
			new Connection(socket,leRecepteur);//lance le thread ind�pendant
											   //pour attendre les messages provenant du serveur
		} catch (UnknownHostException e) {//serveur n'est pas disponible
		
			JOptionPane.showMessageDialog(null, "erreur : serveur indisponible");
		} catch (IOException e) {// il y a eu un probl�me d'entr�e/sortie
			
			JOptionPane.showMessageDialog(null, "erreur: v�rifiez l'adresse ip");
		}
		
	}

	/**
	 * @return the connexionOk
	 */
	public boolean getConnexionOk() {
		return connexionOk;
	}

	
	}
