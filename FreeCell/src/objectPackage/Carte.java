package objectPackage;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

import constantesPackage.Constantes;

public class Carte {
	/*
	 * 3 ATTRIBUTS
	 */
	private int valeur;
	private String famille;
	private String couleur;
	BufferedImage imageCarte;
	
	/*
	 * 2 CONSTRUCTEURS
	 */
	public Carte (){
		valeur = 0;
		famille = Constantes.Carte.Famille.nul;
		couleur = Constantes.Carte.Couleur.blanche;
	}
	
	public Carte (int newValeur, String newFamille, String newCouleur){
		valeur = newValeur;
		famille = newFamille;
		couleur = newCouleur;
		String nomImage = valeur + "_" + famille + ".png";
		imageCarte = Constantes.Carte.Image.initImage(nomImage);
	}
	/*
	 * FIN CONSTRUCTEURS
	 */
	
	/*
	 * 4 ACCESSEURS
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
	public BufferedImage getImage (){
		return imageCarte;
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
	public void setImage (BufferedImage newImage){
		imageCarte = newImage;
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
	 * Methode permettant de savoir si la Carte appelante est la cons�cutive de la carte donnee
	 * en parametre
	 * @param otherCarte
	 * @return vrai si la carte appelante est d'une valeur inferieure de 1 a celle de "otherCarte" ainsi que de la meme famille; faux sinon
	 */
	public boolean estConsecutive (Carte otherCarte){
		boolean consecutive;
		consecutive = (this.valeur+1) == otherCarte.getValeur() && this.famille.equals(otherCarte.getFamille());
		return consecutive;
	}
	public boolean isEmpty (){
		boolean vide = false;
		vide = (valeur == 0) && famille.equals(Constantes.Carte.Famille.nul) && couleur.equals(Constantes.Carte.Couleur.blanche);
		return vide;
	}	
	public void clear (){
		valeur = 0;
		famille = Constantes.Carte.Famille.nul;
		couleur = Constantes.Carte.Couleur.blanche;
		imageCarte = null;
	}
	public BufferedImage copierImage() {
		BufferedImage imageACopier = this.getImage();
		ColorModel cm = imageACopier.getColorModel();
		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		WritableRaster raster = imageACopier.copyData(null);
		return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
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
		chaine_resultat += this.valeur + " ";
		chaine_resultat += this.famille + " ";
		chaine_resultat += this.couleur;
		return chaine_resultat;
	}
	public Carte clone (){
		Carte renvoi = new Carte();
		renvoi.valeur = this.valeur;
		renvoi.famille = this.famille;
		renvoi.couleur = this.couleur;
		renvoi.imageCarte = this.copierImage();
		return renvoi;
	}
}
