package graphiquePackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ListIterator;

import javax.swing.JComponent;

import movePackage.Engine;
import objectPackage.Carte;
import objectPackage.Plateau;
import objectPackage.Stock;
import constantesPackage.Constantes;

public class Panneau_v2 extends JComponent{
	private Graphics2D crayon;
	private int largeur, hauteur;
	private int largeurCarte, hauteurCarte;
	private Engine engi;
	
	/*
	 * Attributs de Selection
	 */
	private int carteSurlignee;
	private int carteSelectionnee;
	private int colonneSurlignee;
	private int colonneSelectionnee;
	/*
	 * FIN Attributs de Selection
	 */
	
	/*
	 * Attributs d'Entiers Graphiques
	 */
	private int coordX, coordY;
	private int decalageInitialX; // = 20;
	private int decalageInitialY; // = 20;
	private int ecartXsurCartes; // = 10;
	private int ecartYsurCartes; // = 25;
	private int ecartXPlateau; // = 15;
	private int ecartYPlateau; // = 20;
	/*
	 * FIN Attributs d'Entiers Graphiques
	 */
	
	private String dimension = "dimensions : ";
	
	
	/*
	 * 1 CONSTRUCTEUR : le Engine + le type de zone du Panneau (stockage, principale, rangement)
	 */	
	public Panneau_v2 (Engine referenceEngi){
		engi = referenceEngi;
		carteSurlignee = -1;
		carteSelectionnee = -1;
		
		GestionSouris_v2 ecouteur = new GestionSouris_v2(this, referenceEngi);
		addMouseListener(ecouteur);
		addMouseMotionListener(ecouteur);
	}
	/*
	 * FIN CONSTRUCTEURS
	 */
	
	/*
	 * ACCESSEURS
	 */
	public int getCarteSurlignee (){
		return carteSurlignee;
	}
	public int getCarteSelectionnee (){
		return carteSelectionnee;
	}
	
	public int getColonneSurlignee (){
		return colonneSurlignee;
	}
	public int getColonneSelectionnee (){
		return colonneSelectionnee;
	}
	
	public int getDecalageInitialX (){
		return decalageInitialX;
	}
	public int getDecalageInitialY (){
		return decalageInitialY;
	}
	public int getEcartXsurCartes (){
		return ecartXsurCartes;
	}
	public int getEcartYsurCartes (){
		return ecartYsurCartes;
	}
	public int getEcartXPlateau (){
		return ecartXPlateau;
	}
	public int getEcartYPlateau (){
		return ecartYPlateau;
	}

	public int getLargeurCarte (){
		return largeurCarte;
	}
	public int getHauteurCarte (){
		return hauteurCarte;
	}
	/*
	 * FIN ACCESSEURS
	 */
	
	/*
	 * Methode de Dessin du Panneau
	 */
	public void paintComponent (Graphics g){
		crayon = (Graphics2D) g;
		
		largeur = getSize().width;
		hauteur = getSize().height;
		
		largeurCarte = largeur / (Constantes.Plateau.nombreColonnes+2);
		hauteurCarte = hauteur / 8;
		ecartXsurCartes = largeurCarte / 6;
		ecartYsurCartes = hauteurCarte / 4;
		
		decalageInitialX = largeurCarte / 5;
		decalageInitialY = hauteurCarte / 3;
		
		ecartXPlateau = decalageInitialX;
		ecartYPlateau = 2* decalageInitialY / 3;
		
		dimension = largeur + " et " + hauteur;
		
		colorierFond(crayon);
		
		System.out.println("dessin de stockage");
		dessinerStockage(crayon, engi.getZoneStockage());

		System.out.println("dessin de rangement");
		dessinerRangement(crayon, engi.getZoneRangement());
		
		System.out.println("dessin de principale");
		dessinerZonePrincipale(crayon, engi.getZonePrincipale());
		
	}
	
	/*
	 * Methodes Publics de Panneau
	 */
	public void activerContours (int numeroCarte){
		carteSurlignee = numeroCarte;
		repaint();
	}
	public void desactiverContours (){
		carteSurlignee = -1;
		repaint();
	}
	
	public void selectionnerCarte (int numeroCarte){
		carteSelectionnee = numeroCarte;
		repaint();
	}
	public void deselectionnerCarte (){
		carteSelectionnee = -1;
		repaint();
	}
	
