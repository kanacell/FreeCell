package objectPackage;

import constantesPackage.Constantes;

public class Stock {
	private Carte[] stock;
	
	public Stock (){
		stock = new Carte[Constantes.Plateau.nombreCellules];
		instanciation();
	}
	
	/*
	 * Methodes Privates de Stock
	 */
	private void instanciation (){
		for (int i = 0; i < stock.length; i++){
			stock[i] = new Carte();
		}
	}
}
