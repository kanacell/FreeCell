package graphiquePackage;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import movePackage.Engine;
import objectPackage.Plateau;
import objectPackage.Stock;
import constantesPackage.Constantes;

public class GestionSouris implements MouseListener, MouseMotionListener{
	private Stock panneauStockage = null;
	private Plateau panneauZonePrincipale = null;
	private Plateau panneauRangement = null;
	
	private Engine engi;
	
	/*
	 * 2 CONSTRUCTEURS
	 */
	public GestionSouris (Stock referenceStock){
		panneauStockage = referenceStock;
	}
	public GestionSouris (Plateau referencePlateau, int typeDeZone){
		if (typeDeZone == Constantes.Panneau.zoneDeJeu){
			panneauZonePrincipale = referencePlateau;
		}
		else if (typeDeZone == Constantes.Panneau.zoneDeRangement){
			panneauRangement = referencePlateau;
		}
	}
	public GestionSouris (Engine referenceEngi){
		engi = referenceEngi;
	}
	/*
	 * FIN CONSTRUCTEURS
	 */

	

	public void mousePressed(MouseEvent e) {
		System.out.println("depart avant : " + engi.getTypePanneauDepart());
		if (panneauStockage != null){
			System.out.println("pressed sur panneau de stockage");
			
			if ( engi.getTypePanneauDepart() == Constantes.Panneau.zoneNulle ){
				engi.setTypePanneauDepart(Constantes.Panneau.zoneDeStockage);
			}
			else {
				engi.setTypePanneauDepart(Constantes.Panneau.zoneNulle);
			}
			
			System.out.println(panneauStockage.toString());
		}
		else if (panneauZonePrincipale != null){
			System.out.println("pressed sur panneau de zone principale");
			
			if ( engi.getTypePanneauDepart() == Constantes.Panneau.zoneNulle ){
				engi.setTypePanneauDepart(Constantes.Panneau.zoneDeJeu);
			}
			else {
				engi.setTypePanneauDepart(Constantes.Panneau.zoneNulle);
			}
			
			System.out.println(panneauZonePrincipale.toString());
		}
		else if (panneauRangement != null){
			System.out.println("pressed sur panneau de rangement");
			
			if ( engi.getTypePanneauDepart() == Constantes.Panneau.zoneNulle ){
				engi.setTypePanneauDepart(Constantes.Panneau.zoneDeRangement);
			}
			else {
				engi.setTypePanneauDepart(Constantes.Panneau.zoneNulle);
			}
			
			System.out.println(panneauRangement.toString());
		}
		System.out.println("depart apres : " + engi.getTypePanneauDepart());
	}

	public void mouseReleased(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

}
