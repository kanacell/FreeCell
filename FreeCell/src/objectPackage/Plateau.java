package objectPackage;

import java.util.ArrayList;

public class Plateau {
	private Colonne[] plateau;
	
	public Plateau (int nombreColonnes){
		plateau = new Colonne[nombreColonnes];
		instanciation();
	}
	
	/*
	 * Methodes Privates de Plateau
	 */
	private void instanciation (){
		for (int i = 0; i < plateau.length; i++){
			plateau[i] = new Colonne();
		}
	}
	
	/*
	 * Classe Interne privee au plateau
	 */
	private class Colonne extends ArrayList<Carte> {
		
		/*
		 * 1 Constructeur
		 */
		public Colonne (){
			super();
		}
	}
	
}
