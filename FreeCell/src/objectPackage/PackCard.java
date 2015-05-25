package objectPackage;

import java.util.ArrayList;
import java.util.ListIterator;

import constantesPackage.Constantes;

public class PackCard extends ArrayList<Carte>{
	
	/*
	 * CONSTRUCTEUR
	 */
	public PackCard (){
		super();
	}
	
	/*
	 * Methodes Private de PackCard
	 */
	private void initialisation (){
		for (int i = 0; i < Constantes.Plateau.totalCartes; i++){
			int valeur = (i+1) % Constantes.Plateau.nombreFamilles;
			int indiceFamille = i / Constantes.Plateau.nombreCartes;
			int indiceCouleur = indiceFamille / 2;
			String famille = Constantes.Carte.Famille.tabFamille[indiceFamille];
			String couleur = Constantes.Carte.Couleur.tabCouleur[indiceCouleur];
			Carte card = new Carte(valeur, famille, couleur);
			this.add(card);
		}
	}
	
	/*
	 * Methodes "Classiques" de PackCard
	 */
	public PackCard clone (){
		PackCard renvoi = new PackCard();
		ListIterator<Carte> iterateurPackCard = this.listIterator();
		while ( iterateurPackCard.hasNext() ){
			renvoi.add(iterateurPackCard.next().clone());
		}
		return renvoi;
	}
	
	public String toString (){
		String chaine_resultat = "PackCard : { ";
		ListIterator<Carte> iterateurPackCard = this.listIterator();
		while ( iterateurPackCard.hasNext() ){
			chaine_resultat += iterateurPackCard.next().toString() + "; ";
		}
		return chaine_resultat;
	}
}
