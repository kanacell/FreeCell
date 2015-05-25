package movePackage;

import objectPackage.Plateau;
import objectPackage.Stock;

public class Engine {
	Plateau zonePrincipal;
	Plateau zoneRangement;
	Stock zoneStockage;
	
	public Engine (Stock referenceStockage, Plateau referencePlateau, Plateau referenceRangement){
		zonePrincipal = referencePlateau;
		zoneRangement = referenceRangement;
		zoneStockage = referenceStockage;
	}
	
	public boolean deplacementInterne (int coordX, int coordY){
		boolean deplacement = false;
		
		return deplacement;
	}
	
	
}