	public void selectionnerColonne (int numeroColonne){
		
	}
	
	/*
	 * Methodes Private de Panneau
	 */
	private void colorierFond (Graphics2D cryaon){
		crayon.setColor(Color.orange);
		crayon.fillRect(0, 0, largeur, hauteur);
	}
	private void dessinerStockage (Graphics2D crayon, Stock referenceStockage){
		for (int numeroCarte = 0; numeroCarte < referenceStockage.length(); numeroCarte++){
			coordX = decalageInitialX + numeroCarte*(largeurCarte + ecartXsurCartes);
			coordY = decalageInitialY;
			if ( !referenceStockage.isEmpty(numeroCarte) ){
				dessinerCarte(crayon, referenceStockage.getCarteAt(numeroCarte), coordX, coordY);
			}
			else {
				dessinerCarteVide(crayon, coordX, coordY);
			}
		}
		if ( carteSurlignee != -1 ){
			coordX = decalageInitialX + carteSurlignee*(largeurCarte + 10);
			coordY = decalageInitialY;
			dessinerSurlignage(crayon, coordX, coordY);
		}
		if ( carteSelectionnee != -1 ){
			coordX = decalageInitialX + carteSelectionnee*(largeurCarte + 10);
			coordY = decalageInitialY;
			dessinerSelection(crayon, coordX, coordY);
		}
		crayon.drawString(dimension, 10, 10);
	}
	private void dessinerRangement (Graphics2D crayon, Plateau referenceRangement){
		coordX = decalageInitialX + engi.getZoneStockage().length()*(largeurCarte + ecartXsurCartes) + largeurCarte/2;
		for (int numeroColonne = 0; numeroColonne < referenceRangement.length(); numeroColonne++){
			coordY = decalageInitialY;
			
			if ( !referenceRangement.getColonneAt(numeroColonne).isEmpty() ){
				dessinerCarte(crayon, referenceRangement.getLastAt(numeroColonne), coordX, coordY);
			}
			else {
				dessinerCarteVide(crayon, coordX, coordY);
			}
			coordX += (largeurCarte + ecartXsurCartes);
		}
		crayon.drawString(dimension, 10, 10);
	}
	private void dessinerZonePrincipale (Graphics2D crayon, Plateau referencePrincipale){
		
		for (int numeroColonne = 0; numeroColonne < referencePrincipale.length(); numeroColonne++){
			coordY = decalageInitialY + hauteurCarte + ecartYPlateau;
			
			ListIterator<Carte> iterateurColonne = referencePrincipale.getColonneAt(numeroColonne).listIterator();
			
			while ( iterateurColonne.hasNext() ){
				coordX = decalageInitialX + ecartXPlateau + numeroColonne*(largeurCarte + ecartXsurCartes);
				
				if ( !referencePrincipale.getColonneAt(numeroColonne).isEmpty() ){
					dessinerCarte(crayon, iterateurColonne.next(), coordX, coordY);
				}
				else {
					dessinerCarteVide(crayon, coordX, coordY);
				}
				coordY += ecartYsurCartes;
			}
		}
		crayon.drawString(dimension, 10, 10);
	}	
	
	private void dessinerCarte (Graphics2D crayon, Carte referenceCarte, int coordX, int coordY){
		crayon.setColor(Color.black);
		crayon.drawImage(referenceCarte.getImage(), coordX, coordY, largeurCarte, hauteurCarte, this);
	}
	private void dessinerCarteVide (Graphics2D crayon, int coordX, int coordY){
		crayon.setColor(Color.black);
		crayon.drawRect(coordX,  coordY, largeurCarte, hauteurCarte);
	}
	private void dessinerSurlignage (Graphics2D crayon, int coordX, int coordY){
		crayon.setColor(Color.black);
		crayon.drawRect(coordX-1, coordY-1, Constantes.Panneau.largeurCarte+1, Constantes.Panneau.hauteurCarte+1);
	}
	private void dessinerSelection (Graphics2D crayon, int coordX, int coordY){
		crayon.setColor(Color.black);
		crayon.drawRect(coordX-1, coordY-1, Constantes.Panneau.largeurCarte+1, Constantes.Panneau.hauteurCarte+1);
	}
}
