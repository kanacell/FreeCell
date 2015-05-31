package graphiquePackage;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import movePackage.Engine;
import objectPackage.Carte;

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
		int numeroCarte, numeroColonne;
		
		if ( (numeroCarte = clicSurStock(coordX,coordY)) != -1 ){
			System.out.println("\tclic sur la zone de stockage");
			panneauDeJeu.selectionnerStock(numeroCarte);
			if ( false ){
				
			}
			engi.setCarteCourante( engi.getZoneStockage().getCarteAt(numeroCarte) );
			System.out.println("carte courante : " + engi.getCarteCourante());
			
		}
		else if ( (numeroCarte = clicSurRangement(coordX, coordY)) != -1 ){
			System.out.println("\tclic sur la zone de rangement");
			panneauDeJeu.selectionnerRangement(numeroCarte);
			engi.rangement();
		}
		else if ( (numeroColonne = clicSurPlateau(coordX, coordY)) != -1 ){
			System.out.println("\tclic sur la zone du plateau");
			panneauDeJeu.selectionnerColonne(numeroColonne);
			
			if ( engi.getNumeroColonneCourante() == -1 && engi.getCarteCourante().isEmpty() ){
				System.out.println("carte courante et colonne courante vides");
				engi.setNumeroColonneCourante(numeroColonne);
			}
			else if ( !engi.getCarteCourante().isEmpty() ){
				System.out.println("carte courante non vide");
				
//				engi.ajouterCarte(engi.getCarteCourante(), numeroColonne);
				
				engi.deplacementExterne(numeroColonne);
/*				
ou bien			engi.deplacement(engi.getCarteCourante(), numeroColonne);
*/
				panneauDeJeu.deselectionner();
			}
			else if ( engi.getNumeroColonneCourante() != -1 ){
				System.out.println("colonne courante non vide");
				
				engi.deplacementInterne(numeroColonne);
				
/*
ou bien			engi.deplacement(engi.getNumeroColonneCourante(), numeroColonne);
*/
				panneauDeJeu.deselectionner();
			}
		}
		else {
			System.out.println("clic hors zone");
			panneauDeJeu.deselectionner();
			engi.setCarteCourante(new Carte());;
			engi.setNumeroColonneCourante(-1);
		}
	}
	
	public void mouseMoved(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}

	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}

	
	/*
	 * Methodes Privates de GestionSouris_v2
	 */
	private int clicSurStock (int coordX, int coordY) {
		int carteSelectionnee = -1;
		int borneGauche = panneauDeJeu.getDecalageInitialX();
		int borneHaute = panneauDeJeu.getDecalageInitialY();
		int numeroCarte = 0;
		
		while ( numeroCarte < engi.getZoneStockage().length() && carteSelectionnee == -1 ){
			if ( coordX >= borneGauche && coordX <= borneGauche + panneauDeJeu.getLargeurCarte()
			&&	 coordY >= borneHaute && coordY <= borneHaute + panneauDeJeu.getHauteurCarte() ){
				carteSelectionnee = numeroCarte;
			}
			else {
				borneGauche += panneauDeJeu.getLargeurCarte() + panneauDeJeu.getEcartXsurCartes();
				numeroCarte++;
			}
		}
		
		return carteSelectionnee;
	}
	private int clicSurRangement (int coordX, int coordY){
		int carteSelectionnee = -1;
		int borneGauche = panneauDeJeu.getDecalageInitialX() + engi.getZoneStockage().length()*(panneauDeJeu.getLargeurCarte() + panneauDeJeu.getEcartXsurCartes()) + panneauDeJeu.getLargeurCarte()/2 ;
		int borneHaute = panneauDeJeu.getDecalageInitialY();
		int numeroCarte = 0;
		
		while ( numeroCarte < engi.getZoneRangement().length() && carteSelectionnee == -1){
			if ( coordX >= borneGauche && coordX <= borneGauche + panneauDeJeu.getLargeurCarte()
			&&	 coordY >= borneHaute && coordY <= borneHaute + panneauDeJeu.getHauteurCarte() ){
				carteSelectionnee = numeroCarte;
			}
			else {
				borneGauche += panneauDeJeu.getLargeurCarte() + panneauDeJeu.getEcartXsurCartes();
				numeroCarte++;
			}
		}
		return carteSelectionnee;
	}
	private int clicSurPlateau (int coordX, int coordY){
		int colonneSelectionnee = -1;
		int borneGauche = panneauDeJeu.getDecalageInitialX() + panneauDeJeu.getEcartXPlateau();
		int borneHaute = panneauDeJeu.getDecalageInitialY() + panneauDeJeu.getHauteurCarte() + panneauDeJeu.getEcartYPlateau();
		int numeroColonne = 0;
		
		while ( numeroColonne < engi.getZonePrincipale().length() && colonneSelectionnee == -1 ){
			int compteurCartes = engi.getZonePrincipale().getColonneAt(numeroColonne).isEmpty() ? 0 : engi.getZonePrincipale().getColonneAt(numeroColonne).size()-1;
			int borneBasse = borneHaute + compteurCartes * panneauDeJeu.getEcartYsurCartes() + panneauDeJeu.getHauteurCarte();
			
			if ( coordX >= borneGauche && coordX <= borneGauche + panneauDeJeu.getLargeurCarte()
			&&	 coordY >= borneHaute && coordY <= borneBasse ){
				colonneSelectionnee = numeroColonne;
			}
			else {
				borneGauche += panneauDeJeu.getLargeurCarte() + panneauDeJeu.getEcartXsurCartes();
				numeroColonne++;
			}
		}
		return colonneSelectionnee;
	}
	
}
