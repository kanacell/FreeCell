package graphiquePackage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import objectPackage.Plateau;
import objectPackage.Stock;
import constantesPackage.Constantes;

public class Fenetre extends JFrame{
	private Stock zoneStockage;
	private Plateau zonePrincipale;
	private Plateau zoneRangement;
	
	public Fenetre (String title, Stock referenceStockage, Plateau referencePrincipale, Plateau referenceRangement){
		super(title);
		setSize(600, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		zoneStockage = referenceStockage;
		zonePrincipale = referencePrincipale;
		zoneRangement = referenceRangement;
	}
	
	public void disposition (){
		System.out.println("lancement de disposition");
		JPanel panneau_General = new JPanel();
		panneau_General.setLayout(new BorderLayout());
		
		JPanel panneau_entete = new JPanel();
		panneau_entete.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()/4));
		panneau_entete.setLayout(new GridLayout(1, 2));
		
		Panneau pan_Stockage = new Panneau(zoneStockage);
		Panneau pan_Principal = new Panneau(zonePrincipale, Constantes.Panneau.zoneDeJeu);
		Panneau pan_Rangement = new Panneau(zoneRangement, Constantes.Panneau.zoneDeRangement);
		
		panneau_entete.add(pan_Stockage);
		panneau_entete.add(pan_Rangement);
		
		panneau_General.add(panneau_entete, BorderLayout.NORTH);
		panneau_General.add(pan_Principal, BorderLayout.CENTER);
		
		add(panneau_General);
		
		System.out.println("fin de disposition");
	}
}
