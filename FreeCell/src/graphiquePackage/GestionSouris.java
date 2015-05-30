package graphiquePackage;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import movePackage.Engine;
import objectPackage.Plateau;
import objectPackage.Stock;
import constantesPackage.Constantes;

public class GestionSouris implements MouseListener, MouseMotionListener{
	private int typeDeZone;
	private Panneau pan;
	private Engine engi;
	
	/*
	 * 2 CONSTRUCTEUR
	 */
	public GestionSouris (Engine referenceEngi){
		engi = referenceEngi;
	}
	public GestionSouris (Engine referenceEngi, int newTypeDeZone, Panneau referencePan){
		engi = referenceEngi;
		typeDeZone = newTypeDeZone;
		pan = referencePan;
	}
	/*
	 * FIN CONSTRUCTEUR
	 */

	

	public void mousePressed(MouseEvent e) {
		System.out.println("depart avant : " + engi.getTypePanneauDepart());
		Point clic = e.getPoint();
		switch (typeDeZone){
		case Constantes.Panneau.zoneDeStockage:
			System.out.println("\tpressed sur panneau de stockage");
			Stock referenceStockage = engi.getZoneStockage();
			int numeroCarte = estSurStock(clic);
			
			if (numeroCarte != -1){
				pan.selectionnerCarte(numeroCarte);
				System.out.println("carte : " + referenceStockage.getCarteAt(numeroCarte).toString() );
				engi.setCarteCourante(referenceStockage.getCarteAt(numeroCarte).clone());
				engi.setTypePanneauDepart(Constantes.Panneau.zoneDeStockage);
			}
			else {
				if (pan.getCarteSelectionnee() != -1){
					pan.deselectionnerCarte();
					engi.getCarteCourante().clear();
					engi.setTypePanneauDepart(Constantes.Panneau.zoneNulle);
				}
			}
			
//			System.out.println(engi.getZoneStockage().toString());
			break;
		case Constantes.Panneau.zoneDeJeu:
			System.out.println("\tpressed sur panneau de zone principale");
			Plateau referencePrincipale = engi.getZonePrincipale();
			int numeroColonne = estSurPrincipale(clic);
			
			if (numeroColonne != -1){
				pan.selectionnerColonne(numeroColonne);
				engi.setNumeroColonneCourante(numeroColonne);
				engi.setTypePanneauDepart(Constantes.Panneau.zoneDeJeu);
			}
			else {
				if (pan.getColonneSelectionnee() != -1){
					pan.deselectionnerColonne();
					engi.setNumeroColonneCourante(-1);
					engi.setTypePanneauDepart(Constantes.Panneau.zoneNulle);
				}
			}
			
//			System.out.println(engi.getZonePrincipale().toString());
			break;
		case Constantes.Panneau.zoneDeRangement:
			System.out.println("\tpressed sur panneau de rangement");
			Plateau referenceRangement = engi.getZoneRangement();
			
			if ( engi.getTypePanneauDepart() != Constantes.Panneau.zoneDeRangement ){
				engi.setTypePanneauDepart(Constantes.Panneau.zoneDeRangement);
			}
			else {
				engi.setTypePanneauDepart(Constantes.Panneau.zoneNulle);
			}
			
			System.out.println(engi.getZoneRangement().toString());
			break;
		}
		System.out.println("depart apres : " + engi.getTypePanneauDepart());
	}
	

	public void mouseMoved(MouseEvent e) {
		Point clic = e.getPoint();
		switch (typeDeZone){
		case Constantes.Panneau.zoneDeStockage:
			int numeroCarte = estSurStock(clic);
			if (numeroCarte != -1){
				if (pan.getCarteSurlignee() == -1){
					pan.activerContours(numeroCarte);
				}
			}
			else {
				if (pan.getCarteSurlignee() != -1){
					pan.desactiverContours();
				}
			}
			break;
		case Constantes.Panneau.zoneDeJeu:
			int numeroColonne = estSurPrincipale(clic);
			if (numeroColonne != -1){
//				if (){
					
//				}
			}
			break;
		case Constantes.Panneau.zoneDeRangement:
			
			break;
		}
	}
	
