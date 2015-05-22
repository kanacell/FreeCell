package objectPackage;

import constantesPackage.Constantes;

public class Carte {
	/*
	 * 3 ATTRIBUTS
	 */
	private int valeur;
	private String famille;
	private String couleur;
	
	/*
	 * 1 CONSTRUCTEUR
	 */
	public Carte (){
		valeur = 0;
		famille = Constantes.Carte.Famille.nul;
		couleur = Constantes.Carte.Couleur.blanche;
	}
	/*
	 * FIN CONSTRUCTEURS
	 */
	
	/*
	 * 3 ACCESSEURS
	 */
	public int getValeur (){
		return valeur;
	}
	public String getFamille (){
		return famille;
	}
	public String getCouleur (){
		return couleur;
	}
	
	public void setValeur (int newValeur){
		valeur = newValeur;
	}
	public void setFamille (String newFamille){
		famille = newFamille;
	}
	public void setCouleur (String newCouleur){
		couleur = newCouleur;
	}
	/*
	 * FIN ACCESSEURS
	 */
	
	/*
	 * Methodes "Classiques" d'un objet
	 */
	public boolean equals (Carte otherCarte){
		boolean identique;
		identique = this.valeur == otherCarte.getValeur() && this.famille.equals(otherCarte.getFamille()) && this.couleur.equals(otherCarte.getCouleur());
		return identique;
	}
	
	public String toString (){
		String chaine_resultat = "";
		chaine_resultat += this.valeur + "\n";
		chaine_resultat += this.famille + "\n";
		chaine_resultat += this.couleur;
		return chaine_resultat;
	}
}
