package vue;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controleur.Global;

import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;

public class ChoixJoeur extends JFrame implements Global {
	String FONDCHOIX;
	

	private JPanel contentPane;
	private JTextField txtPseudo;
	
	private int numPerso;
	private JLabel lblPersonnage;
	
	/**
	 * Create the frame.
	 */
	public ChoixJoeur() {
		setTitle("Choice");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 416, 313);
		contentPane = new JPanel();/////
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		
		
		JLabel lblPrecedent = new JLabel("");
		lblPrecedent.addMouseListener(new MouseAdapter() {//
			@Override
			public void mouseClicked(MouseEvent arg0) {
				lblPrecedent_clic();
			}
			
			
		});
		lblPrecedent.setBounds(58, 161, 46, 14);
		contentPane.add(lblPrecedent);
		
		JLabel lblSuivant = new JLabel("");
		lblSuivant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblSuivant_clic();
			}
		});
		lblSuivant.setBounds(293, 161, 46, 14);
		contentPane.add(lblSuivant);
		
		lblPersonnage = new JLabel("");
		
		lblPersonnage.setBounds(183, 152, 39, 44);
		contentPane.add(lblPersonnage);
		//
		JLabel lblGo = new JLabel("");
		lblGo.addMouseListener(new MouseAdapter() {
			
			public void mouseEntered(MouseEvent e) {
				souris_normale();
			}
			
			public void mouseExited(MouseEvent e) {
				souris_doigt();
			}
			
			
		});
		lblGo.setBounds(323, 218, 46, 14);
		contentPane.add(lblGo);//
		
		txtPseudo = new JTextField();
		txtPseudo.setBounds(138, 246, 125, 20);
		contentPane.add(txtPseudo);
		txtPseudo.setColumns(10);
		
		txtPseudo.requestFocus();
		
		JLabel lblFond = new JLabel("");//
		lblFond.setBounds(0,0, 400, 275);
		lblFond.setIcon(new ImageIcon("F:\\U_Marginal\\Urban Marginal\\bin\\media\\fonds\\fondchoix.jpg"));
		
		contentPane.add(lblFond);
		numPerso=1;
		affichePerso();
	}
	
	private void affichePerso() {
			//Donc, en concaténant correctement des constantes, des chaînes et numPerso, affichez le bon personnage dans la bonne position (n'oubliez pas à la fin de concaténer l'extension
			while(numPerso<=3){
			lblPersonnage.setIcon(new ImageIcon("F:\\U_Marginal\\Urban Marginal\\bin\\"+PERSO+numPerso+MARCHE+numPerso +"d"+GAUCHE+EXTIMAGE ));
			numPerso=numPerso+1;
			}	
	}
	public void lblPrecedent_clic(){
		
		
	}
	public void lblSuivant_clic(){
		
	}
	private void souris_normale(){//Change l'aspect de la souris.
		contentPane.setCursor(new Cursor(DEFAULT_CURSOR));
	}
	private void souris_doigt(){
		contentPane.setCursor(new Cursor(HAND_CURSOR));
	}
	
	
	


}
