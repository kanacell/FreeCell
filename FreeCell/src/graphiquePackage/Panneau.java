package graphiquePackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import objectPackage.Plateau;
import objectPackage.Stock;
import constantesPackage.Constantes;

public class Panneau extends JPanel{
	private Stock panneauStockage = null;
	private Plateau panneauZonePrincipale = null;
	private Plateau panneauRangement = null;
	private Graphics2D crayon;
	private int largeur, hauteur;
	private int zone;
	
	/*
	 * 2 CONSTRUCTEURS
	 */
	public Panneau (Plateau referencePlateau, int typeDeZone){
		zone = typeDeZone;
		if (typeDeZone == Constantes.Panneau.zoneDeJeu){
			panneauZonePrincipale = referencePlateau;
		}
		else if (typeDeZone == Constantes.Panneau.zoneDeRangement){
			panneauRangement = referencePlateau;
		}
	}
	
	public Panneau (Stock referenceStock){
		panneauStockage = referenceStock;
	}	
	/*
	 * FIN CONSTRUCTEURS
	 */
	
	/*
	 * Methode de Dessin du Panneau
	 */
	public void paintComponent (Graphics g){
		crayon = (Graphics2D) g;
		largeur = getWidth();
		hauteur = getHeight();
		
		if (panneauStockage != null){
			dessinerStockage(crayon);
		}
		else if (panneauZonePrincipale != null){
			dessinerZonePrincipale(crayon);
		}
		else if (panneauRangement != null){
			dessinerRangement(crayon);
		}
	}
	
	/*
	 * Methodes Private de Panneau
	 */
	private void dessinerStockage (Graphics2D crayon){
		crayon.setColor(Color.red);
		crayon.fillRect(0, 0, largeur, hauteur);
	}
	
	private void dessinerZonePrincipale (Graphics2D crayon){
		crayon.setColor(Color.green);
		crayon.fillRect(0, 0, largeur, hauteur);
	}
	
	private void dessinerRangement (Graphics2D crayon){
		crayon.setColor(Color.black);
		crayon.fillRect(0, 0, largeur, hauteur);
	}
}
