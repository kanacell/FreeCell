package movePackage;

import objectPackage.Carte;
import objectPackage.Plateau;
import objectPackage.Stock;

public class Engine {
	private Plateau zonePrincipale;
	private Plateau zoneRangement;
	private Stock zoneStockage;
	private int numeroColonneCourante;
	private Carte carteCourante;
	private int typePanneauDepart;
	
	/*
	 * CONSTRUCTEURS
	 */
	public Engine (Stock referenceStockage, Plateau referencePlateau, Plateau referenceRangement){
		zonePrincipale = referencePlateau;
		zoneRangement = referenceRangement;
		zoneStockage = referenceStockage;
		numeroColonneCourante = -1;
		typePanneauDepart = -1;
		carteCourante = new Carte();
	}
	
	/*
	 * FIN CONSTRUCTEURS
	 */
	
	/*
	 * ACCESSEURS
	 */
	public Plateau getZonePrincipale (){
		return zonePrincipale;
	}
	public Plateau getZoneRangement (){
		return zoneRangement;
	}
	public Stock getZoneStockage (){
		return zoneStockage;
	}
	public int getNumeroColonneCourante (){
		return numeroColonneCourante;
	}
	public Carte getCarteCourante (){
		return carteCourante;
	}
	public int getTypePanneauDepart (){
		return typePanneauDepart;
	}
	
	public void setZonePrincipale (Plateau referencePrincipale){
		zonePrincipale = referencePrincipale;
	}
	public void setZoneRangement (Plateau referenceRangement){
		zoneRangement = referenceRangement;
	}
	public void setZoneStockage (Stock referenceStockage){
		zoneStockage = referenceStockage;
	}
	public void setNumeroColonneCourante (int newColonneCourante){
		numeroColonneCourante = newColonneCourante;
	}
	public void setCarteCourante (Carte referenceCarteCourante){
		carteCourante = referenceCarteCourante;
	}
	public void setTypePanneauDepart (int newTypePanneauDepart){
		typePanneauDepart = newTypePanneauDepart;
	}
	/*
	 * FIN ACCESSEURS
	 */
	
	/*
	 * Methodes Public de Engine
	 */
	public boolean deplacement (){
		boolean deplacement = false;
		
		return deplacement;
	}
	
	
}
