package vue;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controleur.Controle;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EntreeJeu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtIp;
	
	private void btnConnect_clic(){
		controle.evenementVue(this, txtIp.getText());//envoi de l'adresse Ip 
		
	}
	private void btnStart_clic() {
		controle.evenementVue(this, "serveur");
	}
	/**
	 * Create the frame.
	 * @param controle 
	 */
	private Controle controle;
	public EntreeJeu(Controle controle) {
		
		
		setTitle("Urban Marginal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblStartAServer = new JLabel("Start a server:");
		lblStartAServer.setBounds(82, 65, 99, 14);
		contentPane.add(lblStartAServer);
		
		JLabel lblConnectAnExisting = new JLabel("Connect an existing server:");
		lblConnectAnExisting.setBounds(82, 90, 141, 14);
		contentPane.add(lblConnectAnExisting);
		
		JLabel lblIpServer = new JLabel("IP Server:");
		lblIpServer.setBounds(82, 115, 60, 14);
		contentPane.add(lblIpServer);
		
		JButton btnStart = new JButton("Start");
		btnStart.addMouseListener(new MouseAdapter() {
			
			// clic sur le bouton Start pour lancer le serveur
			 
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnStart_clic();
			}

		
			
		});
		btnStart.setBounds(275, 61, 89, 23);
		contentPane.add(btnStart);
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.addMouseListener(new MouseAdapter(){
			// Clic sur le bouton connect pour se connecter
			 
			public void mouseClicked(MouseEvent arg0) {
				btnConnect_clic();
			}
			
		
			
		});
		btnConnect.setBounds(275, 90, 89, 23);
		contentPane.add(btnConnect);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addMouseListener(new MouseAdapter() {
			//Clic sur le bouton Exit pour arreter l'application
			 
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnExit_clic();
			}

		
			private void btnExit_clic(){
			System.exit(0);
		}
		});
		btnExit.setBounds(275, 124, 89, 23);
		contentPane.add(btnExit);
		
		
		txtIp = new JTextField();
		txtIp.setText("127.0.0.1");
		txtIp.setBounds(139, 115, 86, 20);
		contentPane.add(txtIp);
		txtIp.setColumns(10);
			this.controle=controle;
			}

}





