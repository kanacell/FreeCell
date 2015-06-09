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

public class Panneau extends JComponent{
	private Graphics2D crayon;
	private int largeur, hauteur;
	private int largeurCarte, hauteurCarte;
	private Engine engi;
	
	/*
	 * Attributs de Selection
	 */
	private int stockageSurlignee;
	private int stockageSelectionnee;
	private int rangementSurlignee;
	private int rangementSelectionnee;
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
	public Panneau (Engine referenceEngi){
		engi = referenceEngi;
		stockageSurlignee = -1;
		stockageSelectionnee = -1;
		rangementSurlignee = -1;
		rangementSelectionnee = -1;
		colonneSurlignee = -1;
		colonneSelectionnee = -1;
		
		GestionSouris ecouteur = new GestionSouris(this, referenceEngi);
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
		return stockageSurlignee;
	}
	public int getCarteSelectionnee (){
		return stockageSelectionnee;
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
		
		dessinerStockage(crayon, engi.getZoneStockage());

		dessinerRangement(crayon, engi.getZoneRangement());
		
		dessinerZonePrincipale(crayon, engi.getZonePrincipale());
		
	}
	
	/*
	 * Methodes Publics de Panneau
	 */
	public void activerContours (int numeroCarte){
		stockageSurlignee = numeroCarte;
		repaint();
	}
	public void desactiverContours (){
		stockageSurlignee = -1;
		repaint();
	}
	
	public void selectionnerStock (int numeroCarte){
		stockageSelectionnee = numeroCarte;
		rangementSelectionnee = -1;
		colonneSelectionnee = -1;
		repaint();
	}
	public void selectionnerRangement (int numeroColonne){
		stockageSelectionnee = -1;
		rangementSelectionnee = numeroColonne;
		colonneSelectionnee = -1;
		repaint();
	}
	public void selectionnerColonne (int numeroColonne){
		stockageSelectionnee = -1;
		rangementSelectionnee = -1;
		colonneSelectionnee = numeroColonne;
		repaint();
	}
	public void deselectionner (){
		stockageSelectionnee = -1;
		rangementSelectionnee = -1;
		colonneSelectionnee = -1;
		repaint();
	}
	
	public void surlignerStock (int numeroCarte){
		stockageSurlignee = numeroCarte;
		rangementSurlignee = -1;
		colonneSurlignee = -1;
		repaint();
	}
	public void surlignerRangement (int numeroColonne){
		stockageSurlignee = -1;
		rangementSurlignee = numeroColonne;
		colonneSurlignee = -1;
		repaint();
	}
	public void surlignerColonne (int numeroColonne){
		stockageSurlignee = -1;
		rangementSurlignee = -1;
		colonneSurlignee = numeroColonne;
		repaint();
	}
	public void desurligner (){
		stockageSurlignee = -1;
		rangementSurlignee = -1;
		colonneSurlignee = -1;
		repaint();
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
		if ( stockageSurlignee != -1 ){
			coordX = decalageInitialX + stockageSurlignee*(largeurCarte + ecartXsurCartes);
			dessinerSurlignage(crayon, coordX, coordY);
		}
		if ( stockageSelectionnee != -1 ){
			coordX = decalageInitialX + stockageSelectionnee*(largeurCarte + ecartXsurCartes);
			dessinerSelection(crayon, coordX, coordY);
		}
	}
	private void dessinerRangement (Graphics2D crayon, Plateau referenceRangement){
		coordX = decalageInitialX + engi.getZoneStockage().length()*(largeurCarte + ecartXsurCartes) + largeurCarte/2;
		coordY = decalageInitialY;
		for (int numeroColonne = 0; numeroColonne < referenceRangement.length(); numeroColonne++){
			
			if ( !referenceRangement.getColonneAt(numeroColonne).isEmpty() ){
				dessinerCarte(crayon, referenceRangement.getLastAt(numeroColonne), coordX, coordY);
			}
			else {
				dessinerCarteVide(crayon, coordX, coordY);
			}
			coordX += (largeurCarte + ecartXsurCartes);
		}
		if ( rangementSurlignee != -1 ){
			coordX = decalageInitialX + referenceRangement.length()*(largeurCarte + ecartXsurCartes) + largeurCarte/2 + rangementSurlignee*(largeurCarte + ecartXsurCartes);
			dessinerSurlignage(crayon, coordX, coordY);
		}
		if ( rangementSelectionnee != -1 ){
			coordX = decalageInitialX + referenceRangement.length()*(largeurCarte + ecartXsurCartes) + largeurCarte/2 + rangementSelectionnee*(largeurCarte + ecartXsurCartes);
			dessinerSelection(crayon, coordX, coordY);
		}
	}
	private void dessinerZonePrincipale (Graphics2D crayon, Plateau referencePrincipale){
		for (int numeroColonne = 0; numeroColonne < referencePrincipale.length(); numeroColonne++){
			coordX = decalageInitialX + ecartXPlateau + numeroColonne*(largeurCarte + ecartXsurCartes);
			coordY = decalageInitialY + hauteurCarte + ecartYPlateau;
			
			if ( referencePrincipale.getColonneAt(numeroColonne).isEmpty() ){
				dessinerCarteVide(crayon, coordX, coordY);
			}
			else {
				ListIterator<Carte> iterateurColonne = referencePrincipale.getColonneAt(numeroColonne).listIterator();
				
				while ( iterateurColonne.hasNext() ){
					if ( !referencePrincipale.getColonneAt(numeroColonne).isEmpty() ){
						dessinerCarte(crayon, iterateurColonne.next(), coordX, coordY);
					}
					else {
						dessinerCarteVide(crayon, coordX, coordY);
					}
					coordY += ecartYsurCartes;
				}
			}
		}
		if ( colonneSurlignee != -1 ){
			int compteurCartes = referencePrincipale.getColonneAt(colonneSurlignee).isEmpty() ? 0 : referencePrincipale.getColonneAt(colonneSurlignee).size()-1;
			
			coordX = decalageInitialX + ecartXPlateau + colonneSurlignee*(largeurCarte + ecartXsurCartes);
			coordY = decalageInitialY + hauteurCarte + ecartYPlateau + compteurCartes * ecartYsurCartes;
			dessinerSurlignage(crayon, coordX, coordY);
		}
		if ( colonneSelectionnee != -1 ){
			
			int compteurCartes = referencePrincipale.getColonneAt(colonneSelectionnee).isEmpty() ? 0 : referencePrincipale.getColonneAt(colonneSelectionnee).size()-1;
			
			coordX = decalageInitialX + ecartXPlateau + colonneSelectionnee*(largeurCarte + ecartXsurCartes);
			coordY = decalageInitialY + hauteurCarte + ecartYPlateau + compteurCartes * ecartYsurCartes;
			dessinerSelection(crayon, coordX, coordY);
		}
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
		crayon.drawRect(coordX-1, coordY-1, largeurCarte+1, hauteurCarte+1);
	}
	private void dessinerSelection (Graphics2D crayon, int coordX, int coordY){
		crayon.setColor(Color.black);
		crayon.drawRect(coordX-1, coordY-1, largeurCarte+1, hauteurCarte+1);
	}

}
