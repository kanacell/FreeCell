package mainPackage;

import graphiquePackage.Fenetre;

import javax.swing.SwingUtilities;

import objectPackage.Plateau;
import objectPackage.Stock;


public class MainClass implements Runnable{

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new MainClass());
	}
	
	public void run (){
		Fenetre fen = new Fenetre("Titre", new Stock(), new Plateau(8), new Plateau(4));
		fen.disposition();
	}

}
