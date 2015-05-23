package objectPackage;

import java.util.ArrayList;
import java.util.ListIterator;

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
	
	public Plateau clone (){
		Plateau renvoi = new Plateau(this.plateau.length);
		for (int i = 0; i < renvoi.plateau.length; i++){
			renvoi.plateau[i] = this.plateau[i].clone();
		}
		return renvoi;
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
		
		public String toString (){
			String chaine_resultat = "Colonne : { ";
			ListIterator<Carte> iterateurColonne = this.listIterator();
			while ( iterateurColonne.hasNext() ){
				chaine_resultat += iterateurColonne.next().toString() + "; ";
			}
			return chaine_resultat;
		}
		
		public boolean equals (Colonne otherColonne){
			boolean identique;
			identique = ( this.size() == otherColonne.size() );
			int i = 0;
			while ( i < this.size() && identique ){
				identique = identique && this.get(i).equals(otherColonne.get(i));
			}
			return identique;
		}
		
		public Colonne clone (){
			Colonne renvoi = new Colonne();
			renvoi.addAll(this);
			return renvoi;
		}
	}
	
}
