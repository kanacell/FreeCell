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
	 * Methodes Public de Carte
	 */
	/**
	 * Methode permettant de savoir si la Carte appelante peut etre posee
	 * sur une carte donnee en parametre
	 * @param otherCarte
	 * @return vrai si la carte appelante est d'une valeur inferieure de 1 a celle de "otherCarte" ainsi que d'une couleur differente; faux sinon
	 */
	public boolean estAlterneeInferieure (Carte otherCarte){
		boolean alternee;
		alternee = (this.valeur+1) == otherCarte.getValeur() && !this.couleur.equals(otherCarte.getCouleur());
		return alternee;
	}
	
	/**
	 * Methode permettant de savoir si la Carte appelante est la consécutive de la carte donnee
	 * en parametre
	 * @param otherCarte
	 * @return vrai si la carte appelante est d'une valeur inferieure de 1 a celle de "otherCarte" ainsi que de la meme famille; faux sinon
	 */
	public boolean estConsecutive (Carte otherCarte){
		boolean consecutive;
		consecutive = (this.valeur+1) == otherCarte.getValeur() && this.famille.equals(otherCarte.getFamille());
		return consecutive;
	}
	
	/*
	 * FIN Methodes Public de Carte
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
	
	public Carte clone (){
		Carte renvoi = new Carte();
		renvoi.valeur = this.valeur;
		renvoi.famille = this.famille;
		renvoi.couleur = this.couleur;
		return renvoi;
	}
}
