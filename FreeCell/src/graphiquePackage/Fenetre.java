package graphiquePackage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import movePackage.Engine;
import constantesPackage.Constantes;

public class Fenetre extends JFrame{
	/*
	 * 1 CONSTRUCTEUR
	 */
	public Fenetre (String title){
		super(title);
		setSize(600, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	/*
	 * FIN CONSTRUCTEUR
	 */
	
	/*
	 * Methodes Public de Fenetre
	 */
	public void disposition (Engine engi){
		JPanel panneau_General = new JPanel();
		panneau_General.setLayout(new BorderLayout());
		
		JPanel panneau_entete = new JPanel();
		panneau_entete.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()/4));
		panneau_entete.setLayout(new GridLayout(1, 2));
		
		Panneau pan_Stockage = new Panneau(engi, Constantes.Panneau.zoneDeStockage);
		Panneau pan_Principal = new Panneau(engi, Constantes.Panneau.zoneDeJeu);
		Panneau pan_Rangement = new Panneau(engi, Constantes.Panneau.zoneDeRangement);
		
		GestionSouris ecouteur_ = new GestionSouris(engi);
		
		pan_Stockage.addMouseListener(new GestionSouris(engi, engi.getZoneStockage()));
		pan_Principal.addMouseListener(new GestionSouris(engi, engi.getZonePrincipale(), Constantes.Panneau.zoneDeJeu) );
		pan_Rangement.addMouseListener(new GestionSouris(engi, engi.getZoneRangement(), Constantes.Panneau.zoneDeRangement) );
		
		panneau_entete.add(pan_Stockage);
		panneau_entete.add(pan_Rangement);
		
		panneau_General.add(panneau_entete, BorderLayout.NORTH);
		panneau_General.add(pan_Principal, BorderLayout.CENTER);
		
		add(panneau_General);
	}
}
