package objectPackage;

import constantesPackage.Constantes;

public class Stock {
	private Carte[] stock;
	
	public Stock (){
		stock = new Carte[Constantes.Plateau.nombreCellules];
		instanciation();
	}
	
	/*
	 * Methodes Public de Stock
	 */
	public int length (){
		return stock.length;
	}
	public Carte getCarteAt (int indice){
		return stock[indice];
	}
	public boolean isEmpty (int indice){
		return stock[indice].isEmpty();
	}
	public int nombreCasesVides (){
		int compteurCartesVides = 0;
		for (int indiceCarte = 0; indiceCarte < stock.length; indiceCarte++){
			if ( stock[indiceCarte].isEmpty() ){
				compteurCartesVides++;
			}
		}
		return compteurCartesVides;
	}
	public void putCarteAt (int indice, Carte otherCarte){
		stock[indice] = otherCarte.clone();
	}
	public void test (){
		for (int numeroCarte = 0; numeroCarte < stock.length; numeroCarte++){
			stock[numeroCarte] = new Carte(numeroCarte+1, Constantes.Carte.Famille.car, Constantes.Carte.Couleur.rouge);
		}
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
	public String toString(){
		String chaine_resultat = "Stock : ";
		for (int numeroCarte = 0; numeroCarte < stock.length; numeroCarte++){
			chaine_resultat += "| " + stock[numeroCarte].toString() + " ";
		}
		chaine_resultat += "|";
		return chaine_resultat;
	}
}
