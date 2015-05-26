package graphiquePackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ListIterator;

import javax.swing.JPanel;

import movePackage.Engine;
import objectPackage.Carte;
import objectPackage.Plateau;
import objectPackage.Stock;
import constantesPackage.Constantes;

public class Panneau extends JPanel{
	private Graphics2D crayon;
	private int largeur, hauteur;
	private int numeroZone;
	
	private String dimension = "dimensions : ";
	
	private Engine engi;
	
	/*
	 * 1 CONSTRUCTEUR : le Engine + le type de zone du Panneau (stockage, principale, rangement)
	 */	
	public Panneau (Engine referenceEngi, int typeDeZone){
		engi = referenceEngi;
		numeroZone = typeDeZone;
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
		
		switch (numeroZone){
		case Constantes.Panneau.zoneDeStockage:
			dessinerStockage(crayon, engi.getZoneStockage());
			break;
		case Constantes.Panneau.zoneDeJeu:
			dessinerZonePrincipale(crayon, engi.getZonePrincipale());
			break;
		case Constantes.Panneau.zoneDeRangement:
			dessinerRangement(crayon, engi.getZoneRangement());
			break;
		}
	}
	
	/*
	 * Methodes Private de Panneau
	 */
	private void dessinerStockage (Graphics2D crayon, Stock referenceStockage){
		crayon.setColor(Color.pink);
		crayon.fillRect(0, 0, largeur, hauteur);
		int coordX, coordY;
		for (int numeroCarte = 0; numeroCarte < referenceStockage.length(); numeroCarte++){
			coordX = 15 + numeroCarte*(Constantes.Panneau.largeurCarte + 10);
			coordY = 20;
			if ( !referenceStockage.isEmpty(numeroCarte) ){
				dessinerCarte(crayon, referenceStockage.getCarteAt(numeroCarte), coordX, coordY);
			}
			else {
				dessinerCarteVide(crayon, coordX, coordY);
			}
		}
		crayon.drawString(dimension, 10, 10);
	}
	
	private void dessinerZonePrincipale (Graphics2D crayon, Plateau referencePrincipale){
		crayon.setColor(Color.orange);
		crayon.fillRect(0, 0, largeur, hauteur);
		int compteurCarte, coordX, coordY;
		for (int numeroColonne = 0; numeroColonne < referencePrincipale.length(); numeroColonne++){
			compteurCarte = 0;
			ListIterator<Carte> iterateurColonne = referencePrincipale.getColonneAt(numeroColonne).listIterator();
			while ( iterateurColonne.hasNext() ){
				coordX = 20 + numeroColonne*(Constantes.Panneau.largeurCarte + 10);
				coordY = 20 + compteurCarte*20;
				if ( !referencePrincipale.getColonneAt(numeroColonne).isEmpty() ){
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
	
	private void dessinerRangement (Graphics2D crayon, Plateau referenceRangement){
		crayon.setColor(Color.cyan);
		crayon.fillRect(0, 0, largeur, hauteur);
		int coordX, coordY;
		for (int numeroColonne = 0; numeroColonne < referenceRangement.length(); numeroColonne++){
			coordX = 5 + numeroColonne*(Constantes.Panneau.largeurCarte + 10);
			coordY = 20;
			if ( !referenceRangement.getColonneAt(numeroColonne).isEmpty() ){
				dessinerCarte(crayon, referenceRangement.getLastAt(numeroColonne), coordX, coordY);
			}
			else {
				dessinerCarteVide(crayon, coordX, coordY);
			}
		}
		crayon.drawString(dimension, 10, 10);
	}
	
	private void dessinerCarte (Graphics2D crayon, Carte referenceCarte, int coordX, int coordY){
		crayon.setColor(Color.black);
		crayon.drawImage(referenceCarte.getImage(), coordX, coordY, Constantes.Panneau.largeurCarte, Constantes.Panneau.hauteurCarte, this);
	}
	
	private void dessinerCarteVide (Graphics2D crayon, int coordX, int coordY){
		crayon.setColor(Color.black);
		crayon.drawRect(coordX,  coordY, Constantes.Panneau.largeurCarte, Constantes.Panneau.hauteurCarte);
	}


}
