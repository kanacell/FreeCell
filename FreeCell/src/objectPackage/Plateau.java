package objectPackage;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;

import constantesPackage.Constantes;

public class Plateau {
	private Colonne[] plateau;
	private Random generateur;
	private int numeroColonneCourante;
	private Carte carteCourante;
	
	/*
	 * 1 CONSTRUCTEUR
	 */
	public Plateau (int nombreColonnes){
		plateau = new Colonne[nombreColonnes];
		instanciation();
	}
	/*
	 * FIN CONSTRUCTEUR
	 */
	
	/*
	 * 2 ACCESSEURS
	 */
	public int getNumeroColonneCourante (){
		return numeroColonneCourante;
	}
	public Carte getCarteCourante (){
		return carteCourante;
	}
	
	public void setNumeroColonneCourante (int newColonneCourante){
		numeroColonneCourante = newColonneCourante;
	}
	public void setCarteCourante (Carte newCarteCourante){
		carteCourante = newCarteCourante.clone();
	}
	/*
	 * FIN ACCESSEURS
	 */
	
	/*
	 * Methodes Public de l'objet
	 */
	public void initialisation (int seed, PackCard paquet){
		generateur = new Random(seed);
		PackCard packTampon = paquet.clone();
		int numeroColonne = 0;
		while ( !packTampon.isEmpty() ){
			int indice = generateur.nextInt(packTampon.size());
			plateau[numeroColonne].add(packTampon.get(indice));
			packTampon.remove(indice);
			numeroColonne = (numeroColonne+1) % plateau.length;
		}
		
	}
	
	public int length (){
		return plateau.length;
	}
	
	public Colonne getColonneAt (int indice){
		return plateau[indice];
	}
	
	public Carte getLastAt (int indice){
		return plateau[indice].get(plateau[indice].size()-1);
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
	 * Methodes "Classiques" de l'objet
	 */
	public Plateau clone (){
		Plateau renvoi = new Plateau(this.plateau.length);
		for (int i = 0; i < renvoi.plateau.length; i++){
			renvoi.plateau[i] = this.plateau[i].clone();
		}
		return renvoi;
	}
	
	public String toString (){
		String chaine_resultat = "Plateau : \n";
		for (int i = 0; i < plateau.length; i++){
			chaine_resultat += plateau[i].toString();
		}
		return chaine_resultat;
	}
	
	/*
	 * Classe Interne au plateau
	 */
	public class Colonne extends ArrayList<Carte> {
		
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
			chaine_resultat += "}\n";
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
