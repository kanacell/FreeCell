package mainPackage;

import graphiquePackage.Fenetre;

import javax.swing.SwingUtilities;

import constantesPackage.Constantes;
import movePackage.Engine;
import objectPackage.PackCard;
import objectPackage.Plateau;
import objectPackage.Stock;


public class MainClass implements Runnable{

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new MainClass());
	}
	
	public void run (){
		Stock stockage = new Stock();
		Plateau principal = new Plateau(Constantes.Plateau.nombreColonnes);
		Plateau rangement = new Plateau(Constantes.Plateau.nombreFamilles);
		PackCard paquet = new PackCard();
		
		Engine engi = new Engine (stockage, principal, rangement, paquet);
		Fenetre fen = new Fenetre("FreeCell");
		fen.disposition_v2(engi);
		
	}

}
