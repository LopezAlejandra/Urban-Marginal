package outils.connexion;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServeurSocket extends Thread {
	
	
	private Object leRecepteur;
	private ServerSocket serverSocket;//Classe Server Socket 
									  //permet de se mettre à l'écoute de clients,
	
	//Constructeur 
	public ServeurSocket(Object leRecepteur, int port){
		this.leRecepteur=leRecepteur;
		try {
			this.serverSocket=new ServerSocket(port);
	
		} catch (IOException e) {
			System.out.println("erreur grave création socket serveur:"+e);//afficher le message d'erreur dans la console
			System.exit(0);//Arrêter l'application
		}
		super.start();
	}
	
	public void run(){
		Socket socket; //Permet de gérer un simple socket(client)
		while(true){
			try {
				System.out.println("le serveur attend");
				socket=this.serverSocket.accept();//attente de client
				System.out.println("un client s'est connecté");
				new Connection(socket, this.leRecepteur);// le socket du client qui vient  d'être récupéré
			} catch (IOException e) {
				System.out.println("Erreur: création du socket : " + e);
				System.exit(0);
			}
			
		}
		
	}
}



