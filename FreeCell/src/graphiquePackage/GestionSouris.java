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
	 * 1 CONSTRUCTEUR
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
			System.out.println("pressed sur panneau de stockage");
			Stock referenceStockage = engi.getZoneStockage();
			
			int numeroCarte = estSurStock(clic);
			if (numeroCarte != -1){
//				if (pan.getCarteSelectionnee() == -1){
					pan.selectionnerCarte(numeroCarte);
//				}
				System.out.println("carte : " + referenceStockage.getCarteAt(numeroCarte).toString() );
				engi.setCarteCourante(referenceStockage.getCarteAt(numeroCarte).clone());
				engi.setTypePanneauDepart(Constantes.Panneau.zoneDeStockage);
			}
			else {
				if (pan.getCarteSelectionnee() != -1){
					pan.deselectionnerCarte();
					engi.getCarteCourante().clear();
				}
			}
			
			
			
			
			
			if ( engi.getTypePanneauDepart() == -1 ){
				engi.setTypePanneauDepart(Constantes.Panneau.zoneDeStockage);
			}
			else {
				engi.setTypePanneauDepart(Constantes.Panneau.zoneNulle);
			}
			
//			System.out.println(engi.getZoneStockage().toString());
			break;
		case Constantes.Panneau.zoneDeJeu:
			System.out.println("pressed sur panneau de zone principale");
			Plateau referencePrincipale = engi.getZonePrincipale();
			
			if ( engi.getTypePanneauDepart() != Constantes.Panneau.zoneDeJeu ){
				engi.setTypePanneauDepart(Constantes.Panneau.zoneDeJeu);
			}
			else {
				engi.setTypePanneauDepart(Constantes.Panneau.zoneNulle);
			}
			
			System.out.println(engi.getZonePrincipale().toString());
			break;
		case Constantes.Panneau.zoneDeRangement:
			System.out.println("pressed sur panneau de rangement");
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
	
	private int estSurStock (Point clic){
		int numeroCarte = -1;
		int borneGauche = 15;
		int borneDroite = borneGauche + Constantes.Panneau.largeurCarte;
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

}