	public void mouseReleased(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
	/*
	 * Methodes Private de GestionSouris
	 */
	private int estSurStock (Point clic){
		int numeroCarte = -1;
		int borneGauche = 20,  borneDroite = borneGauche + Constantes.Panneau.largeurCarte;
		int borneSuperieure = 20;
		int borneInferieure = borneSuperieure + Constantes.Panneau.hauteurCarte;
		if (clic.x >= borneGauche && clic.x <= borneDroite 
		&&	clic.y >= borneSuperieure && clic.y <= borneInferieure){
			numeroCarte = 0;
		}
		else if (clic.x >= (borneGauche = borneDroite + 10) && clic.x <= (borneDroite = borneGauche + Constantes.Panneau.largeurCarte) 
			&&	 clic.y >= borneSuperieure && clic.y <= borneInferieure ){
			numeroCarte = 1;
		}
		else if (clic.x >= (borneGauche = borneDroite + 10) && clic.x <= (borneDroite = borneGauche + Constantes.Panneau.largeurCarte)
			&&	 clic.y >= borneSuperieure && clic.y <= borneInferieure ){
			numeroCarte = 2;
		}
		else if (clic.x >= (borneGauche = borneDroite + 10) && clic.x <= (borneDroite = borneGauche + Constantes.Panneau.largeurCarte)
			&&	 clic.y >= borneSuperieure && clic.y <= borneInferieure ){
			numeroCarte = 3;
		}
		
		return numeroCarte;
	}

	private int estSurPrincipale (Point clic){
		int numeroColonne = 0;
		int borneGauche = 15, borneDroite = borneGauche + Constantes.Panneau.largeurCarte;
		if (clic.x >= borneGauche && clic.x <= borneDroite 
		&&	estSurZoneCliquable(clic, engi.getZonePrincipale(), numeroColonne++) ){
			numeroColonne = 0;
		}
		else if (clic.x >= (borneGauche = borneDroite + 10) && clic.x <= (borneDroite = borneGauche + Constantes.Panneau.largeurCarte)
			&&	 estSurZoneCliquable(clic, engi.getZonePrincipale(), numeroColonne++) ){
			numeroColonne = 1;
		}
		else if (clic.x >= (borneGauche = borneDroite + 10) && clic.x <= (borneDroite = borneGauche + Constantes.Panneau.largeurCarte)
			&&	 estSurZoneCliquable(clic, engi.getZonePrincipale(), numeroColonne++) ){
			numeroColonne = 2;
		}
		else if (clic.x >= (borneGauche = borneDroite + 10) && clic.x <= (borneDroite = borneGauche + Constantes.Panneau.largeurCarte)
			&&	 estSurZoneCliquable(clic, engi.getZonePrincipale(), numeroColonne++) ){
			numeroColonne = 3;
		}
		else if (clic.x >= (borneGauche = borneDroite + 10) && clic.x <= (borneDroite = borneGauche + Constantes.Panneau.largeurCarte)
			&&	 estSurZoneCliquable(clic, engi.getZonePrincipale(), numeroColonne++) ){
			numeroColonne = 4;
		}
		else if (clic.x >= (borneGauche = borneDroite + 10) && clic.x <= (borneDroite = borneGauche + Constantes.Panneau.largeurCarte)
			&&	 estSurZoneCliquable(clic, engi.getZonePrincipale(), numeroColonne++) ){
			numeroColonne = 5;
		}
		else if (clic.x >= (borneGauche = borneDroite + 10) && clic.x <= (borneDroite = borneGauche + Constantes.Panneau.largeurCarte)
			&&	 estSurZoneCliquable(clic, engi.getZonePrincipale(), numeroColonne++) ){
			numeroColonne = 6;
		}
		else if (clic.x >= (borneGauche = borneDroite + 10) && clic.x <= (borneDroite = borneGauche + Constantes.Panneau.largeurCarte)
			&&	 estSurZoneCliquable(clic, engi.getZonePrincipale(), numeroColonne) ){
			numeroColonne = 7;
		}
		else {
			numeroColonne = -1;
		}
		
		return numeroColonne;
	}
	
	private boolean estSurZoneCliquable (Point clic, Plateau zonePrincipale, int numeroColonne){
		System.out.println("numeroColonne : " + numeroColonne);
		boolean clicValide;
		int compteurCarte = ( zonePrincipale.getColonneAt(numeroColonne).size() > 0 ) ? zonePrincipale.getColonneAt(numeroColonne).size() : 1;
		int borneSuperieure = 20;
		int borneInferieure = borneSuperieure + (compteurCarte-1)*20 + Constantes.Panneau.hauteurCarte;
		
		clicValide = clic.y >= borneSuperieure && clic.y <= borneInferieure; 
		
		return clicValide;
	}
}
