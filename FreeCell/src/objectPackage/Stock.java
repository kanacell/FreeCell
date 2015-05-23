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
	
	/*
	 * Methodes "Classiques" de l'objet
	 */
	public Stock clone(){
		Stock renvoi = new Stock();
		for (int i = 0; i < renvoi.stock.length; i++){
			renvoi.stock[i] = this.stock[i].clone();
		}
		return renvoi;
	}
}
