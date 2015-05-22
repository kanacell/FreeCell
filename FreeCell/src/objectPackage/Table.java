package objectPackage;

import constantesPackage.Constantes;

public class Table {
	private Stock zoneDeStockage;
	private Plateau zoneDeJeu;
	private Plateau zoneDeRangement;
	
	public Table (){
		zoneDeStockage = new Stock ();
		zoneDeJeu = new Plateau (Constantes.Plateau.nombreColonnes);
		zoneDeRangement = new Plateau (Constantes.Plateau.nombreFamilles);
	}
}
