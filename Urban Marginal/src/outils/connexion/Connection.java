package outils.connexion;
import java.net.Socket;

import javax.swing.JOptionPane;

import java.io.*;

public class Connection  extends Thread{
	private Object leRecepteur;
	private ObjectInputStream in; //canal d'entr�e pour recevoir les messages
	private ObjectOutputStream out; //canal de sortie pour envoyer des messages
	
	//Constructeur
	public Connection(Socket socket, Object leRecepteur){
		this.leRecepteur=leRecepteur;
		try {
			out= new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			System.out.println("Erreur cr�ation canal de sortie");
			System.exit(0);
		}
		try {
			in=new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			System.out.println("Erreur cr�ation canal d'entr�e");
			System.exit(0);
		}
		start();
	}
	public void run(){
		boolean inOk=true; //m�morise la d�connection de l'ordinateur distant
		Object reception;
		while(inOk==true){
			try {
				reception= in.readObject();//attendre la r�ception d'un message de l'ordinateur distant
			} catch (ClassNotFoundException e) {
				System.out.println("Classe non trouv�e");
				System.exit(0);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null,"l'ordinateur distant s'est d�connect�");
				inOk=false;
				try {
					in.close();
				} catch (IOException e1) {
					System.out.println("la fermeture du canal s'est mal pass�e");
				}
				
			}
		}
	
	}
	
	public void envoi(Object unObjet){//ce sera l'Objet a envoyer.
		try {
			this.out.writeObject(unObjet);//appliquez la m�thode writeObject sur l'objet out, en mettant en param�tre, unObjet.
			this.out.flush();// pour vider le canal de sortie. 
		} catch (IOException e) {
			System.out.println("L'objet out ne fonctionne pas");
		}
		
	}

}
