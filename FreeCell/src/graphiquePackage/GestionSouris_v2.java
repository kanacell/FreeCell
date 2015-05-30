package graphiquePackage;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import movePackage.Engine;

public class GestionSouris_v2 implements MouseListener, MouseMotionListener {

	private Panneau_v2 panneauDeJeu;
	private Engine engi;
	
/*
 * Constructeur de GestionSouris_v2
 */
	public GestionSouris_v2 (Panneau_v2 referencePanneau, Engine referenceEngi){
		panneauDeJeu = referencePanneau;
		engi = referenceEngi;
	}
/*
 * FIN Constructeur de GestionSouris_v2
 */
	
	public void mousePressed(MouseEvent e) {
		int coordX = e.getPoint().x;
		int coordY = e.getPoint().y;
		
		if ( clicSurStock(coordX,coordY) ){
			System.out.println("clic sur la zone de stockage");
			int numeroCarte = carteDuStockSelectionnee(coordX);
		}
		else if ( clicSurRangement(coordX, coordY) ){
			System.out.println("clic sur la zone de rangement");
			
		}
		else if ( clicSurPlateau(coordX, coordY) ){
			System.out.println("clic sur la zone du plateau");
			
		}
		else {
			System.out.println("clic hors zone");
		}
	}
	
	public void mouseMoved(MouseEvent e) {
		
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}

	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}

	
	/*
	 * Methodes Privates de GestionSouris_v2
	 */
	private boolean clicSurStock (int coordX, int coordY) {
		System.out.println("coord : " + coordX + " " + coordY);
		boolean clicValide = false;
		int borneGauche = panneauDeJeu.getDecalageInitialX();
		int borneHaute = panneauDeJeu.getDecalageInitialY();
		int numeroCarte = 0;
		
		while (numeroCarte < engi.getZoneStockage().length() && !clicValide){
			System.out.println("bornes gauche/droite : " + borneGauche + " " + (borneGauche+panneauDeJeu.getLargeurCarte()) );
			System.out.println("bornes haute/basse : " + borneHaute + " " + (borneHaute+panneauDeJeu.getHauteurCarte()) );
			System.out.println();
			
			if ( coordX >= borneGauche && coordX <= borneGauche + panneauDeJeu.getLargeurCarte()
			&&	 coordY >= borneHaute && coordY <= borneHaute + panneauDeJeu.getHauteurCarte() ){
				clicValide = true;
			}
			else {
				borneGauche += panneauDeJeu.getLargeurCarte() + panneauDeJeu.getEcartXsurCartes();
				numeroCarte++;
			}
		}
		
		return clicValide;
	}
	private boolean clicSurRangement (int coordX, int coordY){
		return false;
	}
	private boolean clicSurPlateau (int coordX, int coordY){
		return false;
	}
	
	private int carteDuStockSelectionnee (int coordX){
		
		
		return -1;
	}
}
