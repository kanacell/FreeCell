package movePackage;

import java.util.ListIterator;

import objectPackage.Carte;
import objectPackage.Plateau;
import objectPackage.Stock;

public class Engine {
	private Plateau zonePrincipale;
	private Plateau zoneRangement;
	private Stock zoneStockage;
	private int numeroColonneCourante;
	private Carte carteCourante;
	private int typePanneauDepart;
	
	/*
	 * CONSTRUCTEURS
	 */
	public Engine (Stock referenceStockage, Plateau referencePlateau, Plateau referenceRangement){
		zonePrincipale = referencePlateau;
		zoneRangement = referenceRangement;
		zoneStockage = referenceStockage;
		numeroColonneCourante = -1;
		typePanneauDepart = -1;
		carteCourante = new Carte();
	}
	/*
	 * FIN CONSTRUCTEURS
	 */
	
	/*
	 * ACCESSEURS
	 */
	public Plateau getZonePrincipale (){
		return zonePrincipale;
	}
	public Plateau getZoneRangement (){
		return zoneRangement;
	}
	public Stock getZoneStockage (){
		return zoneStockage;
	}
	public int getNumeroColonneCourante (){
		return numeroColonneCourante;
	}
	public Carte getCarteCourante (){
		return carteCourante;
	}
	public int getTypePanneauDepart (){
		return typePanneauDepart;
	}
	
	public void setZonePrincipale (Plateau referencePrincipale){
		zonePrincipale = referencePrincipale;
	}
	public void setZoneRangement (Plateau referenceRangement){
		zoneRangement = referenceRangement;
	}
	public void setZoneStockage (Stock referenceStockage){
		zoneStockage = referenceStockage;
	}
	public void setNumeroColonneCourante (int newColonneCourante){
		numeroColonneCourante = newColonneCourante;
	}
	public void setCarteCourante (Carte referenceCarteCourante){
		carteCourante = referenceCarteCourante;
	}
	public void setTypePanneauDepart (int newTypePanneauDepart){
		typePanneauDepart = newTypePanneauDepart;
	}
	/*
	 * FIN ACCESSEURS
	 */
	
	/*
	 * Methodes Public de Engine
	 */
	public void deplacementExterne (int colonneCible){
		System.out.println("deplacement du stock vers une colonne");
		if ( deplacementExternePossible(colonneCible) ){
			System.out.println("deplacement externe possible");
			effectuerDeplacementExterne(colonneCible);
			carteCourante.clear();
		}
		else {
			System.out.println("deplacement externe impossible");
		}
		carteCourante = new Carte();
	}
	public void deplacementInterne (int colonneCible){
		System.out.println("deplacement d'une colonne vers une autre");
		if ( deplacementInternePossible(colonneCible) ){
			System.out.println("deplacement interne possible");
			effectuerDeplacementInterne(colonneCible);
		}
		else {
			System.out.println("deplacement interne impossible");
		}
		numeroColonneCourante = -1;
	}
	public void rangement (){
		
	}

/*
	public void ajouterCarte (Carte referenceCarte, int colonneCible){
		Carte transfert = referenceCarte.clone();
		zonePrincipale.getColonneAt(colonneCible).add(transfert);
		referenceCarte.clear();
	}
*/
	
	/*
	 * Methodes Private de Engine
	 */
	private boolean deplacementExternePossible (int colonneCible){
		boolean deplacementValide = false;
		
		Carte carteDestination = ( zonePrincipale.getColonneAt(colonneCible).isEmpty() ) ? new Carte() : zonePrincipale.getLastAt(colonneCible);
		deplacementValide = zonePrincipale.getColonneAt(colonneCible).isEmpty() || carteCourante.estAlterneeInferieure(carteDestination);

		return deplacementValide;
	}
	private boolean deplacementInternePossible (int colonneCible){
		boolean deplacementValide = false;
		
		if ( zonePrincipale.getColonneAt(colonneCible).isEmpty() ){
			deplacementValide = true;
		}
		else {
			Carte carteDestination = zonePrincipale.getLastAt(colonneCible);
			ListIterator<Carte> iterateurColonneSource = zonePrincipale.getColonneAt(numeroColonneCourante).listIterator(zonePrincipale.getColonneAt(numeroColonneCourante).size());
			Carte carteSource = iterateurColonneSource.previous();
			
			int nbColonnesVides = zonePrincipale.nombreColonnesVides();
			int nbCartesVides = zoneStockage.nombreCasesVides();
			int nbMaxDeplacements = (nbColonnesVides + 1) * (nbCartesVides + 1);
			int compteurDeplacements = 0;
			
			deplacementValide = carteSource.estAlterneeInferieure(carteDestination);
			
			System.out.println("\t avant boucle : ");
			System.out.println("carteSource : " + carteSource.toString());
			while ( !deplacementValide && compteurDeplacements < nbMaxDeplacements && iterateurColonneSource.hasPrevious() ){
				Carte secondeCarte = carteSource;
				carteSource = iterateurColonneSource.previous();;
				compteurDeplacements++;
				
				System.out.println("cartes seconde/source : " + secondeCarte.toString() + " || " + carteSource.toString());
				System.out.println("compteurs deplacement/Max : " + compteurDeplacements + " " + nbMaxDeplacements);
				
				deplacementValide = secondeCarte.estAlterneeInferieure(carteSource) && carteSource.estAlterneeInferieure(carteDestination);
			}
		}
		
		return deplacementValide;
	}
	private void effectuerDeplacementExterne (int colonneCible){
		Carte transfert = carteCourante.clone();
		zonePrincipale.getColonneAt(colonneCible).add(transfert);
	}
	private void effectuerDeplacementInterne (int colonneCible){
		if ( zonePrincipale.getColonneAt(colonneCible).isEmpty() ){
			int nbColonnesVides = zonePrincipale.nombreColonnesVides();
			int nbCartesVides = zoneStockage.nombreCasesVides();
			int nbMaxDeplacements = nbColonnesVides * (nbCartesVides + 1);
			int compteurCartes = 0;
			
			ListIterator<Carte> iterateurColonneSource = zonePrincipale.getColonneAt(numeroColonneCourante).listIterator(zonePrincipale.getColonneAt(numeroColonneCourante).size());
			while ( compteurCartes < nbMaxDeplacements && iterateurColonneSource.hasPrevious() ){
				Carte transfert = iterateurColonneSource.previous();
				zonePrincipale.getColonneAt(colonneCible).add(0, transfert.clone());
				zonePrincipale.getColonneAt(numeroColonneCourante).remove(transfert);
				compteurCartes++;
			}
		}
		else {
			int indexAjout = zonePrincipale.getColonneAt(colonneCible).size();
			Carte carteDestination = zonePrincipale.getLastAt(colonneCible);
			Carte carteSource = zonePrincipale.getLastAt(numeroColonneCourante);
			
			while ( !carteSource.estAlterneeInferieure(carteDestination) ){
				zonePrincipale.getColonneTransfert().add(0, carteSource.clone());
				zonePrincipale.getColonneAt(numeroColonneCourante).remove(carteSource);
				carteSource = zonePrincipale.getLastAt(numeroColonneCourante);
			}
			zonePrincipale.getColonneTransfert().add(0, carteSource.clone());
			zonePrincipale.getColonneAt(numeroColonneCourante).remove(carteSource);
			zonePrincipale.getColonneAt(colonneCible).addAll(indexAjout, zonePrincipale.getColonneTransfert());
			zonePrincipale.clearTransfert();
		}
		
	}
}
