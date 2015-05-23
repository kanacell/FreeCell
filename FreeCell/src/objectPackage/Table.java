package objectPackage;

import constantesPackage.Constantes;

public class Table {
	/*
	 * 3 ATTRIBUTS
	 */
	private Stock zoneDeStockage;
	private Plateau zoneDeJeu;
	private Plateau zoneDeRangement;
	
	/*
	 * 1 CONSTRUCTEUR
	 */
	public Table (){
		zoneDeStockage = new Stock ();
		zoneDeJeu = new Plateau (Constantes.Plateau.nombreColonnes);
		zoneDeRangement = new Plateau (Constantes.Plateau.nombreFamilles);
	}
	/*
	 * FIN CONSTRUCTEUR
	 */
	
	/*
	 * 3 ACCESSEURS
	 */
	public Stock getStock (){
		return zoneDeStockage;
	}
	public Plateau getZoneDeJeu (){
		return zoneDeJeu;
	}
	public Plateau getZoneDeRangement (){
		return zoneDeRangement;
	}
	
	public void setStock (Stock newStock){
		zoneDeStockage = newStock.clone();
	}
	public void setZoneDeJeu (Plateau newZoneDeJeu){
		zoneDeJeu = newZoneDeJeu.clone();
	}
	public void setZoneDeRangement (Plateau newZoneDeRangement){
		zoneDeRangement = newZoneDeRangement.clone();
	}
	
	/*
	 * Methodes Public de Table
	 */
	
}
