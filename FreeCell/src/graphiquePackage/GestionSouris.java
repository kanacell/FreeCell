package graphiquePackage;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import constantesPackage.Constantes;
import objectPackage.Plateau;
import objectPackage.Stock;

public class GestionSouris implements MouseListener, MouseMotionListener{
	private Stock panneauStockage = null;
	private Plateau panneauZonePrincipale = null;
	private Plateau panneauRangement = null;
	
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
	/*
	 * FIN CONSTRUCTEURS
	 */

	public void mouseDragged(MouseEvent e) {
		
	}

	public void mouseMoved(MouseEvent e) {
		
	}

	public void mouseClicked(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		if (panneauStockage != null){
			System.out.println("pressed sur panneau de stockage");
		}
		else if (panneauZonePrincipale != null){
			System.out.println("pressed sur panneau de zone principale");
		}
		else if (panneauRangement != null){
			System.out.println("pressed sur panneau de rangement");
		}
	}

	public void mouseReleased(MouseEvent e) {
		
	}

}
