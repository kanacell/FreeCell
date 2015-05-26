package constantesPackage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Constantes {
	public static class Carte {
		public static class Famille {
			public static final String coe = "coe";
			public static final String car = "car";
			public static final String piq = "piq";
			public static final String tre = "tre";
			public static final String nul = "nul";
			
			public static final String[] tabFamille = { coe, car, piq, tre };
		}
		
		public static class Couleur {
			public static final String blanche = "blanche";
			public static final String rouge = "rouge";
			public static final String noire = "noire";
			
			public static final String[] tabCouleur = { rouge, noire };
		}
	
		public static class Image {
			public static BufferedImage initImage (String nomImage){
				BufferedImage imageRetournee = null;
				String chemin = "ImagesCartes/" + nomImage;
				try {
					imageRetournee = ImageIO.read(new File (chemin));
				} catch (IOException e){
					System.out.println("aucun fichier de ce nom trouve");
				}
				return imageRetournee;
			}
		}
	}
	
	public static class Plateau {
		public static final int nombreColonnes = 8;
		public static final int nombreCartes = 13;
		public static final int nombreFamilles = 4;
		public static final int nombreCellules = 4;
		public static final int totalCartes = nombreCartes * nombreFamilles;
	}
	
	public static class Panneau {
		public static final int zoneNulle = -1;
		public static final int zoneDeJeu = 1;
		public static final int zoneDeRangement = 2;
		public static final int zoneDeStockage = 3;
		public static final int largeurCarte = 60;
		public static final int hauteurCarte = 90;
	}
	
}
