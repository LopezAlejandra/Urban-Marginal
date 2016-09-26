package outils.connexion;
import java.net.Socket;

import javax.swing.JOptionPane;

import java.io.*;

public class Connection  extends Thread{
	private Object leRecepteur;
	private ObjectInputStream in; //canal d'entrée pour recevoir les messages
	private ObjectOutputStream out; //canal de sortie pour envoyer des messages
	
	//Constructeur
	public Connection(Socket socket, Object leRecepteur){
		this.leRecepteur=leRecepteur;
		try {
			out= new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			System.out.println("Erreur création canal de sortie");
			System.exit(0);
		}
		try {
			in=new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			System.out.println("Erreur création canal d'entrée");
			System.exit(0);
		}
		start();
	}
	public void run(){
		boolean inOk=true; //mémorise la déconnection de l'ordinateur distant
		Object reception;
		while(inOk==true){
			try {
				reception= in.readObject();//attendre la réception d'un message de l'ordinateur distant
			} catch (ClassNotFoundException e) {
				System.out.println("Classe non trouvée");
				System.exit(0);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null,"l'ordinateur distant s'est déconnecté");
				inOk=false;
				try {
					in.close();
				} catch (IOException e1) {
					System.out.println("la fermeture du canal s'est mal passée");
				}
				
			}
		}
	
	}
	
	public void envoi(Object unObjet){//ce sera l'Objet a envoyer.
		try {
			this.out.writeObject(unObjet);//appliquez la méthode writeObject sur l'objet out, en mettant en paramètre, unObjet.
			this.out.flush();// pour vider le canal de sortie. 
		} catch (IOException e) {
			System.out.println("L'objet out ne fonctionne pas");
		}
		
	}

}
