package mainPackage;

import graphiquePackage.Fenetre;

import javax.swing.SwingUtilities;

import objectPackage.PackCard;
import objectPackage.Plateau;
import objectPackage.Stock;


public class MainClass implements Runnable{

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new MainClass());
	}
	
	public void run (){
		Stock stockage = new Stock();
		Plateau principal = new Plateau(8);
		Plateau rangement = new Plateau(4);
		PackCard paquet = new PackCard();
		paquet.initialisation();
		
		principal.initialisation(0, paquet);
//		rangement.initialisation(0, paquet);
		
		System.out.println(principal.toString());
		
		Fenetre fen = new Fenetre("Titre", stockage, principal, rangement);
		fen.disposition();

	}

}
