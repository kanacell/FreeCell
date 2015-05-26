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
			System.out.println(panneauStockage.toString());
		}
		else if (panneauZonePrincipale != null){
			System.out.println("pressed sur panneau de zone principale");
			System.out.println(panneauZonePrincipale.toString());
		}
		else if (panneauRangement != null){
			System.out.println("pressed sur panneau de rangement");
			System.out.println(panneauRangement.toString());
		}
	}

	public void mouseReleased(MouseEvent e) {
		
	}

}
