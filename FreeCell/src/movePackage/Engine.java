package movePackage;

import java.util.ListIterator;

import constantesPackage.Constantes;
import objectPackage.Carte;
import objectPackage.PackCard;
import objectPackage.Plateau;
import objectPackage.Stock;

public class Engine {
	private Plateau zonePrincipale;
	private Plateau zoneRangement;
	private Stock zoneStockage;
	private PackCard paquet;
	
	private int numeroColonneCourante;
	private Carte carteCourante;
	private int typePanneauDepart;
	
	/*
	 * CONSTRUCTEURS
	 */
	public Engine (Stock referenceStockage, Plateau referencePlateau, Plateau referenceRangement, PackCard referencePaquet){
		zonePrincipale = referencePlateau;
		zoneRangement = referenceRangement;
		zoneStockage = referenceStockage;
		paquet = referencePaquet;
		
		numeroColonneCourante = -1;
		typePanneauDepart = -1;
		carteCourante = new Carte();
		
		paquet.initialisation();
		zonePrincipale.initialisation(paquet);
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
	public void nouvellePartie (){
		zonePrincipale = new Plateau(Constantes.Plateau.nombreColonnes);
		zonePrincipale.initialisation(paquet);
		zoneStockage = new Stock();
		zoneRangement = new Plateau(Constantes.Plateau.nombreFamilles);
	}
	public void nouvellePartie (int numeroPartie){
		zonePrincipale = new Plateau(Constantes.Plateau.nombreColonnes);
		zonePrincipale.initialisation(numeroPartie, paquet);
		zoneStockage = new Stock();
		zoneRangement = new Plateau(Constantes.Plateau.nombreFamilles);
	}
	public void deplacementExterne (int colonneCible){
		System.out.println("deplacement du stock vers une colonne");
		if ( deplacementExternePossible(colonneCible) ){
			System.out.println("deplacement externe possible");
			effectuerDeplacementExterne(colonneCible);
		}
		else {
			System.out.println("deplacement externe impossible");
		}
		carteCourante.clear();
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
	public void stockageExterne (int numeroCarteCible){
		System.out.println("stockage d'une colonne vers le stock");
		if ( stockagePossible(numeroCarteCible) ){
			System.out.println("stockage possible");
			effectuerStockageExterne(numeroCarteCible);
		}
		else {
			System.out.println("stockage impossible");
		}
		numeroColonneCourante = -1;
	}
	public void stockageInterne (int numeroCarteCible){
		System.out.println("stockage du stock vers lui meme");
		if ( stockagePossible(numeroCarteCible) ){
			System.out.println("stockage possible");
			effectuerStockageInterne(numeroCarteCible);
		}
		else {
			System.out.println("stockage impossible");
		}
		carteCourante = new Carte();
	}
	public void rangementDepuisStock (int colonneCible){
		System.out.println("rangement depuis le stock");
		if ( rangementPossibleDepuisStock(colonneCible) ){
			System.out.println("rangement possible");
			effectuerRangementDepuisStock(colonneCible);
		}
		else {
			System.out.println("rangement impossible");
		}
		carteCourante = new Carte();
	}
	public void rangementDepuisPlateau (int colonneCible){
		System.out.println("rangement depuis le plateau");
		if ( rangementPossibleDepuisPlateau(colonneCible) ){
			System.out.println("rangement possible");
			effectuerRangementDepuisPlateau(colonneCible);
		}
		else {
			System.out.println("rangement impossible");
		}
		numeroColonneCourante = -1;
	}
	public void rangementAutomatique (){
		while ( rangementAutoStockPossible() || rangementAutoPlateauPossible() ){
			if ( rangementAutoStockPossible() ){
				effectuerRangementAutoStock();
			}
			if ( rangementAutoPlateauPossible() ){
				effectuerRangementAutoPlateau();
			}
		}
	}
	
	public boolean partieGagnee (){
		boolean victoire = true;
		int numeroColonne = 0; 
		while ( numeroColonne < zoneRangement.length() && victoire){
			victoire = zoneRangement.getColonneAt(numeroColonne).isEmpty() ? false : zoneRangement.getLastAt(numeroColonne).getValeur() == 13;
			numeroColonne++;
		}
		return victoire;
	}
	
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
			int compteurDeplacements = 1;
			
			deplacementValide = carteSource.estAlterneeInferieure(carteDestination);
			
			while ( !deplacementValide && compteurDeplacements < nbMaxDeplacements && iterateurColonneSource.hasPrevious() ){
				Carte secondeCarte = carteSource;
				carteSource = iterateurColonneSource.previous();;
				compteurDeplacements++;
				
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
			
			Carte transfert = zonePrincipale.getLastAt(numeroColonneCourante);
			zonePrincipale.getColonneTransfert().add(0, transfert.clone());
			zonePrincipale.getColonneAt(numeroColonneCourante).remove(transfert);
			compteurCartes++;
			
			if ( !zonePrincipale.getColonneAt(numeroColonneCourante).isEmpty() ){
				transfert = zonePrincipale.getLastAt(numeroColonneCourante);
				while ( compteurCartes < nbMaxDeplacements && zonePrincipale.getColonneTransfert().first().estAlterneeInferieure(transfert) ) {
					zonePrincipale.getColonneTransfert().add(0, transfert.clone());
					zonePrincipale.getColonneAt(numeroColonneCourante).remove(transfert);
					compteurCartes++;
					transfert = zonePrincipale.getLastAt(numeroColonneCourante);
				}
			}
			zonePrincipale.getColonneAt(colonneCible).addAll(zonePrincipale.getColonneTransfert());
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
		}
		zonePrincipale.clearTransfert();
	}
	
	private boolean stockagePossible (int numeroCarteCible){
		return zoneStockage.getCarteAt(numeroCarteCible).isEmpty();
	}
	private void effectuerStockageExterne (int numeroCarteCible){
		Carte transfert = zonePrincipale.getLastAt(numeroColonneCourante);
		zoneStockage.putCarteAt(numeroCarteCible, transfert.clone());
		zonePrincipale.getColonneAt(numeroColonneCourante).remove(transfert);
	}
	private void effectuerStockageInterne (int numeroCarteCible){
		zoneStockage.putCarteAt(numeroCarteCible, carteCourante.clone());
		carteCourante.clear();
	}
	
	private boolean rangementPossibleDepuisStock (int colonneCible){
		boolean rangementValide;
		if ( zoneRangement.getColonneAt(colonneCible).isEmpty() ){
			rangementValide = carteCourante.getValeur() == 1;
		}
		else {
			rangementValide = zoneRangement.getLastAt(colonneCible).estConsecutive(carteCourante);
		}
		return rangementValide;
	}
	private boolean rangementPossibleDepuisPlateau (int colonneCible){
		boolean rangementValide;
		if ( zoneRangement.getColonneAt(colonneCible).isEmpty() ){
			rangementValide = zonePrincipale.getLastAt(numeroColonneCourante).getValeur() == 1;
		}
		else {
			rangementValide = zoneRangement.getLastAt(colonneCible).estConsecutive(zonePrincipale.getLastAt(numeroColonneCourante));
		}
		return rangementValide;
	}
	private void effectuerRangementDepuisStock (int colonneCible){
		Carte transfert = carteCourante;
		zoneRangement.getColonneAt(colonneCible).add(transfert.clone());
		carteCourante.clear();
	}
	private void effectuerRangementDepuisPlateau (int colonneCible){
		Carte transfert = zonePrincipale.getLastAt(numeroColonneCourante);
		zoneRangement.getColonneAt(colonneCible).add(transfert.clone());
		zonePrincipale.getColonneAt(numeroColonneCourante).remove(transfert);
	}

	private boolean rangementAutoStockPossible (){
		boolean rangementValide = false;
		int numeroCarte = 0;
		while (numeroCarte < zoneStockage.length() && !rangementValide){
			int numeroColonne = 0;
			while (numeroColonne < zoneRangement.length() && !rangementValide){
				if ( !zoneRangement.getColonneAt(numeroColonne).isEmpty() ){
					rangementValide = zoneRangement.getLastAt(numeroColonne).estConsecutive(zoneStockage.getCarteAt(numeroCarte));
				}
				else {
					rangementValide = zoneStockage.getCarteAt(numeroCarte).getValeur() == 1;
				}
				numeroColonne++;
			}
			numeroCarte++;
		}
		return rangementValide;
	}
	private boolean rangementAutoPlateauPossible (){
		boolean rangementValide = false;
		int numeroColonnePlateau = 0;
		while (numeroColonnePlateau < zonePrincipale.length() && !rangementValide){
			int numeroColonneRangement = 0;
			while (numeroColonneRangement < zoneRangement.length() && !rangementValide){
				if ( !zonePrincipale.getColonneAt(numeroColonnePlateau).isEmpty() ){
					if ( !zoneRangement.getColonneAt(numeroColonneRangement).isEmpty() ){
						rangementValide = zoneRangement.getLastAt(numeroColonneRangement).estConsecutive(zonePrincipale.getLastAt(numeroColonnePlateau));
					}
					else {
						rangementValide = zonePrincipale.getLastAt(numeroColonnePlateau).getValeur() == 1;
					}
				}
				numeroColonneRangement++;
			}
			numeroColonnePlateau++;
		}
		return rangementValide;
	}
	private void effectuerRangementAutoStock (){
		for (int numeroCarte = 0; numeroCarte < zoneStockage.length(); numeroCarte++){
			for (int numeroColonneRangement = 0; numeroColonneRangement < zoneRangement.length(); numeroColonneRangement++){
				Carte transfert = zoneStockage.getCarteAt(numeroCarte);
				if ( zoneRangement.getColonneAt(numeroColonneRangement).isEmpty() ){
					if ( transfert.getValeur() == 1 ){
						zoneRangement.getColonneAt(numeroColonneRangement).add(transfert.clone());
						zoneStockage.putCarteAt(numeroCarte, new Carte());
					}
				}
				else {
					if ( zoneRangement.getLastAt(numeroColonneRangement).estConsecutive(transfert) ){
						zoneRangement.getColonneAt(numeroColonneRangement).add(transfert.clone());
						zoneStockage.putCarteAt(numeroCarte, new Carte());
					} 
				}
			}
		}
	}
	private void effectuerRangementAutoPlateau (){
		for (int numeroColonnePlateau = 0; numeroColonnePlateau < zonePrincipale.length(); numeroColonnePlateau++){
			for (int numeroColonneRangement = 0; numeroColonneRangement < zoneRangement.length(); numeroColonneRangement++){
				if ( !zonePrincipale.getColonneAt(numeroColonnePlateau).isEmpty() ){
					Carte transfert = zonePrincipale.getLastAt(numeroColonnePlateau);
					if ( zoneRangement.getColonneAt(numeroColonneRangement).isEmpty() ){
						if ( transfert.getValeur() == 1 ) {
							zoneRangement.getColonneAt(numeroColonneRangement).add(transfert.clone());
							zonePrincipale.getColonneAt(numeroColonnePlateau).remove(transfert);
						}
					}
					else {
						if ( zoneRangement.getLastAt(numeroColonneRangement).estConsecutive(transfert) ){
							zoneRangement.getColonneAt(numeroColonneRangement).add(transfert.clone());
							zonePrincipale.getColonneAt(numeroColonnePlateau).remove(transfert);
						}
					}
				}
			}
		}
	}
}
