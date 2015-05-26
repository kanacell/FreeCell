package graphiquePackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ListIterator;

import javax.swing.JPanel;

import objectPackage.Carte;
import objectPackage.Plateau;
import objectPackage.Stock;
import constantesPackage.Constantes;

public class Panneau extends JPanel{
	private Stock zoneStockage = null;
	private Plateau zonePrincipale = null;
	private Plateau zoneRangement = null;
	private Graphics2D crayon;
	private int largeur, hauteur;
	private int numeroZone;
	
	private String dimension = "dimensions : ";
	
	/*
	 * 2 CONSTRUCTEURS
	 */
	public Panneau (Plateau referencePlateau, int typeDeZone){
		numeroZone = typeDeZone;
		if (typeDeZone == Constantes.Panneau.zoneDeJeu){
			zonePrincipale = referencePlateau;
		}
		else if (typeDeZone == Constantes.Panneau.zoneDeRangement){
			zoneRangement = referencePlateau;
		}
	}
	
	public Panneau (Stock referenceStock){
		zoneStockage = referenceStock;
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
		dimension = largeur + " et " + hauteur;
		
		if (zoneStockage != null){
			dessinerStockage(crayon);
		}
		else if (zonePrincipale != null){
			dessinerZonePrincipale(crayon);
		}
		else if (zoneRangement != null){
			dessinerRangement(crayon);
		}
	}
	
	/*
	 * Methodes Private de Panneau
	 */
	private void dessinerStockage (Graphics2D crayon){
		crayon.setColor(Color.pink);
		crayon.fillRect(0, 0, largeur, hauteur);
		int coordX, coordY;
		for (int numeroCarte = 0; numeroCarte < zoneStockage.length(); numeroCarte++){
			crayon.drawRect(15 + numeroCarte*(Constantes.Panneau.largeurCarte+10), 20, Constantes.Panneau.largeurCarte, Constantes.Panneau.hauteurCarte);
			coordX = 15 + numeroCarte*(Constantes.Panneau.largeurCarte + 10);
			coordY = 20;
			if ( !zoneStockage.isEmpty(numeroCarte) ){
				dessinerCarte(crayon, zoneStockage.getCarteAt(numeroCarte), coordX, coordY);
			}
			else {
				dessinerCarteVide(crayon, coordX, coordY);
			}
		}
		crayon.drawString(dimension, 10, 10);
	}
	
	private void dessinerZonePrincipale (Graphics2D crayon){
		crayon.setColor(Color.orange);
		crayon.fillRect(0, 0, largeur, hauteur);
		int compteurCarte, coordX, coordY;
		for (int numeroColonne = 0; numeroColonne < zonePrincipale.length(); numeroColonne++){
			compteurCarte = 0;
			ListIterator<Carte> iterateurColonne = zonePrincipale.getColonneAt(numeroColonne).listIterator();
			while ( iterateurColonne.hasNext() ){
				coordX = 20 + numeroColonne*(Constantes.Panneau.largeurCarte + 10);
				coordY = 20 + compteurCarte*20;
				if ( !zonePrincipale.getColonneAt(numeroColonne).isEmpty() ){
					dessinerCarte(crayon, iterateurColonne.next(), coordX, coordY);
				}
				else {
					dessinerCarteVide(crayon, coordX, coordY);
				}				
				compteurCarte++;
			}
		}
		crayon.drawString(dimension, 10, 10);
	}
	
	private void dessinerRangement (Graphics2D crayon){
		crayon.setColor(Color.cyan);
		crayon.fillRect(0, 0, largeur, hauteur);
		int coordX, coordY;
		for (int numeroColonne = 0; numeroColonne < zoneRangement.length(); numeroColonne++){
			coordX = 5 + numeroColonne*(Constantes.Panneau.largeurCarte + 10);
			coordY = 20;
			if ( !zoneRangement.getColonneAt(numeroColonne).isEmpty() ){
				dessinerCarte(crayon, zoneRangement.getLastAt(numeroColonne), coordX, coordY);
			}
			else {
				dessinerCarteVide(crayon, coordX, coordY);
			}
		}
		crayon.drawString(dimension, 10, 10);
	}
	
	private void dessinerCarte (Graphics2D crayon, Carte carte, int coordX, int coordY){
		crayon.setColor(Color.black);
		crayon.drawImage(carte.getImage(), coordX, coordY, Constantes.Panneau.largeurCarte, Constantes.Panneau.hauteurCarte, this);
	}
	
	private void dessinerCarteVide (Graphics2D crayon, int coordX, int coordY){
		crayon.setColor(Color.black);
		crayon.drawRect(coordX,  coordY, Constantes.Panneau.largeurCarte, Constantes.Panneau.hauteurCarte);
	}
}
