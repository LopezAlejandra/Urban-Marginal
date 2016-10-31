package outils.connexion;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServeurSocket extends Thread {
	
	
	private Object leRecepteur;
	private ServerSocket serverSocket;//Classe Server Socket 
									  //permet de se mettre � l'�coute de clients,
	
	//Constructeur 
	public ServeurSocket(Object leRecepteur, int port){
		this.leRecepteur=leRecepteur;
		try {
			this.serverSocket=new ServerSocket(port);
	
		} catch (IOException e) {
			System.out.println("erreur grave cr�ation socket serveur:"+e);//afficher le message d'erreur dans la console
			System.exit(0);//Arr�ter l'application
		}
		super.start();
	}
	
	public void run(){
		Socket socket; //Permet de g�rer un simple socket(client)
		while(true){
			try {
				System.out.println("le serveur attend");
				socket=this.serverSocket.accept();//attente de client
				System.out.println("un client s'est connect�");
				new Connection(socket, this.leRecepteur);// le socket du client qui vient  d'�tre r�cup�r�
			} catch (IOException e) {
				System.out.println("Erreur: cr�ation du socket : " + e);
				System.exit(0);
			}
			
		}
		
	}
}



